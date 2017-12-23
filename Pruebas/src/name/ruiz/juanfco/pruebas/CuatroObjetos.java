package name.ruiz.juanfco.pruebas;

/**
 *
 * @author hamfree
 */
public class CuatroObjetos {

    // objetoB apuntará a objetoA para probar el metodo equals() y hashCode().
    private Object a;
    private Object b;
    private Object c;
    private Object d;

    // Constructores
    public CuatroObjetos() {
        this.a = new Object();

        // b es una referencia a el objeto b
        this.b = this.a;

        // c es una referencia a el objeto b que es una referencia al objeto a.
        this.c = this.b;

        /*
            d es una referencia a c que es una referencia a b que es una referencia
            al objeto a.
         */
        this.d = this.c;
    }

    public CuatroObjetos(Object a) {
        this.a = (a != null) ? a : new Object();
        this.b = this.a;
        this.c = this.b;
        this.d = this.c;
    }

    public CuatroObjetos(Object c, Object d) {
        this.a = new Object();
        this.b = this.a; // b es una referencia al objeto a
        this.c = (c != null) ? c : new Object();
        this.d = (d != null) ? d : new Object();
    }

    public CuatroObjetos(Object a, Object b, Object c, Object d) {
        /*
            Con este constructor a, b, c y d son instancias independientes (a menos que se
            les pase como parámetro el mismo objeto)
         */
        this.a = (a != null) ? a : new Object();
        this.b = (b != null) ? b : new Object();
        this.c = (c != null) ? c : new Object();
        this.d = (d != null) ? d : new Object();

    }

    // Getters y Setters
    public Object getC() {
        return c;
    }

    public void setC(Object c) {
        this.c = c;
    }

    public Object getD() {
        return d;
    }

    public void setD(Object d) {
        this.d = d;
    }

    public Object getA() {
        return a;
    }

    public void setA(Object a) {
        this.a = a;
    }

    public Object getB() {
        return b;
    }

    public void setB(Object b) {
        this.b = b;
    }
}
