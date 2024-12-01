package dev.fastcampus.restaurantbatch.repository;

import dev.fastcampus.restaurantbatch.model.RestaurantOption;

import java.util.List;

public class InMemoryRestaurantOptionRepository extends InMemoryRepository<RestaurantOption> implements RestaurantOptionRepository {
    @Override
    public void saveAll(List<RestaurantOption> restaurantOptions) {
        restaurantOptions.forEach(this::insert);
    }

    @Override
    public List<RestaurantOption> findMany() {
        return super.findMany();
    }
}
