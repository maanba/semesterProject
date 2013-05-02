/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Ordre;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author DragønEye
 */
public class OrderMapperTest {

    private Connection con = null;
    private ArrayList<Ordre> ol = new ArrayList<>();
    private int ono1;
    private int ono2;

    public void getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@delfi.lyngbyes.dk:1521:KNORD", "CPHMB15", "CPHMB15");
        } catch (Exception e) {
            System.out.println("fejl i test.SetUp()");
            System.out.println(e);
        }
    }

    public OrderMapperTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        getConnection();
        ol.add(new Ordre("",0, 0, 67228, 200.0, 10.0, 60.0, "", "", "Afhentes af kunden", 0, "Påbegyndt", "10-01-2013", "11-01-2013", "12-01-2013", 1));
        ol.add(new Ordre("",0, 0, 67227, 500.0, 20.0, 100.0, "16:00", "14:00", "Leveres af os", 1, "Påbegyndt", "10-10-2013", "11-10-2013", "12-10-2013", 1));
        ono1 = 111111;
        ono2 = 111112;
        ol.get(0).setOnummer(ono1);
        ol.get(1).setOnummer(ono2);
    }

    @After
    public void tearDown() {
        try {
            con.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     * Test of insertOrdrer method, of class OrderMapper.
     */
    @Test
    public void testInsertOrdrer() throws Exception {
        System.out.println("insertOrdrer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        boolean expResult = true;
        boolean result = instance.insertOrdrer(ol, conn);
        assertEquals(expResult, result);
        Ordre o = instance.getOrdre(ono1, conn);
        assertTrue(o.getOnummer() == ol.get(0).getOnummer());
        assertTrue(o.getKnummer() == ol.get(0).getKnummer());
        assertTrue(o.getAfhentning().equals(ol.get(0).getAfhentning()));
        assertTrue(o.getModtaget().equals(ol.get(0).getModtaget()));
        assertTrue(o.getLevering().equals(ol.get(0).getLevering()));
        assertTrue(o.getReturnering().equals(ol.get(0).getReturnering()));
        o = instance.getOrdre(ono2, conn);
        assertTrue(o.getOnummer() == ol.get(1).getOnummer());
        assertTrue(o.getKnummer() == ol.get(1).getKnummer());
        assertTrue(o.getAfhentning().equals(ol.get(1).getAfhentning()));
        assertTrue(o.getModtaget().equals(ol.get(1).getModtaget()));
        assertTrue(o.getLevering().equals(ol.get(1).getLevering()));
        assertTrue(o.getReturnering().equals(ol.get(1).getReturnering()));
    }

    /**
     * Test of updateOrdrer method, of class OrderMapper.
     */
    @Test
    public void testUpdateOrdrer() throws Exception {
        System.out.println("updateOrdrer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        boolean expResult = true;
        ol.get(0).setKnummer(67226);
        ol.get(0).setPris(300);
        ol.get(0).setRabat(15);
        ol.get(0).setModtaget("20-10-2000");
        ol.get(1).setKnummer(67228);
        ol.get(1).setPris(600);
        ol.get(1).setRabat(25);
        ol.get(1).setModtaget("10-10-2000");
        boolean result = instance.updateOrdrer(ol, conn);
        assertEquals(expResult, result);
        Ordre o = instance.getOrdre(ono1, conn);
        assertEquals(67226, o.getKnummer());
        assertTrue(300 == o.getPris());
        assertTrue(15 == o.getRabat());
        assertEquals("20-10-2000", o.getModtaget());
        o = instance.getOrdre(ono2, conn);
        assertEquals(67228, o.getKnummer());
        assertTrue(600 == o.getPris());
        assertTrue(25 == o.getRabat());
        assertEquals("10-10-2000", o.getModtaget());
    }

    /**
     * Test of deleteOrdrer method, of class OrderMapper.
     */
    @Test
    public void testDeleteOrdrer() throws Exception {
        System.out.println("deleteOrdrer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        boolean expResult = true;
        boolean result = instance.deleteOrdrer(ol, conn);
        assertEquals(expResult, result);
        assertNull(instance.getOrdre(ono1, conn));
        assertNull(instance.getOrdre(ono2, conn));
    }

    /**
     * Test of getAllOrdrer method, of class OrderMapper.
     */
    @Test
    public void testGetAllOrdrer() throws Exception {
        System.out.println("getAllOrdrer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        int expResult = 2;
        instance.insertOrdrer(ol, conn);
        ArrayList result = instance.getAllOrdrer(conn);
        assertEquals(expResult, result.size());
        ArrayList<Ordre> ar = new ArrayList();
        int ono = instance.getNextOnummer(conn);
        int fno = instance.getNextFnummer(conn);
        ar.add(new Ordre("", ono, fno, 67226, 40, 0.00, 20, "16:00", "14:00", "Leveres af os", 1, "Påbegyndt", "10-SEP-94", "10-DEC-94","12-DEC-94", 2));
        instance.insertOrdrer(ar, conn);
        expResult = 3;
        result = instance.getAllOrdrer(conn);
        assertEquals(expResult, result.size());
        instance.deleteOrdrer(ol, conn);
        expResult = 1;
        result = instance.getAllOrdrer(conn);
        assertEquals(expResult, result.size());
        instance.deleteOrdrer(ar, conn);
        expResult = 0;
        result = instance.getAllOrdrer(conn);
        assertEquals(expResult, result.size());
    }

    /**
     * Test of getOrdre method, of class OrderMapper.
     */
    @Test
    public void testGetOrdre() throws Exception {
        System.out.println("getOrdre");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        int ono = instance.getNextOnummer(conn);
        int fno = instance.getNextFnummer(conn);
        Ordre expResult = new Ordre("", ono, fno, 67226, 40, 0.00, 20, "16:00", "14:00", "Leveres af os", 1, "Påbegyndt", "10-SEP-94", "10-DEC-94","12-DEC-94", 2);
        ArrayList<Ordre> ar = new ArrayList();
        ar.add(expResult);
        instance.insertOrdrer(ar, conn);
        Ordre result = instance.getOrdre(ono, conn);
        assertEquals(expResult.getOnummer(), result.getOnummer());
        instance.deleteOrdrer(ar, conn);
    }

    /**
     * Test of getNextOnummer method, of class OrderMapper.
     */
    @Test
    public void testGetNextOnummer() throws Exception {
        System.out.println("getNextOnummer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        int expResult = 123458;
        int result1 = instance.getNextOnummer(conn);
        int result2 = instance.getNextOnummer(conn);
        assertNotSame(result2, result1);
        assertTrue(result1 < result2);
        assertTrue(result1 > expResult);
    }

    /**
     * Test of getNextFnummer method, of class OrderMapper.
     */
    @Test
    public void testGetNextFnummer() throws Exception {
        System.out.println("getNextFnummer");
        Connection conn = con;
        OrderMapper instance = new OrderMapper();
        int expResult = 123456;
        int result1 = instance.getNextFnummer(conn);
        int result2 = instance.getNextFnummer(conn);
        assertNotSame(result2, result1);
        assertTrue(result1 < result2);
        assertTrue(result1 > expResult);
    }
}
