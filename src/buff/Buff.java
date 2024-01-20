/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buff;

import Frame.MainFrame;
import Mxl.Mxl;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.CheckIfInt;

/**
 *
 * @author brasa
 */
public class Buff implements Runnable {

    public static Config config;
    private String ip;
    private int id;
    private String[] resultRegister;
    private final int brRegZaCitanje;
    private int startnaAdresaRegistra;
    private int startnaAdresaRegistraZaBazu;
    private int startnaAdresaDigitalInput;
    private final Mxl modbus;
    private boolean[] resultDI;
    private final int brBoolZaCitanje;
    private int[] citanjeHoldRegistra;
    public int[] bazaOcitaj;

    public String jLabelK1ZadatoHeader = "0";
    public String jLabelK1IzdatoHeader = "0";
    public String jLabelK2ZadatoHeader = "0";
    public String jLabelK2IzdatoHeader = "0";
    public String jLabelK3ZadatoHeader = "0";
    public String jLabelK3IzdatoHeader = "0";
    public String jLabelK4ZadatoHeader = "0";
    public String jLabelK4IzdatoHeader = "0";
    public String jLabelK5ZadatoHeader = "0";
    public String jLabelK5IzdatoHeader = "0";
    public String jLabelK6ZadatoHeader = "0";
    public String jLabelK6IzdatoHeader = "0";
    public String jLabelK7ZadatoHeader = "0";
    public String jLabelK7IzdatoHeader = "0";
    public String jLabelK8ZadatoHeader = "0";
    public String jLabelK8IzdatoHeader = "0";
    public String jLabelK9ZadatoHeader = "0";
    public String jLabelK9IzdatoHeader = "0";
    public String jLabelK10ZadatoHeader = "0";
    public String jLabelK10IzdatoHeader = "0";
    public String jLabelK11ZadatoHeader = "0";
    public String jLabelK11IzdatoHeader = "0";
    public String jLabelK12ZadatoHeader = "0";
    public String jLabelK12IzdatoHeader = "0";
    public String jLabelZm3 = "0";
    public String jLabelIm3 = "0";
    public String jLabelZSarze = "0";
    public String jLabelISarze = "0";

    public String pozicijaKorpe = "0";

    public String jTextFieldDisplej;

    public String jTextFieldCementText;
    public String jLabelNumberCement;

    public String jLabelVodaText;
    public String jTextFieldNumberVoda;

    public String jTextFieldAditivText;
    public String jTextFieldNumberAditiv1;
    public String jTextFieldNumberAditiv2;

    public String jTextFieldAgregatText;
    public String jTextFieldAgregatNumber;

    public String jTextFieldBrOtpreme;
    public String jTextFieldBrSarze;
    public String jTextFieldRecBeton;

    public String jTextFieldMesalicaText;
    

    /////////////////////////////////////////////////////////
    public boolean jToggleButtonRucAut;
    public boolean jLabelCementSilos1;
    public boolean jLabelCementSilos2;
    public boolean jLabelCementSilos3;
    public boolean jLabelCementPraznjenjeVage;
    public boolean jLabelCementVagaZatvorena;
    public boolean jLabelCementVagaPuna;
    public boolean jLabelVodaSilos;
    public boolean jLabelVodaPraznjenjeVaga;
    public boolean jLabelVodaVagaZatvorena;
    public boolean jLabelVodaVagaPuna;
    public boolean jLabelAditivSilos1;
    public boolean jLabelAditivSilos2;
    public boolean jLabelAditivSilos3;
    public boolean jLabelAditivPraznjenjeVage;
    public boolean jLabelAditivVagaZatvorena;
    public boolean jLabelAditivVagaPuna;
    public boolean jLabelAgregatSilos8_16;
    public boolean jLabelAgregatSilos4_8;
    public boolean jLabelAgregatSilos0_4;
    public boolean jLabelAgregatSilos0_2;
    public boolean jLabelAgregatSilos16_32;
    public boolean jLabelTrakaAgregat;

    public boolean jLabelHidropumpaStatus;
    public boolean jLabelMesalicaStatus;
    public boolean jLabelKompresorStatus;
    
    
    /////////////////////////////////////////////////////
    public boolean writeRegister = false;
    public boolean writeVlaznostRegistre = false;
    public int[] vrednostiVlaznosti = new int[4]; 

