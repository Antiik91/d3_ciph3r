/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    /**
     * New UserInterface creates logic wich operates commands from the user.
     *
     * @param scanner comes from Main
     */
    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.logic = new Logic();
    }

    /**
     * Run is called from Main. it's the centre of program and is active while
     * program is running.
     */
    public void run() {
        System.out.println("Hello and welcome to D3ciph3r program! \n"
                + "In here you can Encrypt and decrypt your messages using Caesar Cipher or Data Encryption Standard [WIP!] \n");

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
    private void execute(int cmd) {
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
 * Handles the String for caesar cipher.
 * @return String to be encrypted or decrypted.
 */
    private String handleCaesar() {
        System.out.println("Type 1 if you want to use a string or "
                + "Type 2 if you want to use a txtfile.");
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
        String plainText = handleCaesar();

        int shift = handleShift();

        String cipherText = this.logic.encrytpCaesar(plainText, shift);
        this.cipherTextContent = cipherText;
        System.out.println("Your shiftkey(Keep safe): " + shift);
        System.out.println("Your crypted text = " + cipherText);
        System.out.println("Dont forget to save!");
    }

    /**
     * Decrypts given cipher text using Caesar Cipher.
     *
     * @param cipherText String to be decrypted.
     * @param shift Shift used for encrypting
     */
    private void caesarDecrypt() {
        String cipherText = handleCaesar();
        int shift = handleShift();
        String plainText = this.logic.decryptCaesar(cipherText, shift);
        System.out.println("Your text: " + plainText);
        System.out.println("Dont forget to save!");
    }

    private void DESencrypt() {
        System.out.println("Sorry, not yet available");
    }

    private void DESDecrypt() {
        System.out.println("Sorry, not yet available");
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
        System.out.println("Path to the file: ");
        String path = this.scanner.nextLine();
        try {
            this.logic.saveFile(path, this.cipherTextContent);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
