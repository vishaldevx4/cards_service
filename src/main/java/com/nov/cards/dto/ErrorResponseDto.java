package com.nov.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema( name = "ErrorResponse" , description = "Schema to hold error response information")
public class ErrorResponseDto {

    @Schema(description = "API path where the error occurred", example = "/api/cards/createCard")
    private String apiPath;
    @Schema(description = "Detailed error message", example = "Invalid mobile number format")
    private String errorMessage;
    @Schema(description = "HTTP status code representing the error", example = "400")
    private HttpStatus errorCode;
    @Schema(description = "Timestamp when the error occurred", example = "2024-06-15T14:30:00")
    private LocalDateTime errorTime;
}
