package com.lebedev.test.Warehouse.Controller;

import com.lebedev.test.Warehouse.Model.Product;
import com.lebedev.test.Warehouse.Model.ProductException;
import com.lebedev.test.Warehouse.Model.ProductStockUpdate;
import com.lebedev.test.Warehouse.Service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.json.ParseException;
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
