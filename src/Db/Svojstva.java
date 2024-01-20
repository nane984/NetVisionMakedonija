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
@Table(name = "svojstva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Svojstva.findAll", query = "SELECT s FROM Svojstva s")
    , @NamedQuery(name = "Svojstva.findById", query = "SELECT s FROM Svojstva s WHERE s.id = :id")
    , @NamedQuery(name = "Svojstva.findBySvojstva", query = "SELECT s FROM Svojstva s WHERE s.svojstva = :svojstva")})
public class Svojstva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "svojstva")
    private String svojstva;

    public Svojstva() {
    }

    public Svojstva(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSvojstva() {
        return svojstva;
    }

    public void setSvojstva(String svojstva) {
        this.svojstva = svojstva;
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
        if (!(object instanceof Svojstva)) {
            return false;
        }
        Svojstva other = (Svojstva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Svojstva[ id=" + id + " ]";
    }
    
}
