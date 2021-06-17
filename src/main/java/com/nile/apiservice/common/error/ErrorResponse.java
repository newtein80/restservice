package com.nile.apiservice.common.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(name = "API 응답 모델 (ERROR)", description = "API 응답 에러 모델")
public class ErrorResponse {
    @Schema(name="timestamp", description = "에러 응답 시간", required = true, nullable = false)
    private final LocalDateTime timestamp;
    @Schema(name="status", description = "에러 응답 상태코드", required = true, nullable = false)
    private final int status;
    @Schema(name="error", description = "에러 응답", required = true, nullable = false)
    private final String error;
    @Schema(name="message", description = "에러 응답 메시지", required = true, nullable = false)
    private final String message;

    public ErrorResponse(HttpStatus httpStatus, String errCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = errCode;
        this.message = message;
    }
}
