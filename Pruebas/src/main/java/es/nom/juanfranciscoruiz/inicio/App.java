package es.nom.juanfranciscoruiz.inicio;

import es.nom.juanfranciscoruiz.consola.IO;
import es.nom.juanfranciscoruiz.reflexion.JarClassLoader;
import es.nom.juanfranciscoruiz.reflexion.ReflexionUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase principal de la aplicacion, desde la que se obtienen dinamicamente el
 * resto de Clases que tienen metodos main (estas clases siguen un patron en el
 * proyecto: Se llaman "App" y se encuentran en cada uno de los paquetes que
 * cuelgan del paquete "es.nom.juanfranciscoruiz.pruebas"). <br>
 * <br>
 * Una vez obtenidas por reflexion las instancias de estas clases se muestran en
 * un menú al usuario para que seleccione la ejecución de una de ellas. Cuando
 * el usuario selecciona la ejecución de una clase "App" esta se ejecutará y
 * después,cuando finalize, se le mostrará al usuario un mensaje en pantalla
 * indicandole que pulse [INTRO] para volver al menú. En el menú habrá una
 * opción para terminar el programa.
 * 
 * TODO: Extraer los metodos comunes a un interfaz y hacer que la clases App
 * implementen esa interfaz mediante una nueva clase AppImpl
 *
 * @author hamfree
 */
public class App {
    //TODO Pasar la ruta de logs a un properties
    // Para FILEMON
    //private final String RUTALOG = "C:\\des\\data\\log\\";
    // Para ELSUPER
    private final String RUTALOG = "D:\\des\\data\\log\\";
    private static final Logger LOG = Logger.getLogger(App.class.getName());
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

    private Map<String, Boolean> opciones = new Hashtable<>();
    private List<String> errores = new ArrayList<>();

    public App() {
        iniciaOpciones();
    }

    public App(String[] args) {
        iniciaOpciones();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int salida = 0;

        App app = new App(args);
        try {
            //app.configuraLog();
            salida = app.ejecutar(args);
        } catch (Exception ex) {
            LOG.severe(ex.getLocalizedMessage());
            throw new RuntimeException(ex.getMessage(), ex);
        }
        System.exit(salida);
    }

    /**
     *
     * @param args
     * @return
     */
    public boolean validar(String[] args) {
        //FIXME: La validacion no funciona. Hay que repensarlo.
        boolean esValido = false;
        if (args != null) {
            if (args.length > 0) {
                for (String arg : args) {
                    boolean existe = false;
                    for (Map.Entry<String, Boolean> entry : opciones.entrySet()) {
                        String key = entry.getKey();
                        if (arg.equalsIgnoreCase(key)) {
                            existe = true;
                            entry.setValue(true);
                        }
                    }
                    if (existe == false) {
                        errores.add("El argumento '" + arg + "' no es valido.");
                        return false;
                    }
                }
                esValido = true;
            } 
        } 
        return esValido;
    }

