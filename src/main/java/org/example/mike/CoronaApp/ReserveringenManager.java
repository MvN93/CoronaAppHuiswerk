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

    //checked de datum nog niet
    void neemReserveringAan(Persoon persoon, String reserveringOnderNaam, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering){
        if(!(persoon == null)){
            neemReserveringAanPersoonsObjectBekend(persoon, beginTijdReservering, eindTijdReservering, datumReservering);
        }
        else {
            neemReserveringAanAlleenNaamBekend(reserveringOnderNaam, beginTijdReservering, eindTijdReservering, datumReservering);
        }
    }

    void neemReserveringAanPersoonsObjectBekend(Persoon persoon, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering) {
        //!!Note: dit houdt nu nog geen rekening met of voldoende plek aan tafel

        boolean vrijeTafelGevonden = false;

        //tafelnummer als index gebruikt
        int tafelnummer = 0;

        //ga alle tafels langs tot plek gevonden is
        while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
            //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
            boolean tafelIsVrij = true;

            if(lijstVanReserveringen.size() > 0) {
                tafelIsVrij = gaNaOfTafelVrijIs(tafelnummer, beginTijdReservering, eindTijdReservering, datumReservering, tafelIsVrij);
            }
            //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij == true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                //new variabele voor gevonden tafel
                Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, eindTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }
    }

    void neemReserveringAanAlleenNaamBekend(String reserveringOnderNaam, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering) {
        //!!Note: dit houdt nu nog geen rekening met of voldoende plek aan tafel

        boolean vrijeTafelGevonden = false;

        //tafelnummer als index gebruikt
        int tafelnummer = 0;

        //ga alle tafels langs tot plek gevonden is
        while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
            //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
            boolean tafelIsVrij = true;

            if(lijstVanReserveringen.size() > 0) {
                tafelIsVrij = gaNaOfTafelVrijIs(tafelnummer, beginTijdReservering, eindTijdReservering, datumReservering, tafelIsVrij);
            }
            //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij == true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, eindTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }
    }

    boolean gaNaOfTafelVrijIs(int tafelnummer, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering, boolean tafelIsVrij){
        for (Reservering reservering : lijstVanReserveringen) {

            boolean reserveringIsVanAnderTafelNummer = !(reservering.getTafel().getTafelNummer() == tafelnummer);
            boolean reserveringIsVanAnderDatum = !(reservering.getDatum() == datumReservering);

            if(reserveringIsVanAnderTafelNummer == true) {
                //beeindig voor deze reserveringdoorloping de forloop want reservering niet relevant voor deze tafel want andere tafel
            }
            else if(reserveringIsVanAnderDatum == true) {
                //beeindig voor deze reserveringdoorloping de forloop want reservering niet relevant voor deze tafel want andere datum
            }
            else {
                //misschien dit blok in hulpmethode??
                boolean beginTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(beginTijdReservering, reservering.getTijdVan(), reservering.getTijdTot());

                if (beginTijdsuurLigtInGereserveerdTijdsvlak == true) {
                    tafelIsVrij = false;
                }

                boolean eindTijdsuurLigtInGereserveerdTijdsvlak = ingevoerdeTijdsuurLigtInVolTijdsvlak(eindTijdReservering, reservering.getTijdVan(), reservering.getTijdTot());

                if (eindTijdsuurLigtInGereserveerdTijdsvlak == true) {
                    tafelIsVrij = false;
                }
            }
        }

        return tafelIsVrij;
    }

    boolean ingevoerdeTijdsuurLigtInVolTijdsvlak(LocalTime ingevoerdeTijd, LocalTime beginTijdTijdsvlak, LocalTime eindTijdTijdsvlak){
        //!!Note houdt niet rekening met datum
        //We minus and add 15 minutes to give space to clean the tables (and to make sure that if the same time then the isbefore method still works)
        boolean TijdsuurLigtInTijdensvlak = (ingevoerdeTijd.isAfter(beginTijdTijdsvlak.minusMinutes(15))) && (ingevoerdeTijd.isBefore(eindTijdTijdsvlak.plusMinutes(15)));
        return TijdsuurLigtInTijdensvlak;
    }

    Tafel vindtTafelHorendeBijTafelnummer(int tafelnummer, HorecaGelegenheid horecaGelegenheid){
        //??haalt een lijst van alle tafels (kan dit efficienter??, want wordt alleen gebruikt bij maken reservering)??
        //maybe met hashmaps?!!? dan alsnog een hele collection
        //dan moet tafelnummer wel ook index zijn
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
