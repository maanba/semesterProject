package dataSource;

import java.sql.*;
import java.util.ArrayList;
import semesterprojekt.*;

public class UnitOfWorkProcessOrder {

    private ArrayList<Ordre> newOrders;
    private ArrayList<Ordre> dirtyOrders;
    private ArrayList<Kunde> newCustomer;
    private ArrayList<Kunde> dirtyCustomer;
    private ArrayList<Odetaljer> newOrderDetails;
    private ArrayList<Odetaljer> dirtyOrderDetails;
    private ArrayList<Vare> newRessources;
    private ArrayList<Vare> dirtyRessources;
    private ArrayList<Ordre> deletedOrders;
    private ArrayList<Odetaljer> deletedOdetails;
    private ArrayList<Kunde> deletedCustomers;
    private ArrayList<Vare> deletedRessources;

    public UnitOfWorkProcessOrder() {
        newOrders = new ArrayList<>(); // will never exceed 1 element
        dirtyOrders = new ArrayList<Ordre>(); // will never exceed 1 element
        newCustomer = new ArrayList<Kunde>();
        dirtyCustomer = new ArrayList<Kunde>();
        newOrderDetails = new ArrayList<Odetaljer>();
        dirtyOrderDetails = new ArrayList<Odetaljer>();
        newRessources = new ArrayList<Vare>();
        dirtyRessources = new ArrayList<Vare>();
        deletedOrders = new ArrayList<Ordre>();
        deletedOdetails = new ArrayList<Odetaljer>();
        deletedCustomers = new ArrayList<Kunde>();
        deletedRessources = new ArrayList<Vare>();
    }
    //====== Methods to register changes ==========================

    public void registerNewOrder(Ordre o) {
        if (!newOrders.contains(o) && // if not allready registered in any list
                !dirtyOrders.contains(o)) {
            newOrders.add(o);
        }
    }

    public void registerDirtyOrder(Ordre o) {
        if (!newOrders.contains(o) && // if not allready registered in any list
                !dirtyOrders.contains(o)) {
            dirtyOrders.add(o);
        }
    }
    
    public void registerDirtyCustomer(Kunde k){
        if (!newCustomer.contains(k) && // if not allready registered in any list
                !dirtyCustomer.contains(k)) {
            dirtyCustomer.add(k);
        }
    }
    
    public void registerNewCustomer(Kunde k) {
        if (!newCustomer.contains(k)) {
            newCustomer.add(k);
        }
    }

    public void registerNewOrderDetail(Odetaljer od) {
        if (!newOrderDetails.contains(od)) // if not allready registered in list
        {
            newOrderDetails.add(od);
        }
    }
    
    public void registerDirtyOrderDetail (Odetaljer od) {
        if (!dirtyOrderDetails.contains(od)){
            dirtyOrderDetails.add(od);
        }
    }
    
    public void registerNewRessource(Vare v) {
        if (!newRessources.contains(v) && // if not allready registered in any list
                !dirtyRessources.contains(v)) {
            newRessources.add(v);
        }
    }
    
    public void registerDirtyRessource(Vare v) {
        if (!newRessources.contains(v) && // if not allready registered in any list
                !dirtyRessources.contains(v)) {
            dirtyRessources.add(v);
        }
    }
    
    public boolean registerDeletedOrder (Ordre o){
        boolean status = false;
        if(!deletedOrders.contains(o)){
            status = true;
            deletedOrders.add(o);
        }
        return status;
    }
    
    public boolean registerDeletedOdetail (Odetaljer od){
        boolean status = false;
        if(!deletedOdetails.contains(od)){
            status = true;
            deletedOdetails.add(od);
        }
        return status;
    }
    
    public boolean registerDeletedCustomer (Kunde k){
        boolean status = false;
        if(!deletedCustomers.contains(k)){
            status = true;
            deletedCustomers.add(k);
        }
        return status;
    }
    
    public boolean registerDeletedRessource (Vare v){
        boolean status = false;
        if(!deletedRessources.contains(v)){
            status = true;
            deletedRessources.add(v);
        }
        return status;
    }

    //====== Method to save changes to DB ===============================
    public boolean commit(Connection conn) throws SQLException {
        boolean status = true;
        try {
            //=== system transaction - start
            conn.setAutoCommit(false);
            OrderMapper om = new OrderMapper();

            status = status && om.insertOrders(newOrders, conn);
            status = status && om.updateOrders(dirtyOrders, conn);
            status = status && om.insertCustomer(newCustomer, conn);
            status = status && om.updateCustomer(newCustomer, conn);
            status = status && om.insertOrderDetails(newOrderDetails, conn);
            status = status && om.updateOrderDetails(dirtyOrderDetails, conn);
            status = status && om.insertRessources(newRessources, conn);
            status = status && om.updateRessources(dirtyRessources, conn);
            status = status && om.deleteOrders(deletedOrders, conn);
            status = status && om.deleteOrderDetials(deletedOdetails, conn);
            status = status && om.deleteCustomers(deletedCustomers, conn);
            status = status && om.deleteRessources(deletedRessources, conn);
            if (!status) {
                throw new Exception("Business Transaction aborted");
            }
            //=== system transaction - end with success
            conn.commit();
        } catch (Exception e) {
            System.out.println("fail in UnitOfWork - commit()");
            System.err.println(e);
            //=== system transaction - end with roll back
            conn.rollback();
            status = false;// rettelse
        }
        return status;
    }

    //====== Methods to read from DB ===================================================
    public Ordre getOrder(int ono, Connection con) {
        Ordre o = null;;
        try {
            o = new OrderMapper().getOrder(ono, con);
        } catch (Exception e) {
            System.out.println("fail in UnitOfWork - getOrder()");
            System.err.println(e);
        }
        return o;

    }
}
