package es.nom.juanfranciscoruiz.prueba2maven;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * Programa simple para hacer pruebas con Maven
 *
 */
public class App {

  final static String SL = System.getProperty("line.separator");

  public static void main(String[] args) {
    App app = new App();
    
    System.out.println(app.getArgs(args));
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
    sb.append("[Propiedades del sistema]").append(SL);
    sb.append("Procesadores disponibles para Java..: ").append(rt.availableProcessors()).append(SL)
      .append("Memoria libre en la MVJ.............: ").append(freeMemory).append(SL)
      .append("Memoria disponible para Java........: ").append(maxMemory).append(SL)
      .append("Memoria total existente MVJ.........: ").append(totalMemmory);
    return sb.toString();
  }

  public String getProperties() {
    StringBuilder sb = new StringBuilder();
    Properties p = System.getProperties();
    sb.append("[Caracteristicas de la JVM]").append(SL);
    p.entrySet().forEach(entry -> {
      Object key = entry.getKey();
      Object value = entry.getValue();
      sb.append(key).append(" = ").append(value).append(SL);
    });
    return sb.toString();
  }
  
  public String getArgs(String[] args){
    Integer i = 0;
    StringBuilder sb = new StringBuilder();
    sb.append("[Argumentos de linea de comandos]").append(SL);
    if (args.length > 0) {
      for (String arg : args) {
        sb.append("Arg " ).append(++i).append(":").append(arg).append(", ");
      }
      sb.deleteCharAt(sb.length() - 2);
    } else {
      sb.append("No se han indicado argumentos.");
    }
    sb.append(SL);
    return sb.toString();
  }
}
