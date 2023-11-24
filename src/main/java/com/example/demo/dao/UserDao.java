package com.example.demo.dao;

import com.example.demo.dto.GetUserResponse;
import com.example.demo.dto.PostUserRequest;
import com.example.demo.dto.PutUserInfoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public boolean hasDuplicateEmail(String email) {
        String sql = "select exists(select user_email from users where user_email=:email and user_status in ('일반','휴면'))";
        Map<String, Object> param = Map.of("email",email);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public boolean hasDuplicateNickName(String nickname) {
        String sql = "select exists(select user_nickname from users where user_nickname=:nickname and user_status in ('일반','휴면'))";
        Map<String, Object> param = Map.of("nickname",nickname);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, param, boolean.class));
    }

    public long createUser(PostUserRequest postUserRequest) {
        String sql = "insert into users(user_email, user_password, user_phonenumber, user_nickname, user_name, user_currentaddress, user_imageurl)" +
                "values(:email, :password, :phoneNumber, :nickname,:name, :currentAddress,:profileImage)";
        SqlParameterSource param = new BeanPropertySqlParameterSource(postUserRequest);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, param,keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public int modifyNickName(long userId, String nickName) {
        String sql = "update users set user_nickname=:nickName where user_id=:userId";
        Map<String, Object> param = Map.of(
                "nickName",nickName,
                "userId",userId
        );
        return jdbcTemplate.update(sql, param);
    }

    public int modifyUserinfo(long userId, PutUserInfoRequest putUserInfoRequest) {
        String sql = "update users set user_name=:name, user_nickname=:nickname, user_password=:password, user_email=:email, user_phonenumber=:phoneNumber," +
                "user_imageurl=:imageUrl, user_currentaddress=:currentAddress where user_id=:userId";
        Map<String, Object> param = Map.of(
                "name",putUserInfoRequest.getName(),
                "nickname", putUserInfoRequest.getNickName(),
                "password", putUserInfoRequest.getPassword(),
                "email", putUserInfoRequest.getEmail(),
                "phoneNumber",putUserInfoRequest.getPhoneNumber(),
                "imageUrl", putUserInfoRequest.getImageUrl(),
                "currentAddress",putUserInfoRequest.getCurrentAddress(),
                "userId",userId
        );
        return jdbcTemplate.update(sql,param);
    }

    public List<GetUserResponse> getUsers(String nickname, String email, String status) {
        String sql = "select user_email, user_phonenumber, user_nickname, user_imageUrl, user_status from users " +
                "where user_nickname like :nickname and user_email like :email and user_status=:status";
        Map<String, Object> param = Map.of(
                "nickname", "%" + nickname + "%",
                "email", "%" + email + "%",
                "status", status
        );

        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetUserResponse(
                        rs.getString("user_email"),
                        rs.getString("user_phonenumber"),
                        rs.getString("user_nickname"),
                        rs.getString("user_imageUrl"),
                        rs.getString("user_status"))
        );
    }
}
