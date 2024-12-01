package com.example.restaurantbatch.repository.impl;

import com.example.restaurantbatch.model.RestaurantAddress;
import com.example.restaurantbatch.repository.RestaurantAddressRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
class RestaurantAddressRepositoryImpl implements RestaurantAddressRepository {

    private final JdbcTemplate jdbcTemplate;

    public RestaurantAddressRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void saveAll(List<RestaurantAddress> restaurantAddresses) {
        String sql = "INSERT INTO restaurant_address ("
                + "restaurant_id, "
                + "location_phone, "
                + "location_area, "
                + "location_postal_code, "
                + "location_full_address, "
                + "roadName_full_address, "
                + "roadName_postal_code, "
                + "coordinate_x, "
                + "coordinate_y "
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sql,
                restaurantAddresses,
                restaurantAddresses.size(),
                (PreparedStatement ps, RestaurantAddress restaurantAddress) -> {
                    ps.setLong(1, restaurantAddress.getRestaurantId());
                    ps.setString(2, restaurantAddress.getLocationPhone());
                    ps.setString(3, restaurantAddress.getLocationArea());
                    ps.setString(4, restaurantAddress.getLocationPostalCode());
                    ps.setString(5, restaurantAddress.getLocationFullAddress());
                    ps.setString(6, restaurantAddress.getRoadNameFullAddress());
                    ps.setString(7, restaurantAddress.getRoadNamePostalCode());
                    ps.setString(8, restaurantAddress.getCoordinateX());
                    ps.setString(9, restaurantAddress.getCoordinateY());
                });
    }
}
