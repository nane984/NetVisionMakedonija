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
@Table(name = "zrnomax")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zrnomax.findAll", query = "SELECT z FROM Zrnomax z")
    , @NamedQuery(name = "Zrnomax.findById", query = "SELECT z FROM Zrnomax z WHERE z.id = :id")
    , @NamedQuery(name = "Zrnomax.findByZrnomax", query = "SELECT z FROM Zrnomax z WHERE z.zrnomax = :zrnomax")})
public class Zrnomax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "zrnomax")
    private String zrnomax;

    public Zrnomax() {
    }

    public Zrnomax(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZrnomax() {
        return zrnomax;
    }

    public void setZrnomax(String zrnomax) {
        this.zrnomax = zrnomax;
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
        if (!(object instanceof Zrnomax)) {
            return false;
        }
        Zrnomax other = (Zrnomax) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Zrnomax[ id=" + id + " ]";
    }
    
}
