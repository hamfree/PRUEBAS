package name.ruiz.juanfco.patrones;

import java.lang.reflect.Array;

public class IteradorMatrices implements ObjetostIterador {

    private final String nombre;
    private final Object matriz;
    private final int longitud;
    private int siguienteIndice = -1;
    private Object elementoActual;

    public IteradorMatrices(String nombre, Object matriz) {
        this.nombre = nombre;
        this.matriz = matriz;
        this.longitud = Array.getLength(matriz);
    }

    @Override
    public boolean siguiente() {
        if (siguienteIndice + 1 >= longitud) {
            return false;
        }

        siguienteIndice++;
        elementoActual = Array.get(matriz, siguienteIndice);
        return true;
    }

    @Override
    public String getNombre() {
        return nombre + "[" + siguienteIndice + "]";
    }

    @Override
    public Object getValor() {
        return elementoActual;
    }

}
