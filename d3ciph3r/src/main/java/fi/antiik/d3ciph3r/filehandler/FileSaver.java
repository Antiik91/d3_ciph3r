package fi.antiik.d3ciph3r.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Class is used to save text files in given path.
 *
 * @author janantik
 */
public class FileSaver {

    public FileSaver() {
    }

    /**
     * Static method to save file in given path.
     *
     * @param path path where the file is saved.
     * @param content content of the file.
     * @throws Exception If path cannot be reached.
     */
    public static void saveFile(String path, String content) throws Exception {
        File text = new File(path);
        FileOutputStream is = new FileOutputStream(text);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer writer = new BufferedWriter(osw);
        writer.write(content);
        writer.close();
    }
}
