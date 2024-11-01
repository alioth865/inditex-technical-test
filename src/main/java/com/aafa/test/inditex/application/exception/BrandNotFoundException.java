package com.aafa.test.inditex.application.exception;

public class BrandNotFoundException extends RuntimeException {

    public BrandNotFoundException(Long brandId) {
        super(String.format("Brand with id: %d not found", brandId));
    }
}
