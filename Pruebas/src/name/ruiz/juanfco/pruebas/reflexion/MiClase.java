/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas.reflexion;

/**
 *
 * @author hamfree
 */
public class MiClase {

    public String unaVariableString = "Un Texto";
    private final int unaVariableInt = 5;

    public String getUnaVariableString(String concatenar) {
        return unaVariableString + concatenar;
    }

    private int getUnaVariableInt(int suma) {
        return unaVariableInt + suma;
    }

}
