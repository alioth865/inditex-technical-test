package com.aafa.test.inditex.infrastructure.database.repositories;

import com.aafa.test.inditex.infrastructure.database.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandJPARepository extends JpaRepository<BrandEntity, Long> {

}
