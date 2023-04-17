package com.lebedev.test.Orders.Model;

public enum OrderErrorType {
    PRODUCT_RESERVATION(2001),
    NOT_FOUND(2002)
    ;

    public final int code;

    OrderErrorType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
