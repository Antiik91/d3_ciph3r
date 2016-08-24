package fi.antiik.d3ciph3r.logic;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;

/**
 * Data Encryption Standard (DES) is a symmetric-keyReady algorithm for the
 * encryption of electron data. Developed originally in the early 1970 at IBM.
 * DES is a block cipher, takes a plaintext bits and transform it to another
 * ciphertext of the same length. block size is 64 bits.
 *
 * @author janantik
 */
public class DES {

    private String keyString = "";
    private byte[] key;
    private byte[][] subKeys;

    /**
     * Permutation Choice 1. Used to permute the 64-bit key according to the
     * following table. Note that every 8 bits are discarded therefore only 56
     * bits of the original key appear in permuted key.
     */
    private int[] PC1 = {
        57, 49, 41, 33, 25, 17, 9,
        1, 58, 50, 42, 34, 26, 18,
        10, 2, 59, 51, 43, 35, 27,
        19, 11, 3, 60, 52, 44, 36,
        63, 55, 47, 39, 31, 23, 15,
        7, 62, 54, 46, 38, 30, 22,
        14, 6, 61, 53, 45, 37, 29,
        21, 13, 5, 28, 20, 12, 4
    };

    /**
     * Permute choice 2. Used to pemute CnDn pairs (56 bits) to 48 bit subkey.
     * every 8. bit is discarded.
     */
    private int[] PC2 = {
        14, 17, 11, 24, 1, 5,
        3, 28, 15, 6, 21, 10,
        23, 19, 12, 4, 26, 8,
        16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55,
        30, 40, 51, 45, 33, 48,
        44, 49, 39, 56, 34, 53,
        46, 42, 50, 36, 29, 32
    };

