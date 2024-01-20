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
@Table(name = "vozaci")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vozaci.findAll", query = "SELECT v FROM Vozaci v")
    , @NamedQuery(name = "Vozaci.findByIdvozaci", query = "SELECT v FROM Vozaci v WHERE v.idvozaci = :idvozaci")
    , @NamedQuery(name = "Vozaci.findByImeprezimevozaci", query = "SELECT v FROM Vozaci v WHERE v.imeprezimevozaci = :imeprezimevozaci")})
public class Vozaci implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvozaci")
    private Integer idvozaci;
    @Column(name = "imeprezimevozaci")
    private String imeprezimevozaci;
    @OneToMany(mappedBy = "vozac")
    private Collection<Vozackamion> vozackamionCollection;

    public Vozaci() {
    }

    public Vozaci(Integer idvozaci) {
        this.idvozaci = idvozaci;
    }

    public Integer getIdvozaci() {
        return idvozaci;
    }

    public void setIdvozaci(Integer idvozaci) {
        this.idvozaci = idvozaci;
    }

    public String getImeprezimevozaci() {
        return imeprezimevozaci;
    }

    public void setImeprezimevozaci(String imeprezimevozaci) {
        this.imeprezimevozaci = imeprezimevozaci;
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
        hash += (idvozaci != null ? idvozaci.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vozaci)) {
            return false;
        }
        Vozaci other = (Vozaci) object;
        if ((this.idvozaci == null && other.idvozaci != null) || (this.idvozaci != null && !this.idvozaci.equals(other.idvozaci))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Vozaci[ idvozaci=" + idvozaci + " ]";
    }
    
}
