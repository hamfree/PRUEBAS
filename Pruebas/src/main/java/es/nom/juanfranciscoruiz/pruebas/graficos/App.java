package es.nom.juanfranciscoruiz.pruebas.graficos;

import es.nom.juanfranciscoruiz.consola.IO;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class App {
    private final String LOGPATH = "C:\\des\\data\\log\\";
    private static final Logger LOG = Logger.getLogger(es.nom.juanfranciscoruiz.pruebas.graficos.App.class.getName());
    private static final int MAXOPTIONS = 10;
    private static int exitCode = 0;
    private String[] msgs = {
        "Protocolo -> ",
        "Listado de clases con metodo main()",
        "Listado de instancias de metodos main()",
        "Error: no se han encontrado métodos main() en las instancias de clase",
        "Error: no se han instancias de clase a partir de sus nombres canonicos",
        "No he encontrado clases con metodo main()",
        "Hilo actual -> ",
        "Nombre de la clase actual -> ",
        "Ruta relativa de la clase -> ",
        "Objeto URL obtenido con el cargador de clases de contexto -> ",
        "No he podido obtener la URL.",
        "Spec -> ",
        "Nombre del paquete -> "
    };

    private String[] options = new String[MAXOPTIONS];
    private List<String> errors = new ArrayList<>();

    private boolean isMessagesActivated;
    private boolean isHelpActivated;
    private boolean isLoggerActivated;

    public App() {
        options[0] = "-msgon";
        options[1] = "-help";
        options[2] = "-logon";

        isMessagesActivated = false;
        isHelpActivated = false;
        isLoggerActivated = false;
    }

    public App(String[] args) {

    }
    
    public static void main(String[] args) {
        App theApp = new App();
        exitCode = theApp.execute(args);
        
    }
    
    public int execute(String[] args){
        int exitCode = 0;
        if (args != null || args.length > 0){
            for (String arg : args) {
                if (arg.equalsIgnoreCase(options[0])){
                    isMessagesActivated = true;
                }
                if (arg.equalsIgnoreCase(options[1])){
                    isHelpActivated = true;
                    
                }
            }
        } else {
            
        }
        return exitCode;
    }
    
    
    private void showHelp() {
        IO.prtln(false, 1, "Programa de pruebas de gráficos");
        IO.prtln(false, 1, "Sintaxis:");
        IO.prtln(false, 2, "PRG [-msgon] [-help] [-logon]");
        IO.prtln(false, 1, "Parámetros: ");
        IO.prtln(false, 1, "-msgon: Muestra mensajes depuracion por pantalla.");
        IO.prtln(false, 1, "-help: Muestra la ayuda de este programa");
        IO.prtln(false, 1, "-logon: Genera trazas de este programa");
    }

    private void showErrors(List<String> errores) {
        if (errores != null && !errores.isEmpty()){
            for (String error: errores){
                IO.prtln(false, 1, error);
            }
        }
    }
}
