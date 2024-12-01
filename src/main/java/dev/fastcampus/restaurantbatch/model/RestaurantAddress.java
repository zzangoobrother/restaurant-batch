package dev.fastcampus.restaurantbatch.model;

import lombok.Getter;

@Getter
public class RestaurantAddress {
    private Long id;

    private Long foodStoreId;

    private Long businessStatusId;

    private String locationPhone;

    private String locationArea;

    private String locationPostalCode;

    private String locationFullAddress;

    private String roadNameFullAddress;

    private String roadNamePostalCode;

    private String coordinateX;

    private String coordinateY;
}
