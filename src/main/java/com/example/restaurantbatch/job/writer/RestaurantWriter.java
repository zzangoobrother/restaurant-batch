package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.model.Restaurant;
import com.example.restaurantbatch.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.example.restaurantbatch.job.reader.RestaurantCsvReader.businessStatusMap;

@RequiredArgsConstructor
@Configuration
public class RestaurantWriter implements ItemWriter<RestaurantCsvDto> {

    private final RestaurantRepository repository;

    @Override
    public void write(Chunk<? extends RestaurantCsvDto> chunk) throws Exception {
        List<Restaurant> restaurants = chunk.getItems().stream()
                .map(it -> it.toRestaurant(businessStatusMap.get(it.getBusinessStatusCode()).getId()))
                .toList();

        repository.saveAll(restaurants);
    }
}
