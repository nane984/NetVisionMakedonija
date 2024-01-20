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
public class Aditiv2Service {
    private final EntityManagerFactory emf;
    
     public Aditiv2Service() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Aditiv2> getVrstaCementa() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT a FROM Aditiv2 a ORDER BY a.text ASC");
        
        List<Db.Aditiv2> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
      public void addNewAditiv(String vrstaCem) {

        Db.Aditiv2 interf = new Db.Aditiv2();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setText(vrstaCem);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste aditiv");
    }
      
      public void updateAditiv(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateAditiv(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),   jTable1.getValueAt(row, 1).toString());
                        
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            Logger.getLogger(Aditiv1Service.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateAditiv(int id, String text) {
        Long a = Integer.toUnsignedLong(id);
        EntityManager em = emf.createEntityManager();
        Db.Aditiv2 ad2 = em.find(Db.Aditiv2.class, a);
        em.getTransaction().begin();

        ad2.setText(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
