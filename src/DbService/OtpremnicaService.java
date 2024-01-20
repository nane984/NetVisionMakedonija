/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbService;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import javax.swing.JOptionPane;
import tools.NullString;

/**
 *
 * @author brasa
 */
public class OtpremnicaService {
    private final EntityManagerFactory emf;
    
    public OtpremnicaService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
    
    public List<Db.Otpremnica> getOtpremnice() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o ORDER BY o.brotpremnice ASC");

        List<Db.Otpremnica> result = q.getResultList();
        em.close();

        return result;
    }
    
    
    public List<Db.Otpremnica> getOtpremniceOrderReceptura() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o ORDER BY o.receptura ASC");

        List<Db.Otpremnica> result = q.getResultList();
        em.close();

        return result;
    }
    
    public Db.Otpremnica getOtpremnica(int brOtp){
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtpremnice" );
        q.setParameter("brOtpremnice", brOtp);
        
        Db.Otpremnica result = (Db.Otpremnica) q.getSingleResult();
        em.close();
        //em.flush();
        return result;
    }
    
    public List<Db.Otpremnica>  getOtpremnicaWhere(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup "
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl "
                + "AND o.masinista LIKE :mas AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        q.setParameter("brOtp",  brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
 
    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOd(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd  AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateDo(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
  
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdDo(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
    public Db.Otpremnica getOtpremnicaFromDatetime(Date datum){
        JOptionPane.showMessageDialog(null, "To je taj ");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o from Otpremnica o WHERE o.datumvreme >=  :datumvreme ORDER BY o.receptura ASC");
        q.setParameter("datumvreme", datum);
        
        Db.Otpremnica result = (Db.Otpremnica) q.getSingleResult();
        em.close();
        return result;
        
    }
    
    
    
    public List<Db.Otpremnica>  getOtpremnicaWhere(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup "
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl "
                + "AND o.masinista LIKE :mas AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
 
    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOd(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd  AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateDo(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
  
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdDo(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.receptura ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    public void addOtpremnica(int brOtpremnice, Date datum, float kolZadata, float kolOstvarena, float k1m3, float k2m3,
            float k3m3, float k4m3, float k5m3, float k6m3, float k7m3, float k8m3, float k9m3, float k10m3, float k11m3, float k12m3,
            String gradiliste, String masinista, String kupac, String vozac, String tablice, 
            String receptura, String cement, String cement2, String filer, String a1, String a2, String a3, String specBetona,
            String kozistencija, String hlorid, String sertifikat, String zrnomax, String svojstva) {

        Db.Otpremnica otpremnica = new Db.Otpremnica();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        otpremnica.setBrotpremnice(brOtpremnice);
        otpremnica.setKolicinazadatam3(kolZadata);
        otpremnica.setKolicinaostvarenam3(kolOstvarena);
        otpremnica.setDatumvreme(datum);
        
        otpremnica.setK1m3(k1m3);
        otpremnica.setK2m3(k2m3);
        otpremnica.setK3m3(k3m3);
        otpremnica.setK4m3(k4m3);
        otpremnica.setK5m3(k5m3);
        otpremnica.setK6m3(k6m3);
        otpremnica.setK7m3(k7m3);
        otpremnica.setK8m3(k8m3);
        otpremnica.setK9m3(k9m3);
        otpremnica.setK10m3(k10m3);
        otpremnica.setK11m3(k11m3);
        otpremnica.setK12m3(k12m3);
        
        otpremnica.setGradiliste(gradiliste);
        otpremnica.setMasinista(masinista);
        otpremnica.setKupac(kupac);
        otpremnica.setVozac(vozac);
        otpremnica.setRegtablice(tablice);
        otpremnica.setReceptura(receptura);
        
        otpremnica.setAditiva1(a1);
        otpremnica.setAditiva2(a2);
        otpremnica.setAditiva3(a3);
        otpremnica.setVrstacementa(cement);
        otpremnica.setVrstacementa2(cement2);
        otpremnica.setVrstafilera(filer);
        
        otpremnica.setSpecbetona(NullString.getString(specBetona));
        otpremnica.setKonzbetona(NullString.getString(kozistencija));
        otpremnica.setStandbetona(NullString.getString(hlorid));
        otpremnica.setSertifikat(sertifikat);
        otpremnica.setZrnomax(zrnomax);
        otpremnica.setSvojstva(svojstva);

        em.persist(otpremnica);
        em.getTransaction().commit();
//        em.flush();
        em.close();

        //JOptionPane.showMessageDialog(null, "Dodali ste Gradiliste");
    }
    
    public void addOtpremnica(int brOtpremnice, String gradiliste, String masinista, String kupac,
                                String vozac, String tablice, String receptura ,float kolZadata, float kolOstvarena) {

        Db.Otpremnica otpremnica = new Db.Otpremnica();
        
        Date dNow = new Date();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        otpremnica.setBrotpremnice(brOtpremnice);
        otpremnica.setGradiliste(gradiliste);
        otpremnica.setMasinista(masinista);
        otpremnica.setKupac(kupac);
        otpremnica.setVozac(vozac);
        otpremnica.setRegtablice(tablice);
        otpremnica.setReceptura(receptura);
        otpremnica.setKolicinazadatam3(kolZadata);
        otpremnica.setKolicinaostvarenam3(kolOstvarena);
        otpremnica.setDatumvreme(dNow);
        
        

        em.persist(otpremnica);
        em.getTransaction().commit();
//        em.flush();
        em.close();

        //JOptionPane.showMessageDialog(null, "Dodali ste Gradiliste");
    }
   
    
    ////////////////////////////////////////
    public List<Db.Otpremnica>  getOtpremnicaWhereOrderOtp(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        /*Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brOtpremnice = :brOtp AND o.kupac LIKE :kup "
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl "
                + "AND o.masinista LIKE :mas AND o.receptura LIKE :mbet ORDER BY o.brOtpremnice ASC");
        
        q.setParameter("brOtp",  brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("mbet", "%" + markaBetona + "%");
        */
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup ORDER BY o.brotpremnice ASC");
        
        q.setParameter("brOtp",  brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        
        return result;
    }
 
    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdOrderOtp(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd  AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateDoOrderOtp(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
  
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdDoOrderOtp(int brOtpremnice, String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.brotpremnice = :brOtp AND o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice.tablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        q.setParameter("brOtp", brOtpremnice);
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
    public Db.Otpremnica getOtpremnicaFromDatetimeOrderOtp(Date datum){
        JOptionPane.showMessageDialog(null, "To je taj ");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o from Otpremnica o WHERE o.datumvreme >=  :datumvreme ORDER BY o.brotpremnice ASC");
        q.setParameter("datumvreme", datum);
        
        Db.Otpremnica result = (Db.Otpremnica) q.getSingleResult();
        em.close();
        return result;
        
    }
    
    
    
    public List<Db.Otpremnica>  getOtpremnicaWhereOrderOtp(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
       
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup "
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl "
                + "AND o.masinista LIKE :mas AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
 
    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdOrderOtp(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd  AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    
    public List<Db.Otpremnica>  getOtpremnicaWhereDateDoOrderOtp(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }
    
  
    public List<Db.Otpremnica>  getOtpremnicaWhereDateOdDoOrderOtp(String kupac, String gradiliste, String vozac, String tablice, String masinista,
                                                    Date datumOd, Date datumDo, String markaBetona)
    {
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT o FROM Otpremnica o WHERE o.kupac LIKE :kup " 
                + "AND o.gradiliste LIKE :gradil AND o.vozac LIKE :voz AND o.regtablice LIKE :tabl " 
                + "AND o.masinista LIKE :mas AND o.datumvreme >= :datOd AND o.datumvreme <= :datDo AND o.receptura LIKE :mbet ORDER BY o.brotpremnice ASC");
        
        q.setParameter("kup", "%" + kupac + "%");
        q.setParameter("gradil", "%" + gradiliste + "%");
        q.setParameter("voz", "%" + vozac + "%");
        q.setParameter("tabl", "%" + tablice + "%");
        q.setParameter("mas", "%" + masinista + "%");
        q.setParameter("datOd", datumOd);
        q.setParameter("datDo", datumDo);
        q.setParameter("mbet", "%" + markaBetona + "%");
        
        List<Db.Otpremnica> result = q.getResultList();
        em.close();
        
        return result;
    }

    public void updateDate(int id, String vrstaCem, String aditivA1, String aditivA2, 
                           String specBetoa, String konzBetona, String standBetona, 
                           String masinista, String gradiliste, String receptura,
                           String vozac, String tablice, String kupac) {

        EntityManager em = emf.createEntityManager();
        Db.Otpremnica otpremnica = em.find(Db.Otpremnica.class, id);
        em.getTransaction().begin();

        otpremnica.setVrstacementa(vrstaCem);
        otpremnica.setAditiva1(aditivA1);
        otpremnica.setAditiva2(aditivA2);
        otpremnica.setSpecbetona(specBetoa);
        otpremnica.setKonzbetona(konzBetona);
        otpremnica.setStandbetona(standBetona);
        otpremnica.setMasinista(masinista);
        otpremnica.setGradiliste(gradiliste);
        otpremnica.setReceptura(receptura);
        otpremnica.setVozac(vozac);
        otpremnica.setRegtablice(tablice);
        otpremnica.setKupac(kupac);
        
        em.persist(otpremnica);
        em.getTransaction().commit();
        em.close();

    }
    
    public void updateNapomena(int id, String napomena) {

        EntityManager em = emf.createEntityManager();
        Db.Otpremnica otpremnica = em.find(Db.Otpremnica.class, id);
        em.getTransaction().begin();

        otpremnica.setNapomena(napomena);
 
        em.persist(otpremnica);
        em.getTransaction().commit();
        em.close();

    }

    public void updateReceptura(int id, String kupac, String gradiliste, String vozac, String tablice, String masinista, String receptura) {

        EntityManager em = emf.createEntityManager();
        Db.Otpremnica otpremnica = em.find(Db.Otpremnica.class, id);
        em.getTransaction().begin();

        otpremnica.setKupac(kupac);
        otpremnica.setGradiliste(gradiliste);
        otpremnica.setVozac(vozac);
        otpremnica.setRegtablice(tablice);
        otpremnica.setMasinista(masinista);
        otpremnica.setReceptura(receptura);
 
        em.persist(otpremnica);
        em.getTransaction().commit();
        em.close();

    }
    
    public void updateCementAd1Ad2Ad3(int id, String cement1,  String cement2, String aditiva1, 
            String aditiva2, String aditiva3, String filer, String sertifikat, String zrnomax,
            String svojstva, String specBet, String konzistencia ,String hlorid) {

        EntityManager em = emf.createEntityManager();
        Db.Otpremnica otpremnica = em.find(Db.Otpremnica.class, id);
        em.getTransaction().begin();

        otpremnica.setVrstacementa(cement1);
        otpremnica.setVrstacementa2(cement2);
        otpremnica.setAditiva1(aditiva1);
        otpremnica.setAditiva2(aditiva2);
        otpremnica.setAditiva3(aditiva3);
        otpremnica.setVrstafilera(filer);
        otpremnica.setSertifikat(sertifikat);
        otpremnica.setZrnomax(zrnomax);
        otpremnica.setSvojstva(svojstva);
        otpremnica.setSpecbetona(specBet);
        otpremnica.setKonzbetona(konzistencia);
        otpremnica.setStandbetona(hlorid);
 
        em.persist(otpremnica);
        em.getTransaction().commit();
        em.close();

    }
    
    public void setStartDateTame(int id,   Date datumvreme) {

        EntityManager em = emf.createEntityManager();
        Db.Otpremnica otpremnica = em.find(Db.Otpremnica.class, id);
        em.getTransaction().begin();

        otpremnica.setDatumvreme(datumvreme);
 
        em.persist(otpremnica);
        em.getTransaction().commit();
        em.close();

    }
}
