/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.antiik.d3ciph3r.logic;

/**
 *
 * @author User
 */
public interface Cipherable {
    public String encrypt(String plaintext);
    public String decrypt(String txt);
}
