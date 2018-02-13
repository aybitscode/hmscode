package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.hms.model.ReportDetails;
import com.hms.util.BigDecimalType;
import com.hms.util.DBConnection;
import com.hms.view.BookingCheckout;
import com.hms.view.BookingDetails;

public class ReportDetailService {
	Connection con = DBConnection.getDBConnection();
	ReportDetails obj_rpt;
	public ReportDetailService(ReportDetails obj_rpt)
	{
		this.obj_rpt = obj_rpt;
	}
	public void submitService()
	{
			double booking_cost = 0;
			booking_cost = (Double.parseDouble(obj_rpt.getRoomCost())+Double.parseDouble(obj_rpt.getExtraBedCharges())+Double.parseDouble(obj_rpt.getFacilitiesCost())) * Integer.parseInt(obj_rpt.getDays()) ;
			int booking_cgst_per = (int) ((Double.parseDouble(obj_rpt.getBookingCGST())*100)/booking_cost);
			int booking_sgst_per = (int) ((Double.parseDouble(obj_rpt.getBookingSGST())*100)/booking_cost);
			double booking_gst = Double.parseDouble(obj_rpt.getBookingCGST())+Double.parseDouble(obj_rpt.getBookingSGST());
			int booking_gst_per = booking_cgst_per + booking_sgst_per;

		
		try{
			int i = 1;
			PreparedStatement pstDel = con.prepareStatement("delete from reports");
			int s1 = pstDel.executeUpdate();
			con.setAutoCommit(false);				
			PreparedStatement pstBatch = con.prepareStatement("insert into reports(slno, particulars, amount) "+ "values(?,?,?)");
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Room Rent");
			pstBatch.setString(3, obj_rpt.getRoomCost());
			pstBatch.addBatch();

			if(BookingCheckout.extra_person!=0)
			{
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Extra Person");
			pstBatch.setString(3,  ""+BigDecimalType.roundDown(BookingCheckout.extra_person));
			pstBatch.addBatch();
			}
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Room Facilities Cost");
			pstBatch.setString(3,obj_rpt.getFacilitiesCost());
			pstBatch.addBatch();
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "No. of Days");
			pstBatch.setString(3, obj_rpt.getDays());
			pstBatch.addBatch();

			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "CGST @ "+booking_cgst_per+"%");
			pstBatch.setString(3, obj_rpt.getBookingCGST());
			pstBatch.addBatch();
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "SGST @ "+booking_sgst_per+"%");
			pstBatch.setString(3, obj_rpt.getBookingSGST());
			pstBatch.addBatch();
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Total GST @ "+booking_gst_per+"%");
			pstBatch.setString(3, ""+BigDecimalType.roundDown(booking_gst));
			pstBatch.addBatch();
			
			if(BookingDetails.total_service_cost > 0)
			{
				PreparedStatement pst_ms = con.prepareStatement("select serviceName, serviceDesc, servicePrice from miscellaneous where bookingID = ?");
				pst_ms.setString(1, obj_rpt.getBookingID());
				ResultSet rs_ms = pst_ms.executeQuery();
				while(rs_ms.next())
				{
					pstBatch.setInt(1, i++);
					pstBatch.setString(2, rs_ms.getString(1)+" "+"("+rs_ms.getString(2)+")");
					pstBatch.setString(3, rs_ms.getString(3));
					pstBatch.addBatch();

					
				}
			
			int service_cgst_per = (int) ((Double.parseDouble(obj_rpt.getServiceCGST())*100)/BookingDetails.total_service_cost);	
			int service_sgst_per = (int) ((Double.parseDouble(obj_rpt.getServiceSGST())*100)/BookingDetails.total_service_cost);
			int service_gst_per = service_cgst_per + service_sgst_per; 
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Service TAX "+service_gst_per+"%"+" ("+ service_cgst_per +"% CGST + "+ service_sgst_per +"% SGST)");
			pstBatch.setString(3, ""+BigDecimalType.roundDown(BookingDetails.total_service_cost));
			pstBatch.addBatch();					
				
			}

			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Gross Amount");
			pstBatch.setString(3, obj_rpt.getGrossTotal());
			pstBatch.addBatch();
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Coupon Discount "+"("+obj_rpt.getCouponName()+")");
			pstBatch.setString(3, ""+BigDecimalType.roundDown(Double.parseDouble(obj_rpt.getDiscount())));
			pstBatch.addBatch();

			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Advance Amount");
			pstBatch.setString(3, obj_rpt.getAdvancePaid());
			pstBatch.addBatch();
			
			
			pstBatch.setInt(1, i++);
			pstBatch.setString(2, "Balance Amount");
			pstBatch.setString(3, obj_rpt.getBalance());   
			pstBatch.addBatch();
			
			pstBatch.executeBatch();
			con.commit();
			con.setAutoCommit(true);
			//JOptionPane.showMessageDialog(null, "Checkout done successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
			}


		

	

}
