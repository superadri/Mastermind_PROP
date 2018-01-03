/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author Sony
 */
public class TimeTest {

    public TimeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of getTime method, of class Time.
     */
    @Test
    public void testGetTime() {
        System.out.println("getTime");
        Time instance = new Time();
        double expResult = 0.0;
        double result = instance.getTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startTime method, of class Time.
     */
    @Test
    public void testStartTime() {
        System.out.println("startTime");
        Time instance = new Time();
        instance.startTime();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopTime method, of class Time.
     */
    @Test
    public void testStopTime() {
        System.out.println("stopTime");
        Time instance = new Time();
        instance.stopTime();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printTime method, of class Time.
     */
    @Test
    public void testPrintTime() {
        System.out.println("printTime");
        double totalSecs = 0.0;
        Time.printTime(totalSecs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
