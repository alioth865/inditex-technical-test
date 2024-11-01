package com.aafa.test.inditex.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PriceIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
        "/brand/1/product/35455/price/2020-06-14-10.00.00",
        "/brand/1/product/35455/price/2020-06-14-16.00.00",
        "/brand/1/product/35455/price/2020-06-14-21.00.00",
        "/brand/1/product/35455/price/2020-06-15-10.00.00",
        "/brand/1/product/35455/price/2020-06-16-21.00.00"
    })
    @SneakyThrows
    void testPrice(String url) {
        mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                result -> result.getResponse().getContentAsString().equalsIgnoreCase("35.50"));
    }

    @Test
    @SneakyThrows
    void testPriceNotFound() {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/brand/1/product/35455/price/2024-06-14-15.00.00"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(result -> result.getResponse().getContentAsString().contains(
                "Not found price for product: 35455 and brand: 1 and date: 2024-06-14T15:00"));
    }
}