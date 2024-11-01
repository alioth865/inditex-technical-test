package com.aafa.test.inditex.domain.ports;

import com.aafa.test.inditex.domain.model.ProductMO;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    List<ProductMO> findAll();

    Optional<ProductMO> findById(Long id);
}
