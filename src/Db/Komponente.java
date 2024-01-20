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
@Table(name = "komponente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Komponente.findAll", query = "SELECT k FROM Komponente k")
    , @NamedQuery(name = "Komponente.findByRbkomp", query = "SELECT k FROM Komponente k WHERE k.rbkomp = :rbkomp")
    , @NamedQuery(name = "Komponente.findByBrSarze", query = "SELECT k FROM Komponente k WHERE k.brSarze = :brSarze")
    , @NamedQuery(name = "Komponente.findByDatumVreme", query = "SELECT k FROM Komponente k WHERE k.datumVreme = :datumVreme")
    , @NamedQuery(name = "Komponente.findByK10Ostv", query = "SELECT k FROM Komponente k WHERE k.k10Ostv = :k10Ostv")
    , @NamedQuery(name = "Komponente.findByK10Zad", query = "SELECT k FROM Komponente k WHERE k.k10Zad = :k10Zad")
    , @NamedQuery(name = "Komponente.findByK1Ostv", query = "SELECT k FROM Komponente k WHERE k.k1Ostv = :k1Ostv")
    , @NamedQuery(name = "Komponente.findByK1Zad", query = "SELECT k FROM Komponente k WHERE k.k1Zad = :k1Zad")
    , @NamedQuery(name = "Komponente.findByK2Ostv", query = "SELECT k FROM Komponente k WHERE k.k2Ostv = :k2Ostv")
    , @NamedQuery(name = "Komponente.findByK2Zad", query = "SELECT k FROM Komponente k WHERE k.k2Zad = :k2Zad")
    , @NamedQuery(name = "Komponente.findByK3Ostv", query = "SELECT k FROM Komponente k WHERE k.k3Ostv = :k3Ostv")
    , @NamedQuery(name = "Komponente.findByK3Zad", query = "SELECT k FROM Komponente k WHERE k.k3Zad = :k3Zad")
    , @NamedQuery(name = "Komponente.findByK4Ostv", query = "SELECT k FROM Komponente k WHERE k.k4Ostv = :k4Ostv")
    , @NamedQuery(name = "Komponente.findByK4Zad", query = "SELECT k FROM Komponente k WHERE k.k4Zad = :k4Zad")
    , @NamedQuery(name = "Komponente.findByK5Ostv", query = "SELECT k FROM Komponente k WHERE k.k5Ostv = :k5Ostv")
    , @NamedQuery(name = "Komponente.findByK5Zad", query = "SELECT k FROM Komponente k WHERE k.k5Zad = :k5Zad")
    , @NamedQuery(name = "Komponente.findByK6Ostv", query = "SELECT k FROM Komponente k WHERE k.k6Ostv = :k6Ostv")
    , @NamedQuery(name = "Komponente.findByK6Zad", query = "SELECT k FROM Komponente k WHERE k.k6Zad = :k6Zad")
    , @NamedQuery(name = "Komponente.findByK7Ostv", query = "SELECT k FROM Komponente k WHERE k.k7Ostv = :k7Ostv")
    , @NamedQuery(name = "Komponente.findByK7Zad", query = "SELECT k FROM Komponente k WHERE k.k7Zad = :k7Zad")
    , @NamedQuery(name = "Komponente.findByK8Ostv", query = "SELECT k FROM Komponente k WHERE k.k8Ostv = :k8Ostv")
    , @NamedQuery(name = "Komponente.findByK8Zad", query = "SELECT k FROM Komponente k WHERE k.k8Zad = :k8Zad")
    , @NamedQuery(name = "Komponente.findByK9Ostv", query = "SELECT k FROM Komponente k WHERE k.k9Ostv = :k9Ostv")
    , @NamedQuery(name = "Komponente.findByK9Zad", query = "SELECT k FROM Komponente k WHERE k.k9Zad = :k9Zad")
    , @NamedQuery(name = "Komponente.findByKolicinaostvarenakg", query = "SELECT k FROM Komponente k WHERE k.kolicinaostvarenakg = :kolicinaostvarenakg")
    , @NamedQuery(name = "Komponente.findByKolicinazadatakg", query = "SELECT k FROM Komponente k WHERE k.kolicinazadatakg = :kolicinazadatakg")
    , @NamedQuery(name = "Komponente.findByOstvarenoprocent", query = "SELECT k FROM Komponente k WHERE k.ostvarenoprocent = :ostvarenoprocent")
    , @NamedQuery(name = "Komponente.findByOtpremnicaRbr", query = "SELECT k FROM Komponente k WHERE k.otpremnicaRbr = :otpremnicaRbr")
    , @NamedQuery(name = "Komponente.findByKolicinabetona", query = "SELECT k FROM Komponente k WHERE k.kolicinabetona = :kolicinabetona")
    , @NamedQuery(name = "Komponente.findByVlagaf1", query = "SELECT k FROM Komponente k WHERE k.vlagaf1 = :vlagaf1")
    , @NamedQuery(name = "Komponente.findByVlagaf2", query = "SELECT k FROM Komponente k WHERE k.vlagaf2 = :vlagaf2")
    , @NamedQuery(name = "Komponente.findByVlagaf3", query = "SELECT k FROM Komponente k WHERE k.vlagaf3 = :vlagaf3")
    , @NamedQuery(name = "Komponente.findByVlagaf4", query = "SELECT k FROM Komponente k WHERE k.vlagaf4 = :vlagaf4")
    , @NamedQuery(name = "Komponente.findByKorigovanof1", query = "SELECT k FROM Komponente k WHERE k.korigovanof1 = :korigovanof1")
    , @NamedQuery(name = "Komponente.findByKorigovanof2", query = "SELECT k FROM Komponente k WHERE k.korigovanof2 = :korigovanof2")
    , @NamedQuery(name = "Komponente.findByKorigovanof3", query = "SELECT k FROM Komponente k WHERE k.korigovanof3 = :korigovanof3")
    , @NamedQuery(name = "Komponente.findByKorigovanof4", query = "SELECT k FROM Komponente k WHERE k.korigovanof4 = :korigovanof4")
    , @NamedQuery(name = "Komponente.findByK11Ostv", query = "SELECT k FROM Komponente k WHERE k.k11Ostv = :k11Ostv")
    , @NamedQuery(name = "Komponente.findByK11Zad", query = "SELECT k FROM Komponente k WHERE k.k11Zad = :k11Zad")
    , @NamedQuery(name = "Komponente.findByK12Ostv", query = "SELECT k FROM Komponente k WHERE k.k12Ostv = :k12Ostv")
    , @NamedQuery(name = "Komponente.findByK12Zad", query = "SELECT k FROM Komponente k WHERE k.k12Zad = :k12Zad")})
