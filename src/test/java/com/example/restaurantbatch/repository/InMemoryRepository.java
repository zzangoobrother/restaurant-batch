package com.example.restaurantbatch.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class InMemoryRepository<T> {
    private Long currentId;
    private final List<T> objects = new ArrayList<>();

    protected InMemoryRepository() {
        this.currentId = 1L;
    }

    protected List<T> findManyBy(Predicate<T> condition) {
        return objects.stream()
                .filter(condition)
                .collect(Collectors.toList());
    }

    protected List<T> findMany() {
        return objects.stream()
                .collect(Collectors.toList());
    }

    protected Optional<T> findOne(Predicate<T> condition) {
        return objects.stream()
                .filter(condition)
                .findFirst();
    }

    public T insert(T object) {
        setIdIfPossible(object);
        objects.add(object);

        return object;
    }

    public void delete(Predicate<T> condition) {
        objects.removeIf(condition);
    }

    private void setIdIfPossible(T object) {
        try {
            Field idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(object, currentId);
            currentId++;
        } catch (Exception e) {

        }
    }
}
