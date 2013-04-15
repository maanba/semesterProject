/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
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
public class PDF {
    
    
    public void PdfOrdre(Ordre currentOrder, Kunde kunde, ArrayList<Odetaljer> odetaljeArray, ArrayList<Vare> vareArray, Postnummer postnummer) throws DocumentException, FileNotFoundException {
        
        int postnr = kunde.getPostnummer();
        String adresse = kunde.getAdresse();
        String navn = "";
        String kontaktperson = "";
        if(kunde.getFirma() == null){
        navn = kunde.getNavn();
        }
        else {
            navn = kunde.getFirma();
            kontaktperson = kunde.getNavn();
        }
        
        String temp = kunde.getTelefonnummer() + "";
        String telefonnummer = temp.substring(0, 2) + " ";
        telefonnummer += temp.substring(2, 4) + " ";
        telefonnummer += temp.substring(4, 6) + " ";
        telefonnummer += temp.substring(6, 8);
        String by = postnummer.getBy();        
        
        int onummer = currentOrder.getOnummer();
        int knummer = currentOrder.getKnummer();
        String status = currentOrder.getStatus() + "";
        
        double pris = currentOrder.getPris();
        double depositum = currentOrder.getDepositum();

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
            table1.addCell(new Phrase(onummer + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(adresse, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Kundenr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(knummer + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(postnr + " " + by, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Status:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(status, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(telefonnummer, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase("            Dato:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
            Date date = new Date();
            table1.addCell(new Phrase(dateFormat.format(date), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(kontaktperson, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
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
            table2.addCell(new Phrase("Depositum:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + depositum, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            
            table2.addCell(" ");
            table2.addCell(new Phrase("Sum eksl. moms:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            sum = sum + depositum;
            table2.addCell(new Phrase("   " + sum, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(new Phrase("Rabat:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + (pris - sum), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(new Phrase("At betale:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + (pris + ((pris/100) * 25)), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
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
        openPDF(currentOrder);
    }
    
    public void PdfFaktura(Ordre currentOrder, Kunde kunde, ArrayList<Odetaljer> odetaljeArray, ArrayList<Vare> vareArray, Postnummer postnummer) throws DocumentException, FileNotFoundException {
        
        int postnr = kunde.getPostnummer();
        String adresse = kunde.getAdresse();
        String navn = "";
        String kontaktperson = "";
        if(kunde.getFirma() == null){
        navn = kunde.getNavn();
        }
        else {
            navn = kunde.getFirma();
            kontaktperson = kunde.getNavn();
        }
        
        String temp = kunde.getTelefonnummer() + "";
        String telefonnummer = temp.substring(0, 2) + " ";
        telefonnummer += temp.substring(2, 4) + " ";
        telefonnummer += temp.substring(4, 6) + " ";
        telefonnummer += temp.substring(6, 8);
        String by = postnummer.getBy();
        
        int fnummer = currentOrder.getFnummer();
        int knummer = currentOrder.getKnummer();
        String status = currentOrder.getStatus() + "";
        
        double pris = currentOrder.getPris();
        double depositum = currentOrder.getDepositum();

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
            table1.addCell(new Phrase("            Fakturanr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(fnummer + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(adresse, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Kundenr:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(knummer + "", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(postnr + " " + by, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase("            Status:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(status, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(telefonnummer, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase("            Dato:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM-yyyy");
            Date date = new Date();
            table1.addCell(new Phrase(dateFormat.format(date), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table1.addCell(new Phrase(kontaktperson, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table1.addCell(new Phrase(" ", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
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
            table2.addCell(new Phrase("Depositum:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + depositum, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(" ");
            
            table2.addCell(" ");
            table2.addCell(new Phrase("Sum eksl. moms:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            sum = sum + depositum;
            table2.addCell(new Phrase("   " + sum, FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(new Phrase("Rabat:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + (pris - sum), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
            table2.addCell(" ");
            table2.addCell(new Phrase("At betale:", FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            table2.addCell(" ");
            table2.addCell(" ");
            table2.addCell(new Phrase("   " + (pris + ((pris/100) * 25)), FontFactory.getFont(FontFactory.TIMES_ROMAN, 14)));
            
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
        openPDF(currentOrder);
    }
    
    public void openPDF(Ordre currentOrder) {
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
