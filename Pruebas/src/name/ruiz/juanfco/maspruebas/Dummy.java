/**
 * Esta clase se utiliza para hacer multitud de pruebas y no pertenece al tutorial de Java.
 */
package name.ruiz.juanfco.maspruebas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

// ------------------Clases que luego se probarán en Dummy.---------------------
class Bicicleta {

    // la clase Bicicleta tiene
    // tres campos
    public int cadencia;
    public int marcha;
    public int velocidad;

    // la clase Bicicleta tiene
    // un constructor
    public Bicicleta(int cadenciaInicio, int velocidadInicio, int marchaInicio) {
        marcha = marchaInicio;
        cadencia = cadenciaInicio;
        velocidad = velocidadInicio;
    }

    // la clase Bicicleta tiene
    // cuatro métodos
    public void setCadencia(int nuevoValor) {
        cadencia = nuevoValor;
    }

    public void setGear(int nuevoValor) {
        marcha = nuevoValor;
    }

    public void applyBrake(int decrement) {
        velocidad -= decrement;
    }

    public void velocidadUp(int increment) {
        velocidad += increment;
    }

    @Override
    public String toString() {
        return "Bicicleta{" + "cadencia=" + cadencia + ", marcha=" + marcha + ", velocidad=" + velocidad + '}';
    }

}

class BicicletaMontaña extends Bicicleta {

    // la subclase BicicletaMontaña tiene
    // un campo
    public int alturaSillin;

    // la subclase BicicletaMontaña tiene
    // un constructor
    public BicicletaMontaña(int alturaInicial, int cadenciaInicial,
            int velocidadInicial, int marchaInicial) {
        super(cadenciaInicial, velocidadInicial, marchaInicial);
        alturaSillin = alturaInicial;
    }

    // la subclase BicicletaMontaña tiene
    // un método
    public void setAlturaSillin(int nuevoValor) {
        alturaSillin = nuevoValor;
    }

    @Override
    public String toString() {
        return "BicicletaMonta\u00f1a{" + "alturaSillin="
                + alturaSillin + ", cadencia="
                + cadencia + ", marcha="
                + marcha + ", velocidad="
                + velocidad + '}';
    }

}

class Standard {

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

// ------------------ Fin de definición de clases a probar ---------------------
/**
 * Clase para la realización de pruebas de ejecución de código de las clases de
 * ejemplos y ejercicios del Tutorial de Java de Oracle
 *
 * @author hamfree
 */
public class Dummy {

    public static void main(String[] args) {
        Dummy d = new Dummy();

        d.testBicicletas();
        d.testStandard();

    }

    public void testBicicletas() {
        Bicicleta bici = new Bicicleta(5, 15, 3);
        BicicletaMontaña bicimnt = new BicicletaMontaña(20, 7, 2, 1);

        this.muestraObjeto(bici);
        this.muestraObjeto(bicimnt);
    }

    public void testStandard() {
        Standard st = new Standard();
        try {
            st.calculaSuma();
        } catch (IOException ex) {
            Logger.getLogger(Dummy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Métodos de utilidad
    public void muestraObjeto(Object o) {
        Bicicleta b;
        BicicletaMontaña bm;

        if (o instanceof Bicicleta) {
            b = (Bicicleta) o;
            System.out.println(b.toString());
        } else if (o instanceof BicicletaMontaña) {
            bm = (BicicletaMontaña) o;
            System.out.println(bm.toString());
        } else {
            System.out.println("Objeto desconocido: " + o.toString());
        }

    }
}
