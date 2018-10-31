/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.TipUser;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author fernanda
 */
public class TipUserDAO {
    
    private EntityManager em;
    
    public TipUserDAO(EntityManager em){
        this.em = em;
    }   
    
    public void alter(long tipId, long userId, boolean like){
     
        if(getById(tipId, userId) == null){
            insert(tipId, userId, like);
            return;
        }
        
        update(tipId, userId, like);
    }
    
    public TipUser getById(long tipId, long userId){
        TipUser tipUser = new TipUser();

        try{
            Query query = em.createQuery("Select t FROM Tip t WHERE t.id_user = :id_user AND t.id_tip = : id_tip", TipUser.class); 
            query.setParameter("id_user", userId);
            query.setParameter("id_tip", tipId);
            
            tipUser = (TipUser) query.getSingleResult();
        
        }catch(Exception e){
            System.out.print("Erro ao buscar usuário no banco.");
        }
        
        return tipUser;
    }
        
    public void update(long tipId, long userId, boolean like){
         
         try{
             
            Query query = em.createQuery("UPDATE TipUser u SET u.like = :like WHERE u.id_user= :id_user AND u.id_tip-:id_tip", TipUser.class); 
            query.setParameter("like", like);
            query.setParameter("id_tip", tipId);
            query.setParameter("id_user", userId);
            query.executeUpdate();
        
        }catch(Exception e){
            System.out.print("Erro ao atualizar gostei das dicas.");
        }
    } 
    
    public void insert(long tipId, long userId, boolean like){
        String query = "INSERT INTO tb_tip_user (id_tip, id_user, like) "
                     + "VALUES(:id_tip , :id_user, :like)";

        em.createNativeQuery(query)
            .setParameter("like", like)
            .setParameter("id_tip", tipId)
            .setParameter("id_user", userId)
            .executeUpdate();
    }
    
}
