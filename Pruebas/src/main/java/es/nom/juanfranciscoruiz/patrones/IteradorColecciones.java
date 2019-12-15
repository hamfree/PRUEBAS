package es.nom.juanfranciscoruiz.patrones;

import java.util.Iterator;


public class IteradorColecciones implements ObjetostIterador {

    private final String nombre;
    private final Iterator<?> iterador;
    private Object elementoActual;
    private int siguienteIndice = -1;

    public IteradorColecciones(String nombre, Iterable<?> coleccion) {
        this.nombre = nombre;
        this.iterador = coleccion.iterator();
	}

	@Override
    public boolean siguiente() {
        if (iterador.hasNext()) {
            siguienteIndice++;
            elementoActual = iterador.next();
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
        return elementoActual;
	}
	
}
