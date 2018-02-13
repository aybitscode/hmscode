package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.hms.controller.CheckOutController;
import com.hms.model.CheckOut;
import com.hms.services.CheckOutService;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.ExcelExporter;
import com.hms.util.SearchBoxModel;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class CheckOutEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JComboBox<String> text_BookingID;
	public static JButton btnSave;
	private JLabel  lblBookingID;
	private JTextField text_customerID;
	private JLabel lblCustomerID;
	private JPanel panel;
	public static String bookingID;
	
	
	
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JLabel lblCustomerDetails_1;
		
	
	java.sql.Date startDate;
	java.sql.Date endDate;
	java.sql.Date bookingDate;
	JDatePanelImpl datePanel;
	Calendar calendar;
	JDatePanelImpl datePanel1;
	Calendar calendar1;	
	JDatePanelImpl datePanel2;
	Calendar calendar2;		
	private JLabel lblBookingStatus;
	private JTextField text_advance;
	
	
	
	GridBagConstraints gbc_scrollPane;
	private JScrollPane scrollPane;
	public static JTable table;
	public static DefaultTableModel tableModel;
	JLabel transExcel;
	String filePath;
	
	private CheckOut obj_checkOut;
	private CheckOutController checkOut_controller;
	private CheckOutService checkOut_service;
	private JLabel lblCheckOut;
	private JTextField text_CheckOutID;

	Connection con = DBConnection.getDBConnection();
	public static SearchBoxModel sbm_consignCom;
	public static JButton btnSubmit;
	private JComboBox combo_search;
	private JLabel lblCheckout;
	public static JLabel lblRows;
	public CheckOutEntry(){
		
		obj_checkOut = new CheckOut();
		
		checkOut_controller = new CheckOutController(obj_checkOut);
		checkOut_service = new CheckOutService();
		
		
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 50, 50, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{24, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblCustomerDetails_1 = new JLabel("Check-Out");
		GridBagConstraints gbc_lblCustomerDetails_1 = new GridBagConstraints();
		gbc_lblCustomerDetails_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerDetails_1.gridx = 2;
		gbc_lblCustomerDetails_1.gridy = 0;
		
		lblRows = new JLabel("Rows");
		GridBagConstraints gbc_lblRows = new GridBagConstraints();
		gbc_lblRows.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblRows.insets = new Insets(5, 5, 5, 5);
		gbc_lblRows.gridx = 2;
		gbc_lblRows.gridy = 0;
		add(lblRows, gbc_lblRows);
		
		
		lblCheckout = new JLabel("Check-out Details");
		GridBagConstraints gbc_lblCheckout = new GridBagConstraints();
		gbc_lblCheckout.insets = new Insets(0, 0, 5, 5);
		gbc_lblCheckout.gridx = 3;
		gbc_lblCheckout.gridy = 0;
		add(lblCheckout, gbc_lblCheckout);
		
		combo_search = new JComboBox();
		GridBagConstraints gbc_combo_search = new GridBagConstraints();
		gbc_combo_search.anchor = GridBagConstraints.SOUTHEAST;
		gbc_combo_search.insets = new Insets(0, 0, 5, 5);
		gbc_combo_search.gridx = 4;
		gbc_combo_search.gridy = 0;
		combo_search.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	checkOut_service.retrieveCheckOutDetails();
		            }
		            else
		            {		            	
		            	checkOut_service.retrieveCheckOutTransaction(DatabaseConstants.ALL_CHECKOUT_ID, ""+combo_search.getSelectedItem());
		            }
		            	
		        }
		    }
		});
		
		combo_search.setMaximumRowCount(10);
		combo_search.setEditable(true);
		
		if(MainPage.user_role.equals(Constants.ADMIN))
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.SELECT_CHECKOUT_BOOKING_ID);
		else
		sbm_consignCom = new SearchBoxModel(combo_search, DatabaseConstants.SELECT_CHECKOUT_BOOKING_ID_USER);
		
		combo_search.setModel(sbm_consignCom);
		combo_search.addItemListener(sbm_consignCom);
		combo_search.addPopupMenuListener(sbm_consignCom);
		add(combo_search, gbc_combo_search);
		
		btnSubmit = new JButton("Submit");
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 5;
		gbc_btnSubmit.gridy = 0;
		add(btnSubmit, gbc_btnSubmit);
		btnSubmit.addActionListener(this);
		//add(lblCustomerDetails_1, gbc_lblCustomerDetails_1);
		

		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 3, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		lblCheckOut = new JLabel("Check-Out");
		GridBagConstraints gbc_lblCheckOut = new GridBagConstraints();
		gbc_lblCheckOut.gridwidth = 3;
		gbc_lblCheckOut.anchor = GridBagConstraints.WEST;
		gbc_lblCheckOut.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckOut.gridx = 0;
		gbc_lblCheckOut.gridy = 2;
		panel.add(lblCheckOut, gbc_lblCheckOut);
		
		text_CheckOutID = new JTextField();
		GridBagConstraints gbc_text_CheckOutID = new GridBagConstraints();
		gbc_text_CheckOutID.gridwidth = 2;
		gbc_text_CheckOutID.insets = new Insets(0, 0, 5, 0);
		gbc_text_CheckOutID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_CheckOutID.gridx = 2;
		gbc_text_CheckOutID.gridy = 2;
		//panel.add(text_CheckOutID, gbc_text_CheckOutID);
		text_CheckOutID.setColumns(10);
		//text_CheckOutID.setText(checkOut_service.generateCheckOutId());
		text_CheckOutID.setEditable(false);
		
