package name.ruiz.juanfco.pruebas.consola;

import java.util.*;

/**
 * Dado un nombre de clase cualificado con paquete, devuelve los nombre de las
 * clases en el arbol de herencia.
 */
public final class InterpretadorHerencia implements Interpretador {

    /**
     * @param unaLinea es el nombre no nulo de una clase.
     * @param unResultado es una lista vacia, no nula, que actua como un
     * parametro de "salida"; cuando vuelve, unResultado debe contener una lista
     * no vacia y no nula, de nombres de clase las cuales forman el arbol de
     * herencia de la clase de entrada.
     *
     * @return true si el usuario ha solictiado terminar el Interpretador.
     * @exception IllegalArgumentException si un parametro no cumple.
     */
    @Override
    public boolean analizaEntrada(String unaLinea, final List unResultado) {
        if (unResultado == null) {
            throw new IllegalArgumentException("El parametro de resultado no puede ser nulo.");
        }
        if (!unResultado.isEmpty()) {
            throw new IllegalArgumentException("El parametro de resultado debe estar vacio.");
        }
        if (unaLinea == null) {
            throw new IllegalArgumentException("La linea no debe ser nula.");
        }

        boolean haSolicitadoTerminar = unaLinea.trim().equalsIgnoreCase(ABANDONAR)
                || unaLinea.trim().equalsIgnoreCase(SALIDA);
        if (haSolicitadoTerminar) {
            unResultado.add(NUEVA_LINEA);
        } else {
            try {
                Class laClase = Class.forName(unaLinea);
                StringBuilder superclases = new StringBuilder();
                superclases.append(CABECERA);
                superclases.append(NUEVA_LINEA);
                while (laClase != null) {
                    superclases.append(laClase);
                    superclases.append(NUEVA_LINEA);
                    laClase = laClase.getSuperclass();
                }
                unResultado.add(superclases);
                unResultado.add(CURSOR_POR_DEFECTO);
            } catch (ClassNotFoundException ex) {
                //recover by asking the user for corrected input
                unResultado.clear();
                unResultado.add(CURSOR_ERROR);
            }
        }

        assert !unResultado.isEmpty() : "El resultado debe ser no vacio.";

        return haSolicitadoTerminar;
    }

    /**
     * Devuelve el texto a visualizar al inicio del Interpretador.
     */
    @Override
    public String getCursorInicio() {
        return CURSOR_INICIO;
    }

    // PRIVADO
    private static final String CURSOR_INICIO = "Por favor introduce un nombre de clase>";
    private static final String CURSOR_POR_DEFECTO = "Por favor introduce un nombre de clase>";
    private static final String CURSOR_ERROR = "No valido.  Ejemplo:\"java.lang.String\">";
    private static final String CABECERA = "El arbol de herencia:";
    private static final String ABANDONAR = "abandonar";
    private static final String SALIDA = "salida";
    private static final String NUEVA_LINEA = System.getProperty("line.separator");
}
