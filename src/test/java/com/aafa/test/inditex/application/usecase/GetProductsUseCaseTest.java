package com.aafa.test.inditex.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.ProductRepositoryPort;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetProductsUseCaseTest {

    @InjectMocks
    private GetProductsUseCase getProductsUseCase;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeReturnsListOfProductsWhenProductsExist() {
        List<ProductMO> expectedProducts = Collections.singletonList(new ProductMO());
        when(productRepositoryPort.findAll()).thenReturn(expectedProducts);

        List<ProductMO> actualProducts = getProductsUseCase.execute();

        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void executeReturnsEmptyListWhenNoProductsExist() {
        List<ProductMO> expectedProducts = Collections.emptyList();
        when(productRepositoryPort.findAll()).thenReturn(expectedProducts);

        List<ProductMO> actualProducts = getProductsUseCase.execute();

        assertEquals(expectedProducts, actualProducts);
    }
}