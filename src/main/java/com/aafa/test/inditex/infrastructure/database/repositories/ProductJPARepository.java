package com.aafa.test.inditex.infrastructure.database.repositories;

import com.aafa.test.inditex.infrastructure.database.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<ProductEntity, Long> {

}
