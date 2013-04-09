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
    private String addresse, navn;

    public Kunde(int knummer, int postnummer, String addresse, String navn, int telefonnummer) {
        this.knummer = knummer;
        this.postnummer = postnummer;
        this.addresse = addresse;
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
