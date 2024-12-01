package dev.fastcampus.restaurantbatch.job.writer;

import dev.fastcampus.restaurantbatch.job.dto.RestaurantCsvDto;
import dev.fastcampus.restaurantbatch.model.RestaurantAddress;
import dev.fastcampus.restaurantbatch.repository.RestaurantAddressRepository;
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
