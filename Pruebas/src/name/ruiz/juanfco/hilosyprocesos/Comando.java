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

    public Comando() {
    }

    /**
     *
     * @param comando
     * @param args
     * @return
     */
    public static int ejecuta(String comando, String... args) {
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
                InputStream is = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String aux = br.readLine();
                while (aux != null) {
                    System.out.println(aux);
                    aux = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            resultado = -1;
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
