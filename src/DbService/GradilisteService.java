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
public class GradilisteService {

    private final EntityManagerFactory emf;

    public GradilisteService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Gradiliste> getGradilista() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM Gradiliste g ORDER BY g.gradiliste ASC");

        List<Db.Gradiliste> result = q.getResultList();
        em.close();
        result.remove(0);
        return result;

    }
    
     public List<Db.Gradiliste> getGradilistaWithX() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM Gradiliste g ORDER BY g.gradiliste ASC");

        List<Db.Gradiliste> result = q.getResultList();
        em.close();
        
        return result;

    }
    
    public Db.Gradiliste getGradiliste(String gradiliste) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT g FROM Gradiliste g WHERE g.gradiliste = :grad");
        q.setParameter("grad", gradiliste);
        
        Db.Gradiliste result = (Db.Gradiliste)q.getSingleResult();
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

    public void addNewGradiliste(String gradiliste, String mesto, String adresa) {

        Db.Gradiliste interf = new Db.Gradiliste();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setGradiliste(gradiliste);
        interf.setMesto(mesto);
        interf.setAdresa(adresa);

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste Gradiliste");

    }

    public void updateGradilista(JTable jTable1) {        
        try {
            int rows = jTable1.getRowCount();
            for (int row = 0; row < rows; row++) {
                updateGradiliste(Integer.parseInt(jTable1.getValueAt(row, 4).toString()),
                        jTable1.getValueAt(row, 1).toString(),
                        jTable1.getValueAt(row, 2).toString(),
                        jTable1.getValueAt(row, 3).toString());
            }
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }
    }

    private void updateGradiliste(int id, String gradil, String mesto, String adresa) {

        EntityManager em = emf.createEntityManager();
        Db.Gradiliste gradiliste = em.find(Db.Gradiliste.class, id);
        em.getTransaction().begin();

        gradiliste.setGradiliste(gradil);
        gradiliste.setMesto(mesto);
        gradiliste.setAdresa(adresa);

        em.persist(gradiliste);
        em.getTransaction().commit();
        em.close();

    }
    
     public void deleteGradiliste(Db.Gradiliste gradiliste) {
        EntityManager em = emf.createEntityManager();        
        Db.Gradiliste mergedBook = em.merge(gradiliste);
        em.remove(mergedBook);
        //logger.info("Book with id: " + mergedBook.getId()  + " deleted successfully");
    }
}
