/*
  * Copyright 2018 hamfree.
 * Licencia bajo la Licencia Apache, Versión 2.0 (la "Licencia");
 * no puede usar este archivo excepto en conformidad con la Licencia.
 * Puede obtener una copia de la Licencia en http://www.apache.org/licenses/LICENSE-2.0
 * A menos que lo exija la ley aplicable o se acuerde por escrito, el software
 * distribuido bajo la Licencia se distribuye "TAL CUAL", SIN GARANTÍAS O CONDICIONES DE NINGÚN
 * TIPO, ya sean expresas o implícitas.
 * Consulte la Licencia para el idioma específico que rige los permisos y limitaciones bajo la
 * licencia.
 */
package es.nom.juanfranciscoruiz;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hamfree
 */
public class ShoppingCartTest {

    private ShoppingCart _bookCart;
    private Product _defaultBook;

    public ShoppingCartTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        _bookCart = new ShoppingCart();

        _defaultBook = new Product("Extreme Programming", 23.95);
        _bookCart.addItem(_defaultBook);
    }

    @After
    public void tearDown() {
        _bookCart = null;
    }

    /**
     * Test of getBalance method, of class ShoppingCart.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(_defaultBook);
        double expResult = 23.95;
        double result = instance.getBalance();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addItem method, of class ShoppingCart.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Product newBook = new Product("Refactoring", 53.95);
        _bookCart.addItem(newBook);

        double expectedBalance = _defaultBook.getPrice() + newBook.getPrice();

        assertEquals(expectedBalance, _bookCart.getBalance(), 0.0);

        assertEquals(2, _bookCart.getItemCount());
    }

    /**
     * Test of removeItem method, of class ShoppingCart.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRemoveItem() throws Exception {
        System.out.println("removeItem");
        _bookCart.removeItem(_defaultBook);

        assertEquals(0, _bookCart.getItemCount());

        assertEquals(0.0, _bookCart.getBalance(), 0.0);
    }

    /**
     * Test of getItemCount method, of class ShoppingCart.
     */
    @Test
    public void testGetItemCount() {
        System.out.println("getItemCount");
        ShoppingCart instance = new ShoppingCart();
        instance.addItem(_defaultBook);
        int expResult = 1;
        int result = instance.getItemCount();
        assertEquals(expResult, result);
    }

    /**
     * Test of empty method, of class ShoppingCart.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        _bookCart.empty();

        assertTrue(_bookCart.isEmpty());
    }

    /**
     * Test of isEmpty method, of class ShoppingCart.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ShoppingCart instance = new ShoppingCart();
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    public void testItemNotFound() {
        System.out.println("itemNotFound");
        try {
            Product book = new Product("Ender's Game", 4.95);
            _bookCart.removeItem(book);

            fail("Debería lanzar una ProductNotFoundException");
        } catch (ProductNotFoundException success) {
            // test que termina bien
        }
    }
}
