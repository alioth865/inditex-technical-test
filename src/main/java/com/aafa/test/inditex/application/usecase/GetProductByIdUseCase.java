package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;

@UseCase
public class GetProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductByIdUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public ProductMO execute(Long productId) {
        return productRepositoryPort.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
