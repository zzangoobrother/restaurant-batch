package dev.fastcampus.restaurantbatch.repository;

import dev.fastcampus.restaurantbatch.model.RestaurantOption;

import java.util.List;

public interface RestaurantOptionRepository {
    void saveAll(List<RestaurantOption> restaurantOptions);
}
