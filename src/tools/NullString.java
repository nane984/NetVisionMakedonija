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
public class NullString {
    
    public static String getString(String in){
        if(in==null){
            return " ";
        }else{
            return in;
        }
    }
    
}
