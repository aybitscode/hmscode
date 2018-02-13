package com.reports;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

import com.hms.model.ReportDetails;
import com.hms.util.DBConnection;
import com.hms.view.BookingDetails;
import com.hotelmanagement.MainPage;

 
public class Report extends JDialog {
 
    HashMap<String, Object> hm = null;
    String reportName;
    int identity;
    Connection con = DBConnection.getDBConnection();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public Report(MainPage mpg) {
    	super(mpg, "Report Details", true);
        setSize(d.width, d.height);
        setLocation(0,0);
    //	super(bds,"Report Details",true);
        //setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
    }
    public Report(BookingDetails bds) {
    	super(bds,"Report Details",true);
    	//setAlwaysOnTop(false);
        setSize(d.width, d.height);
        setLocation(0,0);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
    }
 
    public Report(HashMap map) {
        this.hm = map;
        setAlwaysOnTop(true);
        //setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
    }
 
    public Report(HashMap map, Connection con, Integer identity) {
        this.hm = map;
        this.identity=identity;
        setAlwaysOnTop(true);
       // setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Report Viewer");
 
    }
 
    public void setReportName(String rptName) {
        this.reportName = rptName;
    }
 
    public void callReport(String bookingID, List<String> custList) {
        JasperPrint jasperPrint = generateReport(bookingID, custList);
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }
    public void callReport(String bookingID, ReportDetails rpt) {
        JasperPrint jasperPrint = generateReport(bookingID, rpt);
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }
 
    public void callConnectionLessReport() {
        JasperPrint jasperPrint = generateEmptyDataSourceReport();
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }
 
    public void closeReport() {
        //jasperViewer.setVisible(false);
    }
 
    /** this method will call the report from data source*/
    public JasperPrint generateReport(String bookingID, List<String> custList) {
            System.out.println("booking id from reports is"+bookingID);
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
            }
    

            try {
                /**You can also test this line if you want to display 
                 * report from any absolute path other than the project root path*/
                //jasperPrint = JasperFillManager.fillReport("F:/testreport/"+reportName+".jasper",hm, con);
            	//hm.put(“REPORT_TITLE”,”This is the title of the report”);        
                //jasperPrint = JasperFillManager.fillReport("C:/oraexe/accdata/db/admin/dbase/Invoice_Table_Based.jasper", hm, con);
            	hm.put("bookingID",bookingID);
            	hm.put("customer_name", custList.get(0));
            	hm.put("customer_mobile", custList.get(1));
            	hm.put("customer_address", custList.get(2));
            	hm.put("gst_number", "SGSG9LK9");
                jasperPrint = JasperFillManager.fillReport("C:/HotelManagement/oraexe/accdata/db/admin/dbase/Invoice.jasper", hm, con);
            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
      
 
 
    }
    
    public JasperPrint generateReport(String bookingID, ReportDetails rpt) {
        System.out.println("booking id from reports is"+bookingID);
        JasperPrint jasperPrint = null;
        if (hm == null) {
            hm = new HashMap();
        }


        try {
            /**You can also test this line if you want to display 
             * report from any absolute path other than the project root path*/
            //jasperPrint = JasperFillManager.fillReport("F:/testreport/"+reportName+".jasper",hm, con);
        	//hm.put(“REPORT_TITLE”,”This is the title of the report”);        
            //jasperPrint = JasperFillManager.fillReport("C:/oraexe/accdata/db/admin/dbase/Invoice_Table_Based.jasper", hm, con);
        	hm.put("bookingID",bookingID);
        	hm.put("customer_name", rpt.getCustomerName());
        	hm.put("customer_mobile", rpt.getMobileNumber());
        	hm.put("customer_address", rpt.getAddress());
        	hm.put("gst_number", "SGSG9LK9");
            jasperPrint = JasperFillManager.fillReport("C:/HotelManagement/oraexe/accdata/db/admin/dbase/Invoice.jasper", hm, con);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return jasperPrint;
  


}
 
    /** call this method when your report has an empty data source*/
    public JasperPrint generateEmptyDataSourceReport() {
        try {
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
            }
            try {
                jasperPrint = JasperFillManager.fillReport(reportName + ".jasper", hm, new JREmptyDataSource());
            } catch (JRException e) {
                e.printStackTrace();
            }
            return jasperPrint;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
 
    }
}