package com.cherentsov.mapback;

import com.cherentsov.mapback.Model.*;
import com.cherentsov.mapback.Service.DBService;
import org.hibernate.HibernateException;

class ExampleData {
//Наполнение БД тестовыми данными
    static void AddExampleToDB(DBService dbService) {
        Country RF = new Country((long) -1, "Российская Федерация");
        Country KZ = new Country((long) -1, "Казахстан");
        Country Gr = new Country((long) -1, "Грузия");
        Country Bl = new Country((long) -1, "Белорусь");

        City Novosibirsk = new City((long) -1, "Новосибирск", RF);
        City Novorosijsk = new City((long) -1, "Новоросийск", RF);
        City Novocherkask = new City((long) -1, "Новочеркасск", RF);
        City Novokuznetsk = new City((long) -1, "Новокузнецк", RF);
        City Astana = new City((long) -1, "Астана", KZ);
        City Kutaisi = new City((long) -1, "Кутаиси", Gr);
        City Sluck = new City((long) -1, "Слуцк", Bl);

        Bank SberBank = new Bank((long) -1,"Сбербанк");
        Bank VTB = new Bank((long) -1,"Банк ВТБ");
        Bank Gazprombank = new Bank((long) -1,"Газпромбанк");

        Point DusiKovalchuk = new Point((long) -1,"ул. Дуси Ковальчук, 173, 1 этаж",55.057256F, 82.906101F, Novosibirsk, SberBank);
        Point Likova = new Point((long) -1,"ул. Лыкова, 7",54.860937F, 83.084001F, Novosibirsk, SberBank);
        Point Olovozovodskaja = new Point((long) -1,"Оловозаводская ул., 18/1",54.963069F, 82.951439F, Novosibirsk, SberBank);
        Point KrasnijProspekt = new Point((long) -1,"Красный просп., 81, этаж 1",55.051196F, 82.914134F, Novosibirsk, VTB);
        Point Stancionnaja = new Point((long) -1,"Станционная ул., 30А, корп. А",54.997896F, 82.852268F, Novosibirsk, VTB);
        Point Krasnoobsk = new Point((long) -1,"6, рабочий посёлок Краснообск",54.918372F, 82.989203F, Novosibirsk, VTB);
        Point Plahotnogo = new Point((long) -1,"ул. Плахотного, 27/1, этаж 1",54.985871F, 82.875862F, Novosibirsk, Gazprombank);
        Point Sovetov = new Point((long) -1,"ул. Советов, 14",44.724369F, 37.768002F, Novorosijsk, SberBank);
        Point Moskovskaja = new Point((long) -1,"Московская ул., 67А",47.420764F, 40.093001F, Novocherkask, SberBank);
        Point Metalurgov = new Point((long) -1,"просп. Металлургов, 41",53.763640F, 87.117944F, Novokuznetsk, SberBank);
        Point Pionerskij = new Point((long) -1,"Пионерский просп., 57",53.762888F, 87.140245F, Novokuznetsk, Gazprombank);
        Point Pavlovskogo = new Point((long) -1,"ул. Павловского, 27",53.771747F, 87.134375F, Novokuznetsk, VTB);
        Point Amangeldj = new Point((long) -1,"ул. Амангельды Иманова, 11",51.164039F, 71.435845F, Astana, VTB);
        Point Turan = new Point((long) -1,"просп. Туран, 13",51.147999F, 71.411383F, Astana, SberBank);
        Point Dostik = new Point((long) -1,"ул. Достык, 12",51.126118F, 71.425798F, Astana, VTB);
        Point Oktiabria = new Point((long) -1,"ул. 70 лет Октября, 1",51.119772F, 71.422547F, Astana, Gazprombank);
        Point Cisper = new Point((long) -1,"ул. Цисперканцелеби, 15",42.270502F, 42.702631F, Kutaisi, VTB);
        Point Avtom = new Point((long) -1,"ул. Автомшенебели, 3",42.260775F, 42.646028F, Kutaisi, VTB);
        Point Lenina = new Point((long) -1,"ул. Ленина, 136",53.025737F, 27.550855F, Sluck, SberBank);
        Point Lenina195 = new Point((long) -1,"ул. Ленина, 195А",53.028795F, 27.554158F, Sluck, VTB);

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
