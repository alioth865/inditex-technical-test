package com.aafa.test.inditex.infrastructure.database.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.infrastructure.database.repositories.PriceJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.price.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PriceRepositoryH2AdapterTest {

    @InjectMocks
    private PriceRepositoryH2Adapter priceRepositoryH2Adapter;

    @Mock
    private PriceJPARepository priceJPARepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByBrandIdAndProductIdAndDateReturnsListOfPricesWhenPricesExist() {
        var brandId = 1L;
        var productId = 1L;
        var dateTime = LocalDateTime.now();
        var priceEntity = new com.aafa.test.inditex.infrastructure.database.entities.PriceEntity();
        var priceMO = new com.aafa.test.inditex.domain.model.PriceMO();
        when(priceJPARepository.findPriceByBrandProductAndDateTime(brandId, productId,
            dateTime)).thenReturn(Optional.of(priceEntity));
        when(priceEntityMapper.toPrice(priceEntity)).thenReturn(priceMO);

        var actual = priceRepositoryH2Adapter.findByBrandIdAndProductIdAndDate(brandId, productId,
            dateTime);

        assertEquals(Optional.of(priceMO), actual);
        verify(priceJPARepository).findPriceByBrandProductAndDateTime(brandId, productId, dateTime);
        verify(priceEntityMapper).toPrice(priceEntity);
    }


}