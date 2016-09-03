/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.main;

import fi.antiik.d3ciph3r.logic.DES;
import fi.antiik.d3ciph3r.logic.*;
import fi.antiik.d3ciph3r.ui.*;
import fi.antiik.d3ciph3r.util.BitTools;
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
//
//           String hello = "abcdefgh";
           String tesTexT = "Hello wolrd what a wonderful day to accomplizh zomething";
           byte[] h = tesTexT.getBytes();
            System.out.println("+++   ORIGINAL STRING IN BYTES   +++");
            for (byte i : h) {
                System.out.print( i+ ", ");
            }
            System.out.println("");
            System.out.println("Original String: " + tesTexT);
//           
           byte[] encrypt = des.encryptPlaintext(tesTexT, key);
            System.out.println("Crypted: " + new String(encrypt));
            System.out.println("+++   ENCRYPTED STRING IN BYTES    +++");
            for (byte f : encrypt) {
                System.out.print(f + ", ");
            }
            System.out.println("");
            byte[] decryptHello = des.decryptData(encrypt, key);
            System.out.println("Decrypted: " + new String(decryptHello));
            System.out.println("+++   DECRYPTED STRING IN BYTES   +++");
            for (byte e : decryptHello) {
                System.out.print(e + ", ");
            }
            System.out.println("");
            System.out.println("END OF PROGRAM");

//            UserInterface ui = new UserInterface(new Scanner(System.in));
//                        ui.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
