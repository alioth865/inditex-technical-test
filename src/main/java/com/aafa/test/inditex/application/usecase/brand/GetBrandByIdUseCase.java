package com.aafa.test.inditex.application.usecase.brand;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.inbound.brand.GetBrandByIdPort;
import com.aafa.test.inditex.domain.ports.outbound.BrandRepositoryPort;

@UseCase
public class GetBrandByIdUseCase implements GetBrandByIdPort {

    private final BrandRepositoryPort brandRepositoryPort;

    public GetBrandByIdUseCase(BrandRepositoryPort brandRepositoryPort) {
        this.brandRepositoryPort = brandRepositoryPort;
    }


    @Override
    public BrandMO execute(Long brandId) {
        return brandRepositoryPort.findById(brandId)
            .orElseThrow(() -> new BrandNotFoundException(brandId));
    }
}
