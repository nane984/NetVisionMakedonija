/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import DbService.KService;
import DbService.KomponenteService;
import Utility.Convert;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import path.ZaglavljePath;

/**
 *
 * @author branko.scekic
 */
public class DetaljniIzvestaj1 extends javax.swing.JFrame {

    /**
     * Creates new form Print1
     *
     * @param otp
     *
     */
    public DetaljniIzvestaj1(Db.Otpremnica otp, DetaljniIzvestaj detaljniIzvestaj) {

        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane6.getViewport().setBackground(Color.WHITE);

        komponenteDb = new KomponenteService();
        komp = new KService();
        otprem = otp;
        
        this.detaljniIzvestaj = detaljniIzvestaj;

        loadZaglavlje();
        fillLable(otprem);

    }

    private void loadZaglavlje() {
        try {
            path = new ZaglavljePath();
            //JOptionPane.showMessageDialog(null,path.getZaglavlje());
            //jLabelZaglavlje.setIcon(new javax.swing.ImageIcon(path.getZaglavlje())); // NOI18N
        } catch (Exception e) {
            //System.out.print(e);
        }
    }

    private void fillLable(Db.Otpremnica otpremnica) {

        jLabelBrOtpremnice.setText(Integer.toString(otpremnica.getBrotpremnice()));
        jLabelMarkaBetona.setText(otpremnica.getReceptura());
        jLabelKupac.setText(otpremnica.getKupac());
        jLabelGradiliste.setText(otpremnica.getGradiliste());
        jLabelDatum.setText(Convert.convertDateToStringDate(otpremnica.getDatumvreme()));
        jLabelVreme.setText(Convert.convertDateToStringTime(otpremnica.getDatumvreme()));
        jLabelMasinista.setText(otpremnica.getMasinista());
        jLabelVozac.setText(otpremnica.getVozac());
        jLabelBrVozila.setText(otpremnica.getRegtablice());

        fillTable(otpremnica.getBrotpremnice());

    }

