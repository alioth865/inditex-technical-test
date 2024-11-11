package com.aafa.test.inditex.application.usecase.product;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.inbound.product.GetProductByIdPort;
import com.aafa.test.inditex.domain.ports.outbound.ProductRepositoryPort;

@UseCase
public class GetProductByIdUseCase implements GetProductByIdPort {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductByIdUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public ProductMO execute(Long productId) {
        return productRepositoryPort.findById(productId)
            .orElseThrow(() -> new ProductNotFoundException(productId));
    }

}
