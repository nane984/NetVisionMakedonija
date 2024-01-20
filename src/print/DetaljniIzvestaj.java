/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import DbService.Aditiv1Service;
import DbService.Aditiv2Service;
import DbService.Aditiv3Service;
import DbService.KService;
import DbService.KomponenteService;
import DbService.KonzbetonaService;
import DbService.OtpremnicaService;
import DbService.SertifikatService;
import DbService.SpecbetonaService;
import DbService.StandbetonaService;
import DbService.SvojstvaService;
import DbService.VrstaCementaService1;
import DbService.VrstaCementaService2;
import DbService.VrstaFilerService;
import DbService.ZrnomaxService;
import Utility.Convert;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import tools.DoubleFormat;
import tools.VelicinaZrna;

/**
 *
 * @author branko.scekic
 */
public class DetaljniIzvestaj extends javax.swing.JFrame {

    /**
     * Creates new form Print1
     *
     *
     */
    public DetaljniIzvestaj() {

        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        //jScrollPane6.getViewport().setBackground(Color.WHITE);

        this.komponenteDb = new KomponenteService();
        this.komp = new KService();
        
        this.otpService = new OtpremnicaService();
        
        this.vrstaCementaDb = new VrstaCementaService1();
        this.vrstaCementa2Db = new VrstaCementaService2();
        this.aditiv1Db = new Aditiv1Service();
        this.aditiv2Db = new Aditiv2Service();
        this.aditiv3Db = new Aditiv3Service();
        this.filerDb = new VrstaFilerService();
        this.sertifikatDb = new SertifikatService();
        this.zrnomaxDb = new ZrnomaxService();
        this.svojstvaDb = new SvojstvaService();
        this.hlorDb = new StandbetonaService();
        this.konzDb = new KonzbetonaService();
        this.specDb = new SpecbetonaService();
    }
    
    public void getIzvestaj(Db.Otpremnica otp) {
        this.otprem = otp;
        fillLable(otprem);
    }
    
