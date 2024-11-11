package com.aafa.test.inditex.application.usecase.brand;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.inbound.brand.GetBrandsPort;
import com.aafa.test.inditex.domain.ports.outbound.BrandRepositoryPort;
import java.util.List;

@UseCase
public class GetBrandsUseCase implements GetBrandsPort {

    private final BrandRepositoryPort brandRepositoryPort;

    public GetBrandsUseCase(BrandRepositoryPort brandRepositoryPort) {
        this.brandRepositoryPort = brandRepositoryPort;
    }

    @Override
    public List<BrandMO> execute() {
        return brandRepositoryPort.findAll();
    }
}
