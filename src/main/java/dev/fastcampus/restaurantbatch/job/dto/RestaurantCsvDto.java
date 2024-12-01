package dev.fastcampus.restaurantbatch.job.dto;

import dev.fastcampus.restaurantbatch.model.Restaurant;
import dev.fastcampus.restaurantbatch.model.RestaurantAddress;
import dev.fastcampus.restaurantbatch.model.RestaurantOption;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantCsvDto {
    Long no;
    String openServiceName;
    String openServiceId;
    String openLocalGovernmentCode;
    String managementNumber;
    String approvalDate;
    String licenseCancellationDate;
    String businessStatusCode;
    String businessStatusName;
    String detailedBusinessStatusCode;
    String detailedBusinessStatusName;
    String closureDate;
    String suspensionStartDate;
    String suspensionEndDate;
    String reopeningDate;
    String locationPhone;
    String locationArea;
    String locationPostalCode;
    String locationFullAddress;
    String roadNameFullAddress;
    String roadNamePostalCode;
    String businessName;
    String lastModifiedTime;
    String dataUpdateType;
    String dataUpdateDate;
    String businessCategoryName;
    String coordinateX;
    String coordinateY;
    String sanitationTypeName;
    String maleEmployeeCount;
    String femaleEmployeeCount;
    String businessAreaTypeName;
    String gradeTypeName;
    String waterSupplyTypeName;
    String totalEmployeeCount;
    String headquartersEmployeeCount;
    String factoryOfficeEmployeeCount;
    String factorySalesEmployeeCount;
    String factoryProductionEmployeeCount;
    String buildingOwnershipTypeName;
    String guaranteeAmount;
    String monthlyRent;
    String isMultiUseFacility;
    String totalFacilityScale;
    String traditionalBusinessDesignationNumber;
    String traditionalBusinessMainFood;
    String website;

    public Restaurant toRestaurant(Long businessStatusId) {
        return Restaurant.builder()
                .id(getNo())
                .businessStatusId(businessStatusId)
                .openLocalGovernmentCode(getOpenLocalGovernmentCode())
                .managementNumber(getManagementNumber())
                .approvalDate(getApprovalDate())
                .licenseCancellationDate(getLicenseCancellationDate())
                .closureDate(getClosureDate())
                .suspensionStartDate(getSuspensionStartDate())
                .suspensionEndDate(getSuspensionEndDate())
                .reopeningDate(getReopeningDate())
                .businessName(getBusinessName())
                .lastModifiedTime(getLastModifiedTime())
                .dataUpdateType(getDataUpdateType())
                .dataUpdateDate(getDataUpdateDate())
                .businessCategoryName(getBusinessCategoryName())
                .sanitationTypeName(getSanitationTypeName())
                .build();
    }

    public RestaurantAddress toRestaurantAddress() {
        return RestaurantAddress.builder()
                .restaurantId(getNo())
                .locationPhone(getLocationPhone())
                .locationArea(getLocationArea())
                .locationPostalCode(getLocationPostalCode())
                .locationFullAddress(getLocationFullAddress())
                .roadNameFullAddress(getRoadNameFullAddress())
                .roadNamePostalCode(getRoadNamePostalCode())
                .coordinateX(getCoordinateX())
                .coordinateY(getCoordinateY())
                .build();
    }

    public RestaurantOption toRestaurantOption() {
        return RestaurantOption.builder()
                .restaurantId(getNo())
                .maleEmployeeCount(getMaleEmployeeCount())
                .femaleEmployeeCount(getFemaleEmployeeCount())
                .businessAreaTypeName(getBusinessAreaTypeName())
                .gradeTypeName(getGradeTypeName())
                .waterSupplyTypeName(getWaterSupplyTypeName())
                .totalEmployeeCount(getTotalEmployeeCount())
                .headquartersEmployeeCount(getHeadquartersEmployeeCount())
                .factoryOfficeEmployeeCount(getFactoryOfficeEmployeeCount())
                .factorySalesEmployeeCount(getFactorySalesEmployeeCount())
                .factoryProductionEmployeeCount(getFactoryProductionEmployeeCount())
                .buildingOwnershipTypeName(getBuildingOwnershipTypeName())
                .guaranteeAmount(getGuaranteeAmount())
                .monthlyRent(getMonthlyRent())
                .isMultiUseFacility(getIsMultiUseFacility())
                .totalFacilityScale(getTotalFacilityScale())
                .traditionalBusinessDesignationNumber(getTraditionalBusinessDesignationNumber())
                .traditionalBusinessMainFood(getTraditionalBusinessMainFood())
                .website(getWebsite())
                .build();
    }
}
