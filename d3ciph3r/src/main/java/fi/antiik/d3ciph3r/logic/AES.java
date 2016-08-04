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
 *
 * @author User
 */
public class AES {

    private static String IV = "BBBBBBBBBBBBBBBB";
    private SecretKey key;

    public AES() {
        initKey();
    }

    private void initKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            this.key = kg.generateKey();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static byte[] encrypt(String plaintext, SecretKey eKey) throws Exception {
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(eKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, eKey, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plaintext.getBytes("UTF-8"));

    }

    public void encryptAndDecrypt(String plaintext) {
        initKey();
        try {
            System.out.println("Message to ciphered: " + plaintext);
            byte[] encrypt = encrypt(plaintext, this.key);
            System.out.println("Cipher:");
            for (int i = 0; i < encrypt.length; i++) {
                System.out.print(new Integer(encrypt[i]) + " ");

            }
            System.out.println("");
            System.out.println("And back again: " + decrypt(encrypt, this.key));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDecrypt(byte[] encryptedText) {
        try {
            String decryptedText = decrypt(encryptedText, this.key);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decrypt(byte[] encryptedText, SecretKey eKey) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(eKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, eKey, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(encryptedText), "UTF-8");
    }
}
