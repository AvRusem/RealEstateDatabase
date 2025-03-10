package org.real_estate_system.repository;

import java.util.List;

public interface Repository<T> {
    void create(T entity);
    List<T> getAll();
    void update(int index, T entity);
    void delete(int index);
}