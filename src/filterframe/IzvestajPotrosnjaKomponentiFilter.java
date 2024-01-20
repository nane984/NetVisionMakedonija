/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterframe;

import DbService.KService;
import DbService.KomponenteService;
import DbService.OtpremnicaService;
import Utility.Convert;
import Utility.JcomboBox;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import Frame.PieChar;
import java.awt.Color;
import java.awt.Cursor;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import org.jfree.data.general.DefaultPieDataset;
import print.IzvestajPotrosnjaKomponenti;
import static tools.DoubleFormat.getRounDouble;

/**
 *
 * @author brasa
 */
public class IzvestajPotrosnjaKomponentiFilter extends javax.swing.JFrame {

    /**
     * Creates new form DetaljniIzvestajFilter
     */
    public IzvestajPotrosnjaKomponentiFilter(OtpremnicaService otpremnicaDB, KomponenteService komponenteDb, KService kDb) {
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
        this.kompon = kDb;

        pie = new PieChar();

        addComboBox();
        addDataPicker();
        getTableWhere();
        reloadComboBox();

        //loadTable();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(102, 153, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "R.B.", "K1", "K2", "K3", "K4", "K5", "K6", "K7", "K8", "K9", "K10"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setEnabled(false);
        jTable1.setFocusable(false);
        jTable1.setRequestFocusEnabled(false);
        jTable1.setRowSelectionAllowed(false);
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
        jLabel11.setText("Izvestaji o potrosnji komponenti");
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

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Print_32x32.png"))); // NOI18N
        jButton2.setText("Stampaj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Grafikoni");

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pie chart.png"))); // NOI18N
        jMenuItem1.setText("Pita");
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1102, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanelConmboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jButton2))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getTableWhere();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        pie.setExtendedState(JFrame.MAXIMIZED_BOTH);
        pie.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        getTableWhere();
        IzvestajPotrosnjaKomponenti izvBet = new IzvestajPotrosnjaKomponenti(result, komponenteDb, kompon, jComboBoxKupac.getSelectedItem().toString(),
                jComboBoxGradiliste.getSelectedItem().toString(), jComboVozac.getSelectedItem().toString(), jComboBoxtablice.getSelectedItem().toString(),
                jComboBoxMasinista.getSelectedItem().toString(), datePickerOd.getText(), timePickerOd.getText(), datePickerDo.getText(), timePickerDo.getText(),
                jComboBoxMarkaBetona.getSelectedItem().toString());
        System.out.println("kupac " + jComboBoxKupac.getSelectedItem().toString());
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButton2ActionPerformed

    private DefaultPieDataset getPieDataset(Object[][] obj) {
        pieDataset = new DefaultPieDataset();

        int j = obj.length;

        for (int i = 0; i < obj[j - 1].length - 2; i++) {
            String[] br = obj[j - 1][i + 2].toString().split(" ");
            pieDataset.setValue(zaglavlje[i + 2], Double.parseDouble(br[0]));
        }

        return pieDataset;
    }

    private void fillTable(List<Db.Otpremnica> otpremnica) {

        double ukupnok1 = 0;
        double ukupnok1Ukupno = 0;
        double ukupnok2 = 0;
        double ukupnok2Ukupno = 0;
        double ukupnok3 = 0;
        double ukupnok3Ukupno = 0;
        double ukupnok4 = 0;
        double ukupnok4Ukupno = 0;
        double ukupnok5 = 0;
        double ukupnok5Ukupno = 0;
        double ukupnok6 = 0;
        double ukupnok6Ukupno = 0;
        double ukupnok7 = 0;
        double ukupnok7Ukupno = 0;
        double ukupnok8 = 0;
        double ukupnok8Ukupno = 0;
        double ukupnok9 = 0;
        double ukupnok9Ukupno = 0;
        double ukupnok10 = 0;
        double ukupnok10Ukupno = 0;
        double ukupnok11 = 0;
        double ukupnok11Ukupno = 0;
        double ukupnok12 = 0;
        double ukupnok12Ukupno = 0;

        List<Db.K> k = kompon.getKomponente();

        int size = 0;

        for (Db.K auto : k) {
            if (auto.getIfcheck() == 1) {
                size++;
            }
        }
        int[] formatK = new int[size];
        int a = 0;
        for (Db.K auto : k) {
            if (auto.getIfcheck() == 1) {
                formatK[a] = auto.getDecformat();
                a++;
            }
        }

        Object[][] obj = new Object[otpremnica.size() + 1][size + 2];
        obj[otpremnica.size()][0] = "ukupno";

        
        
        for (int i = 0; i < otpremnica.size(); i++) {

            ukupnok1 = 0;
            ukupnok2 = 0;
            ukupnok3 = 0;
            ukupnok4 = 0;
            ukupnok5 = 0;
            ukupnok6 = 0;
            ukupnok7 = 0;
            ukupnok8 = 0;
            ukupnok9 = 0;
            ukupnok10 = 0;
            ukupnok11 = 0;
            ukupnok12 = 0;

            List<Db.Komponente> komp = komponenteDb.getKomponenteWhere(otpremnica.get(i).getBrotpremnice());

            obj[i][0] = i + 1;              // da bi imali r.b kolonu stavili smo size + 1;
            obj[i][1] = otpremnica.get(i).getBrotpremnice();

            if (k.get(0).getIfcheck() == 1) {

                for (int j = 0; j < komp.size(); j++) {
                    ukupnok1 += komp.get(j).getK1Ostv().doubleValue();
                }
                obj[i][2] = getRounDouble(ukupnok1, formatK[0]);
                ukupnok1Ukupno += Double.parseDouble(obj[i][2].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][2] = getRounDouble(ukupnok1Ukupno, formatK[0]) + " Kg";
                }
            }

            if (k.get(1).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok2 += komp.get(j).getK2Ostv().doubleValue();
                }
                obj[i][3] = getRounDouble(ukupnok2, formatK[1]);
                ukupnok2Ukupno += Double.parseDouble(obj[i][3].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][3] = getRounDouble(ukupnok2Ukupno, formatK[1]) + " Kg";
                }
            }

            if (k.get(2).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok3 += komp.get(j).getK3Ostv().doubleValue();
                }
                obj[i][4] = getRounDouble(ukupnok3, formatK[2]);
                ukupnok3Ukupno += Double.parseDouble(obj[i][4].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][4] = getRounDouble(ukupnok3Ukupno, formatK[2]) + " Kg";
                }
            }

            if (k.get(3).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok4 += komp.get(j).getK4Ostv().doubleValue();
                }
                obj[i][5] = getRounDouble(ukupnok4, formatK[3]);
                ukupnok4Ukupno += Double.parseDouble(obj[i][5].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][5] = getRounDouble(ukupnok4Ukupno, formatK[3]) + " Kg";
                }
            }

            if (k.get(4).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok5 += komp.get(j).getK5Ostv().doubleValue();
                }
                obj[i][6] = getRounDouble(ukupnok5, formatK[4]);;
                ukupnok5Ukupno += Double.parseDouble(obj[i][6].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][6] = getRounDouble(ukupnok5Ukupno, formatK[4]) + " Kg";
                }
            }

            if (k.get(5).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok6 += komp.get(j).getK6Ostv().doubleValue();
                }
                obj[i][7] = getRounDouble(ukupnok6, formatK[5]);
                ukupnok6Ukupno += Double.parseDouble(obj[i][7].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][7] = getRounDouble(ukupnok6Ukupno, formatK[5]) + " Kg";
                }
            }

            if (k.get(6).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok7 += komp.get(j).getK7Ostv().doubleValue();
                }
                obj[i][8] = getRounDouble(ukupnok7, formatK[6]);
                ukupnok7Ukupno += Double.parseDouble(obj[i][8].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][8] = getRounDouble(ukupnok7Ukupno, formatK[6]) + " Kg";
                }
            }

            if (k.get(7).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok8 += komp.get(j).getK8Ostv().doubleValue();
                }
                obj[i][9] = getRounDouble(ukupnok8, formatK[7]);
                ukupnok8Ukupno += Double.parseDouble(obj[i][9].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][9] = getRounDouble(ukupnok8Ukupno, formatK[7]) + " Kg";
                }
            }

            if (k.get(8).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok9 += komp.get(j).getK9Ostv().doubleValue();
                }
                obj[i][10] = getRounDouble(ukupnok9, formatK[8]);
                ukupnok9Ukupno += Double.parseDouble(obj[i][10].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][10] = getRounDouble(ukupnok9Ukupno, formatK[8]) + " Kg";
                }
            }

            if (k.get(9).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok10 += komp.get(j).getK10Ostv().doubleValue();
                }
                obj[i][11] = getRounDouble(ukupnok10, formatK[9]);
                ukupnok10Ukupno += Double.parseDouble(obj[i][11].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][11] = getRounDouble(ukupnok10Ukupno, formatK[9]) + " Kg";
                }
            }
            
            if (k.get(10).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok11 += komp.get(j).getK11Ostv().doubleValue();
                }
                obj[i][12] = getRounDouble(ukupnok11, formatK[10]);
                ukupnok11Ukupno += Double.parseDouble(obj[i][12].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][12] = getRounDouble(ukupnok11Ukupno, formatK[10]) + " Kg";
                }
            }
            
            if (k.get(11).getIfcheck() == 1) {
                for (int j = 0; j < komp.size(); j++) {
                    ukupnok12 += komp.get(j).getK12Ostv().doubleValue();
                }
                obj[i][13] = getRounDouble(ukupnok12, formatK[11]);
                ukupnok12Ukupno += Double.parseDouble(obj[i][13].toString());
                if (i + 1 == otpremnica.size()) {
                    obj[otpremnica.size()][13] = getRounDouble(ukupnok12Ukupno, formatK[11]) + " Kg";
                }
            }
        }

        zaglavlje = new String[size + 2];
        zaglavlje[0] = "r.b";
        zaglavlje[1] = "Otp.br";

        for (int m = 0;
                m < size;
                m++) {
            zaglavlje[m + 2] = k.get(m).getKomponenta();

        }

        pie.setChar(getPieDataset(obj), "Izvestaj o potrosnji komponenti");
        model = new DefaultTableModel(obj, zaglavlje);

        jTable1.setModel(model);

        jTable1.setRowMargin(
                4);
        jTable1.setRowHeight(
                35);
        jTable1.setShowGrid(
                true);
        jTable1.getColumnModel()
                .getColumn(0).setMaxWidth(65);
        jTable1.setDefaultEditor(Object.class,
                 null);

    }

    private void getTableWhere() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (datePickerOd.getText().isEmpty() && datePickerDo.getText().isEmpty()) {

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
            //JOptionPane.showMessageDialog(null, e);

            System.out.println(e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
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

    private final OtpremnicaService otpremnicaDB;
    private List<Db.Otpremnica> result;

    private final KomponenteService komponenteDb;
    private final KService kompon;

    private final PieChar pie;
    private DefaultPieDataset pieDataset;

    private String[] zaglavlje;
    private DefaultTableModel model;

    private void removeTable() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            // model.removeRow(i);
        }
    }

}
