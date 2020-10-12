package org.example.mike.CoronaApp;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class HorecaCoronaTests {

    @Test
    void aanmakenVanEenReserveringMetScannerInJuisteFormatEnPrintDezeNaarConsole(){
        HorecaGelegenheid horecaGelegenheid = maakEenHorecaGelegenheidAan();
        ReserveringenManager reserveringenManager = new ReserveringenManager(horecaGelegenheid);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the date for which you would like to book in the following format: YYYY;MM;DD");

        String ingevoerdeDatumAlsString = scanner.nextLine();
        String[] ingevoerdeDatumAlsLosseStringsYearMonthDay = ingevoerdeDatumAlsString.split(";");
        int ingevoerdeJaar = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[0]);
        int ingevoerdeMaand = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[1]);
        int ingevoerdeDag = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[2]);

        LocalDate ingevoerdeDatum = LocalDate.of(ingevoerdeJaar, ingevoerdeMaand, ingevoerdeDag);

        System.out.println("Please enter the starting time for which you would like to book in the following format: HH;MM");

        String ingevoerdeBeginTijdAlsString = scanner.nextLine();
        String[] ingevoerdeBeginTijdAlsLosseStrings = ingevoerdeBeginTijdAlsString.split(";");
        int ingevoerdeBeginUur = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[0]);
        int ingevoerdeBeginMinuut = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[1]);

        LocalTime ingevoerdeBeginTijd = LocalTime.of(ingevoerdeBeginUur, ingevoerdeBeginMinuut);

        System.out.println("Please enter the ending time for which you would like to book in the following format: HH;MM");

        String ingevoerdeEindTijdAlsString = scanner.nextLine();
        String[] ingevoerdeEindTijdAlsLosseStrings = ingevoerdeEindTijdAlsString.split(";");
        int ingevoerdeEindUur = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[0]);
        int ingevoerdeEindMinuut = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[1]);

        LocalTime ingevoerdeEindTijd = LocalTime.of(ingevoerdeEindUur, ingevoerdeEindMinuut);

        System.out.println("Wat is uw naam die ik mag noteren?");
        String ingevoerdeNaam = scanner.nextLine();

        System.out.println("Ik zal nu een reservering voor u maken");
        reserveringenManager.neemReserveringAanAlleenNaamBekend(ingevoerdeNaam,ingevoerdeBeginTijd, ingevoerdeEindTijd, ingevoerdeDatum);

        reserveringenManager.printLijstVanReserveringen();
    }

    @Test
    void probeerTeReserverenViaConsoleOpVolleSlotGeeftVolmeldingTerug(){
        //WERKT NU NIET??? WHY, DE EERSTE FIJF LUKKEN WEL ... SOMEHOW WORDT DE DATUM ALS VERSCHILLEND GELEZEN
        ReserveringenManager reserveringenManager =geefReserveringenManagerTerugWaarbij5TestreserveringenZijnOpgenomen();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the date for which you would like to book in the following format: YYYY;MM;DD");

        String ingevoerdeDatumAlsString = scanner.nextLine();
        String[] ingevoerdeDatumAlsLosseStringsYearMonthDay = ingevoerdeDatumAlsString.split(";");
        int ingevoerdeJaar = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[0]);
        int ingevoerdeMaand = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[1]);
        int ingevoerdeDag = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[2]);

        LocalDate ingevoerdeDatum = LocalDate.of(ingevoerdeJaar, ingevoerdeMaand, ingevoerdeDag);

        System.out.println("Please enter the starting time for which you would like to book in the following format: HH;MM");

        String ingevoerdeBeginTijdAlsString = scanner.nextLine();
        String[] ingevoerdeBeginTijdAlsLosseStrings = ingevoerdeBeginTijdAlsString.split(";");
        int ingevoerdeBeginUur = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[0]);
        int ingevoerdeBeginMinuut = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[1]);

        LocalTime ingevoerdeBeginTijd = LocalTime.of(ingevoerdeBeginUur, ingevoerdeBeginMinuut);

        System.out.println("Please enter the ending time for which you would like to book in the following format: HH;MM");

        String ingevoerdeEindTijdAlsString = scanner.nextLine();
        String[] ingevoerdeEindTijdAlsLosseStrings = ingevoerdeEindTijdAlsString.split(";");
        int ingevoerdeEindUur = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[0]);
        int ingevoerdeEindMinuut = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[1]);

        LocalTime ingevoerdeEindTijd = LocalTime.of(ingevoerdeEindUur, ingevoerdeEindMinuut);

        System.out.println("Wat is uw naam die ik mag noteren?");
        String ingevoerdeNaam = scanner.nextLine();

        System.out.println("Ik zal nu een reservering voor u maken");
        reserveringenManager.neemReserveringAanAlleenNaamBekend(ingevoerdeNaam,ingevoerdeBeginTijd, ingevoerdeEindTijd, ingevoerdeDatum);

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
    void aannemenVanReserveringenDoorDeManager(){
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
