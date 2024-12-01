package dev.fastcampus.restaurantbatch.job.writer;

import dev.fastcampus.restaurantbatch.job.dto.RestaurantCsvDto;
import dev.fastcampus.restaurantbatch.model.RestaurantAddress;
import dev.fastcampus.restaurantbatch.repository.InMemoryRestaurantAddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantAddressWriterTest {

    private InMemoryRestaurantAddressRepository restaurantAddressRepository;
    public RestaurantAddressWriter restaurantAddressWriter;

    @BeforeEach
    void setUp() {
        restaurantAddressRepository = new InMemoryRestaurantAddressRepository();
        restaurantAddressWriter = new RestaurantAddressWriter(restaurantAddressRepository);
    }

    @Test
    void 음식점_주소_정보_저장() throws Exception {
        restaurantAddressWriter.write(Chunk.of(RestaurantCsvDto.builder().locationFullAddress("대전광역시 서구 관저동 1020").build()));

        List<RestaurantAddress> restaurantAddresses = restaurantAddressRepository.findMany();

        assertThat(restaurantAddresses.size()).isEqualTo(1);
        assertThat(restaurantAddresses.get(0).getLocationFullAddress()).isEqualTo("대전광역시 서구 관저동 1020");
    }
}
