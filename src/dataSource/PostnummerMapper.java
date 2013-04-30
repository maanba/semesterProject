/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import semesterprojekt.Postnummer;

/**
 *
 * @author Daniel Krarup Knudsen
 */
public class PostnummerMapper {
    
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
}
