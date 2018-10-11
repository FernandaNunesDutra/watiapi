/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.ChallengeDAO;
import api.dao.UserDAO;
import api.model.Challenge;
import api.response.ChallengesResponse;
import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author fernanda
 */
@Stateless
@Path("challenge")
public class ChallengeFacadeREST extends AbstractFacade<Challenge> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public ChallengeFacadeREST() {
        super(Challenge.class);
    }

    @GET
    @Path("all")
    //@Consumes(MediaType.APPLICATION_JSON)
    //@HEAD
    public Response all(@HeaderParam("token") String token, @QueryParam("date") String date) {
      
        UserDAO userDao = new UserDAO(em);
        ChallengeDAO challengeDao = new ChallengeDAO(em);
        
        try{

            boolean validate = userDao.validate(token);

            if(validate){
           
                ChallengesResponse response = new ChallengesResponse(challengeDao.getAll());
                Gson gson = new Gson();
                String json = gson.toJson(response);
                
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
