/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import static sun.security.krb5.Confounder.bytes;

/**
 *
 * @author fernanda
 */
public class UserDAO {
    
    //@PersistenceContext(unitName = "watiapiPU")
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
    
}
