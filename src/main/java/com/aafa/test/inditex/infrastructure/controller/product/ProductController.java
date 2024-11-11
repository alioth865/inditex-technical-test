package com.aafa.test.inditex.infrastructure.controller.product;

import com.aafa.test.inditex.application.usecase.product.GetProductByIdUseCase;
import com.aafa.test.inditex.application.usecase.product.GetProductsUseCase;
import com.aafa.test.inditex.infrastructure.controller.ProductApi;
import com.aafa.test.inditex.infrastructure.dto.Product;
import com.aafa.test.inditex.infrastructure.mapper.product.ProductDtoMapper;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController implements ProductApi {

    private final GetProductsUseCase getProductsUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ProductDtoMapper productMapper;

    public ProductController(GetProductsUseCase getProductsUseCase,
        GetProductByIdUseCase getProductByIdUseCase,
        ProductDtoMapper productMapper) {
        this.getProductsUseCase = getProductsUseCase;
        this.getProductByIdUseCase = getProductByIdUseCase;
        this.productMapper = productMapper;
    }

    @Override
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productMapper.toDtoList(getProductsUseCase.execute()));
    }

    @Override
    public ResponseEntity<Product> getProductById(Long productId) {
        return ResponseEntity.ok(productMapper.toDto(getProductByIdUseCase.execute(productId)));
    }
}
