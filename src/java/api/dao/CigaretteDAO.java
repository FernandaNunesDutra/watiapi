/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.dao;

import api.model.Cigarette;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author fernanda
 */
public class CigaretteDAO {
    
    private EntityManager em;
    
    public CigaretteDAO(EntityManager em){
        this.em = em;
    }   
    
    public Cigarette getToday(Long userId){
        Date creationDate;
        
        try{
            Date today = new Date();
            creationDate = new SimpleDateFormat("yyyyMMdd").parse(today.toString());
        
        }catch(Exception e){
           return null;  
        }
        
        return getByDate(creationDate, userId);  
    }
    
    public Cigarette getByDate(Date date, Long userId){
        
        try{
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           String dateString  = formatter.format(date);
           String query = String.format("SELECT * FROM tb_cigarette WHERE id_user = %d AND date_creation='%s'", userId, dateString, Cigarette.class);

           Cigarette dateCigarette = (Cigarette)em.createNativeQuery(query, Cigarette.class).getSingleResult();

           return new Cigarette();
        }
        catch(NoResultException ex)
        {
            return null;
        }
        
    }
    
    
    public void alter(Cigarette cigarette){
    
        if(getByDate(cigarette.getDateCreation(), cigarette.getUserId()) == null){
            insert(cigarette);
        }else{
            update(cigarette);
        }
    }
    
    public void insert(Cigarette cigarette){
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date  = formatter.format(cigarette.getDateCreation());
 
        String query = String.format("INSERT INTO tb_cigarette (pack_cigarettes_price, num_cigarette, date_creation,id_user, economized, spent) "
                + "         VALUES(%s , %d, '%s', %d, %s , %s)" ,
                            cigarette.getFormatPricePack(), 
                            cigarette.getNumCigarette(), 
                            date, 
                            cigarette.getUserId(),
                            cigarette.getFormatEconomized(),
                            cigarette.getFormatSpent()); 

        em.createNativeQuery(query).executeUpdate();
    }
    
    public void update(Cigarette cigarette){
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date  = formatter.format(cigarette.getDateCreation());
 
        String query = String.format("UPDATE tb_cigarette SET pack_cigarettes_price = %s, num_cigarette = %d, "
                + " economized = %s, spent = %s WHERE id_user = %d AND date_creation = '%s'", 
                            cigarette.getFormatPricePack(), 
                            cigarette.getNumCigarette(), 
                            cigarette.getFormatEconomized(),
                            cigarette.getFormatSpent(),
                            cigarette.getUserId(),
                            date); 

        em.createNativeQuery(query).executeUpdate();
    }
    
    public double getTotalSpent(Long userId){
        
        try{
            String query = String.format("SELECT SUM(c.spent) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            double spent = (double) em.createNativeQuery(query, Double.class)
                            .getSingleResult();
            return spent;
        }
        catch(NullPointerException e)
        {
            return 0.0;
        }        
       
    }
    
    public double getTotalEconomized(Long userId){
        
        try{
            
            String query = String.format("SELECT SUM(c.economized) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            double economized = (double) em.createNativeQuery(query, Double.class)
                            .getSingleResult();
            return economized;
        }
        catch(NullPointerException e)
        {
            return 0.0;
        }      
    }
    
    public int getSmokedTotal(Long userId){        
        
        try{
            
            String query = String.format("SELECT SUM(c.num_cigarette) FROM tb_cigarette c WHERE c.id_user = %d", userId);

            int smoked = (int) em.createNativeQuery(query, Integer.class)
                            .getSingleResult();
            return smoked;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
    }
    
    public int getAverage(Long userId){
 
          try{
            
            int cigarette = getSmokedTotal(userId);
            String query = String.format("SELECT SUM(c.id) FROM tb_cigarette c WHERE c.id_user = %d", userId);
            int day = (int) em.createNativeQuery(query, Integer.class)
                            .getSingleResult();
            if(day > 0)
                return cigarette/day;
        }
        catch(NullPointerException e)
        {
            return 0;
        }
          
        return 0;
    }
}
