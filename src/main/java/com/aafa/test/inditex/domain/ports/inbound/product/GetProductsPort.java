package com.aafa.test.inditex.domain.ports.inbound.product;

import com.aafa.test.inditex.domain.model.ProductMO;
import java.util.List;

public interface GetProductsPort {

    List<ProductMO> execute();
}
