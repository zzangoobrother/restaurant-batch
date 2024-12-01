package dev.fastcampus.restaurantbatch.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Restaurant {
    private Long id;

    private Long businessStatusId;

    private String openLocalGovernmentCode;

    private String managementNumber;

    private String approvalDate;

    private String licenseCancellationDate;

    private String closureDate;

    private String suspensionStartDate;

    private String suspensionEndDate;

    private String reopeningDate;

    private String businessName;

    private String lastModifiedTime;

    private String dataUpdateType;

    private String dataUpdateDate;

    private String businessCategoryName;

    private String sanitationTypeName;
}
