/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;
import fi.antiik.d3ciph3r.filehandler.*;
/**
 *
 * @author janantik
 */
public class Logic {
private CaesarCipher cs;

    public Logic() {
        this.cs = new CaesarCipher();
    }

    public String encrytpCaesar(String plaintext, int shift) {
        return this.cs.encrypt(plaintext, shift);
    }

    public String decryptCaesar(String cipherText, int shift) {
        return this.cs.decrypt(cipherText, shift);
    }
    
    public String returnFileAsString(String path) throws Exception{
        return FileReader.readFile(path);
    }
    
    public void saveFile(String path, String content) throws Exception {
        FileSaver.saveFile(path, content);
    }
}
