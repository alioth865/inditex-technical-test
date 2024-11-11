package com.aafa.test.inditex.application.usecase.product;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.inbound.product.GetProductsPort;
import com.aafa.test.inditex.domain.ports.outbound.ProductRepositoryPort;
import java.util.List;

@UseCase
public class GetProductsUseCase implements GetProductsPort {

    private final ProductRepositoryPort productRepositoryPort;

    public GetProductsUseCase(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public List<ProductMO> execute() {
        return productRepositoryPort.findAll();
    }
}
