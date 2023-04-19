package com.lebedev.test.warehouse.controller;

import com.lebedev.test.warehouse.Model.Product;
import com.lebedev.test.warehouse.Model.ProductException;
import com.lebedev.test.warehouse.Model.ProductStockUpdate;
import com.lebedev.test.warehouse.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.ParseException;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/")
public class RestController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/warehouse/product",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public Map<String, Long> saveProduct(@RequestBody Product product) throws ProductException {

        Long newId = productService.save(product);
        System.out.println("Created: " + product);
        return Map.of("productId", newId);
    }

    @GetMapping("/warehouse/product/id/{id}")
    @RouterOperation(operation = @Operation(description = "Get Product by Id", operationId = "getProductById", tags = "persons",
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id") },
            responses = @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = Product.class)))))
    public Product getProductById(@PathVariable(name = "id", required = true) String id, HttpServletResponse res) throws ParseException {

        Product result = productService.getById(Long.valueOf(id));
        if (result != null)
            return result;
        else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @GetMapping("/warehouse/product/name/{name}")
    public Product getProductByName(@PathVariable(name = "name", required = true) String name, HttpServletResponse res) {

        Product result = productService.getByName(name);
        if (result != null)
            return result;
        else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @GetMapping("/warehouse/products")
    public List<Product> getAllProduct(HttpServletResponse res) {

        List<Product> result = productService.getAllProduct();
        if (result != null)
            return result;
        else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    @PutMapping(value = "/warehouse/product",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Product> updateProduct(@RequestBody List<ProductStockUpdate> productUpdateList, HttpServletResponse res) throws ProductException {

        List<Product> result = productService.updateProduct(productUpdateList);
        if (result != null) return result;
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Product amount was not updated");
    }

}
