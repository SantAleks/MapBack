package com.cherentsov.mapback.Dao;

import com.cherentsov.mapback.Model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
        return session.get(persistClass, identifier);
    }
    @Override
    public <T> List<T> findByName(final Class<T> persistClass, String name) {
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName() +
                        " where lower(name) like :parName ORDER BY name", persistClass);
        query.setParameter("parName", "%" + name.toLowerCase() + "%");
        return query.list();
    }

    public <T> List<T> getAll(final Class<T> persistClass) {
        Query<T> query = session.createQuery("from " + persistClass.getSimpleName() + " ORDER BY name", persistClass);
        return query.list();
    }

    @Override
    public List<Point> findPointByFK(List<Country> lCountry, List<City> lCity, List<Bank> lBank) {
        String qS = "select pt from Point as pt";

        //Если задана только страна, но не задан город, отдадим все пункты в стране
        if (lCountry.size() == 1 && lCity.size() != 1){
            qS = qS + " join pt.city as cit with cit.country.id = " + lCountry.get(0).getId();
        }
        if (lBank.size() == 1){
            qS = qS + " where pt.bank.id = " + lBank.get(0).getId();
        }
        if (lCity.size() == 1){
            qS = qS + (lBank.size() != 1?" where ":" and ");
            qS = qS + "city.id = " + lCity.get(0).getId();
        }
        qS = qS + " ORDER BY pt.address";
        Query<Point> query = session.createQuery(qS , Point.class).setMaxResults(200);
        return query.list();
    }
}
