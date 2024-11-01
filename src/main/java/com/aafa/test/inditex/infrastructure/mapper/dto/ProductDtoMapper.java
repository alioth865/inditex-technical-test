package com.aafa.test.inditex.infrastructure.mapper.dto;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.dto.Product;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductDtoMapper {

    Product toDto(ProductMO productMO);

    List<Product> toDtoList(List<ProductMO> productMOList);
}
