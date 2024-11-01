package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.ports.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Comparator;

@UseCase
public class GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(
        PriceRepositoryPort priceRepositoryPort) {

        this.priceRepositoryPort = priceRepositoryPort;
    }

    public PriceMO execute(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort
            .findByBrandIdAndProductIdAndDate(brandId, productId, date)
            .stream()
            // Asumimos que la prioridad es unica
            .min(Comparator.comparing(PriceMO::getPriority))
            .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
