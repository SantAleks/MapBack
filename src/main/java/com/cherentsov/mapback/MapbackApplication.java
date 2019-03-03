package com.cherentsov.mapback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MapbackApplication {
    private static final Log logger = LogFactory.getLog(MapbackApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MapbackApplication.class, args);
        logger.info("Start Aplication. DB is Empty");
        ExampleData.AddExampleToDB();
        logger.info("DB is Full");
    }
}

