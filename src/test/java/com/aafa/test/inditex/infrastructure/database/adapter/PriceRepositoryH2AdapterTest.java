package com.aafa.test.inditex.infrastructure.database.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.infrastructure.database.repositories.PriceJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.PriceEntityMapper;
import java.time.LocalDateTime;
import java.util.Collections;
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
        var priceEntities = Collections.singletonList(
            new com.aafa.test.inditex.infrastructure.database.entities.PriceEntity());
        var priceMOs = Collections.singletonList(new com.aafa.test.inditex.domain.model.PriceMO());
        when(priceJPARepository.findPriceByBrandProductAndDateTime(brandId, productId,
            dateTime)).thenReturn(priceEntities);
        when(priceEntityMapper.toPriceList(priceEntities)).thenReturn(priceMOs);

        var actual = priceRepositoryH2Adapter.findByBrandIdAndProductIdAndDate(brandId, productId,
            dateTime);

        assertEquals(priceMOs, actual);
        verify(priceJPARepository).findPriceByBrandProductAndDateTime(brandId, productId, dateTime);
        verify(priceEntityMapper).toPriceList(priceEntities);
    }


}