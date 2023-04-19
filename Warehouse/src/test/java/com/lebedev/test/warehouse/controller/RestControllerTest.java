package com.lebedev.test.warehouse.controller;

import com.lebedev.test.warehouse.Model.Product;
import com.lebedev.test.warehouse.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestController.class)
@ActiveProfiles("test")
class RestControllerTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Test for get all product endpoint")
    public void getAllProduct() throws Exception {
        List<Product> result = List.of(new Product(11L, "TestProduct", null, 12));
        when(this.productService.getAllProduct()).thenReturn(result);

        mvc.perform(MockMvcRequestBuilders
                        .get("/warehouse/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productId").value(11l))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("TestProduct"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].amount").value(12));
    }

    @Test
    @DisplayName("Test for get product by Id endpoint")
    void getProductById() throws Exception {
        Product result = new Product(11L, "TestProduct", "Descr", 12);
        when(this.productService.getById(1l)).thenReturn(result);

        mvc.perform(MockMvcRequestBuilders
                        .get("/warehouse/product/id/{id}", 1l)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(11l))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("TestProduct"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Descr"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(12));
    }

    @Test
    void getProductByName() {
        // TODO
    }


    @Test
    void saveProduct() {
        // TODO
    }

    @Test
    void updateProduct() {
        // TODO
    }
}