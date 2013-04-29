package dataSource;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import semesterprojekt.*;

public class OrderMapper {

    static boolean testRun = false;

    // Ordre
    public boolean insertOrdrer(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into ordrer values (?,?,?,?,?,?,?,?,?,?,?,"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'),"
                + "?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        try {
            for (int i = 0; i < ol.size(); i++) {
                Ordre o = ol.get(i);
                statement.setInt(1, o.getOnummer());
                statement.setInt(2, o.getFnummer());
                statement.setInt(3, o.getKnummer());
                statement.setDouble(4, o.getPris());
                statement.setDouble(5, o.getRabat());
                statement.setDouble(6, o.getDepositum());
                statement.setString(7, o.getTidLev());
                statement.setString(8, o.getTidRet());
                statement.setString(9, o.getAfhentning());
                statement.setInt(10, o.getMontoerer());
                statement.setString(11, o.getStatus());
                statement.setString(12, o.getModtaget());
                statement.setString(13, o.getLevering());
                statement.setString(14, o.getReturnering());
                statement.setString(15, o.getKommentar());
                statement.setInt(16, o.getVer());
                rowsInserted += statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertOrdrer(): " + (rowsInserted == ol.size())); // for test
        }
        return (rowsInserted == ol.size());
    }

    public boolean updateOrdrer(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update ordrer "
                + "set fnummer = ?, knummer = ?, pris = ?, rabat = ?, depositum = ?, tidlev = ?, tidret = ?, afhentning = ?, montoerer = ?, status = ?, modtaget = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), levering = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), returnering = to_date(?, 'DD MM YYYY','NLS_DATE_LANGUAGE = American'), kommentar = ?, ver = ? "
                + "where onummer = ? and ver = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < ol.size(); i++) {
                Ordre o = ol.get(i);
                statement.setInt(1, o.getFnummer());
                statement.setInt(2, o.getKnummer());
                statement.setDouble(3, o.getPris());
                statement.setDouble(4, o.getRabat());
                statement.setDouble(5, o.getDepositum());
                statement.setString(6, o.getTidLev());
                statement.setString(7, o.getTidRet());
                statement.setString(8, o.getAfhentning());
                statement.setInt(9, o.getMontoerer());
                statement.setString(10, o.getStatus());
                statement.setString(11, o.getModtaget());
                statement.setString(12, o.getLevering());
                statement.setString(13, o.getReturnering());
                statement.setString(14, o.getKommentar());
                statement.setInt(15, o.getVer() + 1); // next version number
                statement.setInt(16, o.getOnummer());
                statement.setInt(17, o.getVer());   // old version number
                int tupleUpdated = statement.executeUpdate();
                if (tupleUpdated == 1) {
                    o.nuVer();                       // increment version in current OrderObject
                }
                rowsUpdated += tupleUpdated;
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateOrdrer: " + (rowsUpdated == ol.size())); // for test
        }
        return (rowsUpdated == ol.size());    // false if any conflict in version number             
    }

    public boolean deleteOrdrer(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int deletedOrdrer = 0;
        String SQLString1 = "delete from ordrer "
                + "where onummer = ? and ver = ? ";
        String SQLString2 = "delete from odetaljer "
                + "where onummer = ? and vnummer = ? ";

        PreparedStatement statement1 = conn.prepareStatement(SQLString2);
        PreparedStatement statement2 = conn.prepareStatement(SQLString1);
        try {
            for (int i = 0; i < ol.size(); i++) {
                Ordre o = (Ordre) ol.get(i);
                for (int j = 0; j < o.getOd().size(); j++) {
                    statement1.setInt(1, o.getOnummer());
                    statement1.setInt(2, o.getOd().get(j).getVnummer());
                    statement1.executeUpdate();
                    statement1.close();
                }
                statement2.setInt(1, o.getOnummer());
                statement2.setInt(2, o.getVer());
                deletedOrdrer += statement2.executeUpdate();
                statement2.close();
            }
        } finally {
            if (!statement1.isClosed()) {
                statement1.close();
            }
            if (!statement2.isClosed()) {
                statement2.close();
            }
        }
        return (deletedOrdrer == ol.size());
    }

