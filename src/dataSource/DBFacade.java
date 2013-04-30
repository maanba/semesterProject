package dataSource;

import domain.DelOrdre;
import domain.Kunde;
import domain.Odetaljer;
import domain.Postnummer;
import domain.Ordre;
import domain.Vare;
import java.sql.*;
import java.util.ArrayList;

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

    // Ordre
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

    public int getNextOnummer() {
        int nextOno = 0;
        try {
            nextOno = new OrderMapper().getNextOnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextOnummer");
            System.out.println(e.getMessage());
        }
        return nextOno;
    }

    // Kunde
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

    public ArrayList<Kunde> getAllKunder() {
        ArrayList<Kunde> o = null;
        try {
            o = new KundeMapper().getAllKunder(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllCostumers");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public Kunde getKunde(int knummer) {
        Kunde k = null;
        try {
            k = new KundeMapper().getKunde(con, knummer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return k;
    }

    public int getNextKnummer() {
        int nextKno = 0;
        try {
            nextKno = new KundeMapper().getNextKnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextKnummer");
            System.out.println(e.getMessage());
        }
        return nextKno;
    }

    // VaresS
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

    public ArrayList<Vare> getAllVarer() {
        ArrayList<Vare> o = null;
        try {
            o = new VareMapper().getAllVarer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllRessources");
            System.out.println(e.getMessage());
        }
        return o;
    }

    public Vare getVare(int vnummer) {
        Vare v = null;
        try {
            v = new VareMapper().getVare(con, vnummer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return v;
    }

    public int getNextVnummer() {
        int nextVno = 0;
        try {
            nextVno = new VareMapper().getNextVnummer(con);
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextVnummer");
            System.out.println(e.getMessage());
        }
        return nextVno;
    }

    // Odetalje
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

    public boolean deleteOdetalje(int ono) {
        boolean status = false;
        try {
            status = new OdetaljeMapper().deleteOdetalje(ono, con);
        } catch (SQLException ex) {
            System.out.println("fail in deleteOdetaljer");
        }
        return status;
    }

    // Del
    // DelOrdre
    public void registerNewDelOrdre(DelOrdre delo) {
        if (uow != null) {
            uow.registerDelOrdre(delo);
        }
    }

    public void registerDirtyDelOrdre(DelOrdre delo) {
        if (uow != null) {
            uow.registerDirtyDelOrdre(delo);
        }
    }

    public boolean deleteDelOrdre(DelOrdre delo) {
        boolean status = false;

        if (uow != null) {
            status = uow.registerDeletedDelOrdre(delo);
        }
        return status;
    }

    public Postnummer getPostnummer(int postnr) {
        Postnummer p = null;
        try {
            p = new PostnummerMapper().getPostnummer(con, postnr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
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

    // Comitting & Connection
    public void startNewBusinessTransaction() {
        uow = new UnitOfWorkProcessOrder();
    }

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
