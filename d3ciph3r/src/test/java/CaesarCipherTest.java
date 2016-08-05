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
    CaesarCipher testCaesar13shift;

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
        assertEquals(compare, testCaesar.encrypt(textTobeCiphered));
    }

    @Test
    public void abcTodef() {
        assertEquals("def", testCaesar.encrypt("abc"));
    }

    @Test
    public void xyzToabc() {
        assertEquals("abc", testCaesar.encrypt("xyz"));
    }

    @Test
    public void decryptingMessage() {
        assertEquals("I need this message to be ciphered. Will it work?", testCaesar.decrypt("L qhhg wklv phvvdjh wr eh flskhuhg. Zloo lw zrun?"));
    }

    
  

}
