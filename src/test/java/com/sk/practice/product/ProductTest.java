package com.sk.practice.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@DataJpaTest
public class ProductTest {

    @PersistenceContext EntityManager em;

    @Test
    void saveProduct(){
        Product product = Product.builder().name("radio").price(100.0).sellType(SellType.SELLING).build();
        em.persist(product);

        Product result = em.find(Product.class, product.getId());
        Assertions.assertThat(result.getName()).isEqualTo("radio");
    }
}
