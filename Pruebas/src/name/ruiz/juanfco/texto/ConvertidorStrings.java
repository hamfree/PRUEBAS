package name.ruiz.juanfco.texto;

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
