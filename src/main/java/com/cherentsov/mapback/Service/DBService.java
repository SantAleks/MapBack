package com.cherentsov.mapback.Service;

import com.cherentsov.mapback.Dao.GenericDAO;
import com.cherentsov.mapback.Dao.IGenericDAO;
import com.cherentsov.mapback.Model.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Locale;

public class DBService implements IDBService {
    private static SessionFactory sessionFactory;
    private static final Log logger = LogFactory.getLog(DBService.class);
    private static DBService instance;

    public static synchronized DBService getInstance() {
        if (instance == null) {
            instance = new DBService();
            Configuration configuration = getOracleConfiguration();
            sessionFactory = createSessionFactory(configuration);
        }
        return instance;
    }

    @Override
    public void Close() {
        sessionFactory.close();
    }

    private static Configuration getOracleConfiguration() {
        logger.info("Конфиг соединения с БД: " + DBConfig.getInstance().toString());

        Locale.setDefault(Locale.US);
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Bank.class);
        configuration.addAnnotatedClass(Point.class);

        configuration.setProperty("hibernate.dialect", DBConfig.getInstance().getDialect());
        configuration.setProperty("hibernate.connection.driver_class", DBConfig.getInstance().getDriver_class());
        configuration.setProperty("hibernate.connection.url", DBConfig.getInstance().getUrl());
        configuration.setProperty("hibernate.connection.username", DBConfig.getInstance().getUsername());
        configuration.setProperty("hibernate.connection.password", DBConfig.getInstance().getPassword());
        configuration.setProperty("hibernate.show_sql", DBConfig.getInstance().getShow_sql());
        configuration.setProperty("hibernate.hbm2ddl.auto", DBConfig.getInstance().getHbm2ddl());
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public <T> Long create(T entity) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            Long id = dao.create(entity);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public <T> T read(Class<T> persistClass, Long id) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            T result = dao.read(persistClass, id);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public <T> List<T> getAll(Class<T> persistClass) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            if (persistClass == City.class){
                session.enableFetchProfile("city-with-country");
            }
            if (persistClass == Point.class){
                session.enableFetchProfile("point-with-bank");
                session.enableFetchProfile("point-with-city");
                session.enableFetchProfile("city-with-country");
            }
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<T> result = dao.getAll(persistClass);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public <T> List<T> findByName(Class<T> persistClass, String name) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            if (persistClass == City.class){
                session.enableFetchProfile("city-with-country");
            }
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<T> result = dao.findByName(persistClass, name);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public List<Point> findPointByFK(List<Country> lCountry, List<City> lCity, List<Bank> lBank) {
        try {
            Session session = sessionFactory.openSession();
            session.enableFetchProfile("point-with-bank");
            session.enableFetchProfile("point-with-city");
            session.enableFetchProfile("city-with-country");
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            List<Point> result = dao.findPointByFK(lCountry, lCity, lBank);
            transaction.commit();
            session.close();
            return result;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }
}
