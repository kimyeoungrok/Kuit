package com.example.demo.common.exception;

import com.example.demo.response.status.ResponseStatus;
import lombok.Getter;

@Getter
public class DatabaseException extends RuntimeException{

    private final ResponseStatus exceptionStatus;

    public DatabaseException(ResponseStatus exceptionStatus) {
        super(exceptionStatus.getMessage());
        this.exceptionStatus = exceptionStatus;
    }
}
