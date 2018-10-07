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
import javax.persistence.Lob;
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
@Table(name = "tb_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")
    , @NamedQuery(name = "User.findByAuthorizeData", query = "SELECT u FROM User u WHERE u.authorizeData = :authorizeData")
    , @NamedQuery(name = "User.findByBirth", query = "SELECT u FROM User u WHERE u.birth = :birth")
    , @NamedQuery(name = "User.findByDtCadastro", query = "SELECT u FROM User u WHERE u.dtCadastro = :dtCadastro")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByExperimentalGroups", query = "SELECT u FROM User u WHERE u.experimentalGroups = :experimentalGroups")
    , @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findByPesquisaEnviada", query = "SELECT u FROM User u WHERE u.pesquisaEnviada = :pesquisaEnviada")
    , @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone")
    , @NamedQuery(name = "User.findByPreferedLanguage", query = "SELECT u FROM User u WHERE u.preferedLanguage = :preferedLanguage")
    , @NamedQuery(name = "User.findByReceiveEmails", query = "SELECT u FROM User u WHERE u.receiveEmails = :receiveEmails")
    , @NamedQuery(name = "User.findByRecoverCode", query = "SELECT u FROM User u WHERE u.recoverCode = :recoverCode")})
public class User implements Serializable {

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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
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
    @Lob
    @Column(name = "password")
    private byte[] password;
    @Column(name = "pesquisa_enviada")
    private Boolean pesquisaEnviada;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Formato de telefone/fax inválido, deve ser xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
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

}
