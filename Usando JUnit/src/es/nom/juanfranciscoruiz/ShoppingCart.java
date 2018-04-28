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

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Un ejemplo de carrito de la compra. Esta clase no debe confundirse con un carrito de la compra de
 * calidad de producción. Simplemente se proporciona como una clase de ejemplo bajo prueba como se
 * describe en JUnitPrimer.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting, Inc.</a>
 */
public class ShoppingCart {

    private ArrayList _items;

    /**
     * Construye una instancia de ShoppingCart.
     */
    public ShoppingCart() {
        _items = new ArrayList();
    }

    /**
     * Devuelve el balance.
     *
     * @return Balance.
     */
    public double getBalance() {
        Iterator i = _items.iterator();
        double balance = 0.00;
        while (i.hasNext()) {
            Product p = (Product) i.next();
            balance = balance + p.getPrice();
        }

        return balance;
    }

    /**
     * Agrega el producto especificado.
     *
     * @param p un producto.
     */
    public void addItem(Product p) {
        _items.add(p);
    }

    /**
     * Elimina el producto especificado.
     *
     * @param p El producto a eliminar.
     * @throws ProductNotFoundException si el producto no existe.
     */
    public void removeItem(Product p) throws ProductNotFoundException {
        if (!_items.remove(p)) {
            throw new ProductNotFoundException();
        }
    }

    /**
     * Devuelve el número de elementos en el carrito.
     *
     * @return el total de elementos.
     */
    public int getItemCount() {
        return _items.size();
    }

    /**
     * Vacía el carrito.
     */
    public void empty() {
        _items = new ArrayList();
    }

    /**
     * Indica si el carrito está vacío.
     *
     * @return true si el carrito está vacío; falso en caso contrario.
     */
    public boolean isEmpty() {
        return (_items.size() == 0);
    }
}
