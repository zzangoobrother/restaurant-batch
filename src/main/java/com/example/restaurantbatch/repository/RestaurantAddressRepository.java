package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.RestaurantAddress;

import java.util.List;

public interface RestaurantAddressRepository {
    void saveAll(List<RestaurantAddress> restaurantAddresses);
}
