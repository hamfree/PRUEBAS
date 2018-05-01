package name.ruiz.juanfco.inicio;

import name.ruiz.juanfco.pruebas.reflexion.Reflexion;

/**
 *
 * @author hamfree
 */
public class UsandoReflexion {

    public static void main(String[] args) {
        UsandoReflexion ur = new UsandoReflexion();
        Reflexion rflx = new Reflexion();

        rflx.extraerDatosClase();
        rflx.extraerAtributos();
        rflx.extraerConstructores();
        rflx.extraerMetodos();
    }
}
