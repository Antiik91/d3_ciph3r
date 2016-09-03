/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.util;

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
public class BitToolsTest {

    public BitToolsTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public boolean bitArrayTester(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean byteArrayTester(byte[] a, byte[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public int[] getCompareArray(byte[] a) {
        int[] byteInBit = new int[8];
        for (int i = 0; i < 8; i++) {
            byteInBit[i] = BitTools.getBit(a, i);
        }
        return byteInBit;
    }

    @Test
    public void byteValOneCorrect() {
        int[] first = {0, 0, 0, 0, 0, 0, 0, 1};
        byte[] oneByte = {1};
        int[] compare = getCompareArray(oneByte);
        assertTrue(bitArrayTester(compare, first));
    }

    @Test
    public void byteValTwoCorrect() {
        int[] second = {0, 0, 0, 0, 0, 0, 1, 0};
        byte[] secondByte = {2};
        int[] compare = getCompareArray(secondByte);
        assertTrue(bitArrayTester(compare, second));
    }

    @Test
    public void byteValFourCorrect() {
        int[] fourth = {0, 0, 0, 0, 0, 1, 0, 0};
        byte[] fourhtBit = {4};
        int[] compare = getCompareArray(fourhtBit);
        assertTrue(bitArrayTester(compare, fourth));
    }

    @Test
    public void byteVal32() {
        int[] threeTwo = {0, 0, 1, 0, 0, 0, 0, 0};
        byte[] arr = {32};
        int[] compare = getCompareArray(arr);
        assertTrue(bitArrayTester(compare, threeTwo));
    }

    @Test
    public void byteVal64() {
        int[] sixFour = {0, 1, 0, 0, 0, 0, 0, 0};
        byte[] arr = {64};
        int[] compare = getCompareArray(arr);
        assertTrue(bitArrayTester(compare, sixFour));
    }

    @Test
    public void fullByte() {
        int[] full = {1, 1, 1, 1, 1, 1, 1, 1};
        byte[] fullByte = {(byte) 255};
        int[] compare = getCompareArray(fullByte);
        assertTrue(bitArrayTester(compare, full));
    }

    @Test
    public void byteVal128() {
        int[] finalBit = {1, 0, 0, 0, 0, 0, 0, 0};
        byte[] finale = {(byte) 128};
        int[] compare = getCompareArray(finale);
        assertTrue(bitArrayTester(compare, finalBit));
    }

    @Test
    public void twoFullBytes() {
        int[] full = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        byte[] twoFull = {(byte) 255, (byte) 255};
        int[] compare = getCompareArray(twoFull);
        assertTrue(bitArrayTester(compare, full));
    }

    @Test
    public void twoEmptyBytes() {
        int[] empty = new int[16];
        byte[] twoZero = {0, 0};
        int[] compare = getCompareArray(twoZero);
        assertTrue(bitArrayTester(compare, empty));
    }

    @Test
    public void setBitZerosIsZero() {
        byte[] zeros = new byte[2];
        byte[] array = new byte[2];
        for (int i = 0; i < 16; i++) {
            BitTools.setBit(array, i, 0);

        }
        assertTrue(byteArrayTester(array, zeros));
    }

    @Test
    public void setBitTest() {
        byte[] compare = {-1, -1};
        byte[] array = new byte[2];
        for (int i = 0; i < 16; i++) {
            BitTools.setBit(array, i, 1);
        }
        assertTrue(byteArrayTester(array, compare));
    }

    @Test
    public void halfBytes() {
        byte[] halves = {-1, -1, -16, 0, 0};
        byte[] array = new byte[5];
        for (int i = 0; i < 20; i++) {
            BitTools.setBit(array, i, 1);
        }
        assertTrue(byteArrayTester(array, halves));
    }

    @Test
    public void fullBytes() {
        byte[] full = {-1, -1, -1, -1, -1};
        byte[] array = new byte[5];
        for (int i = 0; i < 40; i++) {
            BitTools.setBit(array, i, 1);
        }
        assertTrue(byteArrayTester(array, full));
    }

    @Test
    public void emptyBytes() {
        byte[] empty = {0, 0, 0, 0, 0};
        byte[] array = new byte[5];
        for (int i = 0; i < 40; i++) {
            BitTools.setBit(array, i, 0);
        }
        assertTrue(byteArrayTester(array, empty));
    }

    @Test
    public void pairFull() {
        byte[] everyPair = {-86, -86, -86, -86, -86};
        byte[] array = new byte[5];
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                BitTools.setBit(array, i, 1);
            } else {
                BitTools.setBit(array, i, 0);
            }
        }
        assertTrue(byteArrayTester(array, everyPair));
    }

    @Test
    public void otherFull() {
        byte[] everyOther = {85, 85, 85, 85, 85};
        byte[] array = new byte[5];
        for (int i = 0; i < 40; i++) {
            if (i % 2 == 0) {
                BitTools.setBit(array, i, 0);
            } else {
                BitTools.setBit(array, i, 1);
            }
        }
        assertTrue(byteArrayTester(array, everyOther));
    }

    @Test
    public void concatenate1() {
        byte[] firstHalf = {1, 2, 4, 55, 22, 11, 22, 66, 100, 32};
        byte[] secondHalf = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        byte[] compare = {1, 2, 4, 55, 22, 11, 22, 66, 100, 32, 4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        byte[] concat = BitTools.concatenate(firstHalf, secondHalf, 80);
        assertTrue(byteArrayTester(compare, concat));
    }

    @Test
    public void concatenate2() {
        byte[] compare = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3, 1, 2, 4, 55, 22, 11, 22, 66, 100, 32};
        byte[] first = {4, 5, 6, 7, 8, 9, 0, 1, 2, 3};
        byte[] second = {1, 2, 4, 55, 22, 11, 22, 66, 100, 32};
        byte[] concat = BitTools.concatenate(first, second, 80);
        assertTrue(byteArrayTester(concat, compare));
    }

    @Test
    public void getSetOfBitsAndConcat() {
        byte[] real = {32, 4, 15, 40, 3, 12, 45, 12};
        byte[] firstHalf = BitTools.getSetOfBits(real, 0, 32);
        byte[] secondHalf = BitTools.getSetOfBits(real, 32, 32);
        byte[] concat = BitTools.concatenate(firstHalf, secondHalf, 32);
        assertTrue(byteArrayTester(real, concat));
    }
}
