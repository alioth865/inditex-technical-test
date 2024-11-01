package com.aafa.test.inditex.infrastructure.database.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.database.entities.BrandEntity;
import com.aafa.test.inditex.infrastructure.database.repositories.BrandJPARepository;
import com.aafa.test.inditex.infrastructure.mapper.entity.BrandEntityMapper;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BrandRepositoryH2AdapterTest {

    @InjectMocks
    private BrandRepositoryH2Adapter brandRepositoryH2Adapter;

    @Mock
    private BrandJPARepository brandJPARepository;

    @Mock
    private BrandEntityMapper brandEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllReturnsListOfBrandsWhenBrandsExist() {
        var brandEntities = Collections.singletonList(new BrandEntity());
        var brandMOs = Collections.singletonList(new BrandMO());
        when(brandJPARepository.findAll()).thenReturn(brandEntities);
        when(brandEntityMapper.toBrandMOList(brandEntities)).thenReturn(brandMOs);

        var actual = brandRepositoryH2Adapter.findAll();

        assertEquals(brandMOs, actual);
        verify(brandJPARepository).findAll();
        verify(brandEntityMapper).toBrandMOList(brandEntities);
    }

    @Test
    void findByIdReturnsBrandWhenBrandExists() {
        Long brandId = 1L;
        BrandMO expectedBrand = new BrandMO();
        when(brandJPARepository.findById(brandId)).thenReturn(Optional.of(new BrandEntity()));
        when(brandEntityMapper.toBrandMO(any(BrandEntity.class))).thenReturn(expectedBrand);

        Optional<BrandMO> actualBrand = brandRepositoryH2Adapter.findById(brandId);

        assertEquals(Optional.of(expectedBrand), actualBrand);
    }
}