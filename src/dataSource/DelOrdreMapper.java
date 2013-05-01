/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import domain.DelOrdre;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class DelOrdreMapper {
    
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
        return rowsInserted == delordre.size();
    }

    public boolean updateDelOrdre(ArrayList<DelOrdre> dol, Connection conn) throws SQLException {
        int rowsUpdated = 0;
        String SQLString = "update delordre "
                + "set status = ?, maengde = ? "
                + "where title = ? and vnummer = ? and onummer = ?";
        PreparedStatement statement = null;

        statement = conn.prepareStatement(SQLString);
        try {
            for (int i = 0; i < dol.size(); i++) {
                DelOrdre delo = dol.get(i);
                statement.setInt(1, delo.getStatus());
                statement.setInt(2, delo.getMaengde());
                statement.setString(3, delo.getTitle());
                statement.setInt(4, delo.getVnummer());
                statement.setInt(5, delo.getOnummer());
                rowsUpdated += statement.executeUpdate();
                statement.close();
            }
        } finally {
            if (!statement.isClosed()) {
                statement.close();
            }
        }
        return (rowsUpdated == dol.size());
    }
}
