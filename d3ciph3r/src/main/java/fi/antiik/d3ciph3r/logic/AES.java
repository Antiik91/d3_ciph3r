/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Advanced Decryption Standard (AES) is a subset of the Rijndael cipher,
 * developed originally by two Belgian cryptographers. AES has fixed block size
 * of 128 bits and key size of 128, 192 or 256 bits.
 *
 * @author janantik
 */
public class AES {

    // initialization vector. 16 bytes should be random or pseudorandom.
    private static String IV = "AAAAAAAAAAAAAAAA";
    //key to be used in encrypting and decrypting the data.
    private SecretKey key;
    private Cipher cipher;

    /**
     * Creates a new secret key when created.
     *
     * @throws Exception
     */
    public AES() throws Exception {
        this.cipher = this.cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
    }

    /**
     * Method create a new secret key to be used both encryption and decryption.
     *
     * @throws Exception
     */
    private void initializeKey() throws Exception {

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        this.key = kg.generateKey();

    }

    public String getIV() {
        return IV;
    }

    public void setIV(String IV) {
        if (IV.getBytes().length == 15) {
            AES.IV = IV;
        }
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {

        this.key = key;

    }

    /**
     * Encrypts the data and return byte array of encrypted data.
     *
     *
     * @param plaintext text to be
     * @return byte array of encypted data
     * @throws Exception
     */
    public byte[] encrypt(String plaintext) throws Exception {
        if (this.key == null) {
            initializeKey();
        }
        cipher.init(Cipher.ENCRYPT_MODE, this.key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plaintext.getBytes("UTF-8"));

    }

    /**
     * Decrypts the byte array and returns String from the data decrypted from
     * the byte array.
     *
     * @param encryptedData byte array to be decrypted
     * @return a new String consisting plaintext of crypted data.
     * @throws Exception
     */
    public String decrypt(byte[] encryptedData) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(eKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, this.key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(encryptedData), "UTF-8");
    }
}
