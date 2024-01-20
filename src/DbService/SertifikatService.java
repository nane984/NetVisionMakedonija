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
public class SertifikatService {
    private final EntityManagerFactory emf;
    
     public SertifikatService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Sertifikat> getSertifikat() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Db.Sertifikat f ORDER BY f.sertifikat ASC");
        
        List<Db.Sertifikat> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
     
     
    public void addNewSertifikat(String vrstaFil) {

        Db.Sertifikat interf = new Db.Sertifikat();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setSertifikat(vrstaFil);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste sertifikat");

    }
     public void updateSertifikat(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateSertifikat(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),   jTable1.getValueAt(row, 1).toString());
                        
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(Aditiv1Service.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateSertifikat(long id, String text) {
        
        EntityManager em = emf.createEntityManager();
        Db.Sertifikat ad2 = em.find(Db.Sertifikat.class, id);
        em.getTransaction().begin();

        ad2.setSertifikat(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
