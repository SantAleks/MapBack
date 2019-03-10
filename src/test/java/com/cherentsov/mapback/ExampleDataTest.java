package com.cherentsov.mapback;

import com.cherentsov.mapback.Model.Bank;
import com.cherentsov.mapback.Model.City;
import com.cherentsov.mapback.Model.Country;
import com.cherentsov.mapback.Model.Point;
import com.cherentsov.mapback.Service.DBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ExampleDataTest {

    @Test
    public void addExampleToDB() {
        //Проверка заполнения таблиц тестовыми данными
        ExampleData.AddExampleToDB();
        DBService dBService = DBService.getInstance();

        Country country = dBService.read(Country.class, 1L);
        assertEquals(country.getName(), "Российская Федерация");

        List<Country> lCountry = dBService.getAll(Country.class);
        assertEquals(lCountry.size(),4);

        lCountry = dBService.findByName(Country.class, "Российская Федерация");
        assertEquals((long)lCountry.get(0).getId(), 1L);

        List<Bank> lBank = dBService.findByName(Bank.class, "Банк ВТБ");
        List<City> lCity = dBService.findByName(City.class, "Новосибирск");

        assertEquals((long)lCountry.get(0).getId(), 1L);
        List<Point> lPoint = dBService.findPointByFK(lCountry, lCity, lBank);
        assertEquals((long)lPoint.size(), 3);

    }
}