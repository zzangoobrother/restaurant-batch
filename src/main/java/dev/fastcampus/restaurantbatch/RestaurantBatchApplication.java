package dev.fastcampus.restaurantbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class RestaurantBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantBatchApplication.class, args);
    }

}
