package com.sk.practice.product.web;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sk.practice.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.practice.product.ProductRequest;
import com.sk.practice.product.SellType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean ProductRepository productRepository;

    @Test
    void savedProduct() throws Exception {
        ProductRequest productRequest = new ProductRequest("kim", 1000L, LocalDateTime.now(), SellType.SELLING);
        mockMvc.perform(
                post("/products")
                        .content(objectMapper.writeValueAsString(productRequest))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }
}
