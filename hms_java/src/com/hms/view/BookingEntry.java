package com.hms.view;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableModel;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.hms.controller.BookingController;
import com.hms.model.Booking;
import com.hms.services.BookingService;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.ExcelExporter;
import com.hotelmanagement.DateLabelFormatter;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class BookingEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_BookingID;
	private JComboBox <String>comboBox_roomCategoryID;
    private JLabel lblBookingRoomCategory;
	private JButton btnSave;
	private JLabel  lblBookingID;
	private JButton btnCancel_1;
	private JLabel lblArrivalDate;
	private JLabel lblEndDate;
	private JLabel lblBookingDate;
	private JTextField text_customerID;
	private JLabel lblCustomerID;
	private JPanel panel;
	private Booking obj_booking;
	private BookingController booking_controller;
	
	
	
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JLabel lblCustomerDetails_1;
	private JLabel lblEnterBookingDetails;
		
	
	java.sql.Date startDate;
	java.sql.Date endDate;
	java.sql.Date bookingDate;
	
	private JDatePickerImpl datePickerArrival;
	JDatePanelImpl datePanel;
	Calendar calendar;
	
	private JDatePickerImpl datePickerDeparture;
	JDatePanelImpl datePanel1;
	Calendar calendar1;	
	
	private JDatePickerImpl datePickerBooking;
	JDatePanelImpl datePanel2;
	Calendar calendar2;		
	
	private JComboBox <String>text_status;
	private JLabel lblBookingStatus;
	private JTextField text_totalNights;
	private JLabel lblTotalNights;
	private JTextField text_totalRooms;
	private JLabel lblTotalRooms;
	private JTextField text_adults;
	private JLabel lblTotalAdults;
	private JTextField text_childs;
	private JLabel lblTotalChilds;	
	private JTextField text_cost;
	private JLabel lblTotalCost;	
	private JComboBox<String> comboBox_roomID;
	private JLabel lblRoomID;	
	
	
	
	GridBagConstraints gbc_scrollPane;
	private JScrollPane scrollPane;
	JTable table;
	DefaultTableModel tableModel;
	private BookingService booking_service;
	JLabel transExcel;
	String filePath;
	Connection con = DBConnection.getDBConnection();
	public BookingEntry(){
		
		obj_booking = new Booking();
		booking_controller = new BookingController(obj_booking);
		booking_service = new BookingService();
		
		new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{24, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblCustomerDetails_1 = new JLabel("Booking Details");
		GridBagConstraints gbc_lblCustomerDetails_1 = new GridBagConstraints();
		gbc_lblCustomerDetails_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerDetails_1.gridx = 2;
		gbc_lblCustomerDetails_1.gridy = 0;
		add(lblCustomerDetails_1, gbc_lblCustomerDetails_1);
		

		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 14, 3, 0, 20, 20, 18, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblEnterBookingDetails = new JLabel("Enter Booking Details");
		GridBagConstraints gbc_lblEnterBookingDetails = new GridBagConstraints();
		gbc_lblEnterBookingDetails.fill = GridBagConstraints.BOTH;
		gbc_lblEnterBookingDetails.gridwidth = 4;
		gbc_lblEnterBookingDetails.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterBookingDetails.gridx = 0;
		gbc_lblEnterBookingDetails.gridy = 1;
		panel.add(lblEnterBookingDetails, gbc_lblEnterBookingDetails);
		lblBookingID = new JLabel("Booking ID");
		GridBagConstraints gbc_lblBookingID = new GridBagConstraints();
		gbc_lblBookingID.anchor = GridBagConstraints.WEST;
		gbc_lblBookingID.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingID.gridx = 0;
		gbc_lblBookingID.gridy = 2;
		panel.add(lblBookingID, gbc_lblBookingID);
		
		text_BookingID = new JTextField();
		GridBagConstraints gbc_text_BookingID = new GridBagConstraints();
		gbc_text_BookingID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_BookingID.gridwidth = 3;
		gbc_text_BookingID.insets = new Insets(0, 0, 5, 5);
		gbc_text_BookingID.gridx = 2;
		gbc_text_BookingID.gridy = 2;
		panel.add(text_BookingID, gbc_text_BookingID);
		text_BookingID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_BookingID.setColumns(10);
		
		text_BookingID.setText(booking_service.generateBookingId());
		
		
		lblCustomerID = new JLabel("Customer ID");
		GridBagConstraints gbc_lblCustomerID = new GridBagConstraints();
		gbc_lblCustomerID.anchor = GridBagConstraints.WEST;
		gbc_lblCustomerID.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomerID.gridx = 0;
		gbc_lblCustomerID.gridy = 3;
		panel.add(lblCustomerID, gbc_lblCustomerID);
		
		
		text_customerID = new JTextField();
		GridBagConstraints gbc_text_customerID = new GridBagConstraints();
		gbc_text_customerID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_customerID.gridwidth = 3;
		gbc_text_customerID.insets = new Insets(0, 0, 5, 5);
		gbc_text_customerID.gridx = 2;
		gbc_text_customerID.gridy = 3;
		panel.add(text_customerID, gbc_text_customerID);	
		System.out.println("the value of customer id is"+CustomerEntry.customer_ID);
		text_customerID.setText(CustomerEntry.customer_ID);
		text_customerID.selectAll();
		

		lblBookingDate = new JLabel("Booking Date");
		GridBagConstraints gbc_lblBookingDate = new GridBagConstraints();
		gbc_lblBookingDate.anchor = GridBagConstraints.WEST;
		gbc_lblBookingDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingDate.gridx = 0;
		gbc_lblBookingDate.gridy = 4;
		panel.add(lblBookingDate, gbc_lblBookingDate);
		

		
		Calendar cal2 = new GregorianCalendar();
		int tyr2 = cal2.get(Calendar.YEAR);
		int tmon2 = cal2.get(Calendar.MONTH);
		int tday2 = cal2.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model2 = new SqlDateModel();
		model2.setDate(tyr2, tmon2, tday2);
		model2.setSelected(true);
		datePanel2 = new JDatePanelImpl(model2);		
		datePickerBooking = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
		datePickerBooking.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_datePickerBooking = new GridBagConstraints();
		gbc_datePickerBooking.gridwidth = 4;
		gbc_datePickerBooking.fill = GridBagConstraints.BOTH;
		gbc_datePickerBooking.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerBooking.gridx = 2;
		gbc_datePickerBooking.gridy = 4;
		panel.add(datePickerBooking, gbc_datePickerBooking);		
		
		
		lblArrivalDate = new JLabel("Arrival Date");
		GridBagConstraints gbc_lblArrivalDate = new GridBagConstraints();
		gbc_lblArrivalDate.anchor = GridBagConstraints.WEST;
		gbc_lblArrivalDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrivalDate.gridx = 0;
		gbc_lblArrivalDate.gridy = 5;
		panel.add(lblArrivalDate, gbc_lblArrivalDate);
		Calendar cal = new GregorianCalendar();
		int tyr = cal.get(Calendar.YEAR);
		int tmon = cal.get(Calendar.MONTH);
		int tday = cal.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model = new SqlDateModel();
		model.setDate(tyr, tmon, tday);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);		
		datePickerArrival = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		datePickerArrival.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_datePickerArrival = new GridBagConstraints();
		gbc_datePickerArrival.gridwidth = 4;
		gbc_datePickerArrival.fill = GridBagConstraints.BOTH;
		gbc_datePickerArrival.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerArrival.gridx = 2;
		gbc_datePickerArrival.gridy = 5;
		panel.add(datePickerArrival, gbc_datePickerArrival);
		

		
		lblEndDate = new JLabel("Departure Date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 0;
		gbc_lblEndDate.gridy = 6;
		panel.add(lblEndDate, gbc_lblEndDate);
		
		Calendar cal1 = new GregorianCalendar();
		int tyr1 = cal1.get(Calendar.YEAR);
		int tmon1 = cal1.get(Calendar.MONTH);
		int tday1 = cal1.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model1 = new SqlDateModel();
		model1.setDate(tyr1, tmon1, tday1);
		model1.setSelected(true);
		datePanel1 = new JDatePanelImpl(model1);		
		datePickerDeparture = new JDatePickerImpl(datePanel1,new DateLabelFormatter());
		datePickerDeparture.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_datePickerDeparture = new GridBagConstraints();
		gbc_datePickerDeparture.gridwidth = 4;
		gbc_datePickerDeparture.fill = GridBagConstraints.BOTH;
		gbc_datePickerDeparture.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerDeparture.gridx = 2;
		gbc_datePickerDeparture.gridy = 6;
		panel.add(datePickerDeparture, gbc_datePickerDeparture);
		
		
		lblBookingRoomCategory = new JLabel("Room Category ID");
		GridBagConstraints gbc_lblBookingRoomCategory = new GridBagConstraints();
		gbc_lblBookingRoomCategory.anchor = GridBagConstraints.WEST;
		gbc_lblBookingRoomCategory.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingRoomCategory.gridx = 0;
		gbc_lblBookingRoomCategory.gridy = 7;
		panel.add(lblBookingRoomCategory, gbc_lblBookingRoomCategory);
		
		comboBox_roomCategoryID = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_roomCategoryID = new GridBagConstraints();
		gbc_comboBox_roomCategoryID.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_roomCategoryID.gridwidth = 3;
		gbc_comboBox_roomCategoryID.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_roomCategoryID.gridx = 2;
		gbc_comboBox_roomCategoryID.gridy = 7;
		panel.add(comboBox_roomCategoryID, gbc_comboBox_roomCategoryID);
		comboBox_roomCategoryID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblRoomID = new JLabel("Room ID");
		GridBagConstraints gbc_lblRoomID = new GridBagConstraints();
		gbc_lblRoomID.anchor = GridBagConstraints.WEST;
		gbc_lblRoomID.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoomID.gridx = 0;
		gbc_lblRoomID.gridy = 8;
		panel.add(lblRoomID, gbc_lblRoomID);
		
		comboBox_roomID = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_roomID = new GridBagConstraints();
		gbc_comboBox_roomID.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_roomID.gridwidth = 3;
		gbc_comboBox_roomID.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_roomID.gridx = 2;
		gbc_comboBox_roomID.gridy = 8;
		panel.add(comboBox_roomID, gbc_comboBox_roomID);
		comboBox_roomID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblTotalRooms = new JLabel("Room Cost");
		GridBagConstraints gbc_lblTotalRooms = new GridBagConstraints();
		gbc_lblTotalRooms.anchor = GridBagConstraints.WEST;
		gbc_lblTotalRooms.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalRooms.gridx = 0;
		gbc_lblTotalRooms.gridy = 9;
		panel.add(lblTotalRooms, gbc_lblTotalRooms);
		
		text_totalRooms = new JTextField();
		GridBagConstraints gbc_text_totalRooms = new GridBagConstraints();
		gbc_text_totalRooms.gridwidth = 3;
		gbc_text_totalRooms.insets = new Insets(0, 0, 5, 5);
		gbc_text_totalRooms.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_totalRooms.gridx = 2;
		gbc_text_totalRooms.gridy = 9;
		panel.add(text_totalRooms, gbc_text_totalRooms);
		text_totalRooms.setColumns(10);
		

		
		
		transExcel = new JLabel();
		transExcel.setIcon(new ImageIcon(BookingEntry.class.getResource("/images/excel.png")));
		GridBagConstraints gbc_lblCustomerDetails_excel = new GridBagConstraints();
		gbc_lblCustomerDetails_excel.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustomerDetails_excel.anchor = GridBagConstraints.SOUTHEAST;
		gbc_lblCustomerDetails_excel.gridx = 3;
		gbc_lblCustomerDetails_excel.gridy = 0;
		add(transExcel, gbc_lblCustomerDetails_excel);	
		
		tableModel = new DefaultTableModel(Constants.bookingEntryColNames, 0);
		table = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){  
				   return false;  
				  }  
		};
		
	    ToolTipHeader header = new ToolTipHeader(table.getColumnModel());
	    header.setToolTipStrings(Constants.bookingTipStr);
	    header.setToolTipText("Default ToolTip TEXT");
	    table.setTableHeader(header);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,14));
		//table.getColumn("SL NO").setMaxWidth(50);
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		 gbc_scrollPane = new GridBagConstraints();
		 gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table.setForeground(new Color(SetColor.cColor));
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		table.setFont(new Font(SFont.ctFType,SFont.ctfProp, SFont.ctSize));
 
		
		booking_service = new BookingService(tableModel, table);
		booking_service.retrieveBookings();
		
		transExcel.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e)
			{
	
			
                try {
       			FileDialog fd=new FileDialog(new JFrame(),"Save",FileDialog.SAVE);
     			fd.setVisible(true);
     			filePath=fd.getDirectory()+fd.getFile();
     			if(!filePath.equals("nullnull"))
     			{
                    ExcelExporter.fillData(table, filePath,"Booking Transactions");
                    JOptionPane.showMessageDialog(null, "Data saved at " +filePath+" successfully", "Message",JOptionPane.INFORMATION_MESSAGE);
                    Desktop.getDesktop().open(new File(filePath));  
     			}
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
			}
		});
		


		
		
		lblTotalNights = new JLabel("Total Days");
		GridBagConstraints gbc_lblTotalNights = new GridBagConstraints();
		gbc_lblTotalNights.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalNights.anchor = GridBagConstraints.WEST;
		gbc_lblTotalNights.gridx = 0;
		gbc_lblTotalNights.gridy = 10;
		panel.add(lblTotalNights, gbc_lblTotalNights);
		
		text_totalNights = new JTextField();
		GridBagConstraints gbc_text_totalNights = new GridBagConstraints();
		gbc_text_totalNights.gridwidth = 3;
		gbc_text_totalNights.insets = new Insets(0, 0, 5, 5);
		gbc_text_totalNights.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_totalNights.gridx = 2;
		gbc_text_totalNights.gridy = 10;
		panel.add(text_totalNights, gbc_text_totalNights);
		text_totalNights.setColumns(10);
		

		
		lblTotalAdults = new JLabel("Total Adults");
		GridBagConstraints gbc_lblTotalAdults = new GridBagConstraints();
		gbc_lblTotalAdults.anchor = GridBagConstraints.WEST;
		gbc_lblTotalAdults.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalAdults.gridx = 0;
		gbc_lblTotalAdults.gridy = 11;
		panel.add(lblTotalAdults, gbc_lblTotalAdults);
		
		text_adults = new JTextField();
		GridBagConstraints gbc_text_adults = new GridBagConstraints();
		gbc_text_adults.gridwidth = 3;
		gbc_text_adults.insets = new Insets(0, 0, 5, 5);
		gbc_text_adults.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_adults.gridx = 2;
		gbc_text_adults.gridy = 11;
		panel.add(text_adults, gbc_text_adults);
		text_adults.setColumns(10);
		
		lblTotalChilds = new JLabel("Total childs");
		GridBagConstraints gbc_lblTotalChilds = new GridBagConstraints();
		gbc_lblTotalChilds.anchor = GridBagConstraints.WEST;
		gbc_lblTotalChilds.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalChilds.gridx = 0;
		gbc_lblTotalChilds.gridy = 12;
		panel.add(lblTotalChilds, gbc_lblTotalChilds);
		
		text_childs = new JTextField();
		GridBagConstraints gbc_text_childs = new GridBagConstraints();
		gbc_text_childs.gridwidth = 3;
		gbc_text_childs.insets = new Insets(0, 0, 5, 5);
		gbc_text_childs.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_childs.gridx = 2;
		gbc_text_childs.gridy = 12;
		panel.add(text_childs, gbc_text_childs);
		text_childs.setColumns(10);
		
		lblTotalCost = new JLabel("Total cost");
		GridBagConstraints gbc_lblTotalCost = new GridBagConstraints();
		gbc_lblTotalCost.anchor = GridBagConstraints.WEST;
		gbc_lblTotalCost.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalCost.gridx = 0;
		gbc_lblTotalCost.gridy = 13;
		panel.add(lblTotalCost, gbc_lblTotalCost);
		
		text_cost = new JTextField();
		GridBagConstraints gbc_text_cost = new GridBagConstraints();
		gbc_text_cost.gridwidth = 3;
		gbc_text_cost.insets = new Insets(0, 0, 5, 5);
		gbc_text_cost.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_cost.gridx = 2;
		gbc_text_cost.gridy = 13;
		panel.add(text_cost, gbc_text_cost);
		text_cost.setColumns(10);		
		
		
		

		
		lblBookingStatus = new JLabel("Booking Status");
		GridBagConstraints gbc_lblBookingStatus = new GridBagConstraints();
		gbc_lblBookingStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingStatus.anchor = GridBagConstraints.WEST;
		gbc_lblBookingStatus.gridx = 0;
		gbc_lblBookingStatus.gridy = 14;
		panel.add(lblBookingStatus, gbc_lblBookingStatus);
		
		text_status = new JComboBox<String>();
		text_status.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GridBagConstraints gbc_text_status = new GridBagConstraints();
		gbc_text_status.gridwidth = 3;
		gbc_text_status.insets = new Insets(0, 0, 5, 5);
		gbc_text_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_status.gridx = 2;
		gbc_text_status.gridy = 14;
		panel.add(text_status, gbc_text_status);
	//	text_status.addItem(Constants.BOOKINGSELECTITEM);
		text_status.addItem(Constants.BOOKED);
		text_status.addItem(Constants.FREE);
		
		btnSave = new JButton("Submit");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 15;
		panel.add(btnSave, gbc_btnSave);
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		btnCancel_1 = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel_1 = new GridBagConstraints();
		gbc_btnCancel_1.gridwidth = 2;
		gbc_btnCancel_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancel_1.fill = GridBagConstraints.BOTH;
		gbc_btnCancel_1.gridx = 3;
		gbc_btnCancel_1.gridy = 15;
		panel.add(btnCancel_1, gbc_btnCancel_1);
		btnCancel_1.setMnemonic(KeyEvent.VK_C);		
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		paybkColor();
		btnCancel_1.addActionListener(this);
		
		
		text_BookingID.addFocusListener(this);
		text_totalNights.addFocusListener(this);
		text_totalRooms.addFocusListener(this);
		text_adults.addFocusListener(this);
		text_childs.addFocusListener(this);
		text_cost.addFocusListener(this);
		text_customerID.addFocusListener(this);
		
		comboBox_roomCategoryID.addActionListener(this);

		
		try
		{

			Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery("select roomCategoryID from roomcategory");
		//	comboBox_roomCategoryID.addItem(Constants.BOOKINGSELECTITEM);
		//	comboBox_roomID.addItem(Constants.BOOKINGSELECTITEM);
			while(rs.next())
			{
				comboBox_roomCategoryID.addItem(rs.getString(1));	
			}
		 
 		
			 

		}catch(Exception e){
			Thread t=new Thread(){
				public void run()
				{
					comboBox_roomCategoryID.removeAllItems();
					comboBox_roomID.removeAllItems();
					JOptionPane.showMessageDialog(null, "No courses found", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				};
				t.start();
		}

		
		
	}

	private void setClear1()
	{
		text_BookingID.setText("");
		text_totalNights.setText("");
		text_totalRooms.setText("");
		text_adults.setText("");
		text_childs.setText("");
		text_cost.setText("");
		text_customerID.setText("");
		

		
	}

 
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==comboBox_roomCategoryID)
		{

			String roomCategoryID=(String) comboBox_roomCategoryID.getSelectedItem();
			comboBox_roomID.removeAllItems();
			//comboBox_roomID.addItem(Constants.BOOKINGSELECTITEM);
			try
			{
				PreparedStatement psmt=con.prepareStatement("Select roomID from rooms where roomCategoryID = ? and roomStatus = ?");
				psmt.setString(1,roomCategoryID);
				psmt.setString(2, Constants.FREE);
				ResultSet rs=psmt.executeQuery();
				while(rs.next())
				comboBox_roomID.addItem(rs.getString(1));
			}catch(Exception ex){}
		}
		if(e.getSource()==btnCancel_1)
		{

			setClear1();
		}

				if(e.getSource()==btnSave)
				{		
					startDate = (java.sql.Date) datePickerArrival.getModel().getValue();
					endDate = (java.sql.Date) datePickerDeparture.getModel().getValue();
					bookingDate = (java.sql.Date) datePickerBooking.getModel().getValue();
	

					try{
//						if(comboBox_customerID.getSelectedItem().equals("------Select------"))
//							throwError("Select the course");
					obj_booking.setBooking_Id(text_BookingID.getText().trim().toUpperCase());
//					obj_booking.setBookingDate(bookingDate);
//					obj_booking.setBookedDate(startDate);
//					obj_booking.setCheckoutDate(endDate);
					obj_booking.setBooking_room_category((String)comboBox_roomCategoryID.getSelectedItem());
					obj_booking.setBooking_room_door_number((String)comboBox_roomID.getSelectedItem());
					obj_booking.setBooking_customer_mobile(text_customerID.getText().trim());
					//obj_booking.setBooking_status(Constants.BOOKED);
					obj_booking.setBooking_total_nights(text_totalNights.getText().trim());
					obj_booking.setBooking_total_rooms(text_totalRooms.getText().trim());
					//obj_booking.setBooking_total_adults(text_adults.getText().trim());
					//obj_booking.setBooking_total_childs(text_childs.getText().trim());
					//obj_booking.setNet_amount(text_cost.getText().trim());


					int s = booking_controller.submitBooking();
					
					if(s>0)
					{
					JOptionPane.showMessageDialog(this,"Room Booked Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					text_BookingID.requestFocus(true);
					booking_service.retrieveBookings();
					updateUI();
					setClear1();
					//comboBox_roomCategoryID.setSelectedItem(Constants.BOOKINGSELECTITEM);
					comboBox_roomID.removeAllItems();
					
					//comboBox_roomID.addItem(Constants.BOOKINGSELECTITEM);
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
					}
					
				
					}catch(NumberFormatException ee){
						text_BookingID.requestFocus(true);
						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
					catch (Exception ee) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					}
					
 
					
					
				}
			}
	
	private static void throwError(String msg) throws Exception 
	{
		    throw new Exception(msg);
	} 

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_BookingID)
			text_BookingID.selectAll();
		else if(arg0.getSource()==text_totalNights)
			text_totalNights.selectAll();
		else if(arg0.getSource()==text_totalRooms)
			text_totalRooms.selectAll();
		else if(arg0.getSource()==text_adults)
			text_adults.selectAll();
		else if(arg0.getSource()==text_childs)
			text_childs.selectAll();
		else if(arg0.getSource()==text_cost)
			text_cost.selectAll();
		else if(arg0.getSource()==text_customerID)
			text_customerID.selectAll();

 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

		if(arg0.getSource()==text_BookingID)
			text_BookingID.setText(text_BookingID.getText().trim().toUpperCase());
		else if(arg0.getSource()==text_customerID)
			text_customerID.setText(text_customerID.getText().trim().toUpperCase());		
		


		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblBookingID.setForeground(new Color(SetColor.cColor));
		lblArrivalDate.setForeground(new Color(SetColor.cColor));
		lblEndDate.setForeground(new Color(SetColor.cColor));
		lblBookingRoomCategory.setForeground(new Color(SetColor.cColor));
		lblCustomerID.setForeground(new Color(SetColor.cColor));
		lblBookingDate.setForeground(new Color(SetColor.cColor));
		lblRoomID.setForeground(new Color(SetColor.cColor));
		lblBookingStatus.setForeground(new Color(SetColor.cColor));
		lblTotalNights.setForeground(new Color(SetColor.cColor));
		lblTotalRooms.setForeground(new Color(SetColor.cColor));
		lblTotalAdults.setForeground(new Color(SetColor.cColor));
		lblTotalChilds.setForeground(new Color(SetColor.cColor));
		lblTotalCost.setForeground(new Color(SetColor.cColor));

		
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		datePanel.setBackground(new Color(SetColor.bkColor));
		datePickerArrival.setBackground(new Color(SetColor.bkColor));
		datePanel1.setBackground(new Color(SetColor.bkColor));
		datePickerDeparture.setBackground(new Color(SetColor.bkColor));
		datePanel2.setBackground(new Color(SetColor.bkColor));
		datePickerBooking.setBackground(new Color(SetColor.bkColor));		
	}
	public void uplmtColor()
	{
		lblCustomerDetails_1.setForeground(new Color(SetColor.mtColor));
		lblEnterBookingDetails.setForeground(new Color(SetColor.mtColor));
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
		lblArrivalDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEndDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingRoomCategory.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingStatus.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalNights.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalRooms.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalAdults.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalChilds.setFont(new Font(ctFType,ctfProp,ctSize));
		lblTotalCost.setFont(new Font(ctFType,ctfProp,ctSize));
		
		
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblCustomerDetails_1.setFont(new Font(stFType,stfProp,stSize));
		lblEnterBookingDetails.setFont(new Font(stFType,stfProp,stSize));
	}
	
	
	
	
	private class JTableButtonMouseListener extends MouseAdapter {
   		private final JTable table;
   		public JTableButtonMouseListener(JTable table) {
   			this.table = table;
   		}
   		public void mousePressed(MouseEvent e) {
   			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
   			int row    = e.getY()/table.getRowHeight(); 
   			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
   			    Object value = table.getValueAt(row, column);
   			    if (value instanceof JLabel || value instanceof Object) {
   			    	{
   			    		JToolTip tip=new JToolTip();
   			    		tip.setTipText("demo");
   			    		tip.show();
   			    		updateUI();
   			    	}
   			    }
   				
   			}
   		}
   	}



 

 
}
