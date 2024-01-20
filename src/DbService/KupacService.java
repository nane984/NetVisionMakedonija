/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbService;

import Frame.MainFrame;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author brasa
 */
public class KupacService {

    private final EntityManagerFactory emf;

    public KupacService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Kupac> getKupci() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT k FROM Kupac k ORDER BY k.kupac ASC");

        List<Db.Kupac> result = q.getResultList();
        em.close();
        result.remove(0);
        return result;

    }

    public List<Db.Kupac> getKupciWithX() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT k FROM Kupac k ORDER BY k.kupac ASC");

        List<Db.Kupac> result = q.getResultList();
        em.close();

        return result;

    }

    public Db.Kupac getKupac(String kupac) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT k FROM Kupac k WHERE k.kupac = :kupac");
        q.setParameter("kupac", kupac);

        Db.Kupac result = (Db.Kupac) q.getSingleResult();
        em.close();

        /*
        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i).getIme();
            System.out.println(res[i]);
        }
         */
        return result;

    }

    public void addNewKupac(String kupac) {
        Db.Kupac interf = new Db.Kupac();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        interf.setKupac(kupac);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste kupca");

    }

    public Db.Kupac getKupac(int id) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT k FROM Kupac k WHERE k.kupac = :kupac");
        q.setParameter("kupac", id);

        Db.Kupac result = (Db.Kupac) q.getSingleResult();
        em.close();

        return result;
    }

    public void updateKupci(JTable jTable1) {
        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateKupac(Integer.parseInt(jTable1.getValueAt(row, 3).toString()),
                        jTable1.getValueAt(row, 1).toString(),
                        jTable1.getValueAt(row, 2).toString());
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(KupacService.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateKupac(int id, String kup, String adresa) {

        EntityManager em = emf.createEntityManager();
        Db.Kupac kupac = em.find(Db.Kupac.class, id);
        em.getTransaction().begin();

        kupac.setKupac(kup);
        kupac.setAdresa(adresa);

        em.persist(kupac);
        em.getTransaction().commit();
        em.close();

    }
}
