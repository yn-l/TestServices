package com.lebedev.test.warehouse.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class
 */
@Configuration
public class Config {

    /**
     * @return {@link ModelMapper} a ModelMapper
     */
    @Bean
    ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
