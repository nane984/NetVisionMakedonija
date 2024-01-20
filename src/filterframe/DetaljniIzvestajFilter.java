/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterframe;

import DbService.KomponenteService;
import DbService.OtpremnicaService;
import Frame.AccessModified;
import Utility.Convert;
import Utility.JcomboBox;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import print.DetaljniIzvestaj;

/**
 *
 * @author brasa
 */


public class DetaljniIzvestajFilter extends javax.swing.JFrame {

    /**
     * Creates new form DetaljniIzvestajFilter
     */
    public DetaljniIzvestajFilter(OtpremnicaService otpremnicaDB, KomponenteService komponenteDb) {
        initComponents();
        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        jComboBoxOtpremnica = new JcomboBox();
        jComboBoxKupac = new JcomboBox();
        jComboBoxGradiliste = new JcomboBox();
        jComboVozac = new JcomboBox();
        jComboBoxtablice = new JcomboBox();
        jComboBoxMasinista = new JcomboBox();
        jComboBoxMarkaBetona = new JcomboBox();

        this.otpremnicaDB = otpremnicaDB;
        this.komponenteDb = komponenteDb;

        addComboBox();
        addDataPicker();
        //loadTable();
        getTableWhere();
        reloadComboBox();
        
        print = new DetaljniIzvestaj();  
    }

    private void addComboBox() {

        List<String> proba = new ArrayList<String>();
        proba.add("masinista");

        List<String> proba2 = new ArrayList<String>();
        proba2.add("otpremnica");

        List<String> proba3 = new ArrayList<String>();
        proba3.add("gradiliste");

        List<String> proba4 = new ArrayList<String>();
        proba4.add("vozac");

        List<String> proba5 = new ArrayList<String>();
        proba5.add("tablice");

        List<String> proba6 = new ArrayList<String>();
        proba6.add("kupac");

        List<String> proba7 = new ArrayList<String>();
        proba7.add("marka betona");

        jComboBoxOtpremnica.loadBox(proba2);
        jComboBoxKupac.loadBox(proba6);
        jComboBoxGradiliste.loadBox(proba3);
        jComboVozac.loadBox(proba4);
        jComboBoxtablice.loadBox(proba5);
        jComboBoxMasinista.loadBox(proba);
        jComboBoxMarkaBetona.loadBox(proba7);

        javax.swing.GroupLayout jPanelComboBox1Layout = new javax.swing.GroupLayout(jPanelComboBox1);
        jPanelComboBox1.setLayout(jPanelComboBox1Layout);
        jPanelComboBox1Layout.setHorizontalGroup(
                jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComboBox1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxOtpremnica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboVozac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxtablice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(176, Short.MAX_VALUE))
        );
        jPanelComboBox1Layout.setVerticalGroup(
                jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComboBox1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jComboBoxOtpremnica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel18)
                                        .addComponent(jComboBoxKupac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel14)
                                        .addComponent(jComboBoxGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel15)
                                        .addComponent(jComboVozac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(jComboBoxtablice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        jPanelConmboBox2.setBackground(new java.awt.Color(204, 204, 204));
        jPanelConmboBox2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Datum od :");

        jLabel12.setText("Vreme od:");

        jLabel17.setText("Masinista:");

        jLabel5.setText("Datum do :");

        jLabel13.setText("Vreme od:");

        jLabel19.setText("Marka betona:");

        javax.swing.GroupLayout jPanelConmboBox2Layout = new javax.swing.GroupLayout(jPanelConmboBox2);
        jPanelConmboBox2.setLayout(jPanelConmboBox2Layout);
        jPanelConmboBox2Layout.setHorizontalGroup(
                jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                                                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                                                .addGap(0, 206, Short.MAX_VALUE)
                                                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(170, 170, 170))
                                                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jComboBoxMasinista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBoxMarkaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelConmboBox2Layout.setVerticalGroup(
                jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(jComboBoxMasinista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGap(25, 25, 25)
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(jLabel13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel19)
                                        .addComponent(jComboBoxMarkaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                )));

    }

    private void reloadComboBox() {

        String[] brOtpremnice;
        String[] kupac;
        String[] gradiliste;
        String[] vozac;
        String[] tablice;
        String[] masinista;
        String[] markaBetona;
        try {
            List<Db.Otpremnica> result = otpremnicaDB.getOtpremnice();

            brOtpremnice = new String[result.size()];
            kupac = new String[result.size()];
            gradiliste = new String[result.size()];
            vozac = new String[result.size()];
            tablice = new String[result.size()];
            masinista = new String[result.size()];
            markaBetona = new String[result.size()];

            for (int i = 0; i < result.size(); i++) {
                brOtpremnice[i] = Integer.toString(result.get(i).getBrotpremnice());
                kupac[i] = result.get(i).getKupac();
                gradiliste[i] = result.get(i).getGradiliste();
                vozac[i] = result.get(i).getVozac();
                tablice[i] = result.get(i).getRegtablice();
                masinista[i] = result.get(i).getMasinista();
                markaBetona[i] = result.get(i).getReceptura();

            }
        } catch (Exception e) {

            brOtpremnice = new String[]{""};
            kupac = new String[]{""};
            gradiliste = new String[]{""};
            vozac = new String[]{""};
            tablice = new String[]{""};
            masinista = new String[]{""};
            markaBetona = new String[]{""};
        }
        List<String> brOt = new ArrayList<>(Arrays.asList(brOtpremnice)).stream().distinct().collect(Collectors.toList());
        List<String> kup = new ArrayList<>(Arrays.asList(kupac)).stream().distinct().collect(Collectors.toList());
        List<String> gradil = new ArrayList<>(Arrays.asList(gradiliste)).stream().distinct().collect(Collectors.toList());
        List<String> voz = new ArrayList<>(Arrays.asList(vozac)).stream().distinct().collect(Collectors.toList());
        List<String> tab = new ArrayList<>(Arrays.asList(tablice)).stream().distinct().collect(Collectors.toList());
        List<String> masin = new ArrayList<>(Arrays.asList(masinista)).stream().distinct().collect(Collectors.toList());
        List<String> mbeton = new ArrayList<>(Arrays.asList(markaBetona)).stream().distinct().collect(Collectors.toList());

        jComboBoxOtpremnica.loadBox(brOt);
        jComboBoxKupac.loadBox(kup);
        jComboBoxGradiliste.loadBox(gradil);
        jComboVozac.loadBox(voz);
        jComboBoxtablice.loadBox(tab);
        jComboBoxMasinista.loadBox(masin);
        jComboBoxMarkaBetona.loadBox(mbeton);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelComboBox1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanelConmboBox2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 153, 255));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "r.b", "Br. otpreme", "Kupac", "Gradiliste", "Vozac", "Tablice", "Masinista", "Marka betona", "Kolicina betona", "Datum", "Vreme"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanelComboBox1.setBackground(new java.awt.Color(204, 204, 204));
        jPanelComboBox1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel14.setText("Gradilište:");

