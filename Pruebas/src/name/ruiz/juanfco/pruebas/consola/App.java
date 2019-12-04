package name.ruiz.juanfco.pruebas.consola;

import java.io.Console;
import java.util.Arrays;

/**
 * Aplicación simple de consola interactiva. Usa la clase java.io.Console de
 * * Java 6.
 */
public final class App {

    public static final void main(String... aArgs) {
        Console consola = System.console();
        //lee el nombre de usuario, usando la sintaxis de java.util.Formatter:
        String nombreusuario = consola.readLine("¿Nombre de Usuario? ");

        //lee la contraseña, sin mostrar la salida
        char[] claveacceso = consola.readPassword("¿Contraseña? ");

        //verifica el nombre del usuario y la contrasena usando algún mecanismo
        //(suprimido). La documentación java para la clase Console recomienda
        //"poner a ceros" la contraseña cuando se termine la verificación.
        Arrays.fill(claveacceso, ' ');

        consola.printf("Bienvenido, %1$s.", nombreusuario);
        consola.printf(NUEVA_LINEA);

        String className = consola.readLine("Por favor, introduzca un nombre de "
                + "clase completamente cualificado:");
        Class laClase = null;
        try {
            laClase = Class.forName(className);
            consola.printf("El árbol de herencia es: %1$s", getInheritanceTree(laClase));
        } catch (ClassNotFoundException ex) {
            consola.printf("No puedo encontrar esta clase.");
        }

        //esta versión simplemente sale, sin preguntar al usuario por más entradas
        consola.printf("Adios.");
    }

    // PRIVADO
    private static final String NUEVA_LINEA = System.getProperty("line.separator");

    private static String getInheritanceTree(Class aClass) {
        StringBuilder superclasses = new StringBuilder();
        superclasses.append(NUEVA_LINEA);
        Class theClass = aClass;
        while (theClass != null) {
            superclasses.append(theClass);
            superclasses.append(NUEVA_LINEA);
            theClass = theClass.getSuperclass();
        }
        superclasses.append(NUEVA_LINEA);
        return superclasses.toString();
    }
}
