package com.hms.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RowComparator {
	public static boolean compareRows(DefaultTableModel tableModel, JTable table, String query, String param){
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		boolean rowequals = false;
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, param);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
					int cl = 1;
				for(int i=0;i<rowCount;i++)
					for(int j=0;j<colCount;j++)
					{
						if(rs.getString(cl).equals(tableModel.getValueAt(i, j)))
						{
							rowequals = true;
						cl++;
						}
						else
						{
							rowequals = false;
							break;
						}
					}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return rowequals;
	}
	
	
	public static boolean equalLists(List<String> one, List<String> two){     
	    if (one == null && two == null){
	        return true;
	    }

	    if((one == null && two != null) 
	      || one != null && two == null
	      || one.size() != two.size()){
	        return false;
	    }

	    //to avoid messing the order of the lists we will use a copy
	    //as noted in comments by A. R. S.
	    one = new ArrayList<String>(one); 
	    two = new ArrayList<String>(two);   

	    Collections.sort(one);
	    Collections.sort(two);      
	    return one.equals(two);
	}


}
