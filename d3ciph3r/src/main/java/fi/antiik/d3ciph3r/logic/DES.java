package fi.antiik.d3ciph3r.logic;


import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class DES {

    private SecretKey key;
    private static Cipher cipher;

    public DES() {
        try {
             this.cipher = Cipher.getInstance("DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    }

    private void initializeKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(56);
            this.key = kg.generateKey();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DES.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    public String decrypt(byte[] bits){
        try  {
        String plainTxt = "";
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(this.cipher.doFinal(bits), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public byte[] encrypt(String plaintext) {
        try {
            initializeKey();
            this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
            return this.cipher.doFinal(plaintext.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
