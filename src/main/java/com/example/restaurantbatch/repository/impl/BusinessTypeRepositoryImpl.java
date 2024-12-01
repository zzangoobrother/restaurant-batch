package com.example.restaurantbatch.repository.impl;

import com.example.restaurantbatch.model.BusinessType;
import com.example.restaurantbatch.repository.BusinessTypeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class BusinessTypeRepositoryImpl implements BusinessTypeRepository {

    private final JdbcTemplate jdbcTemplate;

    public BusinessTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<BusinessType> getAll() {
        String sql = "SELECT * FROM business_type";
        List<BusinessType> result = jdbcTemplate.query(
                sql,
                (rs, rowNum) -> {
                    BusinessType businessType = BusinessType.builder()
                            .id(rs.getLong("id"))
                            .openServiceName(rs.getString("open_service_name"))
                            .openServiceId(rs.getString("open_service_id"))
                            .build();

                    return businessType;
                }
        );

        return result;
    }
}
