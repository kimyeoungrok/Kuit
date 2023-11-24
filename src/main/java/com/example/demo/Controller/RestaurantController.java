package com.example.demo.Controller;

import com.example.demo.Service.RestaurantService;
import com.example.demo.dto.GetRestaurantResponse;
import com.example.demo.dto.GetUserResponse;
import com.example.demo.response.BaseResponse;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("")
    public BaseResponse<List<GetRestaurantResponse>> getRestaurants(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "0") long minimumPrice
    ){
        log.info("[RestaurantController.getUsers]");
        return new BaseResponse<>(restaurantService.getRestaurants(name, minimumPrice));
    }
}

