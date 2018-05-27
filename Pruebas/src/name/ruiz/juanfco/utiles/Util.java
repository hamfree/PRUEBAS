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

    private static final String SL = System.getProperty("line.separator");
    private static final String SF = System.getProperty("file.separator");
    private static final String CAR_INI = "[";
    private static final String CAR_FIN = "]";
    private static final String SEP = ", ";
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
     *
     * @param obj
     * @return
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
     * @throws Exception en caso de pasar parámetros nulos.
     */
    public static void imp(boolean usaConsola, Object... args) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (args == null) {
            throw new Exception("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
        }
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
     * Lista los contenidos del directorio para una carpeta de recursos. No es
     * recursiva. Esta es básicamente una implementación de fuerza bruta.
     * Funciona para ficheros regulares y también JARs.
     *
     * @author Greg Briggs
     * @param clazz Cualquier clase java que vive en el mismo lugar que los
     * recursos que quieres.
     * @param path Debe terminar con "/", pero no empezar con una.
     * @return Solo el nombre de cada elemento miembro, no las rutas completas.
     * @throws URISyntaxException en caso de error
     * @throws IOException en caso de error de entrada/salida
     */
    public String[] getResourceListing(Class clazz, String path) throws URISyntaxException, IOException {
        URL dirURL = clazz.getClassLoader().getResource(path);
        if (dirURL != null && dirURL.getProtocol().equals("file")) {
            /* Una ruta de fichero: suficientemente fácil */
            return new File(dirURL.toURI()).list();
        }

        if (dirURL == null) {
            /*
             * En caso de un fichero jar, no podemos en realidad encontrar un directorio.
             * Tengo que asumir el mismo jar como clase.
             */
            String me = clazz.getName().replace(".", "/") + ".class";
            dirURL = clazz.getClassLoader().getResource(me);
        }

        if (dirURL.getProtocol().equals("jar")) {
            /* Una ruta de JAR */
            // quito solo el fichero JAR
            String jarPath = dirURL.getPath().substring(5, dirURL.getPath().indexOf("!"));
            JarFile jar = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
            Enumeration<JarEntry> entries = jar.entries(); //da TODAS las entradas en el jar
            Set<String> result = new HashSet<>(); //evita duplicados en caso se que sea un subdirectorio
            while (entries.hasMoreElements()) {
                String name = entries.nextElement().getName();
                if (name.startsWith(path)) { //filtra segun la ruta
                    String entry = name.substring(path.length());
                    int checkSubdir = entry.indexOf("/");
                    if (checkSubdir >= 0) {
                        // si esto es un subdirectorio, simplemente devolvemos el nombre del directorio
                        entry = entry.substring(0, checkSubdir);
                    }
                    result.add(entry);
                }
            }
            return result.toArray(new String[result.size()]);
        }

        throw new UnsupportedOperationException("No puedo listar ficheros para la URL " + dirURL);
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
