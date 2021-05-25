/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nom.juanfranciscoruiz.autotest.util;

import java.io.File;
import java.net.URL;

/**
 * Obtiene el directorio donde se encuentra la clase que se le pase como argumento.
 *
 * @author hamfree
 */
public class Directorio {

  private File DIRECTORIO_TRABAJO;

  public File get() {
    DIRECTORIO_TRABAJO = get(Directorio.class);
    return DIRECTORIO_TRABAJO;
  }

  public File get(Class clase) throws IllegalArgumentException {
    URL ubicacion;
    if (clase != null) {
      ubicacion = clase.getProtectionDomain().getCodeSource().getLocation();
    } else {
      throw new IllegalArgumentException("¡Parametro nulo!");
    }
    DIRECTORIO_TRABAJO = new File(ubicacion.getFile());

    return DIRECTORIO_TRABAJO;
  }
}
