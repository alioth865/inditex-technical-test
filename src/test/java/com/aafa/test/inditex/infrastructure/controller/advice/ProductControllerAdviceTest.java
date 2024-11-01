package com.aafa.test.inditex.infrastructure.controller.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ProductControllerAdviceTest {

    @InjectMocks
    private ProductControllerAdvice productControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void productNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long productId = 1L;
        String expectedMessage = String.format("Product with id: %d not found", productId);
        ProductNotFoundException exception = new ProductNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = productControllerAdvice.productNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
    }

}