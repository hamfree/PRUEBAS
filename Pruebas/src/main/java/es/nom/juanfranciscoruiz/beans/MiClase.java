package es.nom.juanfranciscoruiz.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author hamfree
 */
public class MiClase implements Serializable {

    private static final long serialVersionUID = -4553014733257969094L;

    public String unaCadena = "Un Texto";
    private final int unNumero = 5;
    protected int otroNumero = 7;
    public String[] matrizCadenas = {"uno", "dos", "tres"};
    public final int[][] matrizDoble = {
        {7, 2, 3},
        {4, 5, 6, 9, 12},
        {2}
    };
    private ArrayList<Object> lista;

    public MiClase() {
        super();
    }

    public MiClase(String uno, int dos) {
        this.unaCadena = uno;
        this.otroNumero = dos;
    }

    public String getUnaVariableString(String concatenar) throws Exception {
        return unaCadena + concatenar;
    }

    private int getUnaVariableInt(int suma) {
        return unNumero + suma;
    }

    protected boolean otroMetodo(int[][] array, Object[] objetos, String... cadenas) {
        return false;
    }

}
