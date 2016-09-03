package fi.antiik.d3ciph3r.logic;

import fi.antiik.d3ciph3r.filehandler.*;

/**
 * Logic for the program.
 *
 * @author janantik
 */
public class Logic {

    private CaesarCipher cs;
    private DES des;

    /**
     * Creates objects used for cyrpting.
     */
    public Logic() throws Exception {
        this.cs = new CaesarCipher();
        this.des = new DES();
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
     * Checks that command can be parsed to integer from input.
     *
     * @param command Command in String.
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

    /**
     * Check if the key is valid.
     *
     * @param key
     * @return true if the key is else false.
     */
    public boolean validKey(String key) {
        return key.length() == 8;
    }

    /**
     * Use DES to encrypt plaintext string.
     *
     * @param plaintext Text to be necrypted
     * @param key Key used in alghorithm.
     * @return ciphered text in byte array.
     * @throws Exception
     */
    public byte[] encryptDES(String plaintext, String key) throws Exception {
        byte[] encrypted = this.des.encryptPlaintext(plaintext, key);
        return encrypted;
    }

    /**
     * This Method parses the bytes from a string.
     *
     * @param byteArray encrypted text in a string.
     * @return bytes array of encrypted data or null if can't be parsed.
     */
    public byte[] getByteArrayFromString(String byteArray) {
        String[] bytesAsString = byteArray.split(" ");
        byte[] bytes = new byte[bytesAsString.length];
        try {
            for (int i = 0; i < bytesAsString.length; i++) {
                byte b = (byte) Integer.parseInt(bytesAsString[i]);
                bytes[i] = b;
            }
        } catch (Exception e) {
            return null;
        }

        return bytes;
    }

    /**
     * Use DES to decrypt cipher text in byte array.
     *
     * @param cipher
     * @param key
     * @return plain text in bytes.
     * @throws Exception
     */
    public byte[] decryptDES(byte[] cipher, String key) throws Exception {
        byte[] plainBytes = this.des.decryptData(cipher, key);
        return plainBytes;
    }
}
