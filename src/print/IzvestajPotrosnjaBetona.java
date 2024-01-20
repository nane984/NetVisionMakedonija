/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import DbService.KomponenteService;
import Frame.MainFrame;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.URL;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PageRanges;

import javax.swing.ImageIcon;
import javax.swing.JTable;
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
public class IzvestajPotrosnjaBetona extends javax.swing.JFrame {

    /**
     * Creates new form Print1
     *
     * @param otp
     *
     */
    public IzvestajPotrosnjaBetona(List<Db.Otpremnica> result, String Otpremnica, String Kupac, String Gradiliste,
            String Vozac, String tablice, String Masinista, String datePickerOd, String timePickerOd,
            String datePickerDo, String timePickerDo,  KomponenteService komponeteDb) {

        initComponents();

        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        jScrollPane6.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane6.getViewport().setBackground(Color.WHITE);
        
        this.komponeteDb = komponeteDb;

        loadZaglavlje(result, Otpremnica, Kupac, Gradiliste,
                Vozac, tablice, Masinista, datePickerOd, timePickerOd,
                datePickerDo, timePickerDo);
        fillTable(result);

    }

    private void loadZaglavlje(List<Db.Otpremnica> result, String Otpremnica, String Kupac, String Gradiliste,
            String Vozac, String tablice, String Masinista, String datePickerOd, String timePickerOd,
            String datePickerDo, String timePickerDo) {

        podaci = new HashMap();
        podaci.put("otpremnica", "");
        podaci.put("kupac", "");
        podaci.put("datumOd", "");
        podaci.put("vremeOd", "");
        podaci.put("gradiliste", "");
        podaci.put("datumDo", "");
        podaci.put("vremeDo", "");
        podaci.put("masinista", "");
        podaci.put("vozac", "");
        podaci.put("brVozila", "");

        if (Otpremnica.length() != 0) {
            podaci.put("otpremnica", Otpremnica);
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

    private void fillTable(List<Db.Otpremnica> result) {
        double ukupno = 0;

        int j = 0;
        String a = "";
        for (Db.Otpremnica auto : result) {
            if (!a.endsWith(auto.getReceptura())) {
                j += 1;
                a = auto.getReceptura();
                //System.out.println(auto.getBrotpremnice() + "  " +auto.getReceptura()+ "  " + j);
            }
        }

        int i = 0;
        String b = "";

        Object[][] obj = new Object[j + 1][3];

        for (Db.Otpremnica auto : result) {
            if (!b.endsWith(auto.getReceptura())) {
                b = auto.getReceptura();

                obj[i][0] = i + 1;
                obj[i][1] = auto.getReceptura();
                obj[i][2] = getRounDouble(komponeteDb.getLastKomponentWhere(auto.getBrotpremnice()).getKolicinaostvarenakg().doubleValue(), 2);
                ukupno += komponeteDb.getLastKomponentWhere(auto.getBrotpremnice()).getKolicinaostvarenakg().doubleValue();
                obj[j][0] = "ukupno";

                i++;
            } else {
                obj[i - 1][2] = getRounDouble((Double.parseDouble(obj[i - 1][2].toString()) + komponeteDb.getLastKomponentWhere(auto.getBrotpremnice()).getKolicinaostvarenakg().doubleValue()), 2);
                ukupno += komponeteDb.getLastKomponentWhere(auto.getBrotpremnice()).getKolicinaostvarenakg().doubleValue();
            }

            if (i == j) {
                obj[j][2] = getRounDouble(ukupno, 2) + "   m3";
            }

        }

        DefaultTableModel model = new DefaultTableModel(obj, new Object[]{"r.b", "Marka Betona", "Kolicina"});
        jTable1.setModel(model);
        jTable1.setRowMargin(4);
        jTable1.setRowHeight(40);
        jTable1.setShowGrid(true);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
        jTable1.setDefaultEditor(Object.class, null);

        printReport();
    }

    private void printReport() {
        try {
            DefaultTableModel de = (DefaultTableModel) jTable1.getModel();
            //JRTableModelDataSource datasource = new JRTableModelDataSource(de);
            JasperReport jr = JasperCompileManager.compileReport("C:\\NetVision\\reports\\ReportPotrosnjaBetona.jrxml");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, podaci, new JRTableModelDataSource(de));
            JasperViewer.viewReport(jasperPrint, false);

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

        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(900, 1200));

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
        jTable1.setFillsViewportHeight(true);
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(204, 204, 204));
        jTable1.setRowHeight(19);
        jTable1.setRowMargin(3);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(jTable1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Izvestaj o potrosnji betona"));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(4);
        jTextArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 831, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        int pageNumber = jTable1.getRowCount() / 28;
        if (jTable1.getRowCount() % 28 > 0) {
            pageNumber++;
        }

        try {

            MessageFormat headerFormat = new MessageFormat(jTextArea1.getText());
            MessageFormat footerFormat = new MessageFormat("             "
                    + "                                                  "
                    + "                                                  "
                    + "        str:  {0} / " + pageNumber);

            PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
            attr.add(OrientationRequested.PORTRAIT);
            attr.add(DialogTypeSelection.NATIVE);
            attr.add(new PageRanges(1, pageNumber));
            attr.add(new MediaPrintableArea(10, 6, 200, 263, MediaSize.MM)); // A4: 216x297mm  A4: 210x297mm
            // left 10  , top 6  ,  right(mereno od pocetka lista 216 - 200 - 10 , bottom 279-263 - 6

            jTable1.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat, true, attr, true);

            //set print range from to
        } catch (PrinterException ex) {
            Logger.getLogger(IzvestajPotrosnjaKomponenti.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private Map<String, Object> podaci;
    private final KomponenteService komponeteDb;
}
