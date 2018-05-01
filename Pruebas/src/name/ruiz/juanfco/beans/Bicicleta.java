package name.ruiz.juanfco.beans;

/**
 *
 * @author hamfree
 */
public class Bicicleta {

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
