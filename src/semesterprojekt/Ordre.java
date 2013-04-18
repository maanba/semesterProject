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

    private int onummer, fnummer, knummer, ver;
    private String status, modtaget, levering, returnering, afhentning, tid;
    private double pris, depositum, rabat;
    private ArrayList<Odetaljer> od = new ArrayList();

    public Ordre(int onummer, int fnummer, int knummer, double pris, double rabat, double depositum, String tid, String afhentning, String status, String modtaget, String levering, String returnering, int ver) {
        this.onummer = onummer;
        this.fnummer = fnummer;
        this.knummer = knummer;
        this.pris = pris;
        this.rabat = rabat;
        this.depositum = depositum;
        this.tid = tid;
        this.afhentning = afhentning;
        this.ver = ver;
        this.status = status;
        this.modtaget = modtaget;
        this.levering = levering;
        this.returnering = returnering;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getFnummer() {
        return fnummer;
    }

    public void setFnummer(int fnummer) {
        this.fnummer = fnummer;
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

    public void setOd(ArrayList<Odetaljer> od) {
        this.od = od;
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

    public void nuVer() {
        ver++;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public double getDepositum() {
        return depositum;
    }

    public void setDepositum(double depositum) {
        this.depositum = depositum;
    }

    @Override
    public String toString() {
        return knummer + ", " + onummer + ", " + status;
    }
}
