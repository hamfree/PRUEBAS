package name.ruiz.juanfco.hilosyprocesos;

/**
 *
 * @author hamfree
 */
public class Pausa {

    public static void unSeg() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void variosSeg(long s) {
        try {
            Thread.sleep(s * 1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
