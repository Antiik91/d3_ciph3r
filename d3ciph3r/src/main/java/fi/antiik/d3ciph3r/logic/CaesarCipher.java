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
     * shift is used to determinate the number of positions to be shifted. If
     * shift is negative it's multplied by one so correct shift is used. If the
     * shift is zero whe use default shift, which is 3.
     *
     * @param shift number how many shift is needed in alphabets.
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
     * @param plaintext plaintext to be encrypted.
     * @param shift shift used in encrypting.
     * @return ciphered text.
     */
    public String encrypt(String plaintext, int shift) {
        setShift(shift);
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
     * @param shift shift used in decrypting.
     * @return plaintext of encrypted string.
     */
    public String decrypt(String encryptedText, int shift) {
        setShift(shift);
        String decryptedTxt = "";
        for (int i = 0; i < encryptedText.length(); i++) {
            int c = (char) (encryptedText.charAt(i));
            if (Character.isUpperCase(c)) {
                c -= this.shift % 26;
                if (c < 'A') {
                    c += 26;
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
