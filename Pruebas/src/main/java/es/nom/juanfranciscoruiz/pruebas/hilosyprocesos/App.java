package es.nom.juanfranciscoruiz.pruebas.hilosyprocesos;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import es.nom.juanfranciscoruiz.consola.IO;
import es.nom.juanfranciscoruiz.hilosyprocesos.Comando;
import es.nom.juanfranciscoruiz.reflexion.JarClassLoader;

/**
 * Esta clase con main debe ejecutarse desde la consola de comandos y no desde
 * el IDE debido a la forma en que funciona la "autolocalización" de la clase.
 *
 * @author hamfree
 */
public class App {

    ArrayList<String> listaClases;
    ArrayList<String> listaClasesConMain;

    public App() {
    }

    public static void main(String[] args) {
        try {
            App m = new App();
            m.procesa();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void procesa() throws Exception {
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        Comando cmd = new Comando();
        String[] args = {"*.*", "/W"};
        int resultado;

        resultado = cmd.ejecuta("DIR", args);
        System.out.println("Resultado de ejecutar el comando :" + resultado);
        System.out.println("url=" + url.toString());
    }

    public void muestraLista(ArrayList<?> lista) {
        if (lista != null && !lista.isEmpty()) {
            for (Object o : lista) {
                Class<?> clazz = o.getClass();
                String s = new String();
                if (clazz.isInstance(s)) {
                    s = (String) o;
                    System.out.println(s);
                } else {
                }
            }
            System.out.println(System.getProperty("line.separator"));
        }
    }

    private void muestraTitulo(String msg) {
        StringBuilder sb = new StringBuilder();
        int letras;
        sb.setLength(0);
        sb.append(msg);
        IO.prtln(true, 1, sb.toString());
        letras = sb.length();
        sb.setLength(0);
        sb.append(IO.repiteCaracter('=', letras));
        IO.prtln(true, 2, sb.toString());
    }

    public void obtenerDatos(URL url) {
        StringBuilder sb = new StringBuilder();
        if (url != null) {
            JarClassLoader jcl = new JarClassLoader(url);

            sb.append("Ruta al fichero JAR : ").append(url.getPath());
            muestraTitulo(sb.toString());

            sb.setLength(0);
            sb.append("Lista de clases en el JAR:");
            muestraTitulo(sb.toString());

            listaClases = (ArrayList<String>) jcl.getAllClassesInJar();
            muestraLista(listaClases);

            listaClasesConMain = (ArrayList<String>) jcl.getClassesWithMainMethod(listaClases);
            try {
				jcl.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
            sb.setLength(0);
            sb.append("Lista de clases con metodo main()");
            muestraTitulo(sb.toString());
            muestraLista(listaClasesConMain);
        }
    }
}
