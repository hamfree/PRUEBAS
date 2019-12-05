/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nom.juanfranciscoruiz.mybatisdemo.util;

import java.io.Console;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class IO {

    private static final Logger LOG = Logger.getLogger(IO.class.getName());

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
     * Imprime los argumentos indicados en la salida estándar. Puede usar el
     * objeto Console si se pasa true al parámetro usaConsola. En caso contrario
     * usara el canal estandar de salida.
     *
     * @param usaConsola booleano que si es true intentará usar Console.
     * @param args una lista de objetos a imprimir
     * @throws IllegalArgumentException en caso de pasar parámetros nulos.
     */
    public static void prt(boolean usaConsola, Object... args) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (args == null) {
            throw new IllegalArgumentException("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
        }
        print(sb, usaConsola);
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
    public static void prtln(boolean usaConsola, int sl, Object... args) throws IllegalArgumentException {
        StringBuilder sb = new StringBuilder();
        if (args == null) {
            throw new IllegalArgumentException("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
            if (sl > 0) {
                for (int i = 0; i < sl; i++) {
                    sb.append(SL);
                }
            }
        }
        print(sb, usaConsola);
    }

    /**
     *
     * @param sb
     * @param usaConsola
     */
    private static void print(StringBuilder sb, boolean usaConsola) {
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
     * Recupera en una cadena lo introducido por el usuario en la entrada
     * estándar hasta que pulse INTRO.
     *
     * @param usaConsola si es true intentará usar el objeto Console para
     * recoger lo introducido por el usuario.
     *
     * @return una cadena con lo introducido por el usuario hasta que pulse la
     * tecla INTRO.
     * @throws java.lang.Exception En caso de producirse algún error.
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
     * Imprime un mensaje centrado como título entre dos líneas
     *
     * @param msg el mensaje del titulo
     * @param car el caracter que compone cada linea
     * @param longitud la longitud de la linea (debe ser mayor que la longitud
     * del mensaje)
     */
    public static void titulo(String msg, Character car, int longitud) {
        StringBuilder sb = new StringBuilder();
        if (Types.isNullOrEmpty(msg) || Types.isNullOrEmpty(car)) {
        } else {
            int longMsg = msg.length();
            if (longitud > longMsg) {
                IO.prt(true, 1, "");
                linea(car, longitud);
                sb.append(getSL());
                int relleno = (longitud - longMsg) / 2;
                sb.append(repiteCaracter(' ', relleno)).append(msg).append(repiteCaracter(' ', relleno));
                IO.prt(true, 1, sb.toString());
                linea(car, longitud);
            }
        }
    }

    /**
     *
     * @param car
     * @param longitud
     */
    public static void linea(Character car, int longitud) {
        if (car != null && longitud > 0) {
            String linea = repiteCaracter(car, longitud);
            IO.prt(false, 1, linea);
        }
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
    /**
     * Devuelve SL, constante con el caracter de control del salto de línea del
     * sistema operativo actual.
     *
     * @return una cadena constante
     */
    public static String getSL() {
        return SL;
    }

    /**
     * Devuelve SF, constante con el caracter separador de rutas del sistema
     * operativo actual.
     *
     * @return una cadena constante
     */
    public static String getSF() {
        return SF;
    }

    /**
     * Devuelve CAR_INI, cosntante con el caracter que indica el inicio del
     * contenido de un objeto
     *
     * @return una cadena constante
     */
    public static String getCAR_INI() {
        return CAR_INI;
    }

    /**
     * Devuelve CAR_FIN, cosntante con el caracter que indica el final del
     * contenido de un objeto
     *
     * @return una cadena constante
     */
    public static String getCAR_FIN() {
        return CAR_FIN;
    }

    /**
     * Devuelve SEP, el separador de items
     *
     * @return una cadena constante
     */
    public static String getSEP() {
        return SEP;
    }

    /**
     * Devuelve NULL, una constante que representa el valor nulo.
     *
     * @return una cadena constante
     */
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
