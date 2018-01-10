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
 *
 * @author hamfree
 */
public class ProbarTerminalTest extends TestCase {

    public ProbarTerminalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ProbarTerminalTest.class);
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
     * Test of tutorial1 method, of class ProbarTerminal.
     */
    public void testTest() throws Exception {
        System.out.println("test");
        ProbarTerminal instance = new ProbarTerminal();
        boolean encontrado = instance.tutorial1();
        assertEquals(true, encontrado);

    }

    /**
     * Test of tutorial2 method, of class ProbarTerminal.
     */
    public void testTest2() {
        System.out.println("test2");
        ProbarTerminal instance = new ProbarTerminal();
        boolean encontrado = instance.tutorial2();
        assertEquals(true, encontrado);
    }

}
