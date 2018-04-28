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
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author hamfree
 */
public class ProductNotFoundExceptionTest {

    private ShoppingCart _bookCart;

    public ProductNotFoundExceptionTest() {
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
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        try {
            Product book = new Product("Ender's Game", 4.95);
            _bookCart.removeItem(book);

            fail("Debería lanzar una ProductNotFoundException");
        } catch (ProductNotFoundException success) {
            // test que termina bien
        }
    }

}
