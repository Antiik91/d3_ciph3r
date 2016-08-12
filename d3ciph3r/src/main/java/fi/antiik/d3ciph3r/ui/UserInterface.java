/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.ui;

import fi.antiik.d3ciph3r.logic.*;
import java.util.Scanner;

/**
 *
 * @author janantik
 */
public class UserInterface {

    private Scanner scanner;
    private Logic logic;
    private String textFromFile = "";
    private String cipherTextContentt = "";

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.logic = new Logic();
    }

    public void run() {
        System.out.println("Hello and welcome to D3ciph3r program! \n"
                + "In here you can Encrypt and decrypt your messages using Caesar Cipher or Data Encryption Standard [WIP!] \n"
                + "If you wish to encrypt txt file please load the file first");

        printInstructions();
        while (true) {
            System.out.print("Your command: ");
            int cmd = handleCommand();
            this.scanner.nextLine();
            execute(cmd);
        }
    }

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

    private void execute(int cmd) {
        switch (cmd) {
            case 1:
                String plainText;
                if (this.textFromFile.isEmpty()) {
                    System.out.print("String to be encrypted: ");
                    plainText = this.scanner.nextLine();
                } else {
                    plainText = this.textFromFile;
                }
                System.out.print("Shift to be used: ");
                int shiftEncrypt = -1;
                while (shiftEncrypt < 0) {
                    shiftEncrypt = handleCommand();
                    if (shiftEncrypt < 0) {
                        System.out.println("Please use positive integers only");
                    }
                    this.scanner.nextLine();
                }
                caesarEncrypt(plainText, shiftEncrypt);
                break;
            case 2:
                System.out.print("String to be decrypted: ");
                String cipherText = this.scanner.nextLine();
                System.out.print("Shift: ");
                int shiftDecrypt = -1;
                while (shiftDecrypt < 0) {
                    shiftDecrypt = handleCommand();
                    this.scanner.nextLine();
                    if (shiftDecrypt < 0) {
                        System.out.println("Please use only positive integers");
                    }
                }
                caesarDecrypt(cipherText, shiftDecrypt);
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
                System.out.print("Path to the file: ");
                String path = this.scanner.nextLine();
                readFile(path);
                break;
            case 7:
                System.out.println("Path to the file: ");
                String pathSave = this.scanner.nextLine();
                saveFile(pathSave);
                System.exit(0);
                break;
            case 8:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid command! ");
                break;

        }
    }

    private void caesarEncrypt(String plainText, int shift) {
        String cipherText = this.logic.encrytpCaesar(plainText, shift);
        this.cipherTextContentt = cipherText;
        System.out.println("Your shiftkey(Keep safe): " + shift);
        System.out.println("Your crypted text = " + cipherText);
    }

    private void caesarDecrypt(String cipherText, int shift) {
        String plainText = "";
        if (this.cipherTextContentt != null) {
            cipherText = this.cipherTextContentt;
        }
        plainText = this.logic.decryptCaesar(cipherText, shift);
        System.out.println("Your text: " + plainText);
    }

    private void DESencrypt() {
        System.out.println("Sorry, not yet available");
    }

    private void DESDecrypt() {
        System.out.println("Sorry, not yet available");
    }

    private int handleCommand() {
        int command = 0;
        try {
            command = this.scanner.nextInt();
            return command;
        } catch (Exception e) {
            System.out.println(" invalid command! Please use integers!");
            return -1;
        }
    }

    private void readFile(String path) {
        try {
            this.textFromFile = this.logic.returnFileAsString(path);
        } catch (Exception e) {
            System.out.println("BYHYY! :( : " + e);
        }

    }

    private void saveFile(String path) {
        try {
            this.logic.saveFile(path, this.cipherTextContentt);
        } catch (Exception e) {
            System.out.println("Ouch:");
        }
    }
}
