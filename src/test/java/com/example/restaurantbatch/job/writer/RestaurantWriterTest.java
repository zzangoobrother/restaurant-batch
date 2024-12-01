package com.example.restaurantbatch.job.writer;

import com.example.restaurantbatch.config.FileProperties;
import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.job.reader.RestaurantCsvReader;
import com.example.restaurantbatch.model.BusinessStatus;
import com.example.restaurantbatch.model.BusinessType;
import com.example.restaurantbatch.model.Restaurant;
import com.example.restaurantbatch.repository.BusinessStatusRepository;
import com.example.restaurantbatch.repository.InMemoryBusinessStatusRepository;
import com.example.restaurantbatch.repository.InMemoryBusinessTypeRepository;
import com.example.restaurantbatch.repository.InMemoryRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;

import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RestaurantWriterTest {

    private InMemoryBusinessStatusRepository businessStatusRepository;
    private InMemoryBusinessTypeRepository businessTypeRepository;
    private InMemoryRestaurantRepository restaurantRepository;
    private RestaurantWriter restaurantWriter;

    @BeforeEach
    void setUp() {
        businessStatusRepository = new InMemoryBusinessStatusRepository();
        businessStatusRepository.insert(BusinessStatus.builder().id(1L).businessStatusCode("code").build());

        businessTypeRepository = new InMemoryBusinessTypeRepository();
        businessTypeRepository.insert(BusinessType.builder().build());

        FileProperties fileProperties = new FileProperties("src/test/resources/data/test.csv", "UTF-8");
        RestaurantCsvReader reader = new RestaurantCsvReader(businessStatusRepository, businessTypeRepository, fileProperties);
        reader.flatFileItemReader();

        businessStatusRepository = new InMemoryBusinessStatusRepository();
        restaurantRepository = new InMemoryRestaurantRepository();
        restaurantWriter = new RestaurantWriter(restaurantRepository);
    }

    @Test
    void 음식점_정보_저장() throws Exception {
        restaurantWriter.write(Chunk.of(RestaurantCsvDto.builder().businessName("test restaurant").businessStatusCode("code").build()));

        List<Restaurant> restaurants = restaurantRepository.findMany();

        assertThat(restaurants.size()).isEqualTo(1);
        assertThat(restaurants.get(0).getBusinessName()).isEqualTo("test restaurant");
    }
}
