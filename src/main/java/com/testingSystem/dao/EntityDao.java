package com.testingSystem.dao;

import com.testingSystem.util.DataBaseException;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<T, PK extends Serializable> {

    List<T> getAll(String orderParameter) throws DataBaseException;

    T getById(PK id) throws DataBaseException;

    T get(String propertyName, Object value) throws DataBaseException;

    PK save(T entity) throws DataBaseException;

    void update(T entity) throws DataBaseException;

    void delete(T entity) throws DataBaseException;
}
