package dev.fastcampus.restaurantbatch.repository;

import dev.fastcampus.restaurantbatch.model.RestaurantAddress;

import java.util.List;

public interface RestaurantAddressRepository {
    void saveAll(List<RestaurantAddress> restaurantAddresses);
}
