package fi.antiik.d3ciph3r.logic;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Data Encryption Standard (DES) is a symmetric-key algorithm for the
 * encryption of electron data. Developed originally in the early 1970 at IBM.
 * DES is a block cipher, takes a plaintext bits and transform it to another
 * ciphertext of the same length. block size is 64 bits.
 *
 * @author janantik
 */
public class DES {

    private SecretKey key;
    private static Cipher cipher;

    public DES() throws Exception {

        this.cipher = Cipher.getInstance("DES");

    }

    /**
     * Generates a new secret key (56 bits) to be used both encryption and
     * dectypion of the data.
     *
     * @throws Exception
     */
    private void initializeKey() throws Exception {

        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        this.key = kg.generateKey();

    }

    /**
     * Decrypts the data from byte array and returns plaintext.
     *
     * @param bits encrypted data array.
     * @return plaintext string.
     * @throws Exception
     */
    public String decrypt(byte[] bits) throws Exception {

        this.cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(this.cipher.doFinal(bits), "UTF-8");

    }

    /**
     * Encrypts the plaintext and returns byte array of crypted data.
     *
     *
     * @param plaintext String to be crypted.
     * @return byte array of crypted data.
     * @throws Exception
     */
    public byte[] encrypt(String plaintext) throws Exception {

        initializeKey();
        this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
        return this.cipher.doFinal(plaintext.getBytes("UTF-8"));

    }
}
