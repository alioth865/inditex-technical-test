package com.aafa.test.inditex.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PriceMO {

    private Long brandId;
    private Long productId;
    private Integer tariffId;
    private Double price;
    private Integer priority;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
