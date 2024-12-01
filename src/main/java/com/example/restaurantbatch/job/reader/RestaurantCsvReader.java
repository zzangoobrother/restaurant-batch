package com.example.restaurantbatch.job.reader;

import com.example.restaurantbatch.config.FileProperties;
import com.example.restaurantbatch.job.dto.RestaurantCsvDto;
import com.example.restaurantbatch.model.BusinessStatus;
import com.example.restaurantbatch.model.BusinessType;
import com.example.restaurantbatch.repository.BusinessStatusRepository;
import com.example.restaurantbatch.repository.BusinessTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Configuration
public class RestaurantCsvReader {

    public static Map<String, BusinessStatus> businessStatusMap = new HashMap<>();
    public static Map<String, BusinessType> businessTypeHashMap = new HashMap<>();

    private final BusinessStatusRepository businessStatusRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final FileProperties fileProperties;

    @Bean
    public FlatFileItemReader<RestaurantCsvDto> flatFileItemReader() {
        businessStatusMap = businessStatusRepository.getAll().stream()
                .collect(Collectors.toMap(BusinessStatus::getBusinessStatusCode, Function.identity()));

        businessTypeHashMap = businessTypeRepository.getAll().stream()
                .collect(Collectors.toMap(BusinessType::getOpenServiceId, Function.identity()));

        return new FlatFileItemReaderBuilder<RestaurantCsvDto>()
                .name("restaurantCsvReader")
                .encoding(fileProperties.encoding())
                .resource(new FileSystemResource(fileProperties.path()))
                .lineTokenizer(new CustomDelimitedLineTokenizer())
                .linesToSkip(1)
                .fieldSetMapper(new CsvFieldSetMapper())
                .build();
    }

    class CustomDelimitedLineTokenizer extends DelimitedLineTokenizer {
        private static final char DEFAULT_QUOTE_CHARACTER = '"';

        @Override
        protected boolean isQuoteCharacter(char c) {
            return c == DEFAULT_QUOTE_CHARACTER;
        }
    }

    class CsvFieldSetMapper implements FieldSetMapper<RestaurantCsvDto> {
        @Override
        public RestaurantCsvDto mapFieldSet(FieldSet fieldSet) throws BindException {
            return RestaurantCsvDto.builder()
                    .no(fieldSet.readLong(0))
                    .openServiceName(fieldSet.readString(1))
                    .openServiceId(fieldSet.readString(2))
                    .openLocalGovernmentCode(fieldSet.readString(3))
                    .managementNumber(fieldSet.readString(4))
                    .approvalDate(fieldSet.readString(5))
                    .licenseCancellationDate(fieldSet.readString(6))
                    .businessStatusCode(fieldSet.readString(7))
                    .businessStatusName(fieldSet.readString(8))
                    .detailedBusinessStatusCode(fieldSet.readString(9))
                    .detailedBusinessStatusName(fieldSet.readString(10))
                    .closureDate(fieldSet.readString(11))
                    .suspensionStartDate(fieldSet.readString(12))
                    .suspensionEndDate(fieldSet.readString(13))
                    .reopeningDate(fieldSet.readString(14))
                    .locationPhone(fieldSet.readString(15))
                    .locationArea(fieldSet.readString(16))
                    .locationPostalCode(fieldSet.readString(17))
                    .locationFullAddress(fieldSet.readString(18))
                    .roadNameFullAddress(fieldSet.readString(19))
                    .roadNamePostalCode(fieldSet.readString(20))
                    .businessName(fieldSet.readString(21))
                    .lastModifiedTime(fieldSet.readString(22))
                    .dataUpdateType(fieldSet.readString(23))
                    .dataUpdateDate(fieldSet.readString(24))
                    .businessCategoryName(fieldSet.readString(25))
                    .coordinateX(fieldSet.readString(26))
                    .coordinateY(fieldSet.readString(27))
                    .sanitationTypeName(fieldSet.readString(28))
                    .maleEmployeeCount(fieldSet.readString(29))
                    .femaleEmployeeCount(fieldSet.readString(30))
                    .businessAreaTypeName(fieldSet.readString(31))
                    .gradeTypeName(fieldSet.readString(32))
                    .waterSupplyTypeName(fieldSet.readString(33))
                    .totalEmployeeCount(fieldSet.readString(34))
                    .headquartersEmployeeCount(fieldSet.readString(35))
                    .factoryOfficeEmployeeCount(fieldSet.readString(36))
                    .factorySalesEmployeeCount(fieldSet.readString(37))
                    .factoryProductionEmployeeCount(fieldSet.readString(38))
                    .buildingOwnershipTypeName(fieldSet.readString(39))
                    .guaranteeAmount(fieldSet.readString(40))
                    .monthlyRent(fieldSet.readString(41))
                    .isMultiUseFacility(fieldSet.readString(42))
                    .totalFacilityScale(fieldSet.readString(43))
                    .traditionalBusinessDesignationNumber(fieldSet.readString(44))
                    .traditionalBusinessMainFood(fieldSet.readString(45))
                    .website(fieldSet.readString(46))
                    .build();
        }
    }
}
