package com.aafa.test.inditex.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetBrandsUseCaseTest {

    @InjectMocks
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private BrandRepositoryPort brandRepositoryPort;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeReturnsListOfBrandsWhenBrandsExist() {
        List<BrandMO> expectedBrands = Collections.singletonList(new BrandMO());
        when(brandRepositoryPort.findAll()).thenReturn(expectedBrands);

        List<BrandMO> actualBrands = getBrandsUseCase.execute();

        assertEquals(expectedBrands, actualBrands);
    }

    @Test
    void executeReturnsEmptyListWhenNoBrandsExist() {
        List<BrandMO> expectedBrands = Collections.emptyList();
        when(brandRepositoryPort.findAll()).thenReturn(expectedBrands);

        List<BrandMO> actualBrands = getBrandsUseCase.execute();

        assertEquals(expectedBrands, actualBrands);
    }
}