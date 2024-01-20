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
import javax.swing.JTable;

/**
 *
 * @author brasa
 */
public class MasinistaService {

    private final EntityManagerFactory emf;

    public MasinistaService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Masinista> getMasinista() {
       
            EntityManager em = emf.createEntityManager();
            //Query q = em.createQuery("SELECT m FROM Db.Masinista m ORDER BY m.imePrezime ASC");
            Query q = em.createQuery("SELECT m FROM Masinista m ORDER BY m.imePrezime");
            

            List<Db.Masinista> result = q.getResultList();
            result.remove(0);
            em.close();
           
            return result;
        
    }
    
    public List<Db.Masinista> getMasinistaWithX() {
       
            EntityManager em = emf.createEntityManager();
            //Query q = em.createQuery("SELECT m FROM Db.Masinista m ORDER BY m.imePrezime ASC");
            Query q = em.createQuery("SELECT m FROM Masinista m ORDER BY m.imePrezime");
            

            List<Db.Masinista> result = q.getResultList();
            em.close();
           
            return result;
        
    }
    
     
    
    public Db.Masinista getMasinista(String imePrezime){
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT m FROM Masinista m WHERE m.imePrezime = :imePrezime");
        q.setParameter("imePrezime", imePrezime);
        
        Db.Masinista result = (Db.Masinista)q.getSingleResult();
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

    public void addNew(String masinista) {
       
            Db.Masinista interf = new Db.Masinista();

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            interf.setImePrezime(masinista);

            em.persist(interf);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null, "Dodali ste Masinistu");
       
        
    }
    
    public void updateMasinisti(JTable jTable1) {
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateMasinista(Integer.parseInt(jTable1.getValueAt(row, 2).toString()),
                        jTable1.getValueAt(row, 1).toString());
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateMasinista(int id, String imePrezime) {

        EntityManager em = emf.createEntityManager();
        Db.Masinista masinista = em.find(Db.Masinista.class, id);
        em.getTransaction().begin();

        masinista.setImePrezime(imePrezime);
        
        em.persist(masinista);
        em.getTransaction().commit();
        em.close();

    }
}
