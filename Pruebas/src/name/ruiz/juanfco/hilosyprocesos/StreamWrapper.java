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
public class StreamWrapper extends Thread {

    private InputStream is;
    private String type;
    private OutputStream os;
    private static final Logger LOG = Logger.getLogger(StreamWrapper.class.getName());

    StreamWrapper(InputStream is, String type) {
        this(is, type, null);
    }

    StreamWrapper(InputStream is, String type, OutputStream redirect) {
        this.is = is;
        this.type = type;
        this.os = redirect;
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
                System.out.println(type + ">" + line);
            }
            if (pw != null) {
                pw.flush();
            }
        } catch (IOException ioe) {
            LOG.severe(ioe.getLocalizedMessage());
        }
    }
}
