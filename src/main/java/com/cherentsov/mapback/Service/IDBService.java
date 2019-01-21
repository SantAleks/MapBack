package com.cherentsov.mapback.Service;

import org.hibernate.HibernateException;

import java.util.List;

public interface IDBService {
    void Close();
    <T> Long create(final T entity) throws HibernateException;
    <T> T read(Class<T> persistClass, Long id)throws HibernateException;
    <T> List<T> getAll(Class<T> persistClass) throws HibernateException;
    <T> List<T> findByName(Class<T> persistClass, String name) throws HibernateException;
}
