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
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k")
    , @NamedQuery(name = "Korisnik.findByIdkorisnik", query = "SELECT k FROM Korisnik k WHERE k.idkorisnik = :idkorisnik")
    , @NamedQuery(name = "Korisnik.findByAdmin", query = "SELECT k FROM Korisnik k WHERE k.admin = :admin")
    , @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime")
    , @NamedQuery(name = "Korisnik.findByL1", query = "SELECT k FROM Korisnik k WHERE k.l1 = :l1")
    , @NamedQuery(name = "Korisnik.findByL2", query = "SELECT k FROM Korisnik k WHERE k.l2 = :l2")
    , @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka")
    , @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime")
    , @NamedQuery(name = "Korisnik.findByUsername", query = "SELECT k FROM Korisnik k WHERE k.username = :username")})
public class Korisnik implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idkorisnik")
    private Integer idkorisnik;
    @Column(name = "admin")
    private Integer admin;
    @Column(name = "ime")
    private String ime;
    @Column(name = "l1")
    private Integer l1;
    @Column(name = "l2")
    private Integer l2;
    @Column(name = "lozinka")
    private String lozinka;
    @Column(name = "prezime")
    private String prezime;
    @Column(name = "username")
    private String username;

    public Korisnik() {
    }

    public Korisnik(Integer idkorisnik) {
        this.idkorisnik = idkorisnik;
    }

    public Integer getIdkorisnik() {
        return idkorisnik;
    }

    public void setIdkorisnik(Integer idkorisnik) {
        this.idkorisnik = idkorisnik;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getL1() {
        return l1;
    }

    public void setL1(Integer l1) {
        this.l1 = l1;
    }

    public Integer getL2() {
        return l2;
    }

    public void setL2(Integer l2) {
        this.l2 = l2;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idkorisnik != null ? idkorisnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.idkorisnik == null && other.idkorisnik != null) || (this.idkorisnik != null && !this.idkorisnik.equals(other.idkorisnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Korisnik[ idkorisnik=" + idkorisnik + " ]";
    }
    
}
