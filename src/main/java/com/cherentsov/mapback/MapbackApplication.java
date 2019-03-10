package com.cherentsov.mapback;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class MapbackApplication {
    private static final Log logger = LogFactory.getLog(MapbackApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(MapbackApplication.class, args);
        logger.info("Start Aplication. DB is Empty");
        ExampleData.AddExampleToDB();
        logger.info("DB is Full");
    }
}

