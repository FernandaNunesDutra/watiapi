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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "tb_tip")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tip.findAll", query = "SELECT t FROM Tip t")
    , @NamedQuery(name = "Tip.findById", query = "SELECT t FROM Tip t WHERE t.id = :id")
    , @NamedQuery(name = "Tip.findByDescription", query = "SELECT t FROM Tip t WHERE t.description = :description")
    , @NamedQuery(name = "Tip.findByTitle", query = "SELECT t FROM Tip t WHERE t.title = :title")
    , @NamedQuery(name = "Tip.findByValue", query = "SELECT t FROM Tip t WHERE t.value = :value")
    , @NamedQuery(name = "Tip.findByDateCreation", query = "SELECT t FROM Tip t WHERE t.dateCreation = :dateCreation")})
public class Tip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @Column(name = "value")
    private Integer value;
    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    public Tip() {
    }

    public Tip(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "api.model.Tip[ id=" + id + " ]";
    }
    
}
