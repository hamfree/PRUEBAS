package es.juanfranciscoruiz.prueba;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entrada principal de la aplicacion. Si las pruebas salen bien, volverá al
 * sistema con código 0. En caso contrario devolverá 1.
 *
 */
public class App {

    public static void main(String[] args) {
        boolean resultado;
        try {
            ProbarTerminal pt = new ProbarTerminal();
            resultado = pt.test();
            resultado = pt.test2();
            if (resultado == true) {
                args[0] = "Ok";
                System.exit(0);
            }
            args[0] = "Failed";
            System.exit(1);
        }
        catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
}
