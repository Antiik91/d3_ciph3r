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
//            Logic loc = new Logic();
//            String filaAsString = loc.returnFileAsString("FILEPATH HERE");
//            CaesarCipher cs = new CaesarCipher();
//            DES des = new DES();
//            String key = "NeedAKey";

            // -------------------------------- DES ENCRYPT TIME AND DECYPT TIME  --------------------------------
//            long startCrypt = System.currentTimeMillis();
//            byte[] encrypted = des.encryptPlaintext(fileAsString, key);
//            long endCrypt = System.currentTimeMillis();
//            System.out.println("DES ENCRYPTING time: " + (endDesDerypt - startDESDecrypt) + "ms.");
//            long startDESDecrypt = System.currentTimeMillis();
//            byte[] decrypt = des.decryptData(encrypted, key);
//            long endDesDeCrypt = System.currentTimeMillis();
//            System.out.println("DES DECRYPTING time: " + (endDesDerypt - startDESDecrypt) + "ms.");
            
        //    -------------------------------- CAESAR CIPHER ENCRYPT TIME AND DECYPT TIME  --------------------------------
//            long startCaesarEncrypt = System.currentTimeMillis();
//             String test1 = cs.encrypt(pap, 3);
//             cs.decrypt(test1, 3);
//            long endCaesarEncrypt = System.currentTimeMillis();
//            System.out.println("CAESAR ENCRYPTING time: " + (endCaesarEncrypt - startCaesarEncrypt) + "ms.");

//            long startCaesarDecrypt = System.currentTimeMillis();
//              cs.decrypt(test1, 3);
//            long endCaesarDecrypt = System.currentTimeMillis();
//            System.out.println("CAESAR DECRYPTING time: " + (endCaesarDecrypt - startCaesarDecrypt) + "ms.");
// ------------------------------------------------------------------------------------------------------------------------------------
            UserInterface ui = new UserInterface(new Scanner(System.in));
                        ui.run();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
