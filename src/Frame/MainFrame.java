/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import DbService.Aditiv1Service;
import DbService.Aditiv2Service;
import DbService.Aditiv3Service;
import buff.AccessSetupPLC;
import buff.Buff;
import DbService.MasinistaService;
import DbService.OtpremnicaService;
import DbService.GradilisteService;
import DbService.InterfAditivService;
import DbService.InterfAgregatService;
import DbService.InterfCementService;
import DbService.InterfDisplejService;
import DbService.InterfMesalicaService;
import DbService.InterfRecepturaService;
import DbService.InterfVodaService;
import DbService.KService;
import DbService.KomponenteService;
import DbService.KonzbetonaService;
import DbService.KupacService;
import DbService.RegtabliceService;
import DbService.SertifikatService;
import DbService.SpecbetonaService;
import DbService.StandbetonaService;
import DbService.SvojstvaService;
import DbService.VozacService;
import DbService.VozackamionService;
import DbService.VrstaCementaService1;
import DbService.VrstaCementaService2;
import DbService.VrstaFilerService;
import DbService.ZrnomaxService;
import Utility.IsporucenoBetona;
import backup.BackupPostgresql;
import filterframe.DetaljniIzvestajFilter;
import filterframe.IzvestajPotrosnjaKomponentiFilter;
import filterframe.IzvestajProizvodnjaBetonaFilter;
import filterframe.ZbirniIzvestajFilter1;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.Timer;
import print.DetaljniIzvestaj;
import tools.IntToDecimal;
import tools.limitUnsignedNumber;

/**
 *
 * @author branko.scekic
 */
