//package com.hms.services;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import com.hms.util.DBConnection;
//import com.hms.view.BookingCashTaxTransactions;
//import com.hotelmanagement.MainPage;
//
//public class BookingCashTaxTransactionService {
//	Statement stmt;
//	ResultSet rk;	
//	DefaultTableModel tableModel;
//	JTable table;
//	Connection con = DBConnection.getDBConnection();
//
//	public BookingCashTaxTransactionService()
//	{
//		
//	}
//	public BookingCashTaxTransactionService(DefaultTableModel tableModel, JTable table)
//	{
//		this.tableModel = tableModel;
//		this.table = table;
//	}
// 
//	public void retrieveAllTransactions(String query, String parameter)
//	{
//
//			BookingCashTaxTransactions.btnSave.setVisible(false);
//
//		
//		int numRows = tableModel.getRowCount();
//		for (int i=numRows;i>0;i--) {
//			tableModel.removeRow(i-1);
//			table.revalidate();
//		}
// 
//		try {
//			PreparedStatement pst = con.prepareStatement(query);
//			pst.setString(1, parameter);
//			ResultSet rk = pst.executeQuery();
//			while(rk.next())
//			{					
//				
//				tableModel.addRow(new Object[]{
//						rk.getString(1),
//						rk.getString(2),	
//						rk.getString(3),	
//						rk.getString(4),
//						rk.getString(5),
//						rk.getString(6),
//						rk.getString(7),
//						rk.getString(8),
//						rk.getString(9),
//						rk.getString(10),
//						rk.getString(11),
//						rk.getString(12),
//						rk.getString(13),
//						rk.getString(14),
//						rk.getString(15),
//						rk.getString(16),
//						rk.getString(17),
//						rk.getString(18)
//
//
//				});
// 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public void retrieveTransaction(String query, String parameter)
//	{
//
//		String s2 = "ADMIN";
//		if(MainPage.user_role == null)
//			BookingCashTaxTransactions.btnSave.setVisible(false);
//		else if(MainPage.user_role.equals(s2))
//			BookingCashTaxTransactions.btnSave.setVisible(true);
//		else
//			BookingCashTaxTransactions.btnSave.setVisible(false);
//
//		
//		int numRows = tableModel.getRowCount();
//		for (int i=numRows;i>0;i--) {
//			tableModel.removeRow(i-1);
//			table.revalidate();
//		}
// 
//		try {
//			PreparedStatement pst = con.prepareStatement(query);
//			pst.setString(1, parameter);
//			ResultSet rk = pst.executeQuery();
//			while(rk.next())
//			{					
//				
//				tableModel.addRow(new Object[]{
//						rk.getString(1),
//						rk.getString(2),	
//						rk.getString(3),	
//						rk.getString(4),
//						rk.getString(5),
//						rk.getString(6),
//						rk.getString(7),
//						rk.getString(8),
//						rk.getString(9),
//						rk.getString(10),
//						rk.getString(11),
//						rk.getString(12),
//						rk.getString(13),
//						rk.getString(14),
//						rk.getString(15),
//						rk.getString(16),
//						rk.getString(17),
//						rk.getString(18)
//
//
//				});
// 
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
////	public void retrieveTransactions(String query, String parameter1, String parameter2)
////	{
////
////		String s2 = "ADMIN";
////		if(MainPage.user_role == null)
////			BookingCashTaxTransactions.btnSave.setVisible(false);
////		else if(MainPage.user_role.equals(s2))
////			BookingCashTaxTransactions.btnSave.setVisible(true);
////		else
////			BookingCashTaxTransactions.btnSave.setVisible(false);
////
////		
////		int numRows = tableModel.getRowCount();
////		for (int i=numRows;i>0;i--) {
////			tableModel.removeRow(i-1);
////			table.revalidate();
////		}
//// 
////		try {
////			PreparedStatement pst = con.prepareStatement(query);
////			pst.setString(1, parameter1);
////			pst.setString(2, parameter2);
////			ResultSet rk = pst.executeQuery();
////			while(rk.next())
////			{					
////				
////				tableModel.addRow(new Object[]{
////						rk.getString(1),
////						rk.getString(2),	
////						rk.getString(3),	
////						rk.getString(4),
////						rk.getString(5),
////						rk.getString(6),
////						rk.getString(7),
////						rk.getString(8),
////						rk.getString(9),
////						rk.getString(10),
////						rk.getString(11),
////						rk.getString(12),
////						rk.getString(13),
////						rk.getString(14),
////						rk.getString(15),
////						rk.getString(16),
////						rk.getString(17),
////						rk.getString(18)
////
////
////				});
//// 
////			}
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//	
//	
//}
