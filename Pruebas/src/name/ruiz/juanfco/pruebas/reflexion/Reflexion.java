/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas.reflexion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class Reflexion {

    private final String SL = System.getProperty("line.separator");
    private final String NOMBRE_CANONICO = "name.ruiz.juanfco.beans.Usuario";
    private StringBuilder sb = new StringBuilder();
    private Class usuarioClass;
    private static final Logger logger = Logger.getLogger(Reflexion.class.getName());

    public Reflexion() {
        try {
            usuarioClass = Class.forName(NOMBRE_CANONICO);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void haciendoOperaciones() {

        // Metodos
    }

    public void extraerDatosClase() {
        // Datos sobre la clase
        sb.setLength(0);
        String nombreCanonico = usuarioClass.getCanonicalName();
        sb.append("CLASE: ").append(nombreCanonico);
        System.out.println(sb.toString());
    }

    public void extraerAtributos() {
        // Acceso a los atributos de la clase
        Field[] usuarioFields = usuarioClass.getDeclaredFields();
        if (usuarioFields.length > 0) {
            sb.setLength(0);
            sb.append("Atributos de la clase").append(SL);
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
                        .append(" ").append(f.getName()).append(SL);
            }
            System.out.print(sb.toString());

        }
    }

    public void extraerConstructores() {
        // Constructores
        Constructor[] constructores = usuarioClass.getDeclaredConstructors();
        if (constructores != null) {
            if (constructores.length > 0) {
                sb.setLength(0);
                sb.append("Constructores de la clase").append(SL);
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
                    Class[] params = c.getParameterTypes();
                    if (params != null) {
                        if (params.length > 0) {
                            sb.append("(");
                            for (Class cparam : params) {
                                sb.append(cparam.getSimpleName())
                                        .append(" param, ");
                            }
                            if (sb.charAt(sb.length() - 1) == ',') {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                            sb.append(")").append(SL);
                        }
                    }
                }
            }
        }
        System.out.print(sb.toString());
    }

    public void extraerMetodos() {

    }
}
