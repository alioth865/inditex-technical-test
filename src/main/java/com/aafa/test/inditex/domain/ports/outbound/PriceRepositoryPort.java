package com.aafa.test.inditex.domain.ports.outbound;

import com.aafa.test.inditex.domain.model.PriceMO;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<PriceMO> findByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime dateTime);
}
