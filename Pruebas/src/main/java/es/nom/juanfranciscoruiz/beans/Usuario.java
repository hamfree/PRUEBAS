package es.nom.juanfranciscoruiz.beans;

/**
 *
 * @author hamfree
 */
public class Usuario {

    private String alias = null;
    public String nombre;
    public String direccion;

    public Usuario(String name) {
        this.nombre = name;
    }

    protected Usuario(String alias, String nombre, String direccion) {
        this.alias = alias;
        this.nombre = nombre;
        this.direccion = direccion;
    }


    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        if (alias == null) {
            return nombre;
        } else {
            return alias;
        }
    }

}
