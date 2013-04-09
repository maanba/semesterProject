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
    private String status, modtaget, sendt, afhentning;
    private double pris;
    private ArrayList<Odetaljer> od = new ArrayList();

    public Ordre(int onummer, int knummer, double pris, String afhentning, String status, String modtaget, String sendt, int ver) {
        this.onummer = onummer;
        this.knummer = knummer;
        this.pris = pris;
        this.afhentning = afhentning;
        this.ver = ver;
        this.status = status;
        this.modtaget = modtaget;
        this.sendt = sendt;
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

    public String getModtaget() {
        return modtaget;
    }

    public void setModtaget(String modtaget) {
        this.modtaget = modtaget;
    }

    public String getSendt() {
        return sendt;
    }

    public void setSendt(String sendt) {
        this.sendt = sendt;
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
        return "Ordre{" + "onummer=" + onummer + ", knummer=" + knummer + ", ver=" + ver + ", status=" + status + ", modtaget=" + modtaget + ", sendt=" + sendt + ", afhentning=" + afhentning + ", pris=" + pris + ", od=" + od + '}';
    }

    
}