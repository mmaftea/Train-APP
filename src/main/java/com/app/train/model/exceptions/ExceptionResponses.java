package com.app.train.model.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public enum ExceptionResponses {

    RESOURCE_NOT_FOUND(NOT_FOUND,"There is no such resource",4000),
    STATION_NOT_FOUND(NOT_FOUND, "The station with the id %s does not exist", 4001),
    ROUTE_NOT_FOUND(NOT_FOUND,"The route with the id %s does not exist",4002),
    TRAVEL_NOT_FOUND(NOT_FOUND,"The travel with the id %s does not exist",4003),
    MALFORMED(BAD_REQUEST,"Request object JSON malformed",5001),
    CONFLICT(BAD_REQUEST,"This request violates some constraints.",5002),
    NOT_ACCEPTABLE_CONTENT(NOT_ACCEPTABLE,"Content type is not acceptable",6001),
    BAD_CREDENTIALS(FORBIDDEN,"The username or password is incorrect",9432),
    ACCESS_DENIED(FORBIDDEN,"You are not authorized to access this resource",9341);

    private final HttpStatus httpStatus;
    private final String messageTemplate;
    private final int errorCode;

    ExceptionResponses(HttpStatus httpStatus, String messageTemplate, int errorCode) {
        this.httpStatus = httpStatus;
        this.messageTemplate = messageTemplate;
        this.errorCode = errorCode;
    }

    public ApiError buildApiError(String messageParam) {
        final String message = String.format(messageTemplate, messageParam);
        return new ApiError(httpStatus, message, errorCode);
    }

    public ApiError buildApiError() {
        return buildApiError(null);
    }
}
