package dev.fastcampus.restaurantbatch.job.writer;

import dev.fastcampus.restaurantbatch.job.dto.RestaurantCsvDto;
import dev.fastcampus.restaurantbatch.model.Restaurant;
import dev.fastcampus.restaurantbatch.repository.InMemoryRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantWriterTest {

    private InMemoryRestaurantRepository restaurantRepository;
    private RestaurantWriter restaurantWriter;

    @BeforeEach
    void setUp() {
        restaurantRepository = new InMemoryRestaurantRepository();
        restaurantWriter = new RestaurantWriter(restaurantRepository);
    }

    @Test
    void 음식점_정보_저장() throws Exception {
        restaurantWriter.write(Chunk.of(RestaurantCsvDto.builder().businessName("test restaurant").build()));

        List<Restaurant> restaurants = restaurantRepository.findMany();

        assertThat(restaurants.size()).isEqualTo(1);
        assertThat(restaurants.get(0).getBusinessName()).isEqualTo("test restaurant");
    }
}