    /**
     *
     * @return @throws Exception
     */
    public int ejecutar(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int resultado = 0;

        if (validar(args) == true) {
            if (opciones.get("-help") != null && opciones.get("-help") == true) {
                muestraAyuda();
                return 0;
            }
            configuraLog();
            URL url = null;
            List<String> clasesMain = null;

            url = getURLfromApp();
            if (url != null) {
                // Hay que saber si se esta apuntando a un JAR o no...
                String protocol = url.getProtocol();

                sb.append(msgs[0]).append(protocol);
                show(1, sb.toString());

                if ("jar".equalsIgnoreCase(protocol)) {
                    clasesMain = getMainClassesFromJar(url);
                } else if ("file".equalsIgnoreCase(protocol)) {
                    // Se está ejecutando llamando a la clase desde el iniciador java o dentro de un
                    // IDE...
                    clasesMain = getMainClassesFromApp();
                }

                if (clasesMain != null && !clasesMain.isEmpty()) {
                    // Por el momento mostramos las clases que tienen metodo main y no hacemos nada
                    // mas...
                    show(1, "");
                    show(2, msgs[1]);
                    LOG.info(msgs[1]);
                    for (String claseMain : clasesMain) {
                        show(1, claseMain);
                        LOG.info(claseMain);
                    }

                    // Obtenemos las instancias class para las nombres de las clases con metodo
                    // main()
                    List<Class<?>> clases = getClassesFromCanonicalNames(clasesMain);

                    if (clases != null && !clases.isEmpty()) {
                        // Obtenemos las instancias de metodos() main de las instancias de clase recién
                        // obtenidas.
                        List<Method> metodosMain = getMainMethodsFromClasses(clases);

                        if (metodosMain != null && !metodosMain.isEmpty()) {
                            //TODO
                            // Si hay metodos main los mostraremos por pantalla y los asociamos
                            // a un numero para que el usuario seleccione que metodo ejecutar.
                            // Nota: Tener en cuenta la paginacion
                            show(1, "");
                            show(2, msgs[2]);
                            metodosMain.forEach(method -> {
                                show(1, method.toGenericString());
                            });
                            // Aqui va la seleccion del usuario y despues la 
                            // ejecucion del main() elegido mediante reflexion.
                            
                        } else {
                            show(2, msgs[3]);
                        }
                    } else {
                        show(2, msgs[4]);
                    }
                }
            } else {
                show(1, msgs[5]);
                resultado = -1;
            }
        } else {
            muestraErrores(errores);
            resultado = -1;
        }
        return resultado;
    }

    public URL getURLfromApp() {
        URL url = null;
        Thread hiloActual = Thread.currentThread();

        show(1, msgs[6] + hiloActual.toString());
        ClassLoader cl = hiloActual.getContextClassLoader();
        String nombreClase = this.getClass().getCanonicalName();
        show(1, msgs[7] + nombreClase);
        String rutaClase = nombreClase.replaceAll("\\.", "/");
        rutaClase = rutaClase + ".class";
        show(1, msgs[8] + rutaClase);
        url = cl.getResource(rutaClase);

        if (url != null) {
            show(1, msgs[9] + url);
        } else {
            show(1, msgs[10]);
        }
        return url;
    }

    public List<String> getMainClassesFromJar(URL url) throws Exception {
        List<String> clasesMain = null;
        URL jarUrl = null;
        String path = url.getPath();
        String spec = path.substring(0, path.indexOf("!"));

        show(1, msgs[11] + spec);

        try {
            jarUrl = new URL(spec);
        } catch (MalformedURLException ex) {
            LOG.severe(ex.getMessage());
            throw new Exception(ex.getMessage(), ex);
        }
        JarClassLoader jcl = new JarClassLoader(jarUrl);
        List<String> todasLasClases = jcl.getAllClassesInJar();
        clasesMain = jcl.getClassesWithMainMethod(todasLasClases);
        jcl.close();
        return clasesMain;
    }

    public List<String> getMainClassesFromApp() {
        List<String> clasesMain = null;
        Collection<Class<?>> clases = null;

        String nombreClase = this.getClass().getCanonicalName();

        String nombrePaquete = nombreClase.substring(0, nombreClase.indexOf("."));

        show(1, msgs[12] + nombrePaquete);

        try {
            clases = ReflexionUtil.dameClasesEnPaquete(nombrePaquete);
        } catch (FileNotFoundException ex) {
            LOG.severe(ex.getMessage());
        }

        if (clases != null && !clases.isEmpty()) {
            clasesMain = new ArrayList<>();
            for (Class<?> clase : clases) {
                try {
                    if (ReflexionUtil.contieneMetodoMain(clase)) {
                        clasesMain.add(clase.getCanonicalName());
                    }
                } catch (Exception ex) {
                    LOG.severe(ex.getMessage());
                }
            }
        }
        return clasesMain;
    }

