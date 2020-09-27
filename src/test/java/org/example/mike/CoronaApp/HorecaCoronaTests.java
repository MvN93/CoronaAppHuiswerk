package org.example.mike.CoronaApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class HorecaCoronaTests {


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
        HorecaGelegenheid horecaTest = new HorecaGelegenheid("TestKroeg", testAdres);
        System.out.println(horecaTest.getNaam() + "; " + horecaTest.getAdres().toStringRepresentation());
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
}
