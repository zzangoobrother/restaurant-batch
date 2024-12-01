package com.example.restaurantbatch.repository;

import com.example.restaurantbatch.model.BusinessStatus;

import java.util.List;

public class InMemoryBusinessStatusRepository extends InMemoryRepository<BusinessStatus> implements BusinessStatusRepository {
    @Override
    public List<BusinessStatus> getAll() {
        return findMany();
    }
}
