package com.hms.view;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.apache.commons.lang.time.DateUtils;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import org.jdatepicker.constraints.RangeConstraint;

import com.hms.controller.BookingController;
import com.hms.controller.CustomerController;
import com.hms.model.Booking;
import com.hms.model.Room;
import com.hms.services.BookingService;
import com.hms.util.BigDecimalType;
import com.hms.util.BookingMobileModel;
import com.hms.util.Constants;
import com.hms.util.CustomDialog;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.DateDifferenceCalculator;
import com.hms.util.StringTimeStamp;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
public class MultipleLeftPanel extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField text_BookingID;
	//private JLabel lblBookingRoomCategory;
	private JButton btnSave;
	private JButton btnCancel;
	private JLabel  lblBookingID;
	JLabel lblLastname; 
	private JLabel lblArrivalDate;
	private JLabel lblEndDate;
	public static JComboBox<String> text_mobile;
	private JLabel lblMobile;
	private JPanel panel;
//	private ButtonGroup bg;	
	private Booking obj_booking;
	private BookingController booking_controller;
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
	private JLabel lblEnterBookingDetails;
	Object columnNames[]={"SLNO","Booking ID","BOOKING DATE","ARRIVAL DATE","DEPARTURE DATE","ROOM CATEGORY", "ROOM NO", "MOBILE", "STATUS", "TOTAL NIGHTS", "TOTAL ROOMS", "TOTAL ADULTS", "TOTAL CHILDS", "TOTAL COST"};	
	
	JDatePicker picker_booked_date;
	public static JDatePicker picker_checkout_date;
	public static JTextField text_status;
	private JLabel lblBookingStatus;
	//private JTextField text_totalNights;
	//private JLabel lblTotalNights;
	//public static JTextField text_room_cost;
	//private JLabel lblRoomCost;
	//private JLabel lblFacilitiesCost;
	//public static JTextField text_facilities_cost;
	//public static JTextField text_roomID;
	//public static JTextField text_room_category;
	//private JLabel lblRoomID;	
	private BookingService booking_service;
	private BookingMobileModel sbm_consignCom;
	public static String BOOKING_ID;
	private JLabel lblFirstname;
	public static JTextField text_firstName;
	public static JTextField text_lastName;
	UtilDateModel booked_model = new UtilDateModel();
	UtilDateModel checkout_model = new UtilDateModel();
	
	RangeConstraint rc_booked;
	RangeConstraint rc_checkout;
	DateFormat date_format;
	public static String roomDoorNumber;
	public static String room_category_ID;
	public static String room_rent;
	public static String facilities_cost;
	private JSpinner checkinSpinner;
	private JSpinner checkoutSpinner;
	private JLabel lblCheckinTime;
	private JLabel lblCheckoutTime;

	public static SpinnerDateModel checkinSpinModel;
	public static SpinnerDateModel checkoutSpinModel;
	SimpleDateFormat format;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
	String checkinTime;
	String checkoutTime;
	int flag = 0;
	public MultipleLeftPanel(){

		BOOKING_ID = "";
		obj_booking = new Booking();
		booking_controller = new BookingController(obj_booking);
		booking_service = new BookingService();
		
		//bg = new ButtonGroup();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};	
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 19, 0};
		gbl_panel.rowHeights = new int[]{100, 0, 0, 0, 0, 20, 0, 0, 20, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblEnterBookingDetails = new JLabel("Room Booking");
		GridBagConstraints gbc_lblEnterBookingDetails = new GridBagConstraints();
		gbc_lblEnterBookingDetails.fill = GridBagConstraints.BOTH;
		gbc_lblEnterBookingDetails.gridwidth = 2;
		gbc_lblEnterBookingDetails.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnterBookingDetails.gridx = 1;
		gbc_lblEnterBookingDetails.gridy = 1;
		panel.add(lblEnterBookingDetails, gbc_lblEnterBookingDetails);
		
		lblBookingID = new JLabel("Booking ID");
		GridBagConstraints gbc_lblBookingID = new GridBagConstraints();
		gbc_lblBookingID.anchor = GridBagConstraints.WEST;
		gbc_lblBookingID.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingID.gridx = 1;
		gbc_lblBookingID.gridy = 2;
		//panel.add(lblBookingID, gbc_lblBookingID);
		
		text_BookingID = new JTextField();
		GridBagConstraints gbc_text_BookingID = new GridBagConstraints();
		gbc_text_BookingID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_BookingID.gridwidth = 3;
		gbc_text_BookingID.insets = new Insets(0, 0, 5, 5);
		gbc_text_BookingID.gridx = 3;
		gbc_text_BookingID.gridy = 2;
		//panel.add(text_BookingID, gbc_text_BookingID);
		text_BookingID.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_BookingID.setColumns(10);
		
		//text_BookingID.setText(booking_service.generateBookingId());
		
		
		lblMobile = new JLabel("Mobile Number");
		GridBagConstraints gbc_lblMobile = new GridBagConstraints();
		gbc_lblMobile.anchor = GridBagConstraints.WEST;
		gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobile.gridx = 1;
		gbc_lblMobile.gridy = 2;
		panel.add(lblMobile, gbc_lblMobile);
		setBackground(new Color(SetColor.bkColor));
		
		text_mobile = new JComboBox<String>();
		GridBagConstraints gbc_text_mobile = new GridBagConstraints();
		gbc_text_mobile.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_mobile.insets = new Insets(0, 0, 5, 0);
		gbc_text_mobile.gridx = 2;
		gbc_text_mobile.gridy = 2;
		
		text_mobile.setEditable(true);
		sbm_consignCom = new BookingMobileModel(text_mobile, DatabaseConstants.CUSTOMER_MOBILE);
		text_mobile.setModel(sbm_consignCom);
		text_mobile.addItemListener(sbm_consignCom);
		text_mobile.addPopupMenuListener(sbm_consignCom);
		panel.add(text_mobile, gbc_text_mobile);
		text_mobile.requestFocus(true);

		
		lblFirstname = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstname = new GridBagConstraints();
		gbc_lblFirstname.anchor = GridBagConstraints.WEST;
		gbc_lblFirstname.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstname.gridx = 1;
		gbc_lblFirstname.gridy = 3;
		panel.add(lblFirstname, gbc_lblFirstname);
		
		text_firstName = new JTextField();
		GridBagConstraints gbc_text_firstName = new GridBagConstraints();
		gbc_text_firstName.insets = new Insets(0, 0, 5, 0);
		gbc_text_firstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_firstName.gridx = 2;
		gbc_text_firstName.gridy = 3;
		panel.add(text_firstName, gbc_text_firstName);
		text_firstName.setColumns(10);
		text_firstName.addFocusListener(this);
		text_firstName.setEditable(false);
		
		lblLastname = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastname = new GridBagConstraints();
		gbc_lblLastname.anchor = GridBagConstraints.WEST;
		gbc_lblLastname.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastname.gridx = 1;
		gbc_lblLastname.gridy = 4;
		panel.add(lblLastname, gbc_lblLastname);
		
		text_lastName = new JTextField();
		GridBagConstraints gbc_text_lastName = new GridBagConstraints();
		gbc_text_lastName.insets = new Insets(0, 0, 5, 0);
		gbc_text_lastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_lastName.gridx = 2;
		gbc_text_lastName.gridy = 4;
		panel.add(text_lastName, gbc_text_lastName);
		text_lastName.setColumns(10);
		text_lastName.addFocusListener(this);
		text_lastName.setEditable(false);
		
		
		lblArrivalDate = new JLabel("Check-In Date");
		GridBagConstraints gbc_lblArrivalDate = new GridBagConstraints();
		gbc_lblArrivalDate.anchor = GridBagConstraints.WEST;
		gbc_lblArrivalDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrivalDate.gridx = 1;
		gbc_lblArrivalDate.gridy = 5;
		panel.add(lblArrivalDate, gbc_lblArrivalDate);
		
		final Date current_date = new Date();
		date_format = new SimpleDateFormat("dd-MM-yyyy");
		Date date_after = DateUtils.addMonths(new Date(), 1);
		Date date_before = DateUtils.addDays(new Date(),-1);
		rc_booked = new RangeConstraint(date_before, date_after);
		booked_model.setValue(current_date);
		picker_booked_date = new JDatePicker(booked_model, "dd-MM-yyyy");	
		picker_booked_date.addDateSelectionConstraint(rc_booked);
		picker_booked_date.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
//				Date bookingDate =  (Date) picker_booked_date.getModel().getValue();
//				java.sql.Timestamp bookingSqlDate = new java.sql.Timestamp(bookingDate.getTime());
//				BookingGUI.rightSplitPanel.removeAll();
//				BookingGUI.rightSplitPanel.updateUI();
//				RightPanel rp = new RightPanel(bookingSqlDate);
//				BookingGUI.rightSplitPanel.add(new JScrollPane(rp));
				
				
				//updateRightPanel();
				//updateMultipleRightPanel();
			}
		});
		picker_booked_date.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				
		        
				//setClear();
				Date bookingDate = (Date)picker_booked_date.getModel().getValue();				
				Date checkoutDate = (Date)picker_checkout_date.getModel().getValue();
				if(DateDifferenceCalculator.validateBookingDate(bookingDate)<0)
				{
					JOptionPane.showMessageDialog(null, "Booking date must be greater than current date", "Error", JOptionPane.ERROR_MESSAGE);
					booked_model.setValue(current_date);
					
				}
				if(DateDifferenceCalculator.validateLimit(bookingDate)>Constants.BOOKING_DAYS_LIMIT)
				{
					JOptionPane.showMessageDialog(null, "You can't book room for more than 30 days", "Error", JOptionPane.ERROR_MESSAGE);
					booked_model.setValue(current_date);
				}
				if(checkoutDate!=null)
				{
						picker_checkout_date.getModel().setValue(null);						
				}
