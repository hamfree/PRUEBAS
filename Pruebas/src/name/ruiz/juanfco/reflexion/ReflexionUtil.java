/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.reflexion;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class ReflexionUtil {

    private static final String SL = System.getProperty("line.separator");
    private static final Logger LOGGER = Logger.getLogger(ReflexionUtil.class.getName());
    private static final String EXTENSION_FICHERO_CLASE = ".class";

    public ReflexionUtil() {
    }

    public static Class dameDatosClase(Class laClase,
            boolean conAtributos,
            boolean conConstructores,
            boolean conMetodos,
            boolean conAnotaciones,
            boolean conClasesAnidadas,
            StringBuilder sb) throws Exception {
        try {
            String tipo;
            //TODO: Posibles anotaciones de clase

            sb.setLength(0);

            int m = laClase.getModifiers();
            boolean isPublic = Modifier.isPublic(m);
            boolean isPrivate = Modifier.isPrivate(m);
            boolean isProtected = Modifier.isProtected(m);
            boolean isFinal = Modifier.isFinal(m);
            boolean isStatic = Modifier.isStatic(m);
            if (isPublic) {
                sb.append("public ");
            } else if (isPrivate) {
                sb.append("private ");
            } else if (isProtected) {
                sb.append("protected ");
            } else if (isFinal) {
                sb.append("final ");
            } else if (isStatic) {
                sb.append("static ");
            }

            // Tipo de objeto...
            if (laClase.isPrimitive()) {
                tipo = laClase.getName();
            } else if (laClase.isAnonymousClass()) {
                tipo = " (clase anonima)";
            } else if (laClase.isArray()) {
                // ¿Dimensiones?
                tipo = " [] " + laClase.getComponentType().getName();
            } else if (laClase.isEnum()) {
                tipo = " enum";
            } else if (laClase.isInterface()) {
                tipo = " interface";
            } else {
                tipo = " class";
            }

            String nombreCanonico = laClase.getCanonicalName();
            sb.append(tipo).append(" ").append(nombreCanonico);

            // Posible Herencia
            Class<?> superclase = laClase.getSuperclass();
            if (superclase != null) {
                sb.append(" extends ").append(superclase.getName());
            }

            // Posibles interfaces que implementa
            Class[] interfaces = laClase.getInterfaces();
            if (interfaces != null) {
                if (interfaces.length > 0) {
                    sb.append(" implements ");
                    for (Class i : interfaces) {
                        sb.append(i.getName()).append(",");
                    }
                    if (sb.charAt(sb.length() - 1) == ',') {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }
            sb.append(" {").append(SL);

            if (conAtributos) {
                dameCampos(laClase, sb, conAnotaciones);
            }

            if (conConstructores) {
                dameConstructores(laClase, sb, conAnotaciones);
            }

            if (conMetodos) {
                dameMetodos(laClase, sb, conAnotaciones);
            }

            sb.append("}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return laClase;
    }

    public static Field[] dameCampos(Class laClase,
            StringBuilder sb,
            boolean conAnotaciones) throws Exception {
        Field[] usuarioFields = null;
        try {
            usuarioFields = laClase.getDeclaredFields();
            if (sb != null) {
                if (usuarioFields.length > 0) {
                    sb.append(SL);
                    for (Field f : usuarioFields) {

                        sb.append("\t");
                        int modificadores = f.getModifiers();
                        boolean isPublic = Modifier.isPublic(modificadores);
                        boolean isPrivate = Modifier.isPrivate(modificadores);
                        boolean isProtected = Modifier.isProtected(modificadores);
                        boolean isFinal = Modifier.isFinal(modificadores);
                        boolean isStatic = Modifier.isStatic(modificadores);

                        if (isPublic) {
                            sb.append("public ");
                        } else if (isPrivate) {
                            sb.append("private ");
                        } else if (isProtected) {
                            sb.append("protected ");
                        } else if (isFinal) {
                            sb.append("final ");
                        } else if (isStatic) {
                            sb.append("static ");
                        }
                        sb.append(f.getType().getSimpleName())
                                .append(" ").append(f.getName()).append(";").append(SL);
                    }
                }
            }
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new Exception(e);
        }
        return usuarioFields;
    }

    public static Constructor[] dameConstructores(Class laClase,
            StringBuilder sb,
            boolean conAnotaciones) throws Exception {
        Constructor[] constructores = null;
        try {
            constructores = laClase.getDeclaredConstructors();
            if (sb != null) {
                if (constructores != null) {
                    if (constructores.length > 0) {
                        sb.append(SL);
                        for (Constructor c : constructores) {
                            sb.append("\t");
                            int mc = c.getModifiers();
                            boolean isPublic = Modifier.isPublic(mc);
                            boolean isPrivate = Modifier.isPrivate(mc);
                            boolean isProtected = Modifier.isProtected(mc);
                            boolean isFinal = Modifier.isFinal(mc);
                            boolean isStatic = Modifier.isStatic(mc);

                            if (isPublic) {
                                sb.append("public ");
                            } else if (isPrivate) {
                                sb.append("private ");
                            } else if (isProtected) {
                                sb.append("protected ");
                            } else if (isFinal) {
                                sb.append("final ");
                            } else if (isStatic) {
                                sb.append("static ");
                            }
                            String nombreConstructor = c.getName();
                            sb.append(nombreConstructor);

                            Parameter[] parameters = c.getParameters();
                            if (parameters != null) {
                                if (parameters.length > 0) {
                                    sb.append("(");
                                    for (Parameter p : parameters) {
                                        sb.append(p.getParameterizedType().getTypeName())
                                                .append(" ")
                                                .append(p.getName());
                                        if (p.isVarArgs()) {
                                            sb.append("...,");
                                        } else {
                                            sb.append(",");
                                        }
                                    }
                                    if (sb.charAt(sb.length() - 1) == ',') {
                                        sb.deleteCharAt(sb.length() - 1);
                                    }
                                    sb.append(")");
                                } else {
                                    sb.append("()");
                                }
                            } else {
                                sb.append("()");
                            }
                            sb.append(" {(codigo)};").append(SL);
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new Exception(e);
        }
        return constructores;
    }

    public static Method[] dameMetodos(Class laClase,
            StringBuilder sb,
            boolean conAnotaciones) throws Exception {
        Method[] metodos = null;
        try {
            metodos = laClase.getDeclaredMethods();
            if (sb != null) {
                if (metodos != null) {
                    if (metodos.length > 0) {
                        sb.append(SL);
                        for (Method m : metodos) {
                            sb.append("\t");

                            //TODO: Anotaciones posibles del método
                            int mc = m.getModifiers();
                            boolean isPublic = Modifier.isPublic(mc);
                            boolean isPrivate = Modifier.isPrivate(mc);
                            boolean isProtected = Modifier.isProtected(mc);
                            boolean isFinal = Modifier.isFinal(mc);
                            boolean isStatic = Modifier.isStatic(mc);

                            if (isPublic) {
                                sb.append("public ");
                            } else if (isPrivate) {
                                sb.append("private ");
                            } else if (isProtected) {
                                sb.append("protected ");
                            } else if (isFinal) {
                                sb.append("final ");
                            } else if (isStatic) {
                                sb.append("static ");
                            }

                            Class<?> tipoRetornado = m.getReturnType();
                            sb.append(tipoRetornado.getName()).append(" ");
                            String nombreMetodo = m.getName();
                            sb.append(nombreMetodo);
                            Parameter[] parameters = m.getParameters();
                            if (parameters != null) {
                                if (parameters.length > 0) {
                                    sb.append("(");
                                    for (Parameter p : parameters) {
                                        //TODO: anotaciones posibles en cada parametro
                                        sb.append(p.getParameterizedType().getTypeName())
                                                .append(" ")
                                                .append(p.getName());
                                        if (p.isVarArgs()) {
                                            sb.append("...,");
                                        } else {
                                            sb.append(",");
                                        }
                                    }
                                    if (sb.charAt(sb.length() - 1) == ',') {
                                        sb.deleteCharAt(sb.length() - 1);
                                    }
                                    sb.append(")");
                                } else {
                                    sb.append("()");
                                }
                            } else {
                                sb.append("()");
                            }

                            // Posible lanzamiento de excepciones...
                            Class<?>[] excepciones = m.getExceptionTypes();
                            if (excepciones != null && excepciones.length > 0) {
                                sb.append(" throws ");
                                for (Class e : excepciones) {
                                    sb.append(e.getName()).append(",");
                                }
                                if (sb.charAt(sb.length() - 1) == ',') {
                                    sb.deleteCharAt(sb.length() - 1);
                                }
                            }
                            sb.append(" {(codigo)};").append(SL);
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new Exception(e);
        }
        return metodos;
    }

    public static boolean contieneMetodoMain(Class clase) throws Exception {
        try {
            if (clase != null) {
                Method[] metodos = dameMetodos(clase, null, false);
                for (Method metodo : metodos) {
                    if (metodo.getName().equalsIgnoreCase("main")) {
                        return true;
                    }
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(ReflexionUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Devuelve una colección de todas las Clases dentro de un paquete dado y
     * los subpaquetes. Tenga en cuenta que este método se basa en que las
     * clases esten en archivos de clase (no en ficheros JAR) en un solo
     * directorio.
     *
     * @param nombrePaquete Nombre completamente cualificado del paquete. Ej:
     * "com.example.foo"
     * @return una colección de todas los objetos Class dentro del paquete dado
     * y de sus subpaquetes.
     * @throws FileNotFoundException si el nombre del paquete no es válido.
     */
    public static List<Class> dameClasesEnPaquete(String nombrePaquete) throws
            FileNotFoundException {
        // Esperamos encontrar las clases en un directorio del sistema de
        // ficheros con un nombre que se corresponde al nombre del paquete.
        String nombreDirectorio = nombrePaquete.replace('.', '/');

        // Intentamos encontrar este dirctorio en el camino de clases.
        // Tenga en cuenta que sólo esperamos uno de esos directorios.
        URL urlPackageDirectory = ClassLoader.getSystemClassLoader().getResource(nombreDirectorio);
        if (urlPackageDirectory == null) {
            throw new FileNotFoundException("No puede encontrar el directorio '"
                    + nombreDirectorio + "' en los caminos de clase.");
        }
        File directorio = new File(urlPackageDirectory.getFile());
        List<Class> clases = new ArrayList<>();
        // Encuentra todos los ficheros de clases en este directorio y sus subdirectorios.
        dameClasesRecursivamente(nombrePaquete, directorio, clases);
        return clases;
    }

    /**
     * Actualiza la lista de clases dada con los ficheros de clases en el
     * directorio y subdirectorios dado del sistema de archivos.
     *
     * @param nombrePaquete El nombre del paquete correspondiente al directorio
     * dado.
     * @param directorio El directorio de donde extraer los ficheros de clases.
     * @param listaClases La lista de clases encontradas. Se le agregan
     * recursivamente.
     */
    private static void dameClasesRecursivamente(String nombrePaquete, File directorio,
            List<Class> listaClases) {
        // Bucle sobre los contenidos de este directorio:
        File[] ficherosHijo = directorio.listFiles();
        for (File ficheroHijo : ficherosHijo) {
            if (ficheroHijo.isFile()) {
                String nombreFichero = ficheroHijo.getName();
                // Solo queremos los ficheros .class
                if (nombreFichero.endsWith(EXTENSION_FICHERO_CLASE)) {
                    // Eliminamos la extensión .class para obtener el nombre de la clase.
                    String className = nombreFichero.substring(0, nombreFichero.length()
                            - EXTENSION_FICHERO_CLASE.length());
                    Class c = null;
                    try {
                        c = Class.forName(nombrePaquete + '.' + className);
                        listaClases.add(c);
                    } catch (ClassNotFoundException | NoClassDefFoundError ex) {
                        // Esto no debería ocurrir ya que estamos encontrando las clases en un
                        // directorio de nuestro camino de clases, aunque si estamos en
                        // una ejecución "mixta" con algunas clases cargadas de ficheros
                        // class en el sistema de ficheros y otras de un fichero JAR como
                        // las de las librerías que necesita la aplicación podría darse.
                        // Vamos a ignorarlo...
                        Logger.getLogger(ReflexionUtil.class.getName()).log(Level.SEVERE, null, ex);
                        c = null;
                        //throw new RuntimeException(ex);
                    }
                }
            } else if (ficheroHijo.isDirectory()) {
                // Ingresamos recursivamente dentro de este subdirectorio
                dameClasesRecursivamente(nombrePaquete + '.' + ficheroHijo.getName(),
                        ficheroHijo, listaClases);
            }
        }
    }

}
