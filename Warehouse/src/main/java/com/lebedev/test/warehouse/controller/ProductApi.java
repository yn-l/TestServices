package com.lebedev.test.warehouse.controller;

import com.lebedev.test.warehouse.model.Product;
import com.lebedev.test.warehouse.model.ProductException;
import com.lebedev.test.warehouse.model.ProductStockUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Product", description = "The product API")
public interface ProductApi {

    @Operation(summary = "Add a new product", description = "Add a new product to the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/warehouse/product",
                    consumes = {MediaType.APPLICATION_JSON_VALUE},
                    produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    Map<String, Long> saveProduct(@Parameter(description = "Create a new product in the warehouse", required = true) @RequestBody Product product) throws ProductException;


    @Operation(summary = "Update product", description = "Update product in the warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PutMapping(value = "/warehouse/product",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Product> updateProduct(@Parameter(description = "Update a product in the warehouse", required = true)@RequestBody List<ProductStockUpdate> productUpdateList, HttpServletResponse res) throws ProductException;

    @Operation(summary = "Get all products", description = "Get list of all product from warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class))) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @GetMapping("/warehouse/products")
    List<Product> getAllProduct(HttpServletResponse res);


    @Operation(summary = "Get Product by Id", description = "Get Product by Id from warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @GetMapping("/warehouse/product/id/{id}")
    Product getProductById(@Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id") @PathVariable(name = "id", required = true) String id, HttpServletResponse res);

    @Operation(summary = "Get Product by name", description = "Get Product by name from warehouse")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = { @Content(mediaType = "application/json",schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @GetMapping("/warehouse/product/name/{name}")
    Product getProductByName(@Parameter(in = ParameterIn.PATH, name = "name", description = "The name of the product") @PathVariable(name = "name", required = true) String name, HttpServletResponse res);


    }
