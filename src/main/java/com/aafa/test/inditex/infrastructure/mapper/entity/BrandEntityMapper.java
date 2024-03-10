package com.aafa.test.inditex.infrastructure.mapper.entity;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.database.entities.BrandEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandEntityMapper {
    List<BrandMO> toBrandMOList(List<BrandEntity> all);

    BrandMO toBrandMO(BrandEntity brandEntity);
}
