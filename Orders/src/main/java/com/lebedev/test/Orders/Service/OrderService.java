package com.lebedev.test.Orders.Service;

import com.lebedev.test.Orders.Model.*;
import com.lebedev.test.Orders.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    ProductRestClient restClient;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderServiceHelper orderServiceHelper;

    @Autowired
    private OrderRepository orderRepository;

    /**
     * Save new order
     *
     * @param order
     * @return @{@link Long} id of saved Order
     */
    public Long createOrder(Order order) throws OrderException {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        // try to reserve product on Warehouse service
        boolean isProductsReserved = false;
        try {
            List<ProductStockUpdate> updateProductList = orderEntity.getProducts()
                    .stream()
                    .map(p -> new ProductStockUpdate(p.getProductId(), -1 * p.getAmount())).toList();
            isProductsReserved = restClient.updateProductSock(updateProductList);

            if (!isProductsReserved) {
                throw new OrderException(OrderErrorType.PRODUCT_RESERVATION, "Product(s) was not reserves in warehouse.", HttpStatus.FORBIDDEN);
            } else {
                return orderServiceHelper.saveOrderEntity(orderEntity);
            }
        } catch (Exception ex) {
            System.err.println(ex);
            if (isProductsReserved) {
                // rollback reserved product in warehouse
                List<ProductStockUpdate> updateProductList = orderEntity.getProducts().stream().map(p -> new ProductStockUpdate(p.getProductId(), p.getAmount())).toList();
                try {
                    restClient.updateProductSock(updateProductList);
                } catch (Exception rex) {
                    System.err.println(rex);
                }
            }
            throw ex;
        }

    }

    /**
     * @param id
     * @return
     */
    @Transactional
    public Boolean deleteOrder(Long id) throws OrderException {
        boolean isProductReserved = false;
        try {
            Optional<OrderEntity> orderEntity = orderRepository.findById(id);
            if (orderEntity.isPresent()) {
                List<ProductStockUpdate> updateProductList = orderEntity.get().getProducts().stream().map(p -> new ProductStockUpdate(p.getProductId(), p.getAmount())).toList();
                isProductReserved = restClient.updateProductSock(updateProductList);
                if (isProductReserved)
                    orderRepository.deleteById(id);
            } else
                throw new OrderException(OrderErrorType.NOT_FOUND, "Order does not exist", HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            if (isProductReserved) {
                // TODO rollback reserved product in warehouse
            }
            System.out.println(ex);
            throw ex;
        }
        return true;
    }
}
