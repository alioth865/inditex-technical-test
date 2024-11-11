package com.aafa.test.inditex.infrastructure.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.usecase.price.GetPriceUseCase;
import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.infrastructure.controller.price.PriceController;
import com.aafa.test.inditex.infrastructure.dto.PriceResponse;
import com.aafa.test.inditex.infrastructure.mapper.price.PriceResponseDtoMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private GetPriceUseCase getPriceUseCase;

    @Mock
    private PriceResponseDtoMapper priceResponseDtoMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPriceByBrandAndProductAndDateReturnsPriceWhenPriceExists() {
        Long brandId = 1L;
        Long productId = 1L;
        String dateTime = "2022-12-31-23.59.59";
        PriceMO expectedPriceMO = new PriceMO();
        PriceResponse expectedPriceResponse = new PriceResponse();
        when(getPriceUseCase.execute(productId, brandId,
            LocalDateTime.parse(dateTime, PriceController.DATE_TIME_FORMATTER)))
            .thenReturn(expectedPriceMO);
        when(priceResponseDtoMapper.toDto(expectedPriceMO)).thenReturn(expectedPriceResponse);

        ResponseEntity<PriceResponse> response = priceController.getPriceByBrandAndProductAndDate(
            brandId, productId, dateTime);

        assertEquals(ResponseEntity.ok(expectedPriceResponse), response);
    }

    @Test
    void getPriceByBrandAndProductAndDateReturnsNullWhenNoPriceExists() {
        Long brandId = 1L;
        Long productId = 1L;
        String dateTime = "2022-12-31-23.59.59";
        when(getPriceUseCase.execute(productId, brandId,
            LocalDateTime.parse(dateTime, PriceController.DATE_TIME_FORMATTER)))
            .thenReturn(null);

        ResponseEntity<PriceResponse> response = priceController.getPriceByBrandAndProductAndDate(
            brandId, productId, dateTime);

        assertEquals(ResponseEntity.ok(null), response);
    }
}