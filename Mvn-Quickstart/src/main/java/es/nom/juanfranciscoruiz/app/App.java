package es.nom.juanfranciscoruiz.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {

  private static final Properties m = System.getProperties();
  private static final String SL = System.getProperty("line.separator");

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    sb.append("[Propiedades de la JVM]").append(SL);
    for (Map.Entry<Object, Object> en : m.entrySet()) {
      Object key = en.getKey();
      Object value = en.getValue();
      sb.append(key).append(" = ").append(value).append(SL);
    }
    System.out.println(sb.toString());
    System.exit(0);
  }
}
