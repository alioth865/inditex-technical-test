package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import com.aafa.test.inditex.domain.ports.PriceRepositoryPort;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class GetPriceUseCaseTest {

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private BrandRepositoryPort brandRepositoryPort;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void executeReturnsPriceWithLowerPriorityWhenPriceExists() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        var price = 100.0;
        var priceWithLowerPriority = PriceMO.builder().price(price).priority(0).build();
        var priceWithHigherPriority = PriceMO.builder().price(price * 2).priority(1).build();

        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(new ProductMO()));
        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.of(new BrandMO()));
        when(priceRepositoryPort.findByBrandIdAndProductIdAndDate(brandId, productId, date))
                .thenReturn(List.of(priceWithLowerPriority, priceWithHigherPriority));

        PriceMO actualPrice = getPriceUseCase.execute(productId, brandId, date);

        assertEquals(priceWithLowerPriority, actualPrice);
    }

    @Test
    public void executeThrowsProductNotFoundExceptionWhenProductDoesNotExist() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> getPriceUseCase.execute(productId, brandId, date));
    }

    @Test
    public void executeThrowsBrandNotFoundExceptionWhenBrandDoesNotExist() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(new ProductMO()));
        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.empty());

        assertThrows(BrandNotFoundException.class, () -> getPriceUseCase.execute(productId, brandId, date));
    }

    @Test
    public void executeThrowsPriceNotFoundExceptionWhenPriceDoesNotExist() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(new ProductMO()));
        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.of(new BrandMO()));
        when(priceRepositoryPort.findByBrandIdAndProductIdAndDate(brandId, productId, date))
                .thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> getPriceUseCase.execute(productId, brandId, date));
    }
}