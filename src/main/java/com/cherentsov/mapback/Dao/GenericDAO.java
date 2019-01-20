package com.cherentsov.mapback.Dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericDAO<T> implements IGenericDAO<T> {

    private Session session;

    public GenericDAO(Session session) {
        this.session = session;
    }

    @Override
    public Long create(T entity) throws HibernateException {
        return (Long)session.save(entity);
    }

    @Override
    public T read(Long identifier, Class<?> persistClass) throws HibernateException {
        return (T) session.get(persistClass, identifier);
    }

    @Override
    public void update(T transientInstance) throws HibernateException{
        session.update(transientInstance);
    }

    @Override
    public void delete(T persistentInstance) throws HibernateException{
        session.delete(persistentInstance);
    }
}
