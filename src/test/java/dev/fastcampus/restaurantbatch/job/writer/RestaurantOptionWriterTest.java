package dev.fastcampus.restaurantbatch.job.writer;

import dev.fastcampus.restaurantbatch.job.dto.RestaurantCsvDto;
import dev.fastcampus.restaurantbatch.model.RestaurantOption;
import dev.fastcampus.restaurantbatch.repository.InMemoryRestaurantOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantOptionWriterTest {

    private InMemoryRestaurantOptionRepository restaurantOptionRepository;
    private RestaurantOptionWriter restaurantOptionWriter;

    @BeforeEach
    void setUp() {
        restaurantOptionRepository = new InMemoryRestaurantOptionRepository();
        restaurantOptionWriter = new RestaurantOptionWriter(restaurantOptionRepository);
    }

    @Test
    void 음식점_주소_정보_저장() throws Exception {
        restaurantOptionWriter.write(Chunk.of(RestaurantCsvDto.builder().waterSupplyTypeName("상수도전용").build()));

        List<RestaurantOption> restaurantOptions = restaurantOptionRepository.findMany();

        assertThat(restaurantOptions.size()).isEqualTo(1);
        assertThat(restaurantOptions.get(0).getWaterSupplyTypeName()).isEqualTo("상수도전용");
    }
}
