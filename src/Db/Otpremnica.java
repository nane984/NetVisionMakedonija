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
@Table(name = "otpremnica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otpremnica.findAll", query = "SELECT o FROM Otpremnica o")
    , @NamedQuery(name = "Otpremnica.findByBrotpremnice", query = "SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brotpremnice")
    , @NamedQuery(name = "Otpremnica.findByDatumvreme", query = "SELECT o FROM Otpremnica o WHERE o.datumvreme = :datumvreme")
    , @NamedQuery(name = "Otpremnica.findByKolicinaostvarenam3", query = "SELECT o FROM Otpremnica o WHERE o.kolicinaostvarenam3 = :kolicinaostvarenam3")
    , @NamedQuery(name = "Otpremnica.findByKolicinazadatam3", query = "SELECT o FROM Otpremnica o WHERE o.kolicinazadatam3 = :kolicinazadatam3")
    , @NamedQuery(name = "Otpremnica.findByStandbetona", query = "SELECT o FROM Otpremnica o WHERE o.standbetona = :standbetona")
    , @NamedQuery(name = "Otpremnica.findByKonzbetona", query = "SELECT o FROM Otpremnica o WHERE o.konzbetona = :konzbetona")
    , @NamedQuery(name = "Otpremnica.findBySpecbetona", query = "SELECT o FROM Otpremnica o WHERE o.specbetona = :specbetona")
    , @NamedQuery(name = "Otpremnica.findByAditiva1", query = "SELECT o FROM Otpremnica o WHERE o.aditiva1 = :aditiva1")
    , @NamedQuery(name = "Otpremnica.findByAditiva2", query = "SELECT o FROM Otpremnica o WHERE o.aditiva2 = :aditiva2")
    , @NamedQuery(name = "Otpremnica.findByVrstacementa", query = "SELECT o FROM Otpremnica o WHERE o.vrstacementa = :vrstacementa")
    , @NamedQuery(name = "Otpremnica.findByReceptura", query = "SELECT o FROM Otpremnica o WHERE o.receptura = :receptura")
    , @NamedQuery(name = "Otpremnica.findByMasinista", query = "SELECT o FROM Otpremnica o WHERE o.masinista = :masinista")
    , @NamedQuery(name = "Otpremnica.findByKupac", query = "SELECT o FROM Otpremnica o WHERE o.kupac = :kupac")
    , @NamedQuery(name = "Otpremnica.findByGradiliste", query = "SELECT o FROM Otpremnica o WHERE o.gradiliste = :gradiliste")
    , @NamedQuery(name = "Otpremnica.findByVozac", query = "SELECT o FROM Otpremnica o WHERE o.vozac = :vozac")
    , @NamedQuery(name = "Otpremnica.findByRegtablice", query = "SELECT o FROM Otpremnica o WHERE o.regtablice = :regtablice")
    , @NamedQuery(name = "Otpremnica.findByK1m3", query = "SELECT o FROM Otpremnica o WHERE o.k1m3 = :k1m3")
    , @NamedQuery(name = "Otpremnica.findByK2m3", query = "SELECT o FROM Otpremnica o WHERE o.k2m3 = :k2m3")
    , @NamedQuery(name = "Otpremnica.findByK3m3", query = "SELECT o FROM Otpremnica o WHERE o.k3m3 = :k3m3")
    , @NamedQuery(name = "Otpremnica.findByK4m3", query = "SELECT o FROM Otpremnica o WHERE o.k4m3 = :k4m3")
    , @NamedQuery(name = "Otpremnica.findByK5m3", query = "SELECT o FROM Otpremnica o WHERE o.k5m3 = :k5m3")
    , @NamedQuery(name = "Otpremnica.findByK6m3", query = "SELECT o FROM Otpremnica o WHERE o.k6m3 = :k6m3")
    , @NamedQuery(name = "Otpremnica.findByK7m3", query = "SELECT o FROM Otpremnica o WHERE o.k7m3 = :k7m3")
    , @NamedQuery(name = "Otpremnica.findByK8m3", query = "SELECT o FROM Otpremnica o WHERE o.k8m3 = :k8m3")
    , @NamedQuery(name = "Otpremnica.findByK9m3", query = "SELECT o FROM Otpremnica o WHERE o.k9m3 = :k9m3")
    , @NamedQuery(name = "Otpremnica.findByK10m3", query = "SELECT o FROM Otpremnica o WHERE o.k10m3 = :k10m3")
    , @NamedQuery(name = "Otpremnica.findByNapomena", query = "SELECT o FROM Otpremnica o WHERE o.napomena = :napomena")
    , @NamedQuery(name = "Otpremnica.findBySadrzaja1", query = "SELECT o FROM Otpremnica o WHERE o.sadrzaja1 = :sadrzaja1")
    , @NamedQuery(name = "Otpremnica.findBySadrzaja2", query = "SELECT o FROM Otpremnica o WHERE o.sadrzaja2 = :sadrzaja2")
    , @NamedQuery(name = "Otpremnica.findByK11m3", query = "SELECT o FROM Otpremnica o WHERE o.k11m3 = :k11m3")
    , @NamedQuery(name = "Otpremnica.findByK12m3", query = "SELECT o FROM Otpremnica o WHERE o.k12m3 = :k12m3")
    , @NamedQuery(name = "Otpremnica.findByAditiva3", query = "SELECT o FROM Otpremnica o WHERE o.aditiva3 = :aditiva3")
    , @NamedQuery(name = "Otpremnica.findByVrstacementa2", query = "SELECT o FROM Otpremnica o WHERE o.vrstacementa2 = :vrstacementa2")
    , @NamedQuery(name = "Otpremnica.findByVrstafilera", query = "SELECT o FROM Otpremnica o WHERE o.vrstafilera = :vrstafilera")
    , @NamedQuery(name = "Otpremnica.findBySertifikat", query = "SELECT o FROM Otpremnica o WHERE o.sertifikat = :sertifikat")
    , @NamedQuery(name = "Otpremnica.findByZrnomax", query = "SELECT o FROM Otpremnica o WHERE o.zrnomax = :zrnomax")
    , @NamedQuery(name = "Otpremnica.findBySvojstva", query = "SELECT o FROM Otpremnica o WHERE o.svojstva = :svojstva")})
