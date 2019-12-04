/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas.reflexion;

import java.util.logging.Level;
import java.util.logging.Logger;
import name.ruiz.juanfco.consola.IO;
import name.ruiz.juanfco.reflexion.ReflexionUtil;

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
            ReflexionUtil rflx = new ReflexionUtil(arg);

            rflx.extraerDatosClase(true, true, true, false, false, sb);

            IO.prtln(false, 1, sb.toString());

        } catch (Exception e) {
            IO.prtln(false, 1, e.getMessage());
            LOG.log(Level.SEVERE, null, e);
        }
    }
}
