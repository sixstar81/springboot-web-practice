package com.sk.practice.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sk.practice.product.Product;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sk.practice.product.QProduct.product;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private List<Product> productList(Pageable pageable){
        throw new UnsupportedOperationException();
    }
}
