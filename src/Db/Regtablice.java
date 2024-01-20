/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Branko
 */
@Entity
@Table(name = "regtablice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regtablice.findAll", query = "SELECT r FROM Regtablice r")
    , @NamedQuery(name = "Regtablice.findByIdtablice", query = "SELECT r FROM Regtablice r WHERE r.idtablice = :idtablice")
    , @NamedQuery(name = "Regtablice.findByTablice", query = "SELECT r FROM Regtablice r WHERE r.tablice = :tablice")})
public class Regtablice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtablice")
    private Integer idtablice;
    @Column(name = "tablice")
    private String tablice;
    @OneToMany(mappedBy = "tablice")
    private Collection<Vozackamion> vozackamionCollection;

    public Regtablice() {
    }

    public Regtablice(Integer idtablice) {
        this.idtablice = idtablice;
    }

    public Integer getIdtablice() {
        return idtablice;
    }

    public void setIdtablice(Integer idtablice) {
        this.idtablice = idtablice;
    }

    public String getTablice() {
        return tablice;
    }

    public void setTablice(String tablice) {
        this.tablice = tablice;
    }

    @XmlTransient
    public Collection<Vozackamion> getVozackamionCollection() {
        return vozackamionCollection;
    }

    public void setVozackamionCollection(Collection<Vozackamion> vozackamionCollection) {
        this.vozackamionCollection = vozackamionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtablice != null ? idtablice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regtablice)) {
            return false;
        }
        Regtablice other = (Regtablice) object;
        if ((this.idtablice == null && other.idtablice != null) || (this.idtablice != null && !this.idtablice.equals(other.idtablice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Regtablice[ idtablice=" + idtablice + " ]";
    }
    
}
