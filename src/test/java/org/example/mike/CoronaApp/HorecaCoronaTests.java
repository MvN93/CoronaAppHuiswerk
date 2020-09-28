package org.example.mike.CoronaApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class HorecaCoronaTests {


    @Test
    void aanmakenVanEenReserveringDoorDeManager(){
        HorecaGelegenheid horecaGelegenheid = maakEenHorecaGelegenheidAan();
        ReserveringenManager reserveringenManager = new ReserveringenManager(horecaGelegenheid);
        Persoon testPersoon = new Persoon("Naam");

        reserveringenManager.neemReserveringAan(testPersoon,LocalTime.now(), LocalTime.now());
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
