package es.juanfranciscoruiz.prueba;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Implementación del conjunto de pruebas de Dummy
 *
 * @author hamfree
 */
public class PruebaSuite extends TestCase {

    public PruebaSuite(String testName) {
        super(testName);
    }

    /**
     * Compone el conjunto de pruebas y lo devuelve.
     *
     * @return Devuelve un TestSuite
     * @see TestSuite
     */
    public static Test suite() {
        TestSuite suite = new TestSuite("PruebaSuite");
        suite.addTest(TutorialTest.suite());

        // No tiene sentido probar la clase que funciona como punto de entrada.
        //suite.addTest(AppTest.suite());
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
