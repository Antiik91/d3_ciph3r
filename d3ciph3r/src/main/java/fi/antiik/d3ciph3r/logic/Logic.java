/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

import fi.antiik.d3ciph3r.filehandler.*;

/**
 * Logic for the program.
 *
 * @author janantik
 */
public class Logic {

    private CaesarCipher cs;

    /**
     * Creates objects used for cyrpting.
     */
    public Logic() {
        this.cs = new CaesarCipher();
    }

    /**
     * Uses Caesar cipher encryption to encrypt plaintext.
     *
     * @param plaintext String to be encrypted.
     * @param shift shift used for encrypting.
     * @return String of encrypted text aka ciphertext.
     */
    public String encrytpCaesar(String plaintext, int shift) {
        return this.cs.encrypt(plaintext, shift);
    }

    /**
     * Uses Caesar cipher decryption to decrypt cipher text
     *
     * @param cipherText encrypted text to be decrypted.
     * @param shift shift using for encrypting.
     * @return String of plaintext.
     */
    public String decryptCaesar(String cipherText, int shift) {
        return this.cs.decrypt(cipherText, shift);
    }

    /**
     * Reads the file and returns it as string.
     *
     * @param path Path to the file.
     * @return content of the file in string.
     * @throws Exception IO exception if unable to open the file.
     */
    public String returnFileAsString(String path) throws Exception {
        return FileReader.readFile(path);
    }

    /**
     * Saves the file.
     *
     * @param path path to the file.
     * @param content content to be saved.
     * @throws Exception
     */
    public void saveFile(String path, String content) throws Exception {
        FileSaver.saveFile(path, content);
    }

    /**
     * Checks that scanner can parse integer from input.
     *
     * @param scanner scanner to scan user input.
     * @return -1 if exception, positive input else.
     */
    public int handleCommand(String command) {
        try {
           int result = Integer.parseInt(command);
            return result;
        } catch (Exception e) {
            return -1;
        }
    }
}
