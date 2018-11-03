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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fernanda
 */
@Entity
@Table(name = "tb_tip_user")
public class TipUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipUserPK tipUserPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "like_tip")
    private boolean like;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "id_tip", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tip tip;
    @JoinColumn(name = "id_user", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public TipUser() {
    }

    public TipUser(TipUserPK tipUserPK) {
        this.tipUserPK = tipUserPK;
    }

    public TipUser(TipUserPK tipUserPK, boolean like) {
        this.tipUserPK = tipUserPK;
        this.like = like;
    }

    public TipUser(int idTip, long idUser) {
        this.tipUserPK = new TipUserPK(idTip, idUser);
    }

    public TipUserPK getTipUserPK() {
        return tipUserPK;
    }

    public void setTipUserPK(TipUserPK tipUserPK) {
        this.tipUserPK = tipUserPK;
    }

    public boolean getLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
