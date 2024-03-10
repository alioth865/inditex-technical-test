package com.aafa.test.inditex.infrastructure.mapper.entity;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.database.entities.ProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
  List<ProductMO> toProductMOList(List<ProductEntity> all);

  ProductMO toProductMO(ProductEntity productEntity);
}
