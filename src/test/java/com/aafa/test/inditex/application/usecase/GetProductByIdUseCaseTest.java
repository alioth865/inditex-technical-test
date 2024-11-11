package com.aafa.test.inditex.application.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.application.exception.ProductNotFoundException;
import com.aafa.test.inditex.application.usecase.product.GetProductByIdUseCase;
import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.domain.ports.outbound.ProductRepositoryPort;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetProductByIdUseCaseTest {

    @InjectMocks
    GetProductByIdUseCase getProductByIdUseCase;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeThrowsBrandNotFoundExceptionWhenBrandDoesNotExist() {
        Long productId = 1L;
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.empty());

        assertThrows(
            ProductNotFoundException.class, () -> getProductByIdUseCase.execute(productId));
    }

    @Test
    void executeReturnsBrandWhenBrandExists() {
        Long productId = 1L;
        when(productRepositoryPort.findById(productId)).thenReturn(Optional.of(new ProductMO()));

        ProductMO actualProduct = getProductByIdUseCase.execute(productId);

        assertNotNull(actualProduct);
    }

}