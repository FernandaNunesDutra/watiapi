/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.Challenge;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author fernanda
 */
public class ChallengeDAO {
    private EntityManager em;
    
    public ChallengeDAO(EntityManager em){
        this.em = em;
    }   
    
    public List<Challenge> getAll(){
        
        List<Challenge> challenges = new ArrayList<>();
        Query query = em.createQuery("Select c FROM Challenge c", Challenge.class); 
            
        try{
        
            challenges = (List<Challenge>)query.getResultList();
        
        }catch(Exception e){
            System.out.print("Erro ao buscar usuário no banco.");
        }
        
        return challenges;
    }
}
