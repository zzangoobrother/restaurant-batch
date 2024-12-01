package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Configuration
public class RestaurantCompositeWriter {

    private final RestaurantWriter restaurantWriter;
    private final RestaurantAddressWriter restaurantAddressWriter;
    private final RestaurantOptionWriter restaurantOptionWriter;

    @Bean
    public CompositeItemWriter<RestaurantCsvDto> writer() {
        List<ItemWriter<? super RestaurantCsvDto>> writers = Stream.of(
                restaurantWriter,
                restaurantAddressWriter,
                restaurantOptionWriter
        ).collect(Collectors.toList());

        return new CompositeItemWriterBuilder<RestaurantCsvDto>()
                .delegates(writers)
                .build();
    }
}
