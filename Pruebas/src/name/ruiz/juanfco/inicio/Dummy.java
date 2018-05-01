/**
 * Esta clase se utiliza para hacer multitud de pruebas y no pertenece al
 * tutorial de Java.
 */
package name.ruiz.juanfco.inicio;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import name.ruiz.juanfco.beans.Bicicleta;
import name.ruiz.juanfco.beans.BicicletaMontaña;
import name.ruiz.juanfco.beans.Standard;

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
