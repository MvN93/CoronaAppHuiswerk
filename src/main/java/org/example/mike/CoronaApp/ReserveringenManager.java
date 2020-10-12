package org.example.mike.CoronaApp;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

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
                if(!(datumReservering == null) && !(beginTijdReservering == null) && !(eindTijdReservering == null)){
                    Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, eindTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                    lijstVanReserveringen.add(nieuweReservering);
                }
                else if(!(datumReservering == null) && !(beginTijdReservering == null)){
                    Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                    lijstVanReserveringen.add(nieuweReservering);
                }
                else if(!(beginTijdReservering == null) && !(eindTijdReservering == null)){
                    Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                    lijstVanReserveringen.add(nieuweReservering);
                }
                else if(!(beginTijdReservering == null)){
                    Reservering nieuweReservering = new Reservering(beginTijdReservering, persoon, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                    lijstVanReserveringen.add(nieuweReservering);
                }
                //nog toevoegen dat als eindttijd of 1 vd anderen null is we er een aanmaken gebruikmakend van de constructor zonder eidTijd en anderen
                //begintijd kan eigenlijk niet null zijn vanwege eerdere exception dus kan eigenlijk weg hier?

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }

        if(vrijeTafelGevonden == false){
            System.out.println("Helaas is deze optie al bezet.");
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
            else if(!(datumReservering == null) && !(beginTijdReservering == null)){
                Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);
            }
            else if(!(beginTijdReservering == null) && !(eindTijdReservering == null)){
                Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);
            }
            else if(!(beginTijdReservering == null)){
                Reservering nieuweReservering = new Reservering(beginTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
                lijstVanReserveringen.add(nieuweReservering);
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }

        if(vrijeTafelGevonden == false){
            System.out.println("Helaas is deze optie al bezet.");
        }
    }


    boolean gaNaOfTafelVrijIs(int tafelnummer, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering, boolean tafelIsVrij){
        if(datumReservering == null){
            datumReservering = LocalDate.now();
        }
        if(eindTijdReservering == null){
            eindTijdReservering = beginTijdReservering.plusHours(2);
        }

        for (Reservering reservering : lijstVanReserveringen) {
            boolean reserveringIsVanAnderTafelNummer = !(reservering.getTafel().getTafelNummer() == tafelnummer);
            boolean reserveringIsVanAnderDatum = !(reservering.getDatum().equals(datumReservering));

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

    //console
    public void neemReserveringAanViaDeConsoleInvoer(){
        Scanner scanner = new Scanner(System.in);

        LocalDate ingevoerdeDatum = vraagOmInvoerDatum(scanner);

        LocalTime ingevoerdeBeginTijd = vraagOmInvoerBeginTijd(scanner);

        LocalTime ingevoerdeEindTijd = vraagOmInvoerEindTijd(scanner);

        String persoonReservering = vraagOmInvoerPersoon(scanner);
        Persoon reserverendePersoon;
        String naamPersoon;
        if(persoonReservering.contains(";")){
            String[] naamEnTelefoonnummer = persoonReservering.split(";",2);
            naamPersoon = naamEnTelefoonnummer[0];
            String telefoonnummerPersoon = naamEnTelefoonnummer[1];
            reserverendePersoon = new Persoon(naamPersoon, telefoonnummerPersoon);
            naamPersoon = reserverendePersoon.getNaam(); //voor het geval gethrowed wordt? check of de naam er echt is?
        }
        else{
            reserverendePersoon = null;
            naamPersoon = persoonReservering;
        }

        System.out.println("Bedankt voor het doorgeven van uw gegevens.");
        System.out.println("Ik zal nu voor u kijken of er een tafel beschikbaar is op de aangevraagde tijd.");

        neemReserveringAan(reserverendePersoon, naamPersoon, ingevoerdeBeginTijd, ingevoerdeEindTijd, ingevoerdeDatum);
    }

    public LocalDate vraagOmInvoerDatum(Scanner scanner){
        LocalDate ingevoerdeDatum;
        System.out.println("Please enter the date for which you would like to book in the following format: YYYY;MM;DD or press enter if you would like to choose today.");

        String ingevoerdeDatumAlsString = scanner.nextLine();
        if(ingevoerdeDatumAlsString.contains(";")){
            String[] ingevoerdeDatumAlsLosseStringsYearMonthDay = ingevoerdeDatumAlsString.split(";");
            int ingevoerdeJaar = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[0]);
            int ingevoerdeMaand = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[1]);
            int ingevoerdeDag = Integer.parseInt(ingevoerdeDatumAlsLosseStringsYearMonthDay[2]);

            ingevoerdeDatum = LocalDate.of(ingevoerdeJaar, ingevoerdeMaand, ingevoerdeDag);
        }
        else{
            ingevoerdeDatum = null;
        }

        return ingevoerdeDatum;
    }

    public LocalTime vraagOmInvoerBeginTijd(Scanner scanner){
        LocalTime ingevoerdeBeginTijd;
        System.out.println("Please enter the starting time for which you would like to book in the following format: HH;MM");

        String ingevoerdeBeginTijdAlsString = scanner.nextLine();
        if(ingevoerdeBeginTijdAlsString.contains(";")) {
            String[] ingevoerdeBeginTijdAlsLosseStrings = ingevoerdeBeginTijdAlsString.split(";");
            int ingevoerdeBeginUur = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[0]);
            int ingevoerdeBeginMinuut = Integer.parseInt(ingevoerdeBeginTijdAlsLosseStrings[1]);

            ingevoerdeBeginTijd = LocalTime.of(ingevoerdeBeginUur, ingevoerdeBeginMinuut);
        }
        else{
            throw new VraagOmInvoerBeginTijdException("Er is geen geldige begintijd ingevoerd" + this.getClass().getName());
        }

        return ingevoerdeBeginTijd;
    }
    public LocalTime vraagOmInvoerEindTijd(Scanner scanner){
        LocalTime ingevoerdeEindTijd;
        System.out.println("Please enter the ending time for which you would like to book in the following format: HH;MM");

        String ingevoerdeEindTijdAlsString = scanner.nextLine();
        if(ingevoerdeEindTijdAlsString.contains(";")) {
            String[] ingevoerdeEindTijdAlsLosseStrings = ingevoerdeEindTijdAlsString.split(";");
            int ingevoerdeEindUur = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[0]);
            int ingevoerdeEindMinuut = Integer.parseInt(ingevoerdeEindTijdAlsLosseStrings[1]);

            ingevoerdeEindTijd = LocalTime.of(ingevoerdeEindUur, ingevoerdeEindMinuut);
        }
        else{
            ingevoerdeEindTijd = null;
        }

        return ingevoerdeEindTijd;
    }

    public String vraagOmInvoerPersoon(Scanner scanner){
        System.out.println("Wilt u als uw contactgegevens voor extra reserveringsgemak bij een volgende keer? Yes or No");

        String persoonsgegevensOpslaan = scanner.nextLine();
        if(persoonsgegevensOpslaan.equalsIgnoreCase("Yes")){
            System.out.println("Bedankt voor uw vertrouwen in onze diensten!");
            System.out.println("Wat is uw naam?");
            String naamPersoon = scanner.nextLine();

            System.out.println("Wat is uw telefoonnummer");
            String telefoonnummerPersoon = scanner.nextLine();

            String persoonsgegevensAlsString = "" + naamPersoon + ";" + telefoonnummerPersoon;
            return persoonsgegevensAlsString;
        }
        else if(persoonsgegevensOpslaan.equalsIgnoreCase("No")){
            System.out.println("Geen probleem. Onder welke naam mag ik de reservering noteren?");
            String naamReservering = scanner.nextLine();
            return naamReservering;
        }
        else{
            throw new VraagOmOpslaagGegevensException("Er is geen geldig antwoord gegeven over het opslaan van persoonsgegevens" + this.getClass().getName());
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
