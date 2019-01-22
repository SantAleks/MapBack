package com.cherentsov.mapback.Dao;

import com.cherentsov.mapback.Model.*;

import java.util.List;

public interface IGenericDAO {
    <T> Long create(final T newInstance);

    <T> T read(Class<T> persistClass, final Long identifier);

    <T> void update(final T transientInstance);

    <T> void delete(final T persistentInstance);

    <T> List<T> findByName(final Class<T> persistClass, final String name);

    <T> List<T> getAll(final Class<T> persistClass);

    List<Point> findPointByFK(final List<Country> lCountry, final List<City> lCity, final List<Bank> lBank);
}

