
package fi.antiik.d3ciph3r.ui;

import fi.antiik.d3ciph3r.logic.*;
import java.util.Scanner;

/**
 * UI to the program.
 *
 * @author janantik
 */
public class UserInterface {

    private Scanner scanner;
    private Logic logic;
    private String textFromFile = "";
    private String cipherTextContent = "";
    private String decryptedText = "";

    /**
     * New UserInterface creates logic wich operates commands from the user.
     *
     * @param scanner comes from Main
     */
    public UserInterface(Scanner scanner) throws Exception {
        this.scanner = scanner;
        this.logic = new Logic();
    }

    /**
     * Run is called from Main. it's the centre of program and is active while
     * program is running.
     */
    public void run() throws Exception {
        System.out.println("Hello and welcome to D3ciph3r program! \n"
                + "In here you can Encrypt and decrypt your messages using Caesar Cipher or Data Encryption Standard \n");

        printInstructions();
        while (true) {
            System.out.print("Your command: ");
            String order = this.scanner.nextLine();
            int cmd = this.logic.handleCommand(order);
            execute(cmd);
        }
    }

    /**
     * Prints the instructions how to use program.
     */
    private void printInstructions() {
        System.out.println("1: Encrypt message with Caesar Cipher \n"
                + "2: Decrypt message with Caesar Cipher \n"
                + "3: Encrypt message with DES \n"
                + "4: Decrypt message with DES \n"
                + "5: Print instructions \n"
                + "6: Load textfile \n"
                + "7: Save textfile \n"
                + "8: Quit");
    }

    /**
     * Executes the command given from param.
     *
     * @param cmd command to be executed.
     */
    private void execute(int cmd) throws Exception {
        switch (cmd) {
            case 1:
                caesarEncrypt();
                break;
            case 2:
                caesarDecrypt();
                break;
            case 3:
                DESencrypt();
                break;
            case 4:
                DESDecrypt();
                break;
            case 5:
                printInstructions();
                break;
            case 6:
                readFile();
                break;
            case 7:
                saveFile();
                break;
            case 8:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command! ");
                break;

        }
    }

    /**
     * Handles the String for crypting.
     *
     * @return String to be encrypted or decrypted.
     */
    private String stringOrFile() {
        System.out.println("Type 1 if you want to use a string or "
                + "type 2 if you want to use a txtfile.");
        int option = -1;
        while (option != 1 || option != 2) {
            String possibleOption = this.scanner.nextLine();
            option = this.logic.handleCommand(possibleOption);
            if (option == 1 || option == 2) {
                break;
            } else {
                System.out.println("Please type 1 or 2");
            }
        }
        if (option == 1) {
            System.out.print("Type the string you wish to use: ");
            String text = this.scanner.nextLine();
            return text;
        } else {
            readFile();
            return this.textFromFile;
        }

    }

    /**
     * Asks the user what shift is to be used in Caesar cipher.
     *
     * @return shift used to encrypt or decrypt the String.
     */
    private int handleShift() {
        int shift = -1;
        while (shift < 0) {
            System.out.print("Shift to be used: ");
            String eShift = this.scanner.nextLine();
            shift = this.logic.handleCommand(eShift);
            if (shift < 0) {
                System.out.println("Please use positive integers only");
            }
        }
        return shift;
    }

    /**
     * Encrypts given string using Caesar Cipher.
     *
     * @param plainText String to be encrypted.
     * @param shift Shift used for encrypting.
     */
    private void caesarEncrypt() {
        String plainText = stringOrFile();

        int shift = handleShift();

        String cipherText = this.logic.encrytpCaesar(plainText, shift);
        this.cipherTextContent = cipherText;
        System.out.println("Your shiftkey(Keep safe): " + shift);
        saveFile(this.cipherTextContent);
    }

    /**
     * Decrypts given cipher text using Caesar Cipher.
     *
     * @param cipherText String to be decrypted.
     * @param shift Shift used for encrypting
     */
    private void caesarDecrypt() {
        String cipherText = stringOrFile();
        int shift = handleShift();
        String plainText = this.logic.decryptCaesar(cipherText, shift);
        this.decryptedText = plainText;
        saveFile(decryptedText);

    }

