package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.annotation.UseCase;
import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import com.aafa.test.inditex.domain.ports.PriceRepositoryPort;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;

import java.time.LocalDateTime;
import java.util.Comparator;

@UseCase
public class GetPriceUseCase {
    private final ProductRepositoryPort productRepositoryPort;
    private final BrandRepositoryPort brandRepositoryPort;
    private final PriceRepositoryPort priceRepositoryPort;

    public GetPriceUseCase(
            ProductRepositoryPort productRepositoryPort,
            BrandRepositoryPort brandRepositoryPort,
            PriceRepositoryPort priceRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.brandRepositoryPort = brandRepositoryPort;
        this.priceRepositoryPort = priceRepositoryPort;
    }

    public PriceMO execute(Long productId, Long brandId, LocalDateTime date) {
        // Comprobamos que el producto y la marca existen
        productRepositoryPort
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        brandRepositoryPort.findById(brandId).orElseThrow(() -> new BrandNotFoundException(brandId));

        return priceRepositoryPort
                .findByBrandIdAndProductIdAndDate(brandId, productId, date)
                .stream()
                // Asumimos que la prioridad es unica
                .min(Comparator.comparing(PriceMO::getPriority))
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
}
