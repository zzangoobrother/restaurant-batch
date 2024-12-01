package dev.fastcampus.restaurantbatch.job.writer;

import dev.fastcampus.restaurantbatch.job.dto.RestaurantCsvDto;
import dev.fastcampus.restaurantbatch.model.Restaurant;
import dev.fastcampus.restaurantbatch.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RestaurantWriter implements ItemWriter<RestaurantCsvDto> {

    private final RestaurantRepository repository;

    @Override
    public void write(Chunk<? extends RestaurantCsvDto> chunk) throws Exception {
        List<Restaurant> restaurants = chunk.getItems().stream()
                .map(it -> it.toRestaurant(1L))
                .toList();

        repository.saveAll(restaurants);
    }
}