//		lblCheckOut = new JLabel("Check Out Details");
//		GridBagConstraints gbc_lblCheckOut = new GridBagConstraints();
//		gbc_lblCheckOut.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblCheckOut.gridwidth = 4;
//		gbc_lblCheckOut.insets = new Insets(0, 0, 5, 0);
//		gbc_lblCheckOut.gridx = 0;
//		gbc_lblCheckOut.gridy = 1;
//		panel.add(lblCheckOut, gbc_lblCheckOut);
//		
		lblBookingID = new JLabel("Booking ID");
		GridBagConstraints gbc_lblBookingID = new GridBagConstraints();
		gbc_lblBookingID.anchor = GridBagConstraints.WEST;
		gbc_lblBookingID.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingID.gridx = 0;
		gbc_lblBookingID.gridy = 3;
		panel.add(lblBookingID, gbc_lblBookingID);
		
		text_BookingID = new JComboBox<String>();
		text_BookingID.setMaximumRowCount(10);
		GridBagConstraints gbc_text_BookingID = new GridBagConstraints();
		gbc_text_BookingID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_BookingID.insets = new Insets(0, 0, 5, 5);
		gbc_text_BookingID.gridx = 1;
		gbc_text_BookingID.gridy = 3;
		text_BookingID.setEditable(true);
		sbm_consignCom = new SearchBoxModel(text_BookingID, DatabaseConstants.CHECKOUT_BOOKING_ID, Constants.CHECKIN);
		text_BookingID.setModel(sbm_consignCom);
		text_BookingID.addItemListener(sbm_consignCom);
		text_BookingID.addPopupMenuListener(sbm_consignCom);
		text_BookingID.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXX");

		

		
		panel.add(text_BookingID, gbc_text_BookingID);
		text_BookingID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_BookingID.addFocusListener(this);
		

		
		
		lblCustomerID = new JLabel("Customer ID");
		GridBagConstraints gbc_lblCustomerID = new GridBagConstraints();
		gbc_lblCustomerID.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerID.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerID.gridx = 0;
		gbc_lblCustomerID.gridy = 4;
		//panel.add(lblCustomerID, gbc_lblCustomerID);
		
		
		text_customerID = new JTextField();
		text_customerID.setColumns(10);
		GridBagConstraints gbc_text_customerID = new GridBagConstraints();
		gbc_text_customerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_customerID.gridwidth = 2;
		gbc_text_customerID.insets = new Insets(0, 0, 5, 0);
		gbc_text_customerID.gridx = 2;
		gbc_text_customerID.gridy = 4;
		//panel.add(text_customerID, gbc_text_customerID);
		

		
		Calendar cal2 = new GregorianCalendar();
		int tyr2 = cal2.get(Calendar.YEAR);
		int tmon2 = cal2.get(Calendar.MONTH);
		int tday2 = cal2.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model2 = new SqlDateModel();
		model2.setDate(tyr2, tmon2, tday2);
		model2.setSelected(true);
		datePanel2 = new JDatePanelImpl(model2);
		Calendar cal = new GregorianCalendar();
		int tyr = cal.get(Calendar.YEAR);
		int tmon = cal.get(Calendar.MONTH);
		int tday = cal.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model = new SqlDateModel();
		model.setDate(tyr, tmon, tday);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);
		
		Calendar cal1 = new GregorianCalendar();
		int tyr1 = cal1.get(Calendar.YEAR);
		int tmon1 = cal1.get(Calendar.MONTH);
		int tday1 = cal1.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model1 = new SqlDateModel();
		model1.setDate(tyr1, tmon1, tday1);
		model1.setSelected(true);
		datePanel1 = new JDatePanelImpl(model1);
		

		
		
		transExcel = new JLabel();
		transExcel.setIcon(new ImageIcon(BookingEntry.class.getResource("/images/excel.png")));
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.EAST;
		gbc_lblCustomerDetails_excel.gridx = 6;
		gbc_lblCustomerDetails_excel.gridy = 0;
		add(transExcel, gbc_lblCustomerDetails_excel);	
		
		tableModel = new DefaultTableModel(Constants.checkOutEntryNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				if(table.getRowCount()>1)
				{
					return false;

				}
				else
				{
					if(column == 0)
						return false;
					else
					   return true;  
				}
				  }  
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.checkOutEntryTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
 
		
		checkOut_service = new CheckOutService(tableModel, table);
		checkOut_service.retrieveCheckOutDetails();
		
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			filePath=fd.getDirectory()+fd.getFile();
     			if(!filePath.equals("nullnull"))
     			{
                    ExcelExporter.fillData(table, filePath,"CheckOutTransactions");
                    JOptionPane.showMessageDialog(null, "Data saved at " +filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(filePath));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		
		text_advance = new JTextField();
		GridBagConstraints gbc_text_advance = new GridBagConstraints();
		gbc_text_advance.gridwidth = 2;
		gbc_text_advance.insets = new Insets(0, 0, 5, 0);
		gbc_text_advance.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_advance.gridx = 2;
		gbc_text_advance.gridy = 5;
		//panel.add(text_advance, gbc_text_advance);
		text_advance.setColumns(10);
		
		
		

		
		lblBookingStatus = new JLabel("Advance Amount");
		GridBagConstraints gbc_lblBookingStatus = new GridBagConstraints();
		gbc_lblBookingStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingStatus.anchor = GridBagConstraints.WEST;
		gbc_lblBookingStatus.gridx = 0;
		gbc_lblBookingStatus.gridy = 5;
		

		
		btnSave = new JButton("Check Out");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.gridwidth = 2;
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 5;
		panel.add(btnSave, gbc_btnSave);
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		btnSave.addFocusListener(this);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		paybkColor();
		text_advance.addFocusListener(this);
		text_customerID.addFocusListener(this);

		
		try
		{

			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("select roomCategoryID from roomcategory");
			while(rs.next())
			{
				//comboBox_roomCategoryID.addItem(rs.getString(1));	
			}
		 
 		
			 

		}catch(Exception e){
			Thread t=new Thread(){
				public void run()
				{
					//comboBox_roomCategoryID.removeAllItems();
					//comboBox_roomID.removeAllItems();
					JOptionPane.showMessageDialog(null, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				};
				t.start();
		}
	
		
	}
	public void setClear()
	{
//		
//		sbm_consignCom.db.removeAll();
//		sbm_consignCom = new SearchBoxModel(text_BookingID, DatabaseConstants.CHECKIN_BOOKING_ID, Constants.NULL);
//		text_BookingID.setModel(sbm_consignCom);
//		
//
//		text_BookingID.updateUI();
		sbm_consignCom.db.remove(text_BookingID.getSelectedItem());
		sbm_consignCom.cb.setSelectedItem("");
	
	}
	public boolean checkBookingInDB(String bookingID)
	{
		boolean b = false;
		if(bookingID.length()>0)
		{
			for(String item : sbm_consignCom.db)
			{
				if(item.equals(bookingID))
				{
					b =  true;
					break;
				}
			}
		}
		return b;		
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSave)
				{		
//					//startDate = (java.sql.Date) datePickerArrival.getModel().getValue();
//					//endDate = (java.sql.Date) datePickerDeparture.getModel().getValue();
//					//bookingDate = (java.sql.Date) datePickerBooking.getModel().getValue();
//	
//
//					try{
////						if(comboBox_customerID.getSelectedItem().equals("------Select------"))
////							throwError("Select the course");
////						DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
////						Date date = new Date();
////						System.out.println(dateFormat.format(date));
//						
//
//		
//
//					obj_checkOut.setBookingID(""+text_BookingID.getSelectedItem());
//					PreparedStatement pst = con.prepareStatement("select roomDoorNumber from booking where bookingID = ?");
//					pst.setString(1, obj_checkOut.getBookingID());
//					ResultSet rs = pst.executeQuery();
//					if(rs.next())
//						obj_checkOut.setRoomDoorNumber(rs.getString(1));
//							
//					int s = checkOut_controller.submitBooking();
//					
//					if(s>0)
//					{
//					JOptionPane.showMessageDialog(this,"CheckOut Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
//					text_BookingID.requestFocus(true);
//					setClear();
//					
//					checkOut_service.retrieveCheckOutDetails();
//					updateUI();
//	 
//					}
//					else
//					{
//					JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
//					}
//				
//					}catch(NumberFormatException ee){
//						text_BookingID.requestFocus(true);
//						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
//					catch (Exception ee) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
//						ee.printStackTrace();
//					}
//					
					if(checkBookingInDB(""+text_BookingID.getSelectedItem()))
					{
					//BookingCheckout dialog = new BookingCheckout(""+text_BookingID.getSelectedItem(), checkOut_service);
					//dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
					//dialog.setVisible(true);
					//dialog.setTitle("Customer Check-Out Details");
					//dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					btnSave.setEnabled(false);
					}
					else
					{
						text_BookingID.requestFocus(true);
						JOptionPane.showMessageDialog(this, "Select the Booking ID from the list", "Failure", JOptionPane.ERROR_MESSAGE);
					}
					
					
					
				}

				else if(e.getSource()==btnSubmit)
				{
					int rowCount = tableModel.getRowCount();
					int colCount = tableModel.getColumnCount();
					int cl = 1;
						if(compareRows())
							JOptionPane.showMessageDialog(this, "There are no changes",  "Error", JOptionPane.ERROR_MESSAGE);
						else
						{
							
							Connection con = DBConnection.getDBConnection();
							try {
								PreparedStatement pst=con.prepareStatement(DatabaseConstants.UPDATE_CHECKOUT);
								for(int i=0;i<rowCount;i++)
									for(int j=0;j<colCount;j++)
									{
										pst.setString(cl, ""+tableModel.getValueAt(i, j));
										if(cl==2)
										{
										SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
										java.util.Date date = sdf1.parse(""+tableModel.getValueAt(i, j));
										java.sql.Date sqlDate = new java.sql.Date(date.getTime());
										pst.setDate(cl, sqlDate);
										}
										cl++;
										
									}
								pst.setString(cl, ""+tableModel.getValueAt(0, 0));
								int s = pst.executeUpdate();
								if(s>0)
								{
									JOptionPane.showMessageDialog(this, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
									checkOut_service.retrieveCheckOutDetails();
									combo_search.setSelectedItem("");
								}
								else
									JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
										
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(this, "Please enter the details correctly.",  "Error", JOptionPane.ERROR_MESSAGE);
								
							} catch (ParseException ee) {
								// TODO Auto-generated catch block
								ee.printStackTrace();
							}
							
						}

				}

			}
	public boolean compareRows(){
		int rowCount = tableModel.getRowCount();
		int colCount = tableModel.getColumnCount();
		boolean rowequals = false;
		Connection con = DBConnection.getDBConnection();
		try {
			PreparedStatement pst = con.prepareStatement(DatabaseConstants.ALL_CHECKIN_ID);
			pst.setString(1, ""+combo_search.getSelectedItem());
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

	

	private static void throwError(String msg) throws Exception 
	{
		    throw new Exception(msg);
	} 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource()==text_BookingID)
//			text_BookingID.selectAll();
//		else if(arg0.getSource()==text_totalNights)
//			text_totalNights.selectAll();
//		else if(arg0.getSource()==text_totalRooms)
//			text_totalRooms.selectAll();
//		else if(arg0.getSource()==text_adults)
//			text_adults.selectAll();
//		else if(arg0.getSource()==text_childs)
//			text_childs.selectAll();
//		else if(arg0.getSource()==text_cost)
//			text_cost.selectAll();
		if(arg0.getSource()==text_customerID)
			text_customerID.selectAll();
//		else if(arg0.getSource()==btnSave)
//		{
//			checkOut_service.retrieveBookingDetails(""+text_BookingID.getSelectedItem());
//		}
 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_BookingID)
		{
			//text_BookingID.setText(text_BookingID.getText().trim().toUpperCase());
//			checkOut_service.retrieveBookingDetails(""+text_BookingID.getSelectedItem());
		
			//checkOut_service.retrieveCheckInDetails(text_BookingID.getText());
			
			
		}
		else if(arg0.getSource()==text_customerID)
			text_customerID.setText(text_customerID.getText().trim().toUpperCase());		



		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblBookingID.setForeground(new Color(SetColor.cColor));
		lblCustomerID.setForeground(new Color(SetColor.cColor));
		lblBookingStatus.setForeground(new Color(SetColor.cColor));
		lblRows.setForeground(new Color(SetColor.cColor));

		
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		datePanel.setBackground(new Color(SetColor.bkColor));
		datePanel1.setBackground(new Color(SetColor.bkColor));
		datePanel2.setBackground(new Color(SetColor.bkColor));
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
		lblCheckOut.setForeground(new Color(SetColor.mtColor));
		lblCheckout.setForeground(new Color(SetColor.mtColor));
		
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblCustomerID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingStatus.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckOut.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRows.setFont(new Font(ctFType,ctfProp,ctSize));
		
		
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
		lblCheckOut.setFont(new Font(stFType,stfProp,stSize));
		lblCheckout.setFont(new Font(stFType,stfProp,stSize));
	}



 

 
}
