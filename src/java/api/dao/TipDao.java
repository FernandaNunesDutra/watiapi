/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.Tip;
import api.model.Tip_;
import java.util.Date;
import java.util.List;
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
public class TipDao {
   
    private EntityManager em;
    
    public TipDao(EntityManager em){
        this.em = em;
    }   
    
     public List<Tip> getByDate(Date date){
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Tip> criteriaQuery = criteriaBuilder.createQuery(Tip.class);
        Root<Tip> root = criteriaQuery.from(em.getMetamodel().entity(Tip.class));
        
        ParameterExpression<Date> parameter = criteriaBuilder.parameter(Date.class);
        Predicate dateCreationPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(Tip_.dateCreation).as(Date.class), parameter);
        Predicate dateCreationOrPredicate = criteriaBuilder.or(dateCreationPredicate, root.get(Tip_.dateCreation).isNull());

        criteriaQuery.where(dateCreationOrPredicate);

        List<Tip> tips = em.createQuery(criteriaQuery)
                .setParameter(parameter, date, TemporalType.DATE)
                .getResultList();
        
        return tips;
    }
}
