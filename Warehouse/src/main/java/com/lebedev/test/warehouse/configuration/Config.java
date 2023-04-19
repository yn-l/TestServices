package com.lebedev.test.warehouse.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class
 */
@Configuration
public class Config {

    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
