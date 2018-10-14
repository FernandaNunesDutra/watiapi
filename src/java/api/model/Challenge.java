/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "tb_challenge")
/*@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Challenge.findAll", query = "SELECT c FROM Challenge c")
    , @NamedQuery(name = "Challenge.findById", query = "SELECT c FROM Challenge c WHERE c.id = :id")
    , @NamedQuery(name = "Challenge.findByDescription", query = "SELECT c FROM Challenge c WHERE c.description = :description")
    , @NamedQuery(name = "Challenge.findByTitle", query = "SELECT c FROM Challenge c WHERE c.title = :title")
    , @NamedQuery(name = "Challenge.findByType", query = "SELECT c FROM Challenge c WHERE c.type = :type")
    , @NamedQuery(name = "Challenge.findByValue", query = "SELECT c FROM Challenge c WHERE c.value = :value")})*/
public class Challenge implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    @SerializedName("id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "description")
    @SerializedName("description")
    private String description;
    @Size(max = 255)
    @Column(name = "title")
    @SerializedName("title")
    private String title;
    @Column(name = "type")
    @SerializedName("type")
    private Integer type;
    @Column(name = "value")
    @SerializedName("value")
    private Integer value;
    @Column(name = "date_creation")
    @SerializedName("date_creation")
    private Date dateCreation;

    public Challenge() {
    }

    public Challenge(Integer id) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
    
    
}
