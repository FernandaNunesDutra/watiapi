/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fernanda
 */
@Embeddable
public class TipUserPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tip")
    private int idTip;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_user")
    private long idUser;

    public TipUserPK() {
    }

    public TipUserPK(int idTip, long idUser) {
        this.idTip = idTip;
        this.idUser = idUser;
    }

    public int getIdTip() {
        return idTip;
    }

    public void setIdTip(int idTip) {
        this.idTip = idTip;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
    
}
