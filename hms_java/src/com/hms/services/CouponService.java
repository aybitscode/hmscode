package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Coupon;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.CouponEntry;
import com.hotelmanagement.MainPage;

public class CouponService {
	private Coupon obj_coupon;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	public CouponService()
	{
		
	}
	public CouponService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public CouponService(Coupon obj_coupon)
	{
		this.obj_coupon = obj_coupon;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery("select * from coupons");
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String generateCouponId(){

		String prefix = "CPN";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_COUPONS);
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
	
	public int submitService(String query)
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, obj_coupon.getCouponId());
			pst.setString(2, obj_coupon.getCouponName());
			pst.setString(3, obj_coupon.getCouponDiscount());
			pst.setString(4, obj_coupon.getCouponType());


			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public int updateForm(Coupon objCoupon) {
		// TODO Auto-generated method stub
		
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.UPDATE_COUPONS );
			pst.setString(1, objCoupon.getCouponName());
			pst.setString(2, objCoupon.getCouponDiscount());
			pst.setString(3, objCoupon.getCouponType());
			pst.setString(4, objCoupon.getCouponId());

			System.out.println("coupon id is"+objCoupon.getCouponId());
			s=pst.executeUpdate();
			if(s>0)
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}	
	
	
	public void retrieveAll(String query)
	{
		CouponEntry.btnSubmit.setVisible(false);
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
		int slno = 0; 
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			while(rk.next())
			{
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3)
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CouponEntry.lblRows.setText("");
		CouponEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			CouponEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			CouponEntry.btnSubmit.setVisible(true);
		else
			CouponEntry.btnSubmit.setVisible(false);

		
		int numRows = tableModel.getRowCount();
		for (int i=numRows;i>0;i--) {
			tableModel.removeRow(i-1);
			table.revalidate();
		}
			int slno = 0;
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{					
				
				tableModel.addRow(new Object[]{
						rk.getString(1),
						rk.getString(2),
						rk.getString(3)
				});			
				slno++;
			}
			CouponEntry.lblRows.setText("");
			CouponEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Coupon poulateForm(String parameter)
	{ 
		Coupon objCoupon = new Coupon();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.TABLE_COUPON_NAME);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery(); 
			if(rk.next())
			{					 
				objCoupon.setCouponId(rk.getString(1));
				objCoupon.setCouponName(rk.getString(2));
				objCoupon.setCouponDiscount(rk.getString(3));
				objCoupon.setCouponType(rk.getString(4)); 
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objCoupon;
	}
	
	
	public void updateService(String query, String query2, String param)
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.ALL_ROOM_CATEGORY_ID, ""+CouponEntry.combo_search.getSelectedItem()))
				JOptionPane.showMessageDialog(null, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
			else
			{
				
				Connection con = DBConnection.getDBConnection();
				try {
					PreparedStatement pst=con.prepareStatement(query);
					for(int i=0;i<rowCount;i++)
						for(int j=0;j<colCount;j++)
						{
							pst.setString(cl, ""+tableModel.getValueAt(i, j));
							cl++;
							System.out.println("counter is "+cl);
							
						}
					pst.setString(cl, param);
					int s = pst.executeUpdate();
					if(s>0)
					{
						CouponEntry.sbm_consignCom.db.remove(CouponEntry.combo_search.getSelectedItem());
						CouponEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						CouponEntry.combo_search.setSelectedItem("");
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Foreign Key constraing fails.",  "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}


	}



	

	
}
