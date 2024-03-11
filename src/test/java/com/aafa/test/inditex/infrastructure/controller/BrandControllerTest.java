package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.usecase.GetBrandsUseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import com.aafa.test.inditex.infrastructure.mapper.dto.BrandDtoMapper;
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

public class BrandControllerTest {

    @InjectMocks
    private BrandController brandController;

    @Mock
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private BrandDtoMapper brandMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getBrandsReturnsListOfBrandsWhenBrandsExist() {
        List<BrandMO> expectedBrandsMO = Collections.singletonList(new BrandMO());
        List<Brand> expectedBrands = Collections.singletonList(new Brand());
        when(getBrandsUseCase.execute()).thenReturn(expectedBrandsMO);
        when(brandMapper.toDtoList(expectedBrandsMO)).thenReturn(expectedBrands);

        ResponseEntity<List<Brand>> response = brandController.getBrands();

        assertEquals(ResponseEntity.ok(expectedBrands), response);
    }

    @Test
    public void getBrandsReturnsEmptyListWhenNoBrandsExist() {
        List<BrandMO> expectedBrandsMO = Collections.emptyList();
        List<Brand> expectedBrands = Collections.emptyList();
        when(getBrandsUseCase.execute()).thenReturn(expectedBrandsMO);
        when(brandMapper.toDtoList(expectedBrandsMO)).thenReturn(expectedBrands);

        ResponseEntity<List<Brand>> response = brandController.getBrands();

        assertEquals(ResponseEntity.ok(expectedBrands), response);
    }
}