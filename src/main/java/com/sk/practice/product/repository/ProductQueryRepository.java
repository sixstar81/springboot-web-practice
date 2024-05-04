package com.sk.practice.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.practice.product.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sk.practice.product.QProduct.product;

@Repository
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;

    public ProductQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Product> productList(Pageable pageable){
        return queryFactory
                .select(product)
                .from(product)
                .fetch();
    }
}