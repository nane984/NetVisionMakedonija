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
@Table(name = "masinista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Masinista.findAll", query = "SELECT m FROM Masinista m")
    , @NamedQuery(name = "Masinista.findByIdmasinista", query = "SELECT m FROM Masinista m WHERE m.idmasinista = :idmasinista")
    , @NamedQuery(name = "Masinista.findByImePrezime", query = "SELECT m FROM Masinista m WHERE m.imePrezime = :imePrezime")})
public class Masinista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmasinista")
    private Integer idmasinista;
    @Column(name = "ime_prezime")
    private String imePrezime;

    public Masinista() {
    }

    public Masinista(Integer idmasinista) {
        this.idmasinista = idmasinista;
    }

    public Integer getIdmasinista() {
        return idmasinista;
    }

    public void setIdmasinista(Integer idmasinista) {
        this.idmasinista = idmasinista;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmasinista != null ? idmasinista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Masinista)) {
            return false;
        }
        Masinista other = (Masinista) object;
        if ((this.idmasinista == null && other.idmasinista != null) || (this.idmasinista != null && !this.idmasinista.equals(other.idmasinista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Masinista[ idmasinista=" + idmasinista + " ]";
    }
    
}
