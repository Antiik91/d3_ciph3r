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
           String hello = "abcdefgh";
           byte[] h = hello.getBytes();
            System.out.println("+++   ORIGINAL STRING IN BYTES   +++");
            for (byte i : h) {
                System.out.print( i+ ", ");
            }
            System.out.println("");
           byte[] encryptHello = des.encrypt("HelloWor", key, true);
            System.out.println("+++   ENCRYPTED STRING IN BYTES    +++");
            for (byte f : encryptHello) {
                System.out.print(f + ", ");
            }
            String test = new String(encryptHello, "UTF-8");
            System.out.println(test + " MAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println("");
            byte[][] hey = des.getSubKeys();
            des.setSubKeys(hey);
            String lol = new String(encryptHello, "UTF-8");
            byte[] decryptHello = des.encrypt(lol, key, false);
            System.out.println("+++   DECRYPTED STRING IN BYTES   +++");
            for (byte e : decryptHello) {
                System.out.print(e + ", ");
            }
            System.out.println("");
            System.out.println("END OF PROGRAM");
//            UserInterface ui = new UserInterface(new Scanner(System.in));
            //            ui.run();
            //            System.out.println(desTest.decrypt(secretText));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
