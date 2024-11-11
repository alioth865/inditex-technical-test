package com.aafa.test.inditex.domain.ports.inbound.brand;

import com.aafa.test.inditex.domain.model.BrandMO;

public interface GetBrandByIdPort {

    BrandMO execute(Long brandId);
}
