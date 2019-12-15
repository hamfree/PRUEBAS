package es.nom.juanfranciscoruiz.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author hamfree
 */
public class Standard {

    public void calculaSuma() throws IOException {
        BufferedReader cin
                = new BufferedReader(new InputStreamReader(System.in));
        String number;
        String msg = "Número entero o <INTRO> para calcular suma y salir > ";
        StringBuilder sb = new StringBuilder();
        int total = 0;

        System.out.println("Esta programita calcula la suma de los números que introduzcas por consola.");
        System.out.print(msg);
        while ((number = cin.readLine()) != null) {
            try {
                if (number.length() > 0) {
                    sb.append(number).append(" + ");
                    total += Integer.parseInt(number);
                    System.out.print(msg);
                } else {
                    break;
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: Número no válido en la entrada");
                System.err.println(e.getLocalizedMessage());
                System.exit(1);
            }
        }
        String sumando = sb.substring(0, sb.length() - 3);
        System.out.println("La suma de " + sumando + " es " + total);
    }
}
