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

    private int vnummer, antal;
    private String titel;
    
    public Del(int vnummer, String titel, int antal) {
        this.vnummer = vnummer;
        this.titel = titel;
        this.antal = antal;
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
        return navn + antal;
    }
}


