package dataSource;

import java.sql.*;
import java.util.ArrayList;
import semesterprojekt.*;

//==	facade to the Data Source Layer
//	encapsulates connection handling
//	implemented as a Singleton to restrict the number of Unit of Work objects to 1.
//	2010/hau
public class DBFacade {

    private UnitOfWorkProcessOrder uow;
    ;
	  
	  //=====	Singleton
	  
	  private static DBFacade instance;

    private DBFacade() {
    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }

    //======	Methods to retrieve data 
    public Ordre getOrder(int ono) {
        Connection con = null;
        Ordre o = null;
        try {
            con = getConnection();
            o = new OrderMapper().getOrder(ono, con);
        } finally {
            releaseConnection(con);
        }
        return o;
    }
    
    public Vare getVare(int vnummer) {
        Connection con = null;
        Vare v = null;
        try {
            con = getConnection();
            v = new OrderMapper().getVare(con, vnummer);
        } finally {
            releaseConnection(con);
        }
        return v;
    }
    
    public ArrayList<Ordre> getAllOrdres(){
        Connection con = null;
        ArrayList<Ordre> o = null;
        try {
            con = getConnection();
            o = new OrderMapper().getAllOrders(con);
        } finally {
            releaseConnection(con);
        }
        return o;
    }
    
    public ArrayList<Kunde> getAllCustumers(){
        Connection con = null;
        ArrayList<Kunde> o = null;
        try {
            con = getConnection();
            o = new OrderMapper().getAllCostumers(con);
        } finally {
            releaseConnection(con);
        }
        return o;
    }
    
    public ArrayList<Vare> getAllRessources(){
        Connection con = null;
        ArrayList<Vare> o = null;
        try {
            con = getConnection();
            o = new OrderMapper().getAllRessources(con);
        } finally {
            releaseConnection(con);
        }
        return o;
    }

    public int getNextOrderNo() {
        Connection con = null;
        int nextOno = 0;
        try {
            con = getConnection();
            nextOno = new OrderMapper().getNextOrderNo(con);
        } finally {
            releaseConnection(con);
        }
        return nextOno;
    }

    //=====	Methods to register changes	in UnitOfWork  
    public void registerNewOrder(Ordre o) {
        if (uow != null) {
            uow.registerNewOrder(o);
        }
    }
    
    public void registerNewKunde(Kunde k) {
        if (uow != null) {
            uow.registerNewCustomer(k);
        }
    }

    public void registerDirtyOrder(Ordre o) {
        if (uow != null) {
            uow.registerDirtyOrder(o);
        }
    }
    
    public void registerNewRessource(Vare v) {
        if (uow != null) {
            uow.registerNewRessource(v);
        }
    }
    
    public void registerDirtyRessource(Vare v){
        if (uow != null) {
            uow.registerDirtyRessource(v);
        }
    }
    
    public void registerDirtyCustomer(Kunde k){
        if (uow != null) {
            uow.registerDirtyCustomer(k);
        }
    }

    public void registerNewOrderDetail(Odetaljer od) {
        if (uow != null) {
            uow.registerNewOrderDetail(od);
        }
    }
    
    public void registerDirtyOrderDetail(Odetaljer od){
        if (uow != null) {
           uow.registerDirtyOrderDetail(od);
        }
    }

    //=== Methods to handle business transactions
    //=====	Ignore changes after last commit
    public void startNewBusinessTransaction() {
        uow = new UnitOfWorkProcessOrder();
    }

    //=====	Save all changes
    public boolean commitBusinessTransaction() {
        boolean status = false;
        if (uow != null) {
            Connection con = null;
            try {
                con = getConnection();
                status = uow.commit(con);
            } catch (Exception e) {
                System.out.println("Fail in DBFacade - commitBusinessTransaction");
                System.err.println(e);
            } finally {
                releaseConnection(con);
            }
            uow = null;
        }
        return status;
    }
    
    //=== delete order
    public boolean deleteOrder(Ordre o) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedOrder(o);
        }
        return status;
    }
    
    public boolean deleteCustomer(Kunde k) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedCustomer(k);
        }
        return status;
    }
    
    public boolean deleteOdetail(Odetaljer od) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedOdetail(od);
        }
        return status;
    }
    
    public boolean deleteRessource(Vare v) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedRessource(v);
        }
        return status;
    }

    //=== connection specifics
    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@delfi.lyngbyes.dk:1521:KNORD", "cphmb15", "cphmb15");
        } catch (Exception e) {
            System.out.println("fejl i DBFacade.getConnection()");
            System.out.println(e);
        }
        return con;
    }

    private void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
