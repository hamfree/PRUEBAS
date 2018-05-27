package name.ruiz.juanfco.utiles;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Clase con metodos de utilidad usados frecuentemente por las otras clases de
 * la aplicación
 *
 * @author hamfree
 */
public class Util {
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

    // Métodos de utilidad
    /**
     * Indica si el objeto pasado es nulo o está vacío (en el caso de ser una
     * colección, u otro objeto contenedor de objetos.
     *
     * @param obj el objeto a comprobar
     * @return true si el objeto es nulo o está vacío, false en el caso
     * contrario.
     */
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            return obj.toString().length() == 0;
        } else if (obj.getClass().isArray()) {
            List l = Arrays.asList(obj);
            return l.isEmpty();
        } else if (obj.getClass().isAssignableFrom(Collection.class)) {
            Collection col = (Collection) obj;
            return col.isEmpty();
        }
        return false;
    }

    /**
     * Imprime los argumentos indicados en la salida estándar. Puede usar el
     * objeto Console si se pasa true al parámetro usaConsola. En caso contrario
     * usara el canal estandar de salida.
     *
     * @param usaConsola booleano que si es true intentará usar Console.
     * @param args una lista de objetos a imprimir
     * @throws IllegalArgumentException en caso de pasar parámetros nulos.
     */
    public static void imp(boolean usaConsola, Object... args) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (args == null) {
            throw new IllegalArgumentException("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
        }
        imprime(sb, usaConsola);
    }

    /**
     * Imprime los argumentos indicados en la salida estándar. Puede usar el
     * objeto Console si se pasa true al parámetro usaConsola. En caso contrario
     * usara el canal estandar de salida. Despues de imprimir la lista de
     * argumentos hará tantos saltos de línea como indiquemos en el parámetro sl
     *
     * @param usaConsola booleano que si es true intentará usar Console.
     * @param sl entero con la cantidad de saltos de línea a realizar
     * @param args una lista de objetos a imprimir
     * @throws IllegalArgumentException en caso de pasar parámetros nulos.
     */
    public static void impsl(boolean usaConsola, int sl, Object... args) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (args == null) {
            throw new IllegalArgumentException("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
            if (sl > 0) {
                for (int i = 0; i < sl; i++) {
                    sb.append(Util.SL);
                }
            }
        }
        imprime(sb, usaConsola);
    }

    /**
     *
     * @param sb
     * @param usaConsola
     */
    private static void imprime(StringBuilder sb, boolean usaConsola) {
        if (usaConsola) {
            Console con = System.console();
            if (con == null) {
                System.out.format("%s", sb.toString());
            } else {
                con.printf("%s", sb.toString());
            }
        } else {
            System.out.format("%s", sb.toString());
        }
    }

    /**
     *
     * @param usaConsola
     * @return
     * @throws java.lang.Exception
     */
    public static String read(boolean usaConsola) throws Exception {
        String dato = "";
        if (usaConsola) {
            Console con = System.console();
            if (con == null) {
                Scanner sc = new Scanner(System.in);
                dato = sc.nextLine();
            } else {
                dato = con.readLine();
            }
        }
        return dato;
    }

    /**
     *
     * @param dato
     * @return
     */
    public boolean isInteger(String dato) {
        if (!isNullOrEmpty(dato)) {
            try {
                int tmp = Integer.parseInt(dato);
                return true;
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @param dato
     * @return
     */
    public boolean isDouble(String dato) {
        if (!isNullOrEmpty(dato)) {
            try {
                double tmp = Double.parseDouble(dato);
            } catch (NumberFormatException ex) {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public List<Charset> getAllCharsets() {
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
     *
     * @param theChar
     * @param times
     * @return
     */
    public static String getCharNTimes(char theChar, int times) {
        StringBuilder sb = new StringBuilder();
        Character c = theChar;
        if (c > 32 && times > 0) {
            for (int i = 0; i < times; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Devuelve una representación textual de una matriz.
     *
     * @param unaMatriz La matriz de la que se quiere su representación textual.
     * @return una cadena con la representacion textual de la matriz o la
     * constante NULL ("null").
     */
    public static String array2String(Object unaMatriz) {
        if (unaMatriz == null) {
            return NULL;
        }
        compruebaObjetoEsUnaMatriz(unaMatriz);

        StringBuilder result = new StringBuilder(CAR_INI);
        int length = Array.getLength(unaMatriz);
        for (int idx = 0; idx < length; ++idx) {
            Object item = Array.get(unaMatriz, idx);
            if (esUnaMatrizNoNula(item)) {
                //recursive call!
                result.append(array2String(item));
            } else {
                result.append(item);
            }
            if (!esUltimoElemento(idx, length)) {
                result.append(SEP);
            }
        }
        result.append(CAR_FIN);
        return result.toString();
    }

    //Metodos de utilidad y ayuda para array2String()
    /**
     *
     * @param unaMatriz
     */
    private static void compruebaObjetoEsUnaMatriz(Object unaMatriz) {
        if (!unaMatriz.getClass().isArray()) {
            throw new IllegalArgumentException("El Objeto no es una matriz.");
        }
    }

    /**
     *
     * @param unElmento
     * @return
     */
    private static boolean esUnaMatrizNoNula(Object unElmento) {
        return unElmento != null && unElmento.getClass().isArray();
    }

    /**
     *
     * @param unIndice
     * @param unaLongitud
     * @return
     */
    private static boolean esUltimoElemento(int unIndice, int unaLongitud) {
        return (unIndice == unaLongitud - 1);
    }
}
