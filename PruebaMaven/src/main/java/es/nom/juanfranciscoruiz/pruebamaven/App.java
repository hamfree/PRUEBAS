package es.nom.juanfranciscoruiz.pruebamaven;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Muestra las propiedades del sistema. Lo importante de este proyecto es el uso de toolchains en el pom.xml mediante el plugin y la
 * configuracion de toolchains en toolchains.xml
 *
 */
public class App {

  final static String SL = System.getProperty("line.separator");

  public static void main(String[] args) {
    App app = new App();

    System.out.println(app.getProperties());
    System.out.println(app.getFeatures());
    System.exit(0);
  }

  public String getFeatures() {
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

  public String getProperties() {
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
