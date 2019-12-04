/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.hilosyprocesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class EncapsuladorFlujo extends Thread {

    private InputStream is;
    private String tipo;
    private OutputStream os;
    private static final Logger LOG = Logger.getLogger(EncapsuladorFlujo.class.getName());

    EncapsuladorFlujo(InputStream is, String tipo) {
        this(is, tipo, null);
    }

    EncapsuladorFlujo(InputStream is, String tipo, OutputStream redirigido) {
        this.is = is;
        this.tipo = tipo;
        this.os = redirigido;
    }

    @Override
    public void run() {
        try {
            PrintWriter pw = null;
            if (os != null) {
                pw = new PrintWriter(os);
            }

            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (pw != null) {
                    pw.println(line);
                }
                System.out.println(tipo + ">" + line);
            }
            if (pw != null) {
                pw.flush();
            }
        } catch (IOException ioe) {
            LOG.severe(ioe.getLocalizedMessage());
        }
    }
}
