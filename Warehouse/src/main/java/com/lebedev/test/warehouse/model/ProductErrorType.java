package com.lebedev.test.warehouse.model;

/**
 * Type of Product error
 */
public enum ProductErrorType {
    /**
     * Duplicate error type
     */
    DUPLICATE(1001),
    /**
     * Amount of requested product(s) exceed amount in DB
     */
    EXCEED_AMOUNT(1002),
    /**
     * Product not exist in service
     */
    NOT_EXIST(1003),
    ;


    /**
     * Error code
     */
    public final int code;

    ProductErrorType(int code) {
        this.code = code;
    }

    /**
     * @return code of the error
     */
    public int getCode() {
        return this.code;
    }
}
