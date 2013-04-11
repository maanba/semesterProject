package dataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import semesterprojekt.*;

//==== 	encapsulates SQL 
//	maps between classes and tables
//	implements Optimistic Offline Lock (version number attribute in the order table)
//	2010/hau
public class OrderMapper {

    static boolean testRun = false;

    //====== Methods to save to DB =========================================================
    // Insert a list of new orders
    // returns true if all elements were inserted successfully
    public boolean insertOrders(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into ordrer values (?,?,?,?,?,?,?,"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        for (int i = 0; i < ol.size(); i++) {
            Ordre o = ol.get(i);
            statement.setInt(1, getNextFnummer(conn));
            statement.setInt(2, o.getOnummer());
            statement.setInt(3, o.getKnummer());
            statement.setDouble(4, o.getPris());
            statement.setDouble(5, o.getDepositum());
            statement.setString(6, o.getAfhentning());
            statement.setString(7, o.getStatus());
            statement.setString(8, o.getModtaget());
            statement.setString(9, o.getLevering());
            statement.setString(10, o.getReturnering());
            statement.setInt(11, o.getVer());
            rowsInserted += statement.executeUpdate();
        }
        if (testRun) {
            System.out.println("insertOrders(): " + (rowsInserted == ol.size())); // for test
        }
        return (rowsInserted == ol.size());
    }

    public boolean insertCustomer(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into Kunde values (?,?,?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        for (int i = 0; i < kl.size(); i++) {
            Kunde o = kl.get(i);
            statement.setInt(1, o.getKnummer());
            statement.setInt(2, o.getPostnummer());
            statement.setString(3, o.getAdresse());
            statement.setString(4, o.getNavn());
            statement.setInt(5, o.getTelefonnummer());
            rowsInserted += statement.executeUpdate();
        }
        if (testRun) {
            System.out.println("insertOrders(): " + (rowsInserted == kl.size())); // for test
        }
        return (rowsInserted == kl.size());
    }

    public boolean insertRessources(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into varer values (?,?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        for (int i = 0; i < vl.size(); i++) {
            Vare v = vl.get(i);
            statement.setInt(1, v.getVnummer());
            statement.setString(2, v.getVnavn());
            statement.setInt(3, v.getQty());
            statement.setDouble(4, v.getPris());
            rowsInserted += statement.executeUpdate();
        }
        if (testRun) {
            System.out.println("insertOrders(): " + (rowsInserted == vl.size())); // for test
        }
        return (rowsInserted == vl.size());
    }

    // Update a list of orders 
    // using optimistic offline lock (version no)
    // Returns true if any conflict in version number
    public boolean updateOrders(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update ordrer "
                + "set fnummer = ?, knummer = ?, pris = ?, depositum = ?, afhentning = ?, status = ?, modtaget = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), levering = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), returnering = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), ver = ? "
                + "where onummer = ? and ver = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < ol.size(); i++) {
            Ordre o = ol.get(i);
            statement.setInt(1, getNextFnummer(conn));
            statement.setInt(2, o.getKnummer());
            statement.setDouble(3, o.getPris());
            statement.setDouble(4, o.getDepositum());
            statement.setString(5, o.getAfhentning());
            statement.setString(6, o.getStatus());
            statement.setString(7, o.getModtaget());
            statement.setString(8, o.getLevering());
            statement.setString(9, o.getReturnering());
            statement.setInt(10, o.getVer() + 1); // next version number
            statement.setInt(11, o.getOnummer());
            statement.setInt(12, o.getVer());   // old version number
            int tupleUpdated = statement.executeUpdate();
            if (tupleUpdated == 1) {
                o.nuVer();                       // increment version in current OrderObject
            }
            rowsUpdated += tupleUpdated;
        }
        if (testRun) {
            System.out.println("updateOrders: " + (rowsUpdated == ol.size())); // for test
        }
        return (rowsUpdated == ol.size());    // false if any conflict in version number             
    }

    public boolean updateCustomer(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update Kunde "
                + "set postnummer = ?, addresse = ?, navn = ?, telefonnummer = ? "
                + "where knummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < kl.size(); i++) {
            Kunde k = kl.get(i);
            statement.setInt(1, k.getPostnummer());
            statement.setString(2, k.getAdresse());
            statement.setString(3, k.getNavn());
            statement.setInt(4, k.getTelefonnummer());
            statement.setInt(5, k.getKnummer());
            int tupleUpdated = statement.executeUpdate();
            rowsUpdated += tupleUpdated;
        }
        if (testRun) {
            System.out.println("updateOrders: " + (rowsUpdated == kl.size())); // for test
        }
        return (rowsUpdated == kl.size());    // false if any conflict in version number             
    }

