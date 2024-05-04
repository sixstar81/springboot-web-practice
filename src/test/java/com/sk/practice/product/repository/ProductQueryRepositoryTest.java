package com.sk.practice.product.repository;

import com.sk.practice.config.ProductTestConfig;
import com.sk.practice.product.Product;
import com.sk.practice.product.SellType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@Import(ProductTestConfig.class)
class ProductQueryRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    ProductQueryRepository productQueryRepository;

    @Test
    void test(){
        Product lee1 = Product.builder().price(101.0).name("lee1").registerTime(LocalDateTime.now()).sellType(SellType.SELLING).build();
        Product lee2 = Product.builder().price(102.0).name("lee2").registerTime(LocalDateTime.now()).sellType(SellType.SELLING).build();
        Product lee3 = Product.builder().price(103.0).name("lee3").registerTime(LocalDateTime.now()).sellType(SellType.SELLING).build();
        em.persist(lee1);
        em.persist(lee2);
        em.persist(lee3);
        List<Product> products = productQueryRepository.productList(null);
        products.forEach(System.out::println);
    }
}