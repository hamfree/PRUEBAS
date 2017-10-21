/*
 * Este proyecto guarda todas las pruebas que hago en java y contiene clases
 * útiles que se pueden reutilizar en los distintos proyectos.
 * 
 */
package name.ruiz.juanfco.pruebas;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static name.ruiz.juanfco.pruebas.ConvertidorStrings.printBytes;

/**
 * Clase principal de la aplicacion que ejecuta las distintas clases que se
 * prueban.
 *
 * @author hamfree
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
//        app.testPausa();
//        app.testConvertidorStrings();
//        app.testEjemploLocale();
//        app.testMetodosDeObject();
//        app.testFicheroUtil();
        app.testverConjuntosCaracteresDisponibles();
        app.testImp();
    }

    /**
     * Realiza el test de la clase Pausa
     */
    public void testPausa() {
        System.out.println("Esperando un segundo...");
        Pausa.unSeg();
        System.out.println("Hecho.\nEsperando cinco segundos...");
        Pausa.variosSeg(5);
        System.out.println("Hecho.");
    }

    /**
     * Realiza el test de la clase ConvertidorStrings
     */
    public void testConvertidorStrings() {
        System.out.println("Codificacion de ficheros del sistema: " + System.getProperty("file.encoding"));
        String original = "A" + "\u00ea" + "\u00f1" + "\u00fc" + "C";
        
        System.out.println("Cadena original = " + original);
        System.out.println();
        
        try {
            byte[] utf8Bytes = original.getBytes("UTF-8");
            byte[] defaultBytes = original.getBytes();
            
            String roundTrip = new String(utf8Bytes, "UTF-8");
            System.out.println("Cadena codificada como UTF-8 = " + roundTrip);
            
            System.out.println("Mostrando los bytes en hexadecimal de los arrays 'utf8Bytes' y 'defaultBytes'");
            printBytes(utf8Bytes, "utf8Bytes");
            System.out.println();
            printBytes(defaultBytes, "defaultBytes");
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Realiza el test de EjemploLocale
     */
    public void testEjemploLocale() {
        EjemploLocale el = new EjemploLocale();
        el.muestraLocale();
    }

    /**
     * Realiza el test de MetodosDeObject
     */
    public void testMetodosDeObject() {
        MetodosDeObject mo = new MetodosDeObject(new Object(), new Object());
        
        System.out.println("mo.getUnObjeto().equals(mo.getOtroObjeto()).: " + mo.getUnObjeto().equals(mo.getOtroObjeto()));
        System.out.println("mo.getOtroObjeto().equals(mo.getUnObjeto()).: " + mo.getOtroObjeto().equals(mo.getUnObjeto()));
        System.out.println("mo.unObjeto.hashCode()......................: " + mo.getUnObjeto().hashCode());
        System.out.println("mo.getOtroObjeto().hashCode()...............: " + mo.getOtroObjeto().hashCode());
        System.out.println("mo.getUnObjeto().toString().................: " + mo.getUnObjeto().toString());
        System.out.println("mo.getOtroObjeto().toString()...............: " + mo.getOtroObjeto().toString());
        System.out.println("mo.getUnObjeto().getClass().................: " + mo.getUnObjeto().getClass());
        System.out.println("mo.getOtroObjeto().getClass()...............: " + mo.getOtroObjeto().getClass());
        System.out.println(" \n");
        System.out.println("Objetos A y B, donde B apunta al A....");
        System.out.println("mo.getObjetoA().equals(mo.getObjetoB()).: " + mo.getObjetoA().equals(mo.getObjetoB()));
        System.out.println("mo.getObjetoB().equals(mo.getObjetoA()).: " + mo.getObjetoB().equals(mo.getObjetoA()));
        System.out.println("mo.getObjetoA().hashCode()..............: " + mo.getObjetoA().hashCode());
        System.out.println("mo.getObjetoB().hashCode()..............: " + mo.getObjetoB().hashCode());
        System.out.println("mo.getObjetoA().toString()..............: " + mo.getObjetoA().toString());
        System.out.println("mo.getObjetoB().toString()..............: " + mo.getObjetoB().toString());
    }

    /**
     * Realiza el test de la clase Ficheroutil
     */
    public void testFicheroUtil() {
        FicheroUtil fu = new FicheroUtil();
        String ls = System.getProperty("line.separator");
        
        String contenido = "Esto es una prueba de escritura en un fichero."
                + ls
                + "El texto debe ir en la segunda línea" + ls
                + "Una última línea, la tercera.";
        String path = "/home/hamfree/Temporal/testFicheroUtil.txt";
        try {
            FicheroUtil.writeFile(path, contenido);
            
            if (Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
                System.out.println("Se escribió el fichero '" + path + "' con el contenido:");
                System.out.println(contenido);
            } else {
                System.out.println("No encuentro el fichero '" + path + "'. Se ha producido algún error.");
            }
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Realiza el test del método Imp() de la clase Util
     */
    public void testImp() {
        try {
            Integer entero = 12345;
            Double real = 56.322332232;
            Character car;
            car = 'r';
            BigDecimal bd = BigDecimal.TEN;
            final String SEP = " , ";
            final String SL = System.getProperty("line.separator");
            byte[] arr_utf8 = " á é í ó ú ñ Ñ ç €".getBytes("UTF-8");
            String cadena_utf_8 = new String(arr_utf8, Charset.forName("UTF-8"));
            byte[] arr = cadena_utf_8.getBytes("windows-1252");
            String cadena_windows_1252;
            cadena_windows_1252 = new String(arr, Charset.forName("windows-1252"));
            A objeto = new A("Juan Francisco", "Ruiz Fernández", 47);
            
            System.out.println("Conjunto de caracteres de la Consola: " + System.getProperty("file.encoding"));
            Util u = new Util();
            System.out.println("Usando el objeto Console:");
            
            try {
                
                u.imp(true, "cadena_utf_8=\"", cadena_utf_8, "\"");
                System.out.println(SL);
                u.imp(true, "cadena_windows_1252=\"", cadena_windows_1252, "\"");
                System.out.println(SL);
                u.imp(true, entero, SEP, real, SEP, car, SEP, bd, SEP, objeto);
            } catch (Exception e) {
                Logger.getLogger(App.class.getName()).log(Level.WARNING, e.getLocalizedMessage());
            }
            System.out.println(SL);
            System.out.println("Usando System.out.format():");
            try {
                u.imp(false, "cadena_utf_8=\"", cadena_utf_8, "\"");
                System.out.println(SL);
                u.imp(false, "cadena_windows_1252=\"", cadena_windows_1252, "\"");
                System.out.println(SL);
                u.imp(false, entero, SEP, real, SEP, car, SEP, bd, SEP, objeto);
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("\nProvocamos un error pasando null como arg....");
            try {
                u.imp(true, (Object) null);
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testverConjuntosCaracteresDisponibles() {
        Util u = new Util();
        ArrayList<Charset> al = (ArrayList<Charset>) u.verConjuntosCaracteresDisponibles();
        System.out.println("Juegos de caracteres dispnibles de la JVM:\n" + al.toString());
    }
}
