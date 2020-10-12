package org.example.mike.CoronaApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HorecaCoronaTests {

    @Test
    void maakEenReserveringViaConsole(){
        ReserveringenManager reserveringenManager = geefReserveringenManagerTerugWaarbij5TestreserveringenZijnOpgenomen();
        reserveringenManager.neemReserveringAanViaDeConsoleInvoer();

        reserveringenManager.printLijstVanReserveringen();
    }

    ReserveringenManager geefReserveringenManagerTerugWaarbij5TestreserveringenZijnOpgenomen(){
        HorecaGelegenheid horecaGelegenheid = maakEenHorecaGelegenheidAan();
        ReserveringenManager reserveringenManager = new ReserveringenManager(horecaGelegenheid);
        Persoon testPersoon = new Persoon("Naam");
        Persoon testPersoon2 = new Persoon("Naam2");
        String testPersoon3 = "Naam3";
        Persoon testPersoon4 = new Persoon("Naam4");
        Persoon testPersoon5 = new Persoon("Naam5");

        LocalTime tijd1 =  LocalTime.of(13, 45);
        LocalTime tijd2 =  LocalTime.of(17, 15);
        LocalTime tijd3 =  LocalTime.of(14, 45);
        LocalTime tijd4 =  LocalTime.of(15, 25);
        LocalTime tijd5 =  LocalTime.of(9, 30);
        LocalTime tijd6 =  LocalTime.of(10, 45);

        LocalDate testDatum = LocalDate.now();
        LocalDate testDatum2 = LocalDate.now().plusDays(2);

        reserveringenManager.neemReserveringAan(testPersoon, testPersoon.getNaam(), tijd1, tijd2, testDatum);
        reserveringenManager.neemReserveringAan(testPersoon2, testPersoon2.getNaam(),tijd3, tijd4, testDatum);
        reserveringenManager.neemReserveringAan(null, testPersoon3,tijd5, tijd6, testDatum);
        reserveringenManager.neemReserveringAan(testPersoon4, testPersoon4.getNaam(),tijd1, tijd2, testDatum2);
        reserveringenManager.neemReserveringAan(testPersoon4, testPersoon5.getNaam(),tijd3, tijd4, testDatum2);

        return reserveringenManager;
    }

    @Test
    void aannemenVan4VanDe5ReserveringenDoorDeManagerEnPrintLijst(){
        HorecaGelegenheid horecaGelegenheid = maakEenHorecaGelegenheidAan();
        ReserveringenManager reserveringenManager = new ReserveringenManager(horecaGelegenheid);
        Persoon testPersoon = new Persoon("Naam");
        Persoon testPersoon2 = new Persoon("Naam2");
        String testPersoon3 = "Naam3";
        Persoon testPersoon4 = new Persoon("Naam4");
        Persoon testPersoon5 = new Persoon("Naam5");

        LocalTime tijd1 =  LocalTime.of(13, 45);
        LocalTime tijd2 =  LocalTime.of(17, 15);
        LocalTime tijd3 =  LocalTime.of(14, 45);
        LocalTime tijd4 =  LocalTime.of(15, 25);
        LocalTime tijd5 =  LocalTime.of(9, 30);
        LocalTime tijd6 =  LocalTime.of(10, 45);

        LocalDate testDatum = LocalDate.now();
        LocalDate testDatum2 = LocalDate.now().plusDays(2);

        reserveringenManager.neemReserveringAan(testPersoon, testPersoon.getNaam(), tijd1, tijd2, testDatum);
        reserveringenManager.neemReserveringAan(testPersoon2, testPersoon2.getNaam(),tijd3, tijd4, testDatum);
        reserveringenManager.neemReserveringAan(null, testPersoon3,tijd5, tijd6, testDatum);
        reserveringenManager.neemReserveringAan(testPersoon4, testPersoon4.getNaam(),tijd3, tijd4, testDatum);
        reserveringenManager.neemReserveringAan(testPersoon4, testPersoon5.getNaam(),tijd3, tijd4, testDatum2);
        reserveringenManager.printLijstVanReserveringen();
    }

    HorecaGelegenheid maakEenHorecaGelegenheidAan() {
        Adres testAdres = new Adres("Zee", 44, "Dam");
        Tafel tafel = new Tafel(0);
        Tafel tafel1 = new Tafel(1);
        ArrayList<Tafel> lijstVanTafels = new ArrayList<Tafel>();
        lijstVanTafels.add(tafel);
        lijstVanTafels.add(tafel1);
        HorecaGelegenheid horecaTest = new HorecaGelegenheid("TestKroeg", testAdres, 2, 40, lijstVanTafels);
        return horecaTest;
    }

    //base tests
    @Test
    void testOfBasisConstructorenKlassenWerken(){
        aanmakenHorecaGelegenheid();
        aanmakenTafel();
        aanmakenPersoon();
        aanmakenReservering();
    }

    void aanmakenHorecaGelegenheid(){
        Adres testAdres = new Adres("Zee", 44, "Dam");
        Tafel tafel = new Tafel(0);
        Tafel tafel1 = new Tafel(1);
        ArrayList<Tafel> lijstVanTafels = new ArrayList<Tafel>();
        lijstVanTafels.add(tafel);
        lijstVanTafels.add(tafel1);
        HorecaGelegenheid horecaTest = new HorecaGelegenheid("TestKroeg", testAdres, 2, 40, lijstVanTafels);
        System.out.println(horecaTest.getNaam() + "; " + horecaTest.getAdres().toString());
    }
    void aanmakenTafel(){
        Tafel tafel = new Tafel(0);
        System.out.println(tafel.getTafelNummer() + "; " + tafel.getAantalPersonen());
    }
    void aanmakenPersoon(){
        Persoon testPersoon = new Persoon("Naam");
        System.out.println(testPersoon.getNaam() + " ; " + testPersoon.getTelefoonNummer());
    }
    void aanmakenReservering(){
        //LocalDate datumNu = LocalDate.now();
        LocalTime tijdNu = LocalTime.now();
        //LocalTime eindTijd = tijdNu.plusHours(2L);
        String naam = "d";
        Tafel tafel = new Tafel(0);
        Reservering reservering = new Reservering(tijdNu, naam, tafel);
        System.out.println(reservering.getDatum() + " ; " + reservering.getTijdVan() + " ; " + reservering.getTijdTot() + " ; " + reservering.getNaamReservering() + "; " + reservering.getTafel().getTafelNummer());
    }
    void aanmakenReserveringenManager(){
        Adres testAdres = new Adres("Zee", 44, "Dam");
        Tafel tafel = new Tafel(0);
        Tafel tafel1 = new Tafel(1);
        ArrayList<Tafel> lijstVanTafels = new ArrayList<Tafel>();
        lijstVanTafels.add(tafel);
        lijstVanTafels.add(tafel1);
        HorecaGelegenheid horecaTest = new HorecaGelegenheid("TestKroeg", testAdres, 2, 40, lijstVanTafels);
        ReserveringenManager reserveringenManager = new ReserveringenManager(horecaTest);
        System.out.println("werkt in:" + reserveringenManager.getHorecaGelegenheid().getNaam());
    }
}
