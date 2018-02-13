package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.Season;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.SeasonEntry;
import com.hotelmanagement.MainPage;

public class SeasonService {
	private Season obj_season;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;	
	Connection con = DBConnection.getDBConnection();
	public SeasonService()
	{
		
	}
	public SeasonService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public SeasonService(Season obj_season)
	{
		this.obj_season = obj_season;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_SEASONS);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String generateID(){

		String prefix = "S";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_SEASONS);
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
			pst.setString(1, obj_season.getSeasonId());
			pst.setString(2, obj_season.getSeasonName());
			pst.setDate(3, obj_season.getDateStart());
			pst.setDate(4, obj_season.getDateEnd());
			pst.setString(5, obj_season.getCouponName());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	public int updateForm(Season objSeason)
	{
		PreparedStatement pst;
		int s = 0;
		try {
			pst = con.prepareStatement(DatabaseConstants.UPDATE_SEASON); 

			pst.setString(1, objSeason.getSeasonName());
			pst.setDate(2, objSeason.getDateStart());
			pst.setDate(3, objSeason.getDateEnd());
			pst.setString(4, objSeason.getCouponName());
			pst.setString(5, objSeason.getSeasonId());

			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public Season populateForm(String parameter)
	{
		Season objSeason = new Season();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.TABLE_SEASON_NAME);
			pst.setString(1, parameter);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{					
				objSeason.setSeasonId(rk.getString(1));
				objSeason.setSeasonName(rk.getString(2));
				objSeason.setDateStart(rk.getDate(3));
				objSeason.setDateEnd(rk.getDate(4));
				objSeason.setCouponName(rk.getString(5));
		 
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objSeason;
	}
	
	public void retrieveAll(String query)
	{
		SeasonEntry.btnSubmit.setVisible(false);
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
						rk.getDate(2),
						rk.getDate(3),
						rk.getString(4)
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SeasonEntry.lblRows.setText("");
		SeasonEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
		if(MainPage.user_role == null)
			SeasonEntry.btnSubmit.setVisible(false);
		else if(MainPage.user_role.equals(s2))
			SeasonEntry.btnSubmit.setVisible(true);
		else
			SeasonEntry.btnSubmit.setVisible(false);

		
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
						rk.getDate(2),
						rk.getDate(3), 
						rk.getString(4)
				});			
				slno++;
			}
			SeasonEntry.lblRows.setText("");
			SeasonEntry.lblRows.setText("No. of Rows: "+slno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void updateService(String query, String query2, String param)
	{
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		int cl = 1;
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.TABLE_SEASON_NAME, ""+SeasonEntry.combo_search.getSelectedItem()))
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
							
						}
					pst.setString(cl, param);
					int s = pst.executeUpdate();
					if(s>0)
					{
						SeasonEntry.sbm_consignCom.db.remove(SeasonEntry.combo_search.getSelectedItem());
						SeasonEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						SeasonEntry.combo_search.setSelectedItem("");
					}
					else
						JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
							
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}


	}	
	public String seasonCouponDiscount(String couponName) {
		Connection con = DBConnection.getDBConnection();
		String coupon_discount = "0.00";
		try {
			
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.COUPON_DISCOUNT);
			pst.setString(1, couponName);
			ResultSet rk=pst.executeQuery();
			java.util.Date currentDate = new java.util.Date();
			while(rk.next())
			{
	
					coupon_discount = rk.getString(1);
			
			}
			//System.out.println("coupon name is"+couponName);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return coupon_discount;
	}



	

	
}
