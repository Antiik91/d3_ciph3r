/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.main;
import fi.antiik.d3ciph3r.logic.*;

/**
 *
 * @author User
 */
public class Main {
    public static void main(String[] args) {
        CaesarCipher test = new CaesarCipher(13);
        String text = "attack at dawn";
        System.out.println("before: " + text);
        text = test.encrypt(text);
        System.out.println("After: " + text);
        text = test.decrypt(text);
        System.out.println("And back: " + text);
    }
}
