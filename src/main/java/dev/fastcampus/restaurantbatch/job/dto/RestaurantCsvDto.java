package dev.fastcampus.restaurantbatch.job.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantCsvDto {
    int no;
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
}
