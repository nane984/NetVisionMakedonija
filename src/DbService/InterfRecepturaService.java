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

/**
 *
 * @author brasa
 */
public class InterfRecepturaService {

    private final EntityManagerFactory emf;

    public InterfRecepturaService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Interfreceptura> getInterface() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Db.Interfreceptura i ORDER BY i.br ASC");

        List<Db.Interfreceptura> result = q.getResultList();
        em.close();

        /*
        String[] res = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i).getNazivRec();
            System.out.println(res[i]);
        }
         */
        return result;
    }

    public Db.Interfreceptura getInterfaceWhere(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Interfreceptura i WHERE i.id = :id");
        q.setParameter("id", id);

        Db.Interfreceptura result = (Db.Interfreceptura) q.getSingleResult();
        em.close();

        return result;
    }

    public void updateName(int id, String k, int brRec, int brRedova) {
        int i = 0;
        try {

            updateNameComponent(id, k, brRec);
            if (i == brRedova - 1) {
                JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo");
        }

    }

    private void updateNameComponent(int id, String komp, int rbr) {
        EntityManager em = emf.createEntityManager();
        Db.Interfreceptura receptura = em.find(Db.Interfreceptura.class, id);
        em.getTransaction().begin();

        receptura.setText(komp);
        receptura.setBr(rbr);

        em.persist(receptura);
        em.getTransaction().commit();
        em.close();

    }

    public void addNewRow(int brRed) {
        Db.Interfreceptura interf = new Db.Interfreceptura();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        interf.setBr(brRed);
        interf.setText("");

        em.persist(interf);
        em.getTransaction().commit();
        em.close();

        JOptionPane.showMessageDialog(null, "Dodali ste red");
    }
}
