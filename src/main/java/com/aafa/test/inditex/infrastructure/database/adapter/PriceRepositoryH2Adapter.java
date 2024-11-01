package com.aafa.test.inditex.infrastructure.database.adapter;

import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.ports.PriceRepositoryPort;
import com.aafa.test.inditex.infrastructure.database.repositories.PriceJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryH2Adapter implements PriceRepositoryPort {

    private final PriceJPARepository priceJPARepository;

    private final PriceEntityMapper priceEntityMapper;

    public PriceRepositoryH2Adapter(
        PriceJPARepository priceJPARepository, PriceEntityMapper priceEntityMapper) {
        this.priceJPARepository = priceJPARepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    public List<PriceMO> findByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime dateTime) {
        return priceEntityMapper.toPriceList(
            priceJPARepository.findPriceByBrandProductAndDateTime(brandId, productId, dateTime));
    }
}
