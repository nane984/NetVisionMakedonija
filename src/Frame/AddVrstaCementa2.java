/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;


import DbService.VrstaCementaService2;
import static Frame.MainFrame.osveziBazu;
import java.net.URL;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author branko.scekic
 */
public class AddVrstaCementa2 extends javax.swing.JFrame {

    private TableRowSorter<TableModel> rowSorter;
    private final VrstaCementaService2 vrstaCemDb;

    /**
     * Creates new form AddVrstaCementa
     *
     * @param vrstaCemDb
     */
    public AddVrstaCementa2(VrstaCementaService2 vrstaCemDb) {
        initComponents();
        URL iconURL = getClass().getResource("/image/truckBar.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());
        this.vrstaCemDb = vrstaCemDb;
        readVrstaCementa();
        rowSorter = new TableRowSorter<>(jTable1.getModel());
        jTable1.setRowSorter(rowSorter);
        addFilterFradiliste();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldGradiliste = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(221, 511, 0, 0);

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Vrsta cementa:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 70, 180, 30);
        getContentPane().add(jTextFieldGradiliste);
        jTextFieldGradiliste.setBounds(190, 70, 240, 40);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTable1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "R.br.", "Vrsta cem"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(51, 51, 255));
        jTable1.setOpaque(false);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 140, 420, 250);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Exit.png"))); // NOI18N
        jButton1.setText("Izlaz");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(320, 430, 110, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Add.png"))); // NOI18N
        jButton2.setText("Dodaj");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(0, 430, 89, 33);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vrsteCementa.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 6, 443, 499);

        setSize(new java.awt.Dimension(443, 512));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        int dialogButton = JOptionPane.showConfirmDialog(null, "Da li zelite da dodate cement " + jTextFieldGradiliste.getText() + " ?");

        if (dialogButton == JOptionPane.YES_OPTION) { //The ISSUE is here
            if (!jTextFieldGradiliste.getText().isEmpty()) {
                vrstaCemDb.addNewVrstaCementa(jTextFieldGradiliste.getText());
                jTextFieldGradiliste.setText("");

                readVrstaCementa();
                rowSorter = new TableRowSorter<>(jTable1.getModel());
                jTable1.setRowSorter(rowSorter);
                addFilterFradiliste();

                MainFrame.osveziBazu = true;
                Baza.osveziPrikaz = true;
            } else {
                JOptionPane.showMessageDialog(rootPane, "Ne moze biti prazno polje");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldGradiliste;
    // End of variables declaration//GEN-END:variables

    private void readVrstaCementa() {
        List<Db.Vrstacementa2> result = vrstaCemDb.getVrstaCementa();
        Object[][] obj = new Object[result.size()][2];

        int i = 0;

        for (Db.Vrstacementa2 auto : result) {
            obj[i][0] = i + 1;
            obj[i][1] = auto.getVrstacem();

            i++;
        }

        DefaultTableModel model = new DefaultTableModel(obj, new Object[]{"r.b.", "vrsta cementa2"});
        jTable1.setModel(model);
        jTable1.setRowMargin(2);
        jTable1.setRowHeight(40);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(45);
    }

    private void addFilterFradiliste() {
        jTextFieldGradiliste.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jTextFieldGradiliste.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jTextFieldGradiliste.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }

    private void readVrstaCem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
