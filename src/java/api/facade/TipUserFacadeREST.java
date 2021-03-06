/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.facade;

import api.dao.TipUserDAO;
import api.dao.UserDAO;
import api.model.TipUser;
import api.response.TipUserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
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
@Path("tipuser")
public class TipUserFacadeREST extends AbstractFacade<TipUser> {

    @PersistenceContext(unitName = "watiapiPU")
    private EntityManager em;

    public TipUserFacadeREST() {
        super(TipUser.class);
    }

    @POST
    @Path("like")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response like(@HeaderParam("token") String token, String tip) {
      
        UserDAO userDao = new UserDAO(em);
        
        try{
            
            boolean validate = userDao.validate(token);

            if(validate){
                JsonParser parser = new JsonParser();                
                JsonObject o = parser.parse(tip).getAsJsonObject();
                
                int tipId =  o.get("id_tip").getAsInt();
                Long userId =  o.get("id_user").getAsLong();                
                boolean like =  o.get("like").getAsBoolean();
                
                TipUserDAO tipUserDao = new TipUserDAO(em);
                tipUserDao.alter(tipId, userId, like);
                
                Gson gson = new Gson();
                String json = gson.toJson(new TipUserResponse(tipId, userId));
                
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
