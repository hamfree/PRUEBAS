package es.nom.juanfranciscoruiz.beans;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author hamfree
 */
public class ObjetoComplejo {
    private Map<String, Object> elMapa = null;
    private TreeMap<Object, Object> elMapaArbol = null;
    private List<Object> laLista = null;

    public ObjetoComplejo() {
    }

    public Map<String, Object> getElMapa() {
        return elMapa;
    }

    public void setElMapa(Map<String, Object> elMapa) {
        this.elMapa = elMapa;
    }

    public TreeMap<Object, Object> getElMapaArbol() {
        return elMapaArbol;
    }

    public void setElMapaArbol(TreeMap<Object, Object> elMapaArbol) {
        this.elMapaArbol = elMapaArbol;
    }

    public List<Object> getLaLista() {
        return laLista;
    }

    public void setLaLista(List<Object> laLista) {
        this.laLista = laLista;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.elMapa);
        hash = 13 * hash + Objects.hashCode(this.elMapaArbol);
        hash = 13 * hash + Objects.hashCode(this.laLista);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjetoComplejo other = (ObjetoComplejo) obj;
        if (!Objects.equals(this.elMapa, other.elMapa)) {
            return false;
        }
        if (!Objects.equals(this.elMapaArbol, other.elMapaArbol)) {
            return false;
        }
        if (!Objects.equals(this.laLista, other.laLista)) {
            return false;
        }
        return true;
    }
}
