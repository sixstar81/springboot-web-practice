package com.sk.practice.product.web;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.practice.product.Product;
import com.sk.practice.product.ProductRepository;
import com.sk.practice.product.ProductRequest;

import lombok.RequiredArgsConstructor;





@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Optional<Product> getMethodName(@PathVariable Long id) {
        return productRepository.findById(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody ProductRequest productRequest) {
        return productRepository.save(productRequest.toDomain());
    }
}
