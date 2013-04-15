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
    private Connection con = null;

    private DBFacade() {
        con = getConnection();
    }

    public static DBFacade getInstance() {
        if (instance == null) {
            instance = new DBFacade();
        }
        return instance;
    }

    //======	Methods to retrieve data 
    public Ordre getOrder(int ono) {
        Ordre o = null;
        o = new OrderMapper().getOrder(ono, con);
        return o;
    }

    public Vare getVare(int vnummer) {
        Vare v = null;
        v = new OrderMapper().getVare(con, vnummer);
        return v;
    }

    public Kunde getKunde(int knummer) {
        Kunde k = null;
        k = new OrderMapper().getKunde(con, knummer);
        return k;
    }

    public Postnummer getPostnummer(int postnr) {
        Postnummer p = null;
        p = new OrderMapper().getPostnummer(con, postnr);
        return p;
    }

    public ArrayList<Ordre> getAllOrdres() {
        ArrayList<Ordre> o = null;
        o = new OrderMapper().getAllOrders(con);
        return o;
    }

    public ArrayList<Kunde> getAllCustumers() {
        ArrayList<Kunde> o = null;
        o = new OrderMapper().getAllCostumers(con);
        return o;
    }

    public ArrayList<Vare> getAllRessources() {
        ArrayList<Vare> o = null;
        o = new OrderMapper().getAllRessources(con);
        return o;
    }

    public int getNextOrderNo() {
        int nextOno = 0;
        nextOno = new OrderMapper().getNextOrderNo(con);
        return nextOno;
    }

    public int getNextFNo() {
        int nextFnummer = 0;
        nextFnummer = new OrderMapper().getNextFnummer(con);
        return nextFnummer;
    }

    //=====	Methods to register changes	in UnitOfWork  
    public void registerNewOrder(Ordre o) {
        if (uow != null) {
            uow.registerNewOrder(o);
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

    public void registerDirtyRessource(Vare v) {
        if (uow != null) {
            uow.registerDirtyRessource(v);
        }
    }

    public void registerNewCustomer(Kunde k) {
        if (uow != null) {
            uow.registerNewCustomer(k);
        }
    }

    public void registerDirtyCustomer(Kunde k) {
        if (uow != null) {
            uow.registerDirtyCustomer(k);
        }
    }

    public void registerNewOrderDetail(Odetaljer od) {
        if (uow != null) {
            uow.registerNewOrderDetail(od);
        }
    }

    public void registerDirtyOrderDetail(Odetaljer od) {
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
            try {
                status = uow.commit(con);
            } catch (Exception e) {
                System.out.println("Fail in DBFacade - commitBusinessTransaction");
                System.err.println(e);
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

    public boolean deleteOdetail(int ono) {
        boolean status = true;
        try {
            status = new OrderMapper().deleteOrderDetails(ono, con);
        } catch (SQLException ex) {
            System.out.println("fail i deleteOdetail");
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

    public void releaseConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
