package com.aafa.test.inditex.infrastructure.controller.price;

import com.aafa.test.inditex.application.usecase.price.GetPriceUseCase;
import com.aafa.test.inditex.infrastructure.controller.PriceApi;
import com.aafa.test.inditex.infrastructure.dto.PriceResponse;
import com.aafa.test.inditex.infrastructure.mapper.price.PriceResponseDtoMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController implements PriceApi {

    public static final DateTimeFormatter DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
    private final GetPriceUseCase getPriceUseCase;
    private final PriceResponseDtoMapper priceResponseDtoMapper;

    public PriceController(
        GetPriceUseCase getPriceUseCase, PriceResponseDtoMapper priceResponseDtoMapper) {
        this.getPriceUseCase = getPriceUseCase;
        this.priceResponseDtoMapper = priceResponseDtoMapper;
    }

    @Override
    public ResponseEntity<PriceResponse> getPriceByBrandAndProductAndDate(
        Long brandId, Long productId, String dateTime) {
        return ResponseEntity.ok(
            priceResponseDtoMapper.toDto(
                getPriceUseCase.execute(
                    productId, brandId, LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER))));
    }
}
