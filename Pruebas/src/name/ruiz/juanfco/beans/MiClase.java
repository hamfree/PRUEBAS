package name.ruiz.juanfco.beans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author hamfree
 */
public class MiClase implements Serializable {

    private static final long serialVersionUID = -4553014733257969094L;

    public String unaVariableString = "Un Texto";
    private final int unaVariableInt = 5;
    protected int otraVariableInt = 7;
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
        this.unaVariableString = uno;
        this.otraVariableInt = dos;
    }

    public String getUnaVariableString(String concatenar) throws Exception {
        return unaVariableString + concatenar;
    }

    private int getUnaVariableInt(int suma) {
        return unaVariableInt + suma;
    }

    protected boolean otroMetodo(int[][] array, Object[] objetos, String... cadenas) {
        return false;
    }

}
