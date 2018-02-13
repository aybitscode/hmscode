package com.hms.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.model.GST;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.RowComparator;
import com.hms.view.GSTEntry;
import com.hotelmanagement.MainPage;


public class GSTService {
	private GST obj_gst;
	Statement stmt;
	ResultSet rk;	
	DefaultTableModel tableModel;
	JTable table;
	Connection con = DBConnection.getDBConnection();
	DecimalFormat df = new DecimalFormat("###.#");
	public GSTService()
	{
		
	}
	public GSTService(DefaultTableModel tableModel, JTable table)
	{
		this.tableModel = tableModel;
		this.table = table;
	}
	public GSTService(GST obj_gst)
	{
		this.obj_gst = obj_gst;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery(DatabaseConstants.SELECT_ALL_GST);
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public String generateGSTId(){

		String prefix = "G";
        String serialNo = "0";

        	 int count = 0;
        	 
        	Statement stmt = null;
        	ResultSet resultSet = null;
			try {
				stmt = con.createStatement();
	        	resultSet = stmt.executeQuery(DatabaseConstants.COUNT_GST);
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
			pst.setString(1, obj_gst.getGstID());
			pst.setString(2, obj_gst.getLowerBound());
			pst.setString(3, obj_gst.getUpperBound());
			pst.setString(4, obj_gst.getCgstPercentage());
			pst.setString(5, obj_gst.getSgstPercentage());
			pst.setString(6, obj_gst.getGstPercentge());
			pst.setString(7, obj_gst.getCategory());
			pst.setString(8, obj_gst.getStatus());
			s=pst.executeUpdate();
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	
	public String retrieveGSTName(String query)
	{
		String GSTName = null;
		try {
		
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rk = pst.executeQuery();
			if(rk.next())
			{
				GSTName = rk.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return GSTName;
	}
	

	public void retrieveAll(String query)
	{
		GSTEntry.btnSubmit.setVisible(false);
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
						rk.getString(2)+" to"+" "+rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7)
						
						
					});
					slno++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GSTEntry.lblRows.setText("");
		GSTEntry.lblRows.setText("No. of Rows: "+slno);
	}
	
	public void retrieve(String query, String parameter)
	{
		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			GSTEntry.btnSubmit.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			GSTEntry.btnSubmit.setVisible(true);
//		else
//			GSTEntry.btnSubmit.setVisible(false);

		
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
						rk.getString(2)+" to"+" "+rk.getString(3),
						rk.getString(4),
						rk.getString(5),
						rk.getString(6),
						rk.getString(7)

				});			
				slno++;
			}
			GSTEntry.lblRows.setText("");
			GSTEntry.lblRows.setText("No. of Rows: "+slno);
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
			if(RowComparator.compareRows(tableModel, table, DatabaseConstants.TABLE_TAX_TYPE, ""+GSTEntry.combo_search.getSelectedItem()))
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
						GSTEntry.sbm_consignCom.db.remove(GSTEntry.combo_search.getSelectedItem());
						GSTEntry.sbm_consignCom.db.add(""+tableModel.getValueAt(0, 0));
						JOptionPane.showMessageDialog(null, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
						retrieveAll(query2);
						GSTEntry.combo_search.setSelectedItem("");
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


	

	
}
