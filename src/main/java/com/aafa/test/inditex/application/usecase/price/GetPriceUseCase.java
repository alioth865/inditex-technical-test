package com.aafa.test.inditex.application.usecase.price;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.ports.inbound.price.GetPricePort;
import com.aafa.test.inditex.domain.ports.outbound.PriceRepositoryPort;
import java.time.LocalDateTime;

@UseCase
public class GetPriceUseCase implements GetPricePort {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(
        PriceRepositoryPort priceRepositoryPort) {

        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public PriceMO execute(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort
            .findByBrandIdAndProductIdAndDate(brandId, productId, date)
            .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
