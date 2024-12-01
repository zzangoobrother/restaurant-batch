package com.example.restaurantbatch.repository.impl;

import com.example.restaurantbatch.model.Restaurant;
import com.example.restaurantbatch.repository.RestaurantRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
class RestaurantRepositoryImpl implements RestaurantRepository {
    private final JdbcTemplate jdbcTemplate;

    public RestaurantRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void saveAll(List<Restaurant> foodStores) {
        String sql = "INSERT INTO restaurant ("
                + "id, "
                + "business_status_id, "
                + "open_local_government_code, "
                + "management_number, "
                + "approval_date, "
                + "license_cancellation_date, "
                + "closure_date, "
                + "suspension_start_date, "
                + "suspension_end_date, "
                + "reopening_date, "
                + "business_name, "
                + "last_modified_time, "
                + "data_update_type, "
                + "data_update_date, "
                + "business_category_name, "
                + "sanitation_type_name "
                + ") VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(
                sql,
                foodStores,
                foodStores.size(),
                (PreparedStatement ps, Restaurant restaurant) -> {
                    ps.setLong(1, restaurant.getId());
                    ps.setLong(2, restaurant.getBusinessStatusId());
                    ps.setString(3, restaurant.getOpenLocalGovernmentCode());
                    ps.setString(4, restaurant.getManagementNumber());
                    ps.setString(5, restaurant.getApprovalDate());
                    ps.setString(6, restaurant.getLicenseCancellationDate());
                    ps.setString(7, restaurant.getClosureDate());
                    ps.setString(8, restaurant.getSuspensionStartDate());
                    ps.setString(9, restaurant.getSuspensionEndDate());
                    ps.setString(10, restaurant.getReopeningDate());
                    ps.setString(11, restaurant.getBusinessName());
                    ps.setString(12, restaurant.getLastModifiedTime());
                    ps.setString(13, restaurant.getDataUpdateType());
                    ps.setString(14, restaurant.getDataUpdateDate());
                    ps.setString(15, restaurant.getBusinessCategoryName());
                    ps.setString(16, restaurant.getSanitationTypeName());
                });
    }
}
