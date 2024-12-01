package dev.fastcampus.restaurantbatch.repository;

import dev.fastcampus.restaurantbatch.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    void saveAll(List<Restaurant> restaurants);
}
