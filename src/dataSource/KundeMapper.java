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
import domain.Kunde;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class KundeMapper {
    
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

}
