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
public class VozackamionService {
    private final EntityManagerFactory emf;

    public VozackamionService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }
    
    public List<Db.Vozackamion> getVozacKamion() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozackamion v ORDER BY v.vozac.imeprezimevozaci");

        List<Db.Vozackamion> result = q.getResultList();
        em.close();
        result.remove(0);
        return result;

    }
    
     public List<Db.Vozackamion> getVozacKamionWithX() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozackamion v ORDER BY v.vozac.imeprezimevozaci");

        List<Db.Vozackamion> result = q.getResultList();
        em.close();
        
        return result;

    }
     
    
     
    public List<Db.Vozackamion> getVozacKamion(String regTab) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozackamion v WHERE v.tablice.tablice = :tablice ORDER BY v.vozac.imeprezimevozaci ");
        q.setParameter("tablice", regTab);
        
        List<Db.Vozackamion> result = q.getResultList();
        em.close();
        
        return result;

    }
    
    public Db.Vozackamion getVozacKamion(String vozac, String regTab) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vozackamion v WHERE v.vozac.imeprezimevozaci = :vozac AND v.tablice.tablice = :tablice");
        q.setParameter("vozac", vozac);
        q.setParameter("tablice", regTab);
        
        Db.Vozackamion result = (Db.Vozackamion)q.getSingleResult();
        em.close();
        
        return result;

    }
    
    public void addNewConnectionDriverTruck(Db.Vozaci vozac, Db.Regtablice kamion) {
       
            Db.Vozackamion interf = new Db.Vozackamion();

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            interf.setVozac(vozac);
            interf.setTablice(kamion);

            em.persist(interf);
            em.getTransaction().commit();
            em.close();

            JOptionPane.showMessageDialog(null, "Dodelili ste kamion vozacu ");
       
        
    }
    
   
    
   
}
