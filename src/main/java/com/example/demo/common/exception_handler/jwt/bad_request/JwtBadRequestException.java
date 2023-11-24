package com.example.demo.common.exception_handler.jwt.bad_request;

import com.example.demo.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtBadRequestException extends RuntimeException {

    private final ResponseStatus exceptionStatus;

    public JwtBadRequestException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
