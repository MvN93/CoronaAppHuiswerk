package org.example.mike.CoronaApp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Reservering {
    private LocalDate datum;
    private LocalTime tijdVan;
    private LocalTime tijdTot;
    private String naamReservering; //voor wanneer geen Persoon maar alleen naam meegegeven
                                    // (note kan met persoon.getNaam altijd worden meegegeven wanneer wel persoon gelinked is)
    private Tafel tafel;

    //wanneer geen datum en eindtijd aangegeven ga uit van vandaag en van max 2 uur
    Reservering(LocalTime tijdVan, String naamReservering, Tafel tafel){
        this(LocalDate.now(), tijdVan, tijdVan.plusHours(2), naamReservering, tafel);
    }

    //wanneer geen datum ga uit van vandaag
    Reservering(LocalTime tijdVan, LocalTime tijdTot, String naamReservering, Tafel tafel){
        this(LocalDate.now(), tijdVan, tijdTot, naamReservering, tafel);
    }

    //wanneer geen eindtijd aangegeven ga uit van max tijd 2 uur
    Reservering(LocalDate datum, LocalTime tijdVan, String naamReservering, Tafel tafel){
        this(datum, tijdVan, tijdVan.plusHours(2), naamReservering, tafel);
    }

    Reservering(LocalDate datum, LocalTime tijdVan, LocalTime tijdTot, String naamReservering, Tafel tafel){
        setDatum(datum);
        setTijdVan(tijdVan);
        setTijdTot(tijdTot);
        setNaamReservering(naamReservering);
        setTafel(tafel);
    }



    public String toString(){
        return "Naam: " + getNaamReservering() + ", van: " + getTijdVan() + " tot: " + getTijdTot() + "; aan tafel nummer: " + getTafel().getTafelNummer();
    }

    //getters and setters
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public LocalDate getDatum() {
        return datum;
    }

    public void setTijdVan(LocalTime tijdVan) {
        this.tijdVan = tijdVan;
    }
    public LocalTime getTijdVan() {
        return tijdVan;
    }

    public void setTijdTot(LocalTime tijdTot) {
        this.tijdTot = tijdTot;
    }
    public LocalTime getTijdTot() {
        return tijdTot;
    }

    public void setNaamReservering(String naamReservering) {
        this.naamReservering = naamReservering;
    }
    public String getNaamReservering() {
        return naamReservering;
    }

    public void setTafel(Tafel tafel) {
        this.tafel = tafel;
    }
    public Tafel getTafel() {
        return tafel;
    }
}
