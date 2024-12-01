package com.example.restaurantbatch.repository.impl;

import com.example.restaurantbatch.model.BusinessStatus;
import com.example.restaurantbatch.repository.BusinessStatusRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BusinessStatusRepositoryImpl implements BusinessStatusRepository {

    private final JdbcTemplate jdbcTemplate;

    public BusinessStatusRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<BusinessStatus> getAll() {
        String sql = "SELECT * FROM business_status";
        List<BusinessStatus> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    BusinessStatus businessStatus = BusinessStatus.builder()
                            .id(rs.getLong("id"))
                            .businessStatusCode(rs.getString("business_status_code"))
                            .businessStatusName(rs.getString("business_status_name"))
                            .detailedBusinessStatusCode(rs.getString("detailed_business_status_code"))
                            .detailedBusinessStatusName(rs.getString("detailed_business_status_name"))
                            .build();

                    return businessStatus;
                }
        );

        return result;
    }
}
