/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DecimalFormat;

/**
 *
 * @author branko.scekic
 */
public class ShiftPoint {
    public static String shiftonePoint(int a, int br) { 
        try{
        double x = (double)a;
        for (int i = 1; i <= br; i++) {
            x = x * .1;
        }
        DecimalFormat df = new DecimalFormat("#####.####");      
        return df.format(x);
        }catch(Exception e){
            return "0";
        }
    }
}
