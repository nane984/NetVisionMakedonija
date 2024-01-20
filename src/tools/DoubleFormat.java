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
 * @author branko.scekic
 */
public class DoubleFormat {
    public static String getRounDouble(Double db, int Format){
        BigDecimal bd = new BigDecimal(Double.toString(db));
        bd = bd.setScale(Format, RoundingMode.HALF_UP);
        return bd.toString();
    }
    
    public static String getRounFloat(float db, int Format){
        BigDecimal bd = new BigDecimal(Double.toString(db));
        bd = bd.setScale(Format, RoundingMode.HALF_UP);
        return bd.toString();
    }
    
    public static float getRounDouble(float db, int Format){
        BigDecimal a1 = new BigDecimal(db);
        float rez = a1.setScale(Format, RoundingMode.HALF_UP).floatValue();
        return rez;
    }
    
    public static double getRounDoubleRetDouble(Double db, int Format){
        BigDecimal a1 = new BigDecimal(db);
        double rez = a1.setScale(Format, RoundingMode.HALF_UP).doubleValue();
        return rez;
    }
}
