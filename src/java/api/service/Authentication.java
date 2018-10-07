/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import java.util.Base64;

/**
 *
 * @author fernanda
 */
public class Authentication {
    
    public static String key = ")`pS_gKY]?`~&>b=`'Za~%UGx3[*ND}/(.'8]PvmN!";
    
    public static String generateToken(String email, byte[] senha, String nome, long id) {
        
        String keySource = email + nome + senha + id + key;
        byte[] tokenByte = Base64.getEncoder().encode(keySource.getBytes());
        String token = new String(tokenByte);
        
        return token;
   
    }  
}
