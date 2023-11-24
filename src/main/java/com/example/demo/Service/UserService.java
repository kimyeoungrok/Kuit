package com.example.demo.Service;

import com.example.demo.common.exception.DatabaseException;
import com.example.demo.common.exception.UserException;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GetUserResponse;
import com.example.demo.dto.PostUserRequest;
import com.example.demo.dto.PostUserResponse;
import com.example.demo.dto.PutUserInfoRequest;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public PostUserResponse signup(PostUserRequest postUserRequest) {

        validateEmail(postUserRequest.getEmail());
        validateNickname(postUserRequest.getNickname());

        String encodePassword = passwordEncoder.encode(postUserRequest.getPassword());
        postUserRequest.setPassword(encodePassword);

        long userId = userDao.createUser(postUserRequest);



        return new PostUserResponse(userId);
    }

    private void validateNickname(String nickname) {

        if(userDao.hasDuplicateNickName(nickname)){
            throw new UserException(DUPLICATE_NICKNAME);
        }
    }

    private void validateEmail(String email) {
        if(userDao.hasDuplicateEmail(email)){
            throw new UserException(DUPLICATE_EMAIL);
        }
    }

    public void modifyUser_nickName(long userId, String nickName) {
        log.info("[UserService.modifyNickname]");

        validateNickname(nickName);
        int affectedRows = userDao.modifyNickName(userId, nickName);
        if(affectedRows != 1){
            throw new DatabaseException(DATABASE_ERROR);
        }
    }

    public void modifyUser_info(long userId, PutUserInfoRequest putUserInfoRequest){
        log.info("[UserService.modifyUserinfo]");

        validateNickname(putUserInfoRequest.getNickName());
        validateEmail(putUserInfoRequest.getEmail());

        int affectedRows = userDao.modifyUserinfo(userId, putUserInfoRequest);
        if(affectedRows != 1){
            throw new DatabaseException(DATABASE_ERROR);
        }
    }


    public List<GetUserResponse> getUsers(String nickname, String email, String status) {
        log.info("[UserService.getUsers]");
        return userDao.getUsers(nickname, email, status);
    }
}