    public void fillTable(int idOtpremnice) {
        try {
            List<Db.Komponente> result = komponenteDb.getKomponenteWhere(idOtpremnice);

            List<Db.K> k = komp.getKomponente();

            DecimalFormat df2 = new DecimalFormat("#.##");
            DecimalFormat df1 = new DecimalFormat("##.#");

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
            double ukupnok11Ostvareno = 0;
            double ukupnok11Zadato = 0;
            double ukupnok12Ostvareno = 0;
            double ukupnok12Zadato = 0;
            


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

            int i = 0;
            Object[][] obj = new Object[result.size() + 3][size + 1];
            for (Db.Komponente auto : result) {

                obj[i][0] = auto.getBrSarze();              // da bi imali r.b kolonu stavili smo size + 1;
                obj[result.size()][0] = "Σ recept";
                obj[result.size() + 1][0] = "izdato";
                obj[result.size() + 2][0] = "%";

                if (k.get(0).getIfcheck() == 1) {
                    obj[i][1] = auto.getK1Ostv();
                    //obj[i][1] = auto.getKorigovanof1();
                    ukupnok1Ostvareno += auto.getK1Ostv().doubleValue();
                    ukupnok1Zadato += kolicinaZadataPreracunato(auto.getK1Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    //korekcijaf1 += checkFloatIsNull(auto.getKorigovanof1());

                    if (i == result.size() - 1) {
                        obj[result.size()][1] = df2.format(ukupnok1Zadato);
                        //obj[result.size()][1] = df2.format(korekcijaf1);
                        obj[result.size() + 1][1] = df2.format(ukupnok1Ostvareno);
                        obj[result.size() + 2][1] = df2.format((ukupnok1Ostvareno / ukupnok1Zadato) * 100) + "  %";
                    }
                    jLabelKolicina.setText(auto.getKolicinaostvarenakg().toString() + " m3");

                }
                if (k.get(1).getIfcheck() == 1) {
                    obj[i][2] = auto.getK2Ostv();
                    //obj[i][2] = auto.getKorigovanof2();
                    ukupnok2Ostvareno += auto.getK2Ostv().doubleValue();
                    ukupnok2Zadato += kolicinaZadataPreracunato(auto.getK2Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    //korekcijaf2 += checkFloatIsNull(auto.getKorigovanof2());

                    if (i == result.size() - 1) {
                        obj[result.size()][2] = df2.format(ukupnok2Zadato);
                        //obj[result.size()][2] = df2.format(korekcijaf2);
                        obj[result.size() + 1][2] = df2.format(ukupnok2Ostvareno);
                        obj[result.size() + 2][2] = df2.format((ukupnok2Ostvareno / ukupnok2Zadato) * 100) + "  %";
                    }

                }

                if (k.get(2).getIfcheck() == 1) {
                    obj[i][3] = auto.getK3Ostv();
                    //obj[i][3] = auto.getKorigovanof3();
                    ukupnok3Ostvareno += auto.getK3Ostv().doubleValue();
                    ukupnok3Zadato += kolicinaZadataPreracunato(auto.getK3Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    //korekcijaf3 += checkFloatIsNull(auto.getKorigovanof3());

                    if (i == result.size() - 1) {
                        obj[result.size()][3] = df2.format(ukupnok3Zadato);
                        //obj[result.size()][3] = df2.format(korekcijaf3);
                        obj[result.size() + 1][3] = df2.format(ukupnok3Ostvareno);
                        obj[result.size() + 2][3] = df2.format((ukupnok3Ostvareno / ukupnok3Zadato) * 100) + "  %";
                    }
                }

                if (k.get(3).getIfcheck() == 1) {
                    obj[i][4] = auto.getK4Ostv();
                    //obj[i][4] = auto.getKorigovanof4();
                    ukupnok4Ostvareno += auto.getK4Ostv().doubleValue();
                    ukupnok4Zadato += kolicinaZadataPreracunato(auto.getK4Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    //korekcijaf4 += checkFloatIsNull(auto.getKorigovanof4());

                    if (i == result.size() - 1) {
                        obj[result.size()][4] = df2.format(ukupnok4Zadato);
                        //obj[result.size()][4] = df2.format(korekcijaf4);
                        obj[result.size() + 1][4] = df2.format(ukupnok4Ostvareno);
                        obj[result.size() + 2][4] = df2.format((ukupnok4Ostvareno / ukupnok4Zadato) * 100) + "  %";
                    }
                }

                if (k.get(4).getIfcheck() == 1) {
                    obj[i][5] = auto.getK5Ostv();
                    ukupnok5Ostvareno += auto.getK5Ostv().doubleValue();

                    ukupnok5Zadato += kolicinaZadataPreracunato(auto.getK5Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][5] = df2.format(ukupnok5Zadato);
                        obj[result.size() + 1][5] = df2.format(ukupnok5Ostvareno);
                        obj[result.size() + 2][5] = df2.format((ukupnok5Ostvareno / ukupnok5Zadato) * 100) + "  %";
                    }
                }

                if (k.get(5).getIfcheck() == 1) {
                    obj[i][6] = auto.getK6Ostv();
                    ukupnok6Ostvareno += auto.getK6Ostv().doubleValue();

                    ukupnok6Zadato += kolicinaZadataPreracunato(auto.getK6Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][6] = df2.format(ukupnok6Zadato);
                        obj[result.size() + 1][6] = df2.format(ukupnok6Ostvareno);
                        obj[result.size() + 2][6] = df2.format((ukupnok6Ostvareno / ukupnok6Zadato) * 100) + "  %";
                    }
                }

                if (k.get(6).getIfcheck() == 1) {
                    obj[i][7] = auto.getK7Ostv();
                    ukupnok7Ostvareno += auto.getK7Ostv().doubleValue();

                    ukupnok7Zadato += kolicinaZadataPreracunato(auto.getK7Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][7] = df2.format(ukupnok7Zadato);
                        obj[result.size() + 1][7] = df2.format(ukupnok7Ostvareno);
                        obj[result.size() + 2][7] = df2.format((ukupnok7Ostvareno / ukupnok7Zadato) * 100) + "  %";
                    }
                }

                if (k.get(7).getIfcheck() == 1) {
                    obj[i][8] = auto.getK8Ostv();
                    ukupnok8Ostvareno += auto.getK8Ostv().doubleValue();

                    ukupnok8Zadato += kolicinaZadataPreracunato(auto.getK8Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][8] = df2.format(ukupnok8Zadato);
                        obj[result.size() + 1][8] = df2.format(ukupnok8Ostvareno);
                        obj[result.size() + 2][8] = df2.format((ukupnok8Ostvareno / ukupnok8Zadato) * 100) + "  %";
                    }
                }

                if (k.get(8).getIfcheck() == 1) {
                    obj[i][9] = auto.getK9Ostv();
                    ukupnok9Ostvareno += auto.getK9Ostv().doubleValue();

                    ukupnok9Zadato += kolicinaZadataPreracunato(auto.getK9Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][9] = df2.format(ukupnok9Zadato);
                        obj[result.size() + 1][9] = df2.format(ukupnok9Ostvareno);
                        obj[result.size() + 2][9] = df2.format((ukupnok9Ostvareno / ukupnok9Zadato) * 100) + "  %";
                    }
                }

                if (k.get(9).getIfcheck() == 1) {
                    obj[i][10] = auto.getK10Ostv();
                    ukupnok10Ostvareno += auto.getK10Ostv().doubleValue();

                    ukupnok10Zadato += kolicinaZadataPreracunato(auto.getK10Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][10] = df2.format(ukupnok10Zadato);
                        obj[result.size() + 1][10] = df2.format(ukupnok10Ostvareno);
                        obj[result.size() + 2][10] = df2.format((ukupnok10Ostvareno / ukupnok10Zadato) * 100) + "  %";
                    }
                }
                
                if (k.get(10).getIfcheck() == 1) {
                    obj[i][11] = auto.getK11Ostv();
                    ukupnok11Ostvareno += auto.getK11Ostv().doubleValue();

                    ukupnok11Zadato += kolicinaZadataPreracunato(auto.getK11Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][11] = df2.format(ukupnok11Zadato);
                        obj[result.size() + 1][11] = df2.format(ukupnok11Ostvareno);
                        obj[result.size() + 2][11] = df2.format((ukupnok11Ostvareno / ukupnok11Zadato) * 100) + "  %";
                    }
                }
                
