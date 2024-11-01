package com.aafa.test.inditex.infrastructure.database.adapter;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;
import com.aafa.test.inditex.infrastructure.database.repositories.ProductJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.ProductEntityMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryH2Adapter implements ProductRepositoryPort {

    private final ProductJPARepository productJPARepository;

    private final ProductEntityMapper mapper;

    public ProductRepositoryH2Adapter(ProductJPARepository productJPARepository,
        ProductEntityMapper mapper) {
        this.productJPARepository = productJPARepository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductMO> findAll() {
        return mapper.toProductMOList(productJPARepository.findAll());
    }

    @Override
    public Optional<ProductMO> findById(Long id) {
        return productJPARepository.findById(id).map(mapper::toProductMO);
    }
}
