/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

/**
 * Also known as shift cipher. It's a type of substituion cipher where each
 * letter in plaintext is replaced fixed number of positions down the alphabet.
 *
 * @author janantik
 */
public class CaesarCipher {

    private String encryptedText;
    private int shift;

    /**
     * shift is used to determinate the number of positions to be shifted.
     *
     * @param shift fixed number to be shifted in alphabets.
     */
    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    /**
     * if no shift is given, use the original 3 shift.
     */
    public CaesarCipher() {
        this.shift = 3;
    }

    /**
     * Encrypt the data and return ciphered text.
     *
     * @param plaintext
     * @return ciphered text.
     */
    public String encrypt(String plaintext) {
        encryptedText = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char c = (char) (plaintext.charAt(i) + this.shift);
            if (c > 'z') {
                encryptedText += (char) (plaintext.charAt(i) - (26 - this.shift));
            } else {
                encryptedText += (char) (plaintext.charAt(i) + this.shift);
            }
        }
        return encryptedText;
    }

    /**
     * decrypts the data from param.
     *
     * @param txt Encrypted text to be decrypted.
     * @return plaintext.
     */
    public String decrypt(String txt) {
        String decryptedTxt = "";
        for (int i = 0; i < txt.length(); i++) {
            char c = (char) (txt.charAt((i)) - this.shift);
            if (c < 'a') {
                decryptedTxt += (char) (txt.charAt(i) + (26 - this.shift));
            } else {
                decryptedTxt += (char) (txt.charAt(i) - this.shift);
            }
        }
        return decryptedTxt;
    }

}
