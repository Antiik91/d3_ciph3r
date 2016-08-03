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
        AES aesTest = new AES();
        String plaintext = "Be ready to move on!";
        String p1 = "Can I use an_ythiGnIw0Nt1?;";
       
        aesTest.encryptAndDecrypt(plaintext);
        aesTest.encryptAndDecrypt(p1);
    }
}
