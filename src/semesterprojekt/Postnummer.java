/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Postnummer {
    
    private int postnummer;
    private String by;

    public Postnummer(int postnummer, String by) {
        this.postnummer = postnummer;
        this.by = by;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }
}
