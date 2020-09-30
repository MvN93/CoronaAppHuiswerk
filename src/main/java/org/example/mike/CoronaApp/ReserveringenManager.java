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

            System.out.println("zoeken op tafelnummer: " + tafelnummer);
            System.out.println("minder dan tafels: " + horecaGelegenheid.getAantalTafels());

            //bepaal of voor deze tafelnummer al een reservering staat
            boolean tafelIsVrij = true;
            System.out.println("begin while loop tafelvrij: "+tafelIsVrij);
            System.out.println("begin while loop gevonden: "+vrijeTafelGevonden);

            if(lijstVanReserveringen.size() > 0) {
                for (Reservering reservering : lijstVanReserveringen) {
                    System.out.println("begin for loop voor reservering: " + reservering.getTijdTot() + "op tafelnummer: " + reservering.getTafel().getTafelNummer());
                    //zouden ook een or negation zelfdeTijd conditie binnen de if moeten toevoegen om te kijken of gelijke tijd
                    //??could maybe do an equals here if we define it on tafels, define as equal if same number?
                    //??then we wouldnt need tafelnummer at all here just check tables in the array? is that better though?
                    if (!(reservering.getTafel().getTafelNummer() == tafelnummer)) {
                        //beeindig voor deze reserveringdoorloping forloop want reservering niet relevant voor deze tafel
                        System.out.println("einde for loop via reservering niet relevant op dit tafelnummer");
                    } else {
                        boolean beginTijdLigtTijdensBestaandeReservering = ((reservering.getTijdVan().getHour() - beginTijdReservering.getHour()) <= 0) && ((beginTijdReservering.getHour() - reservering.getTijdTot().getHour()) <= 0);
                        if (beginTijdLigtTijdensBestaandeReservering == true) {
                            tafelIsVrij = false;
                            System.out.println("beginTijdLigtFout: " + beginTijdLigtTijdensBestaandeReservering);
                        }

                        boolean eindTijdLigtTijdensBestaandeReservering = ((reservering.getTijdVan().getHour() - eindTijdReservering.getHour()) <= 0) && ((eindTijdReservering.getHour() - reservering.getTijdTot().getHour()) <= 0);
                        if (eindTijdLigtTijdensBestaandeReservering == true) {
                            tafelIsVrij = false;
                            System.out.println("eindTijdLigtFout: " +eindTijdLigtTijdensBestaandeReservering);
                        }
                        System.out.println("via de else, tafelisvrij:" + tafelIsVrij + "voor reservering: " + reservering.getTijdVan() + "op tafelnummer: " + reservering.getTafel().getTafelNummer());
                    }
                }
            }
            //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

            System.out.println("einde doorloping whileloop tafelvrij: "+tafelIsVrij);
            System.out.println("einde doorloping loop gevonden: "+vrijeTafelGevonden);
            System.out.println("tafelnummer: "+tafelnummer);
            //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
            if(tafelIsVrij == true) { //&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
                System.out.println("succes, maak reservering");
                Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, persoon.getNaam(), lijstVanTafels.get(tafelnummer));
                lijstVanReserveringen.add(nieuweReservering);

                //om de while loop te breken
                System.out.println("breek while loop");
                vrijeTafelGevonden = true;
            }
            else{
                System.out.println("geen succes, volgende tafel kijken");
                tafelnummer = tafelnummer +1;
            }
            System.out.println("vrijetafel gevonden: " + vrijeTafelGevonden);
            System.out.println("------------------------------");
        }
    }


    //print lijst van reserveringen
    void printLijstVanReserveringen(){
        for(Reservering reservering : lijstVanReserveringen){
            System.out.println("Gereserveerd door " + reservering.toString());
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
