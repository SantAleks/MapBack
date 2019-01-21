package com.cherentsov.mapback.Controller;

import com.cherentsov.mapback.Model.Bank;
import com.cherentsov.mapback.Model.City;
import com.cherentsov.mapback.Model.Country;
import com.cherentsov.mapback.Model.Point;
import com.cherentsov.mapback.Service.DBService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {

    //private static final String template = "Hello, %s!";
    //private final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public String map(@RequestParam(value="co", required=false, defaultValue="") String countryPattern,
                      @RequestParam(value="ci", required=false, defaultValue="") String cityPattern,
                      @RequestParam(value="ba", required=false, defaultValue="") String bankPattern,
                      @RequestParam(value="fc", required=false, defaultValue="") String focus) {
        DBService dBService = DBService.getInstance();

        List<Country> lCountry = new ArrayList<>();
        if (countryPattern.trim().length() > 0){
            lCountry = dBService.findByName(Country.class, countryPattern.trim());
        }
//        System.out.println("lCountry " + lCountry);

        List<City> lCity = new ArrayList<>();
        if (cityPattern.trim().length() > 0){ // && lCountry.size() != 1){
            lCity = dBService.findByName(City.class, cityPattern.trim());
        }
        System.out.println("lCity " + lCity);

        List<Bank> lBank = new ArrayList<>();
        if (bankPattern.trim().length() > 0){
            lBank = dBService.findByName(Bank.class, bankPattern.trim());
        }
        System.out.println("lBank " + lBank);

      /*  List<Point> lPoint = new ArrayList<>();
        if (lCountry.size() == 1 || lCity.size() == 1 || lBank.size() == 1){
            lPoint = dBService.findByName(Point.class, bankPattern.strip());
        }
        System.out.println("lBank " + lBank);
        */

        //City result = dBService.read(City.class, (long) 1);
        //return new String("Город: "+ result.getName());

        //List<City> result = dBService.getAll(City.class);

        List<City> result = dBService.findByName(City.class, "С");

        return "Город: "+ result;
    }
    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "Сервис справочника находится по адресу /map.";
    }

}