//				Date checkOutDate = (Date)picker_checkout_date.getModel().getValue();
//				validateCheckoutDate(current_date, bookingDate, checkOutDate);
				
			}
		});
		
		
		GridBagConstraints gbc_datePickerArrival = new GridBagConstraints();
		gbc_datePickerArrival.fill = GridBagConstraints.BOTH;
		gbc_datePickerArrival.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerArrival.gridx = 2;
		gbc_datePickerArrival.gridy = 5;
		panel.add(picker_booked_date, gbc_datePickerArrival);
		
		lblCheckinTime = new JLabel("Checkin Time");
		GridBagConstraints gbc_lblCheckinTime = new GridBagConstraints();
		gbc_lblCheckinTime.anchor = GridBagConstraints.WEST;
		gbc_lblCheckinTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblCheckinTime.gridx = 1;
		gbc_lblCheckinTime.gridy = 6;
		panel.add(lblCheckinTime, gbc_lblCheckinTime);
		
	    
	    Calendar cal = Calendar.getInstance();
	    java.util.Date current_time = cal.getTime();
	    checkinSpinModel  = new SpinnerDateModel();;
	    checkinSpinModel.setValue(current_time);
		checkinSpinner = new JSpinner(checkinSpinModel);
		format = ((JSpinner.DateEditor) checkinSpinner.getEditor()).getFormat();
		format.applyPattern("hh:mm a");
		

		
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 6;
		panel.add(checkinSpinner, gbc_spinner);
		
	    ChangeListener checkinListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	        	updateRightPanel();
	        	//updateMultipleRightPanel();
	        	
	        	
