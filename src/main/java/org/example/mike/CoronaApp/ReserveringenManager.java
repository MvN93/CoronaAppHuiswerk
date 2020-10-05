package org.example.mike.CoronaApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class ReserveringenManager {
    private HorecaGelegenheid horecaGelegenheid;
    private ArrayList<Reservering> lijstVanReserveringen;

    ReserveringenManager(HorecaGelegenheid horecaGelegenheid){
        setHorecaGelegenheid(horecaGelegenheid);
        lijstVanReserveringen = new ArrayList<Reservering>();
    }

    void neemReserveringAan(Persoon persoon, String reserveringOnderNaam, LocalTime beginTijdReservering, LocalTime eindTijdReservering){
        if(!(persoon == null)){
            neemReserveringAanPersoonsObjectBekend(persoon, beginTijdReservering, eindTijdReservering);
        }
        else {
            neemReserveringAanAlleenNaamBekend(reserveringOnderNaam, beginTijdReservering, eindTijdReservering);
        }

    }

    void neemReserveringAanPersoonsObjectBekend(Persoon persoon, LocalTime beginTijdReservering, LocalTime eindTijdReservering) {
        //!!Note: dit houdt nu nog geen rekening met of voldoende plek aan tafel

        boolean vrijeTafelGevonden = false;

        //tafelnummer als index gebruikt
        int tafelnummer = 0;

        //ga alle tafels langs tot plek gevonden is
        while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
            //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
            boolean tafelIsVrij = true;

            if(lijstVanReserveringen.size() > 0) {
                for (Reservering reservering : lijstVanReserveringen) {

                    boolean reserveringIsVanAnderTafelNummer = !(reservering.getTafel().getTafelNummer() == tafelnummer);

                    if(reserveringIsVanAnderTafelNummer == true) {
                        //beeindig voor deze reserveringdoorloping de forloop want reservering niet relevant voor deze tafel
                    }
                    else {
                        boolean beginTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(beginTijdReservering.getHour(), reservering.getTijdVan().getHour(), reservering.getTijdTot().getHour());

                        if (beginTijdsuurLigtInGereserveerdTijdsvlak == true) {
                            tafelIsVrij = false;
                        }

                        boolean eindTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(eindTijdReservering.getHour(), reservering.getTijdVan().getHour(), reservering.getTijdTot().getHour());

                        if (eindTijdsuurLigtInGereserveerdTijdsvlak == true) {
                            tafelIsVrij = false;
                        }
                    }
                }
            }
            //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij == true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }
    }

    void neemReserveringAanAlleenNaamBekend(String reserveringOnderNaam, LocalTime beginTijdReservering, LocalTime eindTijdReservering) {
        //!!Note: dit houdt nu nog geen rekening met of voldoende plek aan tafel

        boolean vrijeTafelGevonden = false;

        //tafelnummer als index gebruikt
        int tafelnummer = 0;

        //ga alle tafels langs tot plek gevonden is
        while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
            //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
            boolean tafelIsVrij = true;

            if(lijstVanReserveringen.size() > 0) {
                for (Reservering reservering : lijstVanReserveringen) {

                    boolean reserveringIsVanAnderTafelNummer = !(reservering.getTafel().getTafelNummer() == tafelnummer);

                    if(reserveringIsVanAnderTafelNummer == true) {
                        //beeindig voor deze reserveringdoorloping de forloop want reservering niet relevant voor deze tafel
                    }
                    else {
                        boolean beginTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(beginTijdReservering.getHour(), reservering.getTijdVan().getHour(), reservering.getTijdTot().getHour());

                        if (beginTijdsuurLigtInGereserveerdTijdsvlak == true) {
                            tafelIsVrij = false;
                        }

                        boolean eindTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(eindTijdReservering.getHour(), reservering.getTijdVan().getHour(), reservering.getTijdTot().getHour());

                        if (eindTijdsuurLigtInGereserveerdTijdsvlak == true) {
                            tafelIsVrij = false;
                        }
                    }
                }
            }
            //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij == true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }
    }


    boolean ingevoerdeTijdsuurLigtInVolTijdsvlak(int ingevoerdeTijd, int beginTijdTijdsvlak, int eindTijdTijdsvlak){
        //!!Note dit gaat nu mis voor na middernacht!!! vanaf 24 uur begint dan namelijk weer 0, 1, 2, etc.
        //Nu oplossen door exception bij reservering, tijd moet <= 23 uur zijn, want coronamaatregel
        //!!Note that now also only checks hours and not minutes, might give problems
        boolean TijdsuurLigtInTijdensvlak = ((beginTijdTijdsvlak - ingevoerdeTijd) <= 0) && ((ingevoerdeTijd - eindTijdTijdsvlak) <= 0);
        return TijdsuurLigtInTijdensvlak;
    }

    Tafel vindtTafelHorendeBijTafelnummer(int tafelnummer, HorecaGelegenheid horecaGelegenheid){
        //??haalt een lijst van alle tafels (kan dit efficienter??, want wordt alleen gebruikt bij maken reservering)??
        //maybe met hashmaps? dan alsnog een hele collection
        ArrayList<Tafel> lijstVanTafels = horecaGelegenheid.getLijstVanTafels();
        Tafel tafel = lijstVanTafels.get(tafelnummer);
        return tafel;
    }

    //print lijst van reserveringen
    void printLijstVanReserveringen(){
        for(Reservering reservering : lijstVanReserveringen){
                System.out.println("Gereserveerd door " + reservering.toStringRepresentation());
        }
    }

    //getters and setters
    public void setHorecaGelegenheid(HorecaGelegenheid horecaGelegenheid) {
        this.horecaGelegenheid = horecaGelegenheid;
    }
    public HorecaGelegenheid getHorecaGelegenheid() {
        return horecaGelegenheid;
    }

}
