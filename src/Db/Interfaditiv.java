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
@Table(name = "interfaditiv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Interfaditiv.findAll", query = "SELECT i FROM Interfaditiv i")
    , @NamedQuery(name = "Interfaditiv.findById", query = "SELECT i FROM Interfaditiv i WHERE i.id = :id")
    , @NamedQuery(name = "Interfaditiv.findByBr", query = "SELECT i FROM Interfaditiv i WHERE i.br = :br")
    , @NamedQuery(name = "Interfaditiv.findByText", query = "SELECT i FROM Interfaditiv i WHERE i.text = :text")})
public class Interfaditiv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "br")
    private Integer br;
    @Column(name = "text")
    private String text;

    public Interfaditiv() {
    }

    public Interfaditiv(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBr() {
        return br;
    }

    public void setBr(Integer br) {
        this.br = br;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        if (!(object instanceof Interfaditiv)) {
            return false;
        }
        Interfaditiv other = (Interfaditiv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Interfaditiv[ id=" + id + " ]";
    }
    
}
