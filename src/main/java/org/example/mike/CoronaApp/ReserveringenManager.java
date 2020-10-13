package org.example.mike.CoronaApp;

import com.sun.xml.internal.bind.Locatable;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ReserveringenManager {
    private HorecaGelegenheid horecaGelegenheid;
    private ArrayList<Reservering> lijstVanReserveringen;

    private final static LocalTime SLUITINGSTIJD_ONDER_CORONAMAATREGELEN = LocalTime.of(23,00);

    ReserveringenManager(HorecaGelegenheid horecaGelegenheid){
        setHorecaGelegenheid(horecaGelegenheid);
        lijstVanReserveringen = new ArrayList<Reservering>();
    }

    //checked de datum nog niet
    void neemReserveringAan(Persoon persoon, String reserveringOnderNaam, LocalTime beginTijdReservering, LocalTime eindTijdReservering, LocalDate datumReservering){
        if(!(persoon == null)){
            neemReserveringAanPersoonsObjectBekend(persoon, beginTijdReservering, eindTijdReservering, datumReservering);
        }
        else{
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
                maakReserveringAanEnVoegToeAanLijstPersoonsObjectBekend(datumReservering, beginTijdReservering, eindTijdReservering, persoon, tafelnummer);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }

        if(vrijeTafelGevonden == false){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Helaas is deze optie al bezet.");

            LocalTime anderTijdsVoorstel = zoekAndereMogelijkheidOpZelfdeDatum(datumReservering);//, tijdsduurReservering);

            if(anderTijdsVoorstel == null){
                System.out.println("Helaas kunt u op deze dag niet reserveren, probeert u het graag nog eens op een andere dag.");
            }
            else{
                System.out.println("Ik zie dat een ander moment, namelijk " + anderTijdsVoorstel.getHour() + ":" + anderTijdsVoorstel.getMinute() + " wel mogelijk is, zou u op deze tijd willen reserveren? Yes or No"); //kan dan nu nog niet uit meerdere opties kiezen, kunnen dit toevoegen door alle opties terug te geven.
                String ingevoerdeAntwoordOfAndereTijdInOrdeIs = scanner.nextLine();
                if(ingevoerdeAntwoordOfAndereTijdInOrdeIs.equalsIgnoreCase("yes")){
                    tafelnummer = 0;
                    //het volgende blok kan dus in methode want herhaling
                    //ga alle tafels langs tot plek gevonden is
                    while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
                        //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
                        boolean tafelIsVrij = true;

                        if(lijstVanReserveringen.size() > 0) {
                            tafelIsVrij = gaNaOfTafelVrijIs(tafelnummer, anderTijdsVoorstel, anderTijdsVoorstel.plusHours(2), datumReservering, tafelIsVrij);
                        }
                        //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

                        //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
                        if(tafelIsVrij == true) {
                            maakReserveringAanEnVoegToeAanLijstPersoonsObjectBekend(datumReservering, anderTijdsVoorstel, anderTijdsVoorstel.plusHours(2), persoon, tafelnummer);

                            //om de while loop te breken
                            vrijeTafelGevonden = true;
                        }
                        else{
                            tafelnummer = tafelnummer +1;
                        }
                    }
                }
                else if(ingevoerdeAntwoordOfAndereTijdInOrdeIs.equalsIgnoreCase("no")){
                    System.out.println("Helaas, hopelijk komt u binnenkort weer langs.");
                }
                else{
                    throw new VraagOmBevestigingAnderMomentException("Er is niet geldig geantwoord op de vraag of een andere tijd in orde is " + this.getClass().getName());
                }
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
            if(tafelIsVrij == true) {
                maakReserveringAanEnVoegToeAanLijstAlleenNaamBekend(datumReservering, beginTijdReservering, eindTijdReservering, reserveringOnderNaam, tafelnummer);

                //om de while loop te breken
                vrijeTafelGevonden = true;
            }
            else{
                tafelnummer = tafelnummer +1;
            }
        }

        if(vrijeTafelGevonden == false){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Helaas is deze optie al bezet.");

            LocalTime anderTijdsVoorstel = zoekAndereMogelijkheidOpZelfdeDatum(datumReservering);//, tijdsduurReservering);

            if(anderTijdsVoorstel == null){
                System.out.println("Helaas kunt u op deze dag niet reserveren, probeert u het graag nog eens op een andere dag.");
            }
            else{
                System.out.println("Ik zie dat een ander moment, namelijk " + anderTijdsVoorstel.getHour() + ":" + anderTijdsVoorstel.getMinute() + " wel mogelijk is, zou u op deze tijd willen reserveren? Yes or No"); //kan dan nu nog niet uit meerdere opties kiezen, kunnen dit toevoegen door alle opties terug te geven.
                String ingevoerdeAntwoordOfAndereTijdInOrdeIs = scanner.nextLine();
                if(ingevoerdeAntwoordOfAndereTijdInOrdeIs.equalsIgnoreCase("yes")){
                    tafelnummer = 0;
                    //het volgende blok kan dus in methode want herhaling
                    //ga alle tafels langs tot plek gevonden is
                    while((vrijeTafelGevonden == false) && tafelnummer < horecaGelegenheid.getAantalTafels()) {
                        //begin met er van uit gaan dat de tafel vrij is, mocht dit niet het geval ijn wordt de boolean false, anders wordt gereserveerd.
                        boolean tafelIsVrij = true;

                        if(lijstVanReserveringen.size() > 0) {
                            tafelIsVrij = gaNaOfTafelVrijIs(tafelnummer, anderTijdsVoorstel, anderTijdsVoorstel.plusHours(2), datumReservering, tafelIsVrij);
                        }
                        //else tafel is vrij, want er zijn geen reserveringen dus hoeven niks te doen

                        //maak reservering als vrij, anders verhoog de tafelindex en controlleer de andere tafel
                        if(tafelIsVrij == true) {
                            maakReserveringAanEnVoegToeAanLijstAlleenNaamBekend(datumReservering, anderTijdsVoorstel, anderTijdsVoorstel.plusHours(2), reserveringOnderNaam, tafelnummer);

                            //om de while loop te breken
                            vrijeTafelGevonden = true;
                        }
                        else{
                            tafelnummer = tafelnummer +1;
                        }
                    }
                }
                else if(ingevoerdeAntwoordOfAndereTijdInOrdeIs.equalsIgnoreCase("no")){
                    System.out.println("Helaas, hopelijk komt u binnenkort weer langs.");
                }
                else{
                    throw new VraagOmBevestigingAnderMomentException("Er is niet geldig geantwoord op de vraag of een andere tijd in orde is " + this.getClass().getName());
                }
            }

        }
    }

    void maakReserveringAanEnVoegToeAanLijstPersoonsObjectBekend(LocalDate datumReservering, LocalTime beginTijdReservering, LocalTime eindTijdReservering, Persoon persoon, int tafelnummer){
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
    }

    void maakReserveringAanEnVoegToeAanLijstAlleenNaamBekend(LocalDate datumReservering, LocalTime beginTijdReservering, LocalTime eindTijdReservering, String reserveringOnderNaam, int tafelnummer){
        if (!(datumReservering == null) && !(beginTijdReservering == null) && !(eindTijdReservering == null)) {//&& als genoeg plek aan tafel (dit moet nog toegevoegd worden)
            Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, eindTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
            lijstVanReserveringen.add(nieuweReservering);
        }
        else if (!(datumReservering == null) && !(beginTijdReservering == null)) {
            Reservering nieuweReservering = new Reservering(datumReservering, beginTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
            lijstVanReserveringen.add(nieuweReservering);
        }
        else if (!(beginTijdReservering == null) && !(eindTijdReservering == null)) {
            Reservering nieuweReservering = new Reservering(beginTijdReservering, eindTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
            lijstVanReserveringen.add(nieuweReservering);
        }
        else if (!(beginTijdReservering == null)) {
            Reservering nieuweReservering = new Reservering(beginTijdReservering, reserveringOnderNaam, vindtTafelHorendeBijTafelnummer(tafelnummer, horecaGelegenheid));
            lijstVanReserveringen.add(nieuweReservering);
        }
    }

    LocalTime zoekAndereMogelijkheidOpZelfdeDatum(LocalDate datumReservering){//, int tijdsduurReservering){
        if(datumReservering == null){
            datumReservering = LocalDate.now();
        }
        //if(tijdsduurReservering == null){
        int tijdsduurReservering = 2;
        //}

        ArrayList<LocalTime> mogelijkeAndereBeginTijden = new ArrayList<LocalTime>();
        LocalTime mogelijkeBeginTijd;

        for(Reservering reservering : lijstVanReserveringen){
            boolean reserveringIsVanAnderDatum = !(reservering.getDatum().equals(datumReservering));

            if(reserveringIsVanAnderDatum == true) {
                //beeindig voor deze reserveringdoorloping de forloop want reservering niet relevant want andere datum
            }
            else{
                LocalTime mogelijkeAndereTijd = reservering.getTijdTot().plusMinutes(15); //add 15 minutes for cleaning the table
                mogelijkeAndereBeginTijden.add(mogelijkeAndereTijd);
            }
        }

        if(mogelijkeAndereBeginTijden.isEmpty() == true){
            mogelijkeBeginTijd = null;
        }
        else{
            //ga na of misschien toch niet met andere reserveringen overlapt
            for(LocalTime mogelijkeAndereBeginTijd : mogelijkeAndereBeginTijden){
                boolean tochGeenMogelijkeTijd = false;

                for(Reservering reservering : lijstVanReserveringen){
                    tochGeenMogelijkeTijd = ingevoerdeTijdsuurLigtInVolTijdsvlak(mogelijkeAndereBeginTijd, reservering.getTijdVan(), reservering.getTijdTot());
                }
                for(Reservering reservering : lijstVanReserveringen){
                    tochGeenMogelijkeTijd = ingevoerdeTijdsuurLigtInVolTijdsvlak(mogelijkeAndereBeginTijd.plusHours(tijdsduurReservering), reservering.getTijdVan(), reservering.getTijdTot());
                }
                for(Reservering reservering : lijstVanReserveringen){
                    if (mogelijkeAndereBeginTijd.isBefore(reservering.getTijdVan()) && mogelijkeAndereBeginTijd.isAfter(reservering.getTijdTot())) {
                        tochGeenMogelijkeTijd = true;
                    }
                }

                if(tochGeenMogelijkeTijd == true){
                    mogelijkeAndereBeginTijden.remove(mogelijkeAndereBeginTijd);
                }
            }

            if(mogelijkeAndereBeginTijden.isEmpty() == true){
                mogelijkeBeginTijd = null;
            }
            else {
                //now we choose just the first option from the ones that are; should become all of them and let them choose
                mogelijkeBeginTijd = mogelijkeAndereBeginTijden.get(0);
            }
        }

        return mogelijkeBeginTijd;
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

                //Note dit is nodig omdat anders bij extreem lange reserveringen andere kortere reserveringen gecovered kunnen worden
                if (beginTijdReservering.isBefore(reservering.getTijdVan()) && eindTijdReservering.isAfter(reservering.getTijdTot())) {
                    tafelIsVrij = false;
                }
            }
        }
        return tafelIsVrij;
    }

    boolean ingevoerdeTijdsuurLigtInVolTijdsvlak(LocalTime ingevoerdeTijd, LocalTime beginTijdTijdsvlak, LocalTime eindTijdTijdsvlak){
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


        if(ingevoerdeBeginTijd.isAfter(SLUITINGSTIJD_ONDER_CORONAMAATREGELEN) || ingevoerdeEindTijd.isAfter(SLUITINGSTIJD_ONDER_CORONAMAATREGELEN)){
            throw new SluitingstijdOnderCoronaException("De ingevoerde tijd gaat tegen de regels in. " + this.getClass().getName());
        }

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
            throw new VraagOmInvoerBeginTijdException("Er is geen geldige begintijd ingevoerd " + this.getClass().getName());
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
            throw new VraagOmOpslaagGegevensException("Er is geen geldig antwoord gegeven over het opslaan van persoonsgegevens " + this.getClass().getName());
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
