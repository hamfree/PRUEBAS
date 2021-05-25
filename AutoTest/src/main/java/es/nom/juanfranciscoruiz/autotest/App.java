package es.nom.juanfranciscoruiz.autotest;

import es.nom.juanfranciscoruiz.autotest.util.Directorio;

/**
 * Punto de entrada de la aplicacion 'AutoTest' que muestra por pantalla
 * los argumentos que se le pasan y también en que directorio se encuentra 
 * la clase 'Directorio'.
 * 
 * @author hamfree
 */
public class App {

    /**
     * Entrada de la aplicación
     * @param args matriz de argumentos que se le pueden pasar al programa (opcional)
     */
    public static void main(String[] args) {
        App app = new App();
        app.procesa(args);
        System.exit(0);
    }

    /**
     * Realiza la función de la aplicación.
     * @param args Los argumentos de la matriz de cadenas que se pasan al programa
     */
    void procesa(String[] args) {
        testMuestraArgumentos(args);
        testDirectorioActual();
        testDirectorioApp();
    }

    /**
     * Muestra por la salida estandar los argumentos que se le pasan al programa, 
     * si así se ha hecho.
     * @param args Los argumentos de la matriz de cadenas que se pasan al programa
     */
    void testMuestraArgumentos(String[] args) {
        StringBuilder sb = new StringBuilder();
        if (args != null && args.length > 0) {
            int i = 0;
            for (String arg : args) {
                sb.append("args[").append(i).append("]=").append(arg).append(",");
                i++;
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }

    /**
     * Muestra el directorio donde se encuentra ubicada la clase Directorio
     */
    void testDirectorioActual() {
        StringBuilder sb = new StringBuilder();
        Directorio dir = new Directorio();
        String rutaDirectorio = dir.get().getAbsolutePath();
        sb.setLength(0);
        sb.append("Directorio trabajo de la clase '")
                .append(Directorio.class.getSimpleName()).append("' :")
                .append(rutaDirectorio);
        System.out.println(sb.toString());
    }
    
    void testDirectorioApp() {
        StringBuilder sb = new StringBuilder();
        Directorio dir = new Directorio();
        String rutaDirectorio = dir.get(this.getClass()).getAbsolutePath();
        sb.setLength(0);
        sb.append("Directorio trabajo de la clase '")
                .append(App.class.getSimpleName()).append("' :")
                .append(rutaDirectorio);
        System.out.println(sb.toString());
    }
    
}
