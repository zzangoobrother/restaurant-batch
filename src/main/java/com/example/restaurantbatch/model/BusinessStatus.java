package com.example.restaurantbatch.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BusinessStatus {
    private Long id;

    private String businessStatusCode;

    private String businessStatusName;

    private String detailedBusinessStatusCode;

    private String detailedBusinessStatusName;
}
