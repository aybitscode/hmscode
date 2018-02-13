//package com.hms.view;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.SimpleDateFormat;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import com.hms.services.CustomerService;
//
//public class CheckinView {
//	
//	DefaultTableModel tableModel;
//	JTable table;
//	CustomerService customerService = new CustomerService();
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
//	
//	public CheckinView(){
//		
//	}
//	
//	public CheckinView(DefaultTableModel tableModel, JTable table) {
//		super();
//		this.tableModel = tableModel;
//		this.table = table;
//	}
//	
//	public void populateCheckInListInModel(){
//		
//		int numRows = tableModel.getRowCount();
//		for (int i=numRows;i>0;i--) {
//			tableModel.removeRow(i-1);
//			table.revalidate();
//		}
//		int slno = 0;
//		try {
//			PreparedStatement pst = con.prepareStatement(query);
//			ResultSet rk = pst.executeQuery();
//
//
//			while(rk.next())
//			{
//				tableModel.addRow(new Object[]{
//						rk.getString(1), 
//						rk.getString(2),
//						sdf.format(rk.getTimestamp(3)),
//						rk.getString(4),
//						rk.getString(5),
//						rk.getString(6)
//						
//				});
//				slno++;
//			}
//			CheckInEntry.lblRows.setText("");
//			CheckInEntry.lblRows.setText("No. of Rows: "+slno);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
