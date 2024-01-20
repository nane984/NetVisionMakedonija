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
public class KorisnikService {

    private final EntityManagerFactory emf;

    public KorisnikService() {
        emf = Persistence.createEntityManagerFactory("NetVisionKraljevoPU");
    }

    public List<Db.Korisnik> getKorisnici() {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT r FROM Db.Korisnik r ");

        List<Db.Korisnik> result = q.getResultList();
        em.close();

        return result;
    }

    public void updateKorisnik(int id, boolean L1, boolean L2, boolean admin) {
        EntityManager em = emf.createEntityManager();
        Db.Korisnik korisnik = em.find(Db.Korisnik.class, id);
        em.getTransaction().begin();

        int a1 = 0;
        if(L1){
            a1=1;
        }
        int a2 = 0;
        if(L2){
            a2=1;
        }
        int adm = 0;
        if(admin){
            adm=1;
        }
        
        korisnik.setL1(a1);
        korisnik.setL2(a2);
        korisnik.setAdmin(adm);

        em.persist(korisnik);
        em.getTransaction().commit();
        em.close();

    }

    public void saveNewUser(String Ime, String Prezime, String username, boolean L1, boolean L2, boolean admin, String lozinka) {

        int a1 = 0;
        if(L1){
            a1=1;
        }
        int a2 = 0;
        if(L2){
            a2=1;
        }
        int adm = 0;
        if(admin){
            adm=1;
        }
        
        if (checkIfExist(Ime, Prezime, username)) {

            Db.Korisnik korisnik = new Db.Korisnik();

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            korisnik.setIme(Ime);
            korisnik.setPrezime(Prezime);
            korisnik.setUsername(username);
            korisnik.setL1(a1);
            korisnik.setL2(a2);
            korisnik.setAdmin(adm);
            korisnik.setLozinka(encriptPass(lozinka));

            em.persist(korisnik);
            em.getTransaction().commit();
            em.close();
            
            JOptionPane.showMessageDialog(null, "Sacuvali ste novog korisnika!");
        }
    }
    
    public void deleteUser(int id){
        EntityManager em = emf.createEntityManager();

        Db.Korisnik korisnik = em.find(Db.Korisnik.class, id);
        em.getTransaction().begin();     
        em.remove(korisnik);
        em.getTransaction().commit();
        em.close();
        
        JOptionPane.showMessageDialog(null, "Obrisali ste korisnika!");

    }

    private boolean checkIfExist(String ime, String prezime, String username) {
        boolean a = false;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Db.Korisnik a WHERE a.username LIKE :usr");
        q.setParameter("usr", username);
        try {
            a = q.getSingleResult().toString().isEmpty();
            JOptionPane.showMessageDialog(null, "Korisnik vec postoji u bazi!");
        } catch (Exception ex) {
            a = true;
        } finally {
            em.close();
            return a;
        }
    }
    
    private String encriptPass(String pass){
        String encryptedPassword = "";
        try {
           
            //encryptedPassword = AESCrypt.encrypt(pass);
            
        
        } catch(Exception e) { System.out.println("bug"+e.getMessage()); 
        
        }
        return encryptedPassword;
    }
    
    public String decryptedPass(String pass){
        String decryptedPassword = "";
        try {
            //decryptedPassword = AESCrypt.decrypt(pass);    
        } catch(Exception e) { System.out.println("bug"+e.getMessage()); }
        return decryptedPassword;
    }
    
}
