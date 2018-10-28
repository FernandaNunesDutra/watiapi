/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "tb_cigarette")
public class Cigarette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "pack_cigarettes_price")
    private Long packCigarettesPrice;
    @Column(name = "num_cigarette")
    private Integer numCigarette;
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    @Column(name = "economized")
    private Long economized;
    @Column(name = "spent")
    private Long spent;

    public Cigarette() {
    }

    public Cigarette(Long packCigarettesPrice, Long economized, Long spent, Integer numCigarette, Date dateCreation) {
        this.packCigarettesPrice = packCigarettesPrice;
        this.numCigarette = numCigarette;
        this.dateCreation = dateCreation;
        this.economized = economized;
        this.spent = spent;
    }    

    public Cigarette(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getPackCigarettesPrice() {
        return packCigarettesPrice;
    }

    public void setPackCigarettesPrice(Long packCigarettesPrice) {
        this.packCigarettesPrice = packCigarettesPrice;
    }

    public Integer getNumCigarette() {
        return numCigarette;
    }

    public void setNumCigarette(Integer numCigarette) {
        this.numCigarette = numCigarette;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getEconomized() {
        return economized;
    }

    public void setEconomized(Long economized) {
        this.economized = economized;
    }

    public Long getSpent() {
        return spent;
    }

    public void setSpent(Long spent) {
        this.spent = spent;
    }
    
}
