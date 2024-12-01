package com.example.restaurantbatch.repository.impl;

import com.example.restaurantbatch.model.RestaurantOption;
import com.example.restaurantbatch.repository.RestaurantOptionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class RestaurantOptionRepositoryImpl implements RestaurantOptionRepository {

    private final JdbcTemplate jdbcTemplate;

    public RestaurantOptionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void saveAll(List<RestaurantOption> restaurantCsvDtos) {
        String sql = "INSERT INTO restaurant_option ("
                + "restaurant_id, "
                + "male_employee_count, "
                + "female_employee_count, "
                + "business_area_type_name, "
                + "grade_type_name, "
                + "water_supply_type_name, "
                + "total_employee_count, "
                + "headquarters_employee_count, "
                + "factory_office_employee_count, "
                + "factory_sales_employee_count, "
                + "factory_production_employee_count, "
                + "building_ownership_type_name, "
                + "guarantee_amount, "
                + "monthly_rent, "
                + "isMulti_use_facility, "
                + "total_facility_scale, "
                + "traditional_business_designation_number, "
                + "traditional_business_main_food, "
                + "website "
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sql,
                restaurantCsvDtos,
                restaurantCsvDtos.size(),
                (PreparedStatement ps, RestaurantOption restaurantOption) -> {
                    ps.setLong(1, restaurantOption.getRestaurantId());
                    ps.setString(2, restaurantOption.getMaleEmployeeCount());
                    ps.setString(3, restaurantOption.getFemaleEmployeeCount());
                    ps.setString(4, restaurantOption.getBusinessAreaTypeName());
                    ps.setString(5, restaurantOption.getGradeTypeName());
                    ps.setString(6, restaurantOption.getWaterSupplyTypeName());
                    ps.setString(7, restaurantOption.getTotalFacilityScale());
                    ps.setString(8, restaurantOption.getHeadquartersEmployeeCount());
                    ps.setString(9, restaurantOption.getFactoryOfficeEmployeeCount());
                    ps.setString(10, restaurantOption.getFactorySalesEmployeeCount());
                    ps.setString(11, restaurantOption.getFactoryProductionEmployeeCount());
                    ps.setString(12, restaurantOption.getBuildingOwnershipTypeName());
                    ps.setString(13, restaurantOption.getGuaranteeAmount());
                    ps.setString(14, restaurantOption.getMonthlyRent());
                    ps.setString(15, restaurantOption.getIsMultiUseFacility());
                    ps.setString(16, restaurantOption.getTotalFacilityScale());
                    ps.setString(17, restaurantOption.getTraditionalBusinessDesignationNumber());
                    ps.setString(18, restaurantOption.getTraditionalBusinessMainFood());
                    ps.setString(19, restaurantOption.getWebsite());
                });
    }
}