    /**
     * Method makes sure that the key we want to use is valid for DES.
     *
     * @return valid key
     */
    private String handleKey() {
        String key = "";
        boolean valid = false;
        System.out.println("Please note that key must be exactly 8 characters long. For example: NeedAKey");
        while (!valid) {
            System.out.print("Key: ");
            key = this.scanner.nextLine();
            valid = this.logic.validKey(key);
            if (!valid) {
                System.out.println("Please note that key must be exactly 8 characters long. For example: NeedAKey");
            }
        }
        return key;
    }

    /**
     * Encrypts the data with DES.
     *
     * @throws Exception
     */
    private void DESencrypt() throws Exception {
        String plainText = stringOrFile();
        String key = handleKey();
        byte[] encrypted = this.logic.encryptDES(plainText, key);

        this.cipherTextContent = "";
        for (byte f : encrypted) {
            this.cipherTextContent += f + " ";
        }
        saveFile(this.cipherTextContent);
    }

    /**
     * Decrypts the data with DES.
     *
     * @throws Exception
     */
    private void DESDecrypt() throws Exception {
        System.out.println("NOTE: Please consider using Files instead of strings as DES decrypting uses byte arrays.");
        byte[] cipher = handleDESDecrypt();
        if (cipher == null) {
            System.out.println("There was a problem while parsing your bytes. Please check that the file or the string is correctly made. For example: 12 -222 34 12 \n "
                    + "Remove any double spaces or extra characthers.");
            return;
        }
        String key = handleKey();
        byte[] plainTextBytes = this.logic.decryptDES(cipher, key);
        this.decryptedText = new String(plainTextBytes);
        saveFile(this.decryptedText);

    }

    /**
     * Because we use byte array with message crypted with DES we need different
     * method to hanle the bytes.
     *
     * @return byte array of ciphertext.
     */
    private byte[] handleDESDecrypt() {
        byte[] crypted = null;
        System.out.print("Type 1 for a string and 2 for a file: ");
        int option = -1;
        while (option != 1 || option != 2) {
            String possibleOption = this.scanner.nextLine();
            option = this.logic.handleCommand(possibleOption);
            if (option == 1 || option == 2) {
                break;
            } else {
                System.out.println("Please type 1 or 2");
            }
        }
        if (option == 1) {
            System.out.println("Type the bytes. Use following method: 12 14 15 124 12");
            String byteArray = this.scanner.nextLine();
            crypted = this.logic.getByteArrayFromString(byteArray);
        } else {
            readFile();
            crypted = this.logic.getByteArrayFromString(this.textFromFile);
        }

        return crypted;
    }

    /**
     * Reads the file from given path.
     *
     * @param path path to the file.
     */
    private void readFile() {
        System.out.print("Path to the file: ");
        String path = this.scanner.nextLine();
        try {
            this.textFromFile = this.logic.returnFileAsString(path);
        } catch (Exception e) {
            System.out.println("Sorry, no file found:  " + e);
        }

    }

    /**
     * Saves the file to given path.
     *
     * @param path Path to the file.
     */
    private void saveFile() {
        System.out.println("Encrypted or ciphered textfile. Leave empty if you don't wish to save it.");
        System.out.println("Path to the file: ");
        String pathEncrypted = this.scanner.nextLine();
        System.out.println("Decrypted or plain textfile. Leave empty if you don't wish to save it.");
        String pathDecrypted = this.scanner.nextLine();
        try {
            if (!pathEncrypted.isEmpty()) {
                this.logic.saveFile(pathEncrypted, this.cipherTextContent);
            }
            if (!pathDecrypted.isEmpty()) {
                this.logic.saveFile(pathDecrypted, this.decryptedText);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
/**
 * Method to save the file when content is crypted.
 * 
 * @param content  content to be saved.
 */
    private void saveFile(String content) {
        System.out.println("Path to save your content: ");
        String path = this.scanner.nextLine();
        try {
            this.logic.saveFile(path, content);
            System.out.println("your content is saved to: " + path + "\n"
                    + "Remember to store your key! ");
        }catch (Exception e) {
            System.out.println("Error while trying to save the file: " + e);
        }

    }
}