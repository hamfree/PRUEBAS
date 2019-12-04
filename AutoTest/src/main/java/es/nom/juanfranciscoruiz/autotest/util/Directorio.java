/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nom.juanfranciscoruiz.autotest.util;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author hamfree
 */
public class Directorio {

    private static File DIRECTORIO_TRABAJO;
    private static final String FILE = "file";
    private static final String JAR = "jar";
    private static final String EXT = ".class";

    public static File get() {
        String recurso = Directorio.class.getSimpleName() + EXT;
        if (DIRECTORIO_TRABAJO == null) {
            try {
                URL url = Directorio.class.getResource(recurso);
                if (url.getProtocol().equals(FILE)) {
                    File f = new File(url.toURI());
                    do {
                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    DIRECTORIO_TRABAJO = f;
                } else if (url.getProtocol().equals(JAR)) {
                    String esperado = "!/" + recurso;
                    String s = url.toString();
                    s = s.substring(4);
                    s = s.substring(0, s.length() - esperado.length());
                    File f = new File(new URL(s).toURI());
                    do {
                        f = f.getParentFile();
                    } while (!f.isDirectory());
                    DIRECTORIO_TRABAJO = f;
                }
            } catch (MalformedURLException | URISyntaxException e) {
                DIRECTORIO_TRABAJO = new File(".");
            }
        }
        return DIRECTORIO_TRABAJO;
    }
}
