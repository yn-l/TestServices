package com.lebedev.test.Orders;

import com.lebedev.test.Orders.Repository.OrderRepository;
import com.lebedev.test.Orders.Service.OrderService;
import com.lebedev.test.Orders.Service.OrderServiceHelper;
import com.lebedev.test.Orders.Service.ProductRestClient;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class OrdersApplicationTests {
	@Autowired
	ProductRestClient restClient;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	OrderServiceHelper orderServiceHelper;
	@Autowired
	OrderService orderService;
	@Autowired
	ModelMapper modelMapper;

	@Test
	void contextLoads() {
		assertThat(modelMapper).isNotNull();
		assertThat(orderService).isNotNull();
		assertThat(restClient).isNotNull();
		assertThat(orderServiceHelper).isNotNull();
		assertThat(orderRepository).isNotNull();
	}

}
