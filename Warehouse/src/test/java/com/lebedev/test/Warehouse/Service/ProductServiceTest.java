package com.lebedev.test.Warehouse.Service;

import com.lebedev.test.Warehouse.Model.Product;
import com.lebedev.test.Warehouse.Model.ProductEntity;
import com.lebedev.test.Warehouse.Model.ProductException;
import com.lebedev.test.Warehouse.Repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {

    @MockBean
    WarehouseRepository whRepository;

    @Autowired
    private ProductService productService;

    @Test
    void save() throws ProductException {
        ProductEntity entity = new ProductEntity();
        entity.setProductId(1l);
        when(whRepository.save(any())).thenReturn(entity);

        Product model = new Product(1, "Product1", null, 2);
        Long newProductId = productService.save(model);
        assertThat(newProductId).isGreaterThan(0);
    }

    @Test
    void getById() {
        // TODO
    }

    @Test
    void getByName() {
        // TODO
    }

    @Test
    void getAllProduct() {
        // TODO
    }

    @Test
    void updateProduct() {
        // TODO
    }
}