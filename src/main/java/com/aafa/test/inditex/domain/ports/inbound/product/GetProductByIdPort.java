package com.aafa.test.inditex.domain.ports.inbound.product;

import com.aafa.test.inditex.domain.model.ProductMO;

public interface GetProductByIdPort {

    ProductMO execute(Long productId);
}
