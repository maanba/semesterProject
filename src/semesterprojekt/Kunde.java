/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Kunde {

    private int knummer, postnummer, telefonnummer;
    private String adresse, navn;

    public Kunde(int knummer, String navn, String adresse, int postnummer, int telefonnummer) {
        this.knummer = knummer;
        this.postnummer = postnummer;
        this.adresse = adresse;
        this.navn = navn;
        this.telefonnummer = telefonnummer;
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
}
