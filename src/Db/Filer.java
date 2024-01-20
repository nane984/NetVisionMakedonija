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
@Table(name = "filer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Filer.findAll", query = "SELECT f FROM Filer f")
    , @NamedQuery(name = "Filer.findById", query = "SELECT f FROM Filer f WHERE f.id = :id")
    , @NamedQuery(name = "Filer.findByFiler", query = "SELECT f FROM Filer f WHERE f.filer = :filer")})
public class Filer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "filer")
    private String filer;

    public Filer() {
    }

    public Filer(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFiler() {
        return filer;
    }

    public void setFiler(String filer) {
        this.filer = filer;
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
        if (!(object instanceof Filer)) {
            return false;
        }
        Filer other = (Filer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Filer[ id=" + id + " ]";
    }
    
}
