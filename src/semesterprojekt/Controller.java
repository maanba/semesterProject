/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterprojekt;
import dataSource.DBFacade;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**

 @author Daniel Krarup Knudsen
 */
public class Controller
{
    private boolean processingOrder;	// state of business transaction
    private Ordre currentOrder;       	// Order in focus
    private DBFacade dbFacade;

    public Controller()
    {
        dbFacade = DBFacade.getInstance();
    }

    public Ordre getOrder(int onummer)
    {
        if (processingOrder)
        {
            return null;
        }
        dbFacade.startNewBusinessTransaction();
        processingOrder = true;
        currentOrder = dbFacade.getOrder(onummer);
        return currentOrder;
    }

    public Ordre createNewOrder(int knummer, double pris, String afhentning, String status, String levering, String returnering, ArrayList<Odetaljer> odetaljer)
    {
        if (processingOrder)
        {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YY");
        Date date = new Date();
        String modtaget = dateFormat.format(date);
        dbFacade.startNewBusinessTransaction();
        int newOrderNo = dbFacade.getNextOrderNo();// DB-generated unique ID
        if (newOrderNo != 0)
        {
            processingOrder = true;
            for (int i = 0; i < odetaljer.size(); i++)
            {
                odetaljer.get(i).setOnummer(newOrderNo);
            }
            currentOrder = new Ordre(newOrderNo, knummer, pris, afhentning, status, modtaget, levering, returnering, 0);
            dbFacade.registerNewOrder(currentOrder);
            for (int i = 0; i < odetaljer.size(); i++)
            {
                dbFacade.registerNewOrderDetail(odetaljer.get(i));
            }
        }
        else
        {
            processingOrder = false;
            currentOrder = null;
        }
        return currentOrder;
    }

    public Ordre changeCnoForOrder(int knummer)
    {
        if (processingOrder)
        {
            currentOrder.setKnummer(knummer);
            dbFacade.registerDirtyOrder(currentOrder);
        }
        return currentOrder;
    }

    public boolean addOrderDetail(int vnummer, int qty)
    {
        boolean status = false;
        if (processingOrder)
        {
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
    public boolean saveOrder()
    {
        boolean status = false;
        if (processingOrder)
        {
            //== ends ongoing business transaction

            status = dbFacade.commitBusinessTransaction();
            processingOrder = false;
            currentOrder = null;
        }
        return status;
    }

    public void resetOrder()
    {
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

    public void setQty(int vnummer, int qty)
    {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++)
        {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty())
            {
                vl.get(i).setQty(vl.get(i).getQty() - qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyRessource(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }

    }

    public void undoQty(String vnavn, int qty)
    {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++)
        {
            if (vl.get(i).getVnavn().equals(vnavn))
            {
                vl.get(i).setQty(vl.get(i).getQty() + qty);
                dbFacade.startNewBusinessTransaction();
                dbFacade.registerDirtyRessource(vl.get(i));
                dbFacade.commitBusinessTransaction();
                break;
            }
        }
    }

    public boolean checkQty(int vnummer, int qty)
    {
        ArrayList<Vare> vl = dbFacade.getAllRessources();

        for (int i = 0; i < vl.size(); i++)
        {
            if (vl.get(i).getVnummer() == vnummer && qty <= vl.get(i).getQty())
            {
                return true;
            }
        }
        return false;

    }

    public ArrayList<Ordre> getAllOrdres()
    {
        ArrayList<Ordre> ol = dbFacade.getAllOrdres();
        return ol;
    }

    public ArrayList<Kunde> getAllCostumers()
    {
        ArrayList<Kunde> kl = dbFacade.getAllCustumers();
        return kl;
    }

    public ArrayList<Vare> getAllRessources()
    {
        ArrayList<Vare> vl = dbFacade.getAllRessources();
        return vl;
    }
}
