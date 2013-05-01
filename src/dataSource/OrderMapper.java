package dataSource;

import domain.DelOrdre;
import domain.Odetaljer;
import domain.Ordre;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OrderMapper {

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
                if (!statement.isClosed()) {
                    statement.close();
                }
                statement = conn.prepareStatement(SQLString);
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
                if (!statement.isClosed()) {
                    statement.close();
                }
                statement = conn.prepareStatement(SQLString);
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
        return (rowsUpdated == ol.size());    // false if any conflict in version number             
    }

    public boolean deleteOrdrer(ArrayList<Ordre> ol, Connection conn) throws SQLException {
        int deletedOrdrer = 0;
        String SQLString1 = "delete from ordrer "
                + "where onummer = ?";
        String SQLString2 = "delete from odetaljer "
                + "where onummer = ? and vnummer = ? ";

        PreparedStatement statement1 = conn.prepareStatement(SQLString2);
        PreparedStatement statement2 = conn.prepareStatement(SQLString1);
        try {
            for (int i = 0; i < ol.size(); i++) {
                if (!statement1.isClosed()) {
                    statement1.close();
                }
                if (!statement2.isClosed()) {
                    statement2.close();
                }
                statement1 = conn.prepareStatement(SQLString2);
                statement2 = conn.prepareStatement(SQLString1);
                Ordre o = (Ordre) ol.get(i);
                for (int j = 0; j < o.getOd().size(); j++) {
                    statement1.setInt(1, o.getOnummer());
                    statement1.setInt(2, o.getOd().get(j).getVnummer());
                    statement1.executeUpdate();
                    statement1.close();
                }
                statement2.setInt(1, o.getOnummer());
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
