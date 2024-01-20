/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import DbService.KService;
import DbService.KomponenteService;
import Frame.MainFrame;
import java.net.URL;
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
import static tools.DoubleFormat.getRounDouble;

/**
 *
 * @author branko.scekic
 */
public class IzvestajPotrosnjaKomponenti extends javax.swing.JFrame {

    /**
     * Creates new form Print1
     *
     * @param otp
     *
     */
    public IzvestajPotrosnjaKomponenti(List<Db.Otpremnica> result, KomponenteService komponenteDb,
            KService kDb, 
            String Kupac, String Gradiliste,
            String Vozac, String tablice, String Masinista, String datePickerOd, String timePickerOd,
            String datePickerDo, String timePickerDo, String MarkaBetona) {

        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
        //jScrollPane6.getViewport().setBackground(Color.WHITE);

        this.komponenteDb = komponenteDb;
        this.komp = kDb;

        
        
            loadZaglavlje(Kupac, Gradiliste, Vozac, tablice, Masinista, datePickerOd, timePickerOd,
                    datePickerDo, timePickerDo, MarkaBetona);
            
            
             
            
             
            fillTable(result);
            
       

    }

    private void loadZaglavlje(String Kupac, String Gradiliste, String Vozac, 
            String tablice, 
            String Masinista, String datePickerOd, String timePickerOd,
            String datePickerDo, String timePickerDo, String MarkaBetona) {
        
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
        
        if (MarkaBetona.length() != 0) {
            podaci.put("markBetona", MarkaBetona);
           
        }
        if (Kupac.length() != 0) {
           podaci.put("kupac", Kupac);
        }
        if (datePickerOd.length() != 0) {
           podaci.put("datumOd", datePickerOd);
        }
        if (timePickerOd.length() != 0 && !timePickerOd.equalsIgnoreCase("00:00")) {
           podaci.put("vremeOd", timePickerOd);
        }
        if (Gradiliste.length() != 0) {   
           podaci.put("gradiliste", Gradiliste);
        }

        if (datePickerDo.length() != 0) {
           podaci.put("datumDo", datePickerDo);
        }

        if (timePickerDo.length() != 0 && !timePickerDo.equalsIgnoreCase("23:59")) {
           podaci.put("vremeDo", timePickerDo);
        }

        if (Masinista.length() != 0) {
           podaci.put("masinista", Masinista);
        }

        if (Vozac.length() != 0) {
          podaci.put("vozac", Vozac);
        }

        if (tablice.length() != 0) {    
           podaci.put("brVozila", tablice);
        }
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

            List<Db.K> k = komp.getKomponente();

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
                        obj[i][10] = getRounDouble(ukupnok8, formatK[8]);
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
                        ukupnok10Ukupno += Double.parseDouble(obj[i][11].toString());;
                        if (i + 1 == otpremnica.size()) {
                            obj[otpremnica.size()][11] = getRounDouble(ukupnok10Ukupno, formatK[9]) + " Kg";
                        }
                    }
                }

            }

            zaglavlje = new String[size + 2];
            zaglavlje[0] = "r.b";
            zaglavlje[1] = "Otp.br";

            for (int m = 0; m < size; m++) {
                zaglavlje[m + 2] = k.get(m).getKomponenta();

            }
            
            

            
            DefaultTableModel model = new DefaultTableModel(obj, zaglavlje);
            jTable1.setModel(model);
            jTable1.setRowMargin(4);
            jTable1.setRowHeight(35);
            jTable1.setShowGrid(true);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(65);
            jTable1.setDefaultEditor(Object.class, null);
            
            printReport();
        
    }
    private void printReport(){
        try {
            DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            //JRTableModelDataSource datasource = new JRTableModelDataSource(de);
            JasperReport jr = JasperCompileManager.compileReport("C:\\NetVision\\reports\\ReportPotrosnjaKomponenti.jrxml");
         
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, podaci, new JRTableModelDataSource(de));
            JasperViewer.viewReport(jasperPrint,false);
            
            
            
        } catch (JRException ex) {
            Logger.getLogger(DetaljniIzvestaj1.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(781, 902));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "r.b", "K1", "K2", "K3", "K4", "K5", "K6", "K7", "K8", "K9", "K10"
            }
        ));
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(19);
        jTable1.setRowMargin(3);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private final KomponenteService komponenteDb;
    private final KService komp;
    private String[] zaglavlje;
    
    private Map<String, Object> podaci;

}
