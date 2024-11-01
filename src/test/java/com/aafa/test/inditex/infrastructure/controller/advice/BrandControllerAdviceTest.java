package com.aafa.test.inditex.infrastructure.controller.advice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class BrandControllerAdviceTest {

    @InjectMocks
    private BrandControllerAdvice brandControllerAdvice;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void brandNotFoundExceptionReturnsErrorResponseWithCorrectMessage() {
        Long brandId = 1L;
        String expectedMessage = String.format("Brand with id: %d not found", brandId);
        BrandNotFoundException exception = new BrandNotFoundException(1L);

        ResponseEntity<ErrorResponse> response = brandControllerAdvice.brandNotFoundException(
            exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(expectedMessage, Objects.requireNonNull(response.getBody()).getMessage());
    }

}