package name.ruiz.juanfco.patrones;

import java.util.Map;


public class IteradorEntradasMapa implements ObjetostIterador {
	
    public enum Estado {

        NUEVO, CLAVE, VALOR, CERRADO;
		
        public String getNombre() {
			return name().toLowerCase();
		}

        public Estado siguiente() {
            if (this.ordinal() + 1 < Estado.values().length) {
                return Estado.values()[this.ordinal() + 1];
			} else {
                return CERRADO;
			}
		}
	}

    private final String nombre;
    private Map.Entry<?, ?> entrada;
    private Estado estado = Estado.NUEVO;

    public IteradorEntradasMapa(String nombre, Map.Entry<?, ?> entrada) {
        this.nombre = nombre;
        this.entrada = entrada;
	}
	
	@Override
    public boolean siguiente() {
        if (estado.ordinal() < Estado.VALOR.ordinal()) {
            estado = estado.siguiente();
			return true;
		}

		return false;
	}

	@Override
    public String getNombre() {
        return estado.getNombre();
	}
	
	@Override
    public Object getValor() {
        if (estado == Estado.CLAVE) {
            return entrada.getKey();
        } else if (estado == Estado.VALOR) {
            return entrada.getValue();
		}
		return null;
	}
	
}
