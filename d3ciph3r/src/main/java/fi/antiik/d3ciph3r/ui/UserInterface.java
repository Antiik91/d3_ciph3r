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

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
        this.logic = new Logic();
    }
    
    public void run() {
        System.out.println("Hello and welcome to D3ciph3r program! \n"
                + "In here you can Encrypt and decrypt your messages using Caesar Cipher or Data Encryption Standard [WIP!]");
        
        printInstructions();
        while(true){
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
                + "6: quit");
    }

    private void execute(int cmd) {
        switch(cmd) {
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
                System.exit(0);
            default:
                System.out.println("Invalid command! ");
                break;
            
        }
    }

    private void caesarEncrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void caesarDecrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DESencrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DESDecrypt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int handleCommand() {
        int command = 0;
        try {
            command = this.scanner.nextInt();
            return command;
        }catch(Exception e) {
            System.out.println(" invalid command! Please use integers!");
            return 0;
        }
    }
}
