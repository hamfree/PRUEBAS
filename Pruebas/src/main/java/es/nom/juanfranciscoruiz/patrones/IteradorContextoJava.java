package es.nom.juanfranciscoruiz.patrones;


public class IteradorContextoJava {

    private final ObjetostIterador iterador;
    private final int profundidad;
    private final int hijo;
    private final String nombre;
    private final Object valor;

    public IteradorContextoJava(ObjetostIterador iterador, int profundidad, int hijo, String nombre, Object valor) {
        this.iterador = iterador;
        this.profundidad = profundidad;
        this.hijo = hijo;
        this.nombre = nombre;
        this.valor = valor;
	}
	
    public ObjetostIterador getIterador() {
        return iterador;
	}
	
    public int getProfundidad() {
        return profundidad;
	}
	
    public int getHijo() {
        return hijo;
	}
	
    public String getNombre() {
        return nombre;
    }
	
    public Object getValor() {
        return valor;
    }

}
