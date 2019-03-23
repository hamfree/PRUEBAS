package name.ruiz.juanfco.inicio;

/**
 * Clase principal de la aplicacion, desde la que se obtienen dinamicamente el
 * resto de Clases que tienen metodos main (estas clases siguen un patron en el
 * proyecto: Se llaman "App" y se encuentran en cada uno de los paquetes que
 * cuelgan del paquete "name.ruiz.juanfco.pruebas").
 * <br><br>
 * Una vez obtenidas por reflexion las instancias de estas clases se muestran en
 * un menú al usuario para que seleccione la ejecución de una de ellas. Cuando
 * el usuario seleciona la ejecución de una clase "App" esta se ejecutará y
 * después,cuando finalize, se le mostrará al usuario un mensaje en pantalla
 * indicandole que pulse <INTRO> para volver al menú. En el menú habrá una
 * opción para terminar el programa.
 *
 * @author hamfree
 */
public class App {


    public App() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app = new App();
        System.exit(0);
    }

}