public class Otpremnica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "brotpremnice")
    private Integer brotpremnice;
    @Column(name = "datumvreme")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumvreme;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kolicinaostvarenam3")
    private Float kolicinaostvarenam3;
    @Column(name = "kolicinazadatam3")
    private Float kolicinazadatam3;
    @Column(name = "standbetona")
    private String standbetona;
    @Column(name = "konzbetona")
    private String konzbetona;
    @Column(name = "specbetona")
    private String specbetona;
    @Column(name = "aditiva1")
    private String aditiva1;
    @Column(name = "aditiva2")
    private String aditiva2;
    @Column(name = "vrstacementa")
    private String vrstacementa;
    @Column(name = "receptura")
    private String receptura;
    @Column(name = "masinista")
    private String masinista;
    @Column(name = "kupac")
    private String kupac;
    @Column(name = "gradiliste")
    private String gradiliste;
    @Column(name = "vozac")
    private String vozac;
    @Column(name = "regtablice")
    private String regtablice;
    @Column(name = "k1m3")
    private Float k1m3;
    @Column(name = "k2m3")
    private Float k2m3;
    @Column(name = "k3m3")
    private Float k3m3;
    @Column(name = "k4m3")
    private Float k4m3;
    @Column(name = "k5m3")
    private Float k5m3;
    @Column(name = "k6m3")
    private Float k6m3;
    @Column(name = "k7m3")
    private Float k7m3;
    @Column(name = "k8m3")
    private Float k8m3;
    @Column(name = "k9m3")
    private Float k9m3;
    @Column(name = "k10m3")
    private Float k10m3;
    @Column(name = "napomena")
    private String napomena;
    @Column(name = "sadrzaja1")
    private Float sadrzaja1;
    @Column(name = "sadrzaja2")
    private Float sadrzaja2;
    @Column(name = "k11m3")
    private Float k11m3;
    @Column(name = "k12m3")
    private Float k12m3;
    @Column(name = "aditiva3")
    private String aditiva3;
    @Column(name = "vrstacementa2")
    private String vrstacementa2;
    @Column(name = "vrstafilera")
    private String vrstafilera;
    @Column(name = "sertifikat")
    private String sertifikat;
    @Column(name = "zrnomax")
    private String zrnomax;
    @Column(name = "svojstva")
    private String svojstva;

    public Otpremnica() {
    }

    public Otpremnica(Integer brotpremnice) {
        this.brotpremnice = brotpremnice;
    }

    public Integer getBrotpremnice() {
        return brotpremnice;
    }

    public void setBrotpremnice(Integer brotpremnice) {
        this.brotpremnice = brotpremnice;
    }

    public Date getDatumvreme() {
        return datumvreme;
    }

    public void setDatumvreme(Date datumvreme) {
        this.datumvreme = datumvreme;
    }

    public Float getKolicinaostvarenam3() {
        return kolicinaostvarenam3;
    }

    public void setKolicinaostvarenam3(Float kolicinaostvarenam3) {
        this.kolicinaostvarenam3 = kolicinaostvarenam3;
    }

    public Float getKolicinazadatam3() {
        return kolicinazadatam3;
    }

    public void setKolicinazadatam3(Float kolicinazadatam3) {
        this.kolicinazadatam3 = kolicinazadatam3;
    }

    public String getStandbetona() {
        return standbetona;
    }

    public void setStandbetona(String standbetona) {
        this.standbetona = standbetona;
    }

    public String getKonzbetona() {
        return konzbetona;
    }

    public void setKonzbetona(String konzbetona) {
        this.konzbetona = konzbetona;
    }

    public String getSpecbetona() {
        return specbetona;
    }

    public void setSpecbetona(String specbetona) {
        this.specbetona = specbetona;
    }

    public String getAditiva1() {
        return aditiva1;
    }

    public void setAditiva1(String aditiva1) {
        this.aditiva1 = aditiva1;
    }

    public String getAditiva2() {
        return aditiva2;
    }

    public void setAditiva2(String aditiva2) {
        this.aditiva2 = aditiva2;
    }

    public String getVrstacementa() {
        return vrstacementa;
    }

    public void setVrstacementa(String vrstacementa) {
        this.vrstacementa = vrstacementa;
    }

    public String getReceptura() {
        return receptura;
    }

    public void setReceptura(String receptura) {
        this.receptura = receptura;
    }

    public String getMasinista() {
        return masinista;
    }

    public void setMasinista(String masinista) {
        this.masinista = masinista;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
        this.kupac = kupac;
    }

    public String getGradiliste() {
        return gradiliste;
    }

    public void setGradiliste(String gradiliste) {
        this.gradiliste = gradiliste;
    }

    public String getVozac() {
        return vozac;
    }

    public void setVozac(String vozac) {
        this.vozac = vozac;
    }

    public String getRegtablice() {
        return regtablice;
    }

    public void setRegtablice(String regtablice) {
        this.regtablice = regtablice;
    }

    public Float getK1m3() {
        return k1m3;
    }

    public void setK1m3(Float k1m3) {
        this.k1m3 = k1m3;
    }

    public Float getK2m3() {
        return k2m3;
    }

    public void setK2m3(Float k2m3) {
        this.k2m3 = k2m3;
    }

    public Float getK3m3() {
        return k3m3;
    }

    public void setK3m3(Float k3m3) {
        this.k3m3 = k3m3;
    }

    public Float getK4m3() {
        return k4m3;
    }

    public void setK4m3(Float k4m3) {
        this.k4m3 = k4m3;
    }

    public Float getK5m3() {
        return k5m3;
    }

    public void setK5m3(Float k5m3) {
        this.k5m3 = k5m3;
    }

    public Float getK6m3() {
        return k6m3;
    }

    public void setK6m3(Float k6m3) {
        this.k6m3 = k6m3;
    }

    public Float getK7m3() {
        return k7m3;
    }

    public void setK7m3(Float k7m3) {
        this.k7m3 = k7m3;
    }

    public Float getK8m3() {
        return k8m3;
    }

    public void setK8m3(Float k8m3) {
        this.k8m3 = k8m3;
    }

    public Float getK9m3() {
        return k9m3;
    }

    public void setK9m3(Float k9m3) {
        this.k9m3 = k9m3;
    }

    public Float getK10m3() {
        return k10m3;
    }

    public void setK10m3(Float k10m3) {
        this.k10m3 = k10m3;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Float getSadrzaja1() {
        return sadrzaja1;
    }

    public void setSadrzaja1(Float sadrzaja1) {
        this.sadrzaja1 = sadrzaja1;
    }

    public Float getSadrzaja2() {
        return sadrzaja2;
    }

    public void setSadrzaja2(Float sadrzaja2) {
        this.sadrzaja2 = sadrzaja2;
    }

    public Float getK11m3() {
        return k11m3;
    }

    public void setK11m3(Float k11m3) {
        this.k11m3 = k11m3;
    }

    public Float getK12m3() {
        return k12m3;
    }

    public void setK12m3(Float k12m3) {
        this.k12m3 = k12m3;
    }

    public String getAditiva3() {
        return aditiva3;
    }

    public void setAditiva3(String aditiva3) {
        this.aditiva3 = aditiva3;
    }

    public String getVrstacementa2() {
        return vrstacementa2;
    }

    public void setVrstacementa2(String vrstacementa2) {
        this.vrstacementa2 = vrstacementa2;
    }

    public String getVrstafilera() {
        return vrstafilera;
    }

    public void setVrstafilera(String vrstafilera) {
        this.vrstafilera = vrstafilera;
    }

    public String getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(String sertifikat) {
        this.sertifikat = sertifikat;
    }

    public String getZrnomax() {
        return zrnomax;
    }

    public void setZrnomax(String zrnomax) {
        this.zrnomax = zrnomax;
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
        hash += (brotpremnice != null ? brotpremnice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otpremnica)) {
            return false;
        }
        Otpremnica other = (Otpremnica) object;
        if ((this.brotpremnice == null && other.brotpremnice != null) || (this.brotpremnice != null && !this.brotpremnice.equals(other.brotpremnice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Db.Otpremnica[ brotpremnice=" + brotpremnice + " ]";
    }
    
}
