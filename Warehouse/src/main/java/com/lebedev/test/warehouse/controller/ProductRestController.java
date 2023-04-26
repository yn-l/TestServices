package com.lebedev.test.warehouse.controller;

import com.lebedev.test.warehouse.model.Product;
import com.lebedev.test.warehouse.model.ProductException;
import com.lebedev.test.warehouse.model.ProductStockUpdate;
import com.lebedev.test.warehouse.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class ProductRestController implements ProductApi{
    @Autowired
    ProductService productService;


    public Product saveProduct(@RequestBody Product product) throws ProductException {
        Product newPoduct = productService.save(product);
        System.out.println("Created: " + newPoduct);
        return newPoduct;
    }

    public Product getProductById(@PathVariable(name = "id") String id, HttpServletResponse res) {

        Product result = productService.getById(Long.valueOf(id));
        if (result != null)
            return result;
        else {
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    public Product getProductByName(@PathVariable(name = "name") String name, HttpServletResponse res) {

        Product result = productService.getByName(name);
        if (result != null)
            return result;
        else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    public List<Product> getAllProduct(HttpServletResponse res) {

        List<Product> result = productService.getAllProduct();
        if (result != null)
            return result;
        else
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }

    public List<Product> updateProduct(@RequestBody List<ProductStockUpdate> productUpdateList, HttpServletResponse res) throws ProductException {
        List<Product> result = productService.updateProduct(productUpdateList);
        if (result != null) return result;
        else throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Product amount was not updated");
    }

}
