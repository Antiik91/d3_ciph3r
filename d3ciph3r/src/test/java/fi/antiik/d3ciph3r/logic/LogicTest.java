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
    private Scanner falseScanner;
    private Scanner trueScanner;
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
        this.falseScanner = new Scanner("Kissa meni yli laidan");
        this.trueScanner = new Scanner("3");
        this.logic = new Logic();
    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void handleCommandReturnstrue(){
        assertEquals(3, this.trueScanner);
    }
}