        jLabel15.setText("Vozac:");

        jLabel16.setText("Tablice vozila:");

        jLabel3.setText("Broj otpreme:");

        jLabel18.setText("Kupac:");

        javax.swing.GroupLayout jPanelComboBox1Layout = new javax.swing.GroupLayout(jPanelComboBox1);
        jPanelComboBox1.setLayout(jPanelComboBox1Layout);
        jPanelComboBox1Layout.setHorizontalGroup(
            jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComboBox1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addGroup(jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(257, Short.MAX_VALUE))
        );
        jPanelComboBox1Layout.setVerticalGroup(
            jPanelComboBox1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelComboBox1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        jPanelConmboBox2.setBackground(new java.awt.Color(204, 204, 204));
        jPanelConmboBox2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Datum od :");

        jLabel12.setText("Vreme od:");

        jLabel17.setText("Masinista:");

        jLabel5.setText("Datum do :");

        jLabel13.setText("Vreme od:");

        jLabel19.setText("Marka betona:");

        javax.swing.GroupLayout jPanelConmboBox2Layout = new javax.swing.GroupLayout(jPanelConmboBox2);
        jPanelConmboBox2.setLayout(jPanelConmboBox2Layout);
        jPanelConmboBox2Layout.setHorizontalGroup(
            jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 224, Short.MAX_VALUE)
                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(170, 170, 170))
        );
        jPanelConmboBox2Layout.setVerticalGroup(
            jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConmboBox2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(25, 25, 25)
                .addGroup(jPanelConmboBox2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setBackground(new java.awt.Color(0, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Detaljni izvestaj");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel11.setOpaque(true);

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Search_24x24.png"))); // NOI18N
        jButton1.setText("Pretraga");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stampaj zbirno otpreme");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanelComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanelConmboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelConmboBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addDataPicker() {

        TimePickerSettings timeSettingsOd = new TimePickerSettings();
        timeSettingsOd.setColor(TimePickerSettings.TimeArea.TimePickerTextValidTime, Color.blue);
        //timeSettingsOd.initialTime = LocalTime.now();
        timeSettingsOd.generatePotentialMenuTimes(TimeIncrement.FiveMinutes, null, null);
        timeSettingsOd.setDisplaySpinnerButtons(true);
        timeSettingsOd.use24HourClockFormat();

        DatePickerSettings dateSettingsOd = new DatePickerSettings();
        dateSettingsOd.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dateSettingsOd.setVisibleTodayButton(true);
        dateSettingsOd.setFormatForDatesCommonEra("dd.MM.yyyy");
        dateSettingsOd.setColor(DatePickerSettings.DateArea.TextClearLabel, Color.blue);

        TimePickerSettings timeSettingsDo = new TimePickerSettings();
        timeSettingsDo.setColor(TimePickerSettings.TimeArea.TimePickerTextValidTime, Color.blue);
        // timeSettingsDo.initialTime = LocalTime.now();
        timeSettingsDo.generatePotentialMenuTimes(TimeIncrement.FiveMinutes, null, null);
        timeSettingsDo.setDisplaySpinnerButtons(true);

        DatePickerSettings dateSettingsDo = new DatePickerSettings();
        dateSettingsDo.setFirstDayOfWeek(DayOfWeek.MONDAY);
        dateSettingsDo.setVisibleTodayButton(true);
        dateSettingsDo.setFormatForDatesCommonEra("dd.MM.yyyy");
        dateSettingsDo.setColor(DatePickerSettings.DateArea.TextClearLabel, Color.blue);
        timeSettingsDo.use24HourClockFormat();

        datePickerOd = new DatePicker(dateSettingsOd);
        datePickerOd.setSize(148, 30);
        datePickerOd.setLocation(104, 46);

        timePickerOd = new TimePicker(timeSettingsOd);
        timePickerOd.setSize(148, 35);
        timePickerOd.setLocation(104, 85);
        timePickerOd.setTime(LocalTime.MIN);

        datePickerDo = new DatePicker(dateSettingsDo);
        datePickerDo.setSize(148, 30);
        datePickerDo.setLocation(390, 46);

        timePickerDo = new TimePicker(timeSettingsDo);
        timePickerDo.setSize(148, 35);
        timePickerDo.setLocation(390, 85);
        timePickerDo.setTime(LocalTime.MAX);

        jPanelConmboBox2.add(datePickerOd);
        jPanelConmboBox2.add(timePickerOd);
        jPanelConmboBox2.add(datePickerDo);
        jPanelConmboBox2.add(timePickerDo);

    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (SwingUtilities.isRightMouseButton(evt)) {
            accessModified = new AccessModified(model, otpremnicaDB);
            accessModified.setVisible(true);
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            print.getIzvestaj(otpremnicaDB.getOtpremnica(Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString())));   
            print.setVisible(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getTableWhere();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered

    }//GEN-LAST:event_jTable1MouseEntered

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        podaci = new HashMap();
        podaci.put("markBetona", "");
        podaci.put("kupac", "");
        podaci.put("datumOd", "");
        podaci.put("vremeOd", "");
        podaci.put("gradiliste", "");
        podaci.put("datumDo", "");
        podaci.put("vremeDo", "");
        podaci.put("masinista", "");
        podaci.put("vozac", "");
        podaci.put("brVozila", "");

        try {
            DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            JRTableModelDataSource datasource = new JRTableModelDataSource(de);
            JasperReport jr = JasperCompileManager.compileReport("C:\\NetVision\\reports\\ReportOtpremnice.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, podaci, datasource);
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            try {
                File currentDirFile = new File(".");
                String helper = currentDirFile.getAbsolutePath();
                String currentDir = helper.substring(0, helper.length() - currentDirFile.getCanonicalPath().length());//this line may need a try-catch block
                JOptionPane.showMessageDialog(null,currentDir);
                JOptionPane.showMessageDialog(null, ex);
                //Logger.getLogger(DetaljniIzvestaj.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex1) {
                Logger.getLogger(DetaljniIzvestajFilter.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void loadTable() {
        try {
            // otpremnicaDB = new OtpremnicaService();
            List<Db.Otpremnica> result = otpremnicaDB.getOtpremnice();
            fillTable(result);

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }

    }

    private void fillTable(List<Db.Otpremnica> result) {
        int i = 0;
        Object[][] obj = new Object[result.size()][11];
        for (Db.Otpremnica auto : result) {
            obj[i][0] = i + 1;
            obj[i][1] = auto.getBrotpremnice();
            obj[i][2] = auto.getKupac();
            obj[i][3] = auto.getGradiliste();
            obj[i][4] = auto.getVozac();
            obj[i][5] = auto.getRegtablice();
            obj[i][6] = auto.getMasinista();
            obj[i][7] = auto.getReceptura();
            obj[i][8] = komponenteDb.getLastKomponentWhere(auto.getBrotpremnice()).getKolicinaostvarenakg();
            //obj[i][8] = "BLA BLA";
            obj[i][9] = Convert.convertDateToStringDate(auto.getDatumvreme());
            obj[i][10] = Convert.convertDateToStringTime(auto.getDatumvreme());

            i++;
        }

        model = new DefaultTableModel(obj, new Object[]{"r.b", "Br. otpreme", "Kupac", "Gradiliste", "Vozac", "Tablice", "Masinista", "Marka betona", "Kolicina betona", "Datum", "Vreme"});
        jTable1.setModel(model);
        jTable1.setRowMargin(4);
        jTable1.setRowHeight(40);
        jTable1.setShowGrid(true);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
        jTable1.setDefaultEditor(Object.class, null);

    }

    private void getTableWhere() {
        try {
            otpremnicaDB = new OtpremnicaService();

            if (datePickerOd.getText().isEmpty() && datePickerDo.getText().isEmpty()) {
                List<Db.Otpremnica> result;
                if (jComboBoxOtpremnica.getSelectedItem().toString().length() == 0) {
                    result = otpremnicaDB.getOtpremnicaWhereOrderOtp(jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                } else {
                    result = otpremnicaDB.getOtpremnicaWhereOrderOtp(Integer.parseInt(jComboBoxOtpremnica.getSelectedItem().toString()), jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                }
                fillTable(result);

            } else if (!datePickerOd.getText().isEmpty() && !datePickerDo.getText().isEmpty()) {
                List<Db.Otpremnica> result;
                if (jComboBoxOtpremnica.getSelectedItem().toString().length() == 0) {
                    result = otpremnicaDB.getOtpremnicaWhereDateOdDoOrderOtp(jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                } else {
                    result = otpremnicaDB.getOtpremnicaWhereDateOdDoOrderOtp(Integer.parseInt(jComboBoxOtpremnica.getSelectedItem().toString()), jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                }
                fillTable(result);
            } else if (!datePickerOd.getText().isEmpty() && datePickerDo.getText().isEmpty()) {
                List<Db.Otpremnica> result;
                if (jComboBoxOtpremnica.getSelectedItem().toString().length() == 0) {
                    result = otpremnicaDB.getOtpremnicaWhereDateOdOrderOtp(jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                } else {
                    result = otpremnicaDB.getOtpremnicaWhereDateOdOrderOtp(Integer.parseInt(jComboBoxOtpremnica.getSelectedItem().toString()), jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                }
                fillTable(result);
            } else if (datePickerOd.getText().isEmpty() && !datePickerDo.getText().isEmpty()) {
                List<Db.Otpremnica> result;
                if (jComboBoxOtpremnica.getSelectedItem().toString().length() == 0) {
                    result = otpremnicaDB.getOtpremnicaWhereDateDoOrderOtp(jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                } else {
                    result = otpremnicaDB.getOtpremnicaWhereDateDoOrderOtp(Integer.parseInt(jComboBoxOtpremnica.getSelectedItem().toString()), jComboBoxKupac.getSelectedItem().toString(),
                            jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                            jComboBoxMasinista.getSelectedItem().toString(), Convert.convertStringToDate(datePickerOd.getText(), timePickerOd.getText()), Convert.convertStringToDate(datePickerDo.getText(), timePickerDo.getText()),
                            jComboBoxMarkaBetona.getSelectedItem().toString());
                }
                fillTable(result);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            //System.out.println(e);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanelComboBox1;
    private javax.swing.JPanel jPanelConmboBox2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private DatePicker datePickerOd;
    private TimePicker timePickerOd;
    private DatePicker datePickerDo;
    private TimePicker timePickerDo;

    private final JcomboBox jComboBoxOtpremnica;
    private final JcomboBox jComboBoxKupac;
    private final JcomboBox jComboBoxGradiliste;
    private final JcomboBox jComboVozac;
    private final JcomboBox jComboBoxtablice;
    private final JcomboBox jComboBoxMasinista;
    private final JcomboBox jComboBoxMarkaBetona;

    private OtpremnicaService otpremnicaDB;
    private KomponenteService komponenteDb;
    DefaultTableModel model;
    private AccessModified accessModified;

    private Map<String, Object> podaci;
    private final DetaljniIzvestaj print;
}
