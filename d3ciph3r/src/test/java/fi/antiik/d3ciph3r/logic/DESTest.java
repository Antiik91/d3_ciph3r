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

    public boolean compareArrays(byte[] a, byte[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void encryptingKeyCantBeEmpty() {
        String empty = "";
        try {
            this.des.encryptPlaintext("ThisHasNoMeaning", empty);
            assertFalse(this.des.getKey().isEmpty());
        } catch (Exception e) {

        }
    }

    @Test
    public void decrypingKeyCantBeEmpty() {
        String empty = "";
        try {
            this.des.decryptData(null, empty);
            assertFalse(this.des.getKey().isEmpty());
        } catch (Exception e) {

        }
    }

    @Test
    public void encryptNullPlainReturnsNull() {
        try {
            byte[] shouldBeNull = this.des.encryptPlaintext(null, null);
            assertTrue(shouldBeNull == null);
        } catch (Exception e) {

        }
    }

    @Test
    public void decryptNullCipherReturnsNull() {
        try {
            byte[] nullArray = null;
            String key = null;
            byte[] shouldBeNull = this.des.decryptData(nullArray, key);
            assertTrue(shouldBeNull == null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test(expected = AssertionError.class)
    public void nullKeyThrowsExceptionInEncryption() {
        try {
            this.des.encryptPlaintext("Welp", null);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e);
        }
    }

    @Test(expected = AssertionError.class)
    public void nullKeyTrhowsExceptionDecryption() {
        try {
            this.des.decryptData(null, null);
        } catch (Exception e) {
            assertEquals(NullPointerException.class, e);
        }
    }

    @Test
    public void keyGenRightLenght() {
        this.des.generateKey();
        assertEquals(8, des.getKeyString().length());
    }

    @Test
    public void encryptAlphabetsLowerCase() {
        byte[] correct = {39, 11, 124, -26, 4, 14, 112, 106, 4, 31, 50, -86, 20, 60, 118, 26, 33, 55, 38, -74, 48, -54, -94, 78, 2, 37, -113, 66, 30, 4, 11, 94};
        try {
            byte[] encrypt = this.des.encryptPlaintext("abcdefghijklmnopqrstuvwxyz", "NeedAKey");
            assertTrue(compareArrays(correct, encrypt));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void encryptAlphabetsUpperCase() {
        byte[] correct = {106, 98, -55, 7, 109, -126, -123, 115, 73, 118, -121, 75, 125, -80, -125, 3, 108, 94, -109, 87, 89, 70, 87, 87, 6, 37, 10, 2, 6, 0, 10, 86};
        try {
            byte[] encrypt = this.des.encryptPlaintext("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "NeedAKey");
            assertTrue(compareArrays(correct, encrypt));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void encryptSpecialChars() {
        byte[] correct = {97, 90, -73, 39, 84, -84, -13, 75, 125, 114, -96, -104, 84, -17, 91, 76, 17, 9, 109, 120, 105, -59, 113, 28, 76, 76, 65, 57, 82, -84, 79, 15};
        try {
            byte[] encrypt = this.des.encryptPlaintext("!#¤%&/()=?`^*-_.:,;<>@£${[]}~*", "NeedAKey");
            assertTrue(compareArrays(correct, encrypt));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void encryptNumbers() {
        byte[] correct = {79, 101, -18, -31, 78, 76, -17, 29, 71, 101, -26, -31, 70, 76, -29, 29, 2, 1, -1, 0, 92, 4, 3, 12};
        try {
            byte[] encrypt = this.des.encryptPlaintext("1 2 3 4 5 6 7 8 9 0", "NeedAKey");
            assertTrue(compareArrays(correct, encrypt));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void decryptNumbers() {
        byte[] correct = {49, 32, 50, 32, 51, 32, 52, 32, 53, 32, 54, 32, 55, 32, 56, 32, 57, 32, 48};
        byte[] compare = {79, 101, -18, -31, 78, 76, -17, 29, 71, 101, -26, -31, 70, 76, -29, 29, 2, 1, -1, 0, 92, 4, 3, 12};
        try {
            compare = this.des.decryptData(compare, "NeedAKey");
            assertTrue(compareArrays(correct, compare));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void decryptAlphabetLowerCase() {
        byte[] alphalower = {39, 11, 124, -26, 4, 14, 112, 106, 4, 31, 50, -86, 20, 60, 118, 26, 33, 55, 38, -74, 48, -54, -94, 78, 2, 37, -113, 66, 30, 4, 11, 94};
        byte[] correct = "abcdefghijklmnopqrstuvwxyz".getBytes();
        try {
            alphalower = this.des.decryptData(alphalower, "NeedAKey");
            assertTrue(compareArrays(correct, alphalower));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void decryptAlphabetUpperCase() {
        byte[] alphaUpper = {106, 98, -55, 7, 109, -126, -123, 115, 73, 118, -121, 75, 125, -80, -125, 3, 108, 94, -109, 87, 89, 70, 87, 87, 6, 37, 10, 2, 6, 0, 10, 86};
        byte[] correct = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
        try {
            alphaUpper = this.des.decryptData(alphaUpper, "NeedAKey");
            assertTrue(compareArrays(correct, alphaUpper));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void decryptSpecialChars() {
        byte[] special = {97, 90, -73, 39, 84, -84, -13, 75, 125, 114, -96, -104, 84, -17, 91, 76, 17, 9, 109, 120, 105, -59, 113, 28, 76, 76, 65, 57, 82, -84, 79, 15};
        byte[] correct = "!#¤%&/()=?`^*-_.:,;<>@£${[]}~*".getBytes();
        try {
            special = this.des.decryptData(special, "NeedAKey");
            assertTrue(compareArrays(correct, special));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void encryptAndDecrypt() {
        String original = "Hello world! What a wonderful day!";
        String key = "NeedAKey";
        try {
            byte[] crypted = this.des.encryptPlaintext(original, key);
            String encrypted = new String(this.des.decryptData(crypted, key));
            assertTrue(original.equals(encrypted));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
