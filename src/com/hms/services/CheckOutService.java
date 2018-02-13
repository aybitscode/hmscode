package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.CheckOut;
import com.hms.model.ReportDetails;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.view.BookingCheckout;
import com.hms.view.CheckOutHistory;
import com.hms.view.MiscellaneousServices;
import com.hotelmanagement.MainPage;

public class CheckOutService {
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	ReportDetails obj_rpt = new ReportDetails();
	JTable table;
	Connection con = DBConnection.getDBConnection();
	double booking_cgst=0, booking_sgst=0, service_cgst=0, service_sgst=0;
	String booking_cgst_per=null, booking_sgst_per=null, booking_gst_per = null, service_cgst_per=null, service_sgst_per=null, service_gst_per = null;
	Map<String, String> hm;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	
	public CheckOutService()
	{
		
	}
	public CheckOutService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public CheckOutService(CheckOut obj_checkOut)
	{
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_CHECK_OUT);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public CheckOutService(ReportDetails obj_rpt)
	{
		this.obj_rpt = obj_rpt;
	}
	
	public String generateCheckOutId(){

		String prefix = "CHO";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_CHECK_OUT);
	        	resultSet.next();
	        	count = resultSet.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
        	 count += 1;
        	 
        	 serialNo = String.valueOf(count);
        	
            serialNo = String.format("%3s", serialNo).replace(' ', '0');
		
