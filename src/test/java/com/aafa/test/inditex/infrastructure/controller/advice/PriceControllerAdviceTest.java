package com.aafa.test.inditex.infrastructure.controller.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class PriceControllerAdviceTest {

    @InjectMocks
    private PriceControllerAdvice priceControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void priceNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long productId = 1L;
        Long brandId = 2L;
        LocalDateTime date = LocalDateTime.now();
        String expectedMessage = String.format(
            "Not found price for product: %d and brand: %d and date: %s",
            productId, brandId, date);
        PriceNotFoundException exception = new PriceNotFoundException(productId, brandId, date);

        ResponseEntity<ErrorResponse> response = priceControllerAdvice.priceNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
    }
}