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
@Table(name = "vrstacementa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vrstacementa.findAll", query = "SELECT v FROM Vrstacementa v")
    , @NamedQuery(name = "Vrstacementa.findById", query = "SELECT v FROM Vrstacementa v WHERE v.id = :id")
    , @NamedQuery(name = "Vrstacementa.findByVrstacem", query = "SELECT v FROM Vrstacementa v WHERE v.vrstacem = :vrstacem")})
public class Vrstacementa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "vrstacem")
    private String vrstacem;

    public Vrstacementa() {
    }

    public Vrstacementa(Integer id) {
        this.id = id;
    }

    public Vrstacementa(Integer id, String vrstacem) {
        this.id = id;
        this.vrstacem = vrstacem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVrstacem() {
        return vrstacem;
    }

    public void setVrstacem(String vrstacem) {
        this.vrstacem = vrstacem;
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
        if (!(object instanceof Vrstacementa)) {
            return false;
        }
        Vrstacementa other = (Vrstacementa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Vrstacementa[ id=" + id + " ]";
    }
    
}
