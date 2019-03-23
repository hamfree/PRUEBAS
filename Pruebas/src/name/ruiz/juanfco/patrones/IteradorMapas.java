package name.ruiz.juanfco.patrones;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class IteradorMapas implements ObjetostIterador {

    private final String nombre;
    private final Iterator<?> iteradorEntrada;
    private Map.Entry<?, ?> entradaActual;
    private int siguienteIndice = -1;

    public IteradorMapas(String nombre, Map<?, ?> mapa) {
        this.nombre = nombre;
        this.iteradorEntrada = mapa.entrySet().iterator();
    }

    @Override
    public boolean siguiente() {
        if (iteradorEntrada.hasNext()) {
            siguienteIndice++;
            entradaActual = (Entry<?, ?>) iteradorEntrada.next();
            return true;
        }
        return false;
    }

    @Override
    public String getNombre() {
        return nombre + "[" + siguienteIndice + "]";
    }

    @Override
    public Object getValor() {
        return entradaActual;
    }

}
