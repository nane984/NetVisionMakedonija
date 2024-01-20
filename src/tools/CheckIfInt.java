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
public class CheckIfInt {
    public static int checkInt(String o){
        int i=0;
        try{
            i=Integer.parseInt(o.toString());
        }catch(NumberFormatException e){
            i = 0;
        }
       return i; 
    }
    
    public static int checkIfInt(String a){
        int res = 0;
        try{
            res = Integer.parseInt(a);
        }catch(Exception e){
            res = 0;
        }
        return res;
    }
}
