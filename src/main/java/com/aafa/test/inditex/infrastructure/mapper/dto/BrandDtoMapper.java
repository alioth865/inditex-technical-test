package com.aafa.test.inditex.infrastructure.mapper.dto;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandDtoMapper {


    Brand toDto(BrandMO brandMO);

    List<Brand> toDtoList(List<BrandMO> brandMOList);
}
