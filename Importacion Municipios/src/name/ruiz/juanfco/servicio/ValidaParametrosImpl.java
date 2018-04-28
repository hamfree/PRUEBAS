/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.servicio;

import java.util.Properties;

/**
 *
 * @author hamfree
 */
public class ValidaParametrosImpl implements ValidaParametros {

    @Override
    public boolean validaJDBC(Properties jdbc) {
        //TODO: Por hacer, por el momento devolvemos true para poder continuar
        return true;
    }

    @Override
    public boolean validaJNDI(String jndi) {
        //TODO: Por hacer, por el momento devolvemos true para poder continuar
        return true;
    }

}
