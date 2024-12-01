package com.example.restaurantbatch.job.reader;

import com.example.restaurantbatch.config.FileProperties;
import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Profile("test")
@ContextConfiguration(classes = RestaurantCsvReader.class)
class RestaurantCsvReaderTest {

    private FileProperties fileProperties;
    private RestaurantCsvReader reader;

    private FlatFileItemReader<RestaurantCsvDto> flatFileItemReader;

    @BeforeEach
    void setUp() {
        fileProperties = new FileProperties("src/test/resources/data/test.csv", "UTF-8");
        this.reader = new RestaurantCsvReader(fileProperties);
    }

    @Test
    void csv_데이터_읽기() throws Exception {
        flatFileItemReader = reader.flatFileItemReader();

        StepExecution stepExecution = MetaDataInstanceFactory.createStepExecution();

        List<RestaurantCsvDto> result = StepScopeTestUtils.doInStepScope(stepExecution, () -> {
            List<RestaurantCsvDto> dtos = new ArrayList<>();
            RestaurantCsvDto dto;
            flatFileItemReader.open(stepExecution.getExecutionContext());
            while ((dto = flatFileItemReader.read()) != null) {
                dtos.add(dto);
            }

            flatFileItemReader.close();
            return dtos;
        });

        assertThat(result.get(0).getBusinessName()).isEqualTo("test음식점1");
    }
}
