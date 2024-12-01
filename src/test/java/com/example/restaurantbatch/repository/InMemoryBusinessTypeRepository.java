package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.BusinessType;

import java.util.List;

public class InMemoryBusinessTypeRepository extends InMemoryRepository<BusinessType> implements BusinessTypeRepository {
    @Override
    public List<BusinessType> getAll() {
        return findMany();
    }
}
