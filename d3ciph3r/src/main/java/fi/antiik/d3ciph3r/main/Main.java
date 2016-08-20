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
            String key = des.getKey();
            System.out.println(key);
            byte[] b = key.getBytes();

            
            byte[] lol = des.encrypt("hello world", key);

            System.out.println(new String(lol));
            System.out.println("Leikitääs: ");
            
//            UserInterface ui = new UserInterface(new Scanner(System.in));
            //            ui.run();
            //            System.out.println(desTest.decrypt(secretText));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
