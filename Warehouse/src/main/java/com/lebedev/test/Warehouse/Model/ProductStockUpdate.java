package com.lebedev.test.Warehouse.Model;

/**
 * Contains changes for amount of productId that should be applied to warehouse
 */
public class ProductStockUpdate {

    private Long productId;

    /**
     * Changes for amount of product, can have negative and positive value
     */
    private Integer amountUpdate;

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
