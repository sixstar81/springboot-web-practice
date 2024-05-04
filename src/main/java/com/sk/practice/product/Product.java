package com.sk.practice.product;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator( 
      name = "product_seq_generator"
    , sequenceName = "product_seq"
    , initialValue = 1
    , allocationSize = 50
)
public class Product {
    
    @Id @GeneratedValue(generator = "product_seq_generator", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private Double price;
    private LocalDateTime registerTime;
    @Enumerated(EnumType.STRING)
    private SellType sellType;

    @Builder
    private Product(String name, Double price, LocalDateTime registerTime,  SellType sellType){
        this.name = name;
        this.price = price;
        this.registerTime = registerTime;
        this.sellType = sellType;
    }
}