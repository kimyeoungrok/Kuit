//package com.example.demo.common.exception_handler;
//
//import com.example.demo.response.BaseErrorResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import static com.example.demo.response.status.BaseExceptionResponseStatus.BAD_REQUEST;
//
//@Slf4j
//@RestControllerAdvice
//public class TempExceptionControllerAdvice {
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public BaseErrorResponse runtimeExceptionHandler(RuntimeException e){
//        log.error("runtimeException=",e);
//        return new BaseErrorResponse(BAD_REQUEST, e.getMessage());
//    }
//}
