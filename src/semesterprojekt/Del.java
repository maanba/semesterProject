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

    private int vnummer, antal, hjemme;
    private String titel;

    public Del(int vnummer, String titel, int antal, int hjemme) {
        this.vnummer = vnummer;
        this.titel = titel;
        this.antal = antal;
        this.hjemme = hjemme;
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
    
    public void setHjemme(int hjemme) {
        this.hjemme = hjemme;
    }

    public int getHjemme() {
        return hjemme;
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
        String status;
        if (hjemme == 1)
        {
            status = "Hjemme";
        }
        else
        {
            status = "Ude";
        }
        return navn + antal;
    }
}
