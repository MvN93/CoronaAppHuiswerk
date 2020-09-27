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

    void neemReserveringAan(Persoon persoon, LocalTime beginTijdReservering, LocalTime eindTijdReservering)
    {
        //!!Note: dit houdt nu nog geen rekening met en eindtijd
        //!!Note: dit houdt nu nog geen rekening met of voldoende plek aan tafel

        boolean vrijeTafelGevonden = false;

        //haalt een lijst van alle tafels (kan dit efficienter)
        ArrayList<Tafel> lijstVanTafels = horecaGelegenheid.getLijstVanTafels();
        //tafelnummer als index gebruikt
        int tafelnummer = 0;

        //ga alle tafels langs tot plek gevonden is
        while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {

            //bepaal of voor deze tafelnummer al een reservering staat
            boolean tafelIsVrij = false;
            for(Reservering reservering : lijstVanReserveringen){
                if(!(reservering.getTafel().getTafelNummer() == tafelnummer)){
                    tafelIsVrij = true;
                }
                //zouden ook een or negation zelfdeTijd conditie binnen de if moeten toevoegen om te kijken of gelijke tijd
            }

            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij = true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, persoon.getNaam(), lijstVanTafels.get(tafelnummer));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }
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
