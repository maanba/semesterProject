/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Del {

    private String titel;
    private int vnummer, antal, status;

    public Del(int vnummer, String titel, int antal) {
        this.vnummer = vnummer;
        this.titel = titel;
        this.antal = antal;
        this.status = status;
    }

    public void setDelStatus(int delStatus) {
        this.status = delStatus;
    }

    public int getDelStatus() {
        return status;
    }

    public int getVnummer() {
        return vnummer;
    }

    public void setVnummer(int vnummer) {
        this.vnummer = vnummer;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public String toString() {
        String navn = titel;
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
        return navn + antal + ", " + stringStatus;
    }
}
