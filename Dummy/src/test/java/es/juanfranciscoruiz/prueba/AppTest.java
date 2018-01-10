/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.juanfranciscoruiz.prueba;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Prueba la clase App de la aplicacion Dummy
 *
 * @author hamfree
 */
public class AppTest extends TestCase {

    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AppTest.class);
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of main method, of class App.
     */
    public void testMain() {
        System.out.println("main");
        String[] args = {"test"};
        App.main(args);
        String expected = "test";
        assertEquals(expected, args[0]);
    }

}
