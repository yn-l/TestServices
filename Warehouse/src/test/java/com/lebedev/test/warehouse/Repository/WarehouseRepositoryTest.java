package com.lebedev.test.warehouse.Repository;

import com.lebedev.test.warehouse.model.ProductEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class WarehouseRepositoryTest {

    @Autowired
    private WarehouseRepository whRepository;

    @Test
    @DisplayName("JUnit test for get all employees operation")
    void findByName() {
        ProductEntity prod = new ProductEntity();
        prod.setName("Thing");
        prod.setDescription("Some strange thing");
        prod.setAmount(1);

        // when - action or the behaviour that we are going test
        ProductEntity savedProduct = whRepository.save(prod);

        // then - verify the output
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.getProductId()).isGreaterThan(0);
    }

    // TODO add another tests
}