package com.lebedev.test.Orders.Model;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class OrderException extends Exception {

    private final OrderErrorType error;
    private final HttpStatus httpStatus;

    public OrderException(OrderErrorType err, String msg) {
        super(msg);
        this.error = err;
        this.httpStatus = HttpStatus.CONFLICT;
    }

    public OrderException(OrderErrorType error, String message, HttpStatus httpCode) {
        super(message);
        this.error = error;
        this.httpStatus = httpCode;
    }

    public OrderErrorType getError() {
        return error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Map<String, Map> toMap() {
        return Map.of("error", Map.of("errorCode", error.getCode(), "message", this.getMessage()));
    }
}
