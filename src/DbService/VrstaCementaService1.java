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
public class VrstaCementaService1 {
    private final EntityManagerFactory emf;
    
     public VrstaCementaService1() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
     
     public List<Db.Vrstacementa> getVrstaCementa() {
         
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Db.Vrstacementa v ORDER BY v.vrstacem ASC");
        
        List<Db.Vrstacementa> result = q.getResultList();
        em.close();
        //result.remove(0);
       return result;
    }
     
     
     
    public void addNewVrstaCementa(String vrstaCem) {

        Db.Vrstacementa interf = new Db.Vrstacementa();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setVrstacem(vrstaCem);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste vrstu cementa");

    }
     public void updateSpecBetona(JTable jTable1) {        
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
        
        EntityManager em = emf.createEntityManager();
        Db.Vrstacementa ad2 = em.find(Db.Vrstacementa.class, id);
        em.getTransaction().begin();

        ad2.setVrstacem(text);

        em.persist(ad2);
        em.getTransaction().commit();
        em.close();

    }
}
