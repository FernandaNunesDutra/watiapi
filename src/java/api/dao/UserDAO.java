/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.User;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author fernanda
 */
public class UserDAO {
    
    private EntityManager em;
    
    public UserDAO(EntityManager em){
        this.em = em;
    }
    
    public User login(String email, String password){
        
        User user = null;
        
        Query query = em.createQuery("Select u FROM User u WHERE u.email = :email and u.password = :password", User.class); 
        query.setParameter("email", email);
        query.setParameter("password", password.getBytes());
        
        try{
            user = (User) query.getSingleResult();
        }catch(Exception e){
            System.out.print("Erro ao buscar usuário no banco.");
        }
        
        return user;
    }
    
     public void updateToken(String token, long id){
         
         try{
             
            Query query = em.createQuery("UPDATE User u SET u.token = :token WHERE u.id = :id", User.class); 
            query.setParameter("token", token);
            query.setParameter("id", id);
            query.executeUpdate();
        
        }catch(Exception e){
            System.out.print("Erro ao deslogar usuário.");
        }
    } 
    
    public boolean validate(String token){
        
        User user = findByToken(token);
                
        try{
            
            if(user !=  null) 
                return true;
        
        }catch(Exception e){
            System.out.print("Erro ao validar usuário pelo token.");
        }
        
        return false;
    }
    
    public boolean logout(String token){
         
         try{
             
            Query query = em.createQuery("UPDATE User u SET u.token = NULL WHERE u.token = :token", User.class); 
            query.setParameter("token", token); 
            query.executeUpdate();
            
            return true;
        
        }catch(Exception e){
            System.out.print("Erro ao deslogar usuário.");
        }
         
         return false;    
    }   
    
    private User findByToken(String token){
        
        User user = null;
        
        Query query = em.createQuery("Select u FROM User u WHERE u.token = :token", User.class); 
        query.setParameter("token", token); 
        
        try{
            user = (User) query.getSingleResult();
        }catch(Exception e){
            System.out.print("Erro ao buscar usuário pelo token no banco.");
        }
        
        return user;
  
    }
    
    
}
