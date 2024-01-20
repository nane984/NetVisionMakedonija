/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DecimalFormat;

/**
 *
 * @author brasa
 */
public class IntToDecimal {
    public static String shiftPoint(String a) {
        try{
            double x = Double.parseDouble(a);
            for (int i = 1; i <= 2; i++) {
                x = x * .1;
            }

            DecimalFormat df = new DecimalFormat("#####.##");
            return df.format(x);
        }catch(Exception e){
            return "***";
        }
        //return x;
    }
    
     public static int removePoint(String a) {
        
         
         try{
            double x = Double.parseDouble(a);
            x = x * 10;
            int ja = (int) Math.round(x);
            return ja;
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
        //return x;
    }
    
    public static String shiftPoint(Long a, int br) {
        //JOptionPane.showMessageDialog(null, "Pre transformacije " + a);
        double x = (double)a;
       // JOptionPane.showMessageDialog(null, "Double " + x);
        for (int i = 1; i <= br; i++) {
            x = x * .1;
        }

        DecimalFormat df = new DecimalFormat("#####.####");
       // JOptionPane.showMessageDialog(null, "Posle transformacije " + df.format(x));
        return df.format(x);
        //return x;
    }
    
   public static String getTwoRegisterIntShiftPoint(String hight, String low, int point){
        try{
            long h = Long.parseLong(hight);
            long l = Long.parseLong(low);
            long c = (long)h << 16 | l;
            double x = c;
            String form = "";
            
            for (int i = 1; i <= point; i++) {
                x = x * .1;
                form = form + "#";
            }

            //DecimalFormat df = new DecimalFormat("#####.##");
             DecimalFormat df;
            if(point == 0){
                df = new DecimalFormat("######");
            }else{
                df = new DecimalFormat("######." + form);
            }
            
            return df.format(x);
        }catch(Exception e){
            return "***";
        }
    }
}
