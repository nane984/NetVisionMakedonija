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
public class InterfAditivService {

    private final EntityManagerFactory emf;

    public InterfAditivService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Interfaditiv> getInterface() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Interfaditiv i ORDER BY i.br ASC");

        List<Db.Interfaditiv> result = q.getResultList();
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

    public Db.Interfaditiv getInterfaceWhere(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT i FROM Interfaditiv i WHERE i.id = :id");
        q.setParameter("id", id);

        Db.Interfaditiv result = (Db.Interfaditiv) q.getSingleResult();
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
        Db.Interfaditiv receptura = em.find(Db.Interfaditiv.class, id);
        em.getTransaction().begin();

        receptura.setText(komp);
        receptura.setBr(rbr);

        em.persist(receptura);
        em.getTransaction().commit();
        em.close();

    }

    public void addNewRow(int brRed) {
        Db.Interfaditiv interf = new Db.Interfaditiv();

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
