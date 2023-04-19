package com.lebedev.test.warehouse;

import com.lebedev.test.warehouse.controller.RestController;
import com.lebedev.test.warehouse.Repository.WarehouseRepository;
import com.lebedev.test.warehouse.service.ProductService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class WarehouseApplicationTests {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	WarehouseRepository whRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private RestController controller;

	@Test
	void contextLoads() {
		assertThat(modelMapper).isNotNull();
		assertThat(whRepository).isNotNull();
		assertThat(productService).isNotNull();
		assertThat(controller).isNotNull();
	}

}
