package dataSource;

import domain.DelOrdre;
import domain.Kunde;
import domain.Odetaljer;
import domain.Ordre;
import domain.Vare;
import java.sql.*;
import java.util.ArrayList;

public class UnitOfWorkProcessOrder {

    private ArrayList<Ordre> newOrdre;
    private ArrayList<Ordre> dirtyOrdre;
    private ArrayList<Ordre> deletedOrdre;
    private ArrayList<Kunde> newKunde;
    private ArrayList<Kunde> dirtyKunde;
    private ArrayList<Kunde> deletedKunde;
    private ArrayList<Odetaljer> newOdetaljer;
    private ArrayList<Odetaljer> dirtyOdetaljer;
    private ArrayList<Odetaljer> deletedOdetaljer;
    private ArrayList<Vare> newVare;
    private ArrayList<Vare> dirtyVare;
    private ArrayList<Vare> deletedVare;
    private ArrayList<DelOrdre> newDelOrdre;
    private ArrayList<DelOrdre> dirtyDelOrdre;
    private ArrayList<DelOrdre> deletedDelOrdre;

    public UnitOfWorkProcessOrder() {
        newOrdre = new ArrayList<>();
        dirtyOrdre = new ArrayList<>();
        deletedOrdre = new ArrayList<>();
        newKunde = new ArrayList<>();
        dirtyKunde = new ArrayList<>();
        deletedKunde = new ArrayList<>();
        newOdetaljer = new ArrayList<>();
        dirtyOdetaljer = new ArrayList<>();
        deletedOdetaljer = new ArrayList<>();
        newVare = new ArrayList<>();
        dirtyVare = new ArrayList<>();
        deletedVare = new ArrayList<>();
        newDelOrdre = new ArrayList<>();
        dirtyDelOrdre = new ArrayList<>();
        deletedDelOrdre = new ArrayList<>();
    }
    
    // Ordre
    
    public void registerNewOrdre(Ordre o) {
        if (!newOrdre.contains(o)
                && !dirtyOrdre.contains(o)) {
            newOrdre.add(o);
        }
    }

    public void registerDirtyOrdre(Ordre o) {
        if (!newOrdre.contains(o)
                && !dirtyOrdre.contains(o)) {
            dirtyOrdre.add(o);
        }
    }

    public boolean registerDeletedOrder(Ordre o) {
        boolean status = false;
        if (!deletedOrdre.contains(o)) {
            status = true;
            deletedOrdre.add(o);
        }
        return status;
    }

    // Kunde
    public void registerNewKunde(Kunde k) {
        if (!newKunde.contains(k)) {
            newKunde.add(k);
        }
    }

    public void registerDirtyKunde(Kunde k) {
        if (!newKunde.contains(k)
                && !dirtyKunde.contains(k)) {
            dirtyKunde.add(k);
        }
    }

    public boolean registerDeletedKunde(Kunde k) {
        boolean status = false;
        if (!deletedKunde.contains(k)) {
            status = true;
            deletedKunde.add(k);
        }
        return status;
    }

    // Vare
    
    public void registerNewVare(Vare v) {
        if (!newVare.contains(v)
                && !dirtyVare.contains(v)) {
            newVare.add(v);
        }
    }

    public void registerDirtyVare(Vare v) {
        if (!newVare.contains(v)
                && !dirtyVare.contains(v)) {
            dirtyVare.add(v);
        }
    }

    public boolean registerDeletedVare(Vare v) {
        boolean status = false;
        if (!deletedVare.contains(v)) {
            status = true;
            deletedVare.add(v);
        }
        return status;
    }
    
    // Odetalje
    
    public void registerNewOdetalje(Odetaljer od) {
        if (!newOdetaljer.contains(od)) {
            newOdetaljer.add(od);
        }
    }

    public void registerDirtyOdetalje(Odetaljer od) {
        if (!dirtyOdetaljer.contains(od)) {
            dirtyOdetaljer.add(od);
        }
    }

    public boolean registerDeletedOdetalje(Odetaljer od) {
        boolean status = false;
        if (!deletedOdetaljer.contains(od)) {
            status = true;
            deletedOdetaljer.add(od);
        }
        return status;
    }

    // Del
    
    // DelOrdre
    
    public void registerDelOrdre(DelOrdre delo) {
        if (!newDelOrdre.contains(delo)
                && !dirtyDelOrdre.contains(delo)) {
            newDelOrdre.add(delo);
        }
    }

    public void registerDirtyDelOrdre(DelOrdre delo) {
        if (!newDelOrdre.contains(delo)
                && !dirtyDelOrdre.contains(delo)) {
            dirtyDelOrdre.add(delo);
        }
    }

    public boolean registerDeletedDelOrdre(DelOrdre delo) {
        boolean status = false;
        if (!deletedDelOrdre.contains(delo)) {
            status = true;
            deletedDelOrdre.add(delo);
        }
        return status;
    }

    // Commit
    public boolean commit(Connection conn) throws SQLException {
        boolean status = true;
        try {
            //=== system transaction - start
            conn.setAutoCommit(false);
            OrderMapper om = new OrderMapper();
            KundeMapper km = new KundeMapper();
            DelOrdreMapper dom = new DelOrdreMapper();
            VareMapper vm = new VareMapper();
            OdetaljeMapper odm = new OdetaljeMapper();

            status = status && om.insertOrdrer(newOrdre, conn);
            status = status && om.updateOrdrer(dirtyOrdre, conn);
            status = status && km.insertKunder(newKunde, conn);
            status = status && km.updateKunder(dirtyKunde, conn);
            status = status && odm.insertOdetaljer(newOdetaljer, conn);
            status = status && odm.updateOdetaljer(dirtyOdetaljer, conn);
            status = status && dom.insertDelOrdrer(newDelOrdre, conn);
            status = status && dom.updateDelOrdre(dirtyDelOrdre, conn);
            status = status && vm.insertVarer(newVare, conn);
            status = status && vm.updateVarer(dirtyVare, conn);
            status = status && om.deleteOrdrer(deletedOrdre, conn);
            status = status && km.deleteKunder(deletedKunde, conn);
            status = status && vm.deleteVarer(deletedVare, conn);
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

    // ???
    public Ordre getOrder(int ono, Connection con) {
        Ordre o = null;
        try {
            o = new OrderMapper().getOrdre(ono, con);
        } catch (Exception e) {
            System.out.println("fail in UnitOfWork - getOrder()");
            System.err.println(e);
        }
        return o;

    }
}
