package es.nom.juanfranciscoruiz.patrones;



public class IteradorRaiz implements ObjetostIterador {
	
    public enum Estado {
        NUEVO, ABIERTO, CERRADO
    };

    private final Object objeto;
    private Estado estado = Estado.NUEVO;
    private final String nombre;

    public IteradorRaiz(String nombre, Object objeto) {
        this.nombre = nombre;
        this.objeto = objeto;
	}
	
	@Override
    public boolean siguiente() {
        if (estado == Estado.NUEVO) {
            estado = Estado.ABIERTO;
			return true;
        } else if (estado == Estado.ABIERTO) {
            estado = Estado.CERRADO;
			return false;
		}
		return false;
	}
	
	@Override
    public String getNombre() {
        return nombre;
	}
	
	@Override
    public Object getValor() {
        if (estado == Estado.ABIERTO) {
            return objeto;
		}
		return null;
	}
	
}
