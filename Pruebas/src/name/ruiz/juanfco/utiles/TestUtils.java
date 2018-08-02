/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.utiles;

/**
 * Utilidades que facilitan el trabajo con las pruebas.
 *
 * @author hamfree
 */
public class TestUtils {

    /**
     * Imprime una cabecera o pie en la salida estandar para enmarcar un test de
     * un método (o cualquier otro item del que queremos colocar una cabecera)
     *
     * @param msg la cadena con el mensaje que se incluirá en la cabecera o pie
     * @param esInicio boolean que si es true indicará que es el inicio del test
     * y si es false que es el final
     */
    public static void marcoTest(String msg, boolean esInicio) {
        StringBuilder sb = new StringBuilder();
        if (msg != null && msg.length() > 0) {
            IO.prt(true, 1, "");
            IO.linea('-', 80);
            sb.append(Util.getSL());
            sb.append(msg);
            if (esInicio) {
                sb.append("() - INICIO");
            } else {
                sb.append("() - FIN");
            }
            IO.prt(true, 1, sb.toString());
            IO.linea('-', 80);
        }
    }

}