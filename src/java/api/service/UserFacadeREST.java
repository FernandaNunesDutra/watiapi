/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.dao.UserDAO;
import api.model.User;
import api.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernanda
 */
@Stateless
@Path("api.user")
public class UserFacadeREST extends AbstractFacade<User> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public UserFacadeREST() {
        super(User.class);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
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
            
            /*Query query = em.createQuery("Select u FROM User u WHERE u.email = :email and u.password = :password", User.class); 
            query.setParameter("email", o.get("email").getAsString());
            query.setParameter("password", password.getBytes());
            User user = (User) query.getSingleResult();*/
            User user = userDao.login(o.get("email").getAsString(), password);

            if(user != null){
            
                UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getName());
                Gson gson = new Gson();
                String json = gson.toJson(response);
                
                return Response.ok(json, MediaType.APPLICATION_JSON).build();   
            
            }else{
                return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inválido.").build();  
            }
            
            

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro no servidor.").build();  
        }
        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
