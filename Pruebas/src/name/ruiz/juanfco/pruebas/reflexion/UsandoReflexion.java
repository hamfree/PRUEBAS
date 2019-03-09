/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas.reflexion;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hamfree
 */
public class UsandoReflexion {

    static final Logger LOG = Logger.getLogger(UsandoReflexion.class.getName());
    Class<? extends MiClase> objetoDeClassConInfoDeMiClase;
    Class classDelTexto;
    Class classDelNumero;
    Class classDeBooleanA;
    Class c;
    String nombreDeLaClase;
    Field variableString;
    Field variableInt;
    Field[] todasLasVariables;
    Field[] todasLasVariablesDeclaradas;
    Method metodoGetString;
    Method metodoGetInt;
    Method[] todosLosMetodos;
    Method[] todosLosMetodosDeclarados;
    Constructor[] todosLosConstructores;
    Constructor[] todosLosConstructoresDeclarados;

    public UsandoReflexion() {
    }

    public static void main(String[] args) {
        MiClase objetoDeMiClase = new MiClase();

        UsandoReflexion ur = new UsandoReflexion();

        ur.obtenerReferenciasClass(objetoDeMiClase);
        ur.obtenerCamposDeMiClase();
        ur.obtenerMetodosDeMiClase();
        ur.obtenerLosConstructoresDeMiClase();
        ur.mostrarResultados(objetoDeMiClase);
    }

