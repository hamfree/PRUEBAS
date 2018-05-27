/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.inicio;

import java.net.URL;
import java.util.ArrayList;
import name.ruiz.juanfco.pruebas.reflexion.JarClassLoader;

/**
 *
 * @author hamfree
 */
public class Menu {

    public Menu() {
    }

    public static void main(String[] args) {

        try {
            Menu m = new Menu();
            m.procesa();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void procesa() throws Exception {
        URL url = getClass().getProtectionDomain().getCodeSource().getLocation();
        ArrayList<String> listaClases = null;
        ArrayList<String> listaClasesConMain = null;
        String linea;

        if (url != null) {
            System.out.println("Ruta al fichero JAR : " + url.getFile());
            JarClassLoader jcl = new JarClassLoader(url);
            listaClases = (ArrayList<String>) jcl.getAllClassesInJar();
            System.out.println("Lista de clases en el JAR:");
            muestraLista(listaClases);

            listaClasesConMain = (ArrayList<String>) jcl.getClassesWithMainMethod(listaClases);
            System.out.println("Lista de clases con metodo main()");
            muestraLista(listaClasesConMain);
        }
    }

    public void muestraLista(ArrayList<?> lista) {
        if (lista != null && !lista.isEmpty()) {
            for (Object o : lista) {
                Class clazz = o.getClass();
                if (clazz.isAssignableFrom(String.class)) {
                    String s = (String) o;
                    System.out.println(s);
                }
            }
        }
    }
}
