/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.reflexion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class ReflexionUtil {

    private final String SL = System.getProperty("line.separator");
    private Class laClase;
    private static final Logger LOGGER = Logger.getLogger(ReflexionUtil.class.getName());

    public ReflexionUtil(String theClass) {
        try {
            this.laClase = Class.forName(theClass);
        } catch (ClassNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public Class extraerDatosClase(boolean conAtributos,
            boolean conConstructores,
            boolean conMetodos,
            boolean conAnotaciones,
            boolean conClasesAnidadas,
            StringBuilder sb) throws Exception {
        try {
            String tipo;
            //TODO: Pôsibles anotaciones de clase

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
                extraerAtributos(sb, conAnotaciones);
            }

            if (conConstructores) {
                extraerConstructores(sb, conAnotaciones);
            }

            if (conMetodos) {
                extraerMetodos(sb, conAnotaciones);
            }

            sb.append("}");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, null, e);
        }
        return laClase;
    }

    public Field[] extraerAtributos(StringBuilder sb, boolean conAnotaciones) throws Exception {
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

    public Constructor[] extraerConstructores(StringBuilder sb, boolean conAnotaciones) throws Exception {
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

    public Method[] extraerMetodos(StringBuilder sb, boolean conAnotaciones) throws Exception {
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
}
