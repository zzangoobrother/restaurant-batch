package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.Restaurant;

import java.util.List;

public class InMemoryRestaurantRepository extends InMemoryRepository implements RestaurantRepository {

    @Override
    public void saveAll(List<Restaurant> restaurants) {
        restaurants.forEach(this::insert);
    }

    @Override
    public List findMany() {
        return super.findMany();
    }
}
