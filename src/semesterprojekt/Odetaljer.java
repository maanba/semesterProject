/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Odetaljer {

    private int onummer, vnummer, maengde, status;

    public Odetaljer(int onummer, int vnummer, int maengde, int status) {
        this.onummer = onummer;
        this.vnummer = vnummer;
        this.maengde = maengde;
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public int getOnummer() {
        return onummer;
    }

    public void setOnummer(int onummer) {
        this.onummer = onummer;
    }

    public int getVnummer() {
        return vnummer;
    }

    public void setVnummer(int vnummer) {
        this.vnummer = vnummer;
    }

    public int getMaengde() {
        return maengde;
    }

    public void setMaengde(int maengde) {
        this.maengde = maengde;
    }

    @Override
    public String toString() {
        String navn = vnummer + "";
        int længde = 0;

        if (navn.length() >= 15) {
            navn = navn.substring(0, 14);
        } else {
            længde = 15 - navn.length();
            for (int p = 0; p <= længde; p++) {
                navn += " ";
            }
        }
        String stringStatus;
        if (status == 1) {
            stringStatus = "Hjemme";
        } else {
            stringStatus = "Ude";
        }
        return navn + maengde + ", " + stringStatus;
    }
}
