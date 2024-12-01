package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.model.RestaurantOption;
import com.example.restaurantbatch.repository.RestaurantOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RestaurantOptionWriter implements ItemWriter<RestaurantCsvDto> {

    private final RestaurantOptionRepository repository;

    @Override
    public void write(Chunk<? extends RestaurantCsvDto> chunk) throws Exception {
        List<RestaurantOption> restaurantOptions = chunk.getItems().stream()
                .map(RestaurantCsvDto::toRestaurantOption)
                .toList();

        repository.saveAll(restaurantOptions);
    }
}
