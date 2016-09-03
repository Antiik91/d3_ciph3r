package fi.antiik.d3ciph3r.main;

import fi.antiik.d3ciph3r.logic.DES;
import fi.antiik.d3ciph3r.logic.*;
import fi.antiik.d3ciph3r.ui.*;
import fi.antiik.d3ciph3r.util.BitTools;
import fi.antiik.d3ciph3r.filehandler.*;
import java.util.Scanner;

/**
 *
 * @author janantik
 */
public class Main {

    public static void main(String[] args) {
        try {
            Logic loc = new Logic();
            String pap = loc.returnFileAsString("E:/pap.txt");
            CaesarCipher cs = new CaesarCipher();
            DES des = new DES();
            des.generateKey();
            String key = "NeedAKey";
//
//           String hello = "abcdefgh";
//            String tesTexT = "Hello wolrd what a wonderful day to accomplizh zomething";
//            byte[] h = tesTexT.getBytes();
//            System.out.println("+++   ORIGINAL STRING IN BYTES   +++");
//            for (byte i : h) {
//                System.out.print(i + ", ");
//            }
//            System.out.println("");
//            System.out.println("Original String: " + tesTexT);

//            byte[] encrypt = des.encryptPlaintext(pap, key);

//            System.out.println("Crypted: " + new String(encrypt));
//            System.out.println("+++   ENCRYPTED STRING IN BYTES    +++");
//            for (byte f : encrypt) {
//                System.out.print(f + ", ");
//            }
//             String test1 = cs.encrypt(pap, 3);
//             
//            long startCrypt = System.currentTimeMillis();
////            byte[] decrypt = des.decryptData(encrypt, key);
//             cs.decrypt(test1, 3);
//            long endCrypt = System.currentTimeMillis();
//            System.out.println("DES DECRYPTING time: " + (endCrypt - startCrypt) + "ms.");

//            System.out.println("Decrypted: " + new String(decryptHello));
//            System.out.println("+++   DECRYPTED STRING IN BYTES   +++");
//            for (byte e : decryptHello) {
//                System.out.print(e + ", ");
//            }
//            System.out.println("");
            System.out.println("END OF PROGRAM");

//            UserInterface ui = new UserInterface(new Scanner(System.in));
//                        ui.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
