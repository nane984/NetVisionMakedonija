/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buff;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;
/**
 *
 * @author branko.scekic
 */
public class Config {
    private final Properties propert; 
    
    public Config() {
        propert = new Properties();      
    }
    ////////////////////////////////////////////////////////////////////////////
    public String getIp(){
        return getProp("ip");
    }
    
    public String getId(){
        return getProp("id");
    }
    
    public  String getvremeCitanjaMS() {
        return getProp("vcms");
    }
    
    ////////////////////REGISTERs////////////////////////////////////////////////////////
    public  String getStartAddressRegister() {
        return getProp("sAHR");
    }
    
    public  String getStartAddressDigitalInput() {
        return getProp("sADI");
    } 
            
    
    public  String getStartAddressCoilOutput() {
        return getProp("sACO");
    }
   
    public String getStartnaAdresaRegistraZaBazu(){
        return getProp("sARB");
    }
    
     public String getReadDI(){
        return getProp("rDI");
    }
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    
    
    
    public void saveProp(String ip, String id ,String startAddressRegister, String startnaAdresaRegistraZaBazu, 
            String startAddressDigitalInput,  String vremeCitanjaMS, String readDI){
        try{
            
            propert.setProperty("ip", ip);
            propert.setProperty("id", id);

            propert.setProperty("sAHR", startAddressRegister);
            propert.setProperty("sARB", startnaAdresaRegistraZaBazu);
            propert.setProperty("sADI", startAddressDigitalInput);
            propert.setProperty("vcms", vremeCitanjaMS);
            
            propert.setProperty("rDI", readDI);
            
            propert.store(new FileOutputStream("Config.cfg"), null);

            JOptionPane.showMessageDialog(null,"Podesavanja su uspesno sacuvana!");
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null,"Doslo je do greske, pokusajte ponovo!");
        }
    }
    
    private String getProp(String title){
        String value="";
        try{
            propert.load(new FileInputStream("Config.cfg"));
            value = propert.getProperty(title); 
        }
        catch(IOException e){     
        }
        return value;      
    }
}