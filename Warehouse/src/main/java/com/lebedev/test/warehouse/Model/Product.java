package com.lebedev.test.warehouse.Model;

/**
 * Product class
 */
public class Product {
    private Long productId;
    private String name;
    private String description;
    private Integer amount = 0;

    /**
     * Empty constructor of Product
     */
    public Product() {
    }

    /**
     * Product constructor
     * @param productId - product id
     * @param name - name of product
     * @param descr - desription of product
     * @param amount - amount of product
     */
    public Product(long productId, String name, String descr, Integer amount) {
        this.productId = productId;
        this.name = name;
        this.description = descr;
        this.amount = amount;
    }

    /**
     * @return product id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * @param productId product id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param name product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return amount of product
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount amount of product
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
