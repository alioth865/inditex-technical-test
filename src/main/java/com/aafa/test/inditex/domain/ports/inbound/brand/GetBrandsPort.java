package com.aafa.test.inditex.domain.ports.inbound.brand;

import com.aafa.test.inditex.domain.model.BrandMO;
import java.util.List;

public interface GetBrandsPort {

    List<BrandMO> execute();
}