    public boolean jTextFieldOcitaj;
    
    private int typeReadDI;

    public Buff() {
        config = new Config();
        jToggleButtonRucAut = false;
        brRegZaCitanje = 68;
        resultRegister = new String[brRegZaCitanje];
        brBoolZaCitanje = 32;
        resultDI = new boolean[brBoolZaCitanje];
        modbus = new Mxl();
        bazaOcitaj = new int[1];
        bazaOcitaj[0] = 0;
        cleanOldValue();
        startCom();
        
        vrednostiVlaznosti[0] = 0;
        vrednostiVlaznosti[1] = 0;
        vrednostiVlaznosti[2] = 0;
        vrednostiVlaznosti[3] = 0;
    }

    public void startCom() {

        ip = config.getIp();
        id = CheckIfInt.checkIfInt(config.getId());
        try {
            modbus.setIp(InetAddress.getByName(ip));
        } catch (UnknownHostException ex) {
            Logger.getLogger(Buff.class.getName()).log(Level.SEVERE, null, ex);
        }
        modbus.setId(id);
        

        startnaAdresaRegistra = CheckIfInt.checkIfInt(config.getStartAddressRegister());
        startnaAdresaDigitalInput = CheckIfInt.checkIfInt(config.getStartAddressDigitalInput());
        startnaAdresaRegistraZaBazu = CheckIfInt.checkIfInt(config.getStartnaAdresaRegistraZaBazu());
        typeReadDI = CheckIfInt.checkIfInt(config.getReadDI());
        modbus.openConnection();
        
       
    }

    @Override
    public void run() {
        while (!MainFrame.restartConn) {
            getRegisters();
            getDigitalInput();
            getRegisterForBaza();
            //modbus.writeRegister(startnaAdresaRegistraZaBazu+1, 1); //4500 + 1
            if (writeRegister) {
                modbus.writeRegister(startnaAdresaRegistraZaBazu, 0);
                writeRegister = false;
            }
            if(writeVlaznostRegistre){
           //    modbus.writeRegisters(startnaAdresaRegistra+59, vrednostiVlaznosti);
               writeVlaznostRegistre = false;
            }
        }
        
    }

    public boolean ping() {
        return modbus.ping();
    }

    

    private String[] getIntToString(int[] ulaz, int brReg) {
        String[] result = new String[brReg];
        for (int i = 0; i < brReg; i++) {
            result[i] = Integer.toString((int) ulaz[i]);
        }
        return result;
    }


    public void getRegisterForBaza() {
        bazaOcitaj = modbus.readRegisters(startnaAdresaRegistraZaBazu, 1);
    }

    public void getRegisters() {
        resultRegister = getIntToString(modbus.readRegisters(startnaAdresaRegistra, brRegZaCitanje), brRegZaCitanje);
        setValueFromRegister(resultRegister);
    }

    public void getDigitalInput() {
        setValueFromDI(resultDI);
        
        if(typeReadDI>=1 && typeReadDI<4){
        citanjeHoldRegistra = modbus.readInputRegisters(startnaAdresaDigitalInput, 2); //startnaAdresaDigitalInput
        getIntToChar(citanjeHoldRegistra[0],0);
        getIntToChar(citanjeHoldRegistra[1],16);
        }else{
            resultDI = modbus.readMX(startnaAdresaDigitalInput, brBoolZaCitanje); //startnaAdresaDigitalInput
        }
    }

