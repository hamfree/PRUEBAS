/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.juanfranciscoruiz;

import es.juanfranciscoruiz.prueba.PruebaSuite;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author hamfree
 */
public class JuanfranciscoruizSuite extends TestCase {

    public JuanfranciscoruizSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("JuanfranciscoruizSuite");
        suite.addTest(PruebaSuite.suite());
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

}
