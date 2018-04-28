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

/**
 * Un ejemplo de producto para usar en el ejemplo del carrito de la compra.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting, Inc.</a>
 */
public class Product {

    private String _title;
    private double _price;

    /**
     * Cosntruye un <b>Product</b>.
     *
     * @param title título del producto.
     * @param price precio del producto.
     */
    public Product(String title, double price) {
        _title = title;
        _price = price;
    }

    /**
     * Devuelve el título del producto.
     *
     * @return un título.
     */
    public String getTitle() {
        return _title;
    }

    /**
     * Devuelve el precio de un producto.
     *
     * @return el precio.
     */
    public double getPrice() {
        return _price;
    }

    /**
     * Prueba la igualdad del producto con otro que se le pase.
     *
     * @return true si los productos son iguales.
     */
    public boolean equals(Object o) {

        if (o instanceof Product) {
            Product p = (Product) o;
            return p.getTitle().equals(_title);
        }

        return false;
    }
}
