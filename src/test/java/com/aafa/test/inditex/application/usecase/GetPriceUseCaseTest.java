package com.aafa.test.inditex.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.exception.PriceNotFoundException;
import com.aafa.test.inditex.application.usecase.price.GetPriceUseCase;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.domain.ports.outbound.PriceRepositoryPort;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetPriceUseCaseTest {

    @InjectMocks
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeReturnsPriceWithLowerPriorityWhenPriceExists() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        var price = 100.0;
        var priceWithLowerPriority = PriceMO.builder().price(price).priority(0).build();

        when(priceRepositoryPort.findByBrandIdAndProductIdAndDate(brandId, productId, date))
            .thenReturn(Optional.of(priceWithLowerPriority));

        PriceMO actualPrice = getPriceUseCase.execute(productId, brandId, date);

        assertEquals(priceWithLowerPriority, actualPrice);
    }


    @Test
    void executeThrowsPriceNotFoundExceptionWhenPriceDoesNotExist() {
        Long productId = 1L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.now();
        when(priceRepositoryPort.findByBrandIdAndProductIdAndDate(brandId, productId, date))
            .thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class,
            () -> getPriceUseCase.execute(productId, brandId, date));
    }
}