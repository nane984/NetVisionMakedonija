/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package path;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
/**
 *
 * @author branko.scekic
 */
public class DbBackupRestorePathBe {
    private static Properties propert; 
    
    public DbBackupRestorePathBe() {
        propert = new Properties();      
    }
    
    public static String getBackupDb() {
        return getProp("backupDbPath");
    }
    
   
    
    
    public static void saveProp(String backupRestoreDBPath){
        try{
            propert = new Properties();      
            propert.setProperty("backupDbPath", backupRestoreDBPath);
            
            propert.store(new FileOutputStream("PathDb.cfg"), null);
            JOptionPane.showMessageDialog(null,"Putanja je sacuvana! " + backupRestoreDBPath);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null,"Doslo je do greske, pokusajte ponovo!");
        }
    }
    
    public static String getProp(String title){
        String value="user.home";
        try{
            propert = new Properties();      
            propert.load(new FileInputStream("PathDb.cfg"));
            value = propert.getProperty(title); 
        }
        catch(IOException e){     
        }
        return value;      
    }
    
    
}