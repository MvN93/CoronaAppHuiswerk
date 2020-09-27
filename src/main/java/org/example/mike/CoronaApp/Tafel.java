package org.example.mike.CoronaApp;

public class Tafel {
    private int tafelNummer;
    private int aantalPersonen;

    Tafel(int tafelNummer){
        this(tafelNummer, 6);
    }

    Tafel(int tafelNummer, int aantalPersonen){
        setTafelNummer(tafelNummer);
        setAantalPersonen(aantalPersonen);
    }

    //getters and setters
    public void setTafelNummer(int tafelNummer) {
        this.tafelNummer = tafelNummer;
    }
    public int getTafelNummer() {
        return tafelNummer;
    }

    public void setAantalPersonen(int aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }
    public int getAantalPersonen() {
        return aantalPersonen;
    }
}
