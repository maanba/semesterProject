/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import semesterprojekt.Del;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class DelMapper {

    static boolean testRun = false;

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
}
