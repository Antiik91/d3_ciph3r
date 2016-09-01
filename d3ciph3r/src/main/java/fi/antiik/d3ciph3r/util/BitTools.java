/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.util;

/**
 *
 * @author User
 */
public class BitTools {

    /**
     * Method to help to set wanted bit to the data.
     *
     * @param data data where the bit to be placed.
     * @param position position where the bit needs to be placed.
     * @param value bit as int.
     */
    public static void setBit(byte[] data, int position, int value) {
        int bytePosition = position / 8;
        int bitPosition = position % 8;
        byte bit = data[bytePosition];
        /* Set the bit (value)  to the wanted position. 
         Param bit is temperary value to get correct information out from
         the table.
         */
        bit = (byte) (((0xFF7F >> bitPosition) & bit) & 0x00FF);
        byte newByte = (byte) ((value << (8 - (bitPosition + 1))) | bit);
        data[bytePosition] = newByte;
    }

    /**
     * Method to help to get the wanted bit out of data.
     *
     * @param data data where the bit gets extracted.
     * @param position position where the bit is located.
     * @return wanted bit as int.
     */
    public static int getBit(byte[] data, int position) {

        int bytePosition = position / 8;
        int bitPosition = position % 8;
        byte byt = data[bytePosition];
        // Get the bit from the bytes position. and extract it to the integer value.
        int value = byt >> (8 - (bitPosition + 1)) & 0x0001;

        return value;
    }

    /**
     * Method to help to get multible bits from data.
     *
     * @param data data where the bits are taken.
     * @param position
     * @param n
     * @return data where the bits are in right order.
     */
    public static byte[] getSetOfBits(byte[] data, int position, int n) {
        int bytes = (n - 1) / 8 + 1;
        byte[] output = new byte[bytes];
        for (int i = 0; i < n; i++) {
            // In order to get multiple bits from the data loop the wanted lenght
            // and set the output as wanted.
            int bit = getBit(data, position + i);
            setBit(output, i, bit);
        }
        return output;

    }
    
        /**
     * Concatenates the halved data into one.
     *
     * @param dataA left half of the data
     * @param dataB right half of the data
     * @param len Lenght of the datasets.
     * @return concatenated data
     */
    public static byte[] concatenate(byte[] dataA, byte[] dataB, int len) {
        byte[] concatenated = new byte[(len + len - 1) / 8 + 1];
        // use helper methods to get the bits from  A and B and set those bits
        // into the concatenaded array.
        for (int i = 0; i < len; i++) {
            int aBit = getBit(dataA, i);
            int bBit = getBit(dataB, i);
            setBit(concatenated, i, aBit);
            setBit(concatenated, len + i, bBit);
        }

        return concatenated;
    }
}
