/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import java.util.Base64;
import java.util.Date;

/**
 *
 * @author fernanda
 */
public class Authentication {
    
    public static String key = ")`pS%UGx3[*ND}(.'8]mN!";
    
    public static String generateToken(String email, long id) {
        
        String keySource = email + new Date() + id + key;
        byte[] tokenByte = Base64.getEncoder().encode(keySource.getBytes());
        String token = new String(tokenByte);
        
        return token;
   
    }  
}
