/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Lob
    @Column(name = "password")
    private byte[] password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<TipUser> tipUserCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "authorize_data")
    private Boolean authorizeData;
    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Column(name = "dt_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dtCadastro;
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Column(name = "experimental_groups")
    private Integer experimentalGroups;
    @Column(name = "gender")
    private Character gender;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "pesquisa_enviada")
    private Boolean pesquisaEnviada;
    @Size(max = 255)
    @Column(name = "phone")
    private String phone;
    @Size(max = 255)
    @Column(name = "prefered_language")
    private String preferedLanguage;
    @Column(name = "receive_emails")
    private Boolean receiveEmails;
    @Column(name = "recover_code")
    private Integer recoverCode;
    @Column(name = "token")
    private String token;
    
    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAuthorizeData() {
        return authorizeData;
    }

    public void setAuthorizeData(Boolean authorizeData) {
        this.authorizeData = authorizeData;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getExperimentalGroups() {
        return experimentalGroups;
    }

    public void setExperimentalGroups(Integer experimentalGroups) {
        this.experimentalGroups = experimentalGroups;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Boolean getPesquisaEnviada() {
        return pesquisaEnviada;
    }

    public void setPesquisaEnviada(Boolean pesquisaEnviada) {
        this.pesquisaEnviada = pesquisaEnviada;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPreferedLanguage() {
        return preferedLanguage;
    }

    public void setPreferedLanguage(String preferedLanguage) {
        this.preferedLanguage = preferedLanguage;
    }

    public Boolean getReceiveEmails() {
        return receiveEmails;
    }

    public void setReceiveEmails(Boolean receiveEmails) {
        this.receiveEmails = receiveEmails;
    }

    public Integer getRecoverCode() {
        return recoverCode;
    }

    public void setRecoverCode(Integer recoverCode) {
        this.recoverCode = recoverCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<TipUser> getTipUserCollection() {
        return tipUserCollection;
    }

    public void setTipUserCollection(Collection<TipUser> tipUserCollection) {
        this.tipUserCollection = tipUserCollection;
    }

}
