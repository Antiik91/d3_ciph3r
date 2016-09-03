package fi.antiik.d3ciph3r.filehandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class is used to read file into a string.
 *
 * @author janantik
 */
public class FileReader {

    public FileReader() {
    }

    /**
     * Method reads the path and returns it's content in string.
     *
     * @param path path where the file is located.
     * @return String of file's content.
     * @throws IOException if invalid path.
     */
    public static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, Charset.defaultCharset());
    }
}
