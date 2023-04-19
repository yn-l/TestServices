package com.lebedev.test.warehouse.Model;

public enum ProductErrorType {
    DUPLICATE(1001),
    EXCEED_AMOUNT(1002),
    NOT_EXIST(1003),
    ;


    public final int code;

    ProductErrorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
