package com.nov.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceAlredyExistsException extends RuntimeException {

    public ResourceAlredyExistsException(String message) {
        super(message);
    }

}
