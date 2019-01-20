package com.cherentsov.mapback.Service;

import org.hibernate.HibernateException;

public interface IDBService {
    void Close();
    <T> Long create(final T entity) throws HibernateException;
}
