package com.lebedev.test.Orders.Model;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Query;

import java.util.Objects;

@Entity
@Table(name = "order_products")
@IdClass(OrderProductPK.class)
public class OrderProductsEntity {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Id
    @Column(name = "product_id")
    private Long productId;
    private Integer amount;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
