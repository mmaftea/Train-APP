package com.app.train.model.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public record ApiError(HttpStatus status, String message, int errorCode) {
    @JsonCreator
    public ApiError(@JsonProperty("status") HttpStatus status, @JsonProperty("message") String message, @JsonProperty("errorCode") int errorCode) {
        this.status = status;
        this.message = message;
        this.errorCode = errorCode;
    }
}