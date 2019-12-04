package name.ruiz.juanfco.utiles;

import name.ruiz.juanfco.patrones.IteradorObjetos;

public class Generador {
    
    public static final String INDENTACION = "  ";
    public static final int LONG_MAX_VALOR = 64;
    public static final int MAX_HIJOS = 64;
    public static final int MAX_LONG_CADENA = 1024 * 16;

    
    public static String genera(Object objeto) {
        String s = "";

        IteradorObjetos iterator = new IteradorObjetos("object", objeto);

        // imprime el primer elemento sin la parte del nombre
        if (iterator.siguiente()) {
            String valorComoUnaCadena = iterator.getValorComoCadena();

            if (valorComoUnaCadena == null) {
                s += "null";
            } else {
                s += truncaCadena(valorComoUnaCadena);
            }
        }

        // imprime los elementos subsiguientes como pares nombre = valor
        while (iterator.siguiente()) {
            if (s.length() >= MAX_LONG_CADENA) {
                return s;
            }

            if (iterator.getHijo() >= MAX_HIJOS) {
                iterator.siguientePadre();
                continue;
            }

            String valorComoCadena = iterator.getValorComoCadena();

            s += System.lineSeparator();
            s += indenta(iterator.getProfundidad()) + truncaCadena(iterator.getNombre());
            if (valorComoCadena == null) {
                s += " = null";
            } else {
                s += " = " + truncaCadena(valorComoCadena);
            }
        }

        return s;
    }

    private static String truncaCadena(String cadena) {
        if (cadena != null && cadena.length() > LONG_MAX_VALOR) {
            cadena = cadena.substring(0, LONG_MAX_VALOR) + "...";
        }
        return cadena;
    }
    
    private static StringBuilder indenta(int profundidad) {
        StringBuilder s = new StringBuilder(profundidad * INDENTACION.length());
        for (int i = 0; i < profundidad; i++) {
            s.append(INDENTACION);
        }
        return s;
    }

}
