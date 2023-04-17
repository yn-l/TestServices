package com.lebedev.test.Orders.Controller;

import com.lebedev.test.Orders.Model.OrderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {OrderException.class})
    protected ResponseEntity<Object> handleProductException(OrderException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.toMap(),
                new HttpHeaders(), ex.getHttpStatus(), request);
    }

    @ExceptionHandler(value = { ResourceAccessException.class})
    protected ResponseEntity<Object> handleConnectException(ResourceAccessException ex, WebRequest request) {
        Map<String, Map<String, String>> bodyOfResponse = Map.of("error", Map.of("message", ex.getMessage()));
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleProductException(HttpClientErrorException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

