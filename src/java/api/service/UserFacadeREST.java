/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.service;

import api.model.User;
import api.response.UserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(User entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, User entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @POST
    @Path("createUserProfile")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUserProfile(String login) {
      
        try{
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(login).getAsJsonObject();
            String password = o.get("password").getAsString();
            Query query = em.createQuery("Select u FROM User u WHERE u.email = :email and u.password = :password", User.class); 
            query.setParameter("email", o.get("email").getAsString());
            query.setParameter("password", password.getBytes());
            User user = (User) query.getSingleResult();

            Gson gson = new Gson();
            UserResponse response = new UserResponse(user.getId(), user.getEmail(), user.getName());
            String json = gson.toJson(response);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();   

        }catch(Exception e){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inválido.").build();  
        }
        
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
