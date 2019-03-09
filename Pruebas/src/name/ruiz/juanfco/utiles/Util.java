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
     * @return el log
     */
    public static Logger getLog() {
        return LOG;
    }
}
