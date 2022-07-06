/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.nom.juanfranciscoruiz.app;

/**
 *
 * @author juanf
 */
public class App {

  record Point(int x, int y) {

  }

  public static void main(String[] args) {
    App obj = new App();
    System.out.println(obj.validateInput("java16"));
    obj.showRecords();
    String codepoint = "U+1F92A";   // crazy face
    System.out.println(convertCodePoints(codepoint));

  }

  private boolean validateInput(Object obj) {

    boolean result = false;

    // pattern matching
    if (obj instanceof String s && s.length() > 5) {
      if (s.equalsIgnoreCase("java16")) {
        result = true;
      }
    }

    // old time
    if (obj instanceof String) {
      String s = (String) obj;
      if (s.length() > 5) {
        if (s.equalsIgnoreCase("java16")) {
          result = true;
        }
      }
    }

    return result;
  }

  private void showRecords() {
    Point p1 = new Point(10, 20);
    System.out.println(p1.x());         // 10
    System.out.println(p1.y());         // 20

    Point p2 = new Point(11, 22);
    System.out.println(p2.x());         // 11
    System.out.println(p2.y());         // 22

    Point p3 = new Point(10, 20);
    System.out.println(p3.x());         // 10
    System.out.println(p3.y());         // 20

    System.out.println(p1.hashCode());  // 330
    System.out.println(p2.hashCode());  // 363
    System.out.println(p3.hashCode());  // 330

    System.out.println(p1.equals(p2));  // false
    System.out.println(p1.equals(p3));  // true
    System.out.println(p2.equals(p3));  // false
  }

  // Java, UTF-16
  // Convert code point to unicode
  static char[] convertCodePoints(String codePoint) {
    Integer i = Integer.valueOf(codePoint.substring(2), 16);
    char[] chars = Character.toChars(i);
    return chars;

  }

}
