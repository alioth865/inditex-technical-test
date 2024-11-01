package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import java.util.List;

@UseCase
public class GetBrandsUseCase {

    private final BrandRepositoryPort brandRepositoryPort;

    public GetBrandsUseCase(BrandRepositoryPort brandRepositoryPort) {
        this.brandRepositoryPort = brandRepositoryPort;
    }

    public List<BrandMO> execute() {
        return brandRepositoryPort.findAll();
    }
}
