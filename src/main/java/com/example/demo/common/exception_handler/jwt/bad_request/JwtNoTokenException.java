package com.example.demo.common.exception_handler.jwt.bad_request;

import com.example.demo.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtNoTokenException extends JwtBadRequestException {

    private final ResponseStatus exceptionStatus;

    public JwtNoTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }
}
