/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.UserDAO;
import api.model.User;
import api.response.UserResponse;
import api.service.Authentication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Base64;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sun.text.normalizer.ICUBinary;

/**
 *
 * @author fernanda
 */
@Stateless
@Path("user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String login) {
      
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(login).getAsJsonObject();
            String password = o.get("password").getAsString();
            User user = userDao.login(o.get("email").getAsString(), password);

            if(user != null){
           
                String token = Authentication.generateToken(user.getEmail(), user.getPassword(), user.getName(), user.getId());
                UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getName(), token);
                Gson gson = new Gson();
                String json = gson.toJson(response);
                
                return Response.ok(json, MediaType.APPLICATION_JSON).build();   
            
            }else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inválido.").build();  
            }            

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