    private void getDataForChange() {

        try {
            List<Db.Vrstacementa> cem1 = vrstaCementaDb.getVrstaCementa();
            String[] cementString1 = new String[cem1.size()];
            
            List<Db.Vrstacementa2> cem2 = vrstaCementa2Db.getVrstaCementa();
            String[] cementString2 = new String[cem2.size()];
            
            List<Db.Aditiv1> ad1 = aditiv1Db.getVrstaCementa();
            String[] aditiv1String = new String[ad1.size()];
            
            List<Db.Aditiv2> ad2 = aditiv2Db.getVrstaCementa();
            String[] aditiv2String = new String[ad2.size()];
            
            List<Db.Aditiv3> ad3 = aditiv3Db.getVrstaCementa();
            String[] aditiv3String = new String[ad3.size()];
            
            List<Db.Filer> fil = filerDb.getVrstaFiler();
            String[] filerString = new String[fil.size()];
            
            List<Db.Sertifikat> ser = sertifikatDb.getSertifikat();
            String[] sertifikatString = new String[ser.size()];
            
            List<Db.Zrnomax> zrm = zrnomaxDb.getZrnomax();
            String[] zrnomaxString = new String[zrm.size()];
            
            List<Db.Standbetona> hlr = hlorDb.getStandBetona();
            String[] hloridmaxString = new String[hlr.size()];
            
            List<Db.Konzbetona> konz = konzDb.getKonzistencijaBetona();
            String[] konzistencujamaxString = new String[konz.size()];
            
            List<Db.Specbetona> spec = specDb.getSpecBetona();
            String[] specbetonamaxString = new String[spec.size()];
            
            List<Db.Svojstva> svj = svojstvaDb.getSvojstva();
            String[] svojstvaString = new String[svj.size()];
            
            
            int i=0;
            for(Db.Vrstacementa sb:cem1){
                cementString1[i]=sb.getVrstacem();
                i++;
            }
            
            int m=0;
            for(Db.Vrstacementa2 sb2:cem2){
                cementString2[m]=sb2.getVrstacem();
                m++;
            }
            
            int j=0;
            for(Db.Aditiv1 a1:ad1){
                aditiv1String[j]=a1.getText();
                j++;
            }

            int k=0;
            for(Db.Aditiv2 a2:ad2){
                aditiv2String[k]=a2.getText();
                k++;
            }
            
            int l=0;
            for(Db.Aditiv3 a3:ad3){
                aditiv3String[l]=a3.getText();
                l++;
            }
            
            int n=0;
            for(Db.Filer filer:fil){
                filerString[n]=filer.getFiler();
                n++;
            }
            int f=0;
            for(Db.Sertifikat sert:ser){
                sertifikatString[f]=sert.getSertifikat();
                f++;
            }
            int s=0;
            for(Db.Zrnomax sb2:zrm){
                zrnomaxString[s]=sb2.getZrnomax();
                s++;
            }
            int x=0;
            for(Db.Standbetona hl:hlr){
                hloridmaxString[x]=hl.getText();
                x++;
            }
            int r=0;
            for(Db.Konzbetona ko:konz){
                konzistencujamaxString[r]=ko.getText();
                r++;
            }
            int q=0;
            for(Db.Specbetona sp:spec){
                specbetonamaxString[q]=sp.getText();
                q++;
            }
            int w=0;
            for(Db.Svojstva sb2:svj){
                svojstvaString[w]=sb2.getSvojstva();
                w++;
            }
            
            List<String> cement1 = new ArrayList<>(Arrays.asList(cementString1)).stream().distinct().collect(Collectors.toList());
            jComboBoxCement1.setModel(new DefaultComboBoxModel(cement1.toArray()));
            
            List<String> cement2 = new ArrayList<>(Arrays.asList(cementString2)).stream().distinct().collect(Collectors.toList());
            jComboBoxCement2.setModel(new DefaultComboBoxModel(cement2.toArray()));
            
            List<String> zrnomax = new ArrayList<>(Arrays.asList(zrnomaxString)).stream().distinct().collect(Collectors.toList());
            jComboBoxZrnomax.setModel(new DefaultComboBoxModel(zrnomax.toArray()));
            
            List<String> hloridmax = new ArrayList<>(Arrays.asList(hloridmaxString)).stream().distinct().collect(Collectors.toList());
            jComboSadrzajnaHlorida.setModel(new DefaultComboBoxModel(hloridmax.toArray()));
            
            List<String> konzistencijamax = new ArrayList<>(Arrays.asList(konzistencujamaxString)).stream().distinct().collect(Collectors.toList());
            jComboKonzistencija.setModel(new DefaultComboBoxModel(konzistencijamax.toArray()));
            
            List<String> specbetonamax = new ArrayList<>(Arrays.asList(specbetonamaxString)).stream().distinct().collect(Collectors.toList());
            jComboSpecBet.setModel(new DefaultComboBoxModel(specbetonamax.toArray()));
            
            List<String> svojstva = new ArrayList<>(Arrays.asList(svojstvaString)).stream().distinct().collect(Collectors.toList());
            jComboBoxSvojstva.setModel(new DefaultComboBoxModel(svojstva.toArray()));
            
            List<String> aditiv1 = new ArrayList<>(Arrays.asList(aditiv1String)).stream().distinct().collect(Collectors.toList());
            jComboBoxAditiv1.setModel(new DefaultComboBoxModel(aditiv1.toArray()));
            
            List<String> aditiv2 = new ArrayList<>(Arrays.asList(aditiv2String)).stream().distinct().collect(Collectors.toList());
            jComboBoxAditiv2.setModel(new DefaultComboBoxModel(aditiv2.toArray()));
            
             List<String> aditiv3 = new ArrayList<>(Arrays.asList(aditiv3String)).stream().distinct().collect(Collectors.toList());
            jComboBoxAditiv3.setModel(new DefaultComboBoxModel(aditiv3.toArray()));
            
            List<String> filer = new ArrayList<>(Arrays.asList(filerString)).stream().distinct().collect(Collectors.toList());
            jComboBoxFiler.setModel(new DefaultComboBoxModel(filer.toArray()));
            
            List<String> sertifikat = new ArrayList<>(Arrays.asList(sertifikatString)).stream().distinct().collect(Collectors.toList());
            jComboBoxSertifikat.setModel(new DefaultComboBoxModel(sertifikat.toArray()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void fillLable(Db.Otpremnica otpremnica) {
        
        getDataForChange();

        jLabelBrOtpremnice.setText(Integer.toString(otpremnica.getBrotpremnice()));
        jLabelMarkaBetona.setText(otpremnica.getReceptura());
        jLabelKupac.setText(otpremnica.getKupac());
        jLabelGradiliste.setText(otpremnica.getGradiliste());
        jLabelDatum.setText(Convert.convertDateToStringDate(otpremnica.getDatumvreme()));
        jLabelMasinista.setText(otpremnica.getMasinista());
        jLabelVozac.setText(otpremnica.getVozac());
        jLabelBrVozila.setText(otpremnica.getRegtablice());
        jComboSpecBet.setSelectedItem(otpremnica.getSpecbetona());
        jComboKonzistencija.setSelectedItem(otpremnica.getKonzbetona());
        jComboBoxCement1.setSelectedItem(otpremnica.getVrstacementa());
        jComboBoxCement2.setSelectedItem(otpremnica.getVrstacementa2());
        jComboBoxFiler.setSelectedItem(otpremnica.getVrstafilera());
        jComboBoxSertifikat.setSelectedItem(otpremnica.getSertifikat());
        jComboBoxAditiv1.setSelectedItem(otpremnica.getAditiva1());
        jComboBoxAditiv2.setSelectedItem(otpremnica.getAditiva2());
        jComboBoxAditiv3.setSelectedItem(otpremnica.getAditiva3());
        jComboBoxZrnomax.setSelectedItem(otpremnica.getZrnomax());
        jComboSadrzajnaHlorida.setSelectedItem(otpremnica.getStandbetona());
        jComboBoxSvojstva.setSelectedItem(otpremnica.getSvojstva());
        //jTextArea1.setText(otpremnica.getNapomena());
        jLabelZadatoBetona.setText(DoubleFormat.getRounFloat(otpremnica.getKolicinazadatam3(),2));
        jLabelVremePocetak.setText(Convert.convertDateToStringTime(otpremnica.getDatumvreme()));
                    

        fillTable(otpremnica.getBrotpremnice());

    }

    public void fillTable(int idOtpremnice) {
        try {
            List<Db.Komponente> result = komponenteDb.getKomponenteWhere(idOtpremnice);
            List<Db.K> k = komp.getKomponente();

            DecimalFormat df2 = new DecimalFormat("#.##");
            DecimalFormat df1 = new DecimalFormat("##.#");
            DecimalFormat df0 = new DecimalFormat("###");

            double ukupnok1Ostvareno = 0;   
            double ukupnok2Ostvareno = 0;
            double ukupnok3Ostvareno = 0;
            double ukupnok4Ostvareno = 0;
            double ukupnok5Ostvareno = 0;
            double ukupnok6Ostvareno = 0;
            double ukupnok7Ostvareno = 0;
            double ukupnok8Ostvareno = 0;
            double ukupnok9Ostvareno = 0;
            double ukupnok10Ostvareno = 0;
            double ukupnok11Ostvareno = 0;
            double ukupnok12Ostvareno = 0;
            
            double ukupnok9Zadato = 0;
            double ukupnok10Zadato = 0;
            double ukupnok11Zadato = 0;
            

            int size = 0;

            for (Db.K auto : k) {
                if (auto.getIfcheck() == 1) {
                    size++;
                }
            }

            int i = 0;
            Object[][] obj = new Object[2][size + 2];
            for (Db.Komponente auto : result) {

                //obj[0][0] = "zadato za 1 m3 (kg)";
                obj[0][0] = "По рецептура за 1m3 (kg)";
                obj[1][0] = "Издадено вкупно m3 (kg)";

                if (k.get(0).getIfcheck() == 1) {
                    jLabelVremeKraj.setText(Convert.convertDateToStringTime(auto.getDatumVreme()));
                    jLabelKolicina.setText(DoubleFormat.getRounFloat(auto.getKolicinaostvarenakg(),2));
                    
                    ukupnok1Ostvareno += auto.getK1Ostv().doubleValue();
                    
                    System.out.println("0 k1Zadato  "+result.get(0).getK1Zad());
                    System.out.println("size-1 k1Zadato  "+result.get(result.size()-1).getK1Zad());
                    
                    System.out.println("0 k2Zadato  "+result.get(0).getK2Zad());
                    System.out.println("size-1 k2Zadato  "+result.get(result.size()-1).getK2Zad());
    
                    if (i == 0) {
                        obj[0][1] = df0.format((auto.getK1Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][1] = df0.format(ukupnok1Ostvareno);
                    }

                }
                if (k.get(1).getIfcheck() == 1) {
                    ukupnok2Ostvareno += auto.getK2Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][2] = df0.format((auto.getK2Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][2] = df0.format(ukupnok2Ostvareno);
                    }

                }

                if (k.get(2).getIfcheck() == 1) {
                    ukupnok3Ostvareno += auto.getK3Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][3] = df0.format((auto.getK3Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][3] = df0.format(ukupnok3Ostvareno);
                    }
                }

                if (k.get(3).getIfcheck() == 1) {
                    ukupnok4Ostvareno += auto.getK4Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][4] = df0.format((auto.getK4Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][4] = df0.format(ukupnok4Ostvareno);
                    }
                }

                if (k.get(4).getIfcheck() == 1) {
                    ukupnok5Ostvareno += auto.getK5Ostv().doubleValue();  
                    if (i == 0) {
                        obj[0][5] = df0.format((auto.getK5Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][5] = df0.format(ukupnok5Ostvareno);
                    }
                }

                if (k.get(5).getIfcheck() == 1) {
                    ukupnok6Ostvareno += auto.getK6Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][6] = df2.format((auto.getK6Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][6] = df2.format(ukupnok6Ostvareno);
                    }
                }

                if (k.get(6).getIfcheck() == 1) {
                    ukupnok7Ostvareno += auto.getK7Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][7] = df2.format((auto.getK7Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][7] = df2.format(ukupnok7Ostvareno);
                    }
                }

                if (k.get(7).getIfcheck() == 1) {
                    ukupnok8Ostvareno += auto.getK8Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][8] = df2.format((auto.getK8Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][8] = df2.format(ukupnok8Ostvareno);
                    }
                }

                if (k.get(8).getIfcheck() == 1) {    
                    ukupnok9Ostvareno += auto.getK9Ostv().doubleValue();
                    if (i == 0) {
                        ukupnok9Zadato = auto.getK9Zad().doubleValue(); //voda
                        obj[0][9] = df0.format((auto.getK9Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][9] = df0.format(ukupnok9Ostvareno);
                    }
                }

                if (k.get(9).getIfcheck() == 1) {
                    ukupnok10Ostvareno += auto.getK10Ostv().doubleValue();
                    
                    if (i == 0) {
                        ukupnok10Zadato = auto.getK10Zad().doubleValue(); //cement1
                        obj[0][10] = df0.format((auto.getK10Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][10] = df0.format(ukupnok10Ostvareno);
                    }
                }
                
                if (k.get(10).getIfcheck() == 1) {
                    ukupnok11Ostvareno += auto.getK11Ostv().doubleValue();
                    
                    if (i == 0) {
                        ukupnok11Zadato = auto.getK11Zad().doubleValue(); //cement2
                        obj[0][11] = df0.format((auto.getK11Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][11] = df0.format(ukupnok11Ostvareno);
                    }
                    
                }
                
                if (k.get(11).getIfcheck() == 1) {
                    ukupnok12Ostvareno += auto.getK12Ostv().doubleValue();
                    if (i == 0) {
                        obj[0][12] = df0.format((auto.getK12Zad()));
                    }
                    if (i == result.size() - 1) {
                        obj[1][12] = df0.format(ukupnok12Ostvareno);
                    }
                }
                
                i++;
            }
            
            float ukupnoRecept = result.get(0).getK1Zad() + result.get(0).getK2Zad() + 
                    result.get(0).getK3Zad() + result.get(0).getK4Zad() + result.get(0).getK5Zad()
                    + result.get(0).getK6Zad() + result.get(0).getK7Zad() + result.get(0).getK8Zad()
                    + result.get(0).getK9Zad() + result.get(0).getK10Zad()
                    + result.get(0).getK11Zad() + result.get(0).getK12Zad();
            
            double ukupno = ukupnok1Ostvareno + ukupnok2Ostvareno + ukupnok3Ostvareno + ukupnok4Ostvareno + ukupnok5Ostvareno + ukupnok6Ostvareno + ukupnok7Ostvareno
                     + ukupnok8Ostvareno + ukupnok9Ostvareno + ukupnok10Ostvareno + ukupnok11Ostvareno + ukupnok12Ostvareno;
            
            obj[0][size+1] = DoubleFormat.getRounDouble(ukupnoRecept, 1);
            obj[1][size+1] = DoubleFormat.getRounDoubleRetDouble(ukupno,1);
            
            
            
            jLabelMaxVCFaktor.setText(Double.toString(VelicinaZrna.deliXYZ(DoubleFormat.getRounDoubleRetDouble(ukupnok9Zadato,0), DoubleFormat.getRounDoubleRetDouble(ukupnok10Zadato,0), DoubleFormat.getRounDoubleRetDouble(ukupnok11Zadato,0))));
            jLabelVCFaktor.setText(Double.toString(VelicinaZrna.deliXYZ(ukupnok9Ostvareno, ukupnok10Ostvareno, ukupnok11Ostvareno)));

            String[] zaglavlje = new String[size + 2];

            for (int m = 0; m < size; m++) {
                zaglavlje[0] = " ";
                zaglavlje[m + 1] = k.get(m).getKomponenta();
                zaglavlje[size+1] = "Вкупно";
                
                //System.out.println(k.get(m).getKomponenta() + "  " + m);
            }

            DefaultTableModel model = new DefaultTableModel(obj, zaglavlje);
            jTable1.setModel(model);
            jTable1.setRowMargin(2);
            jTable1.setRowHeight(31);
            jTable1.setShowGrid(true);
            jTable1.getColumnModel().getColumn(0).setMinWidth(175);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(195);
            
            DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
            rightRender.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(13).setCellRenderer(rightRender);

            printReport();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabelStandBet1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jSeparator24 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jSeparator29 = new javax.swing.JSeparator();
        jLabel44 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabelBrOtpr2 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabelKupac = new javax.swing.JLabel();
        jLabelGradiliste = new javax.swing.JLabel();
        jLabelMarkaBetona = new javax.swing.JLabel();
        jLabelZadatoBetona = new javax.swing.JLabel();
        jComboBoxCement1 = new javax.swing.JComboBox<>();
        jComboBoxCement2 = new javax.swing.JComboBox<>();
        jComboBoxAditiv1 = new javax.swing.JComboBox<>();
        jComboBoxAditiv2 = new javax.swing.JComboBox<>();
        jComboBoxAditiv3 = new javax.swing.JComboBox<>();
        jLabelMaxVCFaktor = new javax.swing.JLabel();
        jLabelVCFaktor = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabelBrVozila = new javax.swing.JLabel();
        jLabelVozac = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabelVremePocetak = new javax.swing.JLabel();
        jLabelVremeKraj = new javax.swing.JLabel();
        jLabelDatum = new javax.swing.JLabel();
        jLabelMasinista = new javax.swing.JLabel();
        jLabelKolicina = new javax.swing.JLabel();
        jLabelBrOtpremnice = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxFiler = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        jComboBoxSertifikat = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jComboBoxZrnomax = new javax.swing.JComboBox<>();
        jComboBoxSvojstva = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jComboSadrzajnaHlorida = new javax.swing.JComboBox<>();
        jComboKonzistencija = new javax.swing.JComboBox<>();
        jComboSpecBet = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Otprema");
        setMaximumSize(new java.awt.Dimension(1280, 831));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(1200, 850));
        jPanel5.setLayout(null);

        jLabel13.setText("Купувач");
        jPanel5.add(jLabel13);
        jLabel13.setBounds(20, 110, 130, 20);

        jLabelStandBet1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jPanel5.add(jLabelStandBet1);
        jLabelStandBet1.setBounds(740, 140, 200, 0);

        jLabel21.setText("Класа на конзистенција");
        jPanel5.add(jLabel21);
        jLabel21.setBounds(570, 180, 140, 14);

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("ИСПОРАЧАН БЕТОН [m3]");
        jPanel5.add(jLabel28);
        jLabel28.setBounds(880, 510, 170, 30);

        jLabel32.setText("Р.Б. ВОЗИЛО");
        jPanel5.add(jLabel32);
        jLabel32.setBounds(20, 490, 160, 20);

        jLabel37.setText("Цемент");
        jPanel5.add(jLabel37);
        jLabel37.setBounds(20, 240, 140, 14);

        jSeparator24.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator24.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSeparator24.setOpaque(true);
        jPanel5.add(jSeparator24);
        jSeparator24.setBounds(0, 288, 1220, 2);

        jLabel15.setText("ВОЗАЧ");
        jPanel5.add(jLabel15);
        jLabel15.setBounds(190, 490, 120, 20);

        jLabel40.setText("Адитив 2");
        jPanel5.add(jLabel40);
        jLabel40.setBounds(820, 240, 150, 14);

        jSeparator29.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator29.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jSeparator29.setOpaque(true);
        jPanel5.add(jSeparator29);
        jSeparator29.setBounds(480, 602, 110, 0);

        jLabel44.setText("Адитив 1");
        jPanel5.add(jLabel44);
        jLabel44.setBounds(650, 240, 120, 14);

        jLabel29.setText("Марка на бетон - класа на јакост");
        jPanel5.add(jLabel29);
        jLabel29.setBounds(20, 180, 200, 14);

        jLabelBrOtpr2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelBrOtpr2.setText("ИСПРАТНИЦА БР");
        jPanel5.add(jLabelBrOtpr2);
        jLabelBrOtpr2.setBounds(40, 60, 170, 30);

        jLabel48.setText("Време крај на утовар");
        jPanel5.add(jLabel48);
        jLabel48.setBounds(20, 620, 140, 14);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("ЕУРОИНГ - Гевгелија");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.add(jPanel6);
        jPanel6.setBounds(770, 720, 370, 50);

        jLabel50.setText("ТРАНСПОРТ [km]");
        jPanel5.add(jLabel50);
        jLabel50.setBounds(460, 490, 200, 20);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("ПРОИЗВОДИТЕЛ");
        jPanel5.add(jLabel51);
        jLabel51.setBounds(840, 680, 200, 30);

        jLabel52.setText("Адитив 3");
        jPanel5.add(jLabel52);
        jLabel52.setBounds(1010, 240, 150, 14);

        jLabel53.setText("Филер");
        jPanel5.add(jLabel53);
        jLabel53.setBounds(390, 240, 100, 14);

        jLabel30.setText("Макс В/Ц Фактор");
        jPanel5.add(jLabel30);
        jLabel30.setBounds(20, 300, 110, 14);

        jLabel33.setText("В/Ц Фактор");
        jPanel5.add(jLabel33);
        jLabel33.setBounds(250, 300, 110, 14);

        jLabel22.setText("Зadadeno m3");
        jPanel5.add(jLabel22);
        jLabel22.setBounds(630, 490, 160, 20);

        jLabel16.setText("Градилиште");
        jPanel5.add(jLabel16);
        jLabel16.setBounds(480, 110, 230, 16);

        jLabel54.setText("Време почетак утовар");
        jPanel5.add(jLabel54);
        jLabel54.setBounds(20, 580, 140, 14);

        jLabel17.setText("Време на почетон на истовар");
        jPanel5.add(jLabel17);
        jLabel17.setBounds(20, 680, 170, 16);
        jPanel5.add(jTextField7);
        jTextField7.setBounds(200, 670, 150, 30);

        jLabel23.setText("Време на крај на истовар");
        jPanel5.add(jLabel23);
        jLabel23.setBounds(20, 730, 170, 16);
        jPanel5.add(jTextField8);
        jTextField8.setBounds(200, 720, 150, 30);

        jLabel34.setText("Класа на изложеност");
        jPanel5.add(jLabel34);
        jLabel34.setBounds(400, 180, 160, 14);

        jLabel18.setText("Декларирани својства");
        jPanel5.add(jLabel18);
        jLabel18.setBounds(980, 180, 150, 14);

        jLabelKupac.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelKupac.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelKupac);
        jLabelKupac.setBounds(20, 140, 430, 20);

        jLabelGradiliste.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelGradiliste.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelGradiliste);
        jLabelGradiliste.setBounds(470, 140, 400, 20);

        jLabelMarkaBetona.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelMarkaBetona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMarkaBetona.setText("***");
        jLabelMarkaBetona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelMarkaBetona);
        jLabelMarkaBetona.setBounds(20, 210, 370, 20);

        jLabelZadatoBetona.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelZadatoBetona.setText("***");
        jLabelZadatoBetona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelZadatoBetona);
        jLabelZadatoBetona.setBounds(630, 510, 150, 30);

