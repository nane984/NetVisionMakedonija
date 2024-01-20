/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author brasa
 */
public class KomponenteService {

    private final EntityManagerFactory emf;

    public KomponenteService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Komponente> getKomponente() {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select k from Db.Komponente k");

        List<Db.Komponente> result = q.getResultList();
        em.close();

        return result;
    }

    public List<Db.Komponente> getKomponenteWhere(int brOtpremnice) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select k from Komponente k where k.otpremnicaRbr = :brOtp ORDER BY k.brSarze");
        q.setParameter("brOtp", brOtpremnice);
        //q.setParameter("prezime", "%" + prezimeTestera + "%");
        //q.setParameter("korisnik", "%" + korisnik + "%");
        //q.setParameter("isporukaZa", "%" + isporukaZa + "%");
        //q.setParameter("srBr", "%" + serBr + "%");

        List<Db.Komponente> result = q.getResultList();
        em.close();

        return result;
    }

    public Db.Komponente getLastKomponentWhere(int brOtpremnice) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("select k from Komponente k where k.otpremnicaRbr = :brOtp ORDER BY k.datumVreme DESC");
        q.setParameter("brOtp", brOtpremnice);
        //q.setParameter("prezime", "%" + prezimeTestera + "%");
        //q.setParameter("korisnik", "%" + korisnik + "%");
        //q.setParameter("isporukaZa", "%" + isporukaZa + "%");
        //q.setParameter("srBr", "%" + serBr + "%");

        List<Db.Komponente> result = q.getResultList();
        em.close();

        if (result.isEmpty()) {
            Db.Komponente e = new Db.Komponente();
            e.setBrSarze(0);
            e.setDatumVreme(new Date());
            e.setK1Ostv(0f);
            e.setK2Ostv(0f);
            e.setK3Ostv(0f);
            e.setK4Ostv(0f);
            e.setK5Ostv(0f);
            e.setK6Ostv(0f);
            e.setK7Ostv(0f);
            e.setK8Ostv(0f);
            e.setK9Ostv(0f);
            e.setK10Ostv(0f);

            e.setK1Zad(0f);
            e.setK2Zad(0f);
            e.setK3Zad(0f);
            e.setK4Zad(0f);
            e.setK5Zad(0f);
            e.setK6Zad(0f);
            e.setK7Zad(0f);
            e.setK8Zad(0f);
            e.setK9Zad(0f);
            e.setK10Zad(0f);

            e.setKolicinabetona(0f);
            e.setKolicinaostvarenakg(0f);
            e.setKolicinazadatakg(0f);

            result.add(e);
        }

        return result.get(0);
    }

    public void addKomponente(int otp, int brSarze,
            float k1z, float k1o,
            float k2z, float k2o,
            float k3z, float k3o,
            float k4z, float k4o,
            float k5z, float k5o,
            float k6z, float k6o,
            float k7z, float k7o,
            float k8z, float k8o,
            float k9z, float k9o,
            float k10z, float k10o,
            float k11z, float k11o,
            float k12z, float k12o,
            float kolZad, float kolOstv,
            float kolBet) {

        int proc = 0;

        Db.Komponente komponente = new Db.Komponente();

        Date dNow = addSeconds(new Date(),45);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        komponente.setOtpremnicaRbr(otp);
        komponente.setBrSarze(brSarze);
        komponente.setK1Zad(k1z);
        komponente.setK1Ostv(k1o);
        komponente.setK2Zad(k2z);
        komponente.setK2Ostv(k2o);
        komponente.setK3Zad(k3z);
        komponente.setK3Ostv(k3o);
        komponente.setK4Zad(k4z);
        komponente.setK4Ostv(k4o);
        komponente.setK5Zad(k5z);
        komponente.setK5Ostv(k5o);
        komponente.setK6Zad(k6z);
        komponente.setK6Ostv(k6o);
        komponente.setK7Zad(k7z);
        komponente.setK7Ostv(k7o);
        komponente.setK8Zad(k8z);
        komponente.setK8Ostv(k8o);
        komponente.setK9Zad(k9z);
        komponente.setK9Ostv(k9o);
        komponente.setK10Zad(k10z);
        komponente.setK10Ostv(k10o);
        komponente.setK11Zad(k11z);
        komponente.setK11Ostv(k11o);
        komponente.setK12Zad(k12z);
        komponente.setK12Ostv(k12o);
        komponente.setKolicinazadatakg(kolZad);
        komponente.setKolicinaostvarenakg(kolOstv);
        komponente.setDatumVreme(dNow);
        komponente.setOstvarenoprocent(proc);
        komponente.setKolicinabetona(kolBet);

        em.persist(komponente);
        em.getTransaction().commit();
        //em.flush();
        em.close();

    }

    public static Date addSeconds(Date date, Integer seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }

    public void updateKomponente(int id, int brSarze,
            float k1o,
            float k2o,
            float k3o,
            float k4o,
            float k5o,
            float k6o,
            float k7o,
            float k8o,
            float k9o,
            float k10o,
            float k11o,
            float k12o,
            float kolBet,
            float kolOstKg) {

        EntityManager em = emf.createEntityManager();
        Db.Komponente komp = em.find(Db.Komponente.class, id);
        em.getTransaction().begin();

        komp.setBrSarze(brSarze);

        komp.setK1Ostv(k1o);
        komp.setK1Zad(k1o);

        komp.setK2Ostv(k2o);
        komp.setK2Zad(k2o);

        komp.setK3Ostv(k3o);
        komp.setK3Zad(k3o);

        komp.setK4Ostv(k4o);
        komp.setK4Zad(k4o);

        komp.setK5Ostv(k5o);
        komp.setK5Zad(k5o);

        komp.setK6Ostv(k6o);
        komp.setK6Zad(k6o);

        komp.setK7Ostv(k7o);
        komp.setK7Zad(k7o);

        komp.setK8Ostv(k8o);
        komp.setK8Zad(k8o);

        komp.setK9Ostv(k9o);
        komp.setK9Zad(k9o);

        komp.setK10Ostv(k10o);
        komp.setK10Zad(k10o);

        komp.setK11Ostv(k11o);
        komp.setK11Zad(k11o);

        komp.setK12Ostv(k12o);
        komp.setK12Zad(k12o);

        komp.setKolicinabetona(kolBet);
        komp.setKolicinaostvarenakg(kolOstKg);

        em.persist(komp);
        em.getTransaction().commit();
        em.close();

    }

}
