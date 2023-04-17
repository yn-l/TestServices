package com.lebedev.test.Orders.Model;

public class ProductStockUpdate {

    private Long productId;

    /**
     * Changes for amount of product, can have negative and positive value
     */
    private Integer amountUpdate;

    public ProductStockUpdate(Long productId, Integer amountUpdate) {
        this.productId = productId;
        this.amountUpdate = amountUpdate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAmountUpdate() {
        return amountUpdate;
    }

    public void setAmountUpdate(Integer amountUpdate) {
        this.amountUpdate = amountUpdate;
    }
}

