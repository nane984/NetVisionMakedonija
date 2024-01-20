/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mxl;

import Frame.MainFrame;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.wimpi.modbus.*;
import net.wimpi.modbus.msg.*;
import net.wimpi.modbus.io.*;
import net.wimpi.modbus.net.*;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.BitVector;

/**
 *
 * @author branko.scekic
 */
public class Mxl {

    private TCPMasterConnection con = null; //the connection
    private ModbusTCPTransaction trans = null; //the transaction

    //****************************Digital input******************************
    private ReadInputDiscretesRequest readReqDI = null; //digital input (read-only) request
    private ReadInputDiscretesResponse readResDI = null; //digital input (read-only) response

    //***************************Digital output******************************
    private ReadCoilsRequest readReqDO = null;  //digital outputs (read) request
    private ReadCoilsResponse readResDO = null; //digital outputs (read) response

    private WriteCoilRequest writeSingleReqDO = null;  //digital output (write) request
    //private WriteCoilRequest writeSingleResDO = null;  //digital output (write) response
    private WriteMultipleCoilsRequest writeReqDO = null;  //digital output (write) request
    //private WriteMultipleCoilsResponse writeResDO = null;  //digital output (write) response

    //****************************Register**********************************
    private ReadInputRegistersRequest readInputReqRegisters = null;   //input register (read) request  (npr sa a/d konvertora) 
    private ReadInputRegistersResponse readInputResRegisters = null;  //input register (read) response  (npr sa a/d konvertora)

    //**************************Registers************************************
    private ReadMultipleRegistersRequest readReqRegisters = null;   //registers (read) request - register
    private ReadMultipleRegistersResponse readResRegister = null;   //registers (read) request

    private WriteSingleRegisterRequest writeSingleReqRegister = null;   //register (write) request
    //private WriteSingleRegisterResponse writeSingleResRegister = null;   //register (write) response
    private WriteMultipleRegistersRequest writeReqMultipleRegister = null;   //registers (write) request
   // private WriteMultipleRegistersResponse writeResMultipleRegister = null;   //registers (write) request

    /* Variables for storing the parameters */
    private InetAddress ip;    //the slave's ip address
    private int port;            //Modbus.DEFAULT_PORT;  //port 502 for modbus (buffer)
    private int repeat;             //a loop for repeating the transaction
    private int id;
    
    public boolean isConnected;
    
    private Register register;

    public Mxl() {
        this.port = 502;
        this.repeat = 0;
        this.id = 1;
        
        isConnected = true;
        
        readReqDI = new ReadInputDiscretesRequest();
        readReqDO = new ReadCoilsRequest();
        readReqRegisters = new ReadMultipleRegistersRequest();
        readInputReqRegisters = new ReadInputRegistersRequest();
        
        writeSingleReqRegister = new WriteSingleRegisterRequest();
        writeReqMultipleRegister  = new WriteMultipleRegistersRequest();
        
        register = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
    }
    
    public Mxl(InetAddress ip, int port, int id) {
        this.ip = ip;
        this.port = port;
        repeat = 0;
        this.id=id;
        
        readReqDI = new ReadInputDiscretesRequest();
        readReqDO = new ReadCoilsRequest();
        readReqRegisters = new ReadMultipleRegistersRequest();
        readInputReqRegisters = new ReadInputRegistersRequest();
        
        writeSingleReqRegister = new WriteSingleRegisterRequest();
        writeReqMultipleRegister  = new WriteMultipleRegistersRequest();
        
        register = ModbusCoupler.getReference().getProcessImageFactory().createRegister();
        
    }

    //*****************seter and getter*****************************************  
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public void setId(int id){
        this.id=id;
    }
    
    //*******************connection**********************************************
    public void openConnection() {
        con = new TCPMasterConnection(ip);
        con.setPort(port);
        trans = new ModbusTCPTransaction(con);
    }

    public void closeConnection() {
        con.close();
    }
    
    public boolean ping(){
        boolean p = false;
        int temp = port;
        try {
            this.port = 7;
            p = ip.isReachable(1000);
        } catch (IOException ex) {
            
            Logger.getLogger(Mxl.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.port = temp;
        return p;
    }

    //*********************read*************************************************
    public boolean[] readDI(int offset, int brDI) { //offset where to start reading from, the number of DI's to read
        readReqDI.setReference(offset);
        readReqDI.setBitCount(brDI);
        readReqDI.setUnitID(id);
        trans.setRequest(readReqDI);
        return executeTransactionDI(brDI);
    }

    public boolean[] readMX(int offset, int brDO_kolikoIhCita) { //offset where to start reading from, the number how many DO's to read
        readReqDO.setReference(offset);
        readReqDO.setBitCount(brDO_kolikoIhCita);
        readReqDO.setUnitID(id);
        trans.setRequest(readReqDO);
        return executeTransactionDO(brDO_kolikoIhCita);
    }

    public int[] readRegisters(int offset, int brReg_kolikoIhCita) { //offset where to start reading from, the number how many Registers to read
        readReqRegisters.setReference(offset);
        readReqRegisters.setWordCount(brReg_kolikoIhCita);
        readReqRegisters.setUnitID(id);
        trans.setRequest(readReqRegisters);
        return executeTransactionRegistri(brReg_kolikoIhCita);
    }

    public int[] readInputRegisters(int offset, int brReg_kolikoIhCita) { //offset where to start reading from, the number how many Registers to read
        readInputReqRegisters.setReference(offset);
        readInputReqRegisters.setWordCount(brReg_kolikoIhCita);
        readInputReqRegisters.setUnitID(id);
        trans.setRequest(readInputReqRegisters);
        return executeTransactionInputRegistri(brReg_kolikoIhCita);
    }

    //*********************************write************************************
    public void writeMX(int offset, boolean vrednost) { //offset where to start reading from, the number how many DO's to read
        writeSingleReqDO = new WriteCoilRequest(offset, vrednost);
        writeSingleReqDO.setCoil(vrednost);
        writeSingleReqDO.setUnitID(id);
        trans.setRequest(writeSingleReqDO);
        transaction();
        
    }
    
    
    public void writeMX(int offset, boolean[] brDO_kolikoIhUpisuje) { //offset where to start reading from, the number how many DO's to read
        writeReqDO = new WriteMultipleCoilsRequest(offset, brDO_kolikoIhUpisuje.length);
        BitVector bv = getVector(brDO_kolikoIhUpisuje);
        writeReqDO.setCoils(bv);
        writeReqDO.setUnitID(id);
        trans.setRequest(writeReqDO);
        transaction();
        
    }

    public void writeRegister(int offset, int value){
        writeSingleReqRegister.setReference(offset);
        register.setValue(value);      
        writeSingleReqRegister.setRegister(register);
        writeSingleReqRegister.setUnitID(id);
        trans.setRequest(writeSingleReqRegister);
        transaction();
    }
    
    public void writeRegisters(int offset, int[] values){  
        writeReqMultipleRegister.setReference(offset); 
        Register[] reg = getRegisters(values);
        writeReqMultipleRegister.setRegisters(reg);
        writeReqMultipleRegister.setUnitID(id);
        trans.setRequest(writeReqMultipleRegister);
        transaction();
    }
    
    
    
    
    //************************transaction***************************************
    private void transaction() {
        try {
            trans.execute();
            isConnected = true;
        } catch (ModbusSlaveException ex) {
            
            Logger.getLogger(Mxl.class.getName()).log(Level.SEVERE, null, ex);
            isConnected = false;
            closeConnection();
            MainFrame.restartConn = true;
        } catch (ModbusException ex) {
            Logger.getLogger(Mxl.class.getName()).log(Level.SEVERE, null, ex);
            isConnected = false;
            closeConnection();
            MainFrame.restartConn = true;
        }
    }

    //************************readExecution*************************************
    private boolean[] executeTransactionDI(int brDI) {
        int k = 0;
        do {
            transaction();
            readResDI = (ReadInputDiscretesResponse) trans.getResponse();
            boolean[] result = new boolean[brDI];
            for (int i = 0; i < brDI; i++) {
                result[i] = readResDI.getDiscreteStatus(i);
                //System.out.println("Vrednost je " + readResDI.getDiscreteStatus(i) );
            }
            k++;
            return result;
        } while (k < repeat);
    }

    private boolean[] executeTransactionDO(int br_kolikocita) {
        int k = 0;
        do {
            transaction();
            readResDO = (ReadCoilsResponse) trans.getResponse();
            
            boolean[] result = new boolean[br_kolikocita];
            for (int i = 0; i < br_kolikocita; i++) {
                result[i] = readResDO.getCoilStatus(i);
                //System.out.println("Vrednost je " + readResDO.getCoilStatus(i) );
            }
            k++;
            return result;
        } while (k < repeat);
    }

    private int[] executeTransactionRegistri(int brReg) {
        int k = 0;
        do {
            transaction();
            readResRegister = (ReadMultipleRegistersResponse) trans.getResponse();
            
            int[] result = new int[brReg];

            for (int i = 0; i < brReg; i++) {
                result[i] = readResRegister.getRegisterValue(i);
            }
            k++;
            return result;
        } while (k < repeat);
    }

    private int[] executeTransactionInputRegistri(int brReg) {
        int k = 0;
        
        do {
            transaction();
            readInputResRegisters = (ReadInputRegistersResponse) trans.getResponse();
            int[] result = new int[brReg];
            for (int i = 0; i < brReg; i++) {
                result[i] = readInputResRegisters.getRegisterValue(i);
                //System.out.println("Input registers Status=" + readInputResRegisters.getRegisterValue(i));
            }
            k++;
            return result;
        } while (k < repeat);
        
    }


    
    //****************pomocne metode********************************************
    private Register[] getRegisters(int[] values) {
        Register[] reg = new Register[values.length];
        for(int i=0; i<values.length; i++){
            reg[i]= ModbusCoupler.getReference().getProcessImageFactory().createRegister();
            reg[i].setValue(values[i]);
        }
        return reg; 
    }

    private BitVector getVector(boolean[] brDO_kolikoIhUpisuje) {
        BitVector bv = new BitVector(brDO_kolikoIhUpisuje.length);
        for(int i=0; i<brDO_kolikoIhUpisuje.length; i++){
            bv.setBit(i, brDO_kolikoIhUpisuje[i]);
        }
        return bv;
    }
    
    
}
