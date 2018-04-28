/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.servicio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import name.ruiz.juanfco.modelo.Poblacion;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hamfree
 */
public class ImportaCSVimplTest {

    public ImportaCSVimplTest() {
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

    @After
    public void tearDown() {
    }

    /**
     * Test of importa method, of class ImportaCSVimpl.
     */
    @Test
    public void testImporta() {
        System.out.println("importa");
        File fcsv = new File("C:\\Users\\hamfree\\Desktop\\TEMPORAL\\municipiosPruebas.csv");
        ImportaCSVimpl instance = new ImportaCSVimpl();
        List<Poblacion> expResult = new ArrayList<>();
        expResult.add(new Poblacion("914", "18", "01", "Valderrubio"));
        expResult.add(new Poblacion("915", "18", "01", "Domingo Pérez de Granada"));
        expResult.add(new Poblacion("001", "04", "01", "Abla"));
        List<Poblacion> result = instance.importa(fcsv);
        System.out.println("*** resultado importa() ***");
        for (Poblacion p : result) {
            System.out.println(p.toString());
        }
        System.out.println("*** resultado esperado ***");
        for (Poblacion p : expResult) {
            System.out.println(p.toString());
        }
        assertEquals(expResult, result);
    }

}
