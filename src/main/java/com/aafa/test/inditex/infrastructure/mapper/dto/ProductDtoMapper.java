package com.aafa.test.inditex.infrastructure.mapper.dto;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.dto.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    Product toDto(ProductMO productMO);

    List<Product> toDtoList(List<ProductMO> productMOList);
}
