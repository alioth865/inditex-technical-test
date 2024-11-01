package com.aafa.test.inditex.domain.ports;

import com.aafa.test.inditex.domain.model.PriceMO;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {

    List<PriceMO> findByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime dateTime);
}
