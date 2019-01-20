package com.cherentsov.mapback.Service;

import org.hibernate.HibernateException;

public interface IDBService<T> {
    public void Close();
    public Long create(final T entity) throws HibernateException;
}
