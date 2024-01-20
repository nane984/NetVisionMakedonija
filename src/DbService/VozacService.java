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
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author brasa
 */
public class VozacService {

    private final EntityManagerFactory emf;

    public VozacService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Vozaci> getVozaci() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozaci v ORDER BY v.imeprezimevozaci ASC");

        List<Db.Vozaci> result = q.getResultList();
       
        em.close();
        result.remove(0);
        return result;

    }
    
     public List<Db.Vozaci> getVozaciWithX() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozaci v ORDER BY v.imeprezimevozaci ASC");

        List<Db.Vozaci> result = q.getResultList();
       
        em.close();
        
        return result;

    }
    
   


    public void addNewVozac(String imeprezime) {
        Db.Vozaci interf = new Db.Vozaci();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        interf.setImeprezimevozaci(imeprezime);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste vozaca");

    }

    public Db.Vozaci getDriver(int id){
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozaci v where v.idvozaci = :idVozac" );
        q.setParameter("idVozac", id);
        
        Db.Vozaci result = (Db.Vozaci) q.getSingleResult();
        em.close();


        return result;
    }
    
    public void updateVozaci(JTable jTable1) {
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                 updateVozac(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),
                        jTable1.getValueAt(row, 1).toString());
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateVozac(int id, String imePrezime) {

        EntityManager em = emf.createEntityManager();
        Db.Vozaci vozac = em.find(Db.Vozaci.class, id);
        em.getTransaction().begin();

        vozac.setImeprezimevozaci(imePrezime);
        
        em.persist(vozac);
        em.getTransaction().commit();
        em.close();

    }
}
