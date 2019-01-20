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

import java.util.Locale;

public class DBService implements IDBService {
    private final SessionFactory sessionFactory;

    /*private DBConfig dBConfig;

    public void setDbConfig(DBConfig dbConfig) {
        this.dBConfig = dbConfig;
    }*/

    public DBService() {
        Configuration configuration = getOracleConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    @Override
    public void Close() {
        sessionFactory.close();
    }

    private Configuration getOracleConfiguration() {
        System.out.println(DBConfig.getInstance().toString());
        //DBConfig dBConfig = new DBConfig();
        //System.out.println(" Dialect: "+ dBConfig.getInstance().getDialect() + "Dialect: "+ dBConfig.getInstance().getDriver_class()
        //        + "Dialect: "+ dBConfig.getInstance().getHbm2ddl() + "Dialect: "+ dBConfig.getInstance().getPassword()+"Dialect: "+
        //        dBConfig.getInstance().getShow_sql()+"Dialect: "+ dBConfig.getInstance().getUrl()+"Dialect: "+ dBConfig.getInstance().getUsername());

        /*
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

*/

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
        //configuration.setProperty("NLS_LANG", "AMERICAN");
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
/*
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
*/
    @Override
    public <T> Long create(T entity) throws HibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            IGenericDAO<T> dao = new GenericDAO<>(session);
            Long id = dao.create(entity);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

}
