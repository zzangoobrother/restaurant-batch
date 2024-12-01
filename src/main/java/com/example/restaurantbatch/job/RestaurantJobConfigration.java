package com.example.restaurantbatch.job;

import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.job.reader.RestaurantCsvReader;
import com.example.restaurantbatch.job.writer.RestaurantCompositeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class RestaurantJobConfigration {

    private final RestaurantCsvReader restaurantCsvReader;
    private final RestaurantCompositeWriter restaurantCompositeWriter;

    @Bean
    public Job exchangeJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("restaurantJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("restaurantStep", jobRepository)
                .<RestaurantCsvDto, RestaurantCsvDto>chunk(1000, platformTransactionManager)
                .reader(restaurantCsvReader.flatFileItemReader())
                .writer(restaurantCompositeWriter.writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("async-step");
    }
}
