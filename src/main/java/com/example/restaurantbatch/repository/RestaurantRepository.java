package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    void saveAll(List<Restaurant> restaurants);
}
