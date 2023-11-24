package com.example.demo.common.exception_handler.jwt.unauthorized;

import com.example.demo.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class JwtExpiredTokenException extends JwtUnauthorizedTokenException {

    private final ResponseStatus exceptionStatus;

    public JwtExpiredTokenException(ResponseStatus exceptionStatus) {
        super(exceptionStatus);
        this.exceptionStatus = exceptionStatus;
    }
}
