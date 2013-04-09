/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Vare {

    private int vnummer, qty;
    private double pris;
    private String vnavn;

    public Vare(int vnummer, String vnavn, int qty, double pris) {
        this.vnummer = vnummer;
        this.vnavn = vnavn;
        this.qty = qty;
        this.pris = pris;
    }

    public int getVnummer() {
        return vnummer;
    }

    public void setVnummer(int vnummer) {
        this.vnummer = vnummer;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getVnavn() {
        return vnavn;
    }

    public void setVnavn(String vnavn) {
        this.vnavn = vnavn;
    }

    @Override
    public String toString() {
        String navn = vnavn;
        int længde = 0;

        if (navn.length() >= 15) {
            navn = navn.substring(0, 14);
        } else {
            længde = 15 - navn.length();
            for (int p = 0; p <= længde; p++) {
                navn += " ";
            }
        }
        return navn + qty;
    }
}