    public void obtenerReferenciasClass(MiClase objetoDeMiClase) {
        objetoDeClassConInfoDeMiClase = objetoDeMiClase.getClass();

        /* Obtener el Class directamente de un objeto (Recordatorio: un
            objeto de tipo String válido es un texto entre comillas) */
        classDelTexto = "Un texto".getClass();

        Integer numero = 5;
        classDelNumero = numero.getClass();

        /* Para tipos primitivos tenemos que usar ".class" o con ".TYPE"
            (preferiblemente utilizar ".class") (Recordatorio: algunos tipos
            primitivos son int, boolean, float, etc.) */
        classDeBooleanA = boolean.class;

        try {
            /* También puede obtener una clase desde un nombre completamente
            cualificado (fully-qualified name); esto es, el nombre del paquete
            donde está nuestra clase, seguido del nombre de la clase */
            c = Class.forName("name.ruiz.juanfco.pruebas.reflexion.MiClase");
        } catch (ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public void obtenerCamposDeMiClase() {
        /* Obtener el nombre de la clase */
        nombreDeLaClase = objetoDeClassConInfoDeMiClase.getSimpleName();

        try {
            /* Si queremos obtener un atributo público */
            variableString = objetoDeClassConInfoDeMiClase.getField("unaVariableString");
        } catch (NoSuchFieldException | SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        try {
            /* Si queremos obtener un atributo, sea privado o no */
            variableInt = objetoDeClassConInfoDeMiClase.getDeclaredField("unaVariableInt");
        } catch (NoSuchFieldException | SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        /* Si queremos obtener un array con todas los atributos públicos de
           nuestra clase. */
        todasLasVariables = objetoDeClassConInfoDeMiClase.getFields();

        /* Si queremos obtener todas los atributos, independientemente de
           si es privada o no */
        todasLasVariablesDeclaradas = objetoDeClassConInfoDeMiClase.getDeclaredFields();
    }

    public void obtenerMetodosDeMiClase() {
        try {
            /* Si queremos obtener un método público. Si tiene parámetros podremos
            pasar cada uno de sus tipos .class en orden después del nombre */
            metodoGetString = objetoDeClassConInfoDeMiClase.getMethod("getUnaVariableString", String.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        try {
            /* Si queremos obtener un método, sea privadao o no. Si tiene parámetros
            podremos pasar cada uno de sus tipos .class en orden después del nombre */
            metodoGetInt = objetoDeClassConInfoDeMiClase.getDeclaredMethod("getUnaVariableInt", int.class);
        } catch (NoSuchMethodException | SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        /* Si queremos obtener un array con todos las metodos públicos de
           nuestra clase */
        todosLosMetodos = objetoDeClassConInfoDeMiClase.getMethods();

        /* Si queremos obtener todos los métodos, independientemente de si son
           privados o no */
        todosLosMetodosDeclarados = objetoDeClassConInfoDeMiClase.getDeclaredMethods();
    }

    public void obtenerLosConstructoresDeMiClase() {
        /* Se puede utilizar getConstructor() pasándole un array con los
        parámetros de entrada de tipo Class que tiene el constructor, para
        que devuelva el objeto de tipo Constructor, normalmente no se utiliza y
        por reducir código no ponemos ejemplos (si quieres saber más tienes más
        información en la bibliografía) */

 /* Si queremos obtener un array con todos los constructores publicos de
        nuestra clase */
        todosLosConstructores = objetoDeClassConInfoDeMiClase.getConstructors();

        /* Si queremos obtener todos los constructores, independientemente de
         si son privados o no */
        todosLosConstructoresDeclarados = objetoDeClassConInfoDeMiClase.getDeclaredConstructors();
    }

    public void mostrarResultados(MiClase objetoDeMiClase) {
        System.out.println("Muestra todos los metodos, publicos y privados");
        System.out.println("----------------------------------------------");
        for (final Method metodo : todosLosMetodosDeclarados) {
            System.out.println("\nNombre del MÉTODO: " + metodo.getName());
            System.out.println("  Cantidad de parámetros: " + metodo.getParameterCount());
        }

        System.out.println("Muestra todos los campos de la clase, publicos y privados");
        System.out.println("---------------------------------------------------------");
        for (Field variable : todasLasVariablesDeclaradas) {
            String nombreVariable = variable.getName();
            int modificador = variable.getModifiers();
            System.out.println("\nNombre del ATRIBUTO: " + nombreVariable);

            Boolean esPublic = Modifier.isPublic(modificador);
            System.out.println("   Es public: " + esPublic);

            Boolean esPrivate = Modifier.isPrivate(modificador);
            System.out.println("   Es private: " + esPrivate);
        }

        System.out.println("Muestra todos los métodos de la clase, publicos y privados");
        System.out.println("----------------------------------------------------------");
        for (final Method metodo : todosLosMetodosDeclarados) {
            System.out.println("Nombre del MÉTODO: " + metodo.getName());

            System.out.println("   Es public: " + Modifier.isPublic(metodo.getModifiers()));
            System.out.println("   Es private: " + Modifier.isPrivate(metodo.getModifiers()));

            System.out.println("  Tipo del return: " + metodo.getGenericReturnType().getTypeName());
            Type[] tipos = metodo.getGenericParameterTypes();

            System.out.println("  Tipos de los parámetros:");
            for (Type tipo : tipos) {
                System.out.println("    " + tipo.getTypeName());
            }
        }

        System.out.println("Obtener el valor de un campo de la clase que es publica");
        System.out.println("-------------------------------------------------------");
        variableString = null;
        String textoObtenido = null;
        try {
            variableString = objetoDeClassConInfoDeMiClase.getField("unaVariableString");
            textoObtenido = (String) variableString.get(objetoDeMiClase);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            LOG.log(Level.SEVERE, null, e);
        }
        System.out.println("Valor de la variable de tipo String: " + textoObtenido);

        System.out.println("Obtener el valor de un campo de la clase que es privado");
        System.out.println("-------------------------------------------------------");
        variableInt = null;
        int intObtenido = -1;
        try {
            variableInt = objetoDeClassConInfoDeMiClase.getDeclaredField("unaVariableInt");
            variableInt.setAccessible(true);
            /* La convertimos en accesible */
            intObtenido = variableInt.getInt(objetoDeMiClase);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            LOG.log(Level.SEVERE, null, e);
        }
        System.out.println("Valor de la variable de tipo int: " + intObtenido);

        /* Obtener todos los valores de todas los atributos declarados en la clase */
        System.out.println("Valores de todas los atributos del objeto de la clase 'objetoDeMiClase'");
        System.out.println("-----------------------------------------------------------------------");
        for (Field variable : todasLasVariablesDeclaradas) {
            String nombreVariable = variable.getName();

            Object valorVariable = null;
            try {
                variable.setAccessible(true);
                valorVariable = variable.get(objetoDeMiClase);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOG.log(Level.SEVERE, null, e);
            }
            System.out.println("\nValor de la variable " + nombreVariable + " es: " + valorVariable);
        }
    }
}
