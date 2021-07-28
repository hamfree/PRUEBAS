package es.nom.juanfranciscoruiz.pruebamaven.inicio;

import es.nom.juanfranciscoruiz.pruebamaven.util.Utiles;

/**
 * Muestra las propiedades del sistema. Lo importante de este proyecto es el uso de toolchains en el pom.xml 
 * mediante el plugin y la configuracion de toolchains en 'toolchains.xml'
 */
public class App {

  public static void main(String[] args) {
    System.out.println(Utiles.getProperties());
    System.out.println(Utiles.getFeatures());
    System.exit(0);
  }

}
