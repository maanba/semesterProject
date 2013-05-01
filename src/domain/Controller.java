/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import com.itextpdf.text.DocumentException;
import dataSource.DBFacade;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Controller {

    private boolean processingOrder;	// state of business transaction
    private boolean rediger = false;
    private Ordre currentOrder;       	// Order in focus
    private Vare currentVare;
    private Kunde currentKunde;
    private Postnummer currentPostnummer;
    private DBFacade dbFacade;

    public Controller() {
        dbFacade = DBFacade.getInstance();
    }

    public Ordre getOrder(int onummer) {
        if (processingOrder) {
            return null;
        }
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentOrder = dbFacade.getOrdre(onummer);
        return currentOrder;
    }

    public Ordre getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Ordre currentOrder) {
        this.currentOrder = currentOrder;
    }

    public boolean kundeSøg(Kunde kunde, String jTextFieldKundeSøg) {
        boolean result = false;
        String firma = null;
        String knavn = null;
        if (kunde.getFirma() != null) {
            firma = kunde.getFirma().toLowerCase();
        }
        if (kunde.getNavn() != null) {
            knavn = kunde.getNavn().toLowerCase();
        }
        String knummer = kunde.getKnummer() + "";
        String telefonnummer = kunde.getTelefonnummer() + "";
        String postnummer = kunde.getPostnummer() + "";
        if (knavn != null && knavn.contains(jTextFieldKundeSøg.toLowerCase())) {
            result = true;
        } else if (firma != null && firma.contains(jTextFieldKundeSøg.toLowerCase()) || kunde.getNavn().contains(jTextFieldKundeSøg.toLowerCase())) {
            result = true;
        } else if (knummer.contains(jTextFieldKundeSøg)) {
            result = true;
        } else if (telefonnummer.contains(jTextFieldKundeSøg)) {
            result = true;
        } else if (postnummer.contains(jTextFieldKundeSøg)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean tilbudSøg(Vare vare, String jTextFieldTilbudSøg) {
        boolean result = false;
        String vnavn = vare.getVnavn().toLowerCase();
        String vnummer = vare.getVnummer() + "";
        if (vnavn.contains(jTextFieldTilbudSøg.toLowerCase())) {
            result = true;
        } else if (vnummer.contains(jTextFieldTilbudSøg)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean historikSøg(Ordre ordre, String jTextFieldHistorikSøg) {
        boolean result = false;
        String onummer = ordre.getOnummer() + "";
        String knummer = ordre.getKnummer() + "";
        String status = ordre.getStatus().toLowerCase();
        Kunde kunde = getKunde(ordre.getKnummer());
        String knavn = kunde.getNavn().toLowerCase();
        if (onummer.contains(jTextFieldHistorikSøg)) {
            result = true;
        } else if (status.contains(jTextFieldHistorikSøg.toLowerCase())) {
            result = true;
        } else if (knummer.contains(jTextFieldHistorikSøg)) {
            result = true;
        } else if (knavn.contains(jTextFieldHistorikSøg.toLowerCase())) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean lagerSøg(Vare vare, String jTextFieldLagerSøg) {
        boolean result = false;
        String vnummer = vare.getVnummer() + "";
        String vnavn = vare.getVnavn().toLowerCase();
        if (vnavn.contains(jTextFieldLagerSøg.toLowerCase())) {
            result = true;
        } else if (vnummer.contains(jTextFieldLagerSøg)) {
            result = true;
        } else {
            result = true;
        }
        return result;
    }

    public boolean returSøg(Ordre ordre, String jTextFieldReturSøg) {
        boolean result = false;
        String onummer = ordre.getOnummer() + "";
        String knummer = ordre.getKnummer() + "";
        String status = ordre.getStatus().toLowerCase();
        Kunde kunde = getKunde(ordre.getKnummer());
        String firma = null;
        String knavn = null;
        if (kunde.getFirma() != null) {
            firma = kunde.getFirma().toLowerCase();
        }
        if (kunde.getNavn() != null) {
            knavn = kunde.getNavn().toLowerCase();
        }
        if (onummer.contains(jTextFieldReturSøg)) {
            result = true;
        } else if (knummer.contains(jTextFieldReturSøg)) {
            result = true;
        } else if (status.contains(jTextFieldReturSøg.toLowerCase())) {
            result = true;
        } else if (knavn != null && knavn.contains(jTextFieldReturSøg.toLowerCase())) {
            result = true;
        } else if (firma != null && firma.contains(jTextFieldReturSøg.toLowerCase())) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public Ordre addNewOrder(int knummer, double pris, double rabat, double depositum, String tidLev, String tidRet, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer, String kommentar, ArrayList<DelOrdre> delordre) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        int montører = 0;
        String modtaget = dateFormat.format(date);
        dbFacade.startNewBusinessTransaction();
        int newOrderNo = dbFacade.getNextOnummer();// DB-generated unique ID
        if (newOrderNo != 0) {
            processingOrder = true;
            for (int i = 0; i < odetaljer.size(); i++) {
                odetaljer.get(i).setOnummer(newOrderNo);
            }
            for (int i = 0; i < delordre.size(); i++) {
                delordre.get(i).setOnummer(newOrderNo);
            }
            if (afhentning.equals("Afhentes af kunden")) {
                montører = 0;
            } else if (tidRet.equalsIgnoreCase("Stor Order") || tidLev.equalsIgnoreCase("Stor Order")) {
                montører = 4;
            } else {
                for (int i = 0; i < odetaljer.size(); i++) {
                    if (getVare(odetaljer.get(i).getVnummer()).getVnavn().toLowerCase().contains("telt")) {
                        montører = 2;
                        break;
                    } else {
                        montører = 1;
                    }
                }
            }
            currentOrder = new Ordre(newOrderNo, 0, knummer, pris, rabat, depositum, tidLev, tidRet, afhentning, montører, status, modtaget, levering, returnering, kommentar, 0);
            dbFacade.registerNewOrdre(currentOrder);
            for (int i = 0; i < odetaljer.size(); i++) {
                dbFacade.registerNewOdetalje(odetaljer.get(i));
            }
            for (int i = 0; i < delordre.size(); i++) {
                dbFacade.registerNewDelOrdre(delordre.get(i));
            }
            dbFacade.commitBusinessTransaction();
        } else {
            processingOrder = false;
        }
        currentOrder = null;
        return currentOrder;
    }

    public void updateOrder(int knummer, double pris, double rabat, double depositum, String tidLev, String tidRet, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer, ArrayList<DelOrdre> delordre) {
        currentOrder.setAfhentning(afhentning);
        currentOrder.setKnummer(knummer);
        currentOrder.setLevering(levering);
        currentOrder.setPris(pris);
        currentOrder.setRabat(rabat);
        currentOrder.setDepositum(depositum);
        currentOrder.setTidLev(tidLev);
        currentOrder.setTidRet(tidRet);
        currentOrder.setReturnering(returnering);
        currentOrder.setOd(odetaljer);
        int montører = 0;
        if (afhentning.equals("Afhentes af kunden")) {
            montører = 0;
        } else {
            for (int i = 0; i < odetaljer.size(); i++) {
                if (getVare(odetaljer.get(i).getVnummer()).getVnavn().toLowerCase().contains("telt")) {
                    montører = 2;
                    break;
                } else {
                    montører = 1;
                }
                if (tidRet.equalsIgnoreCase("Stor Order") || tidLev.equalsIgnoreCase("Stor Order")) {
                    montører = 4;
                }
            }
        }
        currentOrder.setMontører(montører);
        dbFacade.startNewBusinessTransaction();
        for (int i = 0; i < odetaljer.size(); i++) {
            odetaljer.get(i).setOnummer(currentOrder.getOnummer());
        }
        for (int i = 0; i < delordre.size(); i++) {
            delordre.get(i).setOnummer(currentOrder.getOnummer());
        }
        dbFacade.deleteOdetalje(currentOrder.getOnummer());
        dbFacade.registerDirtyOrdre(currentOrder);
        for (int i = 0; i < odetaljer.size(); i++) {
            dbFacade.registerNewOdetalje(odetaljer.get(i));
        }
        for (int i = 0; i < delordre.size(); i++) {
            dbFacade.registerNewDelOrdre(delordre.get(i));
        }
        dbFacade.commitBusinessTransaction();
        currentOrder = null;
    }

    public boolean deleteOrder(Ordre o) {
        boolean result = false;
        dbFacade.startNewBusinessTransaction();
        result = dbFacade.deleteOrdre(o);
        dbFacade.commitBusinessTransaction();
        return result;
    }

    public void addOrderFakturaNummer(Ordre o) {
        setCurrentOrder(o);
        ArrayList<Odetaljer> odetaljer = o.getOd();
        currentOrder.setFnummer(dbFacade.getNextFnummer());

        dbFacade.startNewBusinessTransaction();
        for (int i = 0; i < odetaljer.size(); i++) {
            odetaljer.get(i).setOnummer(currentOrder.getOnummer());
        }
        dbFacade.deleteOdetalje(currentOrder.getOnummer());
        dbFacade.registerDirtyOrdre(currentOrder);
        for (int i = 0; i < odetaljer.size(); i++) {
            dbFacade.registerNewOdetalje(odetaljer.get(i));
        }
        dbFacade.commitBusinessTransaction();
        currentOrder = null;
    }

    public Ordre changeCnoForOrder(int knummer) {
        if (processingOrder) {
            currentOrder.setKnummer(knummer);
            dbFacade.registerDirtyOrdre(currentOrder);
        }
        return currentOrder;
    }

    public void ordreFaktureret(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Faktureret");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrdre(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void bekraeftOrdre(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        dbFacade.startNewBusinessTransaction();

        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Bekræftet ordre");
                for (int j = 0; j < ol.get(i).getOd().size(); j++) {
                    dbFacade.registerDirtyOdetalje(ol.get(i).getOd().get(j));
                }
                dbFacade.registerDirtyOrdre(ol.get(i));
            }
        }
        dbFacade.commitBusinessTransaction();
    }

    public void updateDelUde(int onummer, int vnummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        dbFacade.startNewBusinessTransaction();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer());
            for (int j = 0; j < ol.get(i).getOd().size(); j++) {
                if (vnummer == ol.get(i).getOd().get(j).getVnummer()) {
                    dbFacade.registerDirtyOdetalje(ol.get(i).getOd().get(j));
                }
            }
        }
        dbFacade.commitBusinessTransaction();
    }

    public void ordreTilbud(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Tilbud");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrdre(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public boolean addOrderDetail(int vnummer, int qty, int status) {
        boolean result = false;
        if (processingOrder) {
            Odetaljer od = new Odetaljer(currentOrder.getOnummer(), vnummer, qty);
            currentOrder.addOd(od);
            dbFacade.registerNewOdetalje(od);
            result = true;
        }
        return result;
    }

    public void addNewKunde(String firma, String navn, String adresse, int postnummer, int telefonnummer) {

        dbFacade.startNewBusinessTransaction();
        int newKnummer = dbFacade.getNextKnummer();
        if (newKnummer != 0) {
            processingOrder = true;
            currentKunde = new Kunde(newKnummer, firma, navn, adresse, postnummer, telefonnummer);
            dbFacade.registerNewKunde(currentKunde);
            dbFacade.commitBusinessTransaction();
        } else {
            processingOrder = false;
        }
        currentKunde = null;
    }

    public void pakkeliste(Ordre ordre) {
        setCurrentOrder(ordre);
        if (ordre.getFnummer() == 0) {
            addOrderFakturaNummer(ordre);
        }
        ordreFaktureret(ordre.getOnummer());
    }

    public void tilbud(Ordre ordre) {
        setCurrentOrder(ordre);
        ordreTilbud(ordre.getOnummer());
    }

    public void faktura(Ordre ordre) {
        setCurrentOrder(ordre);
        if (ordre.getFnummer() == 0) {
            addOrderFakturaNummer(ordre);
        }
        ordreFaktureret(ordre.getOnummer());
    }

    public void updateKunde(int knummer, String firma, String navn, String adresse, int postnummer, int telefonnummer) {

        dbFacade.startNewBusinessTransaction();



        dbFacade.registerDirtyKunde(currentKunde);
        dbFacade.commitBusinessTransaction();

        currentKunde = null;
    }

    public void addVare(int vnummer, String vnavn, int qty, double pris, int aktiv) {
        Vare ressource = new Vare(vnummer, vnavn, qty, pris, aktiv);
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerNewVare(ressource);
        dbFacade.commitBusinessTransaction();

        System.out.println(dbFacade.getAllKunder());
    }

    public int getNextVnummer() {
        return dbFacade.getNextVnummer();
    }

    public int getNextKnummer() {
        return dbFacade.getNextKnummer();
    }

    public void deleteVare(Vare v) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.deleteVare(v);
        dbFacade.commitBusinessTransaction();
    }

    public boolean saveOrder() {
        boolean status = false;
        if (processingOrder) {
            //== ends ongoing business transaction

            status = dbFacade.commitBusinessTransaction();
            processingOrder = false;
            currentOrder = null;
        }
        return status;
    }

    public void resetOrder() {
        processingOrder = false;
        currentOrder = null;
    }

    public void setQty(int vnummer, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllVarer();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty()) {
                vl.get(i).setQty(vl.get(i).getQty() - qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyVare(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }

    }

    public void undoQty(String vnavn, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllVarer();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnavn().equals(vnavn)) {
                vl.get(i).setQty(vl.get(i).getQty() + qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyVare(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void ordreAfslut(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Afsluttet");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrdre(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void ordrePaabegynd(int knummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        for (int i = 0; i < ol.size(); i++) {
            if (knummer == ol.get(i).getKnummer()) {
                ol.get(i).setStatus("Påbegyndt");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrdre(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public boolean checkQty(int vnummer, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllVarer();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty()) {
                return true;
            }
        }
        return false;
    }

    public void gemLager(int vnummer, String vnavn, int qty, double pris, int aktiv, ArrayList<Del> vd) {
        if (getRediger() == true) {

            Vare vare = new Vare(vnummer, vnavn, qty, pris, aktiv);
            for (int i = 0; i < vd.size(); i++) {
                vare.addDel(vd.get(i));
            }
            updateVare(vare);
        } else {
            vnummer = getNextVnummer();
            Vare vare = new Vare(vnummer, vnavn, qty, pris, aktiv);
            for (int i = 0; i < vd.size(); i++) {
                vare.addDel(vd.get(i));
            }
            tilføjVare(vare);
        }
    }

    public Vare[] updateList1(int levYear, int levMonth, int levDay, int retYear, int retMonth, int retDay, ArrayList<Ordre> l3, ArrayList<Vare> l2) {
        ArrayList<Vare> vl = getAllRessources();
        Vare[] va = new Vare[vl.size()];
        for (int i = 0; i < vl.size(); i++) {
            for (int j = 0; j < l3.size(); j++) {
                Ordre o = l3.get(j);
                if (getRediger() == false) {
                    int oLevYear = Integer.parseInt(o.getLevering().substring(6, 10));
                    int oLevMonth = Integer.parseInt(o.getLevering().substring(3, 5));
                    int oLevDay = Integer.parseInt(o.getLevering().substring(0, 2));
                    int oRetYear = Integer.parseInt(o.getReturnering().substring(6, 10));
                    int oRetMonth = Integer.parseInt(o.getReturnering().substring(3, 5));
                    int oRetDay = Integer.parseInt(o.getReturnering().substring(0, 2));
                    for (int k = 0; k < o.getOd().size(); k++) {
                        if ((levDay <= oLevDay || levDay <= oRetDay) && (retDay >= oRetDay || retDay >= oLevDay)) {
                            if ((levMonth <= oLevMonth || levMonth <= oRetMonth) && (retMonth >= oRetMonth || retMonth >= oLevMonth)) {
                                if ((levYear <= oLevYear || levYear <= oRetYear) && (retYear >= oRetYear || retYear >= oLevYear)) {
                                    if (vl.get(i).getVnummer() == o.getOd().get(k).getVnummer()) {
                                        vl.get(i).setQty(vl.get(i).getQty() - o.getOd().get(k).getMaengde());
                                    }
                                }
                            }

                        }
                    }
                } else {
                    if (currentOrder.getOnummer() != o.getOnummer()) {
                        int oLevYear = Integer.parseInt(o.getLevering().substring(6, 10));
                        int oLevMonth = Integer.parseInt(o.getLevering().substring(3, 5));
                        int oLevDay = Integer.parseInt(o.getLevering().substring(0, 2));
                        int oRetYear = Integer.parseInt(o.getReturnering().substring(6, 10));
                        int oRetMonth = Integer.parseInt(o.getReturnering().substring(3, 5));
                        int oRetDay = Integer.parseInt(o.getReturnering().substring(0, 2));
                        for (int k = 0; k < o.getOd().size(); k++) {
                            if ((levDay <= oLevDay || levDay <= oRetDay) && (retDay >= oRetDay || retDay >= oLevDay)) {
                                if ((levMonth <= oLevMonth || levMonth <= oRetMonth) && (retMonth >= oRetMonth || retMonth >= oLevMonth)) {
                                    if ((levYear <= oLevYear || levYear <= oRetYear) && (retYear >= oRetYear || retYear >= oLevYear)) {
                                        if (vl.get(i).getVnummer() == o.getOd().get(k).getVnummer()) {
                                            vl.get(i).setQty(vl.get(i).getQty() - o.getOd().get(k).getMaengde());
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            va[i] = vl.get(i);
        }
        Vare vare;
        if (!l2.isEmpty()) {
            for (int i = 0; i < l2.size(); i++) {
                vare = (Vare) l2.get(i);
                for (int j = 0; j < va.length; j++) {
                    if (vare.getVnummer() == va[j].getVnummer()) {
                        va[j].setQty(va[j].getQty() - vare.getQty());
                    }
                }
            }
        }
        return va;
    }

    public boolean gennemførOrdrer(boolean afBool, String kunde, double pris, double rabat, double depositum, String tidLev, String tidRet, String lev, String ret, ArrayList<Odetaljer> odetaljer, String kommentar, ArrayList<DelOrdre> delordre) {
        boolean result = false;
        ArrayList<Kunde> kunder = getAllCustomers();
        int kno = 0;
        String afhentning;
        if (afBool) {
            afhentning = "Afhentes af kunden";
        } else {
            afhentning = "Leveres af os";
        }
        for (int i = 0; i < kunder.size(); i++) {
            if (kunde.equals(kunder.get(i).getNavn())) {
                kno = kunder.get(i).getKnummer();
            }
        }
        if (currentOrder == null) {
            if (afBool) {
                addNewOrder(kno, pris, rabat, depositum, "", "", afhentning, "Påbegyndt", lev, ret, odetaljer, kommentar, delordre);
            } else {
                addNewOrder(kno, pris, rabat, depositum, tidLev, tidRet, afhentning, "Påbegyndt", lev, ret, odetaljer, kommentar, delordre);
            }
            result = true;
        } else if (currentOrder != null) {
            if (afBool) {
                updateOrder(kno, pris, rabat, depositum, "", "", afhentning, currentOrder.getStatus(), lev, ret, odetaljer, delordre);
            } else {
                updateOrder(kno, pris, rabat, depositum, tidLev, tidRet, afhentning, currentOrder.getStatus(), lev, ret, odetaljer, delordre);
            }
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    private int partitionVare(Vare[] array, int left, int right, int pivotIndex) {

        Vare pivotValue = array[pivotIndex];

        array[pivotIndex] = array[right];
        array[right] = pivotValue;

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i].getVnavn().compareTo(pivotValue.getVnavn()) < 0) {
                Vare temp = array[i];
                array[i] = array[storeIndex];
                array[storeIndex] = temp;
                storeIndex++;
            }
        }
        Vare temp = array[storeIndex];
        array[storeIndex] = array[right];
        array[right] = temp;

        return storeIndex;
    }

    public Vare[] quickSortVare(Vare[] array, int left, int right) {

        if (left < right) {
            int pivotIndex = (left + right) / 2;

            int pivotNewIndex = partitionVare(array, left, right, pivotIndex);

            quickSortVare(array, left, pivotNewIndex - 1);

            quickSortVare(array, pivotNewIndex + 1, right);
        }
        return array;
    }

    public void updateVare(Vare vare) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerDirtyVare(vare);
        dbFacade.commitBusinessTransaction();
    }

    public void tilføjKunder(Kunde kunde) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerDirtyKunde(kunde);
        dbFacade.commitBusinessTransaction();
    }

    public void updateDelOrdre(DelOrdre delordre) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerDirtyDelOrdre(delordre);
        dbFacade.commitBusinessTransaction();
    }

    private int partitionKunde(Kunde[] array, int left, int right, int pivotIndex) {

        Kunde pivotValue = array[pivotIndex];

        array[pivotIndex] = array[right];
        array[right] = pivotValue;

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i].getNavn().compareTo(pivotValue.getNavn()) < 0) {
                Kunde temp = array[i];
                array[i] = array[storeIndex];
                array[storeIndex] = temp;
                storeIndex++;
            }
        }
        Kunde temp = array[storeIndex];
        array[storeIndex] = array[right];
        array[right] = temp;

        return storeIndex;
    }

    public Kunde[] quickSortKunde(Kunde[] array, int left, int right) {

        if (left < right) {
            int pivotIndex = (left + right) / 2;

            int pivotNewIndex = partitionKunde(array, left, right, pivotIndex);

            quickSortKunde(array, left, pivotNewIndex - 1);

            quickSortKunde(array, pivotNewIndex + 1, right);
        }
        return array;
    }

    private int partitionOrdre(Ordre[] array, int left, int right, int pivotIndex) {

        Ordre pivotValue = array[pivotIndex];

        array[pivotIndex] = array[right];
        array[right] = pivotValue;

        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (array[i].getOnummer() < pivotValue.getOnummer()) {
                Ordre temp = array[i];
                array[i] = array[storeIndex];
                array[storeIndex] = temp;
                storeIndex++;
            }
        }
        Ordre temp = array[storeIndex];
        array[storeIndex] = array[right];
        array[right] = temp;

        return storeIndex;
    }

    public Ordre[] quickSortOrdre(Ordre[] array, int left, int right) {

        if (left < right) {
            int pivotIndex = (left + right) / 2;

            int pivotNewIndex = partitionOrdre(array, left, right, pivotIndex);

            quickSortOrdre(array, left, pivotNewIndex - 1);

            quickSortOrdre(array, pivotNewIndex + 1, right);
        }
        return array;
    }

    public Vare getVare(int vnummer) {
        currentVare = dbFacade.getVare(vnummer);
        return currentVare;
    }

    public Kunde getKunde(int knummer) {
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentKunde = dbFacade.getKunde(knummer);
        return currentKunde;
    }

    public Postnummer getPostnummer(int postnr) {
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentPostnummer = dbFacade.getPostnummer(postnr);
        return currentPostnummer;
    }

    public ArrayList<Ordre> getAllOrdres() {
        ArrayList<Ordre> ol = dbFacade.getAllOrdrer();
        return ol;
    }

    public ArrayList<Kunde> getAllCustomers() {
        ArrayList<Kunde> kl = dbFacade.getAllKunder();
        return kl;
    }

    public ArrayList<Vare> getAllRessources() {
        ArrayList<Vare> vl = dbFacade.getAllVarer();
        return vl;
    }

    public void gemKunde(String firma, String navn, String adresse, int postnummer, int telefonnummer, int knummer) {
        if (getRediger() == true) {
            Kunde kunde = new Kunde(knummer, firma, navn, adresse, postnummer, telefonnummer);
            tilføjKunder(kunde);
            redigerFalse();
        }
        if (getRediger() == false) {
            addNewKunde(firma, navn, adresse, postnummer, telefonnummer);
        }
    }

    public void pdfOrdre() {
        ArrayList<Odetaljer> odetaljeArray = currentOrder.getOd();
        ArrayList<Vare> vareArray = new ArrayList<>();
        for (int i = 0; i < odetaljeArray.size(); i++) {
            Vare vare = getVare(odetaljeArray.get(i).getVnummer());
            vare.setQty(odetaljeArray.get(i).getMaengde());
            vareArray.add(vare);
        }
        Kunde kunde = getKunde(currentOrder.getKnummer());
        Postnummer postnummer = getPostnummer(currentKunde.getPostnummer());

        PDF pdf = new PDF();
        try {
            pdf.pdfOrdre(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
        } catch (DocumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pdfTilbud() {
        ArrayList<Odetaljer> odetaljeArray = currentOrder.getOd();
        ArrayList<Vare> vareArray = new ArrayList<>();
        for (int i = 0; i < odetaljeArray.size(); i++) {
            Vare vare = getVare(odetaljeArray.get(i).getVnummer());
            vare.setQty(odetaljeArray.get(i).getMaengde());
            vareArray.add(vare);
        }
        Kunde kunde = getKunde(currentOrder.getKnummer());
        Postnummer postnummer = getPostnummer(currentKunde.getPostnummer());

        PDF pdf = new PDF();
        try {
            pdf.pdfTilbud(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
        } catch (DocumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PDFPakkeliste() {
        ArrayList<Odetaljer> odetaljeArray = currentOrder.getOd();
        ArrayList<Vare> vareArray = new ArrayList<>();
        for (int i = 0; i < odetaljeArray.size(); i++) {
            Vare vare = getVare(odetaljeArray.get(i).getVnummer());
            vare.setQty(odetaljeArray.get(i).getMaengde());
            vareArray.add(vare);
        }
        Kunde kunde = getKunde(currentOrder.getKnummer());
        Postnummer postnummer = getPostnummer(currentKunde.getPostnummer());

        PDF pdf = new PDF();
        try {
            pdf.pdfPakkeliste(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
        } catch (DocumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void pdfFaktura() {
        ArrayList<Odetaljer> odetaljeArray = currentOrder.getOd();
        ArrayList<Vare> vareArray = new ArrayList<>();
        for (int i = 0; i < odetaljeArray.size(); i++) {
            Vare vare = getVare(odetaljeArray.get(i).getVnummer());
            vare.setQty(odetaljeArray.get(i).getMaengde());
            vareArray.add(vare);
        }
        Kunde kunde = getKunde(currentOrder.getKnummer());
        Postnummer postnummer = getPostnummer(currentKunde.getPostnummer());

        PDF pdf = new PDF();
        try {
            pdf.pdfFaktura(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
        } catch (DocumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void releaseConnection() {
        dbFacade.releaseConnection();
    }

    public void tilføjVare(Vare vare) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerNewVare(vare);
        dbFacade.commitBusinessTransaction();
    }

    public boolean redigerTrue() {
        return rediger = true;
    }

    public boolean redigerFalse() {
        return rediger = false;
    }

    public boolean getRediger() {
        return rediger;
    }
}
