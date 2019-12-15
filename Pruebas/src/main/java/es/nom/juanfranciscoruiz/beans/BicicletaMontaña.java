package es.nom.juanfranciscoruiz.beans;

/**
 *
 * @author hamfree
 */
public class BicicletaMontaña extends Bicicleta {

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
