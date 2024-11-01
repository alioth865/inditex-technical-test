package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;
import java.util.List;

@UseCase
public class GetProductsUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    public List<ProductMO> execute() {
        return productRepositoryPort.findAll();
    }
}
