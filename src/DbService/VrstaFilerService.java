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
public class VrstaFilerService {
    private final EntityManagerFactory emf;
    
     public VrstaFilerService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Filer> getVrstaFiler() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Db.Filer f ORDER BY f.filer ASC");
        
        List<Db.Filer> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
     
     
    public void addNewVrstaFilera(String vrstaFil) {

        Db.Filer interf = new Db.Filer();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setFiler(vrstaFil);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste vrstu filera");

    }
     public void updateFiler(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateFiler(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),   jTable1.getValueAt(row, 1).toString());
                        
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(Aditiv1Service.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateFiler(long id, String text) {
        
        EntityManager em = emf.createEntityManager();
        Db.Filer ad2 = em.find(Db.Filer.class, id);
        em.getTransaction().begin();

        ad2.setFiler(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
