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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Branko
 */
@Entity
@Table(name = "vozackamion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vozackamion.findAll", query = "SELECT v FROM Vozackamion v")
    , @NamedQuery(name = "Vozackamion.findById", query = "SELECT v FROM Vozackamion v WHERE v.id = :id")})
public class Vozackamion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "tablice", referencedColumnName = "idtablice")
    @ManyToOne
    private Regtablice tablice;
    @JoinColumn(name = "vozac", referencedColumnName = "idvozaci")
    @ManyToOne
    private Vozaci vozac;

    public Vozackamion() {
    }

    public Vozackamion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Regtablice getTablice() {
        return tablice;
    }

    public void setTablice(Regtablice tablice) {
        this.tablice = tablice;
    }

    public Vozaci getVozac() {
        return vozac;
    }

    public void setVozac(Vozaci vozac) {
        this.vozac = vozac;
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
        if (!(object instanceof Vozackamion)) {
            return false;
        }
        Vozackamion other = (Vozackamion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Vozackamion[ id=" + id + " ]";
    }
    
}
