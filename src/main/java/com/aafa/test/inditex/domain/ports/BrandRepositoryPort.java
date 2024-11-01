package com.aafa.test.inditex.domain.ports;

import com.aafa.test.inditex.domain.model.BrandMO;
import java.util.List;
import java.util.Optional;

public interface BrandRepositoryPort {

    List<BrandMO> findAll();

    Optional<BrandMO> findById(Long id);
}
