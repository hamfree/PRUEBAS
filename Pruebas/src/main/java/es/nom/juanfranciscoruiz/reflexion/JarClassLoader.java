package es.nom.juanfranciscoruiz.reflexion;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

/**
 * Un cargador de clases para cargar ficheros jar, tanto locales como remotos.
 */
public class JarClassLoader extends URLClassLoader {

    private URL url;
    private ArrayList<JarEntry> alje = null;
    private static final Logger LOG = Logger.getLogger(JarClassLoader.class.getName());

    /**
     * Crea un JarClassLoader nuevo para la url especificada.
     *
     * @param url la url del fichero jar
     */
    public JarClassLoader(URL url) {
        super(new URL[]{url});
        this.url = url;
    }

    /**
     * Devuelve el nombre del fichero de la clase main del jar, o null si no se
     * definió un atributo "Main-Class" en el manifiesto.
     *
     * @return
     * @throws java.io.IOException
     */
    public String getMainClassName() throws IOException {
        URL u = new URL("jar", "", url + "!/");
        JarURLConnection uc = (JarURLConnection) u.openConnection();
        Attributes attr = uc.getMainAttributes();
        return attr != null ? attr.getValue(Attributes.Name.MAIN_CLASS) : null;
    }

    /**
     * Invokes the application in this jar file given the name of the main class
     * and an array of arguments. The class must define a static method "main"
     * which takes an array of String arguemtns and is of return type "void".
     *
     * @param name the name of the main class
     * @param args the arguments for the application
     * @exception ClassNotFoundException if the specified class could not be
     * found
     * @exception NoSuchMethodException if the specified class does not contain
     * a "main" method
     * @exception InvocationTargetException if the application raised an
     * exception
     */
    public void invokeClass(String name, String[] args)
            throws ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException {
        Class<?> c = loadClass(name);
        Method m = c.getMethod("main", new Class[]{args.getClass()});
        m.setAccessible(true);
        int mods = m.getModifiers();
        if (m.getReturnType() != void.class || !Modifier.isStatic(mods)
                || !Modifier.isPublic(mods)) {
            throw new NoSuchMethodException("main");
        }
        try {
            m.invoke(null, new Object[]{args});
        } catch (IllegalAccessException e) {
            // This should not happen, as we have disabled access checks
        }
    }

    /**
     *
     * @return
     */
    public List<String> getAllClassesInJar() {
        ArrayList<String> listaClases = null;
        String nombreCanonicoClase;

        try {
            URL u = new URL("jar", "", url + "!/");
            JarURLConnection uc = (JarURLConnection) u.openConnection();
            if (uc != null) {
                JarFile jf = uc.getJarFile();
                if (jf != null) {
                    Enumeration<JarEntry> entradas = jf.entries();
                    alje = new ArrayList<>();
                    listaClases = new ArrayList<>();
                    while (entradas.hasMoreElements()) {
                        JarEntry je = entradas.nextElement();
                        ZipEntry ze = jf.getEntry(je.getName());
                        if (!ze.isDirectory()) {
                            String camino = ze.getName();
                            if (!esMETA_INF(camino)) {
                                nombreCanonicoClase = ze.getName().replace("/", ".");
                                nombreCanonicoClase = nombreCanonicoClase.replaceAll(".class", "");
                                listaClases.add(nombreCanonicoClase);
                            }
                        }
                        String ruta = ze.getName();

                        // El directorio META-INF es especial y no contiene paquetes
                        if (!esMETA_INF(ruta)) {
                            alje.add(je);
                        }
                    }
                }
            }
        } catch (MalformedURLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return listaClases;

    }

    /**
     *
     * @param clases
     * @return
     */
    public List<String> getClassesWithMainMethod(List<String> clases) {
        ArrayList<String> clasesConMain = null;
        //Hay que usar reflexion
		if (clases != null && !clases.isEmpty()) {
		    clasesConMain = new ArrayList<>();
		    for (String clase : clases) {
		    	Class<?> clazz = null;
		    	try {
		    		clazz = Class.forName(clase);
				} catch (ClassNotFoundException e) {
					// No se encuentra la clase, ignoramos y NO guardamos 
				} catch (NoClassDefFoundError e) {
					// No se encuentra la definición de la clase, ignoramos y NO guardamos 
				}
		        if (clazz != null) {
		        	Method[] metodosDeclarados = clazz.getDeclaredMethods();
			        for (Method m : metodosDeclarados) {
			            if (m.getName().equalsIgnoreCase("main")) {
			                clasesConMain.add(clase);
			                break;
			            }
			        }
		        }
		    }
		}
        return clasesConMain;
    }

    /**
     *
     * @param ruta
     * @return
     */
    private boolean esMETA_INF(String ruta) {
        boolean resultado = false;
        if ("META-INF/MANIFEST.MF".equalsIgnoreCase(ruta) || "META-INF/".equalsIgnoreCase(ruta)) {
            resultado = true;
        }
        return resultado;
    }

    //GETTERS Y SETTERS
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public ArrayList<JarEntry> getAlje() {
        return alje;
    }

    public void setAlje(ArrayList<JarEntry> alje) {
        this.alje = alje;
    }

}
