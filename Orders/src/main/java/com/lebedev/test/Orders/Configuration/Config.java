package com.lebedev.test.Orders.Configuration;

import com.lebedev.test.Orders.Model.Order;
import com.lebedev.test.Orders.Model.OrderEntity;
import com.lebedev.test.Orders.Model.OrderProductsEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class Config {

    @Bean
    ModelMapper getModelMapper() {

        ModelMapper mapper = new ModelMapper();
        TypeMap<Order, OrderEntity> orderProductMapper = mapper.createTypeMap(Order.class, OrderEntity.class);
        orderProductMapper.addMappings(
                opMapper -> opMapper.map(src -> src.getProducts(), OrderEntity::setProductsFromMap)
        );

        return mapper;
    }


}
