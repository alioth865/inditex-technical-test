package com.aafa.test.inditex.application.usecase;

import com.aafa.test.inditex.application.usecase.GetBrandsUseCase;
import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.domain.ports.BrandRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetBrandsUseCaseTest {

    @InjectMocks
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private BrandRepositoryPort brandRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void executeReturnsListOfBrandsWhenBrandsExist() {
        List<BrandMO> expectedBrands = Collections.singletonList(new BrandMO());
        when(brandRepositoryPort.findAll()).thenReturn(expectedBrands);

        List<BrandMO> actualBrands = getBrandsUseCase.execute();

        assertEquals(expectedBrands, actualBrands);
    }

    @Test
    public void executeReturnsEmptyListWhenNoBrandsExist() {
        List<BrandMO> expectedBrands = Collections.emptyList();
        when(brandRepositoryPort.findAll()).thenReturn(expectedBrands);

        List<BrandMO> actualBrands = getBrandsUseCase.execute();

        assertEquals(expectedBrands, actualBrands);
    }
}