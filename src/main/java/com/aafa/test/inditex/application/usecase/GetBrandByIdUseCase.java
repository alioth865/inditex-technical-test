package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;

@UseCase
public class GetBrandByIdUseCase {

    private final BrandRepositoryPort brandRepositoryPort;

    public GetBrandByIdUseCase(BrandRepositoryPort brandRepositoryPort) {
        this.brandRepositoryPort = brandRepositoryPort;
    }


    public BrandMO execute(Long brandId) {
        return brandRepositoryPort.findById(brandId)
            .orElseThrow(() -> new BrandNotFoundException(brandId));
    }
}
