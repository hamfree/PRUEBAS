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
 * Excepción lanzada cuando un producto no se encuentra en un carrito de compras.
 *
 * @author <a href="mailto:mike@clarkware.com">Mike Clark</a>
 * @author <a href="http://www.clarkware.com">Clarkware Consulting, Inc.</a>
 */
public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = -8958691386567827084L;

    /**
     * Construye una <b>ProductNotFoundException</b>.
     */
    public ProductNotFoundException() {
        super();
    }
}
