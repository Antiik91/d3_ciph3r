/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.main;

import fi.antiik.d3ciph3r.logic.DES;
import fi.antiik.d3ciph3r.logic.*;
import fi.antiik.d3ciph3r.ui.*;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        try {

            DES des = new DES();
            des.generateKey();
            String key = "NeedAKey";
           
           String encryptHello = des.encrypt("HelloWor", key, false);
            System.out.println("Size of the encryption. " + encryptHello.length());
            System.out.println(encryptHello);
            String decryptHello = des.encrypt(encryptHello, key, true);
            System.out.println("Decrytped: " + decryptHello);
            System.out.println("size of the decryp "+ decryptHello.length());
            for (int i = 0; i < encryptHello.length(); i++) {
                System.out.println("CHARACTER " + i + ": " + encryptHello.charAt(i));
            }
//            UserInterface ui = new UserInterface(new Scanner(System.in));
            //            ui.run();
            //            System.out.println(desTest.decrypt(secretText));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
