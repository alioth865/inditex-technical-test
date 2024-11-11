package com.aafa.test.inditex.domain.ports.inbound.price;

import com.aafa.test.inditex.domain.model.PriceMO;
import java.time.LocalDateTime;

public interface GetPricePort {

    PriceMO execute(Long productId, Long brandId, LocalDateTime date);
}