//	        	String checkinTime = format.format(checkinSpinner.getValue());
//	        	Calendar cal = Calendar.getInstance();
//	        	Date date = null;
//	        	try {
//					date = format.parse(checkinTime);
//				} catch (ParseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	        	cal.setTime(date);
//	        	 cal.add(Calendar.HOUR, Constants.CHECKOUT_TIME_LIMIT);
//	        	 System.out.println("the chckout time is"+cal.getTime());	  
//	        	 checkoutSpinner.setValue(cal.getTime());
//	         System.out.println("checkin time is"+checkinTime);
	          
	        }


	      };
	     
	     checkinSpinner.addChangeListener(checkinListener);

		
		lblEndDate = new JLabel("Check-Out Date");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.anchor = GridBagConstraints.WEST;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 7;
		panel.add(lblEndDate, gbc_lblEndDate);
		

		picker_checkout_date = new JDatePicker(checkout_model, "dd-MM-yyyy");
		Date date_after_checkout = DateUtils.addMonths(new Date(), Constants.BOOKING_MONTHS_LIMIT);
		rc_checkout = new RangeConstraint(current_date, date_after_checkout);
		picker_checkout_date.addDateSelectionConstraint(rc_checkout);
		GridBagConstraints gbc_datePickerDeparture = new GridBagConstraints();
		gbc_datePickerDeparture.fill = GridBagConstraints.BOTH;
		gbc_datePickerDeparture.insets = new Insets(0, 0, 5, 0);
		gbc_datePickerDeparture.gridx = 2;
		gbc_datePickerDeparture.gridy = 7;
		panel.add(picker_checkout_date, gbc_datePickerDeparture);
		picker_checkout_date.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(roomDoorNumber != null)
				{
					if(roomDoorNumber.length()==0)
					{
						JOptionPane.showMessageDialog(null, "Please select the room first", "Error", JOptionPane.ERROR_MESSAGE);
						picker_checkout_date.getModel().setValue(null);
						
					}
					else
					{
							Date d2 =  (Date) picker_checkout_date.getModel().getValue();
							java.sql.Timestamp checkout_date = new java.sql.Timestamp(d2.getTime());						
							int count = booking_controller.checkRoomAvailability(checkout_date, roomDoorNumber); 
							if(count > 0)
							{
								JOptionPane.showMessageDialog(null, "Room has been already booked on that date", "Error", JOptionPane.ERROR_MESSAGE);
								SingleRightPanel.bg.clearSelection();
								roomDoorNumber = "";
								picker_checkout_date.getModel().setValue(null);
							}
	
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please select the room first", "Error", JOptionPane.ERROR_MESSAGE);
					picker_checkout_date.getModel().setValue(null);
				}
				
			}
			});
		picker_checkout_date.getModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e)
			{
				Date bookingDate = (Date)picker_booked_date.getModel().getValue();	
				Date checkOutDate = (Date)picker_checkout_date.getModel().getValue();
				validateCheckoutDate(current_date, bookingDate, checkOutDate);
			}


		});

		
		
//		lblBookingRoomCategory = new JLabel("Room Category");
//		GridBagConstraints gbc_lblBookingRoomCategory = new GridBagConstraints();
//		gbc_lblBookingRoomCategory.anchor = GridBagConstraints.WEST;
//		gbc_lblBookingRoomCategory.insets = new Insets(0, 0, 5, 5);
//		gbc_lblBookingRoomCategory.gridx = 1;
//		gbc_lblBookingRoomCategory.gridy = 7;
//		panel.add(lblBookingRoomCategory, gbc_lblBookingRoomCategory);
//		
//		text_room_category = new JTextField();
//		GridBagConstraints gbc_text_room_category = new GridBagConstraints();
//		gbc_text_room_category.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_room_category.insets = new Insets(0, 0, 5, 0);
//		gbc_text_room_category.gridx = 2;
//		gbc_text_room_category.gridy = 7;
//		panel.add(text_room_category, gbc_text_room_category);
//		text_room_category.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
//		lblRoomID = new JLabel("Room No.");
//		GridBagConstraints gbc_lblRoomID = new GridBagConstraints();
//		gbc_lblRoomID.anchor = GridBagConstraints.WEST;
//		gbc_lblRoomID.insets = new Insets(0, 0, 5, 5);
//		gbc_lblRoomID.gridx = 1;
//		gbc_lblRoomID.gridy = 8;
//		panel.add(lblRoomID, gbc_lblRoomID);
//		
//		text_roomID = new JTextField();
//		GridBagConstraints gbc_text_roomID = new GridBagConstraints();
//		gbc_text_roomID.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_roomID.insets = new Insets(0, 0, 5, 0);
//		gbc_text_roomID.gridx = 2;
//		gbc_text_roomID.gridy = 8;
//		panel.add(text_roomID, gbc_text_roomID);
//		text_roomID.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		
		
//		lblRoomCost = new JLabel("Room Cost");
//		GridBagConstraints gbc_lblRoomCost = new GridBagConstraints();
//		gbc_lblRoomCost.anchor = GridBagConstraints.WEST;
//		gbc_lblRoomCost.insets = new Insets(0, 0, 5, 5);
//		gbc_lblRoomCost.gridx = 1;
//		gbc_lblRoomCost.gridy = 9;
//		panel.add(lblRoomCost, gbc_lblRoomCost);
//		
//		text_room_cost = new JTextField();
//		GridBagConstraints gbc_text_room_cost = new GridBagConstraints();
//		gbc_text_room_cost.insets = new Insets(0, 0, 5, 0);
//		gbc_text_room_cost.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_room_cost.gridx = 2;
//		gbc_text_room_cost.gridy = 9;
//		panel.add(text_room_cost, gbc_text_room_cost);
//		text_room_cost.setColumns(10);

		
//		lblFacilitiesCost = new JLabel("Facilities Cost");
//		GridBagConstraints gbc_lblFacilitiesCost = new GridBagConstraints();
//		gbc_lblFacilitiesCost.anchor = GridBagConstraints.WEST;
//		gbc_lblFacilitiesCost.insets = new Insets(0, 0, 5, 5);
//		gbc_lblFacilitiesCost.gridx = 1;
//		gbc_lblFacilitiesCost.gridy = 10;
//		panel.add(lblFacilitiesCost, gbc_lblFacilitiesCost);
//		
//		text_facilities_cost = new JTextField();
//		GridBagConstraints gbc_text_facilities_cost = new GridBagConstraints();
//		gbc_text_facilities_cost.insets = new Insets(0, 0, 5, 0);
//		gbc_text_facilities_cost.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_facilities_cost.gridx = 2;
//		gbc_text_facilities_cost.gridy = 10;
//		panel.add(text_facilities_cost, gbc_text_facilities_cost);
//		text_facilities_cost.setColumns(10);
//		text_facilities_cost.setEditable(false);
		
