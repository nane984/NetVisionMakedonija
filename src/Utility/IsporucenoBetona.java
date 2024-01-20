/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Branko
 */
public class IsporucenoBetona {

    private static float k1Zadato = 0;
    private static float k2Zadato = 0;
    private static float k3Zadato = 0;
    private static float k4Zadato = 0;
    private static float k5Zadato = 0;
    private static float k6Zadato = 0;
    private static float k7Zadato = 0;
    private static float k8Zadato = 0;
    private static float k9Zadato = 0;
    private static float k10Zadato = 0;
    private static float k11Zadato = 0;
    private static float k12Zadato = 0;
    
    private static float ukupnoIsporuceno = 0;

    public static float isporucenoBetona(
            float k1Izdato,
            float k2Izdato,
            float k3Izdato,
            float k4Izdato,
            float k5Izdato,
            float k6Izdato,
            float k7Izdato,
            float k8Izdato,
            float k9Izdato,
            float k10Izdato,
            float k11Izdato,
            float k12Izdato) {
        
        float zbirIzdato = k1Izdato + k2Izdato + k3Izdato + k4Izdato + k5Izdato +
                k6Izdato + k7Izdato + k8Izdato + k9Izdato + k10Izdato + k11Izdato + k12Izdato;
        
        float beton = zbirIzdato/getZadatoPo1m3();
        BigDecimal a1 = new BigDecimal(beton);
        float rez = a1.setScale(2, RoundingMode.HALF_UP).floatValue();
        
        if(rez != 0){
            return rez;
        }else{
            return 1;
        }
    }

    private static float getZadatoPo1m3() {
        float zbir = k1Zadato + k2Zadato + k3Zadato + k4Zadato + k5Zadato + k6Zadato + k7Zadato
                + k8Zadato + k9Zadato + k10Zadato + k11Zadato + k12Zadato;

        if (zbir != 0) {
            return zbir;
        } else {
            return 1;
        }
    }

    public static void setK1Zadato(float k1Zadato) {
        IsporucenoBetona.k1Zadato = k1Zadato;
        
    }

    public static void setK2Zadato(float k2Zadato) {
        IsporucenoBetona.k2Zadato = k2Zadato;
    }

    public static void setK3Zadato(float k3Zadato) {
        IsporucenoBetona.k3Zadato = k3Zadato;
    }

    public static void setK4Zadato(float k4Zadato) {
        IsporucenoBetona.k4Zadato = k4Zadato;
    }

    public static void setK5Zadato(float k5Zadato) {
        IsporucenoBetona.k5Zadato = k5Zadato;
    }

    public static void setK6Zadato(float k6Zadato) {
        IsporucenoBetona.k6Zadato = k6Zadato;
    }

    public static void setK7Zadato(float k7Zadato) {
        IsporucenoBetona.k7Zadato = k7Zadato;
    }

    public static void setK8Zadato(float k8Zadato) {
        IsporucenoBetona.k8Zadato = k8Zadato;
    }

    public static void setK9Zadato(float k9Zadato) {
        IsporucenoBetona.k9Zadato = k9Zadato;
    }

    public static void setK10Zadato(float k10Zadato) {
        IsporucenoBetona.k10Zadato = k10Zadato;
    }

    public static void setK11Zadato(float k11Zadato) {
        IsporucenoBetona.k11Zadato = k11Zadato;
    }

    public static void setK12Zadato(float k12Zadato) {
        IsporucenoBetona.k12Zadato = k12Zadato;
    }

    public static void setUkupnoIsporuceno(float ukupnoIsporuceno) {
        IsporucenoBetona.ukupnoIsporuceno = ukupnoIsporuceno;
    }

    public static float getUkupnoIsporuceno() {
        return ukupnoIsporuceno;
    }
    
    

    public static void printk(){
        System.out.println("k1=" + k1Zadato + " k2="+k2Zadato + " k3="+k3Zadato 
         + " k4="+k4Zadato + " k5="+k5Zadato + " k6="+k6Zadato + " k7="+k7Zadato
         + " k8="+k8Zadato + " k9="+k9Zadato + " k10="+k10Zadato + " k11="+k11Zadato
         + " k12="+k12Zadato);
        
    }
    
}
