/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author Branko
 */
public class limitUnsignedNumber {
    
    public static String putOnZero(String in){
        int x = 0;
        
        try{
            x = Integer.parseInt(in);
            if(x>65400){
                in = "0";
            }
        }catch(Exception e){
            
        }
        
        return in;
    }
}