    public ArrayList<Ordre> getAllOrdrer(Connection conn) throws SQLException {
        Ordre o = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        ArrayList<Ordre> ol = new ArrayList();
        ArrayList ono = new ArrayList();
        String SQLString1 = // get ordre
                "select * "
                + "from ordrer "
                + "where onummer = ?";
        String SQLString2 = // get Odetaljer
                "select * "
                + "from odetaljer "
                + "where onummer = ? ";
        String SQLString3 = // get ordre
                "select * "
                + "from ordrer ";
        String SQLString4 = 
                "select * "
                + "from delordre "
                + "where onummer = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                ono.add(rs.getInt(i));
            }
            statement.close();
            rs.close();
            //=== get ordre
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
                            rs.getDouble(6),
                            rs.getString(7),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getInt(10),
                            rs.getString(11),
                            dateFormat.format(rs.getDate(12)),
                            dateFormat.format(rs.getDate(13)),
                            dateFormat.format(rs.getDate(14)),
                            rs.getString(15),
                            rs.getInt(16));

                    //=== get ordre details
                    statement.close();
                    statement = conn.prepareStatement(SQLString2);
                    statement.setInt(1, o.getOnummer());
                    rs.close();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        o.addOd(new Odetaljer(
                                o.getOnummer(),
                                rs.getInt(2),
                                rs.getInt(3)));
                    }
                    //=== get del ordre
                    statement.close();
                    statement = conn.prepareStatement(SQLString4);
                    statement.setInt(1, o.getOnummer());
                    rs.close();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        o.addDelo(new DelOrdre(
                                rs.getString(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5)));
                    }
                    ol.add(o);
                }
                statement.close();
                rs.close();
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("Retrieved ordrer: " + o);
        }
        return ol;
    }

    public Ordre getOrdre(int ono, Connection conn) throws SQLException {
        Ordre o = null;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String SQLString1 = // get ordre
                "select * "
                + "from ordrer "
                + "where onummer = ?";
        String SQLString2 = // get ordre details
                "select * "
                + "from odetaljer "
                + "where onummer = ? ";
        String SQLString3 = 
                "select * "
                + "from delordre "
                + "where onummer = ?";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            //=== get ordre
            statement = conn.prepareStatement(SQLString1);
            statement.setInt(1, ono);
            rs = statement.executeQuery();
            if (rs.next()) {
                o = new Ordre(ono,
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getInt(10),
                        rs.getString(11),
                        dateFormat.format(rs.getDate(12)),
                        dateFormat.format(rs.getDate(13)),
                        dateFormat.format(rs.getDate(14)),
                        rs.getString(15),
                        rs.getInt(16));

                //=== get ordre details
                statement.close();
                statement = conn.prepareStatement(SQLString2);
                statement.setInt(1, ono);
                rs.close();
                rs = statement.executeQuery();
                while (rs.next()) {
                    o.addOd(new Odetaljer(
                            ono,
                            rs.getInt(2),
                            rs.getInt(3)));
                }
                //=== get del ordre
                    statement.close();
                    statement = conn.prepareStatement(SQLString3);
                    statement.setInt(1, o.getOnummer());
                    rs.close();
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        o.addDelo(new DelOrdre(
                                rs.getString(1),
                                rs.getInt(2),
                                rs.getInt(3),
                                rs.getInt(4),
                                rs.getInt(5)));
                    }
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("Retrieved ordre: " + o);
        }
        return o;
    }

    public int getNextOnummer(Connection conn) throws SQLException {
        int nextOno = 0;
        String SQLString = "select ordreseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            rs = statement.executeQuery();
            if (rs.next()) {
                nextOno = rs.getInt(1);
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return nextOno;
    }

    // Kunde
    public boolean insertKunder(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into Kunder values (?,?,?,?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        try {
            for (int i = 0; i < kl.size(); i++) {
                Kunde k = kl.get(i);
                statement.setInt(1, k.getKnummer());
                statement.setString(2, k.getFirma());
                statement.setString(3, k.getNavn());
                statement.setString(4, k.getAdresse());
                statement.setInt(5, k.getPostnummer());
                statement.setInt(6, k.getTelefonnummer());
                rowsInserted += statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertOrdrer(): " + (rowsInserted == kl.size())); // for test
        }
        return (rowsInserted == kl.size());
    }

    public boolean updateKunder(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update Kunder "
                + "set firma = ?, navn = ?, addresse = ?, postnummer = ?, telefonnummer = ? "
                + "where knummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < kl.size(); i++) {
                Kunde k = kl.get(i);
                statement.setString(1, k.getFirma());
                statement.setString(2, k.getNavn());
                statement.setString(3, k.getAdresse());
                statement.setInt(4, k.getPostnummer());
                statement.setInt(5, k.getTelefonnummer());
                statement.setInt(6, k.getKnummer());
                int tupleUpdated = statement.executeUpdate();
                rowsUpdated += tupleUpdated;
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateOrdrer: " + (rowsUpdated == kl.size())); // for test
        }
        return (rowsUpdated == kl.size());    // false if any conflict in version number             
    }

    public boolean deleteKunder(ArrayList<Kunde> kl, Connection conn) throws SQLException {
        int deletedOrdrer = 0;
        String SQLString = "delete from kunder "
                + "where knummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < kl.size(); i++) {
                Kunde k = kl.get(i);
                statement.setInt(1, k.getKnummer());
                statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return (deletedOrdrer == kl.size());
    }

    public ArrayList<Kunde> getAllKunder(Connection conn) throws SQLException {
        Kunde k = null;
        ArrayList<Kunde> kl = new ArrayList();
        ArrayList kno = new ArrayList();
        String SQLString1 = // get ordre
                "select * "
                + "from kunder "
                + "where knummer = ?";
        String SQLString3 = // get ordre
                "select * "
                + "from kunder ";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                kno.add(rs.getInt(i));
            }
            //=== get ordre
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
                statement.close();
                rs.close();
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("Retrieved ordrer: " + k);
        }
        return kl;
    }

    public Kunde getKunde(Connection conn, int knummer) throws SQLException {
        String SQLString = "select * from kunder where knummer = ?";
        PreparedStatement statement = null;
        Kunde kunde = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, knummer);
            rs = statement.executeQuery();
            if (rs.next()) {
                kunde = new Kunde(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6));
            }

        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return kunde;
    }

    public int getNextKnummer(Connection conn) throws SQLException {
        int nextKno = 0;
        String SQLString = "select kundeseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            rs = statement.executeQuery();
            if (rs.next()) {
                nextKno = rs.getInt(1);
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return nextKno;
    }

    // Varer
    public boolean insertVarer(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int rowsInserted = 0;
        String SQLString = "insert into varer values (?,?,?,?,?)";
        String SQLString2 = "insert into dele values (?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        try {
            for (int i = 0; i < vl.size(); i++) {
                if (!statement.isClosed()) {
                    statement.close();
                }
                statement = conn.prepareStatement(SQLString);
                Vare v = vl.get(i);
                statement.setInt(1, v.getVnummer());
                statement.setString(2, v.getVnavn());
                statement.setInt(3, v.getQty());
                statement.setDouble(4, v.getPris());
                statement.setInt(5, v.getAktiv());
                rowsInserted += statement.executeUpdate();
                statement.close();
                for (int j = 0; j < v.getDel().size(); j++) {
                    statement = conn.prepareStatement(SQLString2);
                    Del d = v.getDel().get(j);
                    statement.setInt(1, d.getVnummer());
                    statement.setString(2, d.getTitel());
                    statement.setInt(3, d.getAntal());
                    statement.executeUpdate();
                    statement.close();
                }
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertOrdrer(): " + (rowsInserted == vl.size())); // for test
        }
        return (rowsInserted == vl.size());
    }

    public boolean updateVarer(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update varer "
                + "set vnavn = ?, qty = ?, pris = ?, aktiv = ? "
                + "where vnummer = ?";
        String SQLString2 = "delete from dele "
                + "where vnummer = ?";
        String SQLString3 = "insert into dele values (?,?,?)";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < vl.size(); i++) {
                if (!statement.isClosed()) {
                    statement.close();
                }
                Vare v = vl.get(i);
                statement = conn.prepareStatement(SQLString2);
                statement.setInt(1, v.getVnummer());
                statement.executeUpdate();
                statement.close();
                statement = conn.prepareStatement(SQLString);
                statement.setString(1, v.getVnavn());
                statement.setInt(2, v.getQty());
                statement.setDouble(3, v.getPris());
                statement.setInt(4, v.getAktiv());
                statement.setInt(5, v.getVnummer());
                int tupleUpdated = statement.executeUpdate();
                rowsUpdated += tupleUpdated;
                statement.close();
                for (int j = 0; j < v.getDel().size(); j++) {
                    statement = conn.prepareStatement(SQLString3);
                    Del d = v.getDel().get(j);
                    statement.setInt(1, d.getVnummer());
                    statement.setString(2, d.getTitel());
                    statement.setInt(3, d.getAntal());
                    statement.executeUpdate();
                    statement.close();
                }
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateOrdrer: " + (rowsUpdated == vl.size())); // for test
        }
        return (rowsUpdated == vl.size());    // false if any conflict in version number             
    }

    public boolean deleteVarer(ArrayList<Vare> vl, Connection conn) throws SQLException {
        int ordrerDeleted = 0;
        String SQLString = "delete from varer "
                + "where vnummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < vl.size(); i++) {
                Vare v = vl.get(i);
                statement.setInt(1, v.getVnummer());
                statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return (ordrerDeleted == vl.size());
    }

    public ArrayList<Vare> getAllVarer(Connection conn) throws SQLException {
        Vare v = null;
        ArrayList<Vare> vl = new ArrayList();
        ArrayList vno = new ArrayList();
        String SQLString1 = // get ordre
                "select * "
                + "from varer "
                + "where vnummer = ?";
        String SQLString2 = // get ordre details
                "select * "
                + "from dele "
                + "where vnummer = ? ";
        String SQLString3 = // get ordre
                "select * "
                + "from varer ";
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {

            statement = conn.prepareStatement(SQLString3);
            rs = statement.executeQuery();
            int i = 1;
            while (rs.next()) {
                vno.add(rs.getInt(i));
            }
            //=== get ordre
            for (int j = 0; j < vno.size(); j++) {
                statement = conn.prepareStatement(SQLString1);
                statement.setInt(1, (int) vno.get(j));
                rs = statement.executeQuery();
                if (rs.next()) {
                    v = new Vare(rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getDouble(4),
                            rs.getInt(5));
                }
                statement.close();
                statement = conn.prepareStatement(SQLString2);
                statement.setInt(1, v.getVnummer());
                rs.close();
                rs = statement.executeQuery();
                while (rs.next()) {
                    v.addDel(new Del(
                            v.getVnummer(),
                            rs.getString(2),
                            rs.getInt(3)));
                }
                vl.add(v);
                statement.close();
                rs.close();
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("Retrieved ordre: " + v);
        }
        return vl;
    }

    public Vare getVare(Connection conn, int vnummer) throws SQLException {
        String SQLString = "select * from varer where vnummer = ?";
        PreparedStatement statement = null;
        Vare vare = null;

        String SQLString2 = // get del
                "select * "
                + "from dele "
                + "where vnummer = ? ";
        ResultSet rs = null;

        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, vnummer);
            rs = statement.executeQuery();
            if (rs.next()) {
                vare = new Vare(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getInt(5));
            }
            statement.close();
            statement = conn.prepareStatement(SQLString2);
            statement.setInt(1, vnummer);
            rs.close();
            rs = statement.executeQuery();
            while (rs.next()) {
                vare.addDel(new Del(
                        vnummer,
                        rs.getString(2),
                        rs.getInt(3)));
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("Retrieved Varer: " + vare);
        }
        return vare;
    }

    public int getNextVnummer(Connection conn) throws SQLException {
        int nextVno = 0;
        String SQLString = "select varerseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            rs = statement.executeQuery();
            if (rs.next()) {
                nextVno = rs.getInt(1);
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return nextVno;
    }

    // Odetaljer
    public boolean updateOdetaljer(ArrayList<Odetaljer> odl, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update odetaljer "
                + "set maengde = ? "
                + "where onummer = ? and vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < odl.size(); i++) {
                Odetaljer od = odl.get(i);
                statement.setInt(1, od.getMaengde());
                statement.setInt(2, od.getOnummer());
                statement.setInt(3, od.getVnummer());
                int tupleUpdated = statement.executeUpdate();
                rowsUpdated += tupleUpdated;
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateOrdrer: " + (rowsUpdated == odl.size())); // for test
        }
        return (rowsUpdated == odl.size());
    }

    public boolean insertOdetaljer(ArrayList<Odetaljer> odl, Connection conn) throws SQLException {

        String SQLString = "insert into odetaljer values (?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        int rowsInserted = 0;
        try {
            for (int i = 0; i < odl.size(); i++) {
                if (!statement.isClosed()) {
                    statement.close();
                }
                statement = conn.prepareStatement(SQLString);
                statement.setInt(1, odl.get(i).getOnummer());
                statement.setInt(2, odl.get(i).getVnummer());
                statement.setInt(3, odl.get(i).getMaengde());
                rowsInserted += statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertOdetaljer:" + (rowsInserted == odl.size())); // for test
        }
        return rowsInserted == odl.size();
    }

    public boolean deleteOdetalje(int ono, Connection conn) throws SQLException {
        int deletedOrdrer = 0;
        String SQLString = "delete from odetaljer "
                + "where onummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        try {
            statement.setInt(1, ono);
            statement.executeUpdate();
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return (deletedOrdrer == 1);
    }

    // Del
    public boolean insertDel(ArrayList<Del> delListe, Connection conn) throws SQLException {
        String SQLString = "insert into odetaljer values (?,?,?)";
        PreparedStatement statement = null;

        int rowsInserted = 0;
        try {
            if (0 < delListe.size()) {
                statement = conn.prepareStatement(SQLString);
                for (int i = 0; i < delListe.size(); i++) {
                    statement.setInt(1, delListe.get(i).getVnummer());
                    statement.setString(2, delListe.get(i).getTitel());
                    statement.setInt(3, delListe.get(i).getAntal());
                    rowsInserted += statement.executeUpdate();
                    statement.close();
                }
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertVareDel:" + (rowsInserted == delListe.size())); // for test
        }
        return rowsInserted == delListe.size();
    }

    public boolean updateDele(ArrayList<Del> delListe, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update del "
                + "set antal = ? "
                + "where vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < delListe.size(); i++) {
                Del od = delListe.get(i);
                statement.setInt(1, od.getVnummer());
                statement.setString(2, od.getTitel());
                statement.setInt(3, od.getAntal());
                int tupleUpdated = statement.executeUpdate();
                rowsUpdated += tupleUpdated;
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateDel: " + (rowsUpdated == delListe.size())); // for test
        }
        return (rowsUpdated == delListe.size());
    }

    public boolean deleteDel(int vnummer, Connection conn) throws SQLException {
        int delDeleted = 0;
        String SQLString = "delete from del "
                + "where vnummer = ?";

        PreparedStatement statement = conn.prepareStatement(SQLString);
        try {
            statement.setInt(1, vnummer);
            statement.executeUpdate();
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return (delDeleted == 1);
    }

    // DelOrdrer
    public boolean insertDelOrdrer(ArrayList<DelOrdre> delordre, Connection conn) throws SQLException {
        String SQLString = "insert into delordre values (?,?,?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(SQLString);

        int rowsInserted = 0;
        try {
            if (0 < delordre.size()) {
                for (int i = 0; i < delordre.size(); i++) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                    statement = conn.prepareStatement(SQLString);
                    statement.setString(1, delordre.get(i).getTitle());
                    statement.setInt(2, delordre.get(i).getVnummer());
                    statement.setInt(3, delordre.get(i).getOnummer());
                    statement.setInt(4, delordre.get(i).getStatus());
                    statement.setInt(5, delordre.get(i).getMaengde());
                    rowsInserted += statement.executeUpdate();
                    statement.close();
                }
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("insertVareDel:" + (rowsInserted == delordre.size())); // for test
        }
        return rowsInserted == delordre.size();
    }

    public boolean updateDelOrdre(ArrayList<DelOrdre> dol, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update delo "
                + "set maengde = ?, title = ?, status = ? "
                + "where onummer = ? and vnummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < dol.size(); i++) {
                DelOrdre delo = dol.get(i);
                statement.setString(1, delo.getTitle());
                statement.setInt(2, delo.getVnummer());
                statement.setInt(1, delo.getOnummer());
                statement.setInt(2, delo.getStatus());
                statement.setInt(3, delo.getMaengde());
                int tupleUpdated = statement.executeUpdate();
                rowsUpdated += tupleUpdated;
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        if (testRun) {
            System.out.println("updateOrdrer: " + (rowsUpdated == dol.size())); // for test
        }
        return (rowsUpdated == dol.size());
    }

    public Postnummer getPostnummer(Connection conn, int postnr) throws SQLException {
        String SQLString = "select * from postnummer where postnummer = ?";
        PreparedStatement statement = null;
        Postnummer postnummer = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            statement.setInt(1, postnr);
            rs = statement.executeQuery();
            if (rs.next()) {
                postnummer = new Postnummer(rs.getInt(1),
                        rs.getString(2));
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return postnummer;
    }

    public int getNextFnummer(Connection conn) throws SQLException {
        int nextFnummer = 0;
        String SQLString = "select fakturaseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.prepareStatement(SQLString);
            rs = statement.executeQuery();
            if (rs.next()) {
                nextFnummer = rs.getInt(1);
            }
        } finally {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return nextFnummer;
    }
}
