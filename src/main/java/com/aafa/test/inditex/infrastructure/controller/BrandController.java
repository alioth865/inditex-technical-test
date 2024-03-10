package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.usecase.GetBrandsUseCase;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import com.aafa.test.inditex.infrastructure.mapper.dto.BrandDtoMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BrandController implements BrandApi {

  private final GetBrandsUseCase getBrandsUseCase;
  private final BrandDtoMapper brandMapper;

    public BrandController(GetBrandsUseCase getBrandsUseCase, BrandDtoMapper brandMapper) {
        this.getBrandsUseCase = getBrandsUseCase;
        this.brandMapper = brandMapper;
    }

    @Override
  public ResponseEntity<List<Brand>> getBrands() {
    return ResponseEntity.ok(brandMapper.toDtoList(getBrandsUseCase.execute()));
  }
}
