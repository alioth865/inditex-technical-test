package com.aafa.test.inditex.infrastructure.database.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brand")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
}
