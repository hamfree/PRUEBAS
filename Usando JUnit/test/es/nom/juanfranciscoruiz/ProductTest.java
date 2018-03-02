/*
 * Copyright 2018 hamfree.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.nom.juanfranciscoruiz;

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
public class ProductTest {

    public ProductTest() {
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
     * Test of getTitle method, of class Product.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Product instance = new Product("Etica a Nicomaco", 23.99);
        String expResult = "Etica a Nicomaco";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = null;
        instance = new Product("2001, una odisea del espacio", 17.13);
        double expResult = 17.13;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = new Product("Mi producto", 12.30);
        Product instance = new Product("Mi producto", 12.30);

        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }

}