    private void getIntToChar(int input, int brReg) {
        String in = Integer.toBinaryString(input);
        if (input > 32767) {
            resultDI[0+brReg] = getBool(in.charAt(15));
            resultDI[1+brReg] = getBool(in.charAt(14));
            resultDI[2+brReg] = getBool(in.charAt(13));
            resultDI[3+brReg] = getBool(in.charAt(12));
            resultDI[4+brReg] = getBool(in.charAt(11));
            resultDI[5+brReg] = getBool(in.charAt(10));
            resultDI[6+brReg] = getBool(in.charAt(9));
            resultDI[7+brReg] = getBool(in.charAt(8));
            resultDI[8+brReg] = getBool(in.charAt(7));
            resultDI[9+brReg] = getBool(in.charAt(6));
            resultDI[10+brReg] = getBool(in.charAt(5));
            resultDI[11+brReg] = getBool(in.charAt(4));
            resultDI[12+brReg] = getBool(in.charAt(3));
            resultDI[13+brReg] = getBool(in.charAt(2));
            resultDI[14+brReg] = getBool(in.charAt(1));
            resultDI[15+brReg] = getBool(in.charAt(0));
        }else if(input<32768 && input>16383){
            resultDI[0+brReg] = getBool(in.charAt(14));
            resultDI[1+brReg] = getBool(in.charAt(13));
            resultDI[2+brReg] = getBool(in.charAt(12));
            resultDI[3+brReg] = getBool(in.charAt(11));
            resultDI[4+brReg] = getBool(in.charAt(10));
            resultDI[5+brReg] = getBool(in.charAt(9));
            resultDI[6+brReg] = getBool(in.charAt(8));
            resultDI[7+brReg] = getBool(in.charAt(7));
            resultDI[8+brReg] = getBool(in.charAt(6));
            resultDI[9+brReg] = getBool(in.charAt(5));
            resultDI[10+brReg] = getBool(in.charAt(4));
            resultDI[11+brReg] = getBool(in.charAt(3));
            resultDI[12+brReg] = getBool(in.charAt(2));
            resultDI[13+brReg] = getBool(in.charAt(1));
            resultDI[14+brReg] = getBool(in.charAt(0));
            resultDI[15+brReg] = false;
        }else if(input<16384 && input>8191){
            resultDI[0+brReg] = getBool(in.charAt(13));
            resultDI[1+brReg] = getBool(in.charAt(12));
            resultDI[2+brReg] = getBool(in.charAt(11));
            resultDI[3+brReg] = getBool(in.charAt(10));
            resultDI[4+brReg] = getBool(in.charAt(9));
            resultDI[5+brReg] = getBool(in.charAt(8));
            resultDI[6+brReg] = getBool(in.charAt(7));
            resultDI[7+brReg] = getBool(in.charAt(6));
            resultDI[8+brReg] = getBool(in.charAt(5));
            resultDI[9+brReg] = getBool(in.charAt(4));
            resultDI[10+brReg] = getBool(in.charAt(3));
            resultDI[11+brReg] = getBool(in.charAt(2));
            resultDI[12+brReg] = getBool(in.charAt(1));
            resultDI[13+brReg] = getBool(in.charAt(0));
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<8192 && input>4095){
            resultDI[0+brReg] = getBool(in.charAt(12));
            resultDI[1+brReg] = getBool(in.charAt(11));
            resultDI[2+brReg] = getBool(in.charAt(10));
            resultDI[3+brReg] = getBool(in.charAt(9));
            resultDI[4+brReg] = getBool(in.charAt(8));
            resultDI[5+brReg] = getBool(in.charAt(7));
            resultDI[6+brReg] = getBool(in.charAt(6));
            resultDI[7+brReg] = getBool(in.charAt(5));
            resultDI[8+brReg] = getBool(in.charAt(4));
            resultDI[9+brReg] = getBool(in.charAt(3));
            resultDI[10+brReg] = getBool(in.charAt(2));
            resultDI[11+brReg] = getBool(in.charAt(1));
            resultDI[12+brReg] = getBool(in.charAt(0));
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<4096 && input>2047){
            resultDI[0+brReg] = getBool(in.charAt(11));
            resultDI[1+brReg] = getBool(in.charAt(10));
            resultDI[2+brReg] = getBool(in.charAt(9));
            resultDI[3+brReg] = getBool(in.charAt(8));
            resultDI[4+brReg] = getBool(in.charAt(7));
            resultDI[5+brReg] = getBool(in.charAt(6));
            resultDI[6+brReg] = getBool(in.charAt(5));
            resultDI[7+brReg] = getBool(in.charAt(4));
            resultDI[8+brReg] = getBool(in.charAt(3));
            resultDI[9+brReg] = getBool(in.charAt(2));
            resultDI[10+brReg] = getBool(in.charAt(1));
            resultDI[11+brReg] = getBool(in.charAt(0));
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<2048 && input>1023){
            resultDI[0+brReg] = getBool(in.charAt(10));
            resultDI[1+brReg] = getBool(in.charAt(9));
            resultDI[2+brReg] = getBool(in.charAt(8));
            resultDI[3+brReg] = getBool(in.charAt(7));
            resultDI[4+brReg] = getBool(in.charAt(6));
            resultDI[5+brReg] = getBool(in.charAt(5));
            resultDI[6+brReg] = getBool(in.charAt(4));
            resultDI[7+brReg] = getBool(in.charAt(3));
            resultDI[8+brReg] = getBool(in.charAt(2));
            resultDI[9+brReg] = getBool(in.charAt(1));
            resultDI[10+brReg] = getBool(in.charAt(0));
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<1024 && input>511){
            resultDI[0+brReg] = getBool(in.charAt(9));
            resultDI[1+brReg] = getBool(in.charAt(8));
            resultDI[2+brReg] = getBool(in.charAt(7));
            resultDI[3+brReg] = getBool(in.charAt(6));
            resultDI[4+brReg] = getBool(in.charAt(5));
            resultDI[5+brReg] = getBool(in.charAt(4));
            resultDI[6+brReg] = getBool(in.charAt(3));
            resultDI[7+brReg] = getBool(in.charAt(2));
            resultDI[8+brReg] = getBool(in.charAt(1));
            resultDI[9+brReg] = getBool(in.charAt(0));
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<512 && input>255){
            resultDI[0+brReg] = getBool(in.charAt(8));
            resultDI[1+brReg] = getBool(in.charAt(7));
            resultDI[2+brReg] = getBool(in.charAt(6));
            resultDI[3+brReg] = getBool(in.charAt(5));
            resultDI[4+brReg] = getBool(in.charAt(4));
            resultDI[5+brReg] = getBool(in.charAt(3));
            resultDI[6+brReg] = getBool(in.charAt(2));
            resultDI[7+brReg] = getBool(in.charAt(1));
            resultDI[8+brReg] = getBool(in.charAt(0));
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<256 && input>127){
            resultDI[0+brReg] = getBool(in.charAt(7));
            resultDI[1+brReg] = getBool(in.charAt(6));
            resultDI[2+brReg] = getBool(in.charAt(5));
            resultDI[3+brReg] = getBool(in.charAt(4));
            resultDI[4+brReg] = getBool(in.charAt(3));
            resultDI[5+brReg] = getBool(in.charAt(2));
            resultDI[6+brReg] = getBool(in.charAt(1));
            resultDI[7+brReg] = getBool(in.charAt(0));
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<128 && input>63){
            resultDI[0+brReg] = getBool(in.charAt(6));
            resultDI[1+brReg] = getBool(in.charAt(5));
            resultDI[2+brReg] = getBool(in.charAt(4));
            resultDI[3+brReg] = getBool(in.charAt(3));
            resultDI[4+brReg] = getBool(in.charAt(2));
            resultDI[5+brReg] = getBool(in.charAt(1));
            resultDI[6+brReg] = getBool(in.charAt(0));
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<64 && input>31){
            resultDI[0+brReg] = getBool(in.charAt(5));
            resultDI[1+brReg] = getBool(in.charAt(4));
            resultDI[2+brReg] = getBool(in.charAt(3));
            resultDI[3+brReg] = getBool(in.charAt(2));
            resultDI[4+brReg] = getBool(in.charAt(1));
            resultDI[5+brReg] = getBool(in.charAt(0));
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<32 && input>15){
            resultDI[0+brReg] = getBool(in.charAt(4));
            resultDI[1+brReg] = getBool(in.charAt(3));
            resultDI[2+brReg] = getBool(in.charAt(2));
            resultDI[3+brReg] = getBool(in.charAt(1));
            resultDI[4+brReg] = getBool(in.charAt(0));
            resultDI[5+brReg] = false;
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<16 && input>7){
            resultDI[0+brReg] = getBool(in.charAt(3));
            resultDI[1+brReg] = getBool(in.charAt(2));
            resultDI[2+brReg] = getBool(in.charAt(1));
            resultDI[3+brReg] = getBool(in.charAt(0));
            resultDI[4+brReg] = false;
            resultDI[5+brReg] = false;
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<8 && input>3){
            resultDI[0+brReg] = getBool(in.charAt(2));
            resultDI[1+brReg] = getBool(in.charAt(1));
            resultDI[2+brReg] = getBool(in.charAt(0));
            resultDI[3+brReg] = false;
            resultDI[4+brReg] = false;
            resultDI[5+brReg] = false;
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<4 && input>1){
            resultDI[0+brReg] = getBool(in.charAt(1));
            resultDI[1+brReg] = getBool(in.charAt(0));
            resultDI[2+brReg] = false;
            resultDI[3+brReg] = false;
            resultDI[4+brReg] = false;
            resultDI[5+brReg] = false;
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }else if(input<2 && input>=0){
            resultDI[0+brReg] = getBool(in.charAt(0));
            resultDI[1+brReg] = false;
            resultDI[2+brReg] = false;
            resultDI[3+brReg] = false;
            resultDI[4+brReg] = false;
            resultDI[5+brReg] = false;
            resultDI[6+brReg] = false;
            resultDI[7+brReg] = false;
            resultDI[8+brReg] = false;
            resultDI[9+brReg] = false;
            resultDI[10+brReg] = false;
            resultDI[11+brReg] = false;
            resultDI[12+brReg] = false;
            resultDI[13+brReg] = false;
            resultDI[14+brReg] = false;
            resultDI[15+brReg] = false;
        }

    }

