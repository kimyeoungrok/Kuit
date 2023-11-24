package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.example.demo.common.exception.UserException;
import com.example.demo.dto.*;
import com.example.demo.response.BaseErrorResponse;
import com.example.demo.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.response.status.BaseExceptionResponseStatus.*;
import static com.example.demo.utils.BindingResultUtils.getErrorMessages;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("")
    public BaseResponse<PostUserResponse> signUp(@Validated @RequestBody PostUserRequest postUserRequest, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        //PostUserResponse postUserResponse = userService.signup(postUserRequest);
        return new BaseResponse<>(userService.signup(postUserRequest));
    }

    @PatchMapping("/{userId}/nickname")
    public BaseResponse<Object> modifyUser_nickName(@PathVariable long userId,
                                                    @Validated@RequestBody PatchNickNameRequest patchNicknameRequest, BindingResult bindingResult){
        log.info("[UserController.modifyUser_nickName]");
        if (bindingResult.hasErrors()) {
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        userService.modifyUser_nickName(userId, patchNicknameRequest.getNickName());
        return new BaseResponse<>(null);
    }

    @PutMapping("/{userId}")
    public BaseResponse<Object> modifyUser_information(@PathVariable long userId,
                                                       @Validated@RequestBody PutUserInfoRequest putUserInfoRequest, BindingResult bindingResult){
        log.info("[UserController.modifyUser_information]");
        if(bindingResult.hasErrors()){
            throw new UserException(INVALID_USER_VALUE, getErrorMessages(bindingResult));
        }
        userService.modifyUser_info(userId, putUserInfoRequest);
        return new BaseResponse<>(null);
    }

    @GetMapping("")
    public BaseResponse<List<GetUserResponse>> getUsers(
            @RequestParam(required = false, defaultValue = "") String nickname,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false, defaultValue = "일반") String status){
                log.info("[UserController.getUsers]");
                if(!status.equals("일반") && !status.equals("휴면") && !status.equals("탈퇴")){
                    throw new UserException(INVALID_USER_STATUS);
                }
                return new BaseResponse<>(userService.getUsers(nickname, email, status));
    }


}
