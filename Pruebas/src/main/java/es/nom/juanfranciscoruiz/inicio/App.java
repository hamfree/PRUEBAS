package es.nom.juanfranciscoruiz.inicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import es.nom.juanfranciscoruiz.consola.IO;
import es.nom.juanfranciscoruiz.reflexion.JarClassLoader;
import es.nom.juanfranciscoruiz.reflexion.ReflexionUtil;

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
 * @author hamfree
 */
public class App {
	private final String RUTALOG = "C:\\des\\data\\log\\";

	public App() {

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		int salida = 0;
		App app = new App();
		try {
			app.configuraLog();
			salida = app.ejecutar();
		} catch (Exception ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
			throw new RuntimeException(ex.getMessage(), ex);
		}
		System.exit(salida);
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public int ejecutar() throws Exception {
		int resultado = 0;

		URL url = null;
		List<String> clasesMain = null;

		url = getURLfromApp();
		if (url != null) {
			// Hay que saber si se esta apuntando a un JAR o no...
			String protocol = url.getProtocol();

			IO.prtln(false, 1, "protocolo: ", protocol);

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
				IO.prtln(true, 1, "");
				IO.prtln(false, 2, "Listado de clases con metodo main()");
				for (String claseMain : clasesMain) {
					IO.prtln(false, 1, claseMain);
				}

				// Obtenemos las instancias class para las nombres de las clases con metodo
				// main()
				List<Class<?>> clases = getClassesFromCanonicalNames(clasesMain);

				if (clases != null && !clases.isEmpty()) {
					// Obtenemos las instancias de metodos() main de las instancias de clase recién
					// obtenidas.
					List<Method> metodosMain = getMainMethodsFromClasses(clases);

					if (metodosMain != null && !metodosMain.isEmpty()) {
						// Temporalmente vamos a mostrar los contenidos de las instancias Method
						IO.prtln(true, 1, "");
						IO.prtln(false, 2, "Listado de instancias de metodos main()");
						for (Method method : metodosMain) {
							IO.prtln(false, 1, method.toGenericString());
						}
					} else {
						IO.prtln(false, 2, "Error: no se han encontrado métodos main() en las instancias de clase");
					}
				} else {
					IO.prtln(false, 2, "Error: no se han instancias de clase a partir de sus nombres canonicos");
				}
			}
		} else {
			IO.prtln(false, 1, "No he encontrado clases con metodo main()");
			resultado = 1;
		}
		return resultado;
	}

	public URL getURLfromApp() {
		URL url = null;
		Thread hiloActual = Thread.currentThread();

		IO.prtln(false, 1, "Hilo actual -> ", hiloActual.toString());
		ClassLoader cl = hiloActual.getContextClassLoader();
		String nombreClase = this.getClass().getCanonicalName();
		IO.prtln(false, 1, "Nombre de la clase actual -> ", nombreClase);
		String rutaClase = nombreClase.replaceAll("\\.", "/");
		rutaClase = rutaClase + ".class";
		IO.prtln(false, 1, "Ruta relativa de la clase -> ", rutaClase);
		url = cl.getResource(rutaClase);

		if (url != null) {
			IO.prtln(false, 1, "Objeto URL obtenido con el cargador de clases de contexto -> ", url);
		} else {
			IO.prtln(false, 1, "No he podido obtener la URL.");
		}
		return url;
	}

	public List<String> getMainClassesFromJar(URL url) throws Exception {
		List<String> clasesMain = null;
		URL jarUrl = null;
		String path = url.getPath();
		String spec = path.substring(0, path.indexOf("!"));

		IO.prtln(false, 1, "spec: ", spec);

		try {
			jarUrl = new URL(spec);
		} catch (MalformedURLException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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

		IO.prtln(false, 1, "Nombre del paquete : ", nombrePaquete);

		try {
			clases = ReflexionUtil.dameClasesEnPaquete(nombrePaquete);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (clases != null && !clases.isEmpty()) {
			clasesMain = new ArrayList<>();
			for (Class<?> clase : clases) {
				try {
					if (ReflexionUtil.contieneMetodoMain(clase)) {
						clasesMain.add(clase.getCanonicalName());
					}
				} catch (Exception ex) {
					Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
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
			clases = new ArrayList<Class<?>>();
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
			metodosMain = new ArrayList<Method>();
			for (Class<?> clase : clases) {
				Method main = clase.getMethod("main", String[].class);
				metodosMain.add(main);
			}
		}
		return metodosMain;
	}

	public void configuraLog() throws SecurityException, IOException {
		// creando manejador de archivo
		FileHandler fh = new FileHandler(RUTALOG + "app%g.log", // patron
				10485760, // limite
				3, // contador
				true); // se agrega
		fh.setLevel(Level.ALL); // nivel
		fh.setFormatter(new SimpleFormatter()); // formateador

		// agregar el manejador de archivo al log
		Logger.getGlobal().addHandler(fh);

		// el manejador de consola se agrega automaticamente, solo
		// cambiamos el nivel de detalle a desplegar
		Logger.getGlobal().getHandlers()[0].setLevel(Level.OFF);

		// se establece el nivel predeterminado global
		Logger.getGlobal().setLevel(Level.ALL);
	}
}
