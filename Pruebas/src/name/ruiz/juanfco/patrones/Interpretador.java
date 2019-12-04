package name.ruiz.juanfco.patrones;

import java.util.*;

/**
 * Analiza una linea de texto y devuelve un resultado.
 *
 * @author hamfree
 */
public interface Interpretador {

    /**
     * @param unaLinea es no nulo.
     * @param unResultado es una lista vacia, no nula la cual actua como un
     * parametro de "salida"; cuando vuelve, unResultado debe contener una lista
     * no nula, no vacia de elementos los cuales tienen todos un metodo
     * <code>toString</code>, para ser usados para mostrar un resultado al
     * usuario.
     *
     * @return true si el usuario ha pedido salir del Interpretador.
     * @exception IllegalArgumentException si un parametro no cumple.
     */
    boolean analizaEntrada(String unaLinea, List<Object> unResultado);

    /**
     * Devuelve el texto que se mostrara al iniciar el Interpretador.
     *
     * @return una cadena
     */
    String getCursorInicio();
}
