/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import DbService.RegtabliceService;
import DbService.VozacService;
import DbService.VozackamionService;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author brasa
 */
public class VozaciVozila extends javax.swing.JFrame {

    /**
     * Creates new form VozaciVozila
     */
    public VozaciVozila() {
        initComponents();
        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        
        jTextFieldGradiliste = new JTextField();
        result = new JLabel();
        result.setText("");
        
      
        regTablicaDb = new RegtabliceService();
        readTablice();
        rowSorterTruck = new TableRowSorter<>(jTableTablice.getModel());
        jTableTablice.setRowSorter(rowSorterTruck);
        addFilterTablice();
        
        
        vozacDb = new VozacService();
        readVozac();
        rowSorterDriver = new TableRowSorter<>(jTableVozac.getModel());
        jTableVozac.setRowSorter(rowSorterDriver);
        addFilterVozac();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextFieldVozac = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldSlova = new javax.swing.JTextField();
        jTextFieldBrojevi = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTablice = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableVozac = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Vozac");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 20, 110, 30);
        getContentPane().add(jTextFieldVozac);
        jTextFieldVozac.setBounds(10, 60, 370, 60);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reg tablice");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(590, 20, 160, 30);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jTextFieldSlova.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSlovaKeyReleased(evt);
            }
        });

        jTextFieldBrojevi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBrojeviKeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "AL", "AR", "AC", "BG", "BO", "BP", "BT", "BĆ", "BU", "BČ", "VA", "VB", "VL", "VP", "VR", "VS", "VŠ", "GL", "GM", "DE", "ĐA", "ZA", "ZR", "IN", "IC", "JA", "KA", "KV", "KG", "KŽ", "KI", "KL", "KM", "KO", "KŠ", "LB", "LE", "LO", "LU", "NV", "NG", "NI", "NP", "NS", "PA", "PB", "PE", "PŽ", "PZ", "PI", "PK", "PN", "PO", "PP", "PR", "PT", "RA", "RU", "SA", "SV", "SD", "SM", "SO", "SP", "ST", "SU", "TO", "TS", "ĆU", "UB", "UE", "UR", "ČA", "ŠA", "ŠI" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jTextFieldBrojevi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldSlova, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSlova, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBrojevi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(616, 54, 370, 90);

        jTableTablice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "rb", "tablice"
            }
        ));
        jTableTablice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTabliceMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableTablice);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(610, 150, 370, 270);

        jTableVozac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "rb", "Ime i prezime"
            }
        ));
        jTableVozac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableVozacMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableVozac);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 150, 560, 270);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        jButton1.setText("Izlaz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(880, 520, 102, 36);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Left-right.png"))); // NOI18N
        jButton3.setText("Povezi");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(410, 440, 190, 40);

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel11);
        jLabel11.setBounds(10, 440, 380, 40);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(640, 440, 340, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vozactruck.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, 0, 990, 570);

        setSize(new java.awt.Dimension(987, 566));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldSlovaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSlovaKeyReleased

        jTextFieldGradiliste.setText(jComboBox1.getSelectedItem().toString() +jTextFieldBrojevi.getText() + jTextFieldSlova.getText());
    }//GEN-LAST:event_jTextFieldSlovaKeyReleased

    private void jTextFieldBrojeviKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBrojeviKeyReleased

        jTextFieldGradiliste.setText(jComboBox1.getSelectedItem().toString() +jTextFieldBrojevi.getText() + jTextFieldSlova.getText());
    }//GEN-LAST:event_jTextFieldBrojeviKeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        jTextFieldGradiliste.setText(jComboBox1.getSelectedItem().toString() +jTextFieldBrojevi.getText() + jTextFieldSlova.getText());
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableTabliceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTabliceMouseClicked
        jLabel12.setText(jTableTablice.getValueAt(jTableTablice.getSelectedRow(), 1).toString());
        regTabId = Integer.parseInt(jTableTablice.getValueAt(jTableTablice.getSelectedRow(), 2).toString());
        
    }//GEN-LAST:event_jTableTabliceMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            if(vozacId>0 && regTabId>0) {
                vozacKamion = new VozackamionService();
                vozacKamion.addNewConnectionDriverTruck(vozacDb.getDriver(vozacId), regTablicaDb.getTruck(regTabId));
            }else{
                JOptionPane.showMessageDialog(rootPane, "Selektujte u tabeli vozaca i vozilo");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "Doslo je do greske u komunikaciji sa bazom");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTableVozacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableVozacMouseClicked
        jLabel11.setText(jTableVozac.getValueAt(jTableVozac.getSelectedRow(), 1).toString());
        vozacId = Integer.parseInt(jTableVozac.getValueAt(jTableVozac.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_jTableVozacMouseClicked

    
    private void readTablice() {

        List<Db.Regtablice> result = regTablicaDb.getRegTablice();
        Object[][] obj = new Object[result.size()][3];

        int i = 0;

        for (Db.Regtablice auto : result) {
            obj[i][0] = i + 1;
            obj[i][1] = auto.getTablice();
            obj[i][2] = auto.getIdtablice();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(obj, new Object[]{"r.b.", "Reg tablice", ""});
        jTableTablice.setModel(model);
        jTableTablice.setRowMargin(4);
        jTableTablice.setRowHeight(40);
        jTableTablice.getColumnModel().getColumn(0).setMaxWidth(45);
        jTableTablice.getColumnModel().getColumn(2).setMaxWidth(0);
        jTableTablice.getColumnModel().getColumn(2).setWidth(0);
        jTableTablice.getColumnModel().getColumn(2).setMaxWidth(0); 
              

    }
    
    private void addFilterTablice() {
        jTextFieldGradiliste.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTextFieldGradiliste.getText();

                if (text.trim().length() == 0) {
                    rowSorterTruck.setRowFilter(null);
                } else {
                    rowSorterTruck.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTextFieldGradiliste.getText();

                if (text.trim().length() == 0) {
                    rowSorterTruck.setRowFilter(null);
                } else {
                    rowSorterTruck.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }
    
     
    private void readVozac() {
        List<Db.Vozaci> result = vozacDb.getVozaci();
        Object[][] obj = new Object[result.size()][3];

        int i = 0;

        for (Db.Vozaci auto : result) {
            obj[i][0] = i + 1;
            obj[i][1] = auto.getImeprezimevozaci();
            obj[i][2] = auto.getIdvozaci();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(obj, new Object[]{"r.b.", "Ime i prezime", ""});
        jTableVozac.setModel(model);
        jTableVozac.setRowMargin(4);
        jTableVozac.setRowHeight(40);
        jTableVozac.getColumnModel().getColumn(0).setMaxWidth(45);
        jTableVozac.getColumnModel().getColumn(2).setMaxWidth(0);
        jTableVozac.getColumnModel().getColumn(2).setWidth(0);
        jTableVozac.getColumnModel().getColumn(2).setMaxWidth(0); 

    }

    private void addFilterVozac() {
        jTextFieldVozac.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTextFieldVozac.getText();

                if (text.trim().length() == 0) {
                    rowSorterDriver.setRowFilter(null);
                } else {
                    rowSorterDriver.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTextFieldVozac.getText();

                if (text.trim().length() == 0) {
                    rowSorterDriver.setRowFilter(null);
                } else {
                    rowSorterDriver.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    } 
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableTablice;
    private javax.swing.JTable jTableVozac;
    private javax.swing.JTextField jTextFieldBrojevi;
    private javax.swing.JTextField jTextFieldSlova;
    private javax.swing.JTextField jTextFieldVozac;
    // End of variables declaration//GEN-END:variables
    private int regTabId;
    private RegtabliceService regTablicaDb;
    private TableRowSorter<TableModel> rowSorterTruck;
    private final JTextField jTextFieldGradiliste;
    private final JLabel result;
    
    private int vozacId;
    private VozacService vozacDb;
    private TableRowSorter<TableModel> rowSorterDriver;
    
    VozackamionService vozacKamion;
    
}
