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
public class RegtabliceService {
    private final EntityManagerFactory emf;
    
    public RegtabliceService(){
            emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
    
    public List<Db.Regtablice> getRegTablice() {
  
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT r FROM Regtablice r ORDER BY r.tablice ASC");

        List<Db.Regtablice> result = q.getResultList();
        em.close();
        result.remove(0);
        return result;
        
    }
    
    public List<Db.Regtablice> getRegTabliceWithX() {
  
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT r FROM Regtablice r ORDER BY r.tablice ASC");

        List<Db.Regtablice> result = q.getResultList();
        em.close();
       
        return result;
        
    }
    
   
    
     public void addNewTablice(String tablice){
            
            Db.Regtablice interf = new Db.Regtablice();

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            interf.setTablice(tablice);
          
            em.persist(interf);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null, "Dodali ste reg tablicu");
         
     }
     
    public Db.Regtablice getTruck(int id){
        
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT r FROM Regtablice r WHERE r.idtablice = :idTablice" );
        q.setParameter("idTablice", id);
        
        Db.Regtablice result = (Db.Regtablice) q.getSingleResult();
        em.close();

        return result;
    } 
    
    public void updateRegTablice(JTable jTable1) {
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                 updateRegTab(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),
                        jTable1.getValueAt(row, 1).toString());
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateRegTab(int id, String tablica) {

        EntityManager em = emf.createEntityManager();
        Db.Regtablice regTab = em.find(Db.Regtablice.class, id);
        em.getTransaction().begin();

        regTab.setTablice(tablica);
        
        em.persist(regTab);
        em.getTransaction().commit();
        em.close();
    }
}
