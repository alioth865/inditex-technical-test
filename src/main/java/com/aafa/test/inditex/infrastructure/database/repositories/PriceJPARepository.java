package com.aafa.test.inditex.infrastructure.database.repositories;

import com.aafa.test.inditex.infrastructure.database.entities.PriceEntity;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PriceJPARepository extends JpaRepository<PriceEntity, Long> {

    @Query(
        "select p from PriceEntity p where p.brand.id = :brandId and p.product.id = :productId and :dateTime between p.startDate and p.endDate order by p.priority ASC limit 1")
    Optional<PriceEntity> findPriceByBrandProductAndDateTime(
        Long brandId, Long productId, LocalDateTime dateTime);
}
