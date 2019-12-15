package es.nom.juanfranciscoruiz.entradasalida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import es.nom.juanfranciscoruiz.utiles.Types;

/**
 *
 * @author hamfree
 */
public class FicheroUtil {

    public static void writeFile(String path, String content) throws Exception {
        if (Types.isNullOrEmpty(path) || Types.isNullOrEmpty(content)) {
            return;
        }
        Files.write(Paths.get(path), content.getBytes());
    }

    public static String readFileAsString(String filePath) throws java.io.IOException {

        StringBuilder fileData = new StringBuilder(1000);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
                buf = new char[1024];
            }
        }
        return fileData.toString();
    }

}
