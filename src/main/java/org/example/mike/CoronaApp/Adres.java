package org.example.mike.CoronaApp;

public class Adres {
        private String plaats;
        private int huisnummer;
        private String straat;

        Adres(String straat, int huisnummer, String plaats){
            setStraat(straat);
            setHuisnummer(huisnummer);
            setPlaats(plaats);
        }

        String toStringRepresentation()
        {
            return straat + " " + huisnummer + ", " + plaats + ".";
        }

        //setters and getters
        public void setPlaats(String plaats) {
            this.plaats = plaats;
        }

        public String getPlaats() {
            return plaats;
        }

        public void setHuisnummer(int huisnummer) {
            this.huisnummer = huisnummer;
        }

        public int getHuisnummer() {
            return huisnummer;
        }

        public void setStraat(String straat) {
            this.straat = straat;
        }

        public String getStraat() {
            return straat;
        }
}
