package com.aafa.test.inditex.infrastructure.mapper.entity;

import com.aafa.test.inditex.domain.model.ProductMO;
import com.aafa.test.inditex.infrastructure.database.entities.ProductEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

    List<ProductMO> toProductMOList(List<ProductEntity> all);

    ProductMO toProductMO(ProductEntity productEntity);
}
