package com.example.demo.Service;

import com.example.demo.dao.RestaurantDao;
import com.example.demo.dto.GetRestaurantResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantDao restaurantDao;

    public List<GetRestaurantResponse> getRestaurants(String name, long minimumPrice) {
        log.info("[RestaurantService.getRestaurant]");
        return restaurantDao.getRestaurants(name, minimumPrice);
    }
}
