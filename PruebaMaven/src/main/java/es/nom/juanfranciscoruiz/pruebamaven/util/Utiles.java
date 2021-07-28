package es.nom.juanfranciscoruiz.pruebamaven.util;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * Clase de utilidad con métodos frecuentemente usados por la aplicación
 * 
 * @author hamfree
 */
public class Utiles {

  /**
   * Constante con el carácter de salto de línea del SO
   */
  final static String SL = System.getProperty("line.separator");

  /**
   * Devuelve en una cadena las características de procesador y memoria 
   * de la MVJ en la que se ejecuta el método.
   * @return una cadena con las características de procesador y memoria 
   * de la MVJ 
   */
  public static String getFeatures() {
    StringBuilder sb = new StringBuilder();
    Runtime rt = Runtime.getRuntime();
    NumberFormat numberFormatter = NumberFormat.getNumberInstance(Locale.getDefault());
    System.gc();
    String freeMemory = numberFormatter.format(rt.freeMemory());
    String maxMemory = numberFormatter.format(rt.maxMemory());
    String totalMemmory = numberFormatter.format(rt.totalMemory());
    sb.append("Procesadores disponibles para Java..: ").append(rt.availableProcessors()).append(SL)
            .append("Memoria libre en la MVJ.......: ").append(freeMemory).append(SL)
            .append("Memoria disponible para Java..: ").append(maxMemory).append(SL)
            .append("Memoria total existente MVJ...: ").append(totalMemmory);
    return sb.toString();
  }

  /**
   * Devuelve en una cadena las propiedades del sistema de la MVJ actual.
   * @return  una cadena con las propiedades del sistema 
   */
  public static String getProperties() {
    StringBuilder sb = new StringBuilder();
    Properties p = System.getProperties();
    p.entrySet().forEach(entry -> {
      Object key = entry.getKey();
      Object value = entry.getValue();
      sb.append(key).append(" = ").append(value).append(SL);
    });
    return sb.toString();
  }
}
