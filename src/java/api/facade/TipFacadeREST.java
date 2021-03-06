/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.TipDAO;
import api.dao.UserDAO;
import api.model.Tip;
import api.response.TipsResponse;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@Path("tip")
public class TipFacadeREST extends AbstractFacade<Tip> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public TipFacadeREST() {
        super(Tip.class);
    }
    
    @GET
    @Path("all")
    public Response all(@HeaderParam("token") String token, @QueryParam("date") String date) {
      
        UserDAO userDao = new UserDAO(em);
        TipDAO tipDao = new TipDAO(em);
        
        try{

            boolean validate = userDao.validate(token);

            if(validate){

                List<Tip> tips;
                Date creationDate = parseDate(date);
                
                if(creationDate == null) {
                    tips = tipDao.getAll();
                }else{
                    tips = tipDao.getByDate(creationDate);
                }          
                
                TipsResponse response = new TipsResponse(tips);
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
    
    private Date parseDate(String date){
        Date creationDate;
        
        try{
           
            creationDate = new SimpleDateFormat("yyyyMMdd").parse(date);
        
        }catch(Exception e){
            
           creationDate = null;
        
        }
        
        return creationDate;
    }
}