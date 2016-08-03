/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

/**
 *
 * @author User
 */
public class CaesarCipher implements Cipherable {

    private String decryptedText;
    private int shift;

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    public CaesarCipher() {
        this.shift = 3;
    }

    @Override
    public String encrypt(String plaintext) {
        decryptedText = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char c = (char) (plaintext.charAt(i) + this.shift);
            if (c > 'z') {
                decryptedText += (char) (plaintext.charAt(i) - (26 - this.shift));
            } else {
                decryptedText += (char) (plaintext.charAt(i) + this.shift);
            }
        }
        return decryptedText;
    }

    @Override
    public String decrypt(String txt) {
        String encrypted = "";
        for (int i = 0; i < txt.length(); i++) {
            char c = (char) (txt.charAt((i)) - this.shift);
            if (c < 'a') {
                encrypted += (char) (txt.charAt(i) + (26 - this.shift));
            } else {
                encrypted += (char) (txt.charAt(i) - this.shift);
            }
        }
        return encrypted;
    }

}
