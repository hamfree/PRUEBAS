/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas;

import java.io.Console;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class Util {

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        } else if (obj instanceof String) {
            if (obj.toString().length() == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj.getClass().isArray()) {
            List l = Arrays.asList(obj);
            if (l.size() == 0) {
                return true;
            } else {
                return false;
            }
        } else if (obj.getClass().isAssignableFrom(Collection.class)) {
            Collection col = (Collection) obj;
            if (col.size() == 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    void imp(boolean usaConsola, Object... args) throws Exception {

        StringBuilder sb = new StringBuilder();
        String cadena = null;
        if (args == null) {
            throw new Exception("¡Parámetros nulos!");
        } else {
            for (Object arg : args) {
                sb.append(arg.toString());
            }
            cadena = sb.toString();
        }
        if (usaConsola) {
            Console con = System.console();
            if (con == null) {
                throw new Exception("No se puede acceder a la consola.");
            } else {
                con.printf("%s", cadena);
            }
        } else {
            System.out.format("%s", cadena);
        }
    }

    List<Charset> verConjuntosCaracteresDisponibles() {
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
}
