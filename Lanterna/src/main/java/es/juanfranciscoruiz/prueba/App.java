package es.juanfranciscoruiz.prueba;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Entrada principal de la aplicación. * *
 */
public class App {

    public static void main(String[] args) {
        boolean resultado;
        try {
            Tutorial pt = new Tutorial();
            resultado = pt.tutorial1();
            System.out.println("Resultado: " + resultado);
            resultado = pt.tutorial2();
            System.out.println("Resultado: " + resultado);
            resultado = pt.tutorial3();
            System.out.println("Resultado: " + resultado);
            resultado = pt.tutorial4();
            System.out.println("Resultado: " + resultado);

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
