package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.RestaurantOption;

import java.util.List;

public interface RestaurantOptionRepository {
    void saveAll(List<RestaurantOption> restaurantOptions);
}
