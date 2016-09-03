/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author janantik
 */
public class LogicTest {

    private Logic logic;

    public LogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            this.logic = new Logic();
        } catch (Exception e) {

        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void handleCommandReturnstrue() {
        assertEquals(3, this.logic.handleCommand("3"));
    }

    @Test
    public void positiveIntegerHandleCommand() {
        assertEquals(30, this.logic.handleCommand("+30"));
    }

    @Test
    public void negativeIntegerHandleCommand() {
        assertEquals(-43, this.logic.handleCommand("-43"));
    }

    @Test
    public void incorrectCommandReturnsMinus1() {
        assertEquals(-1, this.logic.handleCommand("Kissa meni yli laidan"));
    }

    @Test
    public void keyShorterThan8Chars() {
        String shortKey = "Hello";
        assertFalse(this.logic.validKey(shortKey));
    }

    @Test
    public void keyLongerThan8Chars() {
        String longKey = "HelloWorldWhatsUP?";
        assertFalse(this.logic.validKey(longKey));
    }

    @Test
    public void correctKey() {
        assertTrue(this.logic.validKey("12345678"));
    }

    @Test
    public void invalidByteStringDoubleSpacesReturnNull() {
        String invalid = "21 23  1";
        byte[] test = this.logic.getByteArrayFromString(invalid);
        assertTrue(test == null);
    }

    @Test
    public void invalideByteStringReturnNull() {
        String invalid = "1,2,4,5,6,7";
        byte[] test = this.logic.getByteArrayFromString(invalid);
        assertTrue(test == null);
    }

    @Test
    public void invalidByteStringEmptyReturnNull() {
        String invalid = "";
        byte[] test = this.logic.getByteArrayFromString(invalid);
        assertTrue(test == null);
    }

    @Test
    public void validByteStrinNotEmpty() {
        String valid = "2 4 6 22 334 223 -12 2";
        byte[] test = this.logic.getByteArrayFromString(valid);
        assertTrue(test.length != 0);
    }

    @Test
    public void validByteStringNotNull() {
        String valid = "2 4 6 22 334 223 -12 2";
        byte[] test = this.logic.getByteArrayFromString(valid);
        assertFalse(test == null);
    }

    @Test
    public void validByteStringParsedCorrectly() {
        String valid = "0 1 2 3 4 5 6 7 8 9 -1 -2 -3 -4 -5 -6 -7 -8 -9";
        byte[] correct = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, -1, -2, -3, -4, -5, -6, -7, -8, -9};
        byte[] compare = this.logic.getByteArrayFromString(valid);
        boolean isSame = true;
        for (int i = 0; i < correct.length; i++) {
            if(correct[i] != compare[i]) {
                isSame = false;
                break;
            }
        }
        assertTrue(isSame);
    }
}
