package name.ruiz.juanfco.beans;

import java.util.Objects;

/**
 * Clase tonta para hacer pruebas de paso por referencia y valor desde métodos
 * de otra clase.
 *
 * @author hamfree
 */
public class A {

    private String cadena;
    private Integer entero;

    public A() {
        this.cadena = null;
        this.entero = null;
    }

    public A(String cadena, Integer entero) {
        this.cadena = cadena;
        this.entero = entero;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public Integer getEntero() {
        return entero;
    }

    public void setEntero(Integer entero) {
        this.entero = entero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.cadena);
        hash = 11 * hash + Objects.hashCode(this.entero);
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
        final A other = (A) obj;
        if (!Objects.equals(this.cadena, other.cadena)) {
            return false;
        }
        if (!Objects.equals(this.entero, other.entero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "A{" + "cadena='" + cadena + "', entero=" + entero + '}';
    }

}
