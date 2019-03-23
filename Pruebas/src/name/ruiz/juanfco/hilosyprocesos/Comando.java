/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.hilosyprocesos;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class Comando {

    private final static String[] COMANDOSINTERNOS = {"ASSOC", "CALL", "CD", "CLS", "COLOR",
        "COPY", "DATE", "DEL", "DIR", "ECHO", "ENDLOCAL", "ERASE", "EXIT", "FOR",
        "FTYPE", "GOTO", "IF", "MD", "MKDIR", "MKLINK", "MOVE", "PATH", "PAUSE",
        "POPD", "PROMPT", "PUSHD", "RD", "RMDIR", "REM", "REN", "SET",
        "SETLOCAL", "SHIFT", "START", "TIME", "TITLE", "TYPE", "VER", "VERIFY",
        "VOL", "::"};

    private static final Logger LOG = Logger.getLogger(Comando.class.getName());

    public static String[] getCOMANDOSINTERNOS() {
        return COMANDOSINTERNOS;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public Comando() {
    }

    /**
     *
     * @param comando
     * @param args
     * @return
     */
    public int ejecuta(String comando, String... args) {
        int resultado = 0;
        StringBuilder orden = new StringBuilder();
        try {
            if (comando != null && !comando.isEmpty()) {
                if (esUnComandoInterno(comando)) {
                    orden.append("cmd /c ").append(comando).append(" ");
                }

                if (args != null && args.length > 0) {
                    for (String arg : args) {
                        orden.append(arg).append(" ");
                    }
                }

                Process p = Runtime.getRuntime().exec(orden.toString());
                EncapsuladorFlujo stError = new EncapsuladorFlujo(p.getErrorStream(), "ERROR");
                EncapsuladorFlujo stOutput = new EncapsuladorFlujo(p.getInputStream(), "SALIDA");

                stError.start();
                stOutput.start();

                resultado = p.waitFor();
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            resultado = -1;
        } catch (InterruptedException ex) {
            Logger.getLogger(Comando.class.getName()).log(Level.SEVERE, null, ex);
            resultado = -2;
        }
        return resultado;
    }

    /**
     *
     * @param comando
     * @return
     */
    private static boolean esUnComandoInterno(String comando) {
        boolean resultado = false;
        comando = comando.trim();
        for (String cmd : COMANDOSINTERNOS) {
            if (comando.equalsIgnoreCase(cmd)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }

}
