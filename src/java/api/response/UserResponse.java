/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author fernanda
 */
public class UserResponse {
    
    @SerializedName("id")
    private long id;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name; 
    @SerializedName("token")
    private String token; 
    

    public UserResponse(long id, String email, String name, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.token = token;
    }
    
    
}
