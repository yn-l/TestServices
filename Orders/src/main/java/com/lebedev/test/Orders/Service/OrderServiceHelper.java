package com.lebedev.test.Orders.Service;

import com.lebedev.test.Orders.Model.OrderEntity;
import com.lebedev.test.Orders.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderServiceHelper {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(rollbackOn = {Exception.class})
    Long saveOrderEntity(OrderEntity orderEntity) {
        Long orderId = orderRepository.getNextValueFromSequence();
        orderEntity.setOrderId(orderId);
        for (var p : orderEntity.getProducts()) {
            p.setOrderId(orderId);
        }
        orderRepository.save(orderEntity);
        return orderId;
    }

}