    public boolean updateRessources(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update varer "
                + "set vnavn = ?, qty = ?, pris = ? "
                + "where vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < vl.size(); i++) {
            Vare v = vl.get(i);
            statement.setString(1, v.getVnavn());
            statement.setInt(2, v.getQty());
            statement.setDouble(3, v.getPris());
            statement.setInt(4, v.getVnummer());
            int tupleUpdated = statement.executeUpdate();
            rowsUpdated += tupleUpdated;
        }
        if (testRun) {
            System.out.println("updateOrders: " + (rowsUpdated == vl.size())); // for test
        }
        return (rowsUpdated == vl.size());    // false if any conflict in version number             
    }

    // Insert a list of new order details
    // Returns true if all elements were inserted successfully
    public boolean insertOrderDetails(ArrayList<Odetaljer> odl, Connection conn) throws SQLException {
        String SQLString = "insert into odetaljer values (?,?,?)";
        PreparedStatement statement = null;

        int rowsInserted = 0;
        if (0 < odl.size()) {
            statement = conn.prepareStatement(SQLString);
            for (int i = 0; i < odl.size(); i++) {
                statement.setInt(1, odl.get(i).getOnummer());
                statement.setInt(2, odl.get(i).getVnummer());
                statement.setInt(3, odl.get(i).getMaengde());
                rowsInserted += statement.executeUpdate();
            }
        }
        if (testRun) {
            System.out.println("insertOrderDetails:" + (rowsInserted == odl.size())); // for test
        }
        return rowsInserted == odl.size();
    }
    public boolean insertVareDel(ArrayList<Del> delListe, Connection conn) throws SQLException {
        String SQLString = "insert into odetaljer values (?,?,?)";
        PreparedStatement statement = null;

        int rowsInserted = 0;
        if (0 < delListe.size()) {
            statement = conn.prepareStatement(SQLString);
            for (int i = 0; i < delListe.size(); i++) {
                statement.setInt(1, delListe.get(i).getVnummer());
                statement.setString(2, delListe.get(i).getTitel());
                statement.setInt(3, delListe.get(i).getAntal());
                rowsInserted += statement.executeUpdate();
            }
        }
        if (testRun) {
            System.out.println("insertVareDel:" + (rowsInserted == delListe.size())); // for test
        }
        return rowsInserted == delListe.size();
    }
    public boolean updateOrderDetails(ArrayList<Odetaljer> odl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update odetaljer "
                + "set maengde = ? "
                + "where onummer = ? and vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < odl.size(); i++) {
            Odetaljer od = odl.get(i);
            statement.setInt(1, od.getMaengde());
            statement.setInt(2, od.getOnummer());
            statement.setInt(3, od.getVnummer());
            int tupleUpdated = statement.executeUpdate();
            rowsUpdated += tupleUpdated;
        }
        if (testRun) {
            System.out.println("updateOrders: " + (rowsUpdated == odl.size())); // for test
        }
        return (rowsUpdated == odl.size());
    }
    public boolean updateVareDel(ArrayList<Del> delListe, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update del "
                + "set antal = ? "
                + "where vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < delListe.size(); i++) {
            Del od = delListe.get(i);
            statement.setInt(1, od.getVnummer());
            statement.setString(2, od.getTitel());
            statement.setInt(3, od.getAntal());
            int tupleUpdated = statement.executeUpdate();
            rowsUpdated += tupleUpdated;
        }
        if (testRun) {
            System.out.println("updateDel: " + (rowsUpdated == delListe.size())); // for test
        }
        return (rowsUpdated == delListe.size());
    }
    public boolean deleteOrderDetials(int ono, Connection conn) throws SQLException {
        int ordersDeleted = 0;
        String SQLString = "delete from odetaljer "
                + "where onummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
            statement.setInt(1, ono);
            statement.executeUpdate();
        
        return (ordersDeleted == 1);
    }
    
    public boolean deleteVareDel(int vnummer, Connection conn) throws SQLException {
        int delDeleted = 0;
        String SQLString = "delete from del "
                + "where vnummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
            statement.setInt(1, vnummer);
            statement.executeUpdate();
        
        return (delDeleted == 1);
    }

    public boolean deleteCustomers(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int ordersDeleted = 0;
        String SQLString = "delete from kunder "
                + "where knummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < kl.size(); i++) {
            Kunde k = kl.get(i);
            statement.setInt(1, k.getKnummer());
            statement.executeUpdate();
        }
        return (ordersDeleted == kl.size());
    }

    public boolean deleteOrders(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int ordersDeleted = 0;
        String SQLString1 = "delete from ordrer "
                + "where onummer = ? and ver = ? ";
        String SQLString2 = "delete from odetaljer "
                + "where onummer = ? and vnummer = ? ";

        PreparedStatement statement1 = conn.prepareStatement(SQLString2);
        PreparedStatement statement2 = conn.prepareStatement(SQLString1);
        for (int i = 0; i < ol.size(); i++) {
            Ordre o = (Ordre) ol.get(i);
            for (int j = 0; j < o.getOd().size(); j++) {
                statement1.setInt(1, o.getOnummer());
                statement1.setInt(2, o.getOd().get(j).getVnummer());
                statement1.executeUpdate();
            }
            statement2.setInt(1, o.getOnummer());
            statement2.setInt(2, o.getVer());
            ordersDeleted += statement2.executeUpdate();
        }
        return (ordersDeleted == ol.size());
    }

    public boolean deleteRessources(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int ordersDeleted = 0;
        String SQLString = "delete from varer "
                + "where knummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        for (int i = 0; i < vl.size(); i++) {
            Vare v = vl.get(i);
            statement.setInt(1, v.getVnummer());
            statement.executeUpdate();
        }
        return (ordersDeleted == vl.size());
    }

    //======  Methods to read from DB =======================================================
    // Retrieve a specific order and related order details
    // Returns the Order-object
    public Ordre getOrder(int ono, Connection conn) {
        Ordre o = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String SQLString1 = // get order
                "select * "
                + "from ordrer "
                + "where onummer = ?";
        String SQLString2 = // get order details
                "select * "
                + "from odetaljer "
                + "where onummer = ? ";
        PreparedStatement statement = null;

        try {
            //=== get order
            statement = conn.prepareStatement(SQLString1);
            statement.setInt(1, ono);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                o = new Ordre(ono,
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        dateFormat.format(rs.getDate(8)),
                        dateFormat.format(rs.getDate(9)),
                        dateFormat.format(rs.getDate(10)),
                        rs.getInt(11));

                //=== get order details
                statement = conn.prepareStatement(SQLString2);
                statement.setInt(1, ono);
                rs = statement.executeQuery();
                while (rs.next()) {
                    o.addOd(new Odetaljer(
                            ono,
                            rs.getInt(2),
                            rs.getInt(3)));
                }
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        }
        if (testRun) {
            System.out.println("Retrieved Order: " + o);
        }
        return o;
    }

    public ArrayList<Ordre> getAllOrders(Connection conn) {
        Ordre o = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        ArrayList<Ordre> ol = new ArrayList();
        ArrayList ono = new ArrayList();
        String SQLString1 = // get order
                "select * "
                + "from ordrer "
                + "where onummer = ?";
        String SQLString2 = // get order details
                "select * "
                + "from odetaljer "
                + "where onummer = ? ";
        String SQLString3 = // get order
                "select * "
                + "from ordrer ";
        PreparedStatement statement = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            ResultSet rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                ono.add(rs.getInt(i));
            }
            //=== get order
            for (int j = 0; j < ono.size(); j++) {
                statement = conn.prepareStatement(SQLString1);
                statement.setInt(1, (int) ono.get(j));
                rs = statement.executeQuery();
                if (rs.next()) {
                    o = new Ordre(rs.getInt(1),
                            rs.getInt(2),
                            rs.getInt(3),
                            rs.getDouble(4),
                            rs.getDouble(5),
                            rs.getString(6),
                            rs.getString(7),
                            dateFormat.format(rs.getDate(8)),
                            dateFormat.format(rs.getDate(9)),
                            dateFormat.format(rs.getDate(10)),
                            rs.getInt(11));

                    //=== get order details
                    statement = conn.prepareStatement(SQLString2);
                    statement.setInt(1, o.getOnummer());
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        o.addOd(new Odetaljer(
                                o.getOnummer(),
                                rs.getInt(2),
                                rs.getInt(3)));
                    }
                    ol.add(o);
                }
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllOrders");
            System.out.println(e.getMessage());
        }
        if (testRun) {
            System.out.println("Retrieved Order: " + o);
        }
        return ol;
    }

    public ArrayList<Kunde> getAllCostumers(Connection conn) {
        Kunde k = null;
        ArrayList<Kunde> kl = new ArrayList();
        ArrayList kno = new ArrayList();
        String SQLString1 = // get order
                "select * "
                + "from kunder "
                + "where knummer = ?";
        String SQLString3 = // get order
                "select * "
                + "from kunder ";
        PreparedStatement statement = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            ResultSet rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                kno.add(rs.getInt(i));
            }
            //=== get order
            for (int j = 0; j < kno.size(); j++) {
                statement = conn.prepareStatement(SQLString1);
                statement.setInt(1, (int) kno.get(j));
                rs = statement.executeQuery();
                if (rs.next()) {
                    k = new Kunde(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getInt(6));
                }
                kl.add(k);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllCostumers");
            System.out.println(e.getMessage());
        }
        if (testRun) {
            System.out.println("Retrieved Order: " + k);
        }
        return kl;
    }

    public ArrayList<Vare> getAllRessources(Connection conn) {
        Vare v = null;
        ArrayList<Vare> vl = new ArrayList();
        ArrayList vno = new ArrayList();
        String SQLString1 = // get order
                "select * "
                + "from varer "
                + "where vnummer = ?";
        String SQLString3 = // get order
                "select * "
                + "from varer ";
        PreparedStatement statement = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            ResultSet rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                vno.add(rs.getInt(i));
            }
            //=== get order
            for (int j = 0; j < vno.size(); j++) {
                statement = conn.prepareStatement(SQLString1);
                statement.setInt(1, (int) vno.get(j));
                rs = statement.executeQuery();
                if (rs.next()) {
                    v = new Vare(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getDouble(4));
                }
                vl.add(v);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getAllRessources");
            System.out.println(e.getMessage());
        }
        if (testRun) {
            System.out.println("Retrieved Order: " + v);
        }
        return vl;
    }

    public Vare getVare(Connection conn, int vnummer) {
        String SQLString = "select * from varer where vnummer = ?";
        PreparedStatement statement = null;
        Vare vare = null;
        
        String SQLString2 = // get del
                "select * "
                + "from del "
                + "where onummer = ? ";
        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, vnummer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                vare = new Vare(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4));
            }
            statement = conn.prepareStatement(SQLString2);
                statement.setInt(1, vnummer);
                rs = statement.executeQuery();
                while (rs.next()) {
                    vare.addDel(new Del(
                            vnummer,
                            rs.getString(2),
                            rs.getInt(3)));
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (testRun) {
            System.out.println("Retrieved Varer: " + vare);
        }
        return vare;
    }

    public Kunde getKunde(Connection conn, int knummer) {
        String SQLString = "select * from kunder where knummer = ?";
        PreparedStatement statement = null;
        Kunde kunde = null;
        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, knummer);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                kunde = new Kunde(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return kunde;
    }
    public Postnummer getPostnummer(Connection conn, int postnr) {
        String SQLString = "select * from postnummer where postnummer = ?";
        PreparedStatement statement = null;
        Postnummer postnummer = null;
        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, postnr);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                postnummer = new Postnummer(rs.getInt(1),
                                        rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return postnummer;
    }
    // Retrieves the next unique order number from DB

    public int getNextOrderNo(Connection conn) {
        int nextOno = 0;
        String SQLString = "select orderseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextOno = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextOrderNo");
            System.out.println(e.getMessage());
        }
        return nextOno;
    }

    public int getNextVnummer(Connection conn) {
        int nextOno = 0;
        String SQLString = "select varerseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextOno = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextVnummer");
            System.out.println(e.getMessage());
        }
        return nextOno;
    }
    public int getNextFnummer(Connection conn) {
        int nextFnummer = 0;
        String SQLString = "select fakturaseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextFnummer = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextFnummer");
            System.out.println(e.getMessage());
        }
        return nextFnummer;
    }

    public int getNextKnummer(Connection conn) {
        int nextOno = 0;
        String SQLString = "select kundeseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                nextOno = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Fail in OrderMapper - getNextKnummer");
            System.out.println(e.getMessage());
        }
        return nextOno;
    }
}
