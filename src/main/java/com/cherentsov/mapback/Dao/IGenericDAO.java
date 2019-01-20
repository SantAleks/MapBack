package com.cherentsov.mapback.Dao;

public interface IGenericDAO<T> {
    public Long create(final T newInstance);

    public T read(final Long identifier, Class<?> persistClass);

    public void update(final T transientInstance);

    public void delete(final T persistentInstance);
}

