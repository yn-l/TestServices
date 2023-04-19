package com.lebedev.test.warehouse.model;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ProductException extends Exception {

    private ProductErrorType error;
    private HttpStatus httpStatus;

    public ProductException(ProductErrorType err, String msg) {
        super(msg);
        this.error = err;
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public ProductException(ProductErrorType error, String message, HttpStatus httpCode) {
        super(message);
        this.error = error;
        this.httpStatus = httpCode;
    }

    public ProductErrorType getError() {
        return error;
    }

    public void setError(ProductErrorType error) {
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, Map> toMap() {
        return Map.of("error", Map.of("errorCode", error.getCode(), "message", this.getMessage()));
    }
}
