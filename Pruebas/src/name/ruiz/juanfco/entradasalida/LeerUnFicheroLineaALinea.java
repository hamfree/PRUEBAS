package name.ruiz.juanfco.entradasalida;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

public class LeerUnFicheroLineaALinea {

    private void process(String line) {
        System.out.println(line);
    }

    public void read() throws IOException {
        try (Stream<String> lines = lines(get("file.txt"), StandardCharsets.UTF_8)) {
            // can be used Charset.defaultCharset()
            lines.forEachOrdered(line -> process(line));    // can be used for unordered lines.forEach
        }
    }

}