public final class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        Rectangle bounds = jScrollPane2.getViewport().getViewRect();

        JScrollBar horizontal = jScrollPane2.getHorizontalScrollBar();
        horizontal.setValue((horizontal.getMaximum() - bounds.width) / 8);

        korpa = new KorpaBeton();

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(18);
        jScrollPane2.getHorizontalScrollBar().setUnitIncrement(10);

        masinistaDb = new MasinistaService();
        otpremnica = new OtpremnicaService();
        vozacDb = new VozacService();
        regtabliceDb = new RegtabliceService();
        gradilisteDb = new GradilisteService();
        kupacDb = new KupacService();
        komp = new KService();
        vodaKamionDb = new VozackamionService();
        komponeteDb = new KomponenteService();
        vrstaCementaDb = new VrstaCementaService1();
        vrstaCementa2Db = new VrstaCementaService2();
        aditiv1Db = new Aditiv1Service();
        aditiv2Db = new Aditiv2Service();
        aditiv3Db = new Aditiv3Service();
        specifBetDb = new SpecbetonaService();
        konzBetonaDb = new KonzbetonaService();
        standbetonaDb = new StandbetonaService();
        vrstaFilerDb = new VrstaFilerService();
        sertifikatDb = new SertifikatService();
        zrnomaxDb = new ZrnomaxService();
        svojstvaDb = new SvojstvaService();
        
        komponenteZaglavlje();

        dodatiPanel();

        modbus = new Buff();

        accessSetup = new AccessSetupPLC(modbus);

        displejDb = new InterfDisplejService();
        cementTextDb = new InterfCementService();
        vodaTextDb = new InterfVodaService();
        aditivTextDb = new InterfAditivService();
        agregatTextDb = new InterfAgregatService();
        recepturaTextDb = new InterfRecepturaService();
        mesalicaTextDb = new InterfMesalicaService();

        sdf = new SimpleDateFormat("HH:mm");

        backupDb = new BackupPostgresql();

        tbdb = new Timer(59000, new ActionListener() {  //1000 1 sec
            @Override
            public void actionPerformed(ActionEvent e) {

                if (DbBackupTimer().equalsIgnoreCase("09:00")) {
                    backup();
                }
            }
        });

        tbdb.start();

        jLabelIm3.setText("");

        loadJComboBox();
        startModbusCommunication();
        restartConnection();
        getDataModbus();
        writeInBasa();
        setStartDate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabelVodaText = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabelVodaSilos1 = new javax.swing.JLabel();
        jLabelNumberVoda = new javax.swing.JLabel();
        jLabelVodaVaga = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabelVodaVagaZatvorena = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabelVodaVagaPuna = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabelTezinaCementSilosBr3 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabelTezinaCementSilosBr4 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabelTezinaCementSilosBr5 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabelCementText = new javax.swing.JLabel();
        jLabelCementSilos1 = new javax.swing.JLabel();
        jLabelCementSilos2 = new javax.swing.JLabel();
        jLabelCementSilos3 = new javax.swing.JLabel();
        jLabelNumberCement = new javax.swing.JLabel();
        jLabelCementVaga = new javax.swing.JLabel();
        jLabelCementVagaZatvorena = new javax.swing.JLabel();
        jLabelCementVagaPuna = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel137 = new javax.swing.JLabel();
        jLabelPolozajKorpe = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabelMesalicaStatus = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabelKompresorStatus = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabelHidropumpaStatus = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelAgregatSilos8_16 = new javax.swing.JLabel();
        jLabelAgregatSilos0_4 = new javax.swing.JLabel();
        jLabelAgregatSilos16_32 = new javax.swing.JLabel();
        jLabelAgregatSilos4_8 = new javax.swing.JLabel();
        jLabelTrakaAgregat = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabelNumberAgregat = new javax.swing.JLabel();
        jLabelAgregatText = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabelAgregatSilos0_2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDisplay = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabelAditivSilos1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabelAditivSilos2 = new javax.swing.JLabel();
        jLabelAditivText = new javax.swing.JLabel();
        jLabelNumberAditiv1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabelAditivSilos3 = new javax.swing.JLabel();
        jLabelAditivVagaZatvorena = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabelAditivVaga = new javax.swing.JLabel();
        jLabelAditivVagaPuna = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabelK1ZadatoHeader = new javax.swing.JLabel();
        jLabelK1IzdatoHeader = new javax.swing.JLabel();
        jLabelK1Header = new javax.swing.JLabel();
        jLabelK2Header = new javax.swing.JLabel();
        jLabelK2ZadatoHeader = new javax.swing.JLabel();
        jLabelK2IzdatoHeader = new javax.swing.JLabel();
        jLabelK3Header = new javax.swing.JLabel();
        jLabelK3ZadatoHeader = new javax.swing.JLabel();
        jLabelK3IzdatoHeader = new javax.swing.JLabel();
        jLabelK4Header = new javax.swing.JLabel();
        jLabelK4ZadatoHeader = new javax.swing.JLabel();
        jLabelK4IzdatoHeader = new javax.swing.JLabel();
        jLabelK5Header = new javax.swing.JLabel();
        jLabelK5ZadatoHeader = new javax.swing.JLabel();
        jLabelK5IzdatoHeader = new javax.swing.JLabel();
        jLabelK6Header = new javax.swing.JLabel();
        jLabelK6ZadatoHeader = new javax.swing.JLabel();
        jLabelK6IzdatoHeader = new javax.swing.JLabel();
        jLabelK7Header = new javax.swing.JLabel();
        jLabelK7ZadatoHeader = new javax.swing.JLabel();
        jLabelK7IzdatoHeader = new javax.swing.JLabel();
        jLabelK8Header = new javax.swing.JLabel();
        jLabelK8ZadatoHeader = new javax.swing.JLabel();
        jLabelK8IzdatoHeader = new javax.swing.JLabel();
        jLabelK9Header = new javax.swing.JLabel();
        jLabelK9ZadatoHeader = new javax.swing.JLabel();
        jLabelK9IzdatoHeader = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabelIm3 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabelZSarze = new javax.swing.JLabel();
        jLabelISarze = new javax.swing.JLabel();
        jLabelK10Header = new javax.swing.JLabel();
        jLabelK10ZadatoHeader = new javax.swing.JLabel();
        jLabelK10IzdatoHeader = new javax.swing.JLabel();
        jLabelK11Header = new javax.swing.JLabel();
        jLabelK11ZadatoHeader = new javax.swing.JLabel();
        jLabelK11IzdatoHeader = new javax.swing.JLabel();
        jLabelK12Header = new javax.swing.JLabel();
        jLabelK12ZadatoHeader = new javax.swing.JLabel();
        jLabelK12IzdatoHeader = new javax.swing.JLabel();
        jLabelZadatom3 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabelBrOtpremnice = new javax.swing.JLabel();
        jLabelBrSarze = new javax.swing.JLabel();
        jLabelRecepturaBetona = new javax.swing.JLabel();
        jLabelKolicinaBetona = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabelAddMasinistu = new javax.swing.JLabel();
        jLabelGradiliste = new javax.swing.JLabel();
        jLabelAddRegTab = new javax.swing.JLabel();
        jLabelAddVozac = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabelAddKupac = new javax.swing.JLabel();
        gradilisteComboBox = new javax.swing.JComboBox<>();
        masinistaComboBox = new javax.swing.JComboBox<>();
        tabliceComboBox = new javax.swing.JComboBox<>();
        vozacComboBox = new javax.swing.JComboBox<>();
        kupacComboBox = new javax.swing.JComboBox<>();
        jLabelAddAditivA2 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        aditivA2ComboBox = new javax.swing.JComboBox<>();
        jLabel75 = new javax.swing.JLabel();
        jLabelAddAditivA1 = new javax.swing.JLabel();
        aditivA1ComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabelAddVrstaCementa1 = new javax.swing.JLabel();
        jComboBoxVrstaCementa1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelAddSpecifBetona = new javax.swing.JLabel();
        jComboBoxSpecificnostBetona = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabelAddKonzinstencijaBetona = new javax.swing.JLabel();
        jComboBoxKonzistencijaBetona = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabelAddStandardBetona = new javax.swing.JLabel();
        jComboBoxStandardBetona = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jLabel76 = new javax.swing.JLabel();
        jLabelAddAditivA3 = new javax.swing.JLabel();
        aditivA3ComboBox = new javax.swing.JComboBox<>();
        jCheckBox13 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabelAddVrstaCementa2 = new javax.swing.JLabel();
        jComboBoxVrstaCementa2 = new javax.swing.JComboBox<>();
        jCheckBox14 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabelAddVrstaFiler = new javax.swing.JLabel();
        jComboBoxVrstaFilera = new javax.swing.JComboBox<>();
        jCheckBox15 = new javax.swing.JCheckBox();
        jComboBoxSertifikat = new javax.swing.JComboBox<>();
        jCheckBox16 = new javax.swing.JCheckBox();
        jLabelAddSertifikat = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxZrnomax = new javax.swing.JComboBox<>();
        jCheckBox17 = new javax.swing.JCheckBox();
        jLabelAddZrnomax = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBoxSvojstva = new javax.swing.JComboBox<>();
        jCheckBox18 = new javax.swing.JCheckBox();
        jLabelAddSvojstva = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButtonDetaljni_Izvestaj2 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuIzvestaji = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuKopijBaze = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuAdministracija = new javax.swing.JMenu();
        jMenuItemBaza = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 255));
        setName("Betonara"); // NOI18N

        jScrollPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBackground(new java.awt.Color(63, 100, 163));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Voda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(null);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(204, 204, 204)));
        jPanel16.setLayout(null);

        jLabelVodaText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelVodaText.setText("Text");
        jPanel16.add(jLabelVodaText);
        jLabelVodaText.setBounds(0, 0, 198, 25);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/VodaPosuda.png"))); // NOI18N
        jPanel16.add(jLabel23);
        jLabel23.setBounds(30, 30, 160, 110);

        jLabelVodaSilos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel16.add(jLabelVodaSilos1);
        jLabelVodaSilos1.setBounds(100, 140, 40, 40);

        jLabelNumberVoda.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelNumberVoda.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNumberVoda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumberVoda.setText("9999.99");
        jLabelNumberVoda.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabelNumberVoda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel16.add(jLabelNumberVoda);
        jLabelNumberVoda.setBounds(30, 290, 170, 40);

        jLabelVodaVaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel16.add(jLabelVodaVaga);
        jLabelVodaVaga.setBounds(100, 350, 40, 40);

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Zatv.");
        jPanel16.add(jLabel44);
        jLabel44.setBounds(10, 350, 58, 17);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/VagaVoda.png"))); // NOI18N
        jPanel16.add(jLabel26);
        jLabel26.setBounds(10, 240, 210, 110);

        jLabelVodaVagaZatvorena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel16.add(jLabelVodaVagaZatvorena);
        jLabelVodaVagaZatvorena.setBounds(10, 370, 40, 40);

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setText("Puna");
        jPanel16.add(jLabel46);
        jLabel46.setBounds(170, 350, 40, 17);

        jLabelVodaVagaPuna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel16.add(jLabelVodaVagaPuna);
        jLabelVodaVagaPuna.setBounds(170, 370, 40, 40);

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeAditiv.png"))); // NOI18N
        jPanel16.add(jLabel22);
        jLabel22.setBounds(110, 140, 20, 90);

        jPanel4.add(jPanel16);
        jPanel16.setBounds(10, 20, 230, 422);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cement", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(null);

        jLabelTezinaCementSilosBr3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTezinaCementSilosBr3.setText("****");
        jPanel9.add(jLabelTezinaCementSilosBr3);
        jLabelTezinaCementSilosBr3.setBounds(20, 30, 69, 14);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Br 1");
        jPanel9.add(jLabel21);
        jLabel21.setBounds(20, 70, 50, 14);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Br 2");
        jPanel9.add(jLabel19);
        jLabel19.setBounds(190, 70, 30, 14);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Br 1");
        jPanel9.add(jLabel14);
        jLabel14.setBounds(100, 70, 50, 14);

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cement.png"))); // NOI18N
        jPanel9.add(jLabel73);
        jLabel73.setBounds(5, 20, 90, 148);

        jLabelTezinaCementSilosBr4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTezinaCementSilosBr4.setText("****");
        jPanel9.add(jLabelTezinaCementSilosBr4);
        jLabelTezinaCementSilosBr4.setBounds(90, 30, 75, 14);

        jLabel129.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cement.png"))); // NOI18N
        jPanel9.add(jLabel129);
        jLabel129.setBounds(85, 20, 80, 148);

        jLabelTezinaCementSilosBr5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTezinaCementSilosBr5.setText("****");
        jPanel9.add(jLabelTezinaCementSilosBr5);
        jLabelTezinaCementSilosBr5.setBounds(170, 30, 70, 14);

        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Cement.png"))); // NOI18N
        jPanel9.add(jLabel130);
        jLabel130.setBounds(160, 20, 80, 148);

        jLabelCementText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCementText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCementText.setText("Text");
        jPanel9.add(jLabelCementText);
        jLabelCementText.setBounds(0, 0, 250, 14);

        jLabelCementSilos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel9.add(jLabelCementSilos1);
        jLabelCementSilos1.setBounds(30, 170, 40, 40);

        jLabelCementSilos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel9.add(jLabelCementSilos2);
        jLabelCementSilos2.setBounds(110, 170, 40, 40);

        jLabelCementSilos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel9.add(jLabelCementSilos3);
        jLabelCementSilos3.setBounds(190, 170, 40, 40);

        jLabelNumberCement.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelNumberCement.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNumberCement.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumberCement.setText("9999.99");
        jLabelNumberCement.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabelNumberCement.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel9.add(jLabelNumberCement);
        jLabelNumberCement.setBounds(30, 280, 190, 38);

        jLabelCementVaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel9.add(jLabelCementVaga);
        jLabelCementVaga.setBounds(110, 340, 40, 40);

        jLabelCementVagaZatvorena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel9.add(jLabelCementVagaZatvorena);
        jLabelCementVagaZatvorena.setBounds(10, 370, 40, 40);

        jLabelCementVagaPuna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel9.add(jLabelCementVagaPuna);
        jLabelCementVagaPuna.setBounds(200, 370, 40, 40);

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("Zatv.");
        jPanel9.add(jLabel50);
        jLabel50.setBounds(10, 350, 59, 17);

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Puna");
        jPanel9.add(jLabel53);
        jLabel53.setBounds(200, 350, 48, 17);

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Vaga");
        jPanel9.add(jLabel52);
        jLabel52.setBounds(110, 350, 48, 17);

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Vaga.png"))); // NOI18N
        jPanel9.add(jLabel37);
        jLabel37.setBounds(20, 230, 210, 110);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        jLabel137.setText("Polozaj korpe");

        jLabelPolozajKorpe.setText("***");

        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel58.setText("Mesalica");

        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setText("Radi");

        jLabelMesalicaStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N

        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel80.setText("Kompresor");

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel79.setText("Radi");

        jLabelKompresorStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel55.setText("Hidropumpa");

        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel56.setText("Radi");

        jLabelHidropumpaStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPolozajKorpe, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(137, 137, 137)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHidropumpaStatus)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabelMesalicaStatus)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelKompresorStatus))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel137)
                            .addComponent(jLabelPolozajKorpe)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel61)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelMesalicaStatus))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel56)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelHidropumpaStatus)
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel80)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel79)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelKompresorStatus)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(132, 155, 196));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agregat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setDoubleBuffered(false);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatRezervoar.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatRezervoar4-8.png"))); // NOI18N

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatRezervoar0-4.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatRezervoar16-32.png"))); // NOI18N

        jLabelAgregatSilos8_16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatOFF.png"))); // NOI18N

        jLabelAgregatSilos0_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatOFF.png"))); // NOI18N

        jLabelAgregatSilos16_32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatOFF.png"))); // NOI18N

        jLabelAgregatSilos4_8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatON.png"))); // NOI18N

        jLabelTrakaAgregat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TrakaONm.gif"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeV.jpg"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeV.jpg"))); // NOI18N

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeV.jpg"))); // NOI18N

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeV.jpg"))); // NOI18N

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pipe2V.jpg"))); // NOI18N

        jLabelNumberAgregat.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelNumberAgregat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumberAgregat.setText("9999.99");
        jLabelNumberAgregat.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabelNumberAgregat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelAgregatText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAgregatText.setText("Text");

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatRezervoar0-2.png"))); // NOI18N

        jLabelAgregatSilos0_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AgregatOFF.png"))); // NOI18N

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeV.jpg"))); // NOI18N

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("A1");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("A2");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("A3");

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("A4");

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("A5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelNumberAgregat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelAgregatText, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabelAgregatSilos0_4)))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabelAgregatSilos4_8))
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabelAgregatSilos8_16)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabelAgregatSilos16_32)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel7)
                                .addGap(37, 37, 37)
                                .addComponent(jLabel8)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jLabel31))
                                    .addComponent(jLabel30)
                                    .addComponent(jLabelAgregatSilos0_2)))
                            .addComponent(jLabelTrakaAgregat, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabelAgregatText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, 0)
                                .addComponent(jLabelAgregatSilos8_16))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, 0)
                                .addComponent(jLabelAgregatSilos4_8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, 0)
                                .addComponent(jLabelAgregatSilos0_4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, 0)
                                .addComponent(jLabelAgregatSilos16_32)))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, 0)
                        .addComponent(jLabelAgregatSilos0_2)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel31)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabelTrakaAgregat, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabelNumberAgregat))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTextAreaDisplay.setEditable(false);
        jTextAreaDisplay.setBackground(javax.swing.UIManager.getDefaults().getColor("menu"));
        jTextAreaDisplay.setColumns(20);
        jTextAreaDisplay.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextAreaDisplay.setLineWrap(true);
        jTextAreaDisplay.setRows(5);
        jTextAreaDisplay.setText("Ovo je display za  poruke");
        jTextAreaDisplay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTextAreaDisplay.setFocusable(false);
        jScrollPane1.setViewportView(jTextAreaDisplay);

        jPanel1.setBackground(new java.awt.Color(0, 0, 207));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aditiv", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(null);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivRezervoar.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(10, 60, 70, 60);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeAditiv.png"))); // NOI18N
        jPanel1.add(jLabel29);
        jLabel29.setBounds(170, 180, 30, 80);

        jLabelAditivSilos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivON.png"))); // NOI18N
        jPanel1.add(jLabelAditivSilos1);
        jLabelAditivSilos1.setBounds(10, 120, 70, 62);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivRezervoar2.png"))); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(80, 60, 70, 60);

        jLabelAditivSilos2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivOFF.png"))); // NOI18N
        jPanel1.add(jLabelAditivSilos2);
        jLabelAditivSilos2.setBounds(80, 120, 70, 62);

        jLabelAditivText.setBackground(new java.awt.Color(255, 255, 204));
        jLabelAditivText.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAditivText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAditivText.setText("Text");
        jPanel1.add(jLabelAditivText);
        jLabelAditivText.setBounds(20, 29, 203, 24);

        jLabelNumberAditiv1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelNumberAditiv1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNumberAditiv1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNumberAditiv1.setText("9999.99");
        jLabelNumberAditiv1.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        jLabelNumberAditiv1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(jLabelNumberAditiv1);
        jLabelNumberAditiv1.setBounds(50, 300, 150, 44);

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeAditiv.png"))); // NOI18N
        jPanel1.add(jLabel16);
        jLabel16.setBounds(100, 180, 30, 70);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PipeAditiv.png"))); // NOI18N
        jPanel1.add(jLabel24);
        jLabel24.setBounds(30, 180, 30, 80);

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/VagaAditiv.png"))); // NOI18N
        jPanel1.add(jLabel36);
        jLabel36.setBounds(10, 250, 220, 109);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivRezervoar3.png"))); // NOI18N
        jPanel1.add(jLabel28);
        jLabel28.setBounds(150, 60, 70, 60);

        jLabelAditivSilos3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/AditivOFF.png"))); // NOI18N
        jPanel1.add(jLabelAditivSilos3);
        jLabelAditivSilos3.setBounds(150, 120, 70, 62);

        jLabelAditivVagaZatvorena.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel1.add(jLabelAditivVagaZatvorena);
        jLabelAditivVagaZatvorena.setBounds(10, 390, 40, 40);

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("Zatv.");
        jPanel1.add(jLabel51);
        jLabel51.setBounds(0, 370, 59, 17);

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Puna");
        jPanel1.add(jLabel54);
        jLabel54.setBounds(180, 370, 48, 17);

        jLabelAditivVaga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Yes.jpg"))); // NOI18N
        jPanel1.add(jLabelAditivVaga);
        jLabelAditivVaga.setBounds(100, 360, 40, 40);

        jLabelAditivVagaPuna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/No.jpg"))); // NOI18N
        jPanel1.add(jLabelAditivVagaPuna);
        jLabelAditivVagaPuna.setBounds(180, 390, 40, 40);

        jLabel122.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/CeviSaKolenom.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel122))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel11.setBackground(new java.awt.Color(51, 255, 204));

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel81.setText("Izdato");

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel82.setText("Recept");

        jLabelK1ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK1ZadatoHeader.setText("9999");

        jLabelK1IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK1IzdatoHeader.setText("9999");

        jLabelK1Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK1Header.setText("K1");

        jLabelK2Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK2Header.setText("K2");

        jLabelK2ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK2ZadatoHeader.setText("9999");

        jLabelK2IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK2IzdatoHeader.setText("9999");

        jLabelK3Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK3Header.setText("K4");

        jLabelK3ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK3ZadatoHeader.setText("9999");

        jLabelK3IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK3IzdatoHeader.setText("9999");

        jLabelK4Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK4Header.setText("K4");

        jLabelK4ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK4ZadatoHeader.setText("9999");

        jLabelK4IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK4IzdatoHeader.setText("9999");

        jLabelK5Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK5Header.setText("K5");

        jLabelK5ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK5ZadatoHeader.setText("9999");

        jLabelK5IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK5IzdatoHeader.setText("9999");

        jLabelK6Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK6Header.setText("K6");

        jLabelK6ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK6ZadatoHeader.setText("9999");

        jLabelK6IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK6IzdatoHeader.setText("9999");

        jLabelK7Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK7Header.setText("K7");

        jLabelK7ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK7ZadatoHeader.setText("9999");

        jLabelK7IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK7IzdatoHeader.setText("9999");

        jLabelK8Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK8Header.setText("K8");

        jLabelK8ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK8ZadatoHeader.setText("9999");

        jLabelK8IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK8IzdatoHeader.setText("9999");

        jLabelK9Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK9Header.setText("K9");

        jLabelK9ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK9ZadatoHeader.setText("9999");

        jLabelK9IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK9IzdatoHeader.setText("9999");

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel119.setText("Zadato ");

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel120.setText("Izdato");

        jLabel121.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel121.setText("m3");

        jLabelIm3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelIm3.setText("9999");

        jLabel124.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel124.setText("Šarže");

        jLabelZSarze.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelZSarze.setText("9999");

        jLabelISarze.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelISarze.setText("9999");

        jLabelK10Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK10Header.setText("K10");

        jLabelK10ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK10ZadatoHeader.setText("9999");

        jLabelK10IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK10IzdatoHeader.setText("9999");

        jLabelK11Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK11Header.setText("K11");

        jLabelK11ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK11ZadatoHeader.setText("9999");

        jLabelK11IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK11IzdatoHeader.setText("9999");

        jLabelK12Header.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK12Header.setText("K12");

        jLabelK12ZadatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK12ZadatoHeader.setText("9999");

        jLabelK12IzdatoHeader.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelK12IzdatoHeader.setText("9999");

        jLabelZadatom3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelZadatom3.setText("9999");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK1ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK1IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK1Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK2ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK2IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK2Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK3ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK3IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK3Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK4Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK4ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK4IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK5Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK5ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK5IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK6Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK6ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK6IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK7Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK7ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK7IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK8Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK8ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK8IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK9ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK9IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK9Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK10ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK10IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK10Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK11ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK11IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK11Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelK12ZadatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK12IzdatoHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelK12Header, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel121, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIm3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabelZadatom3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(39, 39, 39)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelZSarze, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelISarze, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(137, 137, 137))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelK12Header)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelK12ZadatoHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelK12IzdatoHeader))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelK11Header)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelK11ZadatoHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelK11IzdatoHeader))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabelK10Header)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelK10ZadatoHeader)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelK10IzdatoHeader))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabelZSarze)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelISarze))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel119)
                                    .addComponent(jLabelZadatom3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelIm3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel120))))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK9Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK9ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK9IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK8Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK8ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK8IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK7Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK7ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK7IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK6Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK6ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK6IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK5Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK5ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK5IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK4Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK4ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK4IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK3Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK3ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK3IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK2Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelK2ZadatoHeader)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelK2IzdatoHeader))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabelK1Header)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabelK1ZadatoHeader)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelK1IzdatoHeader))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel82)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel81))))))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel121)
                        .addComponent(jLabel124)))
                .addGap(47, 47, 47))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(153, 153, 153));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Izvestaj", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Podaci sa plk"));
        jPanel14.setEnabled(false);

        jLabel64.setText("Бр. отпреме");

        jLabel65.setText("Марка на бетон");

        jLabel66.setText("Количина");

        jLabel71.setText("Бр. шарже");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBrSarze, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRecepturaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelKolicinaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel71)
                    .addComponent(jLabelBrSarze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel65)
                    .addComponent(jLabelRecepturaBetona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addComponent(jLabelKolicinaBetona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(156, 156, 156))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Podaci za otpremnicu"));

        jLabel67.setText("Оператер");

        jLabel68.setText("Градилиште");

        jLabel69.setText("Возач");

        jLabel70.setText("Р.Б. Возило");
        jLabel70.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelAddMasinistu.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddMasinistu.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddMasinistu.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddMasinistu.setText("+");
        jLabelAddMasinistu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddMasinistu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddMasinistu.setOpaque(true);
        jLabelAddMasinistu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddMasinistuMousePressed(evt);
            }
        });

        jLabelGradiliste.setBackground(new java.awt.Color(204, 204, 204));
        jLabelGradiliste.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelGradiliste.setForeground(new java.awt.Color(255, 51, 51));
        jLabelGradiliste.setText("+");
        jLabelGradiliste.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelGradiliste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelGradiliste.setOpaque(true);
        jLabelGradiliste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelGradilisteMousePressed(evt);
            }
        });

        jLabelAddRegTab.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddRegTab.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddRegTab.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddRegTab.setText("+");
        jLabelAddRegTab.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddRegTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddRegTab.setOpaque(true);
        jLabelAddRegTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddRegTabMousePressed(evt);
            }
        });

        jLabelAddVozac.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddVozac.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddVozac.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddVozac.setText("+");
        jLabelAddVozac.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddVozac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddVozac.setOpaque(true);
        jLabelAddVozac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddVozacMousePressed(evt);
            }
        });

        jLabel72.setText("Купувач");

        jLabelAddKupac.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddKupac.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddKupac.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddKupac.setText("+");
        jLabelAddKupac.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddKupac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddKupac.setOpaque(true);
        jLabelAddKupac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddKupacMousePressed(evt);
            }
        });

        gradilisteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gradilisteComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                gradilisteComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        masinistaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        masinistaComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                masinistaComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        tabliceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tabliceComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                tabliceComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        vozacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        vozacComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                vozacComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        kupacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        kupacComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                kupacComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabelAddAditivA2.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddAditivA2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddAditivA2.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddAditivA2.setText("+");
        jLabelAddAditivA2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddAditivA2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddAditivA2.setOpaque(true);
        jLabelAddAditivA2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddAditivA2MousePressed(evt);
            }
        });

        jLabel74.setText("Адитив 2");

        aditivA2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        aditivA2ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                aditivA2ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel75.setText("Адитив 1");

        jLabelAddAditivA1.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddAditivA1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddAditivA1.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddAditivA1.setText("+");
        jLabelAddAditivA1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddAditivA1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddAditivA1.setOpaque(true);
        jLabelAddAditivA1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddAditivA1MousePressed(evt);
            }
        });

        aditivA1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        aditivA1ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                aditivA1ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel1.setText("Враста цем. 1");

        jLabelAddVrstaCementa1.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddVrstaCementa1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddVrstaCementa1.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddVrstaCementa1.setText("+");
        jLabelAddVrstaCementa1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddVrstaCementa1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddVrstaCementa1.setOpaque(true);
        jLabelAddVrstaCementa1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddVrstaCementa1MousePressed(evt);
            }
        });

        jComboBoxVrstaCementa1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVrstaCementa1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxVrstaCementa1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jComboBoxVrstaCementa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVrstaCementa1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Класа на излож.");

        jLabelAddSpecifBetona.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddSpecifBetona.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddSpecifBetona.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddSpecifBetona.setText("+");
        jLabelAddSpecifBetona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddSpecifBetona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddSpecifBetona.setOpaque(true);
        jLabelAddSpecifBetona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddSpecifBetonaMousePressed(evt);
            }
        });

        jComboBoxSpecificnostBetona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSpecificnostBetona.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxSpecificnostBetonaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel3.setText("Конз бетона");

        jLabelAddKonzinstencijaBetona.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddKonzinstencijaBetona.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddKonzinstencijaBetona.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddKonzinstencijaBetona.setText("+");
        jLabelAddKonzinstencijaBetona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddKonzinstencijaBetona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddKonzinstencijaBetona.setOpaque(true);
        jLabelAddKonzinstencijaBetona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddKonzinstencijaBetonaMousePressed(evt);
            }
        });

        jComboBoxKonzistencijaBetona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxKonzistencijaBetona.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxKonzistencijaBetonaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setText("Класа на хлорид");

        jLabelAddStandardBetona.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddStandardBetona.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddStandardBetona.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddStandardBetona.setText("+");
        jLabelAddStandardBetona.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddStandardBetona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddStandardBetona.setOpaque(true);
        jLabelAddStandardBetona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddStandardBetonaMousePressed(evt);
            }
        });

        jComboBoxStandardBetona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxStandardBetona.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxStandardBetonaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel76.setText("Адитив 3");

        jLabelAddAditivA3.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddAditivA3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddAditivA3.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddAditivA3.setText("+");
        jLabelAddAditivA3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddAditivA3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddAditivA3.setOpaque(true);
        jLabelAddAditivA3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddAditivA3MousePressed(evt);
            }
        });

        aditivA3ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        aditivA3ComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                aditivA3ComboBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel5.setText("Враста цем. 2");

        jLabelAddVrstaCementa2.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddVrstaCementa2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddVrstaCementa2.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddVrstaCementa2.setText("+");
        jLabelAddVrstaCementa2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddVrstaCementa2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddVrstaCementa2.setOpaque(true);
        jLabelAddVrstaCementa2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddVrstaCementa2MousePressed(evt);
            }
        });

        jComboBoxVrstaCementa2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVrstaCementa2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxVrstaCementa2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel15.setText("Филер");

        jLabelAddVrstaFiler.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddVrstaFiler.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddVrstaFiler.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddVrstaFiler.setText("+");
        jLabelAddVrstaFiler.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddVrstaFiler.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddVrstaFiler.setOpaque(true);
        jLabelAddVrstaFiler.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddVrstaFilerMousePressed(evt);
            }
        });

        jComboBoxVrstaFilera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVrstaFilera.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxVrstaFileraPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jComboBoxSertifikat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSertifikat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxSertifikatPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabelAddSertifikat.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddSertifikat.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddSertifikat.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddSertifikat.setText("+");
        jLabelAddSertifikat.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddSertifikat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddSertifikat.setOpaque(true);
        jLabelAddSertifikat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddSertifikatMousePressed(evt);
            }
        });

        jLabel20.setText("Сертификат");

        jComboBoxZrnomax.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxZrnomax.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxZrnomaxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabelAddZrnomax.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddZrnomax.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddZrnomax.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddZrnomax.setText("+");
        jLabelAddZrnomax.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddZrnomax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddZrnomax.setOpaque(true);
        jLabelAddZrnomax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddZrnomaxMousePressed(evt);
            }
        });

        jLabel25.setText("Зрно макс");

        jComboBoxSvojstva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxSvojstva.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBoxSvojstvaPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabelAddSvojstva.setBackground(new java.awt.Color(204, 204, 204));
        jLabelAddSvojstva.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelAddSvojstva.setForeground(new java.awt.Color(255, 51, 51));
        jLabelAddSvojstva.setText("+");
        jLabelAddSvojstva.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabelAddSvojstva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelAddSvojstva.setOpaque(true);
        jLabelAddSvojstva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabelAddSvojstvaMousePressed(evt);
            }
        });

        jLabel27.setText("Својства");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAddSvojstva, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxSvojstva, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox18))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAddZrnomax, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxZrnomax, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox17))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAddSertifikat, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxSertifikat, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox16))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabelAddAditivA2)
                                        .addGap(18, 18, 18)
                                        .addComponent(aditivA2ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabelAddSpecifBetona)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxSpecificnostBetona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabelAddKonzinstencijaBetona)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBoxKonzistencijaBetona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabelAddMasinistu)
                                            .addComponent(jLabelAddStandardBetona)
                                            .addComponent(jLabelGradiliste)
                                            .addComponent(jLabelAddRegTab)
                                            .addComponent(jLabelAddVozac)
                                            .addComponent(jLabelAddKupac))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(masinistaComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBoxStandardBetona, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(gradilisteComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(tabliceComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(vozacComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(kupacComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelAddAditivA1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelAddVrstaCementa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxVrstaCementa1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(aditivA1ComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jCheckBox2)
                                        .addComponent(jCheckBox3, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jCheckBox4)
                                    .addComponent(jCheckBox5)
                                    .addComponent(jCheckBox6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addComponent(jCheckBox7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox9)
                            .addComponent(jCheckBox10)
                            .addComponent(jCheckBox11)
                            .addComponent(jCheckBox12, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelAddAditivA3)
                        .addGap(18, 18, 18)
                        .addComponent(aditivA3ComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox13))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAddVrstaCementa2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxVrstaCementa2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAddVrstaFiler, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxVrstaFilera, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox15)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabelAddVrstaCementa1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxVrstaCementa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox14)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabelAddVrstaCementa2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxVrstaCementa2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox15)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jLabelAddVrstaFiler, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxVrstaFilera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox17)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxZrnomax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(jLabelAddZrnomax, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox18)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27)
                        .addComponent(jLabelAddSvojstva, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxSvojstva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox16)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxSertifikat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jLabelAddSertifikat, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(aditivA1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel75)
                        .addComponent(jLabelAddAditivA1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel74)
                        .addComponent(jLabelAddAditivA2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(aditivA2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel76)
                        .addComponent(jLabelAddAditivA3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(aditivA3ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabelAddSpecifBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxSpecificnostBetona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabelAddKonzinstencijaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxKonzistencijaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabelAddStandardBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxStandardBetona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAddMasinistu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(masinistaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel68)
                        .addComponent(gradilisteComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox9))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelAddRegTab, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tabliceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox10)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel70)))
                .addGap(12, 12, 12)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAddVozac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel69)
                        .addComponent(vozacComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox11))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox12)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelAddKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(kupacComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel72)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Štampa"));

        jButtonDetaljni_Izvestaj2.setBackground(new java.awt.Color(102, 255, 102));
        jButtonDetaljni_Izvestaj2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Print_32x32.png"))); // NOI18N
        jButtonDetaljni_Izvestaj2.setText("Tekuci izvestaj");
        jButtonDetaljni_Izvestaj2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonDetaljni_Izvestaj2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetaljni_Izvestaj2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonDetaljni_Izvestaj2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonDetaljni_Izvestaj2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jCheckBox1.setText("Ostavi podatke i za sledece otpremnice");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jScrollPane2.setViewportView(jPanel3);

        jMenuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jMenuIzvestaji.setText("Izvestaji");

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Report.png"))); // NOI18N
        jMenuItem7.setText("Izvestaji");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenuIzvestaji.add(jMenuItem7);
        jMenuIzvestaji.add(jSeparator2);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pie chart.png"))); // NOI18N
        jMenuItem18.setText("Proizvodnja betona");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenuIzvestaji.add(jMenuItem18);

        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Pie chart.png"))); // NOI18N
        jMenuItem19.setText("Potrosnja komponenti");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenuIzvestaji.add(jMenuItem19);
        jMenuIzvestaji.add(jSeparator3);

        jMenuBar1.add(jMenuIzvestaji);

        jMenuKopijBaze.setText("BackUp");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Download.png"))); // NOI18N
        jMenuItem3.setText("kreiraj kopiju baze");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuKopijBaze.add(jMenuItem3);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Upload.png"))); // NOI18N
        jMenuItem4.setText("preuzmi kopiju baze");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuKopijBaze.add(jMenuItem4);

        jMenuBar1.add(jMenuKopijBaze);

        jMenuAdministracija.setText("Baza");

        jMenuItemBaza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Database.png"))); // NOI18N
        jMenuItemBaza.setText("Baza");
        jMenuItemBaza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBazaActionPerformed(evt);
            }
        });
        jMenuAdministracija.add(jMenuItemBaza);

        jMenuBar1.add(jMenuAdministracija);

        jMenu4.setText("Komunikacija");

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Wrench.png"))); // NOI18N
        jMenuItem5.setText("PLK");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/List.png"))); // NOI18N
        jMenuItem14.setText("Interface");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1624, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dodatiPanel() {

        jPanel12.setBackground(new java.awt.Color(214, 217, 223));
        korpa.setBackground(new java.awt.Color(214, 217, 223));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(korpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(1, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
                jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(korpa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(1, Short.MAX_VALUE))
        );

        String[] podaci = new String[1];
        podaci[0] = "_";

        jComboBoxVrstaCementa1.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxVrstaCementa2.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxVrstaFilera.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxZrnomax.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxSvojstva.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxSertifikat.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        aditivA1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        aditivA2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        aditivA3ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxSpecificnostBetona.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxKonzistencijaBetona.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        masinistaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        jComboBoxStandardBetona.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        gradilisteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        tabliceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        vozacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));
        kupacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(podaci));

    }

    private void loadJComboBox() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        tabliceComboBox.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                loadJComboBoxVozacFor();
            }
        });

        List<Db.Masinista> masinisti_;
        String[] masinisti = new String[1];
        List<Db.Gradiliste> gradiliste;
        String[] gradiliste_ = new String[1];
        List<Db.Regtablice> regTab;
        String[] tablica = new String[1];
        List<Db.Vozaci> vozac;
        String[] vozaci = new String[1];
        List<Db.Kupac> kupci;
        String[] kupci_ = new String[1];
        List<Db.Vrstacementa> vrstaCementa;
        String[] vrstaCem = new String[1];
        List<Db.Vrstacementa2> vrstaCementa2;
        String[] vrstaCem2 = new String[1];
        List<Db.Filer> vrstaFiler;
        String[] vrstaFil = new String[1];
        List<Db.Sertifikat> vrstaSertifikat;
        String[] vrstaSer = new String[1];
        List<Db.Zrnomax> vrstaZrnomax;
        String[] svj = new String[1];
        List<Db.Svojstva> svojstva;
        String[] vrstaZrm = new String[1];
        List<Db.Aditiv1> aditiv1_;
        String[] aditiv1 = new String[1];
        List<Db.Aditiv2> aditiv2_;
        String[] aditiv2 = new String[1];
        List<Db.Aditiv3> aditiv3_;
        String[] aditiv3 = new String[1];
        List<Db.Specbetona> specBet_;
        String[] specBet = new String[1];
        List<Db.Konzbetona> konzbetona_;
        String[] konzbetona = new String[1];
        List<Db.Standbetona> standbeton_;
        String[] standbeton = new String[1];

        try {
            masinisti_ = masinistaDb.getMasinistaWithX();
            masinisti = new String[masinisti_.size()];
            regTab = regtabliceDb.getRegTabliceWithX();
            tablica = new String[regTab.size()];
            gradiliste = gradilisteDb.getGradilistaWithX();
            gradiliste_ = new String[gradiliste.size()];
            vozac = vozacDb.getVozaciWithX();
            vozaci = new String[vozac.size()];
            kupci = kupacDb.getKupciWithX();
            kupci_ = new String[kupci.size()];
            vrstaCementa = vrstaCementaDb.getVrstaCementa();
            vrstaCem = new String[vrstaCementa.size()];
            vrstaCementa2 = vrstaCementa2Db.getVrstaCementa();
            vrstaCem2 = new String[vrstaCementa2.size()];
            vrstaSertifikat = sertifikatDb.getSertifikat();
            vrstaSer = new String[vrstaSertifikat.size()];
            vrstaFiler = vrstaFilerDb.getVrstaFiler();
            vrstaFil = new String[vrstaFiler.size()];
            vrstaZrnomax = zrnomaxDb.getZrnomax();
            vrstaZrm = new String[vrstaZrnomax.size()];
            svojstva = svojstvaDb.getSvojstva();
            svj = new String[svojstva.size()];
            aditiv1_ = aditiv1Db.getVrstaCementa();
            aditiv1 = new String[aditiv1_.size()];
            aditiv2_ = aditiv2Db.getVrstaCementa();
            aditiv2 = new String[aditiv2_.size()];
            aditiv3_ = aditiv3Db.getVrstaCementa();
            aditiv3 = new String[aditiv3_.size()];
            specBet_ = specifBetDb.getSpecBetona();
            specBet = new String[specBet_.size()];
            konzbetona_ = konzBetonaDb.getKonzistencijaBetona();
            konzbetona = new String[konzbetona_.size()];
            standbeton_ = standbetonaDb.getStandBetona();
            standbeton = new String[standbeton_.size()];

            for (int i = 0; i < masinisti_.size(); i++) {
                masinisti[i] = masinisti_.get(i).getImePrezime();
            }
            for (int i = 0; i < regTab.size(); i++) {
                tablica[i] = regTab.get(i).getTablice();
            }
            for (int i = 0; i < vozac.size(); i++) {
                vozaci[i] = vozac.get(i).getImeprezimevozaci();

            }
            for (int i = 0; i < gradiliste.size(); i++) {
                gradiliste_[i] = gradiliste.get(i).getGradiliste();
            }
            for (int i = 0; i < kupci.size(); i++) {
                kupci_[i] = kupci.get(i).getKupac();
            }
            for (int i = 0; i < vrstaCementa.size(); i++) {
                vrstaCem[i] = vrstaCementa.get(i).getVrstacem();
            }
            for (int i = 0; i < vrstaCementa2.size(); i++) {
                vrstaCem2[i] = vrstaCementa2.get(i).getVrstacem();
            }
            for (int i = 0; i < vrstaFiler.size(); i++) {
                vrstaFil[i] = vrstaFiler.get(i).getFiler();
            }
            for (int i = 0; i < aditiv1_.size(); i++) {
                aditiv1[i] = aditiv1_.get(i).getText();
            }
            for (int i = 0; i < aditiv2_.size(); i++) {
                aditiv2[i] = aditiv2_.get(i).getText();
            }
            for (int i = 0; i < aditiv3_.size(); i++) {
                aditiv3[i] = aditiv3_.get(i).getText();
            }
            for (int i = 0; i < specBet_.size(); i++) {
                specBet[i] = specBet_.get(i).getText();
            }
            for (int i = 0; i < konzbetona_.size(); i++) {
                konzbetona[i] = konzbetona_.get(i).getText();
            }
            for (int i = 0; i < standbeton_.size(); i++) {
                standbeton[i] = standbeton_.get(i).getText();
            }
            for (int i = 0; i < vrstaSertifikat.size(); i++) {
                vrstaSer[i] = vrstaSertifikat.get(i).getSertifikat();
            }
             for (int i = 0; i < vrstaZrnomax.size(); i++) {
                vrstaZrm[i] = vrstaZrnomax.get(i).getZrnomax();
            }
            for (int i = 0; i < svojstva.size(); i++) {
                svj[i] = svojstva.get(i).getSvojstva();
            }

        } catch (Exception e) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske u komunikaciji sa bazom");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        jComboBoxVrstaCementa1.setModel(new javax.swing.DefaultComboBoxModel<>(vrstaCem));
        jComboBoxVrstaCementa2.setModel(new javax.swing.DefaultComboBoxModel<>(vrstaCem2));
        jComboBoxVrstaFilera.setModel(new javax.swing.DefaultComboBoxModel<>(vrstaFil));
        jComboBoxSertifikat.setModel(new javax.swing.DefaultComboBoxModel<>(vrstaSer));
        jComboBoxZrnomax.setModel(new javax.swing.DefaultComboBoxModel<>(vrstaZrm));
        jComboBoxSvojstva.setModel(new javax.swing.DefaultComboBoxModel<>(svj));
        aditivA1ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(aditiv1));
        aditivA2ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(aditiv2));
        aditivA3ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(aditiv3));
        jComboBoxSpecificnostBetona.setModel(new javax.swing.DefaultComboBoxModel<>(specBet));
        jComboBoxKonzistencijaBetona.setModel(new javax.swing.DefaultComboBoxModel<>(konzbetona));
        jComboBoxStandardBetona.setModel(new javax.swing.DefaultComboBoxModel<>(standbeton));
        masinistaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(masinisti));
        gradilisteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(gradiliste_));
        tabliceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(tablica));
        vozacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(vozaci));
        kupacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(kupci_));

        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }

    private void loadJComboBoxVozac() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        List<Db.Vozaci> vozac;
        String[] vozaci = new String[1];

        try {
            vozac = vozacDb.getVozaciWithX();
            vozaci = new String[vozac.size()];
            for (int i = 0; i < vozac.size(); i++) {
                vozaci[i] = vozac.get(i).getImeprezimevozaci();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske do komunikacije sa bazom");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        vozacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(vozaci));
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void loadJComboBoxVozacFor() {
        /*
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        if (tabliceComboBox.getSelectedItem().toString().length() < 3) {
            loadJComboBoxVozac();
        } else {

            List<Db.Vozackamion> vozac;
            String[] vozaci = new String[1];

            try {
                vozac = vodaKamionDb.getVozacKamion(tabliceComboBox.getSelectedItem().toString());
                vozaci = new String[vozac.size()];
                for (int i = 0; i < vozac.size(); i++) {
                    vozaci[i] = vozac.get(i).getVozac().getImeprezimevozaci();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Doslo je do greske do komunikacije sa bazom");
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            List<String> voz = new ArrayList<>(Arrays.asList(vozaci));
            vozacComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(vozaci));
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
         */
    }

    private void loadJComboBoxRegTab() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        List<Db.Regtablice> regTab;
        String[] tablica = new String[1];

        try {
            regTab = regtabliceDb.getRegTabliceWithX();
            tablica = new String[regTab.size()];

            for (int i = 0; i < regTab.size(); i++) {
                tablica[i] = regTab.get(i).getTablice();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske do komunikacije sa bazom");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        tabliceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(tablica));
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }


    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            accessSetup.jPasswordField1.setText("");
            accessSetup.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");

        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jButtonDetaljni_Izvestaj2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetaljni_Izvestaj2ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            DetaljniIzvestaj detIzv = new DetaljniIzvestaj();
            detIzv.getIzvestaj(otpremnica.getOtpremnica(Integer.parseInt(jLabelBrOtpremnice.getText())));
            //Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
            //detIzv.setMaximumSize(DimMax);
            //detIzv.setExtendedState(JFrame.MAXIMIZED_BOTH);
            detIzv.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske u komunikaciji sa bazom ili je broj otpremnice nepoznat");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jButtonDetaljni_Izvestaj2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        backup();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        restoreDb = new RestoreBackDb(backupDb);
        restoreDb.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        detaljniIzvestajFilter = new DetaljniIzvestajFilter(otpremnica, komponeteDb);
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        detaljniIzvestajFilter.setMaximumSize(DimMax);
        detaljniIzvestajFilter.setExtendedState(JFrame.MAXIMIZED_BOTH);
        detaljniIzvestajFilter.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jLabelAddMasinistuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddMasinistuMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addMasinista = new AddMasinista(masinistaDb);
            addMasinista.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddMasinistuMousePressed

    private void jLabelGradilisteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelGradilisteMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addGradiliste = new AddGradiliste(gradilisteDb);
            addGradiliste.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");

        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelGradilisteMousePressed

    private void jLabelAddVozacMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddVozacMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addVozac = new AddVozac1(vozacDb);
            addVozac.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddVozacMousePressed

    private void jLabelAddRegTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddRegTabMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {

            addRegTab = new AddRegTab(regtabliceDb);
            addRegTab.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do greske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddRegTabMousePressed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            accessSettupInterface = new AccessSetupInterface();
            accessSettupInterface.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        proizvodnjaBetona = new IzvestajProizvodnjaBetonaFilter(otpremnica, komponeteDb);
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        proizvodnjaBetona.setMaximumSize(DimMax);
        proizvodnjaBetona.setExtendedState(JFrame.MAXIMIZED_BOTH);
        proizvodnjaBetona.setVisible(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jLabelAddKupacMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddKupacMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addKupac = new AddKupc(kupacDb);
            addKupac.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddKupacMousePressed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        try {
            IzvestajPotrosnjaKomponentiFilter izvestajBeton = new IzvestajPotrosnjaKomponentiFilter(otpremnica, komponeteDb, komp);

            Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
            izvestajBeton.setMaximumSize(DimMax);
            izvestajBeton.setExtendedState(JFrame.MAXIMIZED_BOTH);
            izvestajBeton.setVisible(true);
        } catch (Exception e) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Doslo je do greske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItemBazaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBazaActionPerformed
        bazaAdmin = new Baza(gradilisteDb, kupacDb, masinistaDb, vozacDb, regtabliceDb,
                vodaKamionDb, aditiv1Db, aditiv2Db, aditiv3Db, konzBetonaDb, specifBetDb,
                standbetonaDb, vrstaCementaDb, vrstaCementa2Db, vrstaFilerDb, zrnomaxDb,
                svojstvaDb, sertifikatDb);
        Dimension DimMax = Toolkit.getDefaultToolkit().getScreenSize();
        bazaAdmin.setMaximumSize(DimMax);
        bazaAdmin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        bazaAdmin.show(true);

    }//GEN-LAST:event_jMenuItemBazaActionPerformed

    private void jLabelAddAditivA2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAditivA2MousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addAditiv2 = new AddAditiv2(aditiv2Db);
            addAditiv2.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddAditivA2MousePressed

    private void jLabelAddAditivA1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAditivA1MousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addAditiv1 == null) {
                addAditiv1 = new AddAditiv1(aditiv1Db);
                addAditiv1.setVisible(true);
            } else {
                addAditiv1.dispose();
                addAditiv1 = new AddAditiv1(aditiv1Db);
                addAditiv1.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddAditivA1MousePressed

    private void jLabelAddVrstaCementa1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddVrstaCementa1MousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addVrstaCementa == null) {
                addVrstaCementa = new AddVrstaCementa(vrstaCementaDb);
                addVrstaCementa.setVisible(true);
            } else {
                addVrstaCementa.dispose();
                addVrstaCementa = new AddVrstaCementa(vrstaCementaDb);
                addVrstaCementa.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddVrstaCementa1MousePressed

    private void jLabelAddSpecifBetonaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddSpecifBetonaMousePressed

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addSpecBetona = new AddSpecBetona(specifBetDb);
            addSpecBetona.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddSpecifBetonaMousePressed

    private void jLabelAddKonzinstencijaBetonaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddKonzinstencijaBetonaMousePressed

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addKonzBetona = new AddKonzBetona(konzBetonaDb);
            addKonzBetona.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddKonzinstencijaBetonaMousePressed

    private void jLabelAddStandardBetonaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddStandardBetonaMousePressed

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addStandBetona = new AddStandBetona(standbetonaDb);
            addStandBetona.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddStandardBetonaMousePressed

    private void jComboBoxVrstaCementa1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxVrstaCementa1PopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_jComboBoxVrstaCementa1PopupMenuWillBecomeInvisible

    private void aditivA1ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_aditivA1ComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_aditivA1ComboBoxPopupMenuWillBecomeInvisible

    private void aditivA2ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_aditivA2ComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_aditivA2ComboBoxPopupMenuWillBecomeInvisible

    private void jComboBoxSpecificnostBetonaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxSpecificnostBetonaPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_jComboBoxSpecificnostBetonaPopupMenuWillBecomeInvisible

    private void jComboBoxKonzistencijaBetonaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxKonzistencijaBetonaPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_jComboBoxKonzistencijaBetonaPopupMenuWillBecomeInvisible

    private void jComboBoxStandardBetonaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxStandardBetonaPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_jComboBoxStandardBetonaPopupMenuWillBecomeInvisible

    private void masinistaComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_masinistaComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_masinistaComboBoxPopupMenuWillBecomeInvisible

    private void gradilisteComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_gradilisteComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_gradilisteComboBoxPopupMenuWillBecomeInvisible

    private void tabliceComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_tabliceComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_tabliceComboBoxPopupMenuWillBecomeInvisible

    private void vozacComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_vozacComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_vozacComboBoxPopupMenuWillBecomeInvisible

    private void kupacComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_kupacComboBoxPopupMenuWillBecomeInvisible
        //updateOtpremnica();
    }//GEN-LAST:event_kupacComboBoxPopupMenuWillBecomeInvisible

    private void jLabelAddAditivA3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddAditivA3MousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            addAditiv3 = new AddAditiv3(aditiv3Db);
            addAditiv3.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddAditivA3MousePressed

    private void aditivA3ComboBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_aditivA3ComboBoxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_aditivA3ComboBoxPopupMenuWillBecomeInvisible

    private void jLabelAddVrstaCementa2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddVrstaCementa2MousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addVrstaCementa2 == null) {
                addVrstaCementa2 = new AddVrstaCementa2(vrstaCementa2Db);
                addVrstaCementa2.setVisible(true);
            } else {
                addVrstaCementa2.dispose();
                addVrstaCementa2 = new AddVrstaCementa2(vrstaCementa2Db);
                addVrstaCementa2.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddVrstaCementa2MousePressed

    private void jComboBoxVrstaCementa2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxVrstaCementa2PopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVrstaCementa2PopupMenuWillBecomeInvisible

    private void jLabelAddVrstaFilerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddVrstaFilerMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addVrstaFiler == null) {
                addVrstaFiler = new AddVrstaFiler(vrstaFilerDb);
                addVrstaFiler.setVisible(true);
            } else {
                addVrstaFiler.dispose();
                addVrstaFiler = new AddVrstaFiler(vrstaFilerDb);
                addVrstaFiler.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddVrstaFilerMousePressed

    private void jComboBoxVrstaFileraPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxVrstaFileraPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVrstaFileraPopupMenuWillBecomeInvisible

    private void jComboBoxVrstaCementa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVrstaCementa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVrstaCementa1ActionPerformed

    private void jComboBoxSertifikatPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxSertifikatPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSertifikatPopupMenuWillBecomeInvisible

    private void jLabelAddSertifikatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddSertifikatMousePressed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addSertifikat == null) {
                addSertifikat = new AddSertifikat(sertifikatDb);
                addSertifikat.setVisible(true);
            } else {
                addSertifikat.dispose();
                addSertifikat = new AddSertifikat(sertifikatDb);
                addSertifikat.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddSertifikatMousePressed

    private void jComboBoxZrnomaxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxZrnomaxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxZrnomaxPopupMenuWillBecomeInvisible

    private void jLabelAddZrnomaxMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddZrnomaxMousePressed
          setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addZrnomax == null) {
                addZrnomax = new AddZrnomax(zrnomaxDb);
                addZrnomax.setVisible(true);
            } else {
                addZrnomax.dispose();
                addZrnomax = new AddZrnomax(zrnomaxDb);
                addZrnomax.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddZrnomaxMousePressed

    private void jComboBoxSvojstvaPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBoxSvojstvaPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSvojstvaPopupMenuWillBecomeInvisible

    private void jLabelAddSvojstvaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAddSvojstvaMousePressed
         setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (addSvojstva == null) {
                addSvojstva = new AddSvojstva(svojstvaDb);
                addSvojstva.setVisible(true);
            } else {
                addSvojstva.dispose();
                addSvojstva = new AddSvojstva(svojstvaDb);
                addSvojstva.setVisible(true);
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Doslo je do kreske u komunikaciji sa bazom");
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jLabelAddSvojstvaMousePressed

    private void komponenteZaglavlje() {
        jLabelK1Header.setVisible(false);
        jLabelK2Header.setVisible(false);
        jLabelK3Header.setVisible(false);
        jLabelK4Header.setVisible(false);
        jLabelK5Header.setVisible(false);
        jLabelK6Header.setVisible(false);
        jLabelK7Header.setVisible(false);
        jLabelK8Header.setVisible(false);
        jLabelK9Header.setVisible(false);
        jLabelK10Header.setVisible(false);
        jLabelK11Header.setVisible(false);
        jLabelK12Header.setVisible(false);

        jLabelK1ZadatoHeader.setVisible(false);
        jLabelK2ZadatoHeader.setVisible(false);
        jLabelK3ZadatoHeader.setVisible(false);
        jLabelK4ZadatoHeader.setVisible(false);
        jLabelK5ZadatoHeader.setVisible(false);
        jLabelK6ZadatoHeader.setVisible(false);
        jLabelK7ZadatoHeader.setVisible(false);
        jLabelK8ZadatoHeader.setVisible(false);
        jLabelK9ZadatoHeader.setVisible(false);
        jLabelK10ZadatoHeader.setVisible(false);
        jLabelK11ZadatoHeader.setVisible(false);
        jLabelK12ZadatoHeader.setVisible(false);

        jLabelK1IzdatoHeader.setVisible(false);
        jLabelK2IzdatoHeader.setVisible(false);
        jLabelK3IzdatoHeader.setVisible(false);
        jLabelK4IzdatoHeader.setVisible(false);
        jLabelK5IzdatoHeader.setVisible(false);
        jLabelK6IzdatoHeader.setVisible(false);
        jLabelK7IzdatoHeader.setVisible(false);
        jLabelK8IzdatoHeader.setVisible(false);
        jLabelK9IzdatoHeader.setVisible(false);
        jLabelK10IzdatoHeader.setVisible(false);
        jLabelK11IzdatoHeader.setVisible(false);
        jLabelK12IzdatoHeader.setVisible(false);

        try {
            k = komp.getKomponente();

            for (int i = 0; i < k.size(); i++) {
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 1) {
                    jLabelK1Header.setVisible(true);
                    jLabelK1Header.setText(k.get(i).getKomponenta());
                    jLabelK1ZadatoHeader.setVisible(true);
                    jLabelK1IzdatoHeader.setVisible(true);
                    formatK1 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 2) {
                    jLabelK2Header.setVisible(true);
                    jLabelK2Header.setText(k.get(i).getKomponenta());
                    jLabelK2ZadatoHeader.setVisible(true);
                    jLabelK2IzdatoHeader.setVisible(true);
                    formatK2 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 3) {
                    jLabelK3Header.setVisible(true);
                    jLabelK3Header.setText(k.get(i).getKomponenta());
                    jLabelK3ZadatoHeader.setVisible(true);
                    jLabelK3IzdatoHeader.setVisible(true);
                    formatK3 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 4) {
                    jLabelK4Header.setVisible(true);
                    jLabelK4Header.setText(k.get(i).getKomponenta());
                    jLabelK4ZadatoHeader.setVisible(true);
                    jLabelK4IzdatoHeader.setVisible(true);
                    formatK4 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 5) {
                    jLabelK5Header.setVisible(true);
                    jLabelK5Header.setText(k.get(i).getKomponenta());
                    jLabelK5ZadatoHeader.setVisible(true);
                    jLabelK5IzdatoHeader.setVisible(true);
                    formatK5 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 6) {
                    jLabelK6Header.setVisible(true);
                    jLabelK6Header.setText(k.get(i).getKomponenta());
                    jLabelK6ZadatoHeader.setVisible(true);
                    jLabelK6IzdatoHeader.setVisible(true);
                    formatK6 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 7) {
                    jLabelK7Header.setVisible(true);
                    jLabelK7Header.setText(k.get(i).getKomponenta());
                    jLabelK7ZadatoHeader.setVisible(true);
                    jLabelK7IzdatoHeader.setVisible(true);
                    formatK7 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 8) {
                    jLabelK8Header.setVisible(true);
                    jLabelK8Header.setText(k.get(i).getKomponenta());
                    jLabelK8ZadatoHeader.setVisible(true);
                    jLabelK8IzdatoHeader.setVisible(true);
                    formatK8 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 9) {
                    jLabelK9Header.setVisible(true);
                    jLabelK9Header.setText(k.get(i).getKomponenta());
                    jLabelK9ZadatoHeader.setVisible(true);
                    jLabelK9IzdatoHeader.setVisible(true);
                    formatK9 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 10) {
                    jLabelK10Header.setVisible(true);
                    jLabelK10Header.setText(k.get(i).getKomponenta());
                    jLabelK10ZadatoHeader.setVisible(true);
                    jLabelK10IzdatoHeader.setVisible(true);
                    formatK10 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 11) {
                    jLabelK11Header.setVisible(true);
                    jLabelK11Header.setText(k.get(i).getKomponenta());
                    jLabelK11ZadatoHeader.setVisible(true);
                    jLabelK11IzdatoHeader.setVisible(true);
                    formatK11 = k.get(i).getDecformat();
                }
                if (k.get(i).getIfcheck() == 1 && k.get(i).getBrK() == 12) {
                    jLabelK12Header.setVisible(true);
                    jLabelK12Header.setText(k.get(i).getKomponenta());
                    jLabelK12ZadatoHeader.setVisible(true);
                    jLabelK12IzdatoHeader.setVisible(true);
                    formatK12 = k.get(i).getDecformat();
                }
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Pokrenite bazu pa zatim aplikaciju, aplikacija se gasi", "Greska", JOptionPane.ERROR_MESSAGE);
            //System.exit(1); 
        }

    }

    private String DbBackupTimer() {
        cal = Calendar.getInstance();
        return (sdf.format(cal.getTime()));
    }

    private void startModbusCommunication() {
        t1 = new Thread(modbus);
        t1.start();
    }

    public void getDataModbus() {
        timer = new Timer(100, new ActionListener() {  //1000 1 sec
            @Override
            public void actionPerformed(ActionEvent e) {
                getRegisters();
                getDigitalInput();
                getDisplayTextData();
            }
        });

        timer.start();
    }
    
    public void setStartDate() {
        timerStartDate = new Timer(1000, new ActionListener() {  //1000 1 sec
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    getdateFosStartingProces();
                   
                }catch(Exception ex){
                    System.out.println(ex);
                }
                
            }
        });

        timerStartDate.start();
    }
    
    private void getdateFosStartingProces(){
        if(jLabelBrSarze.getText().matches("1") && modbus.jLabelVodaPraznjenjeVaga){
            if(pocetnoVreme<2){
                datumvreme = new Date();
                pocetnoVreme ++;
            }
            //System.out.println(datumvreme);
        }
        
    }

    private void restartConnection() {
        timerRestart = new Timer(10000, new ActionListener() {  //1000 1 sec
            @Override
            public void actionPerformed(ActionEvent e) {
                if (restartConn) {
                    t1.stop();
                    modbus = null;
                    modbus = new Buff();
                    modbus.cleanOldValue();
                    t1 = new Thread(modbus);
                    t1.start();
                    restartConn = false;
                }
            }
        });

        timerRestart.start();
    }

    public void writeInBasa() {
        count = 0;
        brOtpremnice = jLabelBrOtpremnice.getText();
        brSarze = "0";

        timerDb = new Timer(1000, new ActionListener() {  //1000 1 sec
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (modbus.bazaOcitaj[0] >= 1) {

                        if (waitForWriteInBaza > 2) {

                            try {

                                if (!brOtpremnice.matches(jLabelBrOtpremnice.getText()) || pokusajPonovoUpisOtpremnice) {

                                    IsporucenoBetona.setUkupnoIsporuceno(0);
                                    setKolicinaPoRecepturi();
                                    //setKolicinaBetona();

                                    brOtpremnice = jLabelBrOtpremnice.getText();
                                    brSarze = "0";
                                    otpremnica.addOtpremnica(Integer.parseInt(brOtpremnice), datumvreme,
                                            Float.parseFloat(jLabelZadatom3.getText()), Float.parseFloat("0"),
                                            Float.parseFloat(jLabelK1ZadatoHeader.getText()), Float.parseFloat(jLabelK2ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK3ZadatoHeader.getText()), Float.parseFloat(jLabelK4ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK5ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK6ZadatoHeader.getText()), Float.parseFloat(jLabelK7ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK8ZadatoHeader.getText()), Float.parseFloat(jLabelK9ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK10ZadatoHeader.getText()), Float.parseFloat(jLabelK11ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK12ZadatoHeader.getText()),
                                            gradilisteComboBox.getSelectedItem().toString(), masinistaComboBox.getSelectedItem().toString(),
                                            kupacComboBox.getSelectedItem().toString(), vozacComboBox.getSelectedItem().toString(),
                                            tabliceComboBox.getSelectedItem().toString(), jLabelRecepturaBetona.getText(),
                                            jComboBoxVrstaCementa1.getSelectedItem().toString(), jComboBoxVrstaCementa2.getSelectedItem().toString(), 
                                            jComboBoxVrstaFilera.getSelectedItem().toString(), aditivA1ComboBox.getSelectedItem().toString(),
                                            aditivA2ComboBox.getSelectedItem().toString(), aditivA3ComboBox.getSelectedItem().toString(),
                                            jComboBoxSpecificnostBetona.getSelectedItem().toString(), jComboBoxKonzistencijaBetona.getSelectedItem().toString(),
                                            jComboBoxStandardBetona.getSelectedItem().toString(), jComboBoxSertifikat.getSelectedItem().toString(),
                                            jComboBoxZrnomax.getSelectedItem().toString(), jComboBoxSvojstva.getSelectedItem().toString());

                                    pokusajPonovoUpisOtpremnice = false;

                                }
                            } catch (Exception exc) {
                                System.out.println(exc);
                                //System.out.println("Ovo je od otpremnice" + exc);
                                //System.out.println("____________________________");
                                brSarze = "0";
                                waitForWriteInBaza=0;
                            }
                            try {
                                //if(brSarze.matches(jLabelBrSarze.getText())){
                                //    brSarze="0";
                                //}

                                if (!brSarze.matches(jLabelBrSarze.getText())) {

                                    setKolicinaBetona();
                                    //if(brSarze.matches("0")){
                                    //    brSarze = "10"+jLabelBrSarze.getText();
                                    //}else{
                                    brSarze = jLabelBrSarze.getText();
                                    //}

                                    komponeteDb.addKomponente(Integer.parseInt(jLabelBrOtpremnice.getText()),
                                            Integer.parseInt(jLabelBrSarze.getText()),
                                            Float.parseFloat(jLabelK1ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK1IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK2ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK2IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK3ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK3IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK4ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK4IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK5ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK5IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK6ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK6IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK7ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK7IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK8ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK8IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK9ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK9IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK10ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK10IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK11ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK11IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelK12ZadatoHeader.getText()),
                                            Float.parseFloat(jLabelK12IzdatoHeader.getText()),
                                            Float.parseFloat(jLabelZadatom3.getText()),
                                            Float.parseFloat(jLabelIm3.getText()),
                                            Float.parseFloat(jLabelIm3.getText()));                               
                                   
                                }
                                modbus.writeRegister = true;
                                count = 0;
                                waitForWriteInBaza = 0;
                                pocetnoVreme = 0;

                            } catch (Exception exc) {
                                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, exc);
                                if (count == 0) {
                                    pokusajPonovoUpisOtpremnice = true;
                                    brSarze = "0";

                                    //JOptionPane.showMessageDialog(rootPane, "Podatak se nije upisao u bazu. Doslo je do greske ");
                                    count++;
                                    waitForWriteInBaza=0;
                                }

                            }
                        }
                        waitForWriteInBaza ++;
                    }
                    

                } catch (Exception exc) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, exc);
                    
                }
            }
        });

        timerDb.start();
    }

    public void setKolicinaPoRecepturi() {
        IsporucenoBetona.setK1Zadato(Float.parseFloat(jLabelK1ZadatoHeader.getText()));
        IsporucenoBetona.setK2Zadato(Float.parseFloat(jLabelK2ZadatoHeader.getText()));
        IsporucenoBetona.setK3Zadato(Float.parseFloat(jLabelK3ZadatoHeader.getText()));
        IsporucenoBetona.setK4Zadato(Float.parseFloat(jLabelK4ZadatoHeader.getText()));
        IsporucenoBetona.setK5Zadato(Float.parseFloat(jLabelK5ZadatoHeader.getText()));
        IsporucenoBetona.setK6Zadato(Float.parseFloat(jLabelK6ZadatoHeader.getText()));
        IsporucenoBetona.setK7Zadato(Float.parseFloat(jLabelK7ZadatoHeader.getText()));
        IsporucenoBetona.setK8Zadato(Float.parseFloat(jLabelK8ZadatoHeader.getText()));
        IsporucenoBetona.setK9Zadato(Float.parseFloat(jLabelK9ZadatoHeader.getText()));
        IsporucenoBetona.setK10Zadato(Float.parseFloat(jLabelK10ZadatoHeader.getText()));
        IsporucenoBetona.setK11Zadato(Float.parseFloat(jLabelK11ZadatoHeader.getText()));
        IsporucenoBetona.setK12Zadato(Float.parseFloat(jLabelK12ZadatoHeader.getText()));
    }

    public void setKolicinaBetona() {
        float isp = 0;
        try {
            isp = IsporucenoBetona.isporucenoBetona(Float.parseFloat(jLabelK1IzdatoHeader.getText()), Float.parseFloat(jLabelK2IzdatoHeader.getText()),
                    Float.parseFloat(jLabelK3IzdatoHeader.getText()), Float.parseFloat(jLabelK4IzdatoHeader.getText()), Float.parseFloat(jLabelK5IzdatoHeader.getText()),
                    Float.parseFloat(jLabelK6IzdatoHeader.getText()), Float.parseFloat(jLabelK7IzdatoHeader.getText()), Float.parseFloat(jLabelK8IzdatoHeader.getText()),
                    Float.parseFloat(jLabelK9IzdatoHeader.getText()), Float.parseFloat(jLabelK10IzdatoHeader.getText()), Float.parseFloat(jLabelK11IzdatoHeader.getText()),
                    Float.parseFloat(jLabelK12IzdatoHeader.getText()));
        } catch (Exception e) {
            isp = 0;
        }

        IsporucenoBetona.setUkupnoIsporuceno(IsporucenoBetona.getUkupnoIsporuceno() + isp);
        jLabelIm3.setText(Float.toString(IsporucenoBetona.getUkupnoIsporuceno()));

    }

    public void backup() {
        //backupDb.deleteOld("betonara_db");
        backupDb.makeBackup("betonara_db", "beton0602984");
    }

    public void getDisplayTextData() {
        if (osveziBazu) {
            loadJComboBox();
            osveziBazu = false;
        }

        if (osveziBazuVozac) {
            loadJComboBoxVozac();
            osveziBazuVozac = false;
        }
        if (osveziBazuRegTab) {
            loadJComboBoxRegTab();
            osveziBazuRegTab = false;
        }

        //if (regTab.compareTo(tabliceComboBox.getSelectedItem().toString())!=0) {
        //    regTab = tabliceComboBox.getSelectedItem().toString();
        //    loadJComboBoxVozacFor();
        //}
        try {
            int textDisp = Integer.parseInt(textDisplay);
            if (textDisp != textDisplayBefore) {
                jTextAreaDisplay.setText(displejDb.getInterfaceWhere(textDisp).getText());
                jTextAreaDisplay.setBackground(Color.white);
                textDisplayBefore = textDisp;
            }
        } catch (Exception en) {
            textDisplayBefore = 0;

        }

        if (!modbus.isConnected()) {
            brIzgubljenihKonekcija++;
            if (brIzgubljenihKonekcija > 3) {
                modbus.cleanOldValue();
            }
        } else {
            brIzgubljenihKonekcija = 0;
        }

        try {
            int cementText = Integer.parseInt(cementDisplay);
            if (cementText != cementDisplayBefore) {
                cementDisplayBefore = cementText;
                jLabelCementText.setText(cementTextDb.getInterfaceWhere(cementText).getText());
            }
        } catch (Exception en) {
            jLabelCementText.setText("*****");
            cementDisplayBefore = 0;
        }

        try {
            int vodaText = Integer.parseInt(vodaDisplay);
            if (vodaText != vodaDisplayBefore) {
                vodaDisplayBefore = vodaText;
                jLabelVodaText.setText(vodaTextDb.getInterfaceWhere(vodaText).getText());
            }
        } catch (Exception en) {
            jLabelVodaText.setText("*****");
            vodaDisplayBefore = 0;
        }

        try {
            int aditivText = Integer.parseInt(aditivDisplay);
            if (aditivText != aditivDisplayBefore) {
                aditivDisplayBefore = aditivText;
                jLabelAditivText.setText(aditivTextDb.getInterfaceWhere(aditivText).getText());
            }
        } catch (Exception en) {
            jLabelAditivText.setText("*****");
            aditivDisplayBefore = 0;
        }

        try {
            int agregatText = Integer.parseInt(agregatDisplay);
            if (agregatText != agregatDisplayBefore) {
                agregatDisplayBefore = agregatText;
                jLabelAgregatText.setText(agregatTextDb.getInterfaceWhere(agregatText).getText());
            }
        } catch (Exception en) {
            jLabelAgregatText.setText("*****");
            agregatDisplayBefore = 0;
        }

        try {
            int recepturaText = Integer.parseInt(recepturaDisplay);
            if (recepturaText != recepturaDisplayBefore) {
                recepturaDisplayBefore = recepturaText;
                jLabelRecepturaBetona.setText(recepturaTextDb.getInterfaceWhere(recepturaText).getText());
            }
        } catch (Exception en) {
            jLabelRecepturaBetona.setText("*****");
            recepturaDisplayBefore = 0;
        }

        try {
            int mesalicaText = Integer.parseInt(mesalicaDisplay);
            if (mesalicaText != mesalicaDisplayBefore) {
                mesalicaDisplayBefore = mesalicaText;
            }
        } catch (Exception en) {
            mesalicaDisplayBefore = 0;
        }
    }

    private void setLabelImage(boolean b, javax.swing.JLabel l, String pathImgOn, String pathImgOff) {
        if (b) {
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathImgOn)));
        } else {
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource(pathImgOff)));
        }

    }

    private void setLabelBackground(boolean b, javax.swing.JLabel l, String text) {
        if (b) {
            l.setBackground(new java.awt.Color(51, 255, 51));
            l.setText(text);
        } else {
            l.setBackground(new java.awt.Color(238, 238, 238));
            l.setText(" ");
        }

    }

    private void setPozicijaKorpe(String pos) {
        try {
            int i = Integer.parseInt(pos);
            if (i >= 0 && i <= 100) {
                korpa.moveKorpa(i);
                jLabelPolozajKorpe.setText(pos);
            }
        } catch (Exception e) {

        }
    }

    private void getRegisters() {
        jLabelK1ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK1ZadatoHeader, formatK1));
        jLabelK1IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK1IzdatoHeader, formatK1));
        jLabelK2ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK2ZadatoHeader, formatK2));
        jLabelK2IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK2IzdatoHeader, formatK2));
        jLabelK3ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK3ZadatoHeader, formatK3));
        jLabelK3IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK3IzdatoHeader, formatK3));
        jLabelK4ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK4ZadatoHeader, formatK4));
        jLabelK4IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK4IzdatoHeader, formatK4));
        jLabelK5ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK5ZadatoHeader, formatK5));
        jLabelK5IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK5IzdatoHeader, formatK5));
        jLabelK6ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK6ZadatoHeader, formatK6));
        jLabelK6IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK6IzdatoHeader, formatK6));
        jLabelK7ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK7ZadatoHeader, formatK7));
        jLabelK7IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK7IzdatoHeader, formatK7));
        jLabelK8ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK8ZadatoHeader, formatK8));
        jLabelK8IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK8IzdatoHeader, formatK8));
        jLabelK9ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK9ZadatoHeader, formatK9));
        jLabelK9IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK9IzdatoHeader, formatK9));
        jLabelK10ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK10ZadatoHeader, formatK10));
        jLabelK10IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK10IzdatoHeader, formatK10));
        jLabelK11ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK11ZadatoHeader, formatK11));
        jLabelK11IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK11IzdatoHeader, formatK11));
        jLabelK12ZadatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK12ZadatoHeader, formatK12));
        jLabelK12IzdatoHeader.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelK12IzdatoHeader, formatK12));
        jLabelZadatom3.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelZm3, 2));
        //jLabelIm3.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", modbus.jLabelIm3, 2));
        jLabelZSarze.setText(modbus.jLabelZSarze);
        jLabelISarze.setText(modbus.jLabelISarze);
        textDisplay = modbus.jTextFieldDisplej;
        cementDisplay = modbus.jTextFieldCementText;
        jLabelNumberCement.setText(Integer.toString(shiftPointDouble(Integer.parseInt(modbus.jLabelNumberCement), 0)));
        vodaDisplay = modbus.jLabelVodaText;
        jLabelNumberVoda.setText(Integer.toString(shiftPointDouble(Integer.parseInt(modbus.jTextFieldNumberVoda), 0)));
        aditivDisplay = modbus.jTextFieldAditivText;
        jLabelNumberAditiv1.setText(IntToDecimal.getTwoRegisterIntShiftPoint("0", limitUnsignedNumber.putOnZero(modbus.jTextFieldNumberAditiv1), 2));
        agregatDisplay = modbus.jTextFieldAgregatText;
        jLabelNumberAgregat.setText(Integer.toString(shiftPointDouble(Integer.parseInt(modbus.jTextFieldAgregatNumber), 0)));
        jLabelBrOtpremnice.setText(modbus.jTextFieldBrOtpreme);
        jLabelBrSarze.setText(modbus.jTextFieldBrSarze);
        recepturaDisplay = modbus.jTextFieldRecBeton;
        jLabelKolicinaBetona.setText(jLabelIm3.getText());
        setPozicijaKorpe(modbus.pozicijaKorpe);
        mesalicaDisplay = modbus.jTextFieldMesalicaText;

    }

    private String intToLong(String visi, String nizi) {

        int visi1 = Integer.parseInt(visi);
        int nizi1 = Integer.parseInt(nizi);

        int a = 0;
        try {
            a = visi1 << 16 | nizi1 & 0xFFFF;
            //return ShiftPoint.shiftonePoint(a, 1) + " kg";
            return Integer.toString(a) + " kg";
        } catch (Exception e) {

        }
        return "*";
    }

    private void getDigitalInput() {

        setLabelImage(modbus.jLabelCementSilos1, jLabelCementSilos1, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelCementSilos2, jLabelCementSilos2, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelCementSilos3, jLabelCementSilos3, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelCementPraznjenjeVage, jLabelCementVaga, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelCementVagaZatvorena, jLabelCementVagaZatvorena, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelCementVagaPuna, jLabelCementVagaPuna, "/image/Yes.jpg", "/image/No.jpg");

        setLabelImage(modbus.jLabelVodaSilos, jLabelVodaSilos1, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelVodaPraznjenjeVaga, jLabelVodaVaga, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelVodaVagaZatvorena, jLabelVodaVagaZatvorena, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelVodaVagaPuna, jLabelVodaVagaPuna, "/image/Yes.jpg", "/image/No.jpg");

        setLabelImage(modbus.jLabelAditivSilos1, jLabelAditivSilos1, "/image/AditivON.png", "/image/AditivOFF.png");
        setLabelImage(modbus.jLabelAditivSilos2, jLabelAditivSilos2, "/image/AditivON.png", "/image/AditivOFF.png");
        setLabelImage(modbus.jLabelAditivSilos3, jLabelAditivSilos3, "/image/AditivON.png", "/image/AditivOFF.png");
        setLabelImage(modbus.jLabelAditivPraznjenjeVage, jLabelAditivVaga, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelAditivVagaZatvorena, jLabelAditivVagaZatvorena, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelAditivVagaPuna, jLabelAditivVagaPuna, "/image/Yes.jpg", "/image/No.jpg");

        setLabelImage(modbus.jLabelHidropumpaStatus, jLabelHidropumpaStatus, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelMesalicaStatus, jLabelMesalicaStatus, "/image/Yes.jpg", "/image/No.jpg");
        setLabelImage(modbus.jLabelKompresorStatus, jLabelKompresorStatus, "/image/Yes.jpg", "/image/No.jpg");

        setLabelImage(modbus.jLabelAgregatSilos8_16, jLabelAgregatSilos8_16, "/image/AgregatON.png", "/image/AgregatOFF.png");
        setLabelImage(modbus.jLabelAgregatSilos4_8, jLabelAgregatSilos4_8, "/image/AgregatON.png", "/image/AgregatOFF.png");
        setLabelImage(modbus.jLabelAgregatSilos0_4, jLabelAgregatSilos0_4, "/image/AgregatON.png", "/image/AgregatOFF.png");
        setLabelImage(modbus.jLabelAgregatSilos16_32, jLabelAgregatSilos16_32, "/image/AgregatON.png", "/image/AgregatOFF.png");
        setLabelImage(modbus.jLabelAgregatSilos0_2, jLabelAgregatSilos0_2, "/image/AgregatON.png", "/image/AgregatOFF.png");
        setLabelImage(modbus.jLabelTrakaAgregat, jLabelTrakaAgregat, "/image/TrakaONm.gif", "/image/TrakaOFF.png");

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> aditivA1ComboBox;
    private javax.swing.JComboBox<String> aditivA2ComboBox;
    private javax.swing.JComboBox<String> aditivA3ComboBox;
    private javax.swing.JComboBox<String> gradilisteComboBox;
    private javax.swing.JButton jButtonDetaljni_Izvestaj2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox13;
    private javax.swing.JCheckBox jCheckBox14;
    private javax.swing.JCheckBox jCheckBox15;
    private javax.swing.JCheckBox jCheckBox16;
    private javax.swing.JCheckBox jCheckBox17;
    private javax.swing.JCheckBox jCheckBox18;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JComboBox<String> jComboBoxKonzistencijaBetona;
    private javax.swing.JComboBox<String> jComboBoxSertifikat;
    private javax.swing.JComboBox<String> jComboBoxSpecificnostBetona;
    private javax.swing.JComboBox<String> jComboBoxStandardBetona;
    private javax.swing.JComboBox<String> jComboBoxSvojstva;
    private javax.swing.JComboBox<String> jComboBoxVrstaCementa1;
    private javax.swing.JComboBox<String> jComboBoxVrstaCementa2;
    private javax.swing.JComboBox<String> jComboBoxVrstaFilera;
    private javax.swing.JComboBox<String> jComboBoxZrnomax;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAddAditivA1;
    private javax.swing.JLabel jLabelAddAditivA2;
    private javax.swing.JLabel jLabelAddAditivA3;
    private javax.swing.JLabel jLabelAddKonzinstencijaBetona;
    private javax.swing.JLabel jLabelAddKupac;
    private javax.swing.JLabel jLabelAddMasinistu;
    private javax.swing.JLabel jLabelAddRegTab;
    private javax.swing.JLabel jLabelAddSertifikat;
    private javax.swing.JLabel jLabelAddSpecifBetona;
    private javax.swing.JLabel jLabelAddStandardBetona;
    private javax.swing.JLabel jLabelAddSvojstva;
    private javax.swing.JLabel jLabelAddVozac;
    private javax.swing.JLabel jLabelAddVrstaCementa1;
    private javax.swing.JLabel jLabelAddVrstaCementa2;
    private javax.swing.JLabel jLabelAddVrstaFiler;
    private javax.swing.JLabel jLabelAddZrnomax;
    private javax.swing.JLabel jLabelAditivSilos1;
    private javax.swing.JLabel jLabelAditivSilos2;
    private javax.swing.JLabel jLabelAditivSilos3;
    private javax.swing.JLabel jLabelAditivText;
    private javax.swing.JLabel jLabelAditivVaga;
    private javax.swing.JLabel jLabelAditivVagaPuna;
    private javax.swing.JLabel jLabelAditivVagaZatvorena;
    private javax.swing.JLabel jLabelAgregatSilos0_2;
    private javax.swing.JLabel jLabelAgregatSilos0_4;
    private javax.swing.JLabel jLabelAgregatSilos16_32;
    private javax.swing.JLabel jLabelAgregatSilos4_8;
    private javax.swing.JLabel jLabelAgregatSilos8_16;
    private javax.swing.JLabel jLabelAgregatText;
    private javax.swing.JLabel jLabelBrOtpremnice;
    private javax.swing.JLabel jLabelBrSarze;
    private javax.swing.JLabel jLabelCementSilos1;
    private javax.swing.JLabel jLabelCementSilos2;
    private javax.swing.JLabel jLabelCementSilos3;
    private javax.swing.JLabel jLabelCementText;
    private javax.swing.JLabel jLabelCementVaga;
    private javax.swing.JLabel jLabelCementVagaPuna;
    private javax.swing.JLabel jLabelCementVagaZatvorena;
    private javax.swing.JLabel jLabelGradiliste;
    private javax.swing.JLabel jLabelHidropumpaStatus;
    private javax.swing.JLabel jLabelISarze;
    private javax.swing.JLabel jLabelIm3;
    private javax.swing.JLabel jLabelK10Header;
    private javax.swing.JLabel jLabelK10IzdatoHeader;
    private javax.swing.JLabel jLabelK10ZadatoHeader;
    private javax.swing.JLabel jLabelK11Header;
    private javax.swing.JLabel jLabelK11IzdatoHeader;
    private javax.swing.JLabel jLabelK11ZadatoHeader;
    private javax.swing.JLabel jLabelK12Header;
    private javax.swing.JLabel jLabelK12IzdatoHeader;
    private javax.swing.JLabel jLabelK12ZadatoHeader;
    private javax.swing.JLabel jLabelK1Header;
    private javax.swing.JLabel jLabelK1IzdatoHeader;
    private javax.swing.JLabel jLabelK1ZadatoHeader;
    private javax.swing.JLabel jLabelK2Header;
    private javax.swing.JLabel jLabelK2IzdatoHeader;
    private javax.swing.JLabel jLabelK2ZadatoHeader;
    private javax.swing.JLabel jLabelK3Header;
    private javax.swing.JLabel jLabelK3IzdatoHeader;
    private javax.swing.JLabel jLabelK3ZadatoHeader;
    private javax.swing.JLabel jLabelK4Header;
    private javax.swing.JLabel jLabelK4IzdatoHeader;
    private javax.swing.JLabel jLabelK4ZadatoHeader;
    private javax.swing.JLabel jLabelK5Header;
    private javax.swing.JLabel jLabelK5IzdatoHeader;
    private javax.swing.JLabel jLabelK5ZadatoHeader;
    private javax.swing.JLabel jLabelK6Header;
    private javax.swing.JLabel jLabelK6IzdatoHeader;
    private javax.swing.JLabel jLabelK6ZadatoHeader;
    private javax.swing.JLabel jLabelK7Header;
    private javax.swing.JLabel jLabelK7IzdatoHeader;
    private javax.swing.JLabel jLabelK7ZadatoHeader;
    private javax.swing.JLabel jLabelK8Header;
    private javax.swing.JLabel jLabelK8IzdatoHeader;
    private javax.swing.JLabel jLabelK8ZadatoHeader;
    private javax.swing.JLabel jLabelK9Header;
    private javax.swing.JLabel jLabelK9IzdatoHeader;
    private javax.swing.JLabel jLabelK9ZadatoHeader;
    private javax.swing.JLabel jLabelKolicinaBetona;
    private javax.swing.JLabel jLabelKompresorStatus;
    private javax.swing.JLabel jLabelMesalicaStatus;
    private javax.swing.JLabel jLabelNumberAditiv1;
    private javax.swing.JLabel jLabelNumberAgregat;
    private javax.swing.JLabel jLabelNumberCement;
    private javax.swing.JLabel jLabelNumberVoda;
    private javax.swing.JLabel jLabelPolozajKorpe;
    private javax.swing.JLabel jLabelRecepturaBetona;
    private javax.swing.JLabel jLabelTezinaCementSilosBr3;
    private javax.swing.JLabel jLabelTezinaCementSilosBr4;
    private javax.swing.JLabel jLabelTezinaCementSilosBr5;
    private javax.swing.JLabel jLabelTrakaAgregat;
    private javax.swing.JLabel jLabelVodaSilos1;
    private javax.swing.JLabel jLabelVodaText;
    private javax.swing.JLabel jLabelVodaVaga;
    private javax.swing.JLabel jLabelVodaVagaPuna;
    private javax.swing.JLabel jLabelVodaVagaZatvorena;
    private javax.swing.JLabel jLabelZSarze;
    private javax.swing.JLabel jLabelZadatom3;
    private javax.swing.JMenu jMenu4;
    public static javax.swing.JMenu jMenuAdministracija;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItemBaza;
    public static javax.swing.JMenu jMenuIzvestaji;
    public static javax.swing.JMenu jMenuKopijBaze;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTextArea jTextAreaDisplay;
    private javax.swing.JComboBox<String> kupacComboBox;
    private javax.swing.JComboBox<String> masinistaComboBox;
    private javax.swing.JComboBox<String> tabliceComboBox;
    private javax.swing.JComboBox<String> vozacComboBox;
    // End of variables declaration//GEN-END:variables

    //private String k1m3;
    //private String k2m3;
    //private String k3m3;
    //private String k4m3;
    //private String k5m3;
    //private String k6m3;
    //private String k7m3;
    //private String k8m3;
    //private String k9m3;
    //private String k10m3;
    //private String k11m3;
    //private String k12m3;
    
    private Date datumvreme = new Date();
    private int pocetnoVreme = 0;

    private final AccessSetupPLC accessSetup;
    private AccessSetupInterface accessSettupInterface;
    private IzvestajProizvodnjaBetonaFilter proizvodnjaBetona;
    private final KorpaBeton korpa;
    private DetaljniIzvestajFilter detaljniIzvestajFilter;
    private ZbirniIzvestajFilter1 zbirniIzvestajFilter;

    private Thread t1;

    private final OtpremnicaService otpremnica;
    private final MasinistaService masinistaDb;
    private final VozacService vozacDb;
    private final GradilisteService gradilisteDb;
    private final RegtabliceService regtabliceDb;
    private final KupacService kupacDb;
    private final VrstaCementaService1 vrstaCementaDb;
    private final VrstaCementaService2 vrstaCementa2Db;
    private final VrstaFilerService vrstaFilerDb;
    private final SertifikatService sertifikatDb;
    private final ZrnomaxService zrnomaxDb;
    private final SvojstvaService svojstvaDb;
    private final Aditiv1Service aditiv1Db;
    private final Aditiv2Service aditiv2Db;
    private final Aditiv3Service aditiv3Db;
    private final SpecbetonaService specifBetDb;
    private final KonzbetonaService konzBetonaDb;
    private final StandbetonaService standbetonaDb;
    private final InterfRecepturaService recepturaTextDb;
    private final KomponenteService komponeteDb;
    private final KService komp;

    private AddVrstaCementa addVrstaCementa = null;
    private AddVrstaCementa2 addVrstaCementa2 = null;
    private AddVrstaFiler addVrstaFiler = null;
    private AddSertifikat addSertifikat = null;
    private AddZrnomax addZrnomax = null;
    private AddSvojstva addSvojstva = null;
    private AddAditiv1 addAditiv1 = null;
    private AddAditiv2 addAditiv2 = null;
    private AddAditiv3 addAditiv3 = null;
    private AddSpecBetona addSpecBetona = null;
    private AddKonzBetona addKonzBetona = null;
    private AddStandBetona addStandBetona = null;
    private AddMasinista addMasinista = null;
    private AddGradiliste addGradiliste = null;
    private AddRegTab addRegTab = null;
    private AddVozac1 addVozac = null;
    private AddKupc addKupac = null;

    private Buff modbus;
    public static boolean setujPodesavanjaModbus = false;
    public static boolean restartConn = false;

    private List<Db.K> k;
    private Timer timer;
    private Timer timerStartDate;
    private Timer timerDb;
    private Timer timerRestart;
    public static boolean osveziBazu = false;
    public static boolean osveziBazuVozac = false;
    public static boolean osveziBazuRegTab = false;
    public static boolean osveziBazuVozacregTab = false;

    private String textDisplay;
    private final InterfDisplejService displejDb;
    private int textDisplayBefore;

    private String cementDisplay;
    private final InterfCementService cementTextDb;
    private int cementDisplayBefore;

    private String vodaDisplay;
    private final InterfVodaService vodaTextDb;
    private int vodaDisplayBefore;

    private String aditivDisplay;
    private final InterfAditivService aditivTextDb;
    private int aditivDisplayBefore;

    private String agregatDisplay;
    private final InterfAgregatService agregatTextDb;
    private int agregatDisplayBefore;

    private String mesalicaDisplay;
    private final InterfMesalicaService mesalicaTextDb;
    private int mesalicaDisplayBefore;

    private String recepturaDisplay;

    private int recepturaDisplayBefore;

    private Calendar cal;
    private final SimpleDateFormat sdf;

    private final VozackamionService vodaKamionDb;

    private String brOtpremnice = "";

    private Baza bazaAdmin;
    private int brIzgubljenihKonekcija = 0;
    private static int count = 0;

    private final Timer tbdb;
    private final BackupPostgresql backupDb;
    private boolean pokusajPonovoUpisOtpremnice = false;
    private RestoreBackDb restoreDb;

    private int formatK1 = 0;
    private int formatK2 = 0;
    private int formatK3 = 0;
    private int formatK4 = 0;
    private int formatK5 = 0;
    private int formatK6 = 0;
    private int formatK7 = 0;
    private int formatK8 = 0;
    private int formatK9 = 0;
    private int formatK10 = 0;
    private int formatK11 = 0;
    private int formatK12 = 0;

    private String brSarze;

    private int waitForWriteInBaza = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HelloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelloFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HelloFrame().setVisible(true);
            }
        });

    }

    public static int shiftPointDouble(int a, int br) {
        try {
            int x = a;

            if (x > 32768) {  // zbog unsigned
                x = x - 65536;
            }

            // for (int i = 1; i <= br; i++) {
            //     x = x * .1;
            // }
            //DecimalFormat df = new DecimalFormat("#####.####");
            //return df.format(x);
            return x;
        } catch (Exception ex) {
            return 0;
        }
    }

}