		return prefix+serialNo;
	}
	public String generateIWTID(){

		String prefix = Constants.PREFIX_TAX_LESS;
        String serialNo = "0";

        	 int count = 0;
        	 
        	
        	ResultSet resultSet = null;
			try {
				PreparedStatement pst = con.prepareStatement(DatabaseConstants.COUNT_INVOICE_TAXL);
	        	resultSet = pst.executeQuery();
	        	resultSet.next();
	        	count = resultSet.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
        	 count += 1;
        	 
        	 serialNo = String.valueOf(count);
        	
            serialNo = String.format("%3s", serialNo).replace(' ', '0');
		
		return prefix+serialNo;
	}
	
	public String generateCardInvoiceID(){

		String prefix = Constants.PREFIX_CARD;
        String serialNo = "0";

        	 int count = 0;
        	 
        	
        	ResultSet resultSet = null;
			try {
				PreparedStatement pst = con.prepareStatement(DatabaseConstants.COUNT_INVOICE_CARD);
	        	resultSet = pst.executeQuery();
	        	resultSet.next();
	        	count = resultSet.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
        	 count += 1;
        	 
        	 serialNo = String.valueOf(count);
        	
            serialNo = String.format("%3s", serialNo).replace(' ', '0');
		
		return prefix+serialNo;
	}
	
	public String generateCashInvoiceID(){

		String prefix = Constants.PREFIX_CASH;
        String serialNo = "0";

        	 int count = 0;
        	 
        	
        	ResultSet resultSet = null;
			try {
				PreparedStatement pst = con.prepareStatement(DatabaseConstants.COUNT_INVOICE_CASH);
	        	resultSet = pst.executeQuery();
	        	resultSet.next();
	        	count = resultSet.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 
        	 count += 1;
        	 
        	 serialNo = String.valueOf(count);
        	
            serialNo = String.format("%3s", serialNo).replace(' ', '0');
		
		return prefix+serialNo;
	}	

	public List<String> retrieveCustomer()
	{
		List<String> custDetails = new ArrayList<String>();
		String firstName, lastName;
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("select firstName, lastName, b1.mobile, addrs from booking b1 inner join customers c1 on b1.mobile = c1.mobile where bookingID = ?");
			pst.setString(1, obj_rpt.getBookingID());
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				firstName = rs.getString(1);
				lastName = rs.getString(2);
				custDetails.add(firstName+" "+lastName);
				custDetails.add(rs.getString(3));
				custDetails.add(rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custDetails;
		
	}
	public void getBookingTaxDetails(double total_amount)
	{			
		double lowerbound = 0, upperbound = 0;
		String taxID = null; 
						for(int i=0;i<4;i++)
						{
							try {
								PreparedStatement pst = con.prepareStatement(DatabaseConstants.SELECT_ALL_TAX_CATEGORY);
								pst.setString(1, Constants.GST_BOOKING);
								ResultSet rs = pst.executeQuery();
								while(rs.next())
								{
									//taxID = rs.getString(1);
									lowerbound = Double.parseDouble(rs.getString(2));
									if(rs.getString(3).equals(Constants.NOT_DEFINED))	
									{
										upperbound = 0;
										taxID = rs.getString(1);
										booking_cgst_per = rs.getString(4);
										booking_sgst_per = rs.getString(5);
										booking_gst_per = rs.getString(6);
										break;
										
									}
									else 
									{
										upperbound = Double.parseDouble(rs.getString(3));
										if(total_amount >= lowerbound && total_amount <= upperbound)
										{
											taxID = rs.getString(1);
											booking_cgst_per = rs.getString(4);
											booking_sgst_per = rs.getString(5);
											booking_gst_per = rs.getString(6);
											break;
										}
									}
									
					
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}						
	}
	
	public void getServiceTaxDetails(double total_amount)
	{			
		double lowerbound = 0, upperbound = 0;
		String taxID = null; 
						for(int i=0;i<4;i++)
						{
							try {
								PreparedStatement pst = con.prepareStatement(DatabaseConstants.SELECT_ALL_TAX_CATEGORY);
								pst.setString(1, Constants.GST_ROOM_SERVICE);
								ResultSet rs = pst.executeQuery();
								while(rs.next())
								{
									//taxID = rs.getString(1);
									lowerbound = Double.parseDouble(rs.getString(2));
									if(rs.getString(3).equals(Constants.NOT_DEFINED))	
									{
										upperbound = 0;
										taxID = rs.getString(1);
										service_cgst_per = rs.getString(4);
										service_sgst_per = rs.getString(5);
										service_gst_per = rs.getString(6);
										break;
										
									}
									else 
									{
										upperbound = Double.parseDouble(rs.getString(3));
										if(total_amount >= lowerbound && total_amount <= upperbound)
										{
											taxID = rs.getString(1);
											service_cgst_per = rs.getString(4);
											service_sgst_per = rs.getString(5);
											service_gst_per = rs.getString(6);
											break;
										}
									}
									
					
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}						
	}

	
	public int submitService()
	{
		int mis_flag = 0;
		PreparedStatement pstBook = null;
		PreparedStatement pst_tax = null;
		int s = 0;		
		double booking_gst=0, service_gst=0;
		double booking_net_total=0, service_net_total=0, gross_total=0, balance=0;
		String invoiceID = null;
		getBookingTaxDetails(BookingCheckout.booking_cost);
		booking_cgst = BookingCheckout.booking_cost * (0.01*Double.parseDouble(booking_cgst_per));
		booking_sgst = BookingCheckout.booking_cost * (0.01*Double.parseDouble(booking_sgst_per));
		booking_gst = booking_cgst+booking_sgst;		
		booking_net_total = BookingCheckout.booking_cost + booking_gst;
		gross_total = booking_net_total;

				
		
		//Updating Booking Details
			try
			{

				pstBook=con.prepareStatement("update booking set  grossAmount = ?, couponName = ?, status = ?, paymentMode = ?, invoiceID = ? where bookingID = ? and roomDoorNumber = ?");
				pstBook.setString(1, ""+BigDecimalType.roundDown(gross_total));
				pstBook.setString(2, ""+BookingCheckout.combo_coupon.getSelectedItem());
				pstBook.setString(3, Constants.CHECKOUT);
				pstBook.setString(4, BookingCheckout.rdbtnValue);
	
				if(BookingCheckout.rdbtnValue.equals(Constants.CARD))
				{
					invoiceID = generateCardInvoiceID();
					pstBook.setString(5, invoiceID);
				}
				else if(BookingCheckout.rdbtnValue.equals(Constants.CASH) && BookingCheckout.tglbtnTaxValue == false)
				{
					invoiceID = generateCashInvoiceID();
					pstBook.setString(5, invoiceID);
				}
				else if(BookingCheckout.rdbtnValue.equals(Constants.CASH) && BookingCheckout.tglbtnTaxValue == true)
				{
					invoiceID =  generateIWTID();
					pstBook.setString(5, invoiceID);
				}
				
				pstBook.setString(6,obj_rpt.getBookingID());
				pstBook.setString(7,BookingCheckout.roomNumber);
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, e, "Failure", JOptionPane.ERROR_MESSAGE);
			}
		
		
			
				
				// Miscellaneous Table Update
				if(BookingCheckout.miscellaneous_cost > 0)
				{
					mis_flag = 1;
					getServiceTaxDetails(BookingCheckout.miscellaneous_cost);
					service_cgst = BookingCheckout.miscellaneous_cost * (0.01*Double.parseDouble(service_cgst_per));
					service_sgst = BookingCheckout.miscellaneous_cost * (0.01*Double.parseDouble(service_sgst_per));
					service_gst = service_cgst + service_sgst;		
					service_net_total = BookingCheckout.miscellaneous_cost + service_gst;
					gross_total = booking_net_total + service_net_total;
					try
					{
						int rows = MiscellaneousServices.table.getRowCount();
						int cols = MiscellaneousServices.table.getColumnCount();	
					con.setAutoCommit(false);
					PreparedStatement pst_batch = con.prepareStatement("insert into miscellaneous(serviceName, serviceDesc, servicePrice, bookingID) "+ "values(?,?,?,?)");
					for(int i=0;i<rows;i++)
					{
						for(int j=0;j<cols+1;j++)	
						{					
							System.out.println("j value is"+j);
							if(j==3)
							{
								System.out.println("j value from if"+(j+1));
								pst_batch.setString(j+1, obj_rpt.getBookingID());							
							}
							else
							{
								pst_batch.setString(j+1, ""+MiscellaneousServices.table.getValueAt(i, j));
								System.out.println("j value from else"+(j+1));
							}
						}
						pst_batch.addBatch();
					}
					pst_batch.executeBatch();
					con.commit();	
					con.setAutoCommit(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e1, "Failure", JOptionPane.ERROR_MESSAGE);
					}
				}
					
		//Billing Table Update		
			try {
				pst_tax = con.prepareStatement(("insert into billing(invoiceID, booking_cgst, booking_sgst, service_cgst, service_sgst, extrabedcharges, employeeID ) "+ "values(?,?,?,?,?,?,?)"));
				pst_tax.setString(1, invoiceID);
				pst_tax.setString(2, ""+BigDecimalType.roundDown(booking_cgst));
				pst_tax.setString(3, ""+BigDecimalType.roundDown(booking_sgst));
				pst_tax.setString(4, ""+BigDecimalType.roundDown(service_cgst));
				pst_tax.setString(5, ""+BigDecimalType.roundDown(service_sgst));				
				pst_tax.setString(6, ""+BigDecimalType.roundDown(BookingCheckout.extra_person));
				pst_tax.setString(7, MainPage.userID);
				
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1, "Failure", JOptionPane.ERROR_MESSAGE);
			}		
			try
			{
				//Checkout Table Update
				PreparedStatement pst = con.prepareStatement("insert into checkout(checkOutID, bookingID, checkOutDate) "+ "values(?,?,?)");
				pst.setString(1, generateCheckOutId());
				pst.setString(2, obj_rpt.getBookingID());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				pst.setTimestamp(3, timestamp);
				s=pst.executeUpdate();								
			
				if(s>0)
				{
				int i = 1;
				PreparedStatement pstDel = con.prepareStatement("delete from reports");
				int s1 = pstDel.executeUpdate();
				pstBook.execute();
				pst_tax.execute();
				
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
				pstBatch.setString(3, ""+BigDecimalType.roundDown(booking_cgst));
				pstBatch.addBatch();
				
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "SGST @ "+booking_sgst_per+"%");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(booking_sgst));
				pstBatch.addBatch();
				
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Total GST @ "+booking_gst_per+"%");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(booking_gst));
				pstBatch.addBatch();
				
				if(mis_flag==1)
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
					
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Service TAX "+service_gst_per+"%"+" ("+ service_cgst_per +"% CGST + "+ service_sgst_per +"% SGST)");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(service_gst));
				pstBatch.addBatch();	
					
					
					
				}

//				pstBatch.setInt(1, i++);
//				pstBatch.setString(2, "Net Amount");
//				pstBatch.setString(3, ""+BigDecimalType.roundDown(booking_net_total));
//				pstBatch.addBatch();				
				
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Gross Amount");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(gross_total));
				pstBatch.addBatch();
				
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Coupon Discount "+"("+obj_rpt.getCouponName()+")");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(Double.parseDouble(obj_rpt.getDiscount())));
				pstBatch.addBatch();

				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Advance Amount");
				pstBatch.setString(3, obj_rpt.getAdvancePaid());
				pstBatch.addBatch();
				
				balance = gross_total - Double.parseDouble(obj_rpt.getAdvancePaid());  
				balance -= Double.parseDouble(obj_rpt.getDiscount());
				
				pstBatch.setInt(1, i++);
				pstBatch.setString(2, "Balance Amount");
				pstBatch.setString(3, ""+BigDecimalType.roundDown(balance));   
				pstBatch.addBatch();
				
				pstBatch.executeBatch();
				con.commit();
				con.setAutoCommit(true);
				JOptionPane.showMessageDialog(null, "Checkout done successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
				
				}
				else
					JOptionPane.showMessageDialog(null, "Error form else part", "Error", JOptionPane.ERROR_MESSAGE);
				
		 
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
			JOptionPane.showMessageDialog(null, e, "Failure", JOptionPane.ERROR_MESSAGE);
		}
		return s;
	}
	

	

	public void retrieveCheckOutDetails()
	{
		//BookingCheckout.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0;
		try {
			PreparedStatement pst;
			if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
				pst=con.prepareStatement(DatabaseConstants.TABLE_CHECKOUT_COLS);
			else
				pst=con.prepareStatement(DatabaseConstants.TABLE_CHECKOUT_COLS_USER);
			ResultSet rk = pst.executeQuery();
			
//			int roomCost, totalDays, totalCost, facilitiesCost;
			while(rk.next())
			{
				
//				//obj_checkOut1.setSlno(rk.getString(1));
//				obj_checkOut1.setBookingID(rk.getString(1));
//				obj_checkOut1.setRoomCategoryID(rk.getString(2));
//				obj_checkOut1.setRoomDoorNumber(rk.getString(3));
////				obj_checkOut1.setCustomerID(rk.getString(5));
//
//				obj_checkOut1.setTotalDays(rk.getString(4));
//				obj_checkOut1.setRoomCost(rk.getString(5));				
//				obj_checkOut1.setTotalCost(rk.getString(6));
//				obj_checkOut1.setCheckInDate(rk.getString(7));
//				obj_checkOut1.setAdvanceAmt(rk.getString(8));
//				obj_checkOut1.setRoomStatus(rk.getString(9));
//				
//				roomCost = Integer.parseInt(obj_checkOut1.getRoomCost());
//				totalDays = Integer.parseInt(obj_checkOut1.getTotalDays());
//				totalCost = Integer.parseInt(obj_checkOut1.getTotalCost());
//				facilitiesCost = totalCost - (roomCost*totalDays);
//				obj_checkOut1.setRoomFacilitesCost(Integer.toString(facilitiesCost));
//				obj_checkOut1.setBalance(Integer.toString(Integer.parseInt(obj_checkOut1.getTotalCost())-Integer.parseInt(obj_checkOut1.getAdvanceAmt())));
//				
				tableModel.addRow(new Object[]{				
//						obj_checkOut1.getBookingID(),
//						//obj_checkOut1.getCustomerID(),
//						obj_checkOut1.getCheckInDate(),
//						obj_checkOut1.getRoomDoorNumber(),	
//						obj_checkOut1.getRoomCategoryID(),
//						obj_checkOut1.getRoomCost(),
//						obj_checkOut1.getRoomFacilitesCost(),
//						obj_checkOut1.getTotalDays(),
//						obj_checkOut1.getTotalCost(),
//						obj_checkOut1.getAdvanceAmt(),
//						obj_checkOut1.getBalance()
						rk.getString(1),
						rk.getString(2),
						rk.getString(3)+" "+rk.getString(4),
						rk.getString(5),
						sdf.format(rk.getTimestamp(6))
						
					
				});
				slno++;
					
			}
//			else
//			{
//				JOptionPane.showMessageDialog(null, "Customer had not yet Checkd In", "Message",JOptionPane.INFORMATION_MESSAGE);	
//			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		CheckOutHistory.lblRows.setText("");
		CheckOutHistory.lblRows.setText("No. of Rows: "+slno);
	}
	public ReportDetails retrieveCheckOutDetails(String bookingID)
	{
		ReportDetails rpt = new ReportDetails();
		int c_totaladults = 0, c_totalchilds = 0, ch_totaladults = 0, ch_totalchilds = 0, extra_adults = 0, extra_childs = 0, extra_persons = 0;
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.BOOKING_CHECKOUT);
			pst.setString(1, bookingID);
			ResultSet rk=pst.executeQuery();
			
			PreparedStatement pst1 = con.prepareStatement(DatabaseConstants.CUSTOMER_NAMES);
			PreparedStatement pst_checkin = con.prepareStatement(DatabaseConstants.CHECKIN_ADULTS_CHILDS);
			pst_checkin.setString(1, bookingID);
			ResultSet rs_checkin = pst_checkin.executeQuery();
			if(rs_checkin.next())
			{
				ch_totaladults = Integer.parseInt(rs_checkin.getString(1));
				ch_totalchilds = Integer.parseInt(rs_checkin.getString(2));
			}

			if(rk.next())
			{
				rpt.setBookingID(rk.getString(1));
				rpt.setBookedDate(sdf.format(rk.getTimestamp(2)));		
				rpt.setCheckinDate(sdf.format(rk.getTimestamp(3)));
				rpt.setCheckoutDate(sdf.format(rk.getTimestamp(4)));
				rpt.setRoomType(rk.getString(5));
				rpt.setRoomNo(rk.getString(6));
				rpt.setMobileNumber(rk.getString(7));
				rpt.setRoomCost(rk.getString(8));
				rpt.setFacilitiesCost(rk.getString(9));
				rpt.setDays(rk.getString(10));
				rpt.setAdvancePaid(rk.getString(11));
				
				pst1.setString(1, rpt.getMobileNumber());
				ResultSet rm = pst1.executeQuery();
				if(rm.next())
					rpt.setCustomerName(rm.getString(1)+" "+rm.getString(2));
				
				PreparedStatement pst_capacity = con.prepareStatement(DatabaseConstants.CAPACITY_ADULTS_CHILDS);
				pst_capacity.setString(1, rpt.getRoomType());
				ResultSet rs_capacity = pst_capacity.executeQuery();
				if(rs_capacity.next())
				{
					c_totaladults = Integer.parseInt(rs_capacity.getString(1));
					c_totalchilds = Integer.parseInt(rs_capacity.getString(2));

				}
				if(ch_totaladults <= c_totaladults)
					extra_adults = 0;
				else
					extra_adults = ch_totaladults - c_totaladults;
				
				if(ch_totalchilds <= c_totalchilds)
					extra_childs = 0;
				else
					extra_childs = ch_totalchilds - c_totalchilds;
				
				extra_persons = extra_adults + extra_childs;
				if(extra_persons <= 0)
				{
					BookingCheckout.text_extrapersons.setText(""+extra_persons);
					BookingCheckout.text_extrabed.setText("0.00");
					BookingCheckout.btnAdd.requestFocus(true);
				}
				else
				{
					BookingCheckout.text_extrapersons.setText(""+extra_persons);
					BookingCheckout.text_extrabed.requestFocus(true);
				}
				
				
			
 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rpt;
	}
	
	public void retrieveCheckOutTransaction(String query, String parameter)
	{

//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			BookingCheckout.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			BookingCheckout.btnSubmit.setVisible(true);
//		else
//			BookingCheckout.btnSubmit.setVisible(false);

		
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
 
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			int slno = 0;
			while(rk.next())
			{					
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3)+" "+rk.getString(4),
						rk.getString(5),
						sdf.format(rk.getTimestamp(6))	
						});
				slno++;
 
			}
//			BookingCheckout.lblRows.setText("");
//			BookingCheckout.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getCouponDiscount(String couponName)
	{
	double discount = 0;
	PreparedStatement pst1;
	try {
		pst1 = con.prepareStatement("select couponDiscount from coupons where couponName = ?");
		pst1.setString(1, couponName);
		ResultSet rcs = pst1.executeQuery();
		if(rcs.next())
			discount = Double.parseDouble(rcs.getString(1));

		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	return discount;
	}
	
	
	
	

}
