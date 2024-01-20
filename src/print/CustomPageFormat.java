/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package print;

import java.awt.print.PageFormat;

/**
 *
 * @author brasa
 */
public class CustomPageFormat extends PageFormat {

    private PageFormat delegate;
    private double headerHeight;
    private double footerHeight;

    public CustomPageFormat(PageFormat format, double headerHeight, double footerHeight) {
        this.delegate = format;
        this.headerHeight = headerHeight;
        this.footerHeight = footerHeight;
    }
    /** 
     * @inherited <p>
     */
    @Override
    public double getImageableY() {
        return delegate.getImageableY() + headerHeight;
    }

    /** 
     * @inherited <p>
     */
    @Override
    public double getImageableHeight() {
        return delegate.getImageableHeight() - headerHeight - footerHeight;
    }
}
