/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author hamfree
 */
public class TestVista {

    private final static String[] PARAMS = {"exploraObjeto",
        "exploraVector",
        "exploraLista",
        "exploraMapa",
        "ayuda"};

    private final Vista v;
    private Object nulo;
    private final Byte octeto;
    private final Short corto;
    private final Integer entero;
    private final Long largo;
    private final Double doble;
    private final BigDecimal cantidad;
    private final Character caracter;
    private final String cadena;

    private final Integer[] venteros;
    private final Integer[] vunidades;
    private final Integer[][] vdecenas;
    private final Integer[][][] vcentenas;
    private final Character[] caracteres;
    private final Double[] vdobles;
    private final BigDecimal[] vmonedas;
    private Object[] vectorVacio = {};
    private final Object[] vector;
    private final Object[][] matriz;
    private final Object[][][][] matriz4;

    private final ArrayList<Object> listaVacia = new ArrayList<>(0);
    private final ArrayList<Object> lista = new ArrayList<>();
    private final ArrayList<Object> otraLista = new ArrayList<>();

    HashMap<Object, Object> elMapa = new HashMap<>();

    /**
     * Punto de inicio del test
     *
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        TestVista tv = new TestVista();
        if (args.length > 0) {
            if (args[0] != null) {
                if (args[0].equalsIgnoreCase(PARAMS[0])) {
                    tv.test();
                } else if (args[0].equalsIgnoreCase(PARAMS[1])) {
                    tv.testVector();
                } else if (args[0].equalsIgnoreCase(PARAMS[2])) {
                    tv.testLista();
                } else if (args[0].equalsIgnoreCase(PARAMS[3])) {
                    tv.testMapa();
                } else if (args[0].equalsIgnoreCase(PARAMS[4])) {
                    tv.ayuda();
                } else {
                    System.out.println("El parámetro '" + args[0] + "' es "
                            + "incorrecto. Indique 'ayuda'"
                            + "para ver la lista de parámetros disponibles");
                }
            }
        } else {
            System.out.println("Este programa necesita un parámetro. "
                    + "Indique 'ayuda' para ver la lista de parámetros "
                    + "disponibles");
        }
    }

    /**
     * Constructor del Test, que ademas inicializa todas las variables de prueba
     */
    public TestVista() {

        v = new VistaImpl();

        octeto = new Byte("2");
        corto = Short.parseShort("13");
        entero = Integer.MAX_VALUE;
        largo = Long.MAX_VALUE;
        doble = Double.MAX_VALUE;
        cantidad = new BigDecimal(3.145916);
        caracter = 'A';
        cadena = "Una cadena";

        venteros = new Integer[10];
        caracteres = new Character[8];
        vdobles = new Double[6];
        vmonedas = new BigDecimal[2];
        vector = new Object[4];
        matriz = new Object[5][];

        vunidades = new Integer[9];
        vdecenas = new Integer[9][9];
        vcentenas = new Integer[9][9][9];
        matriz4 = new Object[1][][][];

        venteros[0] = 10;
        venteros[1] = 9;
        venteros[2] = 8;
        venteros[3] = 7;
        venteros[4] = 6;
        venteros[5] = 5;
        venteros[6] = 4;
        venteros[7] = 3;
        venteros[8] = 2;
        venteros[9] = 1;

        caracteres[0] = 'z';
        caracteres[1] = 'y';
        caracteres[2] = 'x';
        caracteres[3] = 'w';
        caracteres[4] = 'v';
        caracteres[5] = 'u';
        caracteres[6] = 't';
        caracteres[7] = 's';

        vdobles[0] = 16.134463;
        vdobles[1] = 212.216122;
        vdobles[2] = 8913.418773;
        vdobles[3] = 48212.427642;
        vdobles[4] = 151000.568541;
        vdobles[5] = 6222123.158226;

        vmonedas[0] = new BigDecimal("7.56");
        vmonedas[1] = new BigDecimal("8.74");

        vectorVacio = null;
        vector[0] = cadena;
        vector[1] = entero;
        vector[2] = doble;
        vector[3] = cantidad;

        matriz[0] = vmonedas;
        matriz[1] = vdobles;
        matriz[2] = vectorVacio;
        matriz[3] = venteros;
        matriz[4] = caracteres;

        for (int i = 0; i < vunidades.length; i++) {
            vunidades[i] = i;
        }

        for (int i = 0; i < vdecenas.length; i++) {
            vdecenas[i] = vunidades;
        }

        for (int i = 0; i < vcentenas.length; i++) {
            for (int j = 0; j < vcentenas[0].length; j++) {
                vcentenas[i][j] = vdecenas[i];
            }
        }

        matriz4[0] = vcentenas;

        // lista con 10 elementos, el noveno es a su vez una lista
        lista.add(cadena); //0
        lista.add(entero);
        lista.add(doble);
        lista.add(cantidad);
        lista.add(nulo);
        lista.add(caracter);
        lista.add(corto);
        lista.add(largo);
        lista.add(octeto);
        lista.add(otraLista); //9

        // otraLista: 2 (Integer) ,'w' (Character) ,'3.413' (Double) ,8.74 (BigDecimal)
        otraLista.add(venteros[1]);
        otraLista.add(caracteres[4]);
        otraLista.add(vdobles[2]);
        otraLista.add(vmonedas[1]);

        // un mapa con distintos objetos
        elMapa.put("Uno", venteros);
        elMapa.put("dos", vdobles);
        elMapa.put("Tres", vmonedas);
        elMapa.put("cuatro", vector);
        elMapa.put("Cinco", nulo);
        elMapa.put("Seis", largo);
        elMapa.put("Siete", corto);
        elMapa.put("Ocho", lista);
        elMapa.put("Nueve", cantidad);
    }

