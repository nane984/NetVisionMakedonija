/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Branko
 */
@Entity
@Table(name = "rucnirad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rucnirad.findAll", query = "SELECT r FROM Rucnirad r")
    , @NamedQuery(name = "Rucnirad.findById", query = "SELECT r FROM Rucnirad r WHERE r.id = :id")
    , @NamedQuery(name = "Rucnirad.findByDatum", query = "SELECT r FROM Rucnirad r WHERE r.datum = :datum")
    , @NamedQuery(name = "Rucnirad.findByOpis", query = "SELECT r FROM Rucnirad r WHERE r.opis = :opis")
    , @NamedQuery(name = "Rucnirad.findByVrednost", query = "SELECT r FROM Rucnirad r WHERE r.vrednost = :vrednost")})
public class Rucnirad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Basic(optional = false)
    @Column(name = "opis")
    private String opis;
    @Basic(optional = false)
    @Column(name = "vrednost")
    private double vrednost;

    public Rucnirad() {
    }

    public Rucnirad(Long id) {
        this.id = id;
    }

    public Rucnirad(Long id, Date datum, String opis, double vrednost) {
        this.id = id;
        this.datum = datum;
        this.opis = opis;
        this.vrednost = vrednost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getVrednost() {
        return vrednost;
    }

    public void setVrednost(double vrednost) {
        this.vrednost = vrednost;
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
        if (!(object instanceof Rucnirad)) {
            return false;
        }
        Rucnirad other = (Rucnirad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Rucnirad[ id=" + id + " ]";
    }
    
}
