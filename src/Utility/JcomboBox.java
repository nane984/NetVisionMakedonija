/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author brasa
 */
public class JcomboBox extends JComboBox {

    private List<String> array;
    public boolean isSelected = false;

    public JcomboBox(){
        Dimension preferredSize = new Dimension();
        preferredSize.setSize(250, 25);
        this.setPreferredSize(preferredSize);
    }
    
    public void loadBoxWithX(List<String> array) {
        //super(array.toArray());
        this.array = array;
        this.array.add(0, "");
        this.setEditable(false);
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilter(textfield.getText());
                    }
                });
            }
        });
        this.setModel(new DefaultComboBoxModel(array.toArray()));
        //textfield.setText("");
       
    }

    public void loadBox(List<String> array) {
        //super(array.toArray());
        this.array = array;
        this.array.add(0, "");
        this.setEditable(false);
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilter(textfield.getText());
                    }
                });
            }
        });
        this.setModel(new DefaultComboBoxModel(array.toArray()));
        //textfield.setText("");
    }

    public void loadBoxMousEvent(List<String> array) {
        //super(array.toArray());
        this.array = array;
        this.array.add(0, "");
        this.setEditable(false);
        final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
        textfield.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        comboFilter(textfield.getText());

                    }
                });
            }
        });
        this.setModel(new DefaultComboBoxModel(array.toArray()));
        //textfield.setText("");
    }

    public void comboFilter(String enteredText) {
        List<String> filterArray = new ArrayList<String>();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
                filterArray.add(array.get(i));
            }
        }
        if (filterArray.size() > 0) {
            this.setModel(new DefaultComboBoxModel(filterArray.toArray()));
            this.setSelectedItem(enteredText);
            this.showPopup();
        } else {
            this.hidePopup();
        }
    }

   
    
    
    /*
    /// Testing Codes 
    public static List<String> populateArray() {
        List<String> test = new ArrayList<String>();
        test.add("");
        test.add("Mountain Flight");
        test.add("Mount Climbing");
        test.add("Trekking");
        test.add("Rafting");
        test.add("Jungle Safari");
        test.add("Bungie Jumping");
        test.add("Para Gliding");
        return test;
    }
     */
}
