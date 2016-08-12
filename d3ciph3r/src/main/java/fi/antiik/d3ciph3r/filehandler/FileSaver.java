/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.filehandler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author User
 */
public class FileSaver {
    
    public FileSaver() {
    }
    
    public static void saveFile(String path, String content) throws Exception {
        File text = new File(path);
        FileOutputStream is = new FileOutputStream(text);
        OutputStreamWriter osw =new OutputStreamWriter(is);
        Writer writer = new BufferedWriter(osw);
        writer.write(content);
        writer.close();
    }
}
