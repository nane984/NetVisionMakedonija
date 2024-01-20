/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Branko
 */
public class VelicinaZrna {
   
    public static double deliXYZ(double voda, double cement1, double cement2) {
        double a = 0;
        double newNum = 0;
        try{
        a = voda/(cement1+cement2);
        BigDecimal bd = new BigDecimal(a).setScale(3, RoundingMode.HALF_UP);  
        newNum = bd.doubleValue();
        }catch(Exception e){
            return newNum;  
        }
        
       // System.out.println("Voda: "+voda+"  cem1: "+cement1+" cem2: "+cement2);
        return newNum;  
    }
}
