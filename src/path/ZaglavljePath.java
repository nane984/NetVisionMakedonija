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
public class ZaglavljePath {
    private Properties propert; 
    
    public ZaglavljePath() {
        propert = new Properties();      
    }
    
    public String getZaglavlje() {
        return getProp("zag");
    }
    
   
    
    
    public void saveProp(String zaglavlje){
        try{
            propert.setProperty("zag", zaglavlje);
            
            propert.store(new FileOutputStream("Path.cfg"), null);
            //JOptionPane.showMessageDialog(null,"Putanja je sacuvana!");
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null,"Doslo je do greske, pokusajte ponovo!");
        }
    }
    
    private String getProp(String title){
        String value="";
        try{
            propert.load(new FileInputStream("Path.cfg"));
            value = propert.getProperty(title); 
        }
        catch(IOException e){     
        }
        return value;      
    }
    
    
}