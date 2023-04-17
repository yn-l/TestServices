package com.lebedev.test.Warehouse.Service;

import com.lebedev.test.Warehouse.Model.*;
import com.lebedev.test.Warehouse.Repository.WarehouseRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    WarehouseRepository whRepository;

    /**
     * Add product in to DB
     *
     * @param model @{@link Product} new product to store in DB
     * @return - @{@link Long} id of saved product
     * @throws @{@link ProductException}
     */
    public Long save(Product model) throws ProductException {
        try {
            ProductEntity entity = modelMapper.map(model, ProductEntity.class);
            ProductEntity storedProduct = whRepository.save(entity);
            return storedProduct.getProductId();
        } catch (Exception se) {
            var msg = "Some unexpected erro occured";
            if (se.getCause() instanceof ConstraintViolationException &&
                    ((ConstraintViolationException) se.getCause()).getConstraintName().equalsIgnoreCase("warehouse_name_unq")) {
                msg = "Product already exist in DB. Please update amount.";
            }
            throw new ProductException(ProductErrorType.DUPLICATE, msg);
        }
    }

    public Product getById(Long id) {
        Optional<ProductEntity> entity = whRepository.findById(id);
        if (entity.isPresent())
            return modelMapper.map(entity.get(), Product.class);
        else
            return null;
    }

    public Product getByName(String name) {
        List<ProductEntity> entities = whRepository.findByName(name);
        // Db has unique constraint on name field so the value ca be only one
        return entities.stream().map(entity -> modelMapper.map(entity, Product.class)).findFirst().orElse(null);
    }

    public List<Product> getAllProduct() {
        List<ProductEntity> entities = whRepository.findAll();
        if (!entities.isEmpty())
            return entities.stream().map(entity -> modelMapper.map(entity, Product.class)).toList();
        else
            return null;
    }

    /**
     * Update anount of products in warehouse
     *
     * @param productUpdateList - list of @{@link ProductStockUpdate}
     * @return @List<{@link Product>} list of updated products
     * @throws @{@link ProductException}
     */
    @Transactional(rollbackOn = {SQLException.class, ProductException.class})
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public List<Product> updateProduct(List<ProductStockUpdate> productUpdateList) throws ProductException {

        List<Product> result = new ArrayList<>();
        for (var productUpdate : productUpdateList) {
            Optional<ProductEntity> entity = whRepository.findById(productUpdate.getProductId());
            ProductEntity product = entity.orElseThrow(
                    () -> new ProductException(ProductErrorType.NOT_EXIST, "Product with id[" + productUpdate.getProductId() + "] not found in warehouse", HttpStatus.NOT_FOUND)
            );
            Integer newAmount = product.getAmount() + productUpdate.getAmountUpdate();
            if (newAmount >= 0) {
                product.setAmount(newAmount);
                ProductEntity updatedEntity = whRepository.save(product);
                result.add(modelMapper.map(updatedEntity, Product.class));
            } else {
                throw new ProductException(ProductErrorType.EXCEED_AMOUNT, "Amount of product with id[" + product.getProductId() + "] is not enough in warehouse.");
            }
        }
        return result;
    }
}
