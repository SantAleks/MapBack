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
        try {
            long countryId = dbService.create(new Country(Long.valueOf(-1), "Российская Федирация"));
            System.out.println("Added country id: " + countryId);
       } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}

