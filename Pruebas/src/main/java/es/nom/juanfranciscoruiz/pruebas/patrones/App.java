package es.nom.juanfranciscoruiz.pruebas.patrones;

/**
 *
 * @author hamfree
 */
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import es.nom.juanfranciscoruiz.patrones.Interpretador;

/**
 * Envía texto de ida y vuelta entre la línea de comando y un intérprete. Para
 * versiones de JDK menores que la 6.
 */
public final class App {

    /**
     * Construye y lanza un <code>Interpretador</code> especifico, cuyo nombre
     * cualificado con el paquete es pasado en la linea de comandos.
     *
     * @param argumentos
     */
    public static void main(String... argumentos) {
        try {
            Class<?> laClase = Class.forName(argumentos[0]);
            Interpretador interpretador = (Interpretador) laClase.newInstance();
            App consola = new App(interpretador);
            consola.ejecuta();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex + " La clase Interpretador debe estar en la ruta de clases.");
        } catch (InstantiationException ex) {
            System.err.println(ex + " La clase Interpretador debe ser concreta.");
        } catch (IllegalAccessException ex) {
            System.err.println(ex + " La clase Interpretador debe tener un "
                    + "constructor sin argumentos.");
        }
    }

    public App(Interpretador unInterpretador) {
        if (unInterpretador == null) {
            throw new IllegalArgumentException("No puede ser null.");
        }
        fInterpretador = unInterpretador;
    }

    /**
     * Muestra un mensaje, espera una línea completa de entrada y luego analiza
     * la entrada usando un interprete.
     *
     * Sale cuando <code>Interpretador.analizaEntrada</code> devuelve verdadero.
     */
    public void ejecuta() {
        visualiza(fInterpretador.getCursorInicio());

        // pasa cada linea de entrada a fInterpretador, y visualiza el resultado
        // de fInterpretador
        InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader stdin = new BufferedReader(is);
        boolean haPedidoAbandonar = false;

        List<Object> resultado = new ArrayList<>();
        try {
            while (!haPedidoAbandonar) {
                String linea = stdin.readLine();
                //note que "resultado" es pasado como un parametro de "salida"
                haPedidoAbandonar = fInterpretador.analizaEntrada(linea, resultado);
                visualiza(resultado);
                resultado.clear();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            visualiza(ADIOS);
            apagar(stdin);
        }
    }

    // PRIVADO
    private static final String ADIOS = "Adios.";
    private Interpretador fInterpretador;

    /**
     * Visualiza algo de texto en la salida estandar. Se usa el resultado de
     * toString().
     */
    private void visualiza(Object unTexto) {
        System.out.print(unTexto.toString());
        System.out.flush();
    }

    /**
     * Visualiza una Lista de objetos como texto en la salida estandar, en el
     * orden devuelto por el iterador de aText.
     */
    private void visualiza(List<Object> aText) {
        for (Object item : aText) {
            visualiza(item);
        }
    }

    private void apagar(Reader aStdin) {
        try {
            aStdin.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
