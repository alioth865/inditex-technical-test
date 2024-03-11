package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.infrastructure.controller.PriceControllerAdvice.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceControllerAdviceTest {

    @InjectMocks
    private PriceControllerAdvice priceControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void brandNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long brandId = 1L;
        String expectedMessage = String.format("Brand with id: %d not found", brandId);
        BrandNotFoundException exception = new BrandNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = priceControllerAdvice.brandNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody().getMessage());
    }

    @Test
    public void productNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long productId = 1L;
        String expectedMessage = String.format("Product with id: %d not found", productId);
        ProductNotFoundException exception = new ProductNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = priceControllerAdvice.productNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody().getMessage());
    }

    @Test
    public void priceNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long productId = 1L;
        Long brandId = 2L;
        LocalDateTime date = LocalDateTime.now();
        String expectedMessage = String.format(
                "Not found price for product: %d and brand: %d and date: %s",
                productId, brandId, date);
        PriceNotFoundException exception = new PriceNotFoundException(productId, brandId, date);

        ResponseEntity<ErrorResponse> response = priceControllerAdvice.priceNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, response.getBody().getMessage());
    }
}