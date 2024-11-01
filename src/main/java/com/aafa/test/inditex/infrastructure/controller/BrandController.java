package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.usecase.GetBrandByIdUseCase;
import com.aafa.test.inditex.application.usecase.GetBrandsUseCase;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import com.aafa.test.inditex.infrastructure.mapper.dto.BrandDtoMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController implements BrandApi {

    private final GetBrandsUseCase getBrandsUseCase;
    private final GetBrandByIdUseCase getBrandByIdUseCase;
    private final BrandDtoMapper brandMapper;

    public BrandController(GetBrandsUseCase getBrandsUseCase,
        GetBrandByIdUseCase getBrandByIdUseCase, BrandDtoMapper brandMapper) {
        this.getBrandsUseCase = getBrandsUseCase;
        this.getBrandByIdUseCase = getBrandByIdUseCase;
        this.brandMapper = brandMapper;
    }

    @Override
    public ResponseEntity<List<Brand>> getBrands() {
        return ResponseEntity.ok(brandMapper.toDtoList(getBrandsUseCase.execute()));
    }

    @Override
    public ResponseEntity<Brand> getBrandById(Long brandId) {
        return ResponseEntity.ok(brandMapper.toDto(getBrandByIdUseCase.execute(brandId)));
    }
}
