package com.cherentsov.mapback;

import com.cherentsov.mapback.Model.*;
import com.cherentsov.mapback.Service.DBService;
import org.hibernate.HibernateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MapbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapbackApplication.class, args);
        DBService dbService = new DBService();
        System.out.println("fdgfg");
        ExampleData.AddExampleToBD(dbService);
    }
}

