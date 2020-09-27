package org.example.mike.CoronaApp;

public class HorecaGelegenheid {
    private String naam;
    private Adres adres;
    private int maximaleCapaciteitHorecaGelegenheid;

    HorecaGelegenheid(String naam, Adres adres){
        setNaam(naam);
        setAdres(adres);
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

    int getMaximaleCapaciteitHorecaGelegenheid() {
        return maximaleCapaciteitHorecaGelegenheid;
    }
    void setMaximaleCapaciteitHorecaGelegenheid(int maximaleCapaciteitHorecaGelegenheid) {
        this.maximaleCapaciteitHorecaGelegenheid = maximaleCapaciteitHorecaGelegenheid;
    }
}
