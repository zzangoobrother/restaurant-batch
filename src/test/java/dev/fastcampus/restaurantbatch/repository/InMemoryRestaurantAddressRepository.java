package dev.fastcampus.restaurantbatch.repository;

import dev.fastcampus.restaurantbatch.model.RestaurantAddress;

import java.util.List;

public class InMemoryRestaurantAddressRepository extends InMemoryRepository implements RestaurantAddressRepository {
    @Override
    public void saveAll(List<RestaurantAddress> restaurantAddresses) {
        restaurantAddresses.forEach(this::insert);
    }

    @Override
    public List<RestaurantAddress> findMany() {
        return super.findMany();
    }
}
