package com.aafa.test.inditex.infrastructure.mapper.price;

import com.aafa.test.inditex.domain.model.PriceMO;
import com.aafa.test.inditex.infrastructure.database.entities.PriceEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    List<PriceMO> toPriceList(List<PriceEntity> priceEntityList);

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "productId", source = "product.id")
    PriceMO toPrice(PriceEntity priceEntity);
}
