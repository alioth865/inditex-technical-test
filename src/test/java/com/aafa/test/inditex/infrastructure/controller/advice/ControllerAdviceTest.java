package com.aafa.test.inditex.infrastructure.controller.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ControllerAdviceTest {

    @InjectMocks
    private ControllerAdvice controllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleInternalErrorReturnsErrorResponseWithCorrectMessage() {
        Exception exception = new Exception("Test exception");

        ResponseEntity<ErrorResponse> response = controllerAdvice.handleInternalError(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.",
            Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void brandNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long brandId = 1L;
        String expectedMessage = String.format("Brand with id: %d not found", brandId);
        BrandNotFoundException exception = new BrandNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = controllerAdvice.brandNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
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

        ResponseEntity<ErrorResponse> response = controllerAdvice.priceNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
    }

    @Test
    void productNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long productId = 1L;
        String expectedMessage = String.format("Product with id: %d not found", productId);
        ProductNotFoundException exception = new ProductNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = controllerAdvice.productNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
    }

}