    public void test() {
        StringBuilder sb = new StringBuilder();

        System.out.println("Pruebas con exploraObjeto()");
        System.out.println(v.exploraObjeto(nulo).toString());
        System.out.println(v.exploraObjeto(octeto));
        System.out.println(v.exploraObjeto(corto));
        System.out.println(v.exploraObjeto(entero));
        System.out.println(v.exploraObjeto(largo));
        System.out.println(v.exploraObjeto(doble));
        System.out.println(v.exploraObjeto(cantidad));
        System.out.println(v.exploraObjeto(caracter));
        System.out.println(v.exploraObjeto(cadena));
        System.out.println(v.exploraObjeto(doble));

    }

    public void testVector() {
        System.out.println("\nPruebas con ExploraVector()");
        System.out.println(v.exploraVector(vector, 0).toString());

        System.out.println("\nPruebas con ExploraVector() - con parametro de dos dimensiones");
        System.out.println(v.exploraVector(matriz, 0).toString());

        System.out.println("\nPruebas con ExploraVector() - con parametros de tres dimensiones");
        System.out.println(v.exploraVector(vcentenas, 0).toString());
    }

    public void testLista() {
        System.out.println("\nPruebas con exploraLista() - lista vacia");
        System.out.println(v.exploraLista(listaVacia, 0).toString());

        System.out.println("\nPruebas con exploraLista() - lista que contiene otra lista");
        System.out.println(v.exploraLista(lista, 0).toString());
    }

    public void testMapa() {
        System.out.println("Pruebas con exploraMapa()");
        System.out.println(v.exploraMapa(elMapa, 0).toString());
    }

    public void ayuda() {
        StringBuilder sb = new StringBuilder();
        sb.append("Debe indicar uno de los cuatro siguientes ")
                .append("parámetros:\n");
        for (int i = 0; i < 4; i++) {
            String s = PARAMS[i];
            sb.append("'").append(s).append("'").append("\n");
        }
        sb.append(" para poder ejecutar el test correspondiente.");
        System.out.println(sb.toString());
    }
}
