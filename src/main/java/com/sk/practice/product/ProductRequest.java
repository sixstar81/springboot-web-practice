package com.sk.practice.product;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ProductRequest(
    String name, 
    Long price, 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime registerTime, 
    SellType sellType) {
    
        public Product toDomain(){
            return Product.builder()
                .name(this.name)
                .price(Double.valueOf(this.price))
                .registerTime(registerTime)
                .sellType(this.sellType)
                .build();
        }
}