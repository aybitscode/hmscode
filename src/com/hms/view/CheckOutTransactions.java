package com.hms.view;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hms.services.CheckOutTransactionService;
import com.hms.util.Constants;
import com.hms.util.ExcelExporter;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;


public class CheckOutTransactions extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GridBagConstraints gbc_scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	CheckOutTransactionService checkout_transaction_service;
	private JScrollPane scrollPane;
	JLabel lblBookingTransactions;
	JLabel transExcel;
	String filePath;
 
	
	/**
	 * Create the panel.//you have constructed the table without table model.
	 */
	public CheckOutTransactions() {
	
		setBackground(Color.BLACK);
		setForeground(Color.ORANGE);

 
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0};
		setLayout(gridBagLayout);
		
		
		
		
		
		

		tableModel = new DefaultTableModel(Constants.checkOutEntryNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				   return false;  
				  }  
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.checkOutEntryTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		
		lblBookingTransactions = new JLabel("Check Out Transactions");
		GridBagConstraints gbc_lblBookingTransactions = new GridBagConstraints();
		gbc_lblBookingTransactions.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingTransactions.gridx = 0;
		gbc_lblBookingTransactions.gridy = 0;
		add(lblBookingTransactions, gbc_lblBookingTransactions);
		
		transExcel = new JLabel();
		GridBagConstraints gbc_lblBookingTransactions1 = new GridBagConstraints();
		gbc_lblBookingTransactions1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingTransactions1.gridx = 1;
		gbc_lblBookingTransactions1.gridy = 0;
		add(transExcel, gbc_lblBookingTransactions1);		
		
		
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 2;
		 gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		checkout_transaction_service = new CheckOutTransactionService(tableModel, table);
		checkout_transaction_service.retrieveBookings();
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
		
		lblBookingTransactions.setForeground(new Color(SetColor.mtColor));
		lblBookingTransactions.setFont(new Font(SFont.stFType, SFont.stfProp, SFont.stSize));
		
//		transExcel = new JLabel("");
//		MainPage.panel_1.add(transExcel,MainPage.gbc_lblNewLabel);

		transExcel.setIcon(new ImageIcon(MainPage.class.getResource("/images/excel.png")));
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			filePath=fd.getDirectory()+fd.getFile();
     			if(!filePath.equals("nullnull"))
     			{
                    ExcelExporter.fillData(table, filePath,"CheckOut Transactions");
                    JOptionPane.showMessageDialog(null, "Data saved at " +filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(filePath));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
	}
	
	

}
