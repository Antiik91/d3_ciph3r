/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

import java.security.InvalidParameterException;
import javax.crypto.KeyGenerator;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test(expected = AssertionError.class)
    public void keyCantBeOver56Bytes() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(563);
            des.setKeyReady(kg.generateKey());
            
        } catch (Exception e) {
            assertEquals(InvalidParameterException.class, e);
        }
    }
    
    @Test(expected = AssertionError.class)
    public void keyCantBeunder56Bytes() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            kg.init(2);
            des.setKeyReady(kg.generateKey());
        } catch (Exception e) {
            assertEquals(InvalidParameterException.class, e);
        }
    }
}
