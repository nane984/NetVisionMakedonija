package tools;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import java.awt.print.*;
import java.text.MessageFormat;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author branko.scekic
 */
public class PrintTool extends JFrame {

    

    /* UI Components */
    private JPanel contentPane;
    private JLabel gradesLabel;
    private JTable gradesTable;
    private JScrollPane scroll;
    private JCheckBox showPrintDialogBox;
    private JCheckBox interactiveBox;
    private JCheckBox fitWidthBox;
    private JButton printButton;
    private BufferedImage bi;

    /* Protected so that they can be modified/disabled by subclasses */
    protected JCheckBox headerBox;
    protected JCheckBox footerBox;
    protected JTextField headerField;
    protected JTextField footerField;

    /**
     * Constructs an instance of the demo.
     */
    public PrintTool(JTable table, BufferedImage bi) {
        super("Table Printing");

        gradesLabel = new JLabel("Final Grades - CSC 101");
        //gradesLabel.setFont(new Font("Dialog", Font.BOLD, 16));

        gradesTable = createTable(table.getModel());
        gradesTable.setFillsViewportHeight(true);
        gradesTable.setRowHeight(24);

        /* Set a custom renderer on the "Passed" column */
       // gradesTable.getColumn("Passed").setCellRenderer(createPassedColumnRenderer());

        scroll = new JScrollPane(gradesTable);

        String tooltipText;

        tooltipText = "Include a page header";
        headerBox = new JCheckBox("Header:", true);
        headerBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                headerField.setEnabled(headerBox.isSelected());
            }
        });
        headerBox.setToolTipText(tooltipText);
        tooltipText = "Page Header (Use {0} to include page number)";
        headerField = new JTextField("Final Grades - CSC 101");
        headerField.setToolTipText(tooltipText);

        tooltipText = "Include a page footer";
        footerBox = new JCheckBox("Footer:", true);
        footerBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                footerField.setEnabled(footerBox.isSelected());
            }
        });
        footerBox.setToolTipText(tooltipText);
        tooltipText = "Page Footer (Use {0} to Include Page Number)";
        footerField = new JTextField("Page {0}");
        footerField.setToolTipText(tooltipText);

        tooltipText = "Show the Print Dialog Before Printing";
        showPrintDialogBox = new JCheckBox("Show print dialog", true);
        showPrintDialogBox.setToolTipText(tooltipText);
        showPrintDialogBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!showPrintDialogBox.isSelected()) {
                    JOptionPane.showMessageDialog(
                        PrintTool.this,
                        "If the Print Dialog is not shown,"
                            + " the default printer is used.",
                        "Printing Message",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        
        tooltipText = "Keep the GUI Responsive and Show a Status Dialog During Printing";
        interactiveBox = new JCheckBox("Interactive (Show status dialog)", true);
        interactiveBox.setToolTipText(tooltipText);
        interactiveBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (!interactiveBox.isSelected()) {
                    JOptionPane.showMessageDialog(
                        PrintTool.this,
                        "If non-interactive, the GUI is fully blocked"
                            + " during printing.",
                        "Printing Message",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        tooltipText = "Shrink the Table to Fit the Entire Width on a Page";
        fitWidthBox = new JCheckBox("Fit width to printed page", true);
        fitWidthBox.setToolTipText(tooltipText);

        tooltipText = "Print the Table";
        printButton = new JButton("Print");
        printButton.setToolTipText(tooltipText);
        
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                printGradesTable();
            }
        });

        contentPane = new JPanel();
        addComponentsToContentPane();
        setContentPane(contentPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);
        
        
        /* hide these fields - our printable will render its own header/footer */
        //String tooltipText = "Disabled - This Demo Prints Custom Header/Footers";
        headerBox.setEnabled(false);
        headerBox.setSelected(false);
        headerBox.setToolTipText(tooltipText);
        headerField.setEnabled(false);
        headerField.setText("<Custom>");
        headerField.setToolTipText(tooltipText);
        footerBox.setEnabled(false);
        footerBox.setSelected(false);
        footerBox.setToolTipText(tooltipText);
        footerField.setEnabled(false);
        footerField.setText("<Custom>");
        footerField.setToolTipText(tooltipText);
        
        this.bi=bi;
        
        
    }

    /**
     * Adds to and lays out all GUI components on the {@code contentPane} panel.
     * <p>
     * It is recommended that you <b>NOT</b> try to understand this code. It was
     * automatically generated by the NetBeans GUI builder.
     * 
     */
    private void addComponentsToContentPane() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createTitledBorder("Printing"));

        GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(headerBox)
                    .addComponent(footerBox))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(footerField)
                    .addComponent(headerField, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addComponent(fitWidthBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(printButton))
                    .addGroup(bottomPanelLayout.createSequentialGroup()
                        .addComponent(showPrintDialogBox)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(interactiveBox)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(headerBox)
                    .addComponent(headerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(interactiveBox)
                    .addComponent(showPrintDialogBox))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bottomPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(footerBox)
                    .addComponent(footerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fitWidthBox)
                    .addComponent(printButton))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(contentPane);
        contentPane.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addComponent(gradesLabel)
                    .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gradesLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bottomPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }

    /**
     * Create and return a table for the given model.
     * <p>
     * This is protected so that a subclass can return an instance
     * of a different {@code JTable} subclass. This is interesting
     * only for {@code TablePrintDemo3} where we want to return a
     * subclass that overrides {@code getPrintable} to return a
     * custom {@code Printable} implementation.
     */
    protected JTable createTable(TableModel model) {
        return new FancyPrintingJTable(model);
    }

    /**
     * Create and return a cell renderer for rendering the pass/fail column.
     * This is protected so that a subclass can further customize it.
     */
    //protected TableCellRenderer createPassedColumnRenderer() {
    //    return new PassedColumnRenderer();
    //}

    /**
     * Print the grades table.
     */
    public  void printGradesTable() {
        /* Fetch printing properties from the GUI components */

        MessageFormat header = null;
        
        /* if we should print a header */
        if (headerBox.isSelected()) {
            /* create a MessageFormat around the header text */
            header = new MessageFormat(headerField.getText());
        }

        MessageFormat footer = null;
        
        /* if we should print a footer */
        if (footerBox.isSelected()) {
            /* create a MessageFormat around the footer text */
            footer = new MessageFormat(footerField.getText());
        }

        boolean fitWidth = fitWidthBox.isSelected();
        boolean showPrintDialog = showPrintDialogBox.isSelected();
        boolean interactive = interactiveBox.isSelected();

        /* determine the print mode */
        JTable.PrintMode mode = fitWidth ? JTable.PrintMode.FIT_WIDTH
                                         : JTable.PrintMode.NORMAL;
        
        PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
        set.add(new MediaPrintableArea(2, 2, 210 - 4, 297 - 4, MediaPrintableArea.MM));

        try {
            /* print the table */
            boolean complete = gradesTable.print(mode, header, footer, showPrintDialog, set,interactive, null);

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
    private class FancyPrintingJTable extends JTable {
        
        public FancyPrintingJTable(TableModel model) {
            super(model);
        }

        /**
         * Overridden to return a fancier printable, that wraps the default.
         * Ignores the given header and footer. Renders its own header.
         * Always uses the page number as the footer.
         */
        @Override
        public Printable getPrintable(JTable.PrintMode printMode,
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
    private class FancyPrintable implements Printable {

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
                clipTop = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/podaci.png"));
                clipTopCenter = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/zaglavlje.jpg"));
                //clipTopRight = ImageIO.read(getClass().getResource("images/clipTopRight.png"));

                //clipBottomLeft = ImageIO.read(getClass().getResource("images/clipBottomLeft.png"));
                clipBottom = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/podaci.png"));
                //clipBottomRight = ImageIO.read(getClass().getResource("images/clipBottomRight.png"));
                
                //clipLeft = ImageIO.read(getClass().getResource("images/clipLeft.png"));
                //clipRight = ImageIO.read(getClass().getResource("images/clipRight.png"));

                finalGrades = ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/podaci.png"));

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
            final int tableW = iw - 10;

            /*
             * inset the table top to leave space for the top image +
             * 10 pixels + the final grades label + 10 pixels.
             */
            final int tableY = iy + topHeight + 5 + finalGradesHeight + 5;

            /* inset the table bottom by the height of the bottom image */
            final int tableH = ih - (topHeight + 5) - (finalGradesHeight + 5) - bottomHeight;


            /*
             * Now print the table into this smaller area.
             */


            /* create a new page format representing the shrunken area to print the table into */
            PageFormat format = new PageFormat() {
                @Override
                public double getImageableX() {return tableX;}
                @Override
                public double getImageableY() {return tableY;}
                @Override
                public double getImageableWidth() {return tableW;}
                @Override
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