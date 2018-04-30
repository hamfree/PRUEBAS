/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.vista;

import java.util.List;
import java.util.Map;

/**
 *
 * @author hamfree
 */
public class VistaImpl implements Vista {

    @Override
    public void muestraMensaje(Object msj) {
        if (msj != null) {
            Class clazz = msj.getClass();
            if (clazz.isInstance(String.class)) {
                System.out.println((String) msj);
            } else if (clazz.isArray()) {

            }
        }
    }

    @Override
    public void muestraLista(List<?> lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void muestraMapa(Map<Object, Object> mapa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void muestraMensajes(Object... msj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void muestraMatriz(Object[][] matriz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
