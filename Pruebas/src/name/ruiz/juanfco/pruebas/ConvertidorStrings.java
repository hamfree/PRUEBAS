/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package name.ruiz.juanfco.pruebas;

/**
 *
 * @author hamfree
 */
public class ConvertidorStrings {

    public static void printBytes(byte[] array, String name) {
        for (int k = 0; k < array.length; k++) {
            System.out.println(name + "[" + k + "] = " + "0x"
                    + UnicodeFormatter.byteToHex(array[k]));
        }
    }
}
