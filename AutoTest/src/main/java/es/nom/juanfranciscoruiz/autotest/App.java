/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nom.juanfranciscoruiz.autotest;

import es.nom.juanfranciscoruiz.autotest.util.Directorio;

/**
 *
 * @author hamfree
 */
public class App {

    public static void main(String[] args) {
        App app = new App();
        app.procesa(args);
        System.exit(0);
    }

    void procesa(String[] args) {
        testMuestraArgumentos(args);
        testDirectorioActual();
    }

    void testMuestraArgumentos(String[] args) {
        StringBuilder sb = new StringBuilder();
        if (args != null && args.length > 0) {
            int i = 0;
            for (String arg : args) {
                sb.append("args[").append(i).append("]=").append(arg).append(",");
                i++;
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }

    void testDirectorioActual() {
        StringBuilder sb = new StringBuilder();
        String ruta = Directorio.get().getAbsolutePath();
        sb.setLength(0);
        sb.append("Directorio trabajo de la clase '")
                .append(Directorio.class.getSimpleName()).append("' :")
                .append(ruta);
        System.out.println(sb.toString());
    }
}
