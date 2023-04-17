package com.lebedev.test.Orders.Model;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductPK implements Serializable {

    private long orderId;
    private Long productId;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderProductPK that)) return false;
        return getOrderId() == that.getOrderId() && Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getProductId());
    }
}
