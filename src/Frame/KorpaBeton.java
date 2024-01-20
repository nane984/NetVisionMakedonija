/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author brasa
 */
public class KorpaBeton extends javax.swing.JPanel {

    int x = 0;
    int y = 0;
    BufferedImage image;
    BufferedImage image2;
    BufferedImage image3;
    BufferedImage image4;
    
    
    public KorpaBeton() {
        initComponents();
        
         try {
            image = ImageIO.read(this.getClass().getResource("/image/kasika.png"));
            image2 = ImageIO.read(this.getClass().getResource("/image/trakaZaBeton.png"));
            image3 = ImageIO.read(this.getClass().getResource("/image/VagaCentralna - Copy.png"));
            image4 = ImageIO.read(this.getClass().getResource("/image/pipe2V.jpg"));
           
        } catch (IOException ex) {
            Logger.getLogger(KorpaBeton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        

        //g2d.rotate(Math.toRadians(75));
        g2d.drawImage(image2, 0, 55, null);
        g2d.drawImage(image3, 245, 100, null);
        g2d.drawImage(image4, 0, -70, null);

        setBackground(new java.awt.Color(214, 217, 223));

        if (y < -82) {
                g2d.rotate(Math.toRadians(160));
                g2d.drawImage(image, -285, -87-183, null);
            }
            else if (y < -79) {
                g2d.rotate(Math.toRadians(160));
                g2d.drawImage(image, -285, -87 + y, null);
            }
            else if (y < -76) {
                g2d.rotate(Math.toRadians(150));
                g2d.drawImage(image, -250, -130 + y, null);
            }  
            else if (y < -74) {
                g2d.rotate(Math.toRadians(140));
                g2d.drawImage(image, -216, -165 + y, null);
            }  
            else if (y < -71) {
                g2d.rotate(Math.toRadians(130));
                g2d.drawImage(image, -177, -192 + y, null);
            }
            else if (y < -69) {
                g2d.rotate(Math.toRadians(120));
                g2d.drawImage(image, -131, -209 + y, null);
            }
            else if (y < -66) {
                g2d.rotate(Math.toRadians(110));
                g2d.drawImage(image, -83, -225 + y, null);
            }   
            else if (y < -63) {
                g2d.rotate(Math.toRadians(100));
                g2d.drawImage(image, -35, -225 + y, null);
            }      
            else if (y < -61) {
                g2d.rotate(Math.toRadians(90));
                g2d.drawImage(image, 10, -215 + y, null);  
            } else if (y < -58) {
                g2d.rotate(Math.toRadians(80));
                g2d.drawImage(image, 55, -200 + y, null);
            
            } else if (y < -55) {
                g2d.rotate(Math.toRadians(70));
                g2d.drawImage(image, 95, -180 + y, null);
            } else if (y > 185) {
                g2d.rotate(Math.toRadians(60));
                g2d.drawImage(image, 130, -154 + 185, null);
            } else {
                g2d.rotate(Math.toRadians(60));
                g2d.drawImage(image, 130, -154 + y, null);
            }
        //g2d.drawImage(image, 11, 15 + y, null);
    }

    // ovde definisemo velicinu
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 260);
    }
    
    public void moveKorpa(int i){
        int j = i; 
        int x = (int) Math.round(j*2.6);
        y= 180 - x;
        repaint();
    }
   

    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
