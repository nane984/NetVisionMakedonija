package tools;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.print.*;
import java.text.MessageFormat;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
/**
 *
 * @author branko.scekic
 */
public class TablePrint extends JFrame{
    
    /**
     * Overridden to return a subclass of JTable with a custom Printable
     * implementation.
     */
    public void createTable(TableModel model) {
        //System.out.println(model.getValueAt(1, 1));
        //return new FancyPrintingJTable(model);
         printGradesTable(new FancyPrintingJTable(model));
         //this.setVisible(true);
    }
    
    private void printGradesTable(JTable jtable) {
        /* Fetch printing properties from the GUI components */

        //MessageFormat header = null;
        
        /* if we should print a header */
        //if (headerBox.isSelected()) {
            /* create a MessageFormat around the header text */
            //header = new MessageFormat(headerField.getText());
        //}

        //MessageFormat footer = null;
        
        /* if we should print a footer */
        //if (footerBox.isSelected()) {
            /* create a MessageFormat around the footer text */
            //footer = new MessageFormat(footerField.getText());
        //}

        //boolean fitWidth = fitWidthBox.isSelected();
        //boolean showPrintDialog = showPrintDialogBox.isSelected();
        //boolean interactive = interactiveBox.isSelected();

        /* determine the print mode */
        //JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH
        //                                 : JTable.PrintMode.NORMAL;

        try {
            /* print the table */
            //boolean complete = jtable.print(mode, header, footer,
            //                                     showPrintDialog, null,
            //                                     interactive, null);
            boolean complete = jtable.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat(""),new MessageFormat("Page {0}") ,
                                                 true, null,
                                                 true, null);

            /* if printing completes */
            if (complete) {
                /* show a success message */
                JOptionPane.showMessageDialog(this,
                                              "Printing Complete",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            } else {
                /* show a message indicating that printing was cancelled */
                JOptionPane.showMessageDialog(this,
                                              "Printing Cancelled",
                                              "Printing Result",
                                              JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (PrinterException pe) {
            /* Printing failed, report to the user */
            JOptionPane.showMessageDialog(this,
                                          "Printing Failed: " + pe.getMessage(),
                                          "Printing Result",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
    

    /**
     * Subclass of JTable that returns a fancy printable implementation.
     */
    private static class FancyPrintingJTable extends JTable {
        
        public FancyPrintingJTable(TableModel model) {
            super(model);
        }

        /**
         * Overridden to return a fancier printable, that wraps the default.
         * Ignores the given header and footer. Renders its own header.
         * Always uses the page number as the footer.
         */
        public Printable getPrintable(PrintMode printMode,
                                      MessageFormat headerFormat,
                                      MessageFormat footerFormat) {

            MessageFormat pageNumber = new MessageFormat("- {0} -");

            /* Fetch the default printable */
            Printable delegate = super.getPrintable(printMode, null, pageNumber);

            /* Return a fancy printable that wraps the default */
            return new FancyPrintable(delegate);
        }

    }

    /**
     * A custom Printable implementation that wraps another printable and
     * decorates the output by placing the table inside an image of a clipboard.
     */
    private static class FancyPrintable implements Printable {

        /* The Printable to wrap */
        private Printable delegate;

        /* Images used to assemble a clipboard image around the painted table */
        //private BufferedImage clipTopLeft;
        private BufferedImage clipTop;
        private BufferedImage clipTopCenter;
        //private BufferedImage clipTopRight;
        private BufferedImage clipBottomLeft;
        private BufferedImage clipBottom;
        private BufferedImage clipBottomRight;
        //private BufferedImage clipLeft;
        //private BufferedImage clipRight;
        
        /* Image with text saying "Final Grades..." */
        private BufferedImage finalGrades;

        /* Whether or not the images loaded successfully */
        boolean imagesLoaded;

        /* Load the images */
        {
            try {
                //clipTopLeft = ImageIO.read(getClass().getResource("images/clipTopLeft.png"));
                clipTop = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/podaci.png"));
                clipTopCenter = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/zaglavlje.jpg"));
                //clipTopRight = ImageIO.read(getClass().getResource("images/clipTopRight.png"));

                //clipBottomLeft = ImageIO.read(getClass().getResource("images/clipBottomLeft.png"));
                clipBottom = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/podaci.png"));
                //clipBottomRight = ImageIO.read(getClass().getResource("images/clipBottomRight.png"));
                
                //clipLeft = ImageIO.read(getClass().getResource("images/clipLeft.png"));
                //clipRight = ImageIO.read(getClass().getResource("images/clipRight.png"));

                finalGrades = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tools/podaci.png"));

                imagesLoaded = true;
            } catch (IOException ioe) {
                // can't load the image, so no clipboard
                imagesLoaded = false;
            }
        }

        /**
         * Constructs a FancyPrintable to wrap the given Printable.
         */
        public FancyPrintable(Printable delegate) {
            this.delegate = delegate;
        }

        /**
         * Prints the delegate Printable, wrapped inside an image of a clipboard.
         * Gives the wrapped printable a smaller area to print into (which substracts
         * the area needed to render the clipboard image), and then prints the
         * clipboard image around the outside.
         */
        public int print(Graphics g,
                         final PageFormat pf,
                         int pageIndex) throws PrinterException {

            /*
             * If we weren't able to load the images, we have nothing to wrap with,
             * so just have the wrapped Printable do its thing, and return.
             */
            if (!imagesLoaded) {
                return delegate.print(g, pf, pageIndex);
            }

            /*
             * Note: Since this is just a demo, we assume that there's enough room
             * on the page to render the clipboard image and the table. A more robust
             * application should check first, and render only the table if there's
             * not enough room.
             */

            /* top left of the imageable area */
            int ix = (int)pf.getImageableX();
            int iy = (int)pf.getImageableY();

            /* width and height of the imageable area */
            int iw = (int)pf.getImageableWidth();
            int ih = (int)pf.getImageableHeight();

            /* width of the clipboard image pieces to be painted on the left */
            //int leftWidth = clipLeft.getWidth();
            
            /* width of the clipboard image pieces to be painted on the right */
            //int rightWidth = clipRight.getWidth();

            /* height of the clipboard image pieces to be painted on the top */
            int topHeight = clipTop.getHeight();

            /* height of the clipboard image pieces to be painted on the bottom */
            int bottomHeight = clipBottom.getHeight();
            
            /* height of the final grades label */
            int finalGradesHeight = finalGrades.getHeight();


            /*
             * First, calculate the shrunken area that we want the table to print
             * into.
             */


            /* inset the table from the left and right images by 10 */
            //final int tableX = ix + leftWidth + 10;
            //final int tableW = iw - (leftWidth + 10) - (rightWidth + 10);
            final int tableX = ix + 10;
            final int tableW = iw - 10 - 10;

            /*
             * inset the table top to leave space for the top image +
             * 10 pixels + the final grades label + 10 pixels.
             */
            final int tableY = iy + topHeight + 10 + finalGradesHeight + 10;

            /* inset the table bottom by the height of the bottom image */
            final int tableH = ih - (topHeight + 10) - (finalGradesHeight + 10) - bottomHeight;


            /*
             * Now print the table into this smaller area.
             */


            /* create a new page format representing the shrunken area to print the table into */
            PageFormat format = new PageFormat() {
                public double getImageableX() {return tableX;}
                public double getImageableY() {return tableY;}
                public double getImageableWidth() {return tableW;}
                public double getImageableHeight() {return tableH;}
            };

            /*
             * We'll use a copy of the graphics to print the table to. This protects
             * us against changes that the delegate printable could make to the graphics
             * object.
             */
            Graphics gCopy = g.create();

            /* print the table into the shrunken area */
            int retVal = delegate.print(gCopy, format, pageIndex);

            /* if there's no pages left, return */
            if (retVal == NO_SUCH_PAGE) {
                return retVal;
            }

            /* dispose of the graphics copy */
            gCopy.dispose();


            /*
             * Now that we've printed the table, assemble the clipboard image around
             * the outside.
             */


            //int leftEnd = ix + leftWidth;
            int leftEnd = ix;
            int clipTopCenterStart = ix + (int)((iw - clipTopCenter.getWidth()) / 2.0f);
            int clipTopCenterEnd = clipTopCenterStart + clipTopCenter.getWidth();
            //int rightStart = ix + iw - rightWidth;
            int rightStart = ix + iw;
            
            /* draw top left corner */
            //g.drawImage(clipTopLeft, ix, iy, null);
            
            /* stretch top from top left corner to center image */
            g.drawImage(clipTop, leftEnd, iy, clipTopCenterStart - leftEnd, topHeight, null);

            /* stretch top from center image to top right corner */
            g.drawImage(clipTop, clipTopCenterEnd, iy, rightStart - clipTopCenterEnd, topHeight, null);

            /* draw top right corner */
            //g.drawImage(clipTopRight, rightStart, iy, null);

            /* draw top center */
            g.drawImage(clipTopCenter, clipTopCenterStart, iy, null);

            int finalGradesStart = ix + (int)((iw - finalGrades.getWidth()) / 2.0f);
            
            /* draw label */
            g.drawImage(finalGrades, finalGradesStart, iy + topHeight + 10, null);

            int bottomY = iy + ih - bottomHeight;

            /* draw bottom left corner */
            g.drawImage(clipBottomLeft, ix, bottomY, null);
            
            /* draw bottom right corner */
            g.drawImage(clipBottomRight, rightStart, bottomY, null);
            
            /* stretch the bottom image from left to right */
            g.drawImage(clipBottom, leftEnd, bottomY, rightStart - leftEnd, bottomHeight, null);
            
            /* stretch left side  from top to bottom */
            //g.drawImage(clipLeft, ix, iy + topHeight, leftWidth, bottomY - iy - topHeight, null);
            
            
            /* stretch right side from top to bottom */
            //g.drawImage(clipRight, rightStart, iy + topHeight, rightWidth, bottomY - iy - topHeight, null);

            return PAGE_EXISTS;
        }
    }
}
