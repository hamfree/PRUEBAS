package es.nom.juanfranciscoruiz.pruebas.reflexion;

import java.util.logging.Logger;
import java.util.logging.Level;
import es.nom.juanfranciscoruiz.consola.IO;
import es.nom.juanfranciscoruiz.reflexion.ReflexionUtil;

/**
 *
 * @author hamfree
 */
public class App {

    private final static Logger LOG = Logger.getLogger(App.class.getName());

    static public void main(String args[]) {
        App app = new App();
        app.ejecuta(args[0]);
        System.exit(0);
    }

    public void ejecuta(String arg) {
        try {
            StringBuilder sb = new StringBuilder();
            ReflexionUtil.dameDatosClase(arg.getClass(), true, true, true, false, false, sb);

            IO.prtln(false, 1, sb.toString());

        } catch (Exception e) {
            IO.prtln(false, 1, e.getMessage());
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
