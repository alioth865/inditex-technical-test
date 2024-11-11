package com.aafa.test.inditex.infrastructure.controller.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorResponse {

    private String message;
}