    /**
     * Number of left shifts to be shifted in Cn and Dn (See createSubKeys())
     */
    private int[] shitfs = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    /**
     * Initial permutation. Permutes 64 bits of message data. Rearranges the
     * bits according to the table.
     */
    private int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    /**
     * Final permutation also nown as FI ^-1
     */
    private int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    /**
     *
     * used in the Feistel function. this expands 32 bits to 48 bits.
     */
    private int[] EbitTable = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };

    /*
     * Permutes 32 bit input.
     */
    private int[] P = {
        16, 7, 20, 21,
        29, 12, 28, 17,
        1, 15, 23, 26,
        5, 18, 31, 10,
        2, 8, 24, 14,
        32, 27, 3, 9,
        19, 13, 30, 6,
        22, 11, 4, 25
    };

    /**
     * Each row takes a 6 bit block as an input and makes it 4-bit output.
     */
    private byte[][] sBox = {{
        14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7,
        0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8,
        4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0,
        15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13
    }, {
        15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10,
        3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5,
        0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15,
        13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9
    }, {
        10, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8,
        13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1,
        13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7,
        1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12
    }, {
        7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15,
        13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9,
        10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4,
        3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14
    }, {
        2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9,
        14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6,
        4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14,
        11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3
    }, {
        12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11,
        10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8,
        9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6,
        4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13
    }, {
        4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1,
        13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6,
        1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2,
        6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12
    }, {
        13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7,
        1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2,
        7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8,
        2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11
    }
    };

    public DES() throws Exception {

    }

    /**
     * Meethod is used to create set of subkeys from the original 56-bit key.
     * First it permutest the key using PC-1. after that the Permuted key splits
     * in half as c and d each of them 28-bits. After that the c and d subkeys'
     * bits gets shifted 16 times according to shift table. Each of the shifts
     * makes a new subkey c0 d0 ... c16 d16. Each of them is then concatenated
     * cndn (56 bits) and permutted using PC-2.
     *
     * @param key original 64-bit key.
     * @return array of subkeys to be used in feistel function.
     */
    private byte[][] createSubKeys(byte[] key) {
        byte[] permutatedKey = permute(PC1, key);
        byte[][] subKeys = new byte[16][];
        byte[] c = new byte[28];
        byte[] d = new byte[28];
        for (int i = 0; i < 28; i++) {
            int valueC = getBit(permutatedKey, i);
            int valueD = getBit(permutatedKey, 28 + i);
            setBit(c, i, valueC);
            setBit(d, 28 + i, valueD);
        }
        for (int i = 0; i < 16; i++) {
            c = rotateShift(c, shitfs[i]);
            d = rotateShift(d, shitfs[i]);

            byte[] subKey = concatenate(c, d);
            subKey = permute(PC2, subKey);
            subKeys[i] = subKey;
        }
        return subKeys;
    }

    /**
     * Concatenates the halved Cn and Dn into one Subkey.
     *
     * @param c left half of the key
     * @param d right half of the key
     * @return concatenated subkey
     */
    public byte[] concatenate(byte[] c, byte[] d) {
        byte[] subKey = new byte[c.length + d.length];
        for (int i = 0; i < subKey.length; i++) {
            int cBit = getBit(c, i);
            int dBit = getBit(d, i);
            setBit(subKey, i, cBit);
            setBit(subKey, d.length + i, dBit);
        }
        return subKey;
    }

    /**
     * left shifts is rotated trough this method.
     *
     * @param data Cn or Dn.
     * @param shift number of left shifts to be made in new
     * @return new half of a subkey.
     */
    private byte[] rotateShift(byte[] data, int shift) {
        byte[] shifted = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            int bit = getBit(data, (i + shift) % data.length);
            setBit(shifted, i, bit);
        }
        return shifted;
    }

    /**
     * Permutation method. permutes the data with given table.
     *
     * @param table table used to permute data.
     * @param data data to be permuted.
     * @return permuted data.
     */
    private byte[] permute(int[] table, byte[] data) {
        byte[] permuted = new byte[(table.length - 1) / 8 +1];
        for (int i = 0; i < table.length; i++) {
            int value = getBit(data, table[i] - 1);
            setBit(permuted, i, value);
        }
        return permuted;
    }

    /**
     * Method to help to get the wanted bit out of data.
     *
     * @param data data where the bit gets extracted.
     * @param position position where the bit is located.
     * @return wanted bit as int.
     */
    private int getBit(byte[] data, int position ) {
  
        int bytePosition = position / 8;
        int bitPosition = position % 8;
        byte bit = data[bytePosition];
        return bit >> (8 - (bitPosition + 1)) & 0x0001;
    }

    /**
     * Method to help to set wanted bit to the data.
     *
     * @param data data where the bit to be placed.
     * @param position position where the bit needs to be placed.
     * @param value bit as int.
     */
    private void setBit(byte[] data, int position, int value) {
        int bytePosition = position / 8;
        int bitPosition = position % 8;
        byte bit = data[bytePosition];
        bit = (byte) (((0xFF7F >> bitPosition) & bit) & 0x00F);
        byte newByte = (byte) ((value << (8 - (bitPosition + 1))) | bit);
        data[bytePosition] = newByte;
    }

    /**
     * The bread and butter of the DES. First expands the right side of the
     * 64-bit long message. 32 bits to 48 bits by using Ebit selection table.
     * After that method XORs the permuted result and subkey. Now we have 48
     * bits / eight group of 6-bits. These give as an addres of a different S
     * box. From the addres is found 3 bit number and it will replace original 6
     * bits.
     *
     * @param rightSide Right side of the message.
     * @param subKey subkey.
     * @return
     */
    private byte[] feistel(byte[] rightSide, byte[] subKey) {
        byte[] permuted;
        permuted = permute(EbitTable, rightSide);
        for (int i = 0; i < permuted.length; i++) {
            permuted[i] = (byte) (permuted[i] ^ subKey[i]);
        }
        //permuted = substitution(permuted);
        permuted = permute(P, permuted);
        return permuted;
    }

    
    // DOESNT WORK CORRECTLY ATM 
    private byte[] substitution(byte[] data) {
        // seperate  block of data and divide it to eight bit groups. number of bits.
        int length = 8 * data.length;
        byte[] substituted = new byte[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 6; j++) {
                int bit = getBit(data, i);
                setBit(substituted, 8 * i + j, bit);
            }
        }
        int a = 0;
        for (int i = 0; i < data.length; i++) {
            byte val = data[i];
            int position = 2 * (val >> 7 & 0x0001) + (val >> 2 & 0x001) + (val >> 3 & 0x000F);
            int sub = sBox[val][position];
            if (i % 2 == 0) {
                a = sub;
            } else {
                substituted[i / 2] = (byte) (16 * a + sub);
            }
        }

        return substituted;
    }

    private byte[] blocCryption(byte[] data, boolean encryption) {
        if (this.subKeys == null) {
            this.subKeys = createSubKeys(this.key);
        }

        byte[] crypted = permute(IP, data);
        byte[] right = new byte[data.length / 2];
        byte[] left = new byte[data.length / 2];
        for (int i = 0; i < data.length; i++) {
            int valueRight = getBit(data, i);
            int valueLeft = getBit(data, 28 + i);
            setBit(right, i, valueRight);
            setBit(left,  i, valueLeft);
        }
        // Do the 16 rounds needed to encrypt Decrypt;
        for (int i = 0; i < 16; i++) {
            round(right, left, i, encryption);
        }
        crypted = concatenate(right, left);
        return crypted;
    }

    private void round(byte[] right, byte[] left, int round, boolean encryption) {
        byte[] lastRight = right;
        if (encryption) {
            right = feistel(right, this.subKeys[round]);
        } else {
            right = feistel(right, this.subKeys[15 - round]);
        }
        for (int i = 0; i < right.length; i++) {
            right[i] = (byte) (right[i] ^ left[i]);
        }
        left = lastRight;
    }

    /**
     * Generates the new 64 bit random key.
     */
    public void generateKey() {
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            this.keyString += (char) random.nextInt(127);
        }
    }

    /**
     * Decrypts the data from byte array and returns plaintext.
     *
     * @param bits encrypted data array.
     * @return plaintext string.
     * @throws Exception
     */
    public String decrypt(byte[] bits) throws Exception {
        return null;

    }

    /**
     * Encrypts the plaintext and returns byte array of crypted data.
     *
     *
     * @param plaintext String to be crypted.
     * @return byte array of crypted data.
     * @throws Exception
     */
    public String encrypt(String plaintext, String key, boolean encrypt) throws Exception {
        this.key = key.getBytes();
        byte[] plaintxt = plaintext.getBytes();
        byte[] crypted = cryptData(plaintxt, encrypt);
        
        return new String(crypted, StandardCharsets.UTF_8);

    }

    public byte[] cryptData(byte[] data, boolean encrypt) {
        //Seperate the data for 64 bits of data.
        int blocks = data.length / 64;
        byte[] cryptedData = new byte[data.length];
        byte[] block = new byte[64];
//        for (int i = 0; i < blocks; i++) {
//            for (int j = 0; j < 64; j++) {
//                int valBit = getBit(data, i * j);
//                setBit(block, j, valBit);
//            }
            cryptedData = blocCryption(data, encrypt);
//        }
        return cryptedData;
    }

    public String getKey() {
        return this.keyString;
    }
}
