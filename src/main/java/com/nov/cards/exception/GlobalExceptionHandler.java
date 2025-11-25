package com.nov.cards.exception;

import com.nov.cards.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
                                                                            WebRequest request) {

        ErrorResponseDto errorDetails = new ErrorResponseDto(
                request.getDescription(false),
                ex.getMessage(),
                org.springframework.http.HttpStatus.NOT_FOUND,
                LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, org.springframework.http.HttpStatus.NOT_FOUND);
    }


}
