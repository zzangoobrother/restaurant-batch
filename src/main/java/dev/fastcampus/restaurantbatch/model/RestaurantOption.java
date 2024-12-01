package dev.fastcampus.restaurantbatch.model;

import lombok.Getter;

@Getter
public class RestaurantOption {
    private Long id;

    private Long restaurantId;

    private String maleEmployeeCount;

    private String femaleEmployeeCount;

    private String businessAreaTypeName;

    private String gradeTypeName;

    private String waterSupplyTypeName;

    private String totalEmployeeCount;

    private String headquartersEmployeeCount;

    private String factoryOfficeEmployeeCount;

    private String factorySalesEmployeeCount;

    private String factoryProductionEmployeeCount;

    private String buildingOwnershipTypeName;

    private String guaranteeAmount;

    private String monthlyRent;

    private String isMultiUseFacility;

    private String totalFacilityScale;

    private String traditionalBusinessDesignationNumber;

    private String traditionalBusinessMainFood;

    private String website;
}
