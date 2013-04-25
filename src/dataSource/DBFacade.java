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

    //=====     Methods to retrieve data 
    
    public Ordre getOrdre(int ono) {
        Ordre o = null;
        try {
            o = new OrderMapper().getOrdre(ono, con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public Vare getVare(int vnummer) {
        Vare v = null;
        try {
            v = new OrderMapper().getVare(con, vnummer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    public Kunde getKunde(int knummer) {
        Kunde k = null;
        try {
            k = new OrderMapper().getKunde(con, knummer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public Postnummer getPostnummer(int postnr) {
        Postnummer p = null;
        try {
            p = new OrderMapper().getPostnummer(con, postnr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public ArrayList<Ordre> getAllOrdrer() {
        ArrayList<Ordre> o = null;
        try {
            o = new OrderMapper().getAllOrdrer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllOrders");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public ArrayList<Kunde> getAllKunder() {
        ArrayList<Kunde> o = null;
        try {
            o = new OrderMapper().getAllKunder(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllCostumers");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public ArrayList<Vare> getAllVarer() {
        ArrayList<Vare> o = null;
        try {
            o = new OrderMapper().getAllVarer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllRessources");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public int getNextOnummer() {
        int nextOno = 0;
        try {
            nextOno = new OrderMapper().getNextOnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextFnummer");
            System.out.println(e.getMessage());
        }
        return nextOno;
    }

    public int getNextVnummer() {
        int nextVno = 0;
        try {
            nextVno = new OrderMapper().getNextVnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextVnummer");
            System.out.println(e.getMessage());
        }
        return nextVno;
    }

    public int getNextKnummer() {
        int nextKno = 0;
        try {
            nextKno = new OrderMapper().getNextKnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextKnummer");
            System.out.println(e.getMessage());
        }
        return nextKno;
    }

    public int getNextFnummer() {
        int nextFnummer = 0;
        try {
            nextFnummer = new OrderMapper().getNextFnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextFnummer");
            System.out.println(e.getMessage());
        }
        return nextFnummer;
    }

    //=====     Methods to register change in UnitOfWork  
    
    public void registerNewOrdre(Ordre o) {
        if (uow != null) {
            uow.registerNewOrdre(o);
        }
    }

    public void registerDirtyOrdre(Ordre o) {
        if (uow != null) {
            uow.registerDirtyOrdre(o);
        }
    }

    public boolean deleteOrdre(Ordre o) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedOrder(o);
        }
        return status;
    }

    public void registerNewVare(Vare v) {
        if (uow != null) {
            uow.registerNewVare(v);
        }
    }

    public void registerDirtyVare(Vare v) {
        if (uow != null) {
            uow.registerDirtyVare(v);
        }
    }
    
    public boolean deleteVare(Vare v) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedVare(v);
        }
        return status;
    }

    public void registerNewKunde(Kunde k) {
        if (uow != null) {
            uow.registerNewKunde(k);
        }
    }

    public void registerDirtyKunde(Kunde k) {
        if (uow != null) {
            uow.registerDirtyKunde(k);
        }
    }
    
    public boolean deleteKunde(Kunde k) {
        boolean status = false;
        if (uow != null) {
            status = uow.registerDeletedKunde(k);
        }
        return status;
    }

    public void registerNewOdetalje(Odetaljer od) {
        if (uow != null) {
            uow.registerNewOdetalje(od);
        }
    }

    public void registerDirtyOdetalje(Odetaljer od) {
        if (uow != null) {
            uow.registerDirtyOdetalje(od);
        }
    }
    
    public boolean deleteOdetalje(Odetaljer od) {
        boolean status = false;
        
        if (uow != null) {
            status = uow.registerDeletedOdetalje(od);
        }
        return status;
    }

    //=====     Methods to handle business transactions
    
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

    //=====     Connection specifics
    private Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@delfi.lyngbyes.dk:1521:KNORD", "CLDATB12E6", "CLDATB12E6");
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
