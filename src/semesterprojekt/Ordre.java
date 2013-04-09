/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import java.util.ArrayList;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Ordre {

    private int onummer, knummer, ver;
    private String status, modtaget, levering, returnering, afhentning;
    private double pris;
    private ArrayList<Odetaljer> od = new ArrayList();

    public Ordre(int onummer, int knummer, double pris, String afhentning, String status, String modtaget, String levering, String returnering, int ver) {
        this.onummer = onummer;
        this.knummer = knummer;
        this.pris = pris;
        this.afhentning = afhentning;
        this.ver = ver;
        this.status = status;
        this.modtaget = modtaget;
        this.levering = levering;
        this.returnering = returnering;
    }

    public String getModtaget() {
        return modtaget;
    }

    public void setModtaget(String modtaget) {
        this.modtaget = modtaget;
    }

    public String getAfhentning() {
        return afhentning;
    }

    public void setAfhentning(String afhentning) {
        this.afhentning = afhentning;
    }

    public ArrayList<Odetaljer> getOd() {
        return od;
    }

    public void addOd(Odetaljer o) {
        od.add(o);
    }

    public int getOnummer() {
        return onummer;
    }

    public void setOnummer(int onummer) {
        this.onummer = onummer;
    }

    public int getKnummer() {
        return knummer;
    }

    public void setKnummer(int knummer) {
        this.knummer = knummer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevering() {
        return levering;
    }

    public void setLevering(String levering) {
        this.levering = levering;
    }

    public String getReturnering() {
        return returnering;
    }

    public void setReturnering(String returnering) {
        this.returnering = returnering;
    }

    public int getVer() {
        return ver;
    }

    public void setVer(int ver) {
        this.ver = ver;
    }
    
    public void nuVer(){
        ver++;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return "Ordre{" + "onummer=" + onummer + ", knummer=" + knummer + ", ver=" + ver + ", status=" + status + ", modtaget=" + levering + ", sendt=" + returnering + ", afhentning=" + afhentning + ", pris=" + pris + ", od=" + od + '}';
    }

    
}