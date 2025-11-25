package com.nov.cards.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String domain, String key, String message) {
        super("%s not found for %s : '%s'".formatted(domain, key, message));
    }
}
