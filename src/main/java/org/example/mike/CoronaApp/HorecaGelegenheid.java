package org.example.mike.CoronaApp;

import java.util.ArrayList;

public class HorecaGelegenheid {
    private String naam;
    private Adres adres;
    private int aantalTafels;
    private int maximaleCapaciteitHorecaGelegenheid;
    private ArrayList<Tafel> lijstVanTafels;

    HorecaGelegenheid(String naam, Adres adres, int aantalTafels, int maximaleCapaciteitHorecaGelegenheid, ArrayList<Tafel> lijstVanTafels){
        setNaam(naam);
        setAdres(adres);
        setAantalTafels(aantalTafels);
        setMaximaleCapaciteitHorecaGelegenheid(maximaleCapaciteitHorecaGelegenheid);
        setLijstVanTafels(lijstVanTafels);
    }


    //getters and setters
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public String getNaam() {
        return naam;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
    public Adres getAdres() {
        return adres;
    }

    public void setAantalTafels(int aantalTafels) {
        //add safety check??
        this.aantalTafels = aantalTafels;
    }
    public int getAantalTafels() {
        return aantalTafels;
    }

    int getMaximaleCapaciteitHorecaGelegenheid() {
        return maximaleCapaciteitHorecaGelegenheid;
    }
    void setMaximaleCapaciteitHorecaGelegenheid(int maximaleCapaciteitHorecaGelegenheid) {
        //add safety check??
        this.maximaleCapaciteitHorecaGelegenheid = maximaleCapaciteitHorecaGelegenheid;
    }

    public void setLijstVanTafels(ArrayList<Tafel> lijstVanTafels) {
        this.lijstVanTafels = lijstVanTafels;
    }
    public ArrayList<Tafel> getLijstVanTafels() {
        return lijstVanTafels;
    }
}
