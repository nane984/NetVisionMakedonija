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
@Table(name = "aditiv1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aditiv1.findAll", query = "SELECT a FROM Aditiv1 a")
    , @NamedQuery(name = "Aditiv1.findById", query = "SELECT a FROM Aditiv1 a WHERE a.id = :id")
    , @NamedQuery(name = "Aditiv1.findByText", query = "SELECT a FROM Aditiv1 a WHERE a.text = :text")})
public class Aditiv1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "text")
    private String text;

    public Aditiv1() {
    }

    public Aditiv1(Long id) {
        this.id = id;
    }

    public Aditiv1(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Aditiv1)) {
            return false;
        }
        Aditiv1 other = (Aditiv1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Aditiv1[ id=" + id + " ]";
    }
    
}
