package com.aafa.test.inditex.infrastructure.controller;

import com.aafa.test.inditex.application.usecase.GetProductsUseCase;
import com.aafa.test.inditex.infrastructure.dto.Product;
import com.aafa.test.inditex.infrastructure.mapper.dto.ProductDtoMapper;
import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {

  private final GetProductsUseCase getProductsUseCase;
  private final ProductDtoMapper productMapper;

    public ProductController(GetProductsUseCase getProductsUseCase, ProductDtoMapper productMapper) {
        this.getProductsUseCase = getProductsUseCase;
        this.productMapper = productMapper;
    }

    @Override
  public ResponseEntity<List<Product>> getProducts() {
    return ResponseEntity.ok(productMapper.toDtoList(getProductsUseCase.execute()));
  }
}
