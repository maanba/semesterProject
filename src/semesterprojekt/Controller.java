/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dataSource.DBFacade;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class Controller  {

    private boolean processingOrder;	// state of business transaction
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

    public void updateOrder(int knummer, double pris, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer) {
        currentOrder.setAfhentning(afhentning);
        currentOrder.setKnummer(knummer);
        currentOrder.setLevering(levering);
        currentOrder.setPris(pris);
        currentOrder.setReturnering(returnering);
        currentOrder.setOd(odetaljer);
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
    
    
    ArrayList<Kunde> kundeArr = new ArrayList<>();
    public void addKunde(int knummer, String navn, String adresse, int postnummer, int telefonnummer)
    {
        Kunde kunde = new Kunde(knummer, navn, adresse, postnummer, telefonnummer);
        kundeArr.add(kunde);
        dbFacade.startNewBusinessTransaction();
        dbFacade.registerNewKunde(kunde);
        dbFacade.commitBusinessTransaction();
        
        System.out.println(dbFacade.getAllCustumers());
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
        dbFacade.startNewBusinessTransaction();
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

    public void PDF() throws DocumentException, FileNotFoundException {
        ArrayList<Odetaljer> odetaljeArray = currentOrder.getOd();
        ArrayList<Vare> vareArray = new ArrayList<>();
        for (int i = 0; i < odetaljeArray.size(); i++) {
            Vare vare = getVare(odetaljeArray.get(i).getVnummer());
            vare.setQty(odetaljeArray.get(i).getMaengde());
            vareArray.add(vare);
        }
        
        Kunde kunde = getKunde(currentOrder.getKnummer());
        Postnummer postnummer = getPostnummer(currentKunde.getPostnummer());
        
        int postnr = kunde.getPostnummer();
        String adresse = kunde.getAdresse();
        String navn = kunde.getNavn();
        String temp = kunde.getTelefonnummer() + "";
        String telefonnummer = temp.substring(0, 2) + " ";
        telefonnummer += temp.substring(2, 4) + " ";
        telefonnummer += temp.substring(4, 6) + " ";
        telefonnummer += temp.substring(6, 8);
        String by = postnummer.getBy();
        
        String onummer = currentOrder.getOnummer() + "";
        String knummer = currentOrder.getKnummer() + "";
        String status = currentOrder.getStatus() + "";
        
        double pris = currentOrder.getPris();
        

        String afhentning = currentOrder.getAfhentning() + "";
        String levering = currentOrder.getLevering() + "";
        String returnering = currentOrder.getReturnering() + "";
        
        String modtaget = currentOrder.getModtaget() + "";
        
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:\\" + currentOrder.getOnummer() + ".pdf"));
            document.open();
            
            PdfPTable top = new PdfPTable(1);
            top.setTotalWidth(500);
            top.setLockedWidth(true);
            top.getDefaultCell().setBorder(0);
            top.setSpacingAfter(25);
            
            top.addCell("");
            
            PdfPTable table1 = new PdfPTable(3);
            table1.setTotalWidth(500);
            table1.setLockedWidth(true);
            table1.setWidths(new float[]{5, 2, 2});
            table1.getDefaultCell().setBorder(0);
            table1.setSpacingAfter(50);
            
            table1.addCell(new Phrase(navn, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Ordrenr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(onummer, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(adresse, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Kundenr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(knummer, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(postnr + " " + by, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Status:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(status, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(telefonnummer, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase("            Dato:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
            Date date = new Date();
            table1.addCell(new Phrase(dateFormat.format(date), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            
            PdfPTable table2 = new PdfPTable(5);
            table2.setTotalWidth(500);
            table2.setLockedWidth(true);
            table2.setWidths(new float[]{1, 3, 1, 1, 1});
            table2.getDefaultCell().setBorder(0);
            table2.setSpacingAfter(100);
            
            table2.addCell(new Phrase("Varenr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(new Phrase("Beskrivelse:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(new Phrase("Pris:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell("Antal:");
            table2.addCell(new Phrase("   Beløb:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            double sum = 0;
            for (int i = 0; i < vareArray.size(); i++) {
                
                table2.addCell(new Phrase(vareArray.get(i).getVnummer() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
                table2.addCell(new Phrase(vareArray.get(i).getVnavn(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
                table2.addCell(new Phrase(vareArray.get(i).getPris() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
                table2.addCell(new Phrase(odetaljeArray.get(i).getMaengde() + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
                table2.addCell(new Phrase("   " + odetaljeArray.get(i).getMaengde() * vareArray.get(i).getPris(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
                sum = sum + odetaljeArray.get(i).getMaengde() * vareArray.get(i).getPris();
            }
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            
            table2.addCell(" ");
            table2.addCell(new Phrase("Sum:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + sum, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));

            PdfPTable table3 = new PdfPTable(3);
            table3.setTotalWidth(500);
            table3.setLockedWidth(true);
            table3.setWidths(new float[]{2, 4, 6});
            table3.getDefaultCell().setBorder(0);
            
            table3.addCell(new Phrase("Transport:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell(new Phrase(afhentning, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell("");
            table3.addCell(new Phrase("Fra dato:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell(new Phrase(levering, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell("");
            table3.addCell(new Phrase("Til dato:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell(new Phrase(returnering, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table3.addCell("");
            
            document.add(top);
            document.add(table1);
            document.add(table2);
            document.add(table3);
            document.close();
        } catch (Exception i) {
            System.out.println(i);
        }
        openPDF();
    }
    public void openPDF() {
        try {
            if ((new File("C:\\" + currentOrder.getOnummer() + ".pdf")).exists()) {
                Process p = Runtime
                        .getRuntime()
                        .exec("rundll32 url.dll,FileProtocolHandler C:\\" + currentOrder.getOnummer() + ".pdf");
                p.waitFor();
            } else {
                System.out.println("File is not exists");
            }
            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
