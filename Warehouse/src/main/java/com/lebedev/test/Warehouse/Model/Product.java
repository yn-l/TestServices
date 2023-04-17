package com.lebedev.test.Warehouse.Model;

/**
 * Product class
 */
public class Product {
    private Long productId;
    private String name;
    private String description;
    private Integer amount = 0;

    public Product() {
    }

    public Product(long productId, String name, String descr, Integer amount) {
        this.productId = productId;
        this.name = name;
        this.description = descr;
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