//		lblTotalNights = new JLabel("Total Days");
//		GridBagConstraints gbc_lblTotalNights = new GridBagConstraints();
//		gbc_lblTotalNights.insets = new Insets(0, 0, 5, 5);
//		gbc_lblTotalNights.anchor = GridBagConstraints.WEST;
//		gbc_lblTotalNights.gridx = 1;
//		gbc_lblTotalNights.gridy = 10;
//		//panel.add(lblTotalNights, gbc_lblTotalNights);
//		
//		text_totalNights = new JTextField();
//		GridBagConstraints gbc_text_totalNights = new GridBagConstraints();
//		gbc_text_totalNights.gridwidth = 3;
//		gbc_text_totalNights.insets = new Insets(0, 0, 5, 5);
//		gbc_text_totalNights.fill = GridBagConstraints.HORIZONTAL;
//		gbc_text_totalNights.gridx = 3;
//		gbc_text_totalNights.gridy = 10;
//		//panel.add(text_totalNights, gbc_text_totalNights);
//		text_totalNights.setColumns(10);
//		text_totalNights.setEditable(false);

		
		lblBookingStatus = new JLabel("Booking Status");
		GridBagConstraints gbc_lblBookingStatus = new GridBagConstraints();
		gbc_lblBookingStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblBookingStatus.anchor = GridBagConstraints.WEST;
		gbc_lblBookingStatus.gridx = 1;
		gbc_lblBookingStatus.gridy = 14;
		//panel.add(lblBookingStatus, gbc_lblBookingStatus);
		
		text_status = new JTextField();
		text_status.setFont(new Font("Tahoma", Font.PLAIN, 11));
		text_status.setColumns(10);
		GridBagConstraints gbc_text_status = new GridBagConstraints();
		gbc_text_status.gridwidth = 3;
		gbc_text_status.insets = new Insets(0, 0, 5, 5);
		gbc_text_status.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_status.gridx = 3;
		gbc_text_status.gridy = 14;
		
		lblCheckoutTime = new JLabel("Checkout Time");
		GridBagConstraints gbc_lblCheckoutTime = new GridBagConstraints();
		gbc_lblCheckoutTime.anchor = GridBagConstraints.WEST;
		gbc_lblCheckoutTime.insets = new Insets(0, 0, 5, 5);
		gbc_lblCheckoutTime.gridx = 1;
		gbc_lblCheckoutTime.gridy = 8;
		panel.add(lblCheckoutTime, gbc_lblCheckoutTime);
		
		Calendar cal1 = Calendar.getInstance();
	    java.util.Date current_time1 = cal1.getTime();
	    checkoutSpinModel  = new SpinnerDateModel();;
	    checkoutSpinModel.setValue(current_time1);
	    checkoutSpinner = new JSpinner(checkoutSpinModel);

	    format = ((JSpinner.DateEditor) checkoutSpinner.getEditor()).getFormat();
	    format.applyPattern("hh:mm a");
	     
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 8;
		panel.add(checkoutSpinner, gbc_spinner_1);

	    ChangeListener checkoutListener = new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
				String bookedDate = null, checkoutDate = null;			
				
				if(picker_checkout_date.getModel().getValue() != null)
				{
				bookedDate = dateFormat.format(picker_booked_date.getModel().getValue());
				checkoutDate = dateFormat.format(picker_checkout_date.getModel().getValue());
				
				
				String checkin_time = format.format(checkinSpinner.getValue());
				String checkout_time = format.format(checkoutSpinner.getValue());
				
				bookedDate += " "+ checkin_time;
				checkoutDate += " "+ checkout_time;
				java.sql.Timestamp booked_date = null;
				java.sql.Timestamp checkout_date = null;

				try {
					booked_date = StringTimeStamp.getSQLTimestamp(bookedDate);
					checkout_date = StringTimeStamp.getSQLTimestamp(checkoutDate);
					System.out.println("time is"+booked_date);
					System.out.println("time is"+checkout_date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
	        	//List<Room> freeRooms = booking_controller.multipleFreeRooms(DatabaseConstants.MULTIPLE_FREE_ROOMS, booked_date, checkout_date, Constants.BOOKED);
	        	//updateMultipleRightPanel(freeRooms);
				}
//	        	
//	        	if(picker_checkout_date.getModel().getValue()!=null)
//	        	{
//		        	checkoutDate = dateFormat.format(picker_checkout_date.getModel().getValue());
//		        	checkoutDate += " "+ checkoutTime;
//		        	checkinDate = dateFormat.format(picker_booked_date.getModel().getValue());
//		        	checkinDate += " "+ checkinTime;
//		        	try {
//						d1 = timeFormat.parse(checkinDate);
//						d2 = timeFormat.parse(checkoutDate); 
//					} catch (ParseException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//		        	System.out.println("checkinspinner is"+d1);
//		        	System.out.println("checkoutspinner is"+d2);
//		        	if(DateDifferenceCalculator.validateCheckoutTime(d1, d2))
//		        	{
//		        		
//		        		JOptionPane.showMessageDialog(null, "Booking time should be atleast "+Constants.CHECKOUT_TIME_LIMIT + " Hours",  "Error", JOptionPane.ERROR_MESSAGE);	        		
//		        		checkoutSpinner.setValue(checkoutTime);
//		        		
//		        	}
//	        	}
//	        	else
//	        	{
//		        	checkoutDate = dateFormat.format(picker_booked_date.getModel().getValue());
//		        	checkoutDate += " "+ checkoutTime;
//		        	checkinDate = dateFormat.format(picker_booked_date.getModel().getValue());
//		        	checkinDate += " "+ checkinTime;
//		        	try {
//						d1 = timeFormat.parse(checkinDate);
//						d2 = timeFormat.parse(checkoutDate); 
//					} catch (ParseException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					}
//		        	System.out.println("checkinspinner is"+d1);
//		        	System.out.println("checkoutspinner is"+d2);
//		        	if(DateDifferenceCalculator.validateCheckoutTime(d1, d2))
//		        	{
//		        		
//		        		JOptionPane.showMessageDialog(null, "Booking time should be atleast from else"+Constants.CHECKOUT_TIME_LIMIT + " Hours",  "Error", JOptionPane.ERROR_MESSAGE);	        		
//		        		checkoutSpinner.setValue(checkoutTime);
//		        		try {
//							checkoutSpinner.commitEdit();
//						} catch (ParseException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//		        		System.out.println("the value form checkoutspinner is"+checkoutSpinner.getValue()); // not executed
//		        	}
//	        	}
	        	
	        
	        	
	        	 
	        	//checkoutTime_RoomAvailability(checkoutTime);
	        }

   
	      };
	      checkoutSpinner.addChangeListener(checkoutListener);
		//panel.add(text_status, gbc_text_status);
		
		
		btnSave = new JButton("Submit");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 0);
		gbc_btnSave.fill = GridBagConstraints.BOTH;
		gbc_btnSave.gridx = 2;
		gbc_btnSave.gridy = 9;
		panel.add(btnSave, gbc_btnSave);
		btnSave.setMnemonic(KeyEvent.VK_B);
		btnSave.addActionListener(this);
		
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.BOTH;
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 14;
		//panel.add(btnCancel, gbc_btnCancel);
		//btnCancel.setMnemonic(KeyEvent.VK_B);
		//btnCancel.addActionListener(this);
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		paybkColor();
		
		
		//text_BookingID.addFocusListener(this);
		//text_status.addFocusListener(this);
