package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.model.RestaurantAddress;
import com.example.restaurantbatch.repository.RestaurantAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RestaurantAddressWriter implements ItemWriter<RestaurantCsvDto> {

    private final RestaurantAddressRepository repository;

    @Override
    public void write(Chunk<? extends RestaurantCsvDto> chunk) throws Exception {
        List<RestaurantAddress> restaurantAddresses = chunk.getItems().stream()
                .map(RestaurantCsvDto::toRestaurantAddress)
                .toList();

        repository.saveAll(restaurantAddresses);
    }
}