public class Komponente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rbkomp")
    private Integer rbkomp;
    @Column(name = "br_sarze")
    private Integer brSarze;
    @Column(name = "datum_vreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumVreme;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "k10_ostv")
    private Float k10Ostv;
    @Column(name = "k10_zad")
    private Float k10Zad;
    @Column(name = "k1_ostv")
    private Float k1Ostv;
    @Column(name = "k1_zad")
    private Float k1Zad;
    @Column(name = "k2_ostv")
    private Float k2Ostv;
    @Column(name = "k2_zad")
    private Float k2Zad;
    @Column(name = "k3_ostv")
    private Float k3Ostv;
    @Column(name = "k3_zad")
    private Float k3Zad;
    @Column(name = "k4_ostv")
    private Float k4Ostv;
    @Column(name = "k4_zad")
    private Float k4Zad;
    @Column(name = "k5_ostv")
    private Float k5Ostv;
    @Column(name = "k5_zad")
    private Float k5Zad;
    @Column(name = "k6_ostv")
    private Float k6Ostv;
    @Column(name = "k6_zad")
    private Float k6Zad;
    @Column(name = "k7_ostv")
    private Float k7Ostv;
    @Column(name = "k7_zad")
    private Float k7Zad;
    @Column(name = "k8_ostv")
    private Float k8Ostv;
    @Column(name = "k8_zad")
    private Float k8Zad;
    @Column(name = "k9_ostv")
    private Float k9Ostv;
    @Column(name = "k9_zad")
    private Float k9Zad;
    @Column(name = "kolicinaostvarenakg")
    private Float kolicinaostvarenakg;
    @Column(name = "kolicinazadatakg")
    private Float kolicinazadatakg;
    @Column(name = "ostvarenoprocent")
    private Integer ostvarenoprocent;
    @Column(name = "otpremnica_rbr")
    private Integer otpremnicaRbr;
    @Column(name = "kolicinabetona")
    private Float kolicinabetona;
    @Column(name = "vlagaf1")
    private Float vlagaf1;
    @Column(name = "vlagaf2")
    private Float vlagaf2;
    @Column(name = "vlagaf3")
    private Float vlagaf3;
    @Column(name = "vlagaf4")
    private Float vlagaf4;
    @Column(name = "korigovanof1")
    private Float korigovanof1;
    @Column(name = "korigovanof2")
    private Float korigovanof2;
    @Column(name = "korigovanof3")
    private Float korigovanof3;
    @Column(name = "korigovanof4")
    private Float korigovanof4;
    @Column(name = "k11_ostv")
    private Float k11Ostv;
    @Column(name = "k11_zad")
    private Float k11Zad;
    @Column(name = "k12_ostv")
    private Float k12Ostv;
    @Column(name = "k12_zad")
    private Float k12Zad;

    public Komponente() {
    }

    public Komponente(Integer rbkomp) {
        this.rbkomp = rbkomp;
    }

    public Integer getRbkomp() {
        return rbkomp;
    }

    public void setRbkomp(Integer rbkomp) {
        this.rbkomp = rbkomp;
    }

    public Integer getBrSarze() {
        return brSarze;
    }

    public void setBrSarze(Integer brSarze) {
        this.brSarze = brSarze;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public Float getK10Ostv() {
        return k10Ostv;
    }

    public void setK10Ostv(Float k10Ostv) {
        this.k10Ostv = k10Ostv;
    }

    public Float getK10Zad() {
        return k10Zad;
    }

    public void setK10Zad(Float k10Zad) {
        this.k10Zad = k10Zad;
    }

    public Float getK1Ostv() {
        return k1Ostv;
    }

    public void setK1Ostv(Float k1Ostv) {
        this.k1Ostv = k1Ostv;
    }

    public Float getK1Zad() {
        return k1Zad;
    }

    public void setK1Zad(Float k1Zad) {
        this.k1Zad = k1Zad;
    }

    public Float getK2Ostv() {
        return k2Ostv;
    }

    public void setK2Ostv(Float k2Ostv) {
        this.k2Ostv = k2Ostv;
    }

    public Float getK2Zad() {
        return k2Zad;
    }

    public void setK2Zad(Float k2Zad) {
        this.k2Zad = k2Zad;
    }

    public Float getK3Ostv() {
        return k3Ostv;
    }

    public void setK3Ostv(Float k3Ostv) {
        this.k3Ostv = k3Ostv;
    }

    public Float getK3Zad() {
        return k3Zad;
    }

    public void setK3Zad(Float k3Zad) {
        this.k3Zad = k3Zad;
    }

    public Float getK4Ostv() {
        return k4Ostv;
    }

    public void setK4Ostv(Float k4Ostv) {
        this.k4Ostv = k4Ostv;
    }

    public Float getK4Zad() {
        return k4Zad;
    }

    public void setK4Zad(Float k4Zad) {
        this.k4Zad = k4Zad;
    }

    public Float getK5Ostv() {
        return k5Ostv;
    }

    public void setK5Ostv(Float k5Ostv) {
        this.k5Ostv = k5Ostv;
    }

    public Float getK5Zad() {
        return k5Zad;
    }

    public void setK5Zad(Float k5Zad) {
        this.k5Zad = k5Zad;
    }

    public Float getK6Ostv() {
        return k6Ostv;
    }

    public void setK6Ostv(Float k6Ostv) {
        this.k6Ostv = k6Ostv;
    }

    public Float getK6Zad() {
        return k6Zad;
    }

    public void setK6Zad(Float k6Zad) {
        this.k6Zad = k6Zad;
    }

    public Float getK7Ostv() {
        return k7Ostv;
    }

    public void setK7Ostv(Float k7Ostv) {
        this.k7Ostv = k7Ostv;
    }

    public Float getK7Zad() {
        return k7Zad;
    }

    public void setK7Zad(Float k7Zad) {
        this.k7Zad = k7Zad;
    }

    public Float getK8Ostv() {
        return k8Ostv;
    }

    public void setK8Ostv(Float k8Ostv) {
        this.k8Ostv = k8Ostv;
    }

    public Float getK8Zad() {
        return k8Zad;
    }

    public void setK8Zad(Float k8Zad) {
        this.k8Zad = k8Zad;
    }

    public Float getK9Ostv() {
        return k9Ostv;
    }

    public void setK9Ostv(Float k9Ostv) {
        this.k9Ostv = k9Ostv;
    }

    public Float getK9Zad() {
        return k9Zad;
    }

    public void setK9Zad(Float k9Zad) {
        this.k9Zad = k9Zad;
    }

    public Float getKolicinaostvarenakg() {
        return kolicinaostvarenakg;
    }

    public void setKolicinaostvarenakg(Float kolicinaostvarenakg) {
        this.kolicinaostvarenakg = kolicinaostvarenakg;
    }

    public Float getKolicinazadatakg() {
        return kolicinazadatakg;
    }

    public void setKolicinazadatakg(Float kolicinazadatakg) {
        this.kolicinazadatakg = kolicinazadatakg;
    }

    public Integer getOstvarenoprocent() {
        return ostvarenoprocent;
    }

    public void setOstvarenoprocent(Integer ostvarenoprocent) {
        this.ostvarenoprocent = ostvarenoprocent;
    }

    public Integer getOtpremnicaRbr() {
        return otpremnicaRbr;
    }

    public void setOtpremnicaRbr(Integer otpremnicaRbr) {
        this.otpremnicaRbr = otpremnicaRbr;
    }

    public Float getKolicinabetona() {
        return kolicinabetona;
    }

    public void setKolicinabetona(Float kolicinabetona) {
        this.kolicinabetona = kolicinabetona;
    }

    public Float getVlagaf1() {
        return vlagaf1;
    }

    public void setVlagaf1(Float vlagaf1) {
        this.vlagaf1 = vlagaf1;
    }

    public Float getVlagaf2() {
        return vlagaf2;
    }

    public void setVlagaf2(Float vlagaf2) {
        this.vlagaf2 = vlagaf2;
    }

    public Float getVlagaf3() {
        return vlagaf3;
    }

    public void setVlagaf3(Float vlagaf3) {
        this.vlagaf3 = vlagaf3;
    }

    public Float getVlagaf4() {
        return vlagaf4;
    }

    public void setVlagaf4(Float vlagaf4) {
        this.vlagaf4 = vlagaf4;
    }

    public Float getKorigovanof1() {
        return korigovanof1;
    }

    public void setKorigovanof1(Float korigovanof1) {
        this.korigovanof1 = korigovanof1;
    }

    public Float getKorigovanof2() {
        return korigovanof2;
    }

    public void setKorigovanof2(Float korigovanof2) {
        this.korigovanof2 = korigovanof2;
    }

    public Float getKorigovanof3() {
        return korigovanof3;
    }

    public void setKorigovanof3(Float korigovanof3) {
        this.korigovanof3 = korigovanof3;
    }

    public Float getKorigovanof4() {
        return korigovanof4;
    }

    public void setKorigovanof4(Float korigovanof4) {
        this.korigovanof4 = korigovanof4;
    }

    public Float getK11Ostv() {
        return k11Ostv;
    }

    public void setK11Ostv(Float k11Ostv) {
        this.k11Ostv = k11Ostv;
    }

    public Float getK11Zad() {
        return k11Zad;
    }

    public void setK11Zad(Float k11Zad) {
        this.k11Zad = k11Zad;
    }

    public Float getK12Ostv() {
        return k12Ostv;
    }

    public void setK12Ostv(Float k12Ostv) {
        this.k12Ostv = k12Ostv;
    }

    public Float getK12Zad() {
        return k12Zad;
    }

    public void setK12Zad(Float k12Zad) {
        this.k12Zad = k12Zad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rbkomp != null ? rbkomp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Komponente)) {
            return false;
        }
        Komponente other = (Komponente) object;
        if ((this.rbkomp == null && other.rbkomp != null) || (this.rbkomp != null && !this.rbkomp.equals(other.rbkomp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Komponente[ rbkomp=" + rbkomp + " ]";
    }
    
}