//		text_totalNights.addFocusListener(this);
//		text_room_cost.addFocusListener(this);
		//setEditable(false);
	}
	
	
	private void updateRightPanel() {
		java.sql.Timestamp checkin_timestamp = null;
    	
    	checkinTime = format.format(checkinSpinner.getValue());
    	String bookedDate = dateFormat.format(picker_booked_date.getModel().getValue());
    	
    	bookedDate += " "+ checkinTime;
    	
		try {
			checkin_timestamp = StringTimeStamp.getSQLTimestamp(bookedDate);
			System.out.println("good"+checkin_timestamp);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookingSingleContainer.rightSplitPanel.removeAll();
		BookingSingleContainer.rightSplitPanel.updateUI();
		SingleRightPanel rp = new SingleRightPanel(checkin_timestamp);
		BookingSingleContainer.rightSplitPanel.add(new JScrollPane(rp));
	}
	
	private void updateMultipleRightPanel(List<Room> freeRooms) {
		java.sql.Timestamp checkin_timestamp = null;
    	
    	checkinTime = format.format(checkinSpinner.getValue());
    	String bookedDate = dateFormat.format(picker_booked_date.getModel().getValue());
    	
    	bookedDate += " "+ checkinTime;
    	
		try {
			checkin_timestamp = StringTimeStamp.getSQLTimestamp(bookedDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BookingSingleContainer.rightSplitPanel.removeAll();
		BookingSingleContainer.rightSplitPanel.updateUI();
		MultipleRightPanel mrp = new MultipleRightPanel(freeRooms);
		BookingSingleContainer.rightSplitPanel.add(new JScrollPane(mrp));
	}
	
	private void validateCheckoutDate(final Date current_date,
			Date bookingDate, Date checkOutDate) {
		if(checkOutDate!=null)
		{
				if(DateDifferenceCalculator.validateCheckoutDate(checkOutDate, bookingDate)<0)
				{
					JOptionPane.showMessageDialog(null, "Checkout date must be greater than booking date", "Error", JOptionPane.ERROR_MESSAGE);
					checkout_model.setValue(bookingDate);
				}
				else if(DateDifferenceCalculator.validateLimit(checkOutDate)>Constants.BOOKING_DAYS_LIMIT)
				{
					JOptionPane.showMessageDialog(null, "You can't book room for more than 30 days", "Error", JOptionPane.ERROR_MESSAGE);
					checkout_model.setValue(current_date);
				}
		}
	}
//	private void setClear()
//	{
//		text_room_cost.setText("");
//		text_room_category.setText("");
//		text_roomID.setText("");
//		text_facilities_cost.setText("");
//	}
	private void checkoutTime_RoomAvailability(String checkoutTime) {
		java.sql.Timestamp checkout_timestamp = null;
		if(picker_checkout_date.getModel().getValue()!=null)
    	{
    		System.out.println("from insede checkout listner sib");
	        	String bookedDate = dateFormat.format(picker_checkout_date.getModel().getValue());
	        	bookedDate += " "+ checkoutTime;
	        	
	    		try {
	    			checkout_timestamp = StringTimeStamp.getSQLTimestamp(bookedDate);
	    		} catch (ParseException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	        	 					
					int count = booking_controller.checkRoomAvailability(checkout_timestamp, roomDoorNumber); 
					if(count > 0)
					{
						flag = 1;
						JOptionPane.showMessageDialog(null, "Room has been already booked on this schedule", "Error", JOptionPane.ERROR_MESSAGE);
						SingleRightPanel.bg.clearSelection();
						roomDoorNumber = "";
						picker_checkout_date.getModel().setValue(null);
					}
    	}
	}	     
	private void setClear1()
	{
		//text_BookingID.setText("");
		//text_status.setText("");
//		text_totalNights.setText("");
//		text_room_cost.setText("");
//		text_room_category.setText("");
//		text_roomID.setText("");
//		text_facilities_cost.setText("");
		picker_checkout_date.getModel().setValue(null);

		//RightPanel.bg.clearSelection();
		//text_mobile.requestFocus(true);


		sbm_consignCom.cb.setSelectedItem("");

		text_firstName.setText("");
		text_lastName.setText("");		
	}
//	private void setEditable(boolean b)
//	{
////		text_room_category.setEditable(b);
////		text_roomID.setEditable(b);
////		text_room_cost.setEditable(b);
//		//text_status.setEditable(b);
//		
//	}
	public boolean checkMobileInDB(String mobile)
	{
		boolean b = false;
		if(mobile.length()>0)
		{
			for(String item : sbm_consignCom.db)
			{
				if(item.equals(mobile))
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

		// TODO Auto-generated method stub
		 
		if(e.getSource()==btnCancel)
		{

			setClear1();
		}
		else if(e.getSource()==btnSave)
		{
					String bookedDate, checkoutDate;
					bookedDate = dateFormat.format(picker_booked_date.getModel().getValue());
					checkoutDate = dateFormat.format(picker_checkout_date.getModel().getValue());
					
					String checkin_time = format.format(checkinSpinner.getValue());
					String checkout_time = format.format(checkoutSpinner.getValue());
					
					bookedDate += " "+ checkin_time;
					checkoutDate += " "+ checkout_time;
					
					Date d1 = null, d2 = null;
					
					try {
						d1 = timeFormat.parse(bookedDate);
						d2 =  timeFormat.parse(checkoutDate);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					if(d2!=null)
					{
						if(checkMobileInDB(""+text_mobile.getSelectedItem()))
						{
							if(DateDifferenceCalculator.validateBookingDate(d1)>=0)
							{							
								if(DateDifferenceCalculator.calculateDatePeriod(d1, d2) >= 0)
								{
									if(DateDifferenceCalculator.calculateCheckoutHours(d1, d2) > 0)
									{
										if(DateDifferenceCalculator.validateCheckinTime(bookedDate))
										{										
											if(room_rent.length() != 0)
											{	
												
												String checkoutTime = format.format(checkoutSpinner.getValue());
												checkoutTime_RoomAvailability(checkoutTime);
												if(flag == 0)
												//submitBooking();
												submitMultiBooking();
											}
											else
											{
												//text_room_cost.requestFocus(true);
												JOptionPane.showMessageDialog(this, "Select the room", "Error", JOptionPane.INFORMATION_MESSAGE);
											}
										}
										else
										{
											new CustomDialog(this, "Checkin time must be greater than current time", "Error", checkinSpinner, 75, 0, CustomDialog.ERROR_ICON);
										}
									}
									else
									{
										checkoutSpinner.requestFocus(true);
										new CustomDialog(this, "Checkout time must be greater "+Constants.CHECKOUT_TIME_LIMIT+" Hours than checkin time", "Error", checkoutSpinner, 75, 0, CustomDialog.ERROR_ICON);	
									}
								}
								else
								{
									picker_checkout_date.requestFocus(true);
									new CustomDialog(this, "Checkout date couldn't be previous date", "Error", picker_checkout_date, 75, 0, CustomDialog.ERROR_ICON);

								}
							}
							else
							{
								picker_booked_date.requestFocus(true);
								new CustomDialog(this, "Booking date should be later than Current Date", "Error", picker_booked_date, 75, 0, CustomDialog.ERROR_ICON);
							}
						}
						else
						{
							text_mobile.requestFocus(true);
							new CustomDialog(this, "Select mobile number from the list", "Error", text_mobile, 75, 0, CustomDialog.ERROR_ICON);
						}
					}
					else
					{
						picker_checkout_date.requestFocus(true);
						new CustomDialog(this, "Select the checkout date", "Error", picker_checkout_date, 75, 0, CustomDialog.ERROR_ICON);						
					}
			
			}

				

				
		}
	private void submitMultiBooking()
	{
		Connection con = DBConnection.getDBConnection();
		String roomID;
		List<Integer> ids = new ArrayList<>();
		List<Room> listRooms = new ArrayList<>();
		System.out.println("Boss the length is "+MultipleRightPanel.tglbtn.length);
		for(int i=0;i<MultipleRightPanel.tglbtn.length;i++)
		{
			System.out.println("the i value is"+i);
			System.out.println(MultipleRightPanel.tglbtn[i].getText());
		if(MultipleRightPanel.tglbtn[i].isSelected())
		{				
			roomID = MultipleRightPanel.roomIDS[i];
			String roomCategoryID = null;
			String roomDoorNo = null;
			int flag = 0;
			try {
				PreparedStatement psmt=con.prepareStatement(DatabaseConstants.ROOMCATEGORY_ROOMID);
				PreparedStatement pstRoomPrice=con.prepareStatement(DatabaseConstants.ROOMPRICE_ID_PRICE);
				psmt.setString(1,roomID);
				ResultSet rs=psmt.executeQuery();
				if(rs.next())
				{
					ids.add(i);
					roomCategoryID = rs.getString(1);	
					PreparedStatement pstDoor =con.prepareStatement(DatabaseConstants.ROOM_DOOR_NO);
					pstDoor.setString(1, roomID);
					ResultSet rsDoor = pstDoor.executeQuery();
					if(rsDoor.next())
						roomDoorNo = rsDoor.getString(1);
					

				}
				
				pstRoomPrice.setString(1, roomCategoryID);
				ResultSet rp = pstRoomPrice.executeQuery();
				if(rp.next())
				{
					flag = 1;
					Room obj_room = new Room();
					obj_room.setRoomCategoryID(roomCategoryID);
					obj_room.setRoomDoorNumber(roomDoorNo);
					obj_room.setRoomCost(rp.getString(2));
					PreparedStatement pst;
					String facilities = "";
					int facilitiesCost = 0;
					try {
						pst = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
						pst.setString(1, rp.getString(1));
						ResultSet rsf = pst.executeQuery();
						while(rsf.next())
						{
							facilities = facilities+rsf.getString(1)+", ";
							facilitiesCost += Double.parseDouble(rsf.getString(2));
							
						}
					obj_room.setFacilitiesCost(""+facilitiesCost);
					listRooms.add(obj_room);
						
						
					}catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//LeftPanel.text_status.setText(Constants.BOOKED);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(flag == 0)
			{
				
				JOptionPane.showMessageDialog(this, "Configure price for category '"+roomCategoryID+"'", "Error", JOptionPane.ERROR_MESSAGE);
				MultipleRightPanel.tglbtn[i].requestFocus(true);
			}
			else
			picker_checkout_date.requestFocus(true);

		}
	}
		
		
		submitMultiBookingDB(listRooms, ids);
	
		
	}
	public void submitMultiBookingDB(List<Room> listRooms, List<Integer> ids)
	{
		double total_room_cost = 0;
		double total_facility_cost = 0;
		for(Room room: listRooms)
		{
			total_room_cost += Double.parseDouble(room.getRoomCost());
			total_facility_cost += Double.parseDouble(room.getFacilitiesCost());
		}
		
		try
		{
		String timePeriod = null;
		Date d1 =  (Date) picker_booked_date.getModel().getValue();
		Date d2 =  (Date) picker_checkout_date.getModel().getValue();
		java.sql.Timestamp booked_date = new java.sql.Timestamp(d1.getTime());
		java.sql.Timestamp checkout_date = new java.sql.Timestamp(d2.getTime());
			
		timePeriod = DateDifferenceCalculator.calculateTimePeriod(d1, d2);										
		
		int answer = JOptionPane.showConfirmDialog(this, "Do you want to continue your total amount is Rs."+BigDecimalType.roundDown((total_room_cost+total_facility_cost)* 1)+" for "+timePeriod+" days \n excluding taxes & other services", null, JOptionPane.YES_NO_OPTION);		
		if (answer == JOptionPane.YES_OPTION)
		{	
			try{
		
					int s = booking_controller.submitMultipleBooking(listRooms, booked_date, checkout_date, ""+timePeriod, ""+text_mobile.getSelectedItem());
					
					if(s>0)
					{
					JOptionPane.showMessageDialog(this,"Rooms Booked Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
					setClear1();		
					for(int id: ids)
					{
						MultipleRightPanel.tglbtn[id].setEnabled(false);
						MultipleRightPanel.tglbtn[id].setToolTipText(Constants.BOOKED);
						MultipleRightPanel.tglbtn[id].setDisabledIcon(new ImageIcon("C:/HotelManagement/boot/images/booked.png"));
					}
					
					int answer1 = JOptionPane.showConfirmDialog(this, "Do you want to continue with check-in", null, JOptionPane.YES_NO_OPTION);
					if (answer1 == JOptionPane.YES_OPTION)
					{
						//MainPage.tabbedPane.setSelectedIndex(3);
						//CheckInEntry.text_BookingID.setSelectedItem(BOOKING_ID);
						//CheckInEntry.text_adults.requestFocus(true);
					}					
					
					}
					else
					{
					JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
					}
					

				}catch(NumberFormatException ee){
					//text_BookingID.requestFocus(true);
					text_mobile.requestFocus(true);
					JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
				catch (Exception ee) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
					
				}				
		}
		else if (answer == JOptionPane.NO_OPTION) {
			setClear1();
		}
		
		}catch(Exception ep){
		JOptionPane.showMessageDialog(this,""+ep,"Failure",JOptionPane.ERROR_MESSAGE);
		
		}
	}
	
	
	
	private void submitBooking() {
		try
		{
		double room_cost,facility_cost;
		//double net_amount, gross_amount;
		String timePeriod = null;
		//double gst_amount = 0;
		String bookedDate, checkoutDate;
		String checkin_time = format.format(checkinSpinner.getValue());
		String checkout_time = format.format(checkoutSpinner.getValue());
		bookedDate = dateFormat.format(picker_booked_date.getModel().getValue());
		checkoutDate = dateFormat.format(picker_checkout_date.getModel().getValue());
		
		
		bookedDate += " "+ checkin_time;
		checkoutDate += " "+ checkout_time;
		
		
		Date d1 =  timeFormat.parse(bookedDate);
		Date d2 =  timeFormat.parse(checkoutDate);
		java.sql.Timestamp booked_date = StringTimeStamp.getSQLTimestamp(bookedDate);
		java.sql.Timestamp checkout_date = StringTimeStamp.getSQLTimestamp(checkoutDate);
		

		

		timePeriod = DateDifferenceCalculator.calculateTimePeriod(d1, d2);										
		
		
		room_cost = Double.parseDouble(room_rent);
		facility_cost = Double.parseDouble(facilities_cost);
		

//		net_amount = totalNights * (room_cost+facility_cost);
//		gst_amount = net_amount * Constants.GST_VALUE;
//		gross_amount = net_amount + gst_amount;
		int answer;
		if(timePeriod.length()>=5)
			answer = JOptionPane.showConfirmDialog(this, "Do you want to continue your total amount is Rs."+BigDecimalType.roundDown((room_cost+facility_cost)*1)+" for "+timePeriod+" \n excluding taxes & other services", null, JOptionPane.YES_NO_OPTION);
		else
			answer = JOptionPane.showConfirmDialog(this, "Do you want to continue your total amount is Rs."+BigDecimalType.roundDown((room_cost+facility_cost)* Integer.parseInt(timePeriod))+" for "+timePeriod+" days \n excluding taxes & other services", null, JOptionPane.YES_NO_OPTION);
		
		
		if (answer == JOptionPane.YES_OPTION)
		{	
		try{
//						if(comboBox_customerID.getSelectedItem().equals("------Select------")).
//							throwError("Select the course");

		Timestamp currentDate = new Timestamp(System.currentTimeMillis());	
		BOOKING_ID = booking_service.generateBookingId();	
		obj_booking.setBooking_Id(BOOKING_ID);
		obj_booking.setBookingDate(currentDate);
		obj_booking.setBookedDate(booked_date);
		obj_booking.setCheckoutDate(checkout_date);
		obj_booking.setBooking_room_category(room_category_ID);
		obj_booking.setBooking_room_door_number(roomDoorNumber);					
		obj_booking.setBooking_customer_mobile(""+text_mobile.getSelectedItem());
		//obj_booking.setBooking_status(Constants.BOOKED);
		obj_booking.setBooking_total_nights(""+timePeriod);
		obj_booking.setRoomCost(""+BigDecimalType.roundDown(room_cost));
//		obj_booking.setNet_amount(""+BigDecimalType.roundDown(net_amount));
//		obj_booking.setGstValue(""+BigDecimalType.roundDown(gst_amount));
//		obj_booking.setGross_amount(""+BigDecimalType.roundDown(gross_amount));
		obj_booking.setFacilitiesCost(""+BigDecimalType.roundDown(facility_cost));
//		obj_booking.setBooking_total_adults(text_adults.getText().trim());
//		obj_booking.setBooking_total_childs(text_childs.getText().trim());
		//obj_booking.setBooking_total_cost(""+net_amount);
		obj_booking.setStatus(Constants.BOOKED);
		//obj_booking.setCouponName(couponName);


		int s = booking_controller.submitBooking();
		
		if(s>0)
		{
		JOptionPane.showMessageDialog(this,"Room Booked Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
		//text_BookingID.requestFocus(true);
		//text_mobile.requestFocus(true);
		setClear1();
		SingleRightPanel.tglbtn[SingleRightPanel.id].setEnabled(false);
		SingleRightPanel.tglbtn[SingleRightPanel.id].setToolTipText(Constants.BOOKED);
		SingleRightPanel.tglbtn[SingleRightPanel.id].setDisabledIcon(new ImageIcon("C:/HotelManagement/boot/images/booked.png"));
		
		int answer1 = JOptionPane.showConfirmDialog(this, "Do you want to continue with check-in", null, JOptionPane.YES_NO_OPTION);
		if (answer1 == JOptionPane.YES_OPTION)
		{
			//MainPage.tabbedPane.setSelectedIndex(3);
			//CheckInEntry.text_BookingID.setPopupVisible(true);
			//CheckInEntry.text_BookingID.setPopupVisible(false);
			//CheckInEntry.text_roomNo.setText(roomDoorNumber); Room number delted temp
			//CheckInEntry.text_BookingID.setSelectedItem(BOOKING_ID);
			CheckInEntry.text_adults.requestFocus(true);
		}					
		
		}
		else
		{
		JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
		}
		

		}catch(NumberFormatException ee){
			//text_BookingID.requestFocus(true);
			text_mobile.requestFocus(true);
			JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
		catch (Exception ee) {
			// TODO Auto-generated catch block
			ee.printStackTrace();
			JOptionPane.showMessageDialog(this,""+ee,"Failure Foolsh",JOptionPane.ERROR_MESSAGE);
			
			
		}
		
 
			
			
		}
		else if (answer == JOptionPane.NO_OPTION) {
			setClear1();
		}
		
		}catch(Exception ep){
		JOptionPane.showMessageDialog(this,""+ep,"Failure",JOptionPane.ERROR_MESSAGE);
		
		}
	}	
	
	

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
//		if(arg0.getSource()==text_BookingID)
//			text_BookingID.selectAll();
//		if(arg0.getSource()==text_status)
//			text_status.selectAll();
//		if(arg0.getSource()==text_room_cost)
//			text_room_cost.selectAll();
		if(arg0.getSource()==text_firstName || arg0.getSource()==text_lastName)
		{
			String mobile = ""+text_mobile.getSelectedItem();
			if(mobile.trim().length()!=0){
				CustomerController cc = new CustomerController();
				String[] customerName = cc.getCustomerName(mobile).split(" ");
				text_firstName.setText(customerName[0]);
				text_lastName.setText(customerName[1]);
			}
		}

//		else if(arg0.getSource()==text_customerID)
//			text_customerID.selectAll();
//		else if(arg0.getSource()==text_totalNights)
//		{			
//			
//		
//		}

 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

//		if(arg0.getSource()==text_BookingID)
//			text_BookingID.setText(text_BookingID.getText().trim().toUpperCase());
//		if(arg0.getSource()==text_status)
//			text_status.setText(text_status.getText().trim().toUpperCase());
//		else if(arg0.getSource()==text_customerID)
//			text_customerID.setText(text_customerID.getText().trim().toUpperCase());
//		if(arg0.getSource()==text_totalNights)
//		{
//
//			
//			
//		}
			
		
//
//
//		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblBookingID.setForeground(new Color(SetColor.cColor));
		lblArrivalDate.setForeground(new Color(SetColor.cColor));
		lblEndDate.setForeground(new Color(SetColor.cColor));
		//lblBookingRoomCategory.setForeground(new Color(SetColor.cColor));
		lblMobile.setForeground(new Color(SetColor.cColor));
		//lblRoomID.setForeground(new Color(SetColor.cColor));
		lblBookingStatus.setForeground(new Color(SetColor.cColor));
		//lblTotalNights.setForeground(new Color(SetColor.cColor));
		//lblRoomCost.setForeground(new Color(SetColor.cColor));
		//lblFacilitiesCost.setForeground(new Color(SetColor.cColor));
		lblFirstname.setForeground(new Color(SetColor.cColor));
		lblLastname.setForeground(new Color(SetColor.cColor));
		lblCheckoutTime.setForeground(new Color(SetColor.cColor));
		lblCheckinTime.setForeground(new Color(SetColor.cColor));

		
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
//		datePanel.setBackground(new Color(SetColor.bkColor));
//		datePickerArrival.setBackground(new Color(SetColor.bkColor));
//		datePanel1.setBackground(new Color(SetColor.bkColor));
//		datePickerDeparture.setBackground(new Color(SetColor.bkColor));
//		datePanel2.setBackground(new Color(SetColor.bkColor));
//		datePickerBooking.setBackground(new Color(SetColor.bkColor));		
	}
	public void uplmtColor()
	{
		lblEnterBookingDetails.setForeground(new Color(SetColor.mtColor));
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblMobile.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblArrivalDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEndDate.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblBookingRoomCategory.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblRoomID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingStatus.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblTotalNights.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblRoomCost.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblFacilitiesCost.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFirstname.setFont(new Font(ctFType,ctfProp,ctSize));
		lblLastname.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckoutTime.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckinTime.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		lblEnterBookingDetails.setFont(new Font(stFType,stfProp,stSize));
	}
}
