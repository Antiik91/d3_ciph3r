/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;


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
public class DESTest {

    private DES des;

    public DESTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        try {
            des = new DES();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
    }


    @Test
    public void encryptingKeyCantBeEmpty() {
        String empty = "";
        try {
            this.des.encrypt("ThisHasNoMeaning", empty);
            assertFalse(this.des.getKey().isEmpty());
        } catch (Exception e) {

        }
    }

    @Test
    public void decrypingKeyCantBeEmpty() {
        String empty = "";
        try {
            this.des.decyrpt("ThishasnoMeaning", empty);
            assertFalse(this.des.getKey().isEmpty());
        }catch(Exception e) {
            
        }
    }
    @Test
    public void encryptNullPlainReturnsNull() {
        try{
        byte[] shouldBeNull = this.des.encrypt(null, null);
            assertTrue(shouldBeNull == null);
        } catch(Exception e) {
            
        }
    }
    
    @Test
    public void decryptNullCipherReturnsNull() {
        try {
            byte[] shouldBeNull = this.des.decyrpt(null, null);
            assertTrue(shouldBeNull == null);
        }catch(Exception e) {
            
        }
    }
    @Test(expected = AssertionError.class) 
    public void nullKeyThrowsExceptionInEncryption() {
        try{
            this.des.encrypt("Welp", null);
        }catch(Exception e) {
            assertEquals(NullPointerException.class, e);
        }
    }
    @Test(expected = AssertionError.class)
    public void nullKeyTrhowsExceptionDecryption(){
        try{
            this.des.decyrpt("NoMeaning", null);
        }catch(Exception e) {
            assertEquals(NullPointerException.class, e);
        }
    }
    @Test
    public void keyGenRightLenght(){
        this.des.generateKey();
        assertEquals(8, des.getKeyString().length());
    }
    @Test
    public void encryptWorks(){
        //Test case for working encryption..
    }
    
    @Test
    public void decryptWorks() {
        //Test case for working decryption...
    }
}
