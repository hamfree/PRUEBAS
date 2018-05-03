package name.ruiz.juanfco.inicio;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import name.ruiz.juanfco.beans.Persona;
import name.ruiz.juanfco.entradasalida.FicheroUtil;
import name.ruiz.juanfco.numeros.TiposNumericos;
import name.ruiz.juanfco.pruebas.CuatroObjetos;
import static name.ruiz.juanfco.texto.ConvertidorStrings.printBytes;
import name.ruiz.juanfco.texto.EjemploLocale;
import name.ruiz.juanfco.utiles.Util;

/**
 * Clase principal de la aplicacion que ejecuta las distintas clases que se prueban.
 *
 * @author hamfree
 */
public class App {

    final String SL;
    final String SF;

    public App() {
        Util u = new Util();
        this.SL = u.SL;
        this.SF = u.SF;
    }

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
//        app.testGetAllCharsets();
//        app.testImp();
//        app.testRead();
        TiposNumericos.muestraLimites();
        System.exit(0);
    }

    /**
     * Realiza el test de la clase Pausa
     */
    public void testPausa() {
        System.out.println("Esperando un segundo...");
        //Pausa.unSeg();
        System.out.println("Hecho.\nEsperando cinco segundos...");
        //Pausa.variosSeg(5);
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
     * Realiza el test de CuatroObjetos
     */
    public void testMetodosDeObject() {
        CuatroObjetos mo = new CuatroObjetos(new Object(), new Object());

        System.out.println("Objetos C y D, donde C y D son instancias independientes....");
        System.out.println("mo.getC().equals(mo.getD()))...: " + mo.getC().equals(mo.getD()));
        System.out.println("mo.getD().equals(mo.getC()))...: " + mo.getD().equals(mo.getC()));
        System.out.println("mo.getC().hashCode()...........: " + mo.getC().hashCode());
        System.out.println("mo.getD().hashCode()...........: " + mo.getD().hashCode());
        System.out.println("mo.getC().toString()...........: " + mo.getC().toString());
        System.out.println("mo.getD().toString()...........: " + mo.getD().toString());
        System.out.println("mo.getC().getClass()...........: " + mo.getC().getClass());
        System.out.println("mo.getD().getClass()...........: " + mo.getD().getClass());
        System.out.println(SL);
        System.out.println("Objetos A y B, donde B apunta al A....");
        System.out.println("mo.getA().equals(mo.getB())....: " + mo.getA().equals(mo.getB()));
        System.out.println("mo.getB().equals(mo.getA())....: " + mo.getB().equals(mo.getA()));
        System.out.println("mo.getA().hashCode()...........: " + mo.getA().hashCode());
        System.out.println("mo.getB().hashCode()...........: " + mo.getB().hashCode());
        System.out.println("mo.getA().toString()...........: " + mo.getA().toString());
        System.out.println("mo.getB().toString()...........: " + mo.getB().toString());
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
     * Realiza el test del método imp() de la clase Util
     */
    public void testImp() {
        try {
            // Declaramos una variedad de tipos primitivos y objetos
            Integer entero;
            Double real;
            Character car;
            BigDecimal bd;
            byte[] arr_utf8;
            byte[] arr;
            final String SP;
            String s_w1252;
            String s_utf8;
            StringBuilder sb;
            Persona persona;

            // Les damos valores
            SP = " , ";
            entero = 12345;
            real = 56.322332232;
            car = 'r';
            bd = BigDecimal.TEN;
            arr_utf8 = " á é í ó ú ñ Ñ ç €".getBytes("UTF-8");
            s_utf8 = new String(arr_utf8, Charset.forName("UTF-8"));
            arr = s_utf8.getBytes("windows-1252");
            s_w1252 = new String(arr, Charset.forName("windows-1252"));
            persona = new Persona("Juan Francisco", "Ruiz Fernández", 47);
            sb = new StringBuilder();

            sb.append("Conjunto de caracteres de la Consola: ").
                    append(System.getProperty("file.encoding"));

            System.out.println(sb.toString());
            try {
                System.out.println(SL);
                System.out.println("Usando imp() con el objeto Console:");
                Util.imp(true, "\tcadena en utf_8 ..........: '", s_utf8, "'");
                System.out.println(SL);
                Util.imp(true, "\tcadena en windows_1252 ...: '", s_w1252, "'");
                System.out.println(SL);
                System.out.println("Mostrando distintos tipos de datos con imp():");
                Util.imp(true, entero, SP, real, SP, car, SP, bd, SP, persona);
            } catch (Exception e) {
                Logger.getLogger(App.class.getName()).log(Level.WARNING, e.getLocalizedMessage());
            }

            try {
                System.out.println(SL);
                System.out.println("Usando imp() con System.out.format():");
                Util.imp(false, "\tcadena en utf_8 ..........: '", s_utf8, "'");
                System.out.println(SL);
                Util.imp(false, "\tcadena en windows_1252 ...: '", s_w1252, "'");
                System.out.println(SL);
                System.out.println("Mostrando distintos tipos de datos con imp():");
                Util.imp(false, entero, SP, real, SP, car, SP, bd, SP, persona);
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("\nProvocamos un error pasando null como arg....");
            try {
                Util.imp(true, (Object) null);
            } catch (Exception ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Comprueba el metodo read() de la clase Util
     */
    public void testRead() {
        String nombre;
        String primerApellido;
        String edad;
        StringBuilder sb = new StringBuilder();

        try {
            Util.imp(true, "Dime tu nombre: ");
            nombre = Util.read(true);
            Util.imp(true, "Dime tu primer apellido: ");
            primerApellido = Util.read(true);
            Util.imp(true, "Teclea tu edad: ");
            edad = Util.read(true);
            Util.imp(true, SL);
            if (nombre.length() > 0) {
                sb.append("Tu nombre es '").append(nombre).append("'.");
            } else {
                sb.append("No has dicho tu nombre.");
            }
            sb.append(SL);
            if (primerApellido.length() > 0) {
                sb.append("Tu apellido es '").append(primerApellido).append("'");
            } else {
                sb.append("No has dicho tu apellido.");
            }
            sb.append(SL);
            if (edad.length() > 0) {
                sb.append("Tu edad es de ").append(edad).append(" años.");
            } else {
                sb.append("No has introducido tu edad.");
            }
            Util.imp(true, sb.toString());
            Util.imp(true, SL);
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Realiza el test de getAllCharsets()
     */
    public void testGetAllCharsets() {
        try {
            StringBuilder sb = new StringBuilder();
            Util u = new Util();
            ArrayList<Charset> al;

            al = (ArrayList<Charset>) u.getAllCharsets();
            Util.imp(false, "Juegos de caracteres disponibles de la JVM:");
            Iterator iter = al.iterator();
            int i = 1;
            while (iter.hasNext()) {
                Charset cs;
                cs = (Charset) iter.next();
                sb.append(i++).append(" ").append(cs.displayName()).append(SL);
            }
            Util.imp(false, sb.toString());
        } catch (Exception e) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
