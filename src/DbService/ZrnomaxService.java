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
public class ZrnomaxService {
    private final EntityManagerFactory emf;
    
     public ZrnomaxService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Zrnomax> getZrnomax() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT f FROM Db.Zrnomax f ORDER BY f.zrnomax ASC");
        
        List<Db.Zrnomax> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
     
     
    public void addNewZrnomax(String vrstaFil) {

        Db.Zrnomax interf = new Db.Zrnomax();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setZrnomax(vrstaFil);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste Zrnomax");

    }
    
    public void updateZrnomax(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateZrnomax(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),   jTable1.getValueAt(row, 1).toString());
                        
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(Aditiv1Service.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateZrnomax(long id, String text) {
        
        EntityManager em = emf.createEntityManager();
        Db.Zrnomax ad2 = em.find(Db.Zrnomax.class, id);
        em.getTransaction().begin();

        ad2.setZrnomax(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
