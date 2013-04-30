/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.Del;
import domain.Vare;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class VareMapper {
    
    static boolean testRun = false;
    
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
}
