package com.monresto.dao;

import java.util.List;

public interface GenericDAO<T, K> {
    void create(T obj) throws Exception;
    T findById(K id) throws Exception;
    List<T> findAll() throws Exception;
    void update(T obj) throws Exception;
    void delete(K id) throws Exception;
}
