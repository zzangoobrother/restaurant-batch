package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.model.Restaurant;
import com.example.restaurantbatch.model.RestaurantAddress;
import com.example.restaurantbatch.model.RestaurantOption;
import com.example.restaurantbatch.repository.InMemoryRestaurantAddressRepository;
import com.example.restaurantbatch.repository.InMemoryRestaurantOptionRepository;
import com.example.restaurantbatch.repository.InMemoryRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.support.CompositeItemWriter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantCompositeWriterTest {

    private InMemoryRestaurantRepository restaurantRepository;
    private InMemoryRestaurantAddressRepository restaurantAddressRepository;
    private InMemoryRestaurantOptionRepository restaurantOptionRepository;

    private RestaurantWriter restaurantWriter;
    private RestaurantAddressWriter restaurantAddressWriter;
    private RestaurantOptionWriter restaurantOptionWriter;

    private RestaurantCompositeWriter restaurantCompositeWriter;

    @BeforeEach
    void setUp() {
        restaurantRepository = new InMemoryRestaurantRepository();
        restaurantWriter = new RestaurantWriter(restaurantRepository);

        restaurantAddressRepository = new InMemoryRestaurantAddressRepository();
        restaurantAddressWriter = new RestaurantAddressWriter(restaurantAddressRepository);

        restaurantOptionRepository = new InMemoryRestaurantOptionRepository();
        restaurantOptionWriter = new RestaurantOptionWriter(restaurantOptionRepository);

        restaurantCompositeWriter = new RestaurantCompositeWriter(restaurantWriter, restaurantAddressWriter, restaurantOptionWriter);
    }

    @Test
    void 음식점_정보_저장() throws Exception {
        CompositeItemWriter<RestaurantCsvDto> writer = restaurantCompositeWriter.writer();

        writer.write(Chunk.of(RestaurantCsvDto.builder()
                .businessName("test restaurant")
                .locationFullAddress("대전광역시 서구 관저동 1020")
                .waterSupplyTypeName("상수도전용")
                .build()));

        List<Restaurant> restaurants = restaurantRepository.findMany();
        List<RestaurantAddress> restaurantAddresses = restaurantAddressRepository.findMany();
        List<RestaurantOption> restaurantOptions = restaurantOptionRepository.findMany();

        assertThat(restaurants.size()).isEqualTo(1);
        assertThat(restaurants.get(0).getBusinessName()).isEqualTo("test restaurant");

        assertThat(restaurantAddresses.size()).isEqualTo(1);
        assertThat(restaurantAddresses.get(0).getLocationFullAddress()).isEqualTo("대전광역시 서구 관저동 1020");

        assertThat(restaurantOptions.size()).isEqualTo(1);
        assertThat(restaurantOptions.get(0).getWaterSupplyTypeName()).isEqualTo("상수도전용");
    }
}
