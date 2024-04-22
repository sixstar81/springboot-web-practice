package com.sk.practice.product;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SellType {

    SELLING("판매중"), STOPPED("판매종료");

    private final String text;

    @JsonCreator
    SellType from(String text){
        return SellType.valueOf(text);
    }
}