package fi.antiik.d3ciph3r.logic;

import java.util.Random;

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
     * Permute choice 2. Used to pemute halved and shifted subkey from key+ CnDn pairs
     * (56 bits) to 48 bit subkey. every 8. bit is discarded.
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
     * bits according to the table. Is used first when crypting data.
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
     * Final permutation also nown as FI ^-1 After everything else is done the
     * message is one final time permuted trhough this table.
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
     * used in the Feistel method. this expands the message's right half from 32
     * bits to 48 bits.
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
     * Permutes 32 bit input. Used in feistel method  after substitution.
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
     * Each row takes a 6 bit block as an input and makes it 4-bit output. First
     * and last bit of the data is used to seek up the row and middle four bits
     * are calcultated to get the column. For example if input is = 011011,
     * first bit is 0 and last is 1, that gives us the row 1. middle four bits
     * are equal to 13 so column number is 13. There we found number 5. Thats
     * the output.
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
     * Method is used to create set of subkeys from the original 56-bit key.
     * First it permutes the key using PC-1 array. after that the Permuted key
     * splits in half as c and d each of them 28-bits. After that the c and d
     * subkeys' bits gets shifted 16 times according to shift table. Each of the
     * shifts makes a new subkey c0 d0 ... c16 d16. Each of them is then
     * concatenated cndn (56 bits) and permutted using PC-2.
     *
     * @param key original 64-bit key.
     * @return array of subkeys to be used in feistel function.
     */
    private byte[][] createSubKeys(byte[] key) {
        // First we permute the original, 56 bit to 48 bit "key+", discarding every 8th bit. 
        byte[] permutatedKey = permute(PC1, key);
        byte[][] subKeySet = new byte[16][];
        // use helper method to divide the key+ into two halves, c and d
        byte[] c = getSetOfBits(permutatedKey, 0, PC1.length / 2);
        byte[] d = getSetOfBits(permutatedKey, PC1.length / 2, PC1.length / 2);
        // Then rotate those halves 1 or 2 bits according to the shifts table.
        // depending on the round
        for (int j = 0; j < 16; j++) {
            c = rotateShift(c, shitfs[j]);
            d = rotateShift(d, shitfs[j]);

            // use concatenate to make the subkey from two halves
            byte[] subKey = concatenate(c, d, 28);

            // Then permute this newly formed subkey according to PC2 table, and add it to the subkeys list.
            subKey = permute(PC2, subKey);
            subKeySet[j] = subKey;
        }
        return subKeySet;
    }

    /**
     * Concatenates the halved data into one.
     *
     * @param dataA left half of the data
     * @param dataB right half of the data
     * @param len Lenght of the datasets.
     * @return concatenated data
     */
    public byte[] concatenate(byte[] dataA, byte[] dataB, int len) {
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

    /**
     * left shifts from creating subkeys is rotated trough this method.
     *
     * @param data Cn or Dn.
     * @param shift number of left shifts to be made in new
     * @return new half of a subkey.
     */
    private byte[] rotateShift(byte[] data, int shift) {
        byte[] shifted = new byte[(28 - 1) / 8 + 1];
        for (int i = 0; i < 28; i++) {
            // get the bit from data (c or d half) and set it to the shifted array.
            // Move the bit 1 or two times left according to the shift.
            int bit = getBit(data, (i + shift) % 28);
            setBit(shifted, i, bit);
        }
        return shifted;
    }

    /**
     * Permutation method. permutes the data with given table. Get the bit from
     * data and set it to the permuted data.
     *
     * @param table table used to permute data.
     * @param data data to be permuted.
     * @return permuted data.
     */
    private byte[] permute(int[] table, byte[] data) {
        /* Use the table to set the according bits from the data. 
         For example if 1st number from the table is 47
         Get the 47th bit from data and put it to the first in new permuted
         array.
         Same for the second number of the table .. all the way.
         */
        byte[] permuted = new byte[(table.length - 1) / 8 + 1];
        for (int i = 0; i < table.length; i++) {
            int bit = getBit(data, table[i] - 1);
            setBit(permuted, i, bit);
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
    private int getBit(byte[] data, int position) {

        int bytePosition = position / 8;
        int bitPosition = position % 8;
        byte byt = data[bytePosition];
        // Get the bit from the bytes position. and extract it to the integer value.
        int value = byt >> (8 - (bitPosition + 1)) & 0x0001;
        return value;
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
        /* Set the bit (value)  to the wanted position. 
         Param bit is temperary value to get correct information out from
         the table.
         */
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
     * @return permuted data.
     */
    private byte[] feistel(byte[] rightSide, byte[] subKey) {
        byte[] permuted;
        // First we expand the message, with E-bit selection table, which uses
        // multiple same integers we use to expand the message.
        permuted = permute(EbitTable, rightSide);
        // After that we use xor method to generate random  data which is combined
        //from permuted right side and subkeys bits.
        permuted = xor(permuted, subKey);
        // Use the substitution table to substitute and further scramble the data.

//        permuted = substitution(permuted);
        // last we permute trhough P table the data and return it
        permuted = permute(P, permuted);
        return permuted;
    }

    // DOESNT WORK CORRECTLY ATM 
    private byte[] substitution(byte[] data) {
        // seperate  block of data and divide it to eight bit groups. number of bits.
        int length = (8 * data.length - 1) / 6 + 1;
        byte[] dataSeperated = new byte[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 6; j++) {
                int bit = getBit(data, 6 * i + j);
                setBit(dataSeperated, 8 * i + j, bit);
            }
        }
        byte[] substituted = new byte[dataSeperated.length / 2];
        int a = 0;
        for (int i = 0; i < dataSeperated.length; i++) {
            byte val = dataSeperated[i];
            int positionR = 2 * (val >> 7 & 0x0001) + (val >> 2 & 0x001);
            int positionC = (val >> 3 & 0x000F);
            int position = positionR + positionC;
            int sub = sBox[i][position];
            if (i % 2 == 0) {
                a = sub;
            } else {
                substituted[i / 2] = (byte) (16 * a + sub);
            }
        }

        return substituted;
    }

    /**
     * Method to help to get multible bits from data.
     *
     * @param data data where the bits are taken.
     * @param position
     * @param n
     * @return data where the bits are in right order.
     */
    private byte[] getSetOfBits(byte[] data, int position, int n) {
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
     * Crypt the 64 bit bloc of data.
     *
     * @param data bloc of data
     * @param encryption if true, we are encrypting, else we are decrypting.
     * @return crypted bloc
     */
    private byte[] cryptBloc(byte[] data, boolean encryption) {
        // We need the subkeys in order to get crypitng to work
        if (this.subKeys == null) {
            this.subKeys = createSubKeys(this.key);
        }
        // First we permute the data with IP table.
        byte[] crypted = permute(IP, data);

        //split the message in half ( 64bits to 32 bits)
        byte[] left = getSetOfBits(crypted, 0, 32);
        byte[] right = getSetOfBits(crypted, 32, 32);

        // Do the 16 rounds needed to encrypt / decrypt;
        for (int i = 0; i < 16; i++) {

            byte[] lastRight = right;
            if (encryption) {
                right = encryptionRound(right, left, i);
            } else {
                right = decryptionRound(right, left, i);
            }
            //Set the left side of the message previous right
            left = lastRight;
        }

        //Concatenate the message and permute it with final permutation
        crypted = concatenate(right, left, 32);
        crypted = permute(FP, crypted);
        return crypted;
    }

    private byte[] xor(byte[] a, byte[] b) {
        byte[] xorred = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            xorred[i] = (byte) (a[i] ^ b[i]);
        }
        return xorred;
    }

    /**
     * If we need to encrypt text, we use this round to encrypt the right side.
     *
     * @param right message data's right side.
     * @param left message data's left sied.
     * @param round number of rounds is used.
     * @return new scrambled right side of the message.
     */
    private byte[] encryptionRound(byte[] right, byte[] left, int round) {
        // Use the feistel method to scramble right side of the message.
        right = feistel(right, this.subKeys[round]);
        // After that take the bits from left and right and scramble the message
        // with xor
        right = xor(left, right);
        return right;
    }

    /**
     * in case of decrypting text, we use this round to decrypt scrambled text.
     *
     * @param right message data's right side.
     * @param left messge data's left side
     * @param round number of round.
     * @return decrypted set.
     */
    private byte[] decryptionRound(byte[] right, byte[] left, int round) {
        //In decryption we use the subkeys other way around.
        right = feistel(right, this.subKeys[15 - round]);
        right = xor(left, right);
        return right;
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
     * Public method to decrypt with ciphertext and key as a String.
     *
     * @param cipherText
     * @param key
     * @return
     * @throws Exception
     */
    public byte[] decyrpt(String cipherText, String key) throws Exception {
        if (cipherText.isEmpty()) {
            return null;
        }
        if (key.isEmpty()) {
            generateKey();
            this.key = this.keyString.getBytes();
        } else {
            this.key = key.getBytes();
        }
        byte[] cipher = cipherText.getBytes();

        byte[] plaintext = decryptData(cipher);
        return plaintext;
    }

    public String getKeyString() {
        return keyString;
    }

    /**
     * Decrypts the data from byte array and returns plaintext.
     *
     * @param data encrypted data array.
     * @return plaintext string.
     * @throws Exception
     */
    public byte[] decryptData(byte[] data) throws Exception {
        int i;
        byte[] decrypted = new byte[data.length];
        byte[] bloc = new byte[8];
        for (i = 0; i < data.length; i++) {
            if (i > 0 && i % 8 == 0) {
                // When we have 64 bit bloc we decrypt it.
                bloc = cryptBloc(bloc, false);
                // Use the ArrayCopy method to copy the bloc to the decrytpted message.
                ArrayCopy.byteCopy(bloc, 0, decrypted, i - 8, bloc.length);
            }
            if (i < data.length) {
                //Add the information to the bloc
                bloc[i % 8] = data[i];
            }
        }
        //decrypt the last bloc
        bloc = cryptBloc(bloc, false);
        ArrayCopy.byteCopy(bloc, 0, decrypted, i - 8, bloc.length);

        int counter = 0;

        //removes the padding 
        int index = decrypted.length - 1;
        while (decrypted[index] == 0) {
            counter++;
            index--;
        }
        byte[] nopadding = new byte[decrypted.length - counter - 1];
        ArrayCopy.byteCopy(decrypted, 0, nopadding, 0, nopadding.length);
        return nopadding;

    }

    /**
     * Encrypts the plaintext and return
     *
     * @param key byte array of crypted data.
     * @param plaintext String to be crypted.
     * @return byte array of crypted data.
     * @throws Exception
     */
    public byte[] encrypt(String plaintext, String key) throws Exception {
        if (plaintext.isEmpty()) {
            return null;
        }
        if (key.isEmpty()) {
            generateKey();
            this.key = this.keyString.getBytes();
        } else {
            this.key = key.getBytes();
        }
        byte[] plainBytes = plaintext.getBytes();
        byte[] crypted = cryptData(plainBytes);

        return crypted;

    }

    public byte[][] getSubKeys() {
        return subKeys;
    }

    public void setSubKeys(byte[][] subKeys) {
        this.subKeys = subKeys;
    }

    /**
     * Crypts the data got from encrypt method.
     *
     * @param data data to be encryted.
     * @return encrypted data aka ciphertext.
     */
    private byte[] cryptData(byte[] data) {
        // Seperate the data for 64 bits of data blocs.
        int len = 8 - data.length % 8;

        // initialize padding. first byte is (decimal) -128 for easy regognizion.
        byte[] padding = new byte[len];
        padding[0] = (byte) 0x80;
        byte[] crytpedData = new byte[data.length + len];
        byte[] bloc = new byte[8];

        int counter = 0;
        int i;
        for (i = 0; i < data.length + len; i++) {
            if (i > 0 && i % 8 == 0) {
                // When we have bloc size of 64 bit, its time to encrypt the block. Copy it to
                // the crypted data array.
                bloc = cryptBloc(bloc, true);
                ArrayCopy.byteCopy(bloc, 0, crytpedData, i - 8, bloc.length);
            }
            if (i < data.length) {
                // add the information.
                bloc[i % 8] = data[i];
            } else {
                // Add the padding. 
                bloc[i % 8] = padding[counter % 8];
                counter++;
            }
        }

        // Crypt the last bloc
        if (bloc.length == 8) {
            bloc = cryptBloc(bloc, true);
            ArrayCopy.byteCopy(bloc, 0, crytpedData, i - 8, bloc.length);
        }

        return crytpedData;
    }

    public String getKey() {
        return this.keyString;
    }
}
