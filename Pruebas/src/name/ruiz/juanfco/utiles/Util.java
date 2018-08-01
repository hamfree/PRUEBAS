package name.ruiz.juanfco.utiles;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Logger;

/**
 * Clase con metodos de utilidad usados frecuentemente por las otras clases de
 * la aplicación
 *
 * @author hamfree
 */
public class Util {

    /**
     * Para la depuracion.
     */
    private static final Logger LOG = Logger.getLogger(Util.class.getName());

    /**
     * Caracter de salto de linea del sistema operativo
     */
    private static final String SL = System.getProperty("line.separator");
    /**
     * Caracter separador de rutas del sistema operativo
     */
    private static final String SF = System.getProperty("file.separator");
    /**
     * Caracter que indica el inicio del contenido de un objeto
     */
    private static final String CAR_INI = "[";
    /**
     * Caracter que indica el final del contenido de un objeto
     */
    private static final String CAR_FIN = "]";
    /**
     * Caracter separador de items
     */
    private static final String SEP = ", ";
    /**
     * Constante que representa el valor textual de NULL
     */
    private static final String NULL = "null";

    /**
     * Evitamos que se pueda instanciar
     */
    private Util() {

    }

    /**
     * Obtiene todos los charsets soportados por la MVJ actual.
     *
     * @return una lista con todos los charsets soportados por la MVJ actual.
     */
    public static List<Charset> getAllCharsets() {
        SortedMap<String, Charset> sm;
        ArrayList<Charset> al = null;
        sm = Charset.availableCharsets();
        Iterator<Charset> it = sm.values().iterator();
        if (it != null) {
            al = new ArrayList<>();
            while (it.hasNext()) {
                Charset ch = it.next();
                al.add(ch);
            }
        }
        return al;
    }

    /**
     * Genera una cadena compuesta por el caracter <code>car</code> tantas veces
     * como indica <code>veces</code>.
     *
     * @param car el caracter con el que va a estar compuesta la cadena
     * @param veces el numero de veces que se repite el caracter en la cadena
     * @return una cadena con el caracter <code>car</code> repetida tantas veces
     * como indica <code>veces</code>
     */
    public static String repiteCaracter(Character car, int veces) {
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        if (car != null && veces > 0) {
            for (int i = 0; i < veces; i++) {
                sb.append(car);
            }
        }
        return sb.toString();
    }

    //Getters y Setters
    public static String getSL() {
        return SL;
    }

    public static String getSF() {
        return SF;
    }

    public static String getCAR_INI() {
        return CAR_INI;
    }

    public static String getCAR_FIN() {
        return CAR_FIN;
    }

    public static String getSEP() {
        return SEP;
    }

    public static String getNULL() {
        return NULL;
    }

    /**
     * @return el log
     */
    public static Logger getLog() {
        return LOG;
    }
}
