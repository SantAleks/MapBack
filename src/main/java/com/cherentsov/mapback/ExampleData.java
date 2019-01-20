package com.cherentsov.mapback;

import com.cherentsov.mapback.Model.*;
import com.cherentsov.mapback.Service.DBService;
import org.hibernate.HibernateException;

public class ExampleData {
//Наполнение БД тестовыми данными
    public static void AddExampleToBD(DBService dbService) {
        Country RF = new Country(Long.valueOf(-1), "Российская Федерация");
        Country KZ = new Country(Long.valueOf(-1), "Казахстан");
        Country Gr = new Country(Long.valueOf(-1), "Грузия");
        Country Bl = new Country(Long.valueOf(-1), "Белорусь");

        City Novosibirsk = new City(Long.valueOf(-1), "Новосибирск", RF);
        City Novorosijsk = new City(Long.valueOf(-1), "Новоросийск", RF);
        City Novocherkask = new City(Long.valueOf(-1), "Новочеркасск", RF);
        City Novokuznetsk = new City(Long.valueOf(-1), "Новокузнецк", RF);
        City Astana = new City(Long.valueOf(-1), "Астана", KZ);
        City Kutaisi = new City(Long.valueOf(-1), "Кутаиси", Gr);
        City Sluck = new City(Long.valueOf(-1), "Слуцк", Bl);

        Bank SberBank = new Bank(Long.valueOf(-1),"Сбербанк");
        Bank VTB = new Bank(Long.valueOf(-1),"Банк ВТБ");
        Bank Gazprombank = new Bank(Long.valueOf(-1),"Газпромбанк");

        Point DusiKovalchuk = new Point(Long.valueOf(-1),"ул. Дуси Ковальчук, 173, 1 этаж",
                Float.valueOf(55.057256F),Float.valueOf(82.906101F), Novosibirsk, SberBank);
        Point Likova = new Point(Long.valueOf(-1),"ул. Лыкова, 7",
                Float.valueOf(54.860937F),Float.valueOf(83.084001F), Novosibirsk, SberBank);
        Point Olovozovodskaja = new Point(Long.valueOf(-1),"Оловозаводская ул., 18/1",
                Float.valueOf(54.963069F),Float.valueOf(82.951439F), Novosibirsk, SberBank);
        Point KrasnijProspekt = new Point(Long.valueOf(-1),"Красный просп., 81, этаж 1",
                Float.valueOf(55.051196F),Float.valueOf(82.914134F), Novosibirsk, VTB);
        Point Stancionnaja = new Point(Long.valueOf(-1),"Станционная ул., 30А, корп. А",
                Float.valueOf(54.997896F),Float.valueOf(82.852268F), Novosibirsk, VTB);
        Point Krasnoobsk = new Point(Long.valueOf(-1),"6, рабочий посёлок Краснообск",
                Float.valueOf(54.918372F),Float.valueOf(82.989203F), Novosibirsk, VTB);
        Point Plahotnogo = new Point(Long.valueOf(-1),"ул. Плахотного, 27/1, этаж 1",
                Float.valueOf(54.985871F),Float.valueOf(82.875862F), Novosibirsk, Gazprombank);
        Point Sovetov = new Point(Long.valueOf(-1),"ул. Советов, 14",
                Float.valueOf(44.724369F),Float.valueOf(37.768002F), Novorosijsk, SberBank);
        Point Moskovskaja = new Point(Long.valueOf(-1),"Московская ул., 67А",
                Float.valueOf(47.420764F),Float.valueOf(40.093001F), Novocherkask, SberBank);
        Point Metalurgov = new Point(Long.valueOf(-1),"просп. Металлургов, 41",
                Float.valueOf(53.763640F),Float.valueOf(87.117944F), Novokuznetsk, SberBank);
        Point Pionerskij = new Point(Long.valueOf(-1),"Пионерский просп., 57",
                Float.valueOf(53.762888F),Float.valueOf(87.140245F), Novokuznetsk, Gazprombank);
        Point Pavlovskogo = new Point(Long.valueOf(-1),"ул. Павловского, 27",
                Float.valueOf(53.771747F),Float.valueOf(87.134375F), Novokuznetsk, VTB);
        Point Amangeldj = new Point(Long.valueOf(-1),"ул. Амангельды Иманова, 11",
                Float.valueOf(51.164039F),Float.valueOf(71.435845F), Astana, VTB);
        Point Turan = new Point(Long.valueOf(-1),"просп. Туран, 13",
                Float.valueOf(51.147999F),Float.valueOf(71.411383F), Astana, SberBank);
        Point Dostik = new Point(Long.valueOf(-1),"ул. Достык, 12",
                Float.valueOf(51.126118F),Float.valueOf(71.425798F), Astana, VTB);
        Point Oktiabria = new Point(Long.valueOf(-1),"ул. 70 лет Октября, 1",
                Float.valueOf(51.119772F),Float.valueOf(71.422547F), Astana, Gazprombank);
        Point Cisper = new Point(Long.valueOf(-1),"ул. Цисперканцелеби, 15",
                Float.valueOf(42.270502F),Float.valueOf(42.702631F), Kutaisi, VTB);
        Point Avtom = new Point(Long.valueOf(-1),"ул. Автомшенебели, 3",
                Float.valueOf(42.260775F),Float.valueOf(42.646028F), Kutaisi, VTB);
        Point Lenina = new Point(Long.valueOf(-1),"ул. Ленина, 136",
                Float.valueOf(53.025737F),Float.valueOf(27.550855F), Sluck, SberBank);
        Point Lenina195 = new Point(Long.valueOf(-1),"ул. Ленина, 195А",
                Float.valueOf(53.028795F),Float.valueOf(27.554158F), Sluck, VTB);

        try {
            //Страны
            RF.setId(dbService.create(RF));
            KZ.setId(dbService.create(KZ));
            Gr.setId(dbService.create(Gr));
            Bl.setId(dbService.create(Bl));
            //Города
            Novosibirsk.setId(dbService.create(Novosibirsk));
            Novorosijsk.setId(dbService.create(Novorosijsk));
            Novocherkask.setId(dbService.create(Novocherkask));
            Novokuznetsk.setId(dbService.create(Novokuznetsk));
            Astana.setId(dbService.create(Astana));
            Kutaisi.setId(dbService.create(Kutaisi));
            Sluck.setId(dbService.create(Sluck));
            //Банки
            SberBank.setId(dbService.create(SberBank));
            VTB.setId(dbService.create(VTB));
            Gazprombank.setId(dbService.create(Gazprombank));
            //Отделения
            dbService.create(DusiKovalchuk);
            dbService.create(Likova);
            dbService.create(Olovozovodskaja);
            dbService.create(KrasnijProspekt);
            dbService.create(Stancionnaja);
            dbService.create(Krasnoobsk);
            dbService.create(Plahotnogo);
            dbService.create(Sovetov);
            dbService.create(Moskovskaja);
            dbService.create(Metalurgov);
            dbService.create(Pionerskij);
            dbService.create(Pavlovskogo);
            dbService.create(Amangeldj);
            dbService.create(Turan);
            dbService.create(Dostik);
            dbService.create(Oktiabria);
            dbService.create(Cisper);
            dbService.create(Avtom);
            dbService.create(Lenina);
            dbService.create(Lenina195);
       } catch (
                HibernateException e) {
            e.printStackTrace();
        }
    }
}
