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
@Table(name = "sertifikat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sertifikat.findAll", query = "SELECT s FROM Sertifikat s")
    , @NamedQuery(name = "Sertifikat.findById", query = "SELECT s FROM Sertifikat s WHERE s.id = :id")
    , @NamedQuery(name = "Sertifikat.findBySertifikat", query = "SELECT s FROM Sertifikat s WHERE s.sertifikat = :sertifikat")})
public class Sertifikat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "sertifikat")
    private String sertifikat;

    public Sertifikat() {
    }

    public Sertifikat(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
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
        if (!(object instanceof Sertifikat)) {
            return false;
        }
        Sertifikat other = (Sertifikat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Sertifikat[ id=" + id + " ]";
    }
    
}
