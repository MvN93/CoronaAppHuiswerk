package org.example.mike.CoronaApp;

public class Persoon {

    private String naam;
    private String telefoonNummer;

        private static String TELEFOON_NUMMER_NIET_BEKEND = "0000000000";

    Persoon(String naam){
        this(naam, TELEFOON_NUMMER_NIET_BEKEND);
    }

    Persoon(String naam, String telefoonNummer){
        setNaam(naam);
        setTelefoonNummer(telefoonNummer);
    }

    //getters and setters
    public void setNaam(String naam) {
        this.naam = naam;
        //add throw
    }
    public String getNaam() {
        return naam;
    }

    public void setTelefoonNummer(String telefoonNummer) {
        this.telefoonNummer = telefoonNummer;
        //add throw
    }
    public String getTelefoonNummer() {
        return telefoonNummer;
    }
}
