package es.juanfranciscoruiz.prueba;

import java.io.IOException;
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
            ProbarTerminal pt = new ProbarTerminal();
            resultado = pt.tutorial1();
            System.out.println("Resultado: " + resultado);
            resultado = pt.tutorial2();
            System.out.println("Resultado: " + resultado);

        }
 catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
