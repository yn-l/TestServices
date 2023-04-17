package com.lebedev.test.Orders.Controller;

import com.lebedev.test.Orders.Model.Order;
import com.lebedev.test.Orders.Model.OrderException;
import com.lebedev.test.Orders.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {

    Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/orders/order",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Map<String, Long> saveOrder(@RequestBody Order order) throws Exception {
        String method = this.getClass().getName() + "." + Thread.currentThread().getStackTrace()[1].getMethodName();
        logger.info("Start " + method);
        Long newId = orderService.createOrder(order);
        logger.info("Created order: " + order);
        return Map.of("orderId", newId);
    }

    @DeleteMapping(value = "/orders/order/id/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable Long id) throws OrderException {

        Boolean isSuccess = orderService.deleteOrder(id);
        System.out.println("Order was deleted");
        return Map.of("isSuccess", isSuccess);
    }
}
