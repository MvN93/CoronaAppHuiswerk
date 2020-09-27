package org.example.mike.CoronaApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class HorecaCoronaTests {


    //base tests
    @Test
    void aanmakenHorecaGelegenheid(){
        Adres testAdres = new Adres("Zee", 44, "Dam");
        HorecaGelegenheid horecaTest = new HorecaGelegenheid("TestKroeg", testAdres);
        System.out.println(horecaTest.getNaam() + "; " + horecaTest.getAdres().toStringRepresentation());
    }

    @Test
    void aanmakenTafel(){
        int tafelNummer = 0;
        Tafel tafel = new Tafel(0);
        System.out.println(tafel.getTafelNummer() + "; " + tafel.getAantalPersonen());
    }

    @Test
    void aanmakenReservering(){
        //LocalDate datumNu = LocalDate.now();
        LocalTime tijdNu = LocalTime.now();
        //LocalTime eindTijd = tijdNu.plusHours(2L);
        String naam = "d";
        Reservering reservering = new Reservering(tijdNu, naam);
        System.out.println(reservering.getDatum() + " ; " + reservering.getTijdVan() + " ; " + reservering.getTijdTot() + " ; " + reservering.getNaamReservering());
    }

    @Test
    void aanmakenPersoon(){
        Persoon testPersoon = new Persoon("Naam");
        System.out.println(testPersoon.getNaam() + " ; " + testPersoon.getTelefoonNummer());
    }
}
