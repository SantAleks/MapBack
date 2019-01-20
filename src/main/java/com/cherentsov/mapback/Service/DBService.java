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
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService<T> implements IDBService<T> {
    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getOracleConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public void Close() {
        sessionFactory.close();
    }

    private Configuration getOracleConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Bank.class);
        configuration.addAnnotatedClass(Point.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        configuration.setProperty("hibernate.connection.driver_class", "oracle.jdbc.OracleDriver");
        configuration.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:XE");
        configuration.setProperty("hibernate.connection.username", "system");
        configuration.setProperty("hibernate.connection.password", "ogabbygabby");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public long addCountry(Country country) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO dao = new GenericDAO(session);
            Long id = dao.create(country);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    public Long create(final T entity) throws HibernateException {
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

}
