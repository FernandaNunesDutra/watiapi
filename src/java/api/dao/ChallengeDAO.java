/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.Challenge;
import api.model.Challenge_;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    
     public List<Challenge> getByDate(Date date){
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Challenge> criteriaQuery = criteriaBuilder.createQuery(Challenge.class);
        Root<Challenge> root = criteriaQuery.from(em.getMetamodel().entity(Challenge.class));
        
        ParameterExpression<Date> parameter = criteriaBuilder.parameter(Date.class);
        Predicate dateCreationPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Challenge_.dateCreation).as(Date.class), parameter);
        Predicate dateCreationOrPredicate = criteriaBuilder.or(dateCreationPredicate, root.get(Challenge_.dateCreation).isNull());

        criteriaQuery.where(dateCreationOrPredicate);

        List<Challenge> challenges = em.createQuery(criteriaQuery)
                .setParameter(parameter, date, TemporalType.DATE)
                .getResultList();
        
        return challenges;
    }
}
