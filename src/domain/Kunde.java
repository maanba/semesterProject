/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Kunde {

    private int knummer, postnummer, telefonnummer;
    private String firma, navn, adresse;

    public Kunde(int knummer, String firma, String navn, String adresse, int postnummer, int telefonnummer) {
        this.knummer = knummer;
        this.firma = firma;
        this.navn = navn;
        this.adresse = adresse;
        this.postnummer = postnummer;
        this.telefonnummer = telefonnummer;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public int getKnummer() {
        return knummer;
    }

    public void setKnummer(int knummer) {
        this.knummer = knummer;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String toString() {
        return "" + knummer;
    }
}
