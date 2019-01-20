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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;

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

        FileInputStream fis;
        Properties property = new Properties();

        String host = "";
        String login = "";
        String password = "";
        try {
            fis = new FileInputStream("G:\\java\\MapBack\\src\\main\\resources\\application.properties");
            property.load(fis);
            host = property.getProperty("db.host");
            login = property.getProperty("hibernate.connection.username");
            password = property.getProperty("hibernate.connection.password");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        System.out.println("HOST: " + host
                + ", LOGIN: " + login
                + ", PASSWORD: " + password);



        Locale.setDefault(Locale.US);
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
        //configuration.setProperty("NLS_LANG", "AMERICAN");
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
