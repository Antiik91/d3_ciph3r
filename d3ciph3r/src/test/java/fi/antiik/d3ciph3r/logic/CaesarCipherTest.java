package fi.antiik.d3ciph3r.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import fi.antiik.d3ciph3r.logic.CaesarCipher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MarraPera
 */
public class CaesarCipherTest {

    CaesarCipher testCaesar;

    public CaesarCipherTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.testCaesar = new CaesarCipher();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void encryptingMessage() {
        String textTobeCiphered = "Hello World! Testing!";
        String compare = "Khoor Zruog! Whvwlqj!";
        assertEquals(compare, testCaesar.encrypt(textTobeCiphered, 3));
    }

    @Test
    public void abcTodef() {
        assertEquals("def", testCaesar.encrypt("abc", 3));
    }

    @Test
    public void xyzToabc() {
        assertEquals("abc", testCaesar.encrypt("xyz", 3));
    }

    @Test
    public void decryptingMessage() {
        assertEquals("I need this message to be ciphered. Will it work?", testCaesar.decrypt("L qhhg wklv phvvdjh wr eh flskhuhg. Zloo lw zrun?", 3));
    }

    @Test
    public void shiftCantBeZero() {
        this.testCaesar.setShift(0);
        assertEquals(3, this.testCaesar.getShift());
    }

    @Test
    public void shiftCantBeNegative() {
        this.testCaesar.setShift(-234);
        assertEquals(3, this.testCaesar.getShift());
    }

    @Test
    public void bigShiftEncryption() {
        this.testCaesar.setShift(4500);
        assertEquals("Vjku ku vjg vguv hqt dki ujkhv. Yknn kv yqtm?", this.testCaesar.encrypt("This is the test for big shift. Will it work?", 4500));

    }

    @Test
    public void encryptAndDecrypt() {
        String message = "Hello my good sir, what a wonderful day to encrypt a message!";
        String decrypt = this.testCaesar.encrypt(message, 4);
        assertEquals(message, this.testCaesar.decrypt(decrypt, 4));
    }
    @Test
    public void alphabetsEncryptAndDecryptLower() {
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String decrypt = this.testCaesar.encrypt(alphabets, 3);
        assertEquals(alphabets, this.testCaesar.decrypt(decrypt,3));
    }
    
    @Test
    public void alphabetsEncryptAndDecryptUpper() {
        String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String decrypt = this.testCaesar.encrypt(alphabets, 3);
        assertEquals(alphabets, this.testCaesar.decrypt(decrypt, 3));
    }
}
