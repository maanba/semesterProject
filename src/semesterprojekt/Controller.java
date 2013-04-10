/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dataSource.DBFacade;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Controller {

    private boolean processingOrder;	// state of business transaction
    private Ordre currentOrder;       	// Order in focus
    private Vare currentVare;
    private Kunde currentKunde;
    private DBFacade dbFacade;
    private Ordre selectedOrdre;

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

    public Ordre createNewOrder(int knummer, double pris, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer) {
        if (processingOrder) {
            return null;
        }
        dbFacade.startNewBusinessTransaction();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String modtaget = dateFormat.format(date);
        dbFacade.startNewBusinessTransaction();
        int newOrderNo = dbFacade.getNextOrderNo();// DB-generated unique ID
        if (newOrderNo != 0) {
            processingOrder = true;
            for (int i = 0; i < odetaljer.size(); i++) {
                odetaljer.get(i).setOnummer(newOrderNo);
            }
            currentOrder = new Ordre(newOrderNo, knummer, pris, afhentning, status, modtaget, levering, returnering, 0);
            dbFacade.registerNewOrder(currentOrder);
            for (int i = 0; i < odetaljer.size(); i++) {
                dbFacade.registerNewOrderDetail(odetaljer.get(i));
            }
            dbFacade.commitBusinessTransaction();
        } else {
            processingOrder = false;
            currentOrder = null;
        }
        return currentOrder;
    }

    public Ordre changeCnoForOrder(int knummer) {
        if (processingOrder) {
            currentOrder.setKnummer(knummer);
            dbFacade.registerDirtyOrder(currentOrder);
        }
        return currentOrder;
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

//    public String getOrderDetailsToString() {
//        if (processingOrder) {
//            return currentOrder.ordredetaljertoString();
//        } else {
//            return null;
//        }
//    }
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
//    public int getQty(int vnummer, int qty){
//        ArrayList<Vare> vl = dbFacade.getAllRessources();
//        
//        for (int i = 0; i < vl.size(); i++){
//            if(vl.get(i).getVnummer() == vnummer){
//                return vl.get(i).getQty();
//            }
//        }
//        return 
//    }

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

    public boolean checkQty(int vnummer, int qty) {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++) {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty()) {
                return true;
            }
        }
        return false;

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
        if (processingOrder) {
            return null;
        }
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentVare = dbFacade.getVare(vnummer);
        return currentVare;
    }

    public Kunde getKunde(int knummer) {
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentKunde = dbFacade.getKunde(knummer);
        return currentKunde;
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
        this.selectedOrdre = ordre;
    }

    public void PDF() throws DocumentException, FileNotFoundException {
        ArrayList<Odetaljer> vareListeArray = selectedOrdre.getOd();

        String vareListe = "";
        for (int i = 0; i < vareListeArray.size(); i++) {
            vareListe += "Varenummer: " + vareListeArray.get(i).getVnummer() + " " + "Mængde: " + vareListeArray.get(i).getMaengde() + "\n";
            System.out.println(vareListe);
        }

        String onummer = selectedOrdre.getOnummer() + "";
        String knummer = selectedOrdre.getKnummer() + "";
        String pris = selectedOrdre.getPris() + " kroner";
        String afhentning = selectedOrdre.getAfhentning() + "";
        String status = selectedOrdre.getStatus() + "";
        String modtaget = selectedOrdre.getModtaget() + "";
        String levering = selectedOrdre.getLevering() + "";
        String returnering = selectedOrdre.getReturnering() + "";
        String ver = selectedOrdre.getVer() + "";

        String result = "Ordre nummer: " + onummer + "\n"
                + "Kunde nummer: " + "\t" + knummer + "\n"
                + "Pris: " + pris + "\n"
                + "Afhentning: " + afhentning + "\n"
                + "Status: " + status + "\n"
                + "Modtaget: " + modtaget + "\n"
                + "Levering: " + levering + "\n"
                + "Returnering: " + returnering + "\n"
                + "Vareliste: " + vareListe;

        Document document = new Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(selectedOrdre.getOnummer() + ".pdf"));
        document.open();
        document.add(new Paragraph(result));
        document.close();
    }
}
