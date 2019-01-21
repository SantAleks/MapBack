package com.cherentsov.mapback.Dao;

import com.cherentsov.mapback.Model.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GenericDAO implements IGenericDAO {

    private Session session;

    public GenericDAO(Session session) {
        this.session = session;
    }

    @Override
    public <T> Long create(T entity) throws HibernateException {
        return (Long)session.save(entity);
    }

    @Override
    public <T> void update(T transientInstance) throws HibernateException{
        session.update(transientInstance);
    }

    @Override
    public <T> void delete(T persistentInstance) throws HibernateException{
        session.delete(persistentInstance);
    }

    @Override
    public <T> T read(Class<T> persistClass, Long identifier) throws HibernateException {
        return (T) session.get(persistClass, identifier);
    }
    @Override
    public <T> List<T> findByName(final Class<T> persistClass, String name) {
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName() +
                        " where lower(name) like :parName", persistClass);

        query.setParameter("parName", "%" + name.toLowerCase() + "%");
        List<T> modelList = query.list();

        //Заставим Hibernate прочитать связанные таблицы по Foreign Key'ям выборки
        String model = null;
        for (T m : modelList){
            model = m.toString();
            //System.out.println(m.toString());
        }
        //List<T> = ArrayList<T>(modelList);
        return modelList;
    }

    public <T> List<T> getAll(final Class<T> persistClass) {
        //System.out.println("Name: " + persistClass.getSimpleName());
        //Query<T> query = session.createQuery("FROM " + persistClass.getSimpleName(), persistClass);
        //return query.list();
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName(), persistClass);
        List<T> modelList = query.list();
        //Заставим Hibernate прочитать связанные таблицы по Foreign Key'ям выборки
        String model = null;
        for (T m : modelList){
            model = m.toString();
            //System.out.println(m.toString());
        }
        //List<T> = ArrayList<T>(modelList);
        return modelList;
    }
}
