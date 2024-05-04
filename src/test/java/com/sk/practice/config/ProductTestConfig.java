package com.sk.practice.config;

import com.sk.practice.product.repository.ProductQueryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductTestConfig {

    //
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public ProductQueryRepository productQueryRepository(){
        return new ProductQueryRepository(entityManager);
    }
}
