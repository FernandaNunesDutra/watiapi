/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.CigaretteDAO;
import api.dao.UserDAO;
import api.model.Cigarette;
import api.model.User;
import api.response.UserResponse;
import api.service.Authentication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernanda
 */
@Stateless
@Path("cigarette")
public class CigaretteFacadeREST extends AbstractFacade<Cigarette> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public CigaretteFacadeREST() {
        super(Cigarette.class);
    }

    @POST
    //@Path("cigarette")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response today(@HeaderParam("token") String token, String cigarette) {
      
         UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                JsonParser parser = new JsonParser();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
                
                JsonObject o = parser.parse(cigarette).getAsJsonObject();
                int numCigarette = o.get("num_cigarette").getAsInt();
                Long packCigarettesPrice =  o.get("pack_cigarettes_price").getAsLong();
                Date date = formatter.parse(o.get("date_creation").getAsString());
                
                CigaretteDAO cigaretteDao = new CigaretteDAO(em);
                cigaretteDao.insert(new Cigarette(packCigarettesPrice, numCigarette, date));
                
                Gson gson = new Gson();
                String json = gson.toJson(cigaretteDao.getToday());
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    @GET
    //@Path("today")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getToday(@HeaderParam("token") String token) {
      
        CigaretteDAO cigaretteDao = new CigaretteDAO(em);
        return cigarette(token, cigaretteDao.getToday());
    }

    
    private Response cigarette(String token, Cigarette cigarette){
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                
                Gson gson = new Gson();
                String json = gson.toJson(cigarette);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
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
