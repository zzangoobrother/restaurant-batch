package com.example.restaurantbatch.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BusinessType {
    private Long id;

    private String openServiceName;

    private String openServiceId;
}
