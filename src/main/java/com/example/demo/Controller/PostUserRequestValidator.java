package com.example.demo.Controller;

import com.example.demo.dto.PostUserRequest;
import com.example.demo.dto.PostUserResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PostUserRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostUserRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PostUserRequest postUserRequest = (PostUserRequest) target;
    }
}
