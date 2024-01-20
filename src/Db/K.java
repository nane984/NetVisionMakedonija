/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Branko
 */
@Entity
@Table(name = "k")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "K.findAll", query = "SELECT k FROM K k")
    , @NamedQuery(name = "K.findByIdkomponenta", query = "SELECT k FROM K k WHERE k.idkomponenta = :idkomponenta")
    , @NamedQuery(name = "K.findByBrK", query = "SELECT k FROM K k WHERE k.brK = :brK")
    , @NamedQuery(name = "K.findByDecformat", query = "SELECT k FROM K k WHERE k.decformat = :decformat")
    , @NamedQuery(name = "K.findByIfcheck", query = "SELECT k FROM K k WHERE k.ifcheck = :ifcheck")
    , @NamedQuery(name = "K.findByKomponenta", query = "SELECT k FROM K k WHERE k.komponenta = :komponenta")})
public class K implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkomponenta")
    private Integer idkomponenta;
    @Column(name = "br_k")
    private Integer brK;
    @Column(name = "decformat")
    private Integer decformat;
    @Column(name = "ifcheck")
    private Integer ifcheck;
    @Column(name = "komponenta")
    private String komponenta;

    public K() {
    }

    public K(Integer idkomponenta) {
        this.idkomponenta = idkomponenta;
    }

    public Integer getIdkomponenta() {
        return idkomponenta;
    }

    public void setIdkomponenta(Integer idkomponenta) {
        this.idkomponenta = idkomponenta;
    }

    public Integer getBrK() {
        return brK;
    }

    public void setBrK(Integer brK) {
        this.brK = brK;
    }

    public Integer getDecformat() {
        return decformat;
    }

    public void setDecformat(Integer decformat) {
        this.decformat = decformat;
    }

    public Integer getIfcheck() {
        return ifcheck;
    }

    public void setIfcheck(Integer ifcheck) {
        this.ifcheck = ifcheck;
    }

    public String getKomponenta() {
        return komponenta;
    }

    public void setKomponenta(String komponenta) {
        this.komponenta = komponenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkomponenta != null ? idkomponenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof K)) {
            return false;
        }
        K other = (K) object;
        if ((this.idkomponenta == null && other.idkomponenta != null) || (this.idkomponenta != null && !this.idkomponenta.equals(other.idkomponenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.K[ idkomponenta=" + idkomponenta + " ]";
    }
    
}
