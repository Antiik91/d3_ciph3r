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
        if (this.shift < 0) {
            this.shift *= -1;
        }
        if (this.shift == 0) {
            this.shift = 3;
        }

    }

    public int getShift() {
        return shift;
    }

    /**
     * shift cannot be negative, and if it's zero there is no point of
     * (en/de)crypting.
     *
     * @param shift new shift.
     */
    public void setShift(int shift) {
        if (shift > 0) {
            this.shift = shift;
        }
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
            int c = (char) (plaintext.charAt(i));
            if (Character.isUpperCase(c)) {
                c += this.shift % 26;
                if (c > 'Z') {
                    c -= 26;
                }
            } else if (Character.isLowerCase(c)) {
                c += this.shift % 26;
                if (c > 'z') {
                    c -= 26;
                }
            }
            encryptedText += (char) c;
        }
        return encryptedText;
    }

    /**
     * decrypts the data from param.
     *
     * @param encryptedText Encrypted text to be decrypted.
     * @return plaintext.
     */
    public String decrypt(String encryptedText) {
        String decryptedTxt = "";
        for (int i = 0; i < encryptedText.length(); i++) {
            int c = (char) (encryptedText.charAt(i));
            if (Character.isUpperCase(c)) {
                c -= this.shift % 26;
                if (c < 'A') {
                    c -= 26;
                }
            } else if (Character.isLowerCase(c)) {
                c -= this.shift % 26;
                if (c < 'a') {
                    c += 26;
                }
            }
            decryptedTxt += (char) c;
        }
        return decryptedTxt;
    }

}