    private boolean getBool(char a) {
        switch (a) {
            case '1':
                return true;
            case '0':
                return false;
            default:
                return false;
        }

    }

    public boolean isConnected() {
        return modbus.isConnected;
    }

    public void cleanOldValue() {
        for (int i = 0; i < brRegZaCitanje; i++) {
            resultRegister[i] = "0";
        }
        setValueFromRegister(resultRegister);
        for (int i = 0; i < brBoolZaCitanje; i++) {
            resultDI[i] = false;
        }
        setValueFromDI(resultDI);
    }

    private void setValueFromRegister(String[] resultRegister) {
        jLabelK1ZadatoHeader = getUnside(resultRegister[0]);
        jLabelK1IzdatoHeader = getUnside(resultRegister[1]);
        jLabelK2ZadatoHeader = getUnside(resultRegister[2]);
        jLabelK2IzdatoHeader = getUnside(resultRegister[3]);
        jLabelK3ZadatoHeader = getUnside(resultRegister[4]);
        jLabelK3IzdatoHeader = getUnside(resultRegister[5]);
        jLabelK4ZadatoHeader = getUnside(resultRegister[6]);
        jLabelK4IzdatoHeader = getUnside(resultRegister[7]);
        jLabelK5ZadatoHeader = getUnside(resultRegister[8]);
        jLabelK5IzdatoHeader = getUnside(resultRegister[9]);
        
        //aditivi
        jLabelK6ZadatoHeader = getUnside(resultRegister[16]);
        jLabelK6IzdatoHeader = getUnside(resultRegister[17]);
        jLabelK7ZadatoHeader = getUnside(resultRegister[18]);
        jLabelK7IzdatoHeader = getUnside(resultRegister[19]);
        jLabelK8ZadatoHeader = getUnside(resultRegister[20]);
        jLabelK8IzdatoHeader = getUnside(resultRegister[21]);
        
        //voda
        jLabelK9ZadatoHeader = getUnside(resultRegister[22]);
        jLabelK9IzdatoHeader = getUnside(resultRegister[23]);
        
        //cement
        jLabelK10ZadatoHeader = getUnside(resultRegister[10]);
        jLabelK10IzdatoHeader = getUnside(resultRegister[11]);
        jLabelK11ZadatoHeader = getUnside(resultRegister[12]);
        jLabelK11IzdatoHeader = getUnside(resultRegister[13]);
        
        //filer
        jLabelK12ZadatoHeader = getUnside(resultRegister[14]);
        jLabelK12IzdatoHeader = getUnside(resultRegister[15]);
        
        jLabelZm3 = getUnside(resultRegister[24]);
        jLabelIm3 = getUnside(resultRegister[25]);
        jLabelZSarze = getUnside(resultRegister[26]);
        jLabelISarze = getUnside(resultRegister[27]);
        
        jTextFieldDisplej = getUnside(resultRegister[28]);
        
        jTextFieldCementText = getUnside(resultRegister[29]);
        jLabelNumberCement = getUnside(resultRegister[30]);
        
        jLabelVodaText = getUnside(resultRegister[31]);
        jTextFieldNumberVoda = getUnside(resultRegister[32]);
        
        jTextFieldAditivText = getUnside(resultRegister[33]);
        jTextFieldNumberAditiv1 = getUnside(resultRegister[34]);
        
        jTextFieldAgregatText = getUnside(resultRegister[35]);
        jTextFieldAgregatNumber = getUnside(resultRegister[36]);
        
        jTextFieldBrOtpreme = getUnside(resultRegister[37]);
        jTextFieldBrSarze = getUnside(resultRegister[38]);
        
        jTextFieldRecBeton = getUnside(resultRegister[39]);
        pozicijaKorpe = getUnside(resultRegister[40]);
        
        jTextFieldMesalicaText = getUnside(resultRegister[41]);
        
        
        //***************************************************************
        /*
        jLabelK1m3 = resultRegister[42];
        jLabelK2m3 = resultRegister[43];
        jLabelK3m3 = resultRegister[44];
        jLabelK4m3 = resultRegister[45];
        jLabelK5m3 = resultRegister[46];
        jLabelK6m3 = resultRegister[47];
        jLabelK7m3 = resultRegister[48];
        jLabelK8m3 = resultRegister[49];
        jLabelK9m3 = resultRegister[50];
        jLabelK10m3 = resultRegister[51];
        jLabelK11m3 = resultRegister[52];
        jLabelK12m3 = resultRegister[53];
       

        cementSilos1TezinaNizi = resultRegister[54];
        cementSilos1TezinaVisi = resultRegister[55];
        cementSilos2TezinaNizi = resultRegister[56];
        cementSilos2TezinaVisi = resultRegister[57];
        cementSilos3TezinaNizi = resultRegister[58];
        cementSilos3TezinaVisi = resultRegister[59];
        

        izmerenaVlagaFrakcije1 = resultRegister[60];
        izmerenaVlagaFrakcije2 = resultRegister[61];
        izmerenaVlagaFrakcije3 = resultRegister[62];
        izmerenaVlagaFrakcije4 = resultRegister[63];
        
        korigovanaKoličinaFrakcije1 = resultRegister[64];
        korigovanaKoličinaFrakcije2 = resultRegister[65];
        korigovanaKoličinaFrakcije3 = resultRegister[66];
        korigovanaKoličinaFrakcije4 = resultRegister[67];
        */
    }

