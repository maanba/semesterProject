/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

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
        currentOrder = dbFacade.getOrder(onummer);
        return currentOrder;
    }

    public Ordre getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Ordre currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Ordre addNewOrder(int knummer, double pris, double rabat, double depositum, String tidLev, String tidRet, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        int montører = 0;
        String modtaget = dateFormat.format(date);
        dbFacade.startNewBusinessTransaction();
        int newOrderNo = dbFacade.getNextOrderNo();// DB-generated unique ID
        if (newOrderNo != 0) {
            processingOrder = true;
            for (int i = 0; i < odetaljer.size(); i++) {
                odetaljer.get(i).setOnummer(newOrderNo);
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
            currentOrder = new Ordre(newOrderNo, 0, knummer, pris, rabat, depositum, tidLev, tidRet, afhentning, montører, status, modtaget, levering, returnering, 0);
            dbFacade.registerNewOrder(currentOrder);
            for (int i = 0; i < odetaljer.size(); i++) {
                dbFacade.registerNewOrderDetail(odetaljer.get(i));
            }
            dbFacade.commitBusinessTransaction();
        } else {
            processingOrder = false;
        }
        currentOrder = null;
        return currentOrder;
    }

    public void updateOrder(int knummer, double pris, double rabat, double depositum, String tidLev, String tidRet, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer) {
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
        dbFacade.deleteOdetail(currentOrder.getOnummer());
        dbFacade.registerDirtyOrder(currentOrder);
        for (int i = 0; i < odetaljer.size(); i++) {
            dbFacade.registerNewOrderDetail(odetaljer.get(i));
        }
        dbFacade.commitBusinessTransaction();
        currentOrder = null;
    }

    public boolean deleteOrder(Ordre o) {
        boolean result = false;
        dbFacade.startNewBusinessTransaction();
        result = dbFacade.deleteOrder(o);
        dbFacade.commitBusinessTransaction();
        return result;
    }

    public void addOrderFakturaNummer(Ordre o) {
        setCurrentOrder(o);
        ArrayList<Odetaljer> odetaljer = o.getOd();
        currentOrder.setFnummer(dbFacade.getNextFNo());

        dbFacade.startNewBusinessTransaction();
        for (int i = 0; i < odetaljer.size(); i++) {
            odetaljer.get(i).setOnummer(currentOrder.getOnummer());
        }
        dbFacade.deleteOdetail(currentOrder.getOnummer());
        dbFacade.registerDirtyOrder(currentOrder);
        for (int i = 0; i < odetaljer.size(); i++) {
            dbFacade.registerNewOrderDetail(odetaljer.get(i));
        }
        dbFacade.commitBusinessTransaction();
        currentOrder = null;
    }

    public Ordre changeCnoForOrder(int knummer) {
        if (processingOrder) {
            currentOrder.setKnummer(knummer);
            dbFacade.registerDirtyOrder(currentOrder);
        }
        return currentOrder;
    }

    public void ordreFaktureret(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Faktureret");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrder(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void bekraeftOrdre(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        ArrayList<Vare> vl = dbFacade.getAllRessources();
        for (int i = 0; i < ol.size(); i++) 
        {
            if (onummer == ol.get(i).getOnummer()) 
            {
                ol.get(i).setStatus("Bekræftet ordre");
                for (int j = 0; j < ol.get(i).getOd().size(); j++) // gældende odetaljers størrelse
                {
                    int ovnummer = ol.get(i).getOd().get(j).getVnummer();    // alle odetaljers (vares) vnummer
                    for (int k = 0; k < vl.size(); k++) // varelistes størrelse
                    {
                        for (int l = 0; l < vl.get(k).getDel().size(); l++) // antal dele i gældende vareliste
                        {
                            if (ovnummer == vl.get(k).getDel().get(l).getVnummer())// vnummer på dele i vareliste
                            { 
                                vl.get(k).getDel().get(l).setStatus(0);
                                dbFacade.startNewBusinessTransaction();
                                dbFacade.registerDirtyOrder(ol.get(i));
                                dbFacade.registerDirtyRessource(vl.get(k));
                                dbFacade.commitBusinessTransaction();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void ordreTilbud(int onummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        for (int i = 0; i < ol.size(); i++) {
            if (onummer == ol.get(i).getOnummer()) {
                ol.get(i).setStatus("Tilbud");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrder(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public boolean addOrderDetail(int vnummer, int qty) {
        boolean status = false;
        if (processingOrder) {
            Odetaljer od = new Odetaljer(currentOrder.getOnummer(), vnummer, qty);
            currentOrder.addOd(od);
            dbFacade.registerNewOrderDetail(od);
            status = true;
        }
        return status;
    }

    public void addNewKunde(String firma, String navn, String adresse, int postnummer, int telefonnummer) {

        dbFacade.startNewBusinessTransaction();
        int newKnummer = dbFacade.getNextKnummer();
        if (newKnummer != 0) {
            processingOrder = true;
            currentKunde = new Kunde(newKnummer, firma, navn, adresse, postnummer, telefonnummer);
            dbFacade.registerNewCustomer(currentKunde);
            dbFacade.commitBusinessTransaction();
        } else {
            processingOrder = false;
        }
        currentKunde = null;
    }

    public void updateKunde(int knummer, String firma, String navn, String adresse, int postnummer, int telefonnummer) {

        dbFacade.startNewBusinessTransaction();



        dbFacade.registerDirtyCustomer(currentKunde);
        dbFacade.commitBusinessTransaction();

        currentKunde = null;
    }

    public void addRessource(int vnummer, String vnavn, int qty, double pris, int aktiv) {
        Vare ressource = new Vare(vnummer, vnavn, qty, pris, aktiv);
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerNewRessource(ressource);
        dbFacade.commitBusinessTransaction();

        System.out.println(dbFacade.getAllCustumers());
    }

    public int getNextVnummer() {
        return dbFacade.getNextVnummer();
    }

    public int getNextKnummer() {
        return dbFacade.getNextKnummer();
    }

    public void deleteVare(Vare v) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.deleteRessource(v);
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
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty()) {
                vl.get(i).setQty(vl.get(i).getQty() - qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyRessource(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }

    }

    public void undoQty(String vnavn, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnavn().equals(vnavn)) {
                vl.get(i).setQty(vl.get(i).getQty() + qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyRessource(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void ordreAfslut(int knummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        for (int i = 0; i < ol.size(); i++) {
            if (knummer == ol.get(i).getKnummer()) {
                ol.get(i).setStatus("Afsluttet");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrder(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public void ordrePaabegynd(int knummer) {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        for (int i = 0; i < ol.size(); i++) {
            if (knummer == ol.get(i).getKnummer()) {
                ol.get(i).setStatus("Påbegyndt");
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyOrder(ol.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public boolean checkQty(int vnummer, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty()) {
                return true;
            }
        }
        return false;
    }

    public Boolean gennemførOrdrer(boolean afBool, String kunde, double pris, double rabat, double depositum, String tidLev, String tidRet, String lev, String ret, ArrayList<Odetaljer> odetaljer) {
        boolean result = false;
        ArrayList<Kunde> kunder = getAllCostumers();
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
                addNewOrder(kno, pris, rabat, depositum, "", "", afhentning, "Påbegyndt", lev, ret, odetaljer);
            } else {
                addNewOrder(kno, pris, rabat, depositum, tidLev, tidRet, afhentning, "Påbegyndt", lev, ret, odetaljer);
            }
        } else if (currentOrder != null) {
            if (afBool) {
                updateOrder(kno, pris, rabat, depositum, "", "", afhentning, currentOrder.getStatus(), lev, ret, odetaljer);
            } else {
                updateOrder(kno, pris, rabat, depositum, tidLev, tidRet, afhentning, currentOrder.getStatus(), lev, ret, odetaljer);
            }
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
        dbFacade.registerDirtyRessource(vare);
        dbFacade.commitBusinessTransaction();
    }

    public void updateKunde(Kunde kunde) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerDirtyCustomer(kunde);
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
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        return ol;
    }

    public ArrayList<Kunde> getAllCostumers() {
        ArrayList<Kunde> kl = dbFacade.getAllCustumers();
        return kl;
    }

    public ArrayList<Vare> getAllRessources() {
        ArrayList<Vare> vl = dbFacade.getAllRessources();
        return vl;
    }

    public void setSelectedOrdre(Ordre ordre) {
        this.currentOrder = ordre;
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
            pdf.PdfOrdre(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
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

    public void pdfPakkeliste() {
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
            pdf.PdfFaktura(currentOrder, kunde, odetaljeArray, vareArray, postnummer);
        } catch (DocumentException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void releaseConnection() {
        dbFacade.releaseConnection();
    }

    public void addVare(Vare vare) {
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerNewRessource(vare);
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


