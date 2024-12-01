package dev.fastcampus.restaurantbatch.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RestaurantAddress {
    private Long id;

    private Long restaurantId;

    private String locationPhone;

    private String locationArea;

    private String locationPostalCode;

    private String locationFullAddress;

    private String roadNameFullAddress;

    private String roadNamePostalCode;

    private String coordinateX;

    private String coordinateY;
}
