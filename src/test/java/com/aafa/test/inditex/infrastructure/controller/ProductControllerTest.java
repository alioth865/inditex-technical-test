package com.aafa.test.inditex.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.usecase.product.GetProductByIdUseCase;
import com.aafa.test.inditex.application.usecase.product.GetProductsUseCase;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.controller.product.ProductController;
import com.aafa.test.inditex.infrastructure.dto.Product;
import com.aafa.test.inditex.infrastructure.mapper.product.ProductDtoMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private GetProductsUseCase getProductsUseCase;

    @Mock
    private GetProductByIdUseCase getProductByIdUseCase;

    @Mock
    private ProductDtoMapper productMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductsReturnsListOfProductsWhenProductsExist() {
        List<Product> expectedProducts = Collections.singletonList(new Product());
        List<ProductMO> expectedProductsMO = Collections.singletonList(new ProductMO());
        when(getProductsUseCase.execute()).thenReturn(expectedProductsMO);
        when(productMapper.toDtoList(expectedProductsMO)).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(ResponseEntity.ok(expectedProducts), response);
    }

    @Test
    void getProductsReturnsEmptyListWhenNoProductsExist() {
        List<Product> expectedProducts = Collections.emptyList();
        List<ProductMO> expectedProductsMO = Collections.emptyList();
        when(getProductsUseCase.execute()).thenReturn(expectedProductsMO);
        when(productMapper.toDtoList(expectedProductsMO)).thenReturn(expectedProducts);

        ResponseEntity<List<Product>> response = productController.getProducts();

        assertEquals(ResponseEntity.ok(expectedProducts), response);
    }

    @Test
    void getProductByIdReturnsProductWhenProductExists() {
        Long productId = 1L;
        Product expectedProduct = new Product();
        ProductMO expectedProductMO = new ProductMO();
        when(getProductByIdUseCase.execute(productId)).thenReturn(expectedProductMO);
        when(productMapper.toDto(expectedProductMO)).thenReturn(expectedProduct);

        ResponseEntity<Product> response = productController.getProductById(productId);

        assertEquals(ResponseEntity.ok(expectedProduct), response);
    }
}