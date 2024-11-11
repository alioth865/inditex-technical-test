package com.aafa.test.inditex.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.usecase.brand.GetBrandByIdUseCase;
import com.aafa.test.inditex.application.usecase.brand.GetBrandsUseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.controller.brand.BrandController;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import com.aafa.test.inditex.infrastructure.mapper.brand.BrandDtoMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class BrandControllerTest {

    @InjectMocks
    private BrandController brandController;

    @Mock
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private GetBrandByIdUseCase getBrandByIdUseCase;

    @Mock
    private BrandDtoMapper brandMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBrandsReturnsListOfBrandsWhenBrandsExist() {
        List<BrandMO> expectedBrandsMO = Collections.singletonList(new BrandMO());
        List<Brand> expectedBrands = Collections.singletonList(new Brand());
        when(getBrandsUseCase.execute()).thenReturn(expectedBrandsMO);
        when(brandMapper.toDtoList(expectedBrandsMO)).thenReturn(expectedBrands);

        ResponseEntity<List<Brand>> response = brandController.getBrands();

        assertEquals(ResponseEntity.ok(expectedBrands), response);
    }

    @Test
    void getBrandsReturnsEmptyListWhenNoBrandsExist() {
        List<BrandMO> expectedBrandsMO = Collections.emptyList();
        List<Brand> expectedBrands = Collections.emptyList();
        when(getBrandsUseCase.execute()).thenReturn(expectedBrandsMO);
        when(brandMapper.toDtoList(expectedBrandsMO)).thenReturn(expectedBrands);

        ResponseEntity<List<Brand>> response = brandController.getBrands();

        assertEquals(ResponseEntity.ok(expectedBrands), response);
    }

    @Test
    void getBrandByIdReturnsBrandWhenBrandExists() {
        Long brandId = 1L;
        BrandMO expectedBrandMO = new BrandMO();
        Brand expectedBrand = new Brand();
        when(getBrandByIdUseCase.execute(brandId)).thenReturn(expectedBrandMO);
        when(brandMapper.toDto(expectedBrandMO)).thenReturn(expectedBrand);

        ResponseEntity<Brand> response = brandController.getBrandById(brandId);

        assertEquals(ResponseEntity.ok(expectedBrand), response);
    }

}