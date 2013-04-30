/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.Odetaljer;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class OdetaljeMapper {
    
    static boolean testRun = false; 
    
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
}