                if (k.get(11).getIfcheck() == 1) {
                    obj[i][12] = auto.getK12Ostv();
                    ukupnok12Ostvareno += auto.getK12Ostv().doubleValue();

                    ukupnok12Zadato += kolicinaZadataPreracunato(auto.getK12Zad().doubleValue(), otprem.getKolicinazadatam3(), result.size());

                    if (i == result.size() - 1) {
                        obj[result.size()][12] = df2.format(ukupnok12Zadato);
                        obj[result.size() + 1][12] = df2.format(ukupnok12Ostvareno);
                        obj[result.size() + 2][12] = df2.format((ukupnok12Ostvareno / ukupnok12Zadato) * 100) + "  %";
                    }
                }

                i++;
            }

            String[] zaglavlje = new String[size + 1];

            for (int m = 0; m < size; m++) {
                zaglavlje[0] = "br s.";
                zaglavlje[m + 1] = k.get(m).getKomponenta();

            }

            model = new DefaultTableModel(obj, zaglavlje);
            jTable1.setModel(model);
            jTable1.setRowMargin(2);
            jTable1.setRowHeight(30);
            jTable1.setShowGrid(true);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(65);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private double kolicinaZadataPreracunato(double zadatoPo1m3, double zadatoBetona, int zadatoBrSarzi){
        if(zadatoPo1m3 != 0 && zadatoBetona != 0 && zadatoBrSarzi!=0){
            return (zadatoPo1m3*zadatoBetona)/zadatoBrSarzi;
        }
        return 0;
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
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelPodaci = new javax.swing.JPanel();
        jLabelBrOtpr = new javax.swing.JLabel();
        jLabelBrOtpremnice = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelMarkaBetona = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabelKupac = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelDatum = new javax.swing.JLabel();
        jLabelKolicina = new javax.swing.JLabel();
        jLabelGradiliste = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelVreme = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabelMasinista = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelVozac = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabelBrVozila = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 800));
        setSize(new java.awt.Dimension(1100, 800));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detaljni Izvestaj", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tempus Sans ITC", 0, 11), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(1400, 950));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1200, 900));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setBorder(null);
        jScrollPane6.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

        jPanelPodaci.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPodaci.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelBrOtpr.setText("Broj otpreme \\ Shipping number:: ");

        jLabel5.setText("Marka betona \\ Concrete grade:  ");

        jLabel7.setText("Kupac \\ Customer :  ");

        jLabel3.setText("Datum \\ Date: ");

        jLabel6.setText("Količina m3 \\ Quantity m3:  ");

        jLabel8.setText("Gradilište \\  Construction area:  ");

        jLabel4.setText("Vreme \\ Time: ");

        jLabel9.setText("Masinista \\ Operator:");

        jLabel10.setText("Vozač \\ Driver:  ");

        jLabel11.setText("Kamion \\ Truck:");

        javax.swing.GroupLayout jPanelPodaciLayout = new javax.swing.GroupLayout(jPanelPodaci);
        jPanelPodaci.setLayout(jPanelPodaciLayout);
        jPanelPodaciLayout.setHorizontalGroup(
            jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPodaciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelBrOtpr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(67, 67, 67)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelKupac, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jLabelMarkaBetona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelMasinista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPodaciLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPodaciLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelPodaciLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPodaciLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)))))
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelKolicina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelVozac, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBrVozila, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanelPodaciLayout.setVerticalGroup(
            jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPodaciLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBrOtpremnice, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBrOtpr)
                    .addComponent(jLabel3)
                    .addComponent(jLabelDatum, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelVreme, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBrVozila, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabelMarkaBetona, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelKolicina, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabelKupac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabelGradiliste, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanelPodaciLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabelMasinista, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabelVozac, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanelPodaci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPodaci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 853, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
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

        jMenu2.setText("Sarza");

        jMenuItem2.setText("Dodati");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Promeniti");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1444, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        printReport();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

        accessAdded = new AccessAddedSarza(jLabelBrOtpremnice.getText(), detaljniIzvestaj, this);
        accessAdded.setVisible(true);

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        ModifiedSarza ms= new ModifiedSarza(komponenteDb, otprem.getBrotpremnice(), detaljniIzvestaj, this);
        ms.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private double checkFloatIsNull(Float vr) {
        if (vr == null) {
            vr = 1f;
        }
        return vr;

    }

    private void printReport() {
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat preformat = pjob.defaultPage();
        preformat.setOrientation(PageFormat.LANDSCAPE);

        Paper pPaper = preformat.getPaper();

        pPaper.setImageableArea(5.0, 5.0, pPaper.getWidth() - 10, pPaper.getHeight() - 10);
        preformat.setPaper(pPaper);

        //Set print component
        pjob.setPrintable(new PrintReport(jPanel4), preformat);
        if (pjob.printDialog()) {
            try {
                pjob.setJobName(jLabelBrOtpremnice.getText() + "_" + jLabelKupac.getText());
                pjob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
    
    

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelPodaci;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private ZaglavljePath path;
    private final KomponenteService komponenteDb;
    private final KService komp;
    private final Db.Otpremnica otprem;

    private DefaultTableModel model;
    private Map<String, Object> podaci;

    private AccessAddedSarza accessAdded;
    
    private DetaljniIzvestaj detaljniIzvestaj;
}
