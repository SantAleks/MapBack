package com.cherentsov.mapback.Service;

import com.cherentsov.mapback.Model.*;
import org.hibernate.HibernateException;

import java.util.List;

public interface IDBService {
    void Close();
    <T> Long create(final T entity) throws HibernateException;
    <T> T read(final Class<T> persistClass, final Long id)throws HibernateException;
    <T> List<T> getAll(final Class<T> persistClass) throws HibernateException;
    <T> List<T> findByName(final Class<T> persistClass, final String name) throws HibernateException;
    List<Point> findPointByFK(final List<Country> lCountry, final List<City> lCity, final List<Bank> lBank);
}
