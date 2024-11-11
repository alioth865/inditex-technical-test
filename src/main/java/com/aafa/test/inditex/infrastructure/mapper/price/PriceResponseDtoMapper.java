package com.aafa.test.inditex.infrastructure.mapper.price;

import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.infrastructure.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceResponseDtoMapper {

    @Mapping(target = "startDate", expression = "java(priceMO.getStartDate().toString())")
    @Mapping(target = "endDate", expression = "java(priceMO.getEndDate().toString())")
    PriceResponse toDto(PriceMO priceMO);
}
