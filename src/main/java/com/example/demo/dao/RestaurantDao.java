package com.example.demo.dao;

import com.example.demo.dto.GetRestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestaurantDao {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public RestaurantDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<GetRestaurantResponse> getRestaurants(String name, long minimumPrice) {
        String sql = "select restaurant_name, restaurant_address, restaurant_minimum_price, restaurant_status from restaurant "+
                "where restaurant_name like :name and restaurant_minimum_price <= :minimumPrice";
        Map<String, Object> param = Map.of(
                "name","%"+name+"%",
                "minimumPrice", minimumPrice
        );
        return jdbcTemplate.query(sql, param,
                (rs, rowNum) -> new GetRestaurantResponse(
                        rs.getString("restaurant_name"),
                        rs.getString("restaurant_address"),
                        rs.getLong("restaurant_minimum_price"),
                        rs.getString("restaurant_status"))
        );
    }
}
