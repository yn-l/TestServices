package com.lebedev.test.Orders.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @param productId
 * @param amount
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record Product(Long productId, Integer amount) {
}
