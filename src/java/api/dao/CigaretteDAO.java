/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.Cigarette;
import api.model.Cigarette_;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
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
public class CigaretteDAO {
    
    private EntityManager em;
    
    public CigaretteDAO(EntityManager em){
        this.em = em;
    }   
    
    public Cigarette getToday(){
        Date creationDate;
        
        try{
            Date today = new Date();
            creationDate = new SimpleDateFormat("yyyyMMdd").parse(today.toString());
        
        }catch(Exception e){
           return null;  
        }
        
        return getByDate(creationDate);  
    }
    
    public Cigarette getByDate(Date date){
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Cigarette> criteriaQuery = criteriaBuilder.createQuery(Cigarette.class);
        Root<Cigarette> root = criteriaQuery.from(em.getMetamodel().entity(Cigarette.class));
        
        ParameterExpression<Date> parameter = criteriaBuilder.parameter(Date.class);
        Predicate dateCreationPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Cigarette_.dateCreation).as(Date.class), parameter);
        Predicate dateCreationOrPredicate = criteriaBuilder.or(dateCreationPredicate, root.get(Cigarette_.dateCreation).isNull());

        criteriaQuery.where(dateCreationOrPredicate);

        Cigarette dateCigarette = em.createQuery(criteriaQuery)
                .setParameter(parameter, date, TemporalType.DATE)
                .getSingleResult();
        
        return dateCigarette;
    }
    
    public void insert(Cigarette cigarette){
        String query = "INSERT INTO tb_cigerette (pack_cigarettes_price, num_cigarette, date_creation) "
                     + "VALUES(:pack , :num, :date)";

        em.createNativeQuery(query)
           .setParameter("pack", cigarette.getPackCigarettesPrice())
           .setParameter("num", cigarette.getNumCigarette())                
           .setParameter("date", cigarette.getDateCreation())
           .executeUpdate();
    }
}
