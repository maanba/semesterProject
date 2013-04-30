/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Vare {

    private int vnummer, qty, aktiv;
    private double pris;
    private String vnavn;
    private ArrayList<Del> del = new ArrayList<>();

    public Vare(int vnummer, String vnavn, int qty, double pris, int aktiv) {
        this.vnummer = vnummer;
        this.vnavn = vnavn;
        this.qty = qty;
        this.pris = pris;
        this.aktiv = aktiv;
    }

    public int getAktiv() {
        return aktiv;
    }

    public void setAktiv(int aktiv) {
        this.aktiv = aktiv;
    }

    public void addDel(Del del) {
        del.setVnummer(vnummer);
        this.del.add(del);
    }

    public ArrayList<Del> getDel() {
        return del;
    }

    public void setDel(ArrayList<Del> del) {
        this.del = del;
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
