/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.CigaretteDAO;
import api.dao.UserDAO;
import api.model.Cigarette;
import api.model.CigarettesAverage;
import api.model.User;
import api.response.RankingResponse;
import api.response.TotalCigaretteResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    @Path("today")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postToday(@HeaderParam("token") String token, String cigarette) {
      
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                JsonParser parser = new JsonParser();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                
                JsonObject o = parser.parse(cigarette).getAsJsonObject();
                
                double packCigarettesPrice =  o.get("pack_cigarettes_price").getAsDouble();
                String dateString = o.get("date_creation").getAsString();
                Date date = formatter.parse(dateString);
                int numCigarette = o.get("num_cigarette").getAsInt();
                double economized =  o.get("economized").getAsDouble();
                double spent =  o.get("spent").getAsDouble();
                Long userId = o.get("id_user").getAsLong();
                
                CigaretteDAO cigaretteDao = new CigaretteDAO(em);
                cigaretteDao.alter(new Cigarette(packCigarettesPrice, economized, spent, numCigarette, date, userId));
                
                Gson gson = new Gson();
                String json = gson.toJson(cigaretteDao.getToday(userId));
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    @GET
    @Path("today")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getToday(@HeaderParam("token") String token) {
        Cigarette today = null;
        UserDAO userDao = new UserDAO(em);
        
        boolean validate = userDao.validate(token);

        if(validate){
            
            User user = userDao.findByToken(token);
            CigaretteDAO cigaretteDao = new CigaretteDAO(em);
            today = cigaretteDao.getToday(user.getId());
        }
        
        return cigarette(token, today);
    }
    
    @GET
    @Path("total")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response total(@HeaderParam("token") String token) {
      
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                
                User user = userDao.findByToken(token);
                CigaretteDAO cigaretteDao = new CigaretteDAO(em);
                
                double spent = cigaretteDao.getTotalSpent(user.getId());
                long smokedTotal = cigaretteDao.getSmokedTotal(user.getId());
                long average = cigaretteDao.getAverage(user.getId());
                double economized = cigaretteDao.getTotalEconomized(user.getId());
                
                TotalCigaretteResponse total = new TotalCigaretteResponse(economized, spent, smokedTotal, average);
                
                Gson gson = new Gson();
                String json = gson.toJson(total);
                return Response.ok(json, MediaType.APPLICATION_JSON).build();  
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
    }
    
    @GET
    @Path("ranking")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response ranking(@HeaderParam("token") String token) {
      
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                
                CigaretteDAO cigaretteDAO = new CigaretteDAO(em);
                
                User user = userDao.findByToken(token);
                List<CigarettesAverage> userCigarettes = cigaretteDAO.getOneMonthAgoSmoked(user.getId());
                List<CigarettesAverage> averageCigarettes = cigaretteDAO.getOneMonthAgoAverageSmoked();
                
                Gson gson = new Gson();
                String json = gson.toJson(new RankingResponse(userCigarettes, averageCigarettes));
                return Response.ok(json, MediaType.APPLICATION_JSON).build(); 
            
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("Ação não permitida para esse usuário.").build();  
            }                  

        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();  
        }
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
