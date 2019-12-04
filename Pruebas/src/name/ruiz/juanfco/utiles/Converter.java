/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.utiles;

import name.ruiz.juanfco.consola.IO;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static name.ruiz.juanfco.utiles.Types.isArray;
import static name.ruiz.juanfco.utiles.Types.isNullOrEmpty;

/**
 *
 * @author hamfree
 */
public class Converter {

    /**
     * Para la depuracion.
     */
    private final static Logger LOG = Logger.getLogger(Converter.class.getName());

    ;

    /**
     * Convierte una coleccion de tipo genérico a una ArrayList de tipo generico
     *
     * @param <T>
     * @param clazz La clase del tipo de la colección
     * @param c la colección a convertir
     * @return Un ArrayList con el contenido de la colección convertido.
     */
    public static <T> List<T> coleccion2Lista(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for (Object o : c) {
            r.add(clazz.cast(o));
        }
        return r;
    }

    /**
     * Convierte un mapa genérico en una lista generica
     *
     * @param <T> el tipo genérico tanto del mapa como el de la lista
     * @param clazz la clase del tipo genérico T
     * @param m el mapa a convertir
     * @return una lista con el contenido del mapa convertido.
     */
    public static <T> List<T> mapa2Lista(Class<? extends T> clazz, Map<String, Object> m) {
        List<T> r = new ArrayList<T>(m.size());

        for (Map.Entry<String, Object> entrada : m.entrySet()) {
            r.add(clazz.cast(entrada.getValue()));
        }
        return r;
    }

    /**
     * Extrae los dígitos existentes en un String y los devuelve en una cadena.
     *
     * @param src la cadena que puede contener dígitos.
     * @return una cadena que sólo contiene digitos.
     */
    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * Extrae un long de una cadena
     *
     * @param src la cadena que puede contener dígitos
     * @return un long a partir de los dígitos existentes en la cadena.
     */
    public static Long extractLongFromString(String src) {
        Long numLargo = new Long(-1);
        try {
            if (src != null) {
                // 'Saneamos' la entrada, porque puede venir con caracteres que
                // no son digitos...
                src = src.trim();
                src = src.replaceAll("\\D+", ""); // expresion regular
                // que 'solo' deja
                // pasar digitos...
                Number number = NumberFormat.getInstance().parse(src); // hace
                // la
                // conversión
                numLargo = number.longValue();
            }

        } catch (NumberFormatException | ParseException ex) {
            LOG.severe(ex.getLocalizedMessage());
            numLargo = new Long(-1);
        }

        if (LOG.isLoggable(Level.SEVERE)) {
            LOG.severe(numLargo.toString());
        }
        return numLargo;
    }

    /**
     * Extrae un double de una cadena
     *
     * @param src una cadena que puede contener dígitos que pueden     * extraerse para generar un dobule
     * @return un double a partir de los dígitos de la cadena
     */
    public static Double extractDoubleFromString(String src) {
        Double numDecimal = new Double(-1);
        try {
            if (src != null) {
                // 'Saneamos' la entrada, porque puede venir con caracteres que
                // no son digitos...
                src = src.trim();
                src = src.replaceAll("[^\\.0123456789]", "");
                /* ¿Y los negativos? Por hacer */
                Number number = NumberFormat.getInstance().parse(src);
                /* hace la conversión */
                numDecimal = number.doubleValue();
            }

        } catch (NumberFormatException | ParseException ex) {
            LOG.severe(ex.getLocalizedMessage());
            numDecimal = Double.NaN;
        }

        if (LOG.isLoggable(Level.SEVERE)) {
            LOG.severe(numDecimal.toString());
        }

        return numDecimal;
    }

    /**
     * Devuelve una representación textual de una matriz.
     *
     * @param obj La matriz de la que se quiere su representación textual.
     * @return una cadena con la representacion textual de la matriz o la
     * constante NULL ("null").
     */
    public static String array2String(Object obj) {
        StringBuilder result = null;
        if (obj == null) {
            return IO.getNULL();
        }
        if (isArray(obj)) {
            result = new StringBuilder(IO.getCAR_INI());
            int length = Array.getLength(obj);
            for (int idx = 0; idx < length; ++idx) {
                Object item = Array.get(obj, idx);
                if (isNotNullArray(item)) {
                    //recursive call!
                    result.append(array2String(item));
                } else {
                    result.append(item);
                }
                if (!isLastElement(idx, length)) {
                    result.append(IO.getSEP());
                }
            }
            result.append(IO.getCAR_FIN());
        } else {
            return null;
        }
        return result.toString();
    }

    /**
     * Devuelve una representación textual de los bytes de un array.
     *
     * @param array el vector de bytes
     * @param showLength si es true mostrará además la longitud del vector.
     * @param showIndex
     * @return una representacion textual del vector de bytes.
     */
    public static String arrayByte2String(byte[] array, boolean showLength, boolean showIndex) {
        StringBuilder sb = new StringBuilder();
        if (!isNullOrEmpty(array)) {
            if (showLength) {
                sb.append("(").append(array.length).append(" bytes), ");
            }
            for (int i = 0; i < array.length; i++) {
                if (showIndex) {
                    sb.append("[").append(i).append("]=");
                }
                sb.append(array[i]).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        } else {
            return "";
        }
        return sb.toString();
    }

    //Metodos de utilidad y ayuda para array2String()
    /**
     *
     * @param obj
     * @return
     */
    private static boolean isNotNullArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    /**
     *
     * @param index
     * @param length
     * @return
     */
    private static boolean isLastElement(int index, int length) {
        return (index == length - 1);
    }

    /**
     * @return el log
     */
    public static Logger getLog() {
        return LOG;
    }

}
