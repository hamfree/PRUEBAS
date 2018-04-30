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
public interface Vista {
    public void muestraMensaje(Object msj);

    public void muestraLista(List<?> lista);

    public void muestraMapa(Map<Object, Object> mapa);

    public void muestraMensajes(Object... msj);

    public void muestraMatriz(Object[][] matriz);
}
