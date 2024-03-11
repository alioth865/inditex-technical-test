package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.usecase.GetProductsUseCase;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.dto.Product;
import com.aafa.test.inditex.infrastructure.mapper.dto.ProductDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private GetProductsUseCase getProductsUseCase;

    @Mock
    private ProductDtoMapper productMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getProductsReturnsListOfProductsWhenProductsExist() {
        List<Product> expectedProducts = Collections.singletonList(new Product());
        List<ProductMO> expectedProductsMO = Collections.singletonList(new ProductMO());
        when(getProductsUseCase.execute()).thenReturn(expectedProductsMO);
        when(productMapper.toDtoList(expectedProductsMO)).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(ResponseEntity.ok(expectedProducts), response);
    }

    @Test
    public void getProductsReturnsEmptyListWhenNoProductsExist() {
        List<Product> expectedProducts = Collections.emptyList();
        List<ProductMO> expectedProductsMO = Collections.emptyList();
        when(getProductsUseCase.execute()).thenReturn(expectedProductsMO);
        when(productMapper.toDtoList(expectedProductsMO)).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(ResponseEntity.ok(expectedProducts), response);
    }
}