    /**
     *
     * @param nombresClases
     * @return
     * @throws Exception
     */
    public List<Class<?>> getClassesFromCanonicalNames(List<String> nombresClases) throws Exception {
        ArrayList<Class<?>> clases = null;

        if (nombresClases != null && !nombresClases.isEmpty()) {
            clases = new ArrayList<>();
            for (String nombreClase : nombresClases) {
                Class<?> clase = Class.forName(nombreClase);
                if (clase != null) {
                    clases.add(clase);
                }
            }
        }

        return clases;
    }

    /**
     *
     * @param clases
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    public List<Method> getMainMethodsFromClasses(List<Class<?>> clases)
            throws NoSuchMethodException, SecurityException {
        List<Method> metodosMain = null;
        if (clases != null && !clases.isEmpty()) {
            metodosMain = new ArrayList<>();
            for (Class<?> clase : clases) {
                Method main = clase.getMethod("main", String[].class);
                metodosMain.add(main);
            }
        }
        return metodosMain;
    }

    public void configuraLog() throws SecurityException, IOException {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %2$s %5$s %n");

        SimpleFormatter sf = new SimpleFormatter();

        // creando manejador de archivo
        FileHandler fh = new FileHandler(RUTALOG + "app%u.%g.log", // patron
                10485760, // limite
                3, // contador
                true); // se agrega
        fh.setLevel(Level.ALL); // nivel
        fh.setFormatter(sf); // formateador

        // creando manejador de consola para el logger actual
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.OFF); // se desactiva para todos los niveles.
        ch.setFormatter(sf); // se asigna el mismo formateador

        LOG.addHandler(fh);
        LOG.addHandler(ch);

        if (opciones.get("-logon") == true) {
            LOG.setLevel(Level.ALL);
        } else {
            LOG.setLevel(Level.OFF);
        }

        show(1, "Manejadores registrados en el Logger Global");
        LOG.info("Manejadores registrados en el Logger Global");
        for (Handler handler : Logger.getGlobal().getHandlers()) {
            LOG.log(Level.INFO, "{0}{1}", new Object[]{IO.repiteCaracter('\t', 1),
                handler.getClass().getCanonicalName()});
            show(1, IO.repiteCaracter('\t', 1) + handler.getClass().getCanonicalName());
        }

        show(1, "Manejadores registrados en el Logger " + LOG.getName());
        LOG.log(Level.INFO, "Manejadores registrados en el Logger {0}", LOG.getName());
        for (Handler handler : LOG.getHandlers()) {
            LOG.log(Level.INFO, "{0}{1}", new Object[]{IO.repiteCaracter('\t', 1),
                handler.getClass().getCanonicalName()});
            show(1, IO.repiteCaracter('\t', 1) + handler.getClass().getCanonicalName());
        }

        LOG.info("El log se guarda en " + RUTALOG);
    }

    private void show(int sl, String msg) {
        if (opciones.get("-msgon") == true) {
            IO.prtln(false, sl, msg);
        }
    }

    private void muestraAyuda() {
        IO.prtln(false, 1, "Programa para pruebas");
        IO.prtln(false, 1, "Sintaxis:");
        IO.prtln(false, 2, "PRG [-msgon] [-help] [-logon]");
        IO.prtln(false, 1, "Parámetros: ");
        IO.prtln(false, 1, "-msgon: Muestra mensajes depuracion por pantalla.");
        IO.prtln(false, 1, "-help: Muestra la ayuda de este programa");
        IO.prtln(false, 1, "-logon: Genera trazas de este programa");
    }

    private void muestraErrores(List<String> errores) {
        if (errores != null && !errores.isEmpty()) {
            for (String error : errores) {
                IO.prtln(false, 1, error);
            }
        }
    }

    private void iniciaOpciones() {
        opciones.put("-msgon", false);
        opciones.put("-help", false);
        opciones.put("-logon", false);
    }
}
