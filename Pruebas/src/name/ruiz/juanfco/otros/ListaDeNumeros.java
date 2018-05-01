package name.ruiz.juanfco.otros;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListaDeNumeros {

    private final List<Integer> lista;
    private static final int CANTIDAD = 10;

    public ListaDeNumeros() {
        lista = new ArrayList<>(CANTIDAD);
        for (int i = 0; i < CANTIDAD; i++) {
            lista.add(i);
        }
    }

    public void escribeLista() {
        // El constructor FileWriter lanza IOException, que debe ser capturada.
        try (PrintWriter salida = new PrintWriter(new FileWriter("ficheroSalida.txt"))) {
            for (int i = 0; i < CANTIDAD; i++) {
                // El método get(int) lanza IndexOutOfBoundsException, que debe ser capturada.
                salida.println("Valor en: " + i + " = " + lista.get(i));
            }
            salida.close();
        } catch (IOException ex) {
            Logger.getLogger(ListaDeNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
