package es.nom.juanfranciscoruiz.patrones;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class IteradorObjetos implements ObjetostIterador {

    private ObjetostIterador iterador;
    private int profundidad;
    private int hijo = -1;
    private final Stack<IteradorContextoJava> iteradorPila = new Stack<>();
    private String nombre;
    private Object valor;
    private String valorComoCadena;
    private final Set<Object> valores = new HashSet<>();

    public IteradorObjetos(String nombre, Object objeto) {
        this.iterador = new IteradorRaiz(nombre, objeto);
    }

    @Override
    public boolean siguiente() {
        nodoFinal();

        if (valor != null) {
            ObjetostIterador iteradorHijo;
            if ((iteradorHijo = iterarPara(valor)) != null && !valores.contains(valor)) {
                valores.add(valor);
                apilar(iteradorHijo);
                return siguiente();
            }
        }

        if (iterador.siguiente()) {
            nodoInicial();
            nombre = iterador.getNombre();
            valor = iterador.getValor();

            if (valor == null) {
                valorComoCadena = null;
            } else if (esDeUnSoloValor(valor)) {
                valorComoCadena = valor.toString();
            } else {
                valorComoCadena = valor.getClass().getName() + "@" + Integer.toHexString(valor.hashCode());
                if (valores.contains(valor)) {
                    valorComoCadena += "...";
                }
            }

            return true;
        }

        if (iteradorPila.size() > 0) {
            retirar();
            return siguiente();
        }

        return false;
    }

    public void siguientePadre() {
        if (iteradorPila.size() > 0) {
            retirar();
        }
    }

    private void apilar(ObjetostIterador iteradorHijo) {
        iteradorPila.push(new IteradorContextoJava(iterador, profundidad, hijo, nombre, valor));
        iterador = iteradorHijo;
        hijo = -1;
        profundidad++;
        nombre = null;
        valor = null;
    }

    private void retirar() {
        IteradorContextoJava context = iteradorPila.pop();
        iterador = context.getIterador();
        hijo = context.getHijo();
        profundidad--;
        // name = context.getNombre();
        // value = context.getValor();
        nombre = null;
        valor = null;
    }

    private void nodoInicial() {
        hijo++;
    }

    private void nodoFinal() {
    }

    public String getNombre() {
        // return iterator.getNombre();
        return nombre;
    }

    public Object getValor() {
        // if (esDeUnSoloValor(iterator.getValor())) {
        // return iterator.getValor();
        // } else {
        // return iterator.getNombre();
        // }

        // return iterator.getValor();
        return valor;
    }

    public String getValorComoCadena() {
        return valorComoCadena;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public int getHijo() {
        return hijo;
    }

    private ObjetostIterador iterarPara(Object objeto) {
        try {
            if (objeto == null) {
                return null;
            }

            if (objeto.getClass().isArray()) {
                return new IteradorMatrices(nombre, objeto);
            }

            if (objeto instanceof Iterable) {
                return new IteradorColecciones(nombre, (Iterable<?>) objeto);
            }

            if (objeto instanceof Map) {
                return new IteradorMapas(nombre, (Map<?, ?>) objeto);
            }

            if (objeto instanceof Map.Entry) {
                return new IteradorEntradasMapa(nombre, (Map.Entry<?, ?>) objeto);
            }

            if (esDeUnSoloValor(objeto)) {
                return null;
            }

            return new IteradoresPropiedades(objeto);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    public static boolean esDeUnSoloValor(Object objeto) {
        if (objeto == null) {
            return true;
        }

        final Class<? extends Object> tipo = objeto.getClass();

        if (tipo.isPrimitive() || tipo.isEnum()) {
            return true;
        }

        if (objeto instanceof Number) {
            return true;
        }

        if (objeto instanceof CharSequence) {
            return true;
        }

        if (objeto instanceof Date) {
            return true;
        }

        if (objeto instanceof Boolean) {
            return true;
        }

        if (objeto instanceof Character) {
            return true;
        }

        if (objeto instanceof Void) {
            return true;
        }

        // if (object instanceof Map.Entry) {
        // return true;
        // }

        return false;
    }

}
