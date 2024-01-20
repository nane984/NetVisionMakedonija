/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbService;

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
 * @author branko.scekic
 */
public class SvojstvaService {
    private final EntityManagerFactory emf;
    
     public SvojstvaService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Svojstva> getSvojstva() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Db.Svojstva f ORDER BY f.svojstva ASC");
        
        List<Db.Svojstva> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
     
     
    public void addNewSvojstva(String vrstaFil) {

        Db.Svojstva interf = new Db.Svojstva();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setSvojstva(vrstaFil);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste Svojstva");

    }
     public void updateSvojstva(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateSvojstva(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),   jTable1.getValueAt(row, 1).toString());
                        
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(Aditiv1Service.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateSvojstva(long id, String text) {
        
        EntityManager em = emf.createEntityManager();
        Db.Svojstva ad2 = em.find(Db.Svojstva.class, id);
        em.getTransaction().begin();

        ad2.setSvojstva(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
