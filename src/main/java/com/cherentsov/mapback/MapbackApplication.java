package com.cherentsov.mapback;

import com.cherentsov.mapback.Service.DBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
public class MapbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MapbackApplication.class, args);
        System.out.println("fdgfg");
        DBService dbService = new DBService();
        ExampleData.AddExampleToBD(dbService);
    }
}

