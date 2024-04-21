package com.sk.practice.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    private SellType sellType;

    @Builder
    private Product(String name, Double price, SellType sellType){
        this.name = name;
        this.price = price;
        this.sellType = sellType;
    }
}
