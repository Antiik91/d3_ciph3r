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

    public AES() throws Exception {
        initKey();
    }

    private void initKey() throws Exception {

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);
        this.key = kg.generateKey();

    }

    public byte[] encrypt(String plaintext) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(eKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plaintext.getBytes("UTF-8"));

    }

    public String decrypt(byte[] encryptedText) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
//        SecretKeySpec key = new SecretKeySpec(eKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, this.key, new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(encryptedText), "UTF-8");
    }
}
