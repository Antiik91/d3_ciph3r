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
 * @author janantik
 */
public class ArrayCopyTest {

    public ArrayCopyTest() {
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

    @Test
    public void copyWholeArray() {
        byte[] to = {1, 2, 3, 4, 5, 6};
        byte[] from = {0, 0, 0, 0, 0, 0};
        ArrayCopy.byteCopy(from, 0, to, 0, 6);
        boolean same = true;
        for (int i = 0; i < to.length; i++) {
            if (to[i] != from[i]) {
                same = false;
            }
        }
        assertTrue(same);
    }

    @Test
    public void copyLastHalfArray() {
        byte[] to = {0, 0, 0, 2, 2, 2};
        byte[] from = {1, 2, 3, 4, 5, 6};
        byte[] compare = {4, 5, 6, 2, 2, 2};
        ArrayCopy.byteCopy(from, from.length / 2, to, 0, 3);
        boolean same = true;
        for (int i = 0; i < compare.length; i++) {
            if (to[i] != compare[i]) {
                same = false;
            }
        }
        assertTrue(same);
    }

    @Test
    public void copyFirstHalfArray() {
        byte[] to = {0, 0, 0, 1, 1, 1};
        byte[] from = {1, 2, 3, 4, 5, 6};
        byte[] cmp = {1, 2, 3, 1, 1, 1};
        ArrayCopy.byteCopy(from, 0, to, 0, 3);
        boolean same = true;
        for (int i = 0; i < cmp.length; i++) {
            if(to[i] != cmp[i]) {
                same = false;
            }
        }
        assertTrue(same);
    }
}
