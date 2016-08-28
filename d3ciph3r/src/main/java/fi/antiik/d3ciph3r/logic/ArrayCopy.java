/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

/**
 *
 * @author janantik
 */
public class ArrayCopy {
    
    /**
     * Copies the byte array to the another array.
     * @param from array where data is copied.
     * @param fromPos Startingposition of the copy.
     * @param to arraywhere data is copied
     * @param toPos starting position of the copy.
     * @param len  lenght of the copy
     */
    public static void byteCopy(byte[] from, int fromPos, byte[] to, int toPos, int len) {
        for (int i = 0; i < len; i++) {
            to[toPos + i] = from[fromPos + i];
        }
    }
}
