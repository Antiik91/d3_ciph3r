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
    public void correctKey(){
        assertTrue(this.logic.validKey("12345678"));
    }
}