    private String getUnside(String br){
       int a = Integer.parseInt(br);
       if(a>65000){
           return "0";
       }else{
           return br;
       }
           
    }
    
    private void setValueFromDI(boolean[] resultDI) {
        
        jLabelAgregatSilos0_4 = resultDI[0];
        jLabelAgregatSilos4_8 = resultDI[1];
        jLabelAgregatSilos8_16 = resultDI[2];
        jLabelAgregatSilos16_32 = resultDI[3];
        jLabelAgregatSilos0_2 = resultDI[4];       
        
        
        //jLabelTrakaAgregat = resultDI[5];
        
        jLabelCementSilos1 = resultDI[5];
        jLabelCementSilos2 = resultDI[6];
        jLabelCementSilos3 = resultDI[7];
        jLabelCementPraznjenjeVage = resultDI[8];
        jLabelCementVagaPuna = resultDI[9];
        jLabelCementVagaZatvorena = resultDI[10];
        
        jLabelAditivSilos1 = resultDI[11];
        jLabelAditivSilos2 = resultDI[12];
        jLabelAditivSilos3 = resultDI[13];              
        jLabelAditivPraznjenjeVage = resultDI[14];
        jLabelAditivVagaPuna = resultDI[15];
        jLabelAditivVagaZatvorena = resultDI[16];
        
        jLabelVodaSilos = resultDI[17];
        jLabelVodaPraznjenjeVaga = resultDI[18];
        jLabelVodaVagaPuna = resultDI[19]; 
        jLabelVodaVagaZatvorena = resultDI[20];
        
        
        
        jToggleButtonRucAut = resultDI[0];

        jLabelMesalicaStatus = resultDI[21];
        jLabelKompresorStatus = resultDI[22]; 
        jLabelHidropumpaStatus = resultDI[23];
              
        
    }
}
