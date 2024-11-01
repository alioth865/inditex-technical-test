package com.aafa.test.inditex.infrastructure.controller.advice;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {

    String message;
}
