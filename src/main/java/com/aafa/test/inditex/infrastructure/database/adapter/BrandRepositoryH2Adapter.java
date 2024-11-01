package com.aafa.test.inditex.infrastructure.database.adapter;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import com.aafa.test.inditex.infrastructure.database.repositories.BrandJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.BrandEntityMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BrandRepositoryH2Adapter implements BrandRepositoryPort {

    private final BrandJPARepository brandJPARepository;

    private final BrandEntityMapper mapper;

    public BrandRepositoryH2Adapter(BrandJPARepository brandJPARepository,
        BrandEntityMapper mapper) {
        this.brandJPARepository = brandJPARepository;
        this.mapper = mapper;
    }

    @Override
    public List<BrandMO> findAll() {
        return mapper.toBrandMOList(brandJPARepository.findAll());
    }

    @Override
    public Optional<BrandMO> findById(Long id) {
        return brandJPARepository.findById(id).map(mapper::toBrandMO);
    }
}
