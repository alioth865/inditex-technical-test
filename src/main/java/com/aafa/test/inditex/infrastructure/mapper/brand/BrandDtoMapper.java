package com.aafa.test.inditex.infrastructure.mapper.brand;

import com.aafa.test.inditex.domain.model.BrandMO;
import com.aafa.test.inditex.infrastructure.dto.Brand;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandDtoMapper {


    Brand toDto(BrandMO brandMO);

    List<Brand> toDtoList(List<BrandMO> brandMOList);
}
