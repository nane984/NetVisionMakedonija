/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import DbService.KService;
import DbService.KomponenteService;
import Utility.Convert;
import Frame.MainFrame;
import java.awt.Color;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import path.ZaglavljePath;

/**
 *
 * @author branko.scekic
 */
public class ZbirniIzvestaj extends javax.swing.JFrame {

    /**
     * Creates new form Print1
     *
     * @param otp
     *
     */
    public ZbirniIzvestaj(Db.Otpremnica otp) {

        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane6.getViewport().setBackground(Color.WHITE);

        komponenteDb = new KomponenteService();
        komp = new KService();
        otprem = otp;

        
            loadZaglavlje();
            fillLable(otp);

       

    }

    private void loadZaglavlje() {
        try {
            path = new ZaglavljePath();
            //JOptionPane.showMessageDialog(null,path.getZaglavlje());
            jLabelZaglavlje.setIcon(new javax.swing.ImageIcon(path.getZaglavlje())); // NOI18N
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void fillLable(Db.Otpremnica otpremnica) {
        brOtpremnice = Integer.toString(otpremnica.getBrotpremnice());
        markaBetona = otpremnica.getReceptura();
        kupac = otpremnica.getKupac();
        kolicina = otprem.getKolicinaostvarenam3().toString() + " m3";
        gradiliste = otpremnica.getGradiliste();
        datum = Convert.convertDateToStringDate(otpremnica.getDatumvreme());
        vreme = Convert.convertDateToStringTime(otpremnica.getDatumvreme());
        masinista = otpremnica.getMasinista();
        vozac = otpremnica.getVozac();
        brVozila = otpremnica.getRegtablice();

        podaci = new HashMap();
        podaci.put("brOtpreme", brOtpremnice);
        podaci.put("markBetona", markaBetona);
        podaci.put("kupac", kupac);
        podaci.put("masinista", masinista);
        podaci.put("gradiliste", gradiliste);
        podaci.put("vozac", vozac);
        podaci.put("datum", datum);
        podaci.put("vreme", vreme);
        podaci.put("brVozila", brVozila);
        podaci.put("kolicina", kolicina);

        fillTable(otpremnica.getBrotpremnice());
    }

    private void fillTable(int idOtpremnice) {
        try {
            List<Db.Komponente> result = komponenteDb.getKomponenteWhere(idOtpremnice);
            List<Db.K> k = komp.getKomponente();

            DecimalFormat df2 = new DecimalFormat("#.##");

            double ukupnok1Ostvareno = 0;
            double ukupnok1Zadato = 0;
            double ukupnok2Ostvareno = 0;
            double ukupnok2Zadato = 0;
            double ukupnok3Ostvareno = 0;
            double ukupnok3Zadato = 0;
            double ukupnok4Ostvareno = 0;
            double ukupnok4Zadato = 0;
            double ukupnok5Ostvareno = 0;
            double ukupnok5Zadato = 0;
            double ukupnok6Ostvareno = 0;
            double ukupnok6Zadato = 0;
            double ukupnok7Ostvareno = 0;
            double ukupnok7Zadato = 0;
            double ukupnok8Ostvareno = 0;
            double ukupnok8Zadato = 0;
            double ukupnok9Ostvareno = 0;
            double ukupnok9Zadato = 0;
            double ukupnok10Ostvareno = 0;
            double ukupnok10Zadato = 0;

            int size = 0;

            for (Db.K auto : k) {
                if (auto.getIfcheck() == 1) {
                    size++;
                }
            }

            int i = 0;
            Object[][] obj = new Object[3][size + 1];
            for (Db.Komponente auto : result) {

                //obj[i][0] = auto.getBrSarze();              // da bi imali r.b kolonu stavili smo size + 1;
                obj[0][0] = "zadato";
                obj[1][0] = "izdato";
                obj[2][0] = "%";

                if (k.get(0).getIfcheck() == 1) {
                    //obj[i][1] = auto.getK1Ostv();
                    ukupnok1Ostvareno += auto.getK1Ostv().doubleValue();
                    ukupnok1Zadato += auto.getK1Zad().doubleValue();
                    jLabelKolicina.setText(otprem.getKolicinaostvarenam3().toString() + " m3");
                    /*
                    if(i == 0){
                         ukupnok1Zadato = auto.getK1Zad().doubleValue() * otprem.getKolicinazadatam3().doubleValue();
                         obj[0][1] = df2.format(ukupnok1Zadato);
                         jLabelKolicina.setText(otprem.getKolicinazadatam3().toString()+ " m3");
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][1] = df2.format(ukupnok1Zadato);
                        obj[1][1] = df2.format(ukupnok1Ostvareno);
                        obj[2][1] = df2.format((ukupnok1Ostvareno / ukupnok1Zadato) * 100) + "  %";
                    }

                }
                if (k.get(1).getIfcheck() == 1) {
                    //obj[i][2] = auto.getK2Ostv();
                    ukupnok2Ostvareno += auto.getK2Ostv().doubleValue();
                    ukupnok2Zadato += auto.getK2Zad().doubleValue();

                    /*
                     if (i == 0) {
                        ukupnok2Zadato = auto.getK2Zad().doubleValue() * otprem.getKolicinazadatam3().doubleValue();
                        obj[0][2] = df2.format(ukupnok2Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][2] = df2.format(ukupnok2Zadato);
                        obj[1][2] = df2.format(ukupnok2Ostvareno);
                        obj[2][2] = df2.format((ukupnok2Ostvareno / ukupnok2Zadato) * 100) + "  %";
                    }

                }

                if (k.get(2).getIfcheck() == 1) {
                    //obj[i][3] = auto.getK3Ostv();
                    ukupnok3Ostvareno += auto.getK3Ostv().doubleValue();

                    ukupnok3Zadato += auto.getK3Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok3Zadato = auto.getK3Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][3] = df2.format(ukupnok3Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][3] = df2.format(ukupnok3Zadato);
                        obj[1][3] = df2.format(ukupnok3Ostvareno);
                        obj[2][3] = df2.format((ukupnok3Ostvareno / ukupnok3Zadato) * 100) + "  %";
                    }
                }

                if (k.get(3).getIfcheck() == 1) {
                    //obj[i][4] = auto.getK4Ostv();
                    ukupnok4Ostvareno += auto.getK4Ostv().doubleValue();

                    ukupnok4Zadato += auto.getK4Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok4Zadato = auto.getK4Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][4] = df2.format(ukupnok4Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][4] = df2.format(ukupnok4Zadato);
                        obj[1][4] = df2.format(ukupnok4Ostvareno);
                        obj[2][4] = df2.format((ukupnok4Ostvareno / ukupnok4Zadato) * 100) + "  %";
                    }
                }

                if (k.get(4).getIfcheck() == 1) {
                    //obj[i][5] = auto.getK5Ostv();
                    ukupnok5Ostvareno += auto.getK5Ostv().doubleValue();

                    ukupnok5Zadato += auto.getK5Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok5Zadato = auto.getK5Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][5] = df2.format(ukupnok5Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][5] = df2.format(ukupnok5Zadato);
                        obj[1][5] = df2.format(ukupnok5Ostvareno);
                        obj[2][5] = df2.format((ukupnok5Ostvareno / ukupnok5Zadato) * 100) + "  %";
                    }
                }

                if (k.get(5).getIfcheck() == 1) {
                    //obj[i][6] = auto.getK6Ostv();
                    ukupnok6Ostvareno += auto.getK6Ostv().doubleValue();

                    ukupnok6Zadato += auto.getK6Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok6Zadato = auto.getK6Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][6] = df2.format(ukupnok6Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][6] = df2.format(ukupnok6Zadato);
                        obj[1][6] = df2.format(ukupnok6Ostvareno);
                        obj[2][6] = df2.format((ukupnok6Ostvareno / ukupnok6Zadato) * 100) + "  %";
                    }
                }

                if (k.get(6).getIfcheck() == 1) {
                    //obj[i][7] = auto.getK7Ostv();
                    ukupnok7Ostvareno += auto.getK7Ostv().doubleValue();

                    ukupnok7Zadato += auto.getK7Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok7Zadato = auto.getK7Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][7] = df2.format(ukupnok7Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][7] = df2.format(ukupnok7Zadato);
                        obj[1][7] = df2.format(ukupnok7Ostvareno);
                        obj[2][7] = df2.format((ukupnok7Ostvareno / ukupnok7Zadato) * 100) + "  %";
                    }
                }

                if (k.get(7).getIfcheck() == 1) {
                    //obj[i][8] = auto.getK8Ostv();
                    ukupnok8Ostvareno += auto.getK8Ostv().doubleValue();

                    ukupnok8Zadato += auto.getK8Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok8Zadato = auto.getK8Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][8] = df2.format(ukupnok8Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][8] = df2.format(ukupnok8Zadato);
                        obj[1][8] = df2.format(ukupnok8Ostvareno);
                        obj[2][8] = df2.format((ukupnok8Ostvareno / ukupnok8Zadato) * 100) + "  %";
                    }
                }

                if (k.get(8).getIfcheck() == 1) {
                    //obj[i][9] = auto.getK9Ostv();
                    ukupnok9Ostvareno += auto.getK9Ostv().doubleValue();
                    ukupnok9Zadato += auto.getK9Zad().doubleValue();

                    /*
                    if(i == 0){
                         ukupnok9Zadato = auto.getK9Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][9] = df2.format(ukupnok9Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][9] = df2.format(ukupnok9Zadato);
                        obj[1][9] = df2.format(ukupnok9Ostvareno);
                        obj[2][9] = df2.format((ukupnok9Ostvareno / ukupnok9Zadato) * 100) + "  %";
                    }
                }

                if (k.get(9).getIfcheck() == 1) {
                    //obj[i][10] = auto.getK10Ostv();
                    ukupnok10Ostvareno += auto.getK10Ostv().doubleValue();
                    ukupnok10Zadato += auto.getK10Zad().doubleValue();

                    /*
                     if(i == 0){
                         ukupnok10Zadato = auto.getK10Zad().doubleValue()* otprem.getKolicinazadatam3().doubleValue();
                         obj[0][10] = df2.format(ukupnok10Zadato);
                    }
                     */
                    if (i == result.size() - 1) {
                        obj[0][10] = df2.format(ukupnok10Zadato);
                        obj[1][10] = df2.format(ukupnok10Ostvareno);
                        obj[2][10] = df2.format((ukupnok10Ostvareno / ukupnok10Zadato) * 100) + "  %";
                    }
                }

                i++;
            }

            String[] zaglavlje = new String[size + 1];

            for (int m = 0; m < size; m++) {
                zaglavlje[0] = "br s.";
                zaglavlje[m + 1] = k.get(m).getKomponenta();

            }

            DefaultTableModel model = new DefaultTableModel(obj, zaglavlje);
            jTable1.setModel(model);
            jTable1.setRowMargin(2);
            jTable1.setRowHeight(30);
            jTable1.setShowGrid(true);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(65);

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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabelZaglavlje = new javax.swing.JLabel();
        jLabelBrOtpr = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelBrOtpremnice = new javax.swing.JLabel();
        jLabelMarkaBetona = new javax.swing.JLabel();
        jLabelKupac = new javax.swing.JLabel();
        jLabelKolicina = new javax.swing.JLabel();
        jLabelGradiliste = new javax.swing.JLabel();
        jLabelDatum = new javax.swing.JLabel();
        jLabelVreme = new javax.swing.JLabel();
        jLabelMasinista = new javax.swing.JLabel();
        jLabelVozac = new javax.swing.JLabel();
        jLabelBrVozila = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Zbirni Izvestaj", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(950, 1400));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(900, 1200));

        jLabelZaglavlje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelBrOtpr.setText("Broj otpreme: ");

        jLabel5.setText("Marka betona:  ");

        jLabel7.setText("Kupac:  ");

        jLabel8.setText("Gradilište:  ");

        jLabel6.setText("Količina:  ");

        jLabel3.setText("Datum: ");

        jLabel4.setText("Vreme: ");

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(null);
        jScrollPane6.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null}
            },
            new String [] {
                "r.b", "Frakcija 1", "Frakcija 2", "Frakcija 3", "Frakcija 4", "Cement", "Aditiv 1", "Aditiv 2", "Voda"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setOpaque(false);
        jTable1.setRowHeight(19);
        jTable1.setRowMargin(3);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable1);

        jLabel9.setText("Mašinista:  ");

        jLabel10.setText("Vozač:  ");

        jLabel11.setText("Broj vozila:  ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelVozac, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelBrVozila, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelMasinista, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelBrOtpr)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelMarkaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelZaglavlje, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabelZaglavlje, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelBrOtpr))
                            .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5))
                            .addComponent(jLabelKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelMarkaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabelKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 745, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMasinista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11))
                    .addComponent(jLabelVozac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBrVozila, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 916, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        jMenu1.setText("Stampa");

        jMenuItem1.setText("Stampaj");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.PORTRAIT);

