/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbService;

import java.awt.HeadlessException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author brasa
 */
public class KService {

    private final EntityManagerFactory emf;

    public KService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.K> getKomponente() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT k FROM Db.K as k ORDER BY k.brK asc ");

        List<Db.K> result = q.getResultList();
        em.close();
        /*
        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i).getKomponenta();
            System.out.println(res[i]);
        }
         */
        return result;
    }

    public void updateName(String k1, String k2, String k3, String k4, String k5,
            String k6, String k7, String k8, String k9, String k10,  String k11, String k12,
            boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6,
            boolean b7, boolean b8, boolean b9, boolean b10, boolean b11, boolean b12, 
            int f1, int f2, int f3,
            int f4, int f5, int f6, int f7, int f8, int f9, int f10, int f11, int f12) {

        String[] s = {k1, k2, k3, k4, k5, k6, k7, k8, k9, k10, k11, k12};
        boolean[] b = {b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12};
        int[] f = {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12};

        try {

            for (int i = 1; i <= 12; i++) {
                //System.out.println("i= "+i+" string k "+ s[i - 1] + " BOOLEAN "+ b[i - 1] + " FORMT " + f[i-1]);
                updateNameComponent(i, s[i - 1], b[i - 1], f[i - 1]);

            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (HeadlessException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");

        }

    }

    private void updateNameComponent(int id, String komp, boolean check, int brDecimala) {
        EntityManager em = emf.createEntityManager();
        Db.K komponenta = em.find(Db.K.class, id);
        em.getTransaction().begin();

        int a = 0;

        if (check) {
            a = 1;
        }

        komponenta.setKomponenta(komp);
        komponenta.setIfcheck(a);
        komponenta.setDecformat(brDecimala);

        em.persist(komponenta);
        em.getTransaction().commit();
        em.close();
    }

    public void addNewRow(int brRed) {
        Db.K interf = new Db.K();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //interf.setIdkomponenta(brRed);
        interf.setBrK(brRed);
        interf.setKomponenta("");

        em.persist(interf);
        em.getTransaction().commit();
        em.close();
        //JOptionPane.showMessageDialog(null, "Dodali ste red");
    }

    public void decFormat(int brRed) {
        Db.K interf = new Db.K();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //interf.setIdkomponenta(brRed);
        interf.setDecformat(brRed);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        //JOptionPane.showMessageDialog(null, "Dodali ste red");
    }

    private void saveNewComponent(int id, String komp, boolean check, int brDecimala) {
        EntityManager em = emf.createEntityManager();
        Db.K komponenta = new Db.K();
        em.getTransaction().begin();

        int a = 0;

        if (check) {
            a = 1;
        }

        komponenta.setKomponenta(komp);
        komponenta.setIfcheck(a);
        komponenta.setDecformat(brDecimala);

        em.persist(komponenta);
        em.getTransaction().commit();
        em.close();
    }

}
