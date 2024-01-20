/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filterframe;

import Db.Interfreceptura;
import DbService.GradilisteService;
import DbService.InterfRecepturaService;
import DbService.KupacService;
import DbService.MasinistaService;
import DbService.OtpremnicaService;
import DbService.RegtabliceService;
import DbService.VozacService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brasa
 */
public class DetaljniIzvestajModified extends javax.swing.JFrame {

    /**
     * Creates new form DetaljniIzvestajFilter
     *
     * @param jtable
     * @param otpremnicaDB
     */
    public DetaljniIzvestajModified(DefaultTableModel model, OtpremnicaService otpremnicaDB) {
        initComponents();
        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        this.otpremnicaDB = otpremnicaDB;
        this.model = model;
        
        this.masinistaDb = new MasinistaService();
        this.vozacDb = new VozacService();
        this.kupacDb = new KupacService();
        this.gradilisteDb = new GradilisteService();
        this.vrstaCementaDb = new InterfRecepturaService();
        this.regtabliceDb = new RegtabliceService();

        kupac = new JComboBox<>();
        gradiliste = new JComboBox<>();
        markaBetona = new JComboBox<>();
        vozac = new JComboBox<>();
        tablice = new JComboBox<>();
        masinista = new JComboBox<>();
        
        loadTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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
                "R.B.", "Br. otpreme", "Kupac", "Gradiliste", "Vozac", "Tablice", "Masinista", "Marka betona", "Kolicina betona", "Datum", "Vreme"
            }
        ));
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jLabel11.setBackground(new java.awt.Color(0, 255, 255));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Detaljni izvestaj dozvola modifikacije");
        jLabel11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jLabel11.setOpaque(true);

        jButton1.setText("Sacuvaj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Izlaz");
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
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        boolean upisano = false;
        
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            try {
                otpremnicaDB.updateReceptura(Integer.parseInt(jTable1.getValueAt(i, 1).toString()), chekIfNull(jTable1.getValueAt(i, 2).toString()), 
                             chekIfNull(jTable1.getValueAt(i, 3).toString()), chekIfNull(jTable1.getValueAt(i, 4).toString()), 
                             chekIfNull(jTable1.getValueAt(i, 5).toString()), chekIfNull(jTable1.getValueAt(i, 6).toString()),
                             chekIfNull(jTable1.getValueAt(i, 7).toString()));
                upisano = true;
            } catch (java.lang.NullPointerException ex) {
                upisano = false;
            } catch (java.lang.Exception e) {
                upisano = false; 
            }

        }
        if(upisano){
            JOptionPane.showMessageDialog(null, "Sacuvali ste izmene");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void loadTable() {
        
        getPodaci();
        
        jTable1.setModel(model);
        jTable1.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(kupac));
        jTable1.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(gradiliste));
        jTable1.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(vozac));
        jTable1.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(tablice));
        jTable1.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(masinista));
        jTable1.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(markaBetona));
        jTable1.setRowMargin(4);
        jTable1.setRowHeight(40);
        jTable1.setShowGrid(true);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
        

    }

    private String chekIfNull(String a) {
        if (a == null) {
            return "*";
        } else {
            return a;
        }
    }
    
    private void getPodaci(){
        String[] kupacString = new String[]{""};
        String[] gradilisteString = new String[]{""};
        String[] vozacString = new String[]{""};
        String[] markaBetonaString = new String[]{""};
        String[] tabliceString = new String[]{""};
        String[] masinistaString = new String[]{""};
        
        
        try {
            //List<Db.Otpremnica> otpremnica = otpremnicaDB.getOtpremnice();
            List<Db.Masinista> masin = masinistaDb.getMasinistaWithX();
            List<Db.Vozaci> voz = vozacDb.getVozaciWithX();
            List<Db.Kupac> kup = kupacDb.getKupciWithX();
            List<Db.Gradiliste> grad = gradilisteDb.getGradilistaWithX();
            List<Interfreceptura> vrc = vrstaCementaDb.getInterface();
            List<Db.Regtablice> regtab = regtabliceDb.getRegTabliceWithX();

            kupacString = new String[kup.size()];
            markaBetonaString = new String[vrc.size()];
            gradilisteString = new String[grad.size()];
            vozacString = new String[voz.size()];
            tabliceString = new String[regtab.size()];
            masinistaString = new String[masin.size()];
            

            for (int i = 0; i < regtab.size(); i++) {
                tabliceString[i] = regtab.get(i).getTablice();
            }
 
            for (int i = 0; i < vrc.size(); i++) {
                markaBetonaString[i] = vrc.get(i).getText();
            }
            
            for (int i = 0; i < grad.size(); i++) {
                gradilisteString[i] = grad.get(i).getGradiliste();
            }
            
            for (int i = 0; i < kup.size(); i++) {
                kupacString[i] = kup.get(i).getKupac();
            }
            
            for (int i = 0; i < voz.size(); i++) {
                vozacString[i] = voz.get(i).getImeprezimevozaci();
            }
            
            for (int i = 0; i < masin.size(); i++) {
                masinistaString[i] = masin.get(i).getImePrezime();
            }
             
        } catch (Exception e) {
            kupacString = new String[]{""};
            markaBetonaString = new String[]{""};
            gradilisteString = new String[]{""};
            vozacString = new String[]{""};
            tabliceString = new String[]{""};
            masinistaString = new String[]{""};
        }
        
        List<String> kup = new ArrayList<>(Arrays.asList(kupacString)).stream().distinct().collect(Collectors.toList());
        List<String> mb = new ArrayList<>(Arrays.asList(markaBetonaString)).stream().distinct().collect(Collectors.toList());
        List<String> grad = new ArrayList<>(Arrays.asList(gradilisteString)).stream().distinct().collect(Collectors.toList());
        List<String> voz = new ArrayList<>(Arrays.asList(vozacString)).stream().distinct().collect(Collectors.toList());
        List<String> tab = new ArrayList<>(Arrays.asList(tabliceString)).stream().distinct().collect(Collectors.toList());
        List<String> mas = new ArrayList<>(Arrays.asList(masinistaString)).stream().distinct().collect(Collectors.toList());
        
        kupac.setModel(new DefaultComboBoxModel(kup.toArray()));
        markaBetona.setModel(new DefaultComboBoxModel(mb.toArray()));
        gradiliste.setModel(new DefaultComboBoxModel(grad.toArray()));
        vozac.setModel(new DefaultComboBoxModel(voz.toArray()));
        tablice.setModel(new DefaultComboBoxModel(tab.toArray()));
        masinista.setModel(new DefaultComboBoxModel(mas.toArray()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
    private final OtpremnicaService otpremnicaDB;
    DefaultTableModel model;
    
    private final JComboBox<String> kupac;
    private final JComboBox<String> markaBetona;
    private final JComboBox<String> gradiliste;
    private final JComboBox<String> vozac;
    private final JComboBox<String> tablice;
    private final JComboBox<String> masinista;
    
    private final MasinistaService masinistaDb;
    private final VozacService vozacDb;
    private final KupacService kupacDb;
    private final GradilisteService gradilisteDb;
    private final InterfRecepturaService vrstaCementaDb;
    private final RegtabliceService regtabliceDb;
}