        //PageFormat postformat = pjob.pageDialog(preformat);
        Paper pPaper = preformat.getPaper();

        //pPaper.setImageableArea(55.0, 15.0, pPaper.getWidth() - 95, pPaper.getHeight() - 25);
        pPaper.setImageableArea(5.0, 5.0, pPaper.getWidth() - 5, pPaper.getHeight() - 5);
        preformat.setPaper(pPaper);

        //If user does not hit cancel then print.
        //if (preformat != postformat) {
        //Set print component
        pjob.setPrintable(new PrintReport(jPanel4), preformat);
        if (pjob.printDialog()) {
            try {
                pjob.setJobName("Izvestaj " + jLabelBrOtpremnice.getText());
                pjob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBrOtpr;
    private javax.swing.JLabel jLabelBrOtpremnice;
    private javax.swing.JLabel jLabelBrVozila;
    private javax.swing.JLabel jLabelDatum;
    private javax.swing.JLabel jLabelGradiliste;
    private javax.swing.JLabel jLabelKolicina;
    private javax.swing.JLabel jLabelKupac;
    private javax.swing.JLabel jLabelMarkaBetona;
    private javax.swing.JLabel jLabelMasinista;
    private javax.swing.JLabel jLabelVozac;
    private javax.swing.JLabel jLabelVreme;
    private javax.swing.JLabel jLabelZaglavlje;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private ZaglavljePath path;
    private KomponenteService komponenteDb;
    private KService komp;
    private Db.Otpremnica otprem;
    private Map<String, Object> podaci;

    private String brOtpremnice;
    private String markaBetona;
    private String kupac;
    private String masinista;
    private String gradiliste;
    private String vozac;
    private String datum;
    private String vreme;
    private String brVozila;
    private Object kolicina;

    private void printReport() {

        try {
            DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            //JRTableModelDataSource datasource = new JRTableModelDataSource(de);
            JasperReport jr = JasperCompileManager.compileReport("src\\reports\\ReportDetaljniIzvestaj.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, podaci, new JRTableModelDataSource(de));
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            Logger.getLogger(DetaljniIzvestaj1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
