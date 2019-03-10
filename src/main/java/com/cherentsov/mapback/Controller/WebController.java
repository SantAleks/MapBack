package com.cherentsov.mapback.Controller;

import com.cherentsov.mapback.Model.*;
import com.cherentsov.mapback.Service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class WebController {

    @Bean
    public DBService dBServiceBean() {
        return DBService.getInstance();
    }

    @Autowired
    DBService dBService;

    @RequestMapping(value = "/map", method = RequestMethod.GET, produces = { "application/json" })
    public Set<Object> map(@RequestParam(value="co", required=false, defaultValue="") String countryPattern,
                      @RequestParam(value="ci", required=false, defaultValue="") String cityPattern,
                      @RequestParam(value="ba", required=false, defaultValue="") String bankPattern,
                      @RequestParam(value="fc", required=false, defaultValue="") String focus) {

        List<Country> lCountry = new ArrayList<>();
        if (countryPattern.trim().length() > 0){
            lCountry = dBService.findByName(Country.class, countryPattern.trim());
        }

        List<City> lCity = new ArrayList<>();
        if (cityPattern.trim().length() > 0){ // && lCountry.size() != 1){
            lCity = dBService.findByName(City.class, cityPattern.trim());
        }

        List<Bank> lBank = new ArrayList<>();
        if (bankPattern.trim().length() > 0){
            lBank = dBService.findByName(Bank.class, bankPattern.trim());
        }

        List<Point> lPoint = new ArrayList<>();
        if (lCountry.size() == 1 || lCity.size() == 1 || lBank.size() == 1){
            lPoint = dBService.findPointByFK(lCountry, lCity, lBank);
        }

        String[] mCountry = new String[lCountry.size()];
        for (int i = 0; i < lCountry.size(); i++) {
            mCountry[i] = lCountry.get(i).getName();
        }

        String[] mCity = new String[lCity.size()];
        for (int i = 0; i < lCity.size(); i++) {
            mCity[i] = lCity.get(i).getName();
        }

        String[] mBank = new String[lBank.size()];
        for (int i = 0; i < lBank.size(); i++) {
            mBank[i] = lBank.get(i).getName();
        }

        String[][] mPoint = new String[lPoint.size()][6];
        for (int i = 0; i < lPoint.size(); i++) {
            mPoint[i][0] = lPoint.get(i).getAddress();
            mPoint[i][1] = lPoint.get(i).getCity().getCountry().getName();
            mPoint[i][2] = lPoint.get(i).getCity().getName();
            mPoint[i][3] = lPoint.get(i).getBank().getName();
            mPoint[i][4] = lPoint.get(i).getfX().toString();
            mPoint[i][5] = lPoint.get(i).getfY().toString();
        }

        Set<Object> result = new LinkedHashSet<Object>();
        result.add(mCountry);
        result.add(mCity);
        result.add(mBank);
        result.add(mPoint);
        return result;
    }
    @RequestMapping("/")
    public String index(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return "Сервис справочника находится по адресу /map.";
    }

}
