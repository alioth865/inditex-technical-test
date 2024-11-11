package com.aafa.test.inditex.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.exception.BrandNotFoundException;
import com.aafa.test.inditex.application.usecase.brand.GetBrandByIdUseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.outbound.BrandRepositoryPort;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetBrandByIdUseCaseTest {

    @Mock
    private BrandRepositoryPort brandRepositoryPort;

    @InjectMocks
    private GetBrandByIdUseCase getBrandByIdUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeThrowsProductNotFoundExceptionWhenProductDoesNotExist() {
        Long brandId = 1L;

        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.empty());

        assertThrows(
            BrandNotFoundException.class, () -> getBrandByIdUseCase.execute(brandId));
    }

    @Test
    void executeReturnsProductWhenProductExists() {
        Long brandId = 1L;

        when(brandRepositoryPort.findById(brandId)).thenReturn(Optional.of(new BrandMO()));

        BrandMO actualBrand = getBrandByIdUseCase.execute(brandId);

        assertNotNull(actualBrand);
    }


}