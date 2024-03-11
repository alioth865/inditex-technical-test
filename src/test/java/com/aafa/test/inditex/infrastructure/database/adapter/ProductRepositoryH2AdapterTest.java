package com.aafa.test.inditex.infrastructure.database.adapter;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.database.entities.ProductEntity;
import com.aafa.test.inditex.infrastructure.database.repositories.ProductJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.ProductEntityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductRepositoryH2AdapterTest {

    @InjectMocks
    private ProductRepositoryH2Adapter productRepositoryH2Adapter;

    @Mock
    private ProductJPARepository productJPARepository;

    @Mock
    private ProductEntityMapper productEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAllReturnsListOfProductsWhenProductsExist() {
        var productEntities = Collections.singletonList(new ProductEntity());
        var productMOs = Collections.singletonList(new ProductMO());
        when(productJPARepository.findAll()).thenReturn(productEntities);
        when(productEntityMapper.toProductMOList(productEntities)).thenReturn(productMOs);

        var actual = productRepositoryH2Adapter.findAll();

        assertEquals(productMOs, actual);
        verify(productJPARepository).findAll();
        verify(productEntityMapper).toProductMOList(productEntities);
    }

    @Test
    public void findByIdReturnsProductWhenProductExists() {
        Long productId = 1L;
        ProductMO expectedProduct = new ProductMO();
        when(productJPARepository.findById(productId)).thenReturn(java.util.Optional.of(new ProductEntity()));
        when(productEntityMapper.toProductMO(Mockito.any(ProductEntity.class))).thenReturn(expectedProduct);

        Optional<ProductMO> actualProduct = productRepositoryH2Adapter.findById(productId);

        assertEquals(expectedProduct, actualProduct.get());
    }



}