        jComboBoxCement1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxCement1);
        jComboBoxCement1.setBounds(20, 260, 160, 20);

        jComboBoxCement2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxCement2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCement2ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBoxCement2);
        jComboBoxCement2.setBounds(200, 260, 170, 20);

        jComboBoxAditiv1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxAditiv1);
        jComboBoxAditiv1.setBounds(650, 260, 160, 20);

        jComboBoxAditiv2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxAditiv2);
        jComboBoxAditiv2.setBounds(820, 260, 180, 20);

        jComboBoxAditiv3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxAditiv3);
        jComboBoxAditiv3.setBounds(1010, 260, 180, 20);

        jLabelMaxVCFaktor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelMaxVCFaktor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelMaxVCFaktor.setText("***");
        jLabelMaxVCFaktor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelMaxVCFaktor);
        jLabelMaxVCFaktor.setBounds(20, 320, 170, 23);

        jLabelVCFaktor.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelVCFaktor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVCFaktor.setText("***");
        jLabelVCFaktor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelVCFaktor);
        jLabelVCFaktor.setBounds(250, 320, 160, 23);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);
        jScrollPane3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(18);
        jTable1.setRowSelectionAllowed(false);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(20, 360, 1160, 90);

        jLabelBrVozila.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelBrVozila.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBrVozila.setText("***");
        jLabelBrVozila.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelBrVozila);
        jLabelBrVozila.setBounds(20, 520, 150, 20);

        jLabelVozac.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelVozac.setText("***");
        jLabelVozac.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelVozac);
        jLabelVozac.setBounds(190, 520, 240, 20);
        jPanel5.add(jTextField1);
        jTextField1.setBounds(460, 510, 90, 30);

        jLabelVremePocetak.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelVremePocetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVremePocetak.setText("***");
        jLabelVremePocetak.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelVremePocetak);
        jLabelVremePocetak.setBounds(200, 570, 150, 30);

        jLabelVremeKraj.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelVremeKraj.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelVremeKraj.setText("***");
        jLabelVremeKraj.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelVremeKraj);
        jLabelVremeKraj.setBounds(200, 610, 150, 30);

        jLabelDatum.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelDatum.setText("***");
        jLabelDatum.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelDatum);
        jLabelDatum.setBounds(460, 570, 130, 30);

        jLabelMasinista.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelMasinista.setText("***");
        jLabelMasinista.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(jLabelMasinista);
        jLabelMasinista.setBounds(460, 630, 250, 30);

        jLabelKolicina.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabelKolicina.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelKolicina.setText("***");
        jPanel5.add(jLabelKolicina);
        jLabelKolicina.setBounds(1050, 510, 130, 30);

        jLabelBrOtpremnice.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabelBrOtpremnice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelBrOtpremnice.setText("123");
        jPanel5.add(jLabelBrOtpremnice);
        jLabelBrOtpremnice.setBounds(210, 60, 120, 30);

        jLabel3.setText("ДАТА");
        jPanel5.add(jLabel3);
        jLabel3.setBounds(460, 550, 80, 20);

        jLabel4.setText("ОПЕРАТЕР");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(460, 600, 100, 30);

        jComboBoxFiler.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxFiler);
        jComboBoxFiler.setBounds(390, 260, 180, 20);

        jLabel55.setText("Цемент 2");
        jPanel5.add(jLabel55);
        jLabel55.setBounds(200, 240, 100, 14);

        jComboBoxSertifikat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxSertifikat);
        jComboBoxSertifikat.setBounds(880, 130, 300, 30);

        jLabel8.setText("Сертификационо тело");
        jPanel5.add(jLabel8);
        jLabel8.setBounds(880, 110, 230, 16);

        jLabel19.setText("Садржина на хлорид");
        jPanel5.add(jLabel19);
        jLabel19.setBounds(720, 180, 140, 14);

        jComboBoxZrnomax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxZrnomax);
        jComboBoxZrnomax.setBounds(870, 210, 100, 20);

        jComboBoxSvojstva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboBoxSvojstva);
        jComboBoxSvojstva.setBounds(980, 210, 160, 20);

        jLabel20.setText("Макс. зрно Dmax");
        jPanel5.add(jLabel20);
        jLabel20.setBounds(870, 180, 100, 14);

        jLabel24.setText("Време на пристигнување на објекат");
        jPanel5.add(jLabel24);
        jLabel24.setBounds(400, 690, 230, 16);
        jPanel5.add(jTextField9);
        jTextField9.setBounds(400, 720, 150, 30);

        jComboSadrzajnaHlorida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboSadrzajnaHlorida);
        jComboSadrzajnaHlorida.setBounds(720, 210, 130, 20);

        jComboKonzistencija.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboKonzistencija);
        jComboKonzistencija.setBounds(570, 210, 140, 20);

        jComboSpecBet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel5.add(jComboSpecBet);
        jComboSpecBet.setBounds(400, 210, 160, 20);

        jScrollPane1.setViewportView(jPanel5);

        jMenu1.setText("Stampa");

        jMenuItem1.setText("Stampaj");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Detaljni izvestaj");

        jMenuItem2.setText("Detaljni izvestaj");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Sacuvaj izmene");

        jMenuItem3.setText("Sacuvaj izmene");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private double checkFloatIsNull(Float vr){
        if(vr==null){
            vr = 0f;
        }
        return vr;
        
    }
    public BufferedImage createImage(JPanel panel) {

        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        g.dispose();

        return bi;
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       //if(jTextArea1.getText().isEmpty()){
      //     jTextArea1.setText("");
      // }
        
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);
        
        

        Paper pPaper = preformat.getPaper();
        
        
        pPaper.setImageableArea(5.0, 5.0, pPaper.getWidth() - 10, pPaper.getHeight() - 10);
        preformat.setPaper(pPaper);

    
        //Set print component
        pjob.setPrintable(new PrintReport(jPanel5), preformat);
        if (pjob.printDialog()) {
            try {
                pjob.setJobName(jLabelBrOtpremnice.getText()+ "_" + jLabelKupac.getText() );
                pjob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
         updateCementAd1Ad2();
        //otpService.updateNapomena(otprem.getBrotpremnice(), jTextArea1.getText());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            DetaljniIzvestaj1 detIzv = new DetaljniIzvestaj1(otprem, this);
            Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
            detIzv.setMaximumSize(DimMax);
            detIzv.setExtendedState(JFrame.MAXIMIZED_BOTH);
            detIzv.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske u komunikaciji sa bazom ili je broj otpremnice nepoznat");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        updateCementAd1Ad2();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jComboBoxCement2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCement2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCement2ActionPerformed

    private void updateCementAd1Ad2(){
        otpService.updateCementAd1Ad2Ad3(otprem.getBrotpremnice(), jComboBoxCement1.getSelectedItem().toString(), 
                jComboBoxCement2.getSelectedItem().toString(), jComboBoxAditiv1.getSelectedItem().toString(), 
                jComboBoxAditiv2.getSelectedItem().toString(), jComboBoxAditiv3.getSelectedItem().toString(),
                jComboBoxFiler.getSelectedItem().toString(), jComboBoxSertifikat.getSelectedItem().toString(),
                jComboBoxZrnomax.getSelectedItem().toString(), jComboBoxSvojstva.getSelectedItem().toString(),
                jComboSpecBet.getSelectedItem().toString(),jComboKonzistencija.getSelectedItem().toString(),
                jComboSadrzajnaHlorida.getSelectedItem().toString());
    }
    
    
    private void printReport() {
        //try {
        /* 
            DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            JasperReport jr = JasperCompileManager.compileReport("src\\reports\\ReportDetaljniIzvestaj.jrxml");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, podaci, new JRTableModelDataSource(de));
            JasperViewer.viewReport(jasperPrint,false);
         */

        //} catch (JRException ex) {
        //   Logger.getLogger(DetaljniIzvestaj.class.getName()).log(Level.SEVERE, null, ex);
        //}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBoxAditiv1;
    private javax.swing.JComboBox<String> jComboBoxAditiv2;
    private javax.swing.JComboBox<String> jComboBoxAditiv3;
    private javax.swing.JComboBox<String> jComboBoxCement1;
    private javax.swing.JComboBox<String> jComboBoxCement2;
    private javax.swing.JComboBox<String> jComboBoxFiler;
    private javax.swing.JComboBox<String> jComboBoxSertifikat;
    private javax.swing.JComboBox<String> jComboBoxSvojstva;
    private javax.swing.JComboBox<String> jComboBoxZrnomax;
    private javax.swing.JComboBox<String> jComboKonzistencija;
    private javax.swing.JComboBox<String> jComboSadrzajnaHlorida;
    private javax.swing.JComboBox<String> jComboSpecBet;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelBrOtpr2;
    private javax.swing.JLabel jLabelBrOtpremnice;
    private javax.swing.JLabel jLabelBrVozila;
    private javax.swing.JLabel jLabelDatum;
    private javax.swing.JLabel jLabelGradiliste;
    private javax.swing.JLabel jLabelKolicina;
    private javax.swing.JLabel jLabelKupac;
    private javax.swing.JLabel jLabelMarkaBetona;
    private javax.swing.JLabel jLabelMasinista;
    private javax.swing.JLabel jLabelMaxVCFaktor;
    private javax.swing.JLabel jLabelStandBet1;
    private javax.swing.JLabel jLabelVCFaktor;
    private javax.swing.JLabel jLabelVozac;
    private javax.swing.JLabel jLabelVremeKraj;
    private javax.swing.JLabel jLabelVremePocetak;
    private javax.swing.JLabel jLabelZadatoBetona;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

   
    private final KomponenteService komponenteDb;
    private final KService komp;
    private Db.Otpremnica otprem;
    private final OtpremnicaService otpService;
    
    private final VrstaCementaService1 vrstaCementaDb;
    private final VrstaCementaService2 vrstaCementa2Db;
    private final Aditiv1Service aditiv1Db;
    private final Aditiv2Service aditiv2Db;
    private final Aditiv3Service aditiv3Db;
    private final VrstaFilerService filerDb;
    private final SertifikatService sertifikatDb;
    private final ZrnomaxService zrnomaxDb;
    private final StandbetonaService hlorDb;
    private final KonzbetonaService konzDb;
    private final SpecbetonaService specDb;
    private final SvojstvaService svojstvaDb;
}
