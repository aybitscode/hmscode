package com.hms.view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.hms.controller.CheckInController;
import com.hms.model.CheckIn;
import com.hms.services.CheckInService;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.CustomDialog;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.SearchBoxModel;
import com.hms.validators.DoubleValidator;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class CheckInEntry extends JPanel implements ActionListener,FocusListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static  JTextField textBookingId;
	private JButton btnSave;
	private JLabel  lblBookingID;
	private JTextField text_customerID;
	private JLabel lblCustomerID;
	private JPanel componentContainer;

	
	
	
	Object[][] dat;
	int rows=0;
	Statement st;
	ResultSet rs;
		
	
	java.sql.Date startDate;
	java.sql.Date endDate;
	java.sql.Date bookingDate;
	JDatePanelImpl datePanel;
	Calendar calendar;
	JDatePanelImpl datePanel1;
	Calendar calendar1;	
	JDatePanelImpl datePanel2;
	Calendar calendar2;		
	private JLabel lblAdvance;
	private JTextField text_advance;  
	private CheckIn obj_checkIn;
	private CheckInController checkin_controller;
	private CheckInService checkin_service;
 
	Connection con = DBConnection.getDBConnection();
	SearchBoxModel sbm_consignCom;
	//public static SearchBoxModel sbm_consignCom1;
	private JLabel lblAdults;
	private JLabel lblChildren;
	public static JTextField text_adults;
	private JTextField text_children;
	public static String bookingID;
	private JCheckBox chckbxNew;
	JPanel searchPanel;
	JComboBox comboSearch;
	JLabel lblCustomerMobile;
	JButton btnSearch;
	private JButton btnClear;
	public static JTextField textName;
	private JLabel lblName;
	public static JTextField textMobile;
	private JLabel lblMobile;
	public static String roomDoorNumber;
	MainPage mpg;
	private JScrollPane scrollPane;
	public CheckInEntry(MainPage mpg){
		this.mpg = mpg;
		obj_checkIn = new CheckIn();
		checkin_controller = new CheckInController(obj_checkIn);
		checkin_service = new CheckInService();
		
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0};
		setLayout(gridBagLayout);
		
		chckbxNew = new JCheckBox("New Checkin");
		chckbxNew.setSelected(true);
		GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
		gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNew.gridx = 1;
		gbc_chckbxNew.gridy = 2;
		//add(chckbxNew, gbc_chckbxNew);
		chckbxNew.addActionListener(this);
		chckbxNew.setSelected(true);
		
 
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][300px]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblCustomerMobile = new JLabel("Customer Mobile");
		searchPanel.add(lblCustomerMobile, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom = new SearchBoxModel(comboSearch,  DatabaseConstants.SELECT_CHECKIN_BOOKING_ID);
		comboSearch.setModel(sbm_consignCom);
		comboSearch.addItemListener(sbm_consignCom);
		comboSearch.addPopupMenuListener(sbm_consignCom); 
		searchPanel.add(comboSearch, "cell 1 0,grow");
		
		comboSearch.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (((JTextComponent) ((JComboBox) ((Component) event
		                    .getSource()).getParent()).getEditor()
		                    .getEditorComponent()).getText().isEmpty())
		            {
		            	//checkin_controller.retrieveBookingIDs(query, param)
		            }
		            else
		            {		    
		            	btnSave.setText(Constants.UPDATE);
//		            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//		            	setData(customer);
//						remove(customerSearchPanel);					
//						add(componentContainer, gbc_componentContainer);
//						updateUI();
		            }
		            	
		        }
		    }
		});
		
		btnSearch = new JButton("Search");
		searchPanel.add(btnSearch, "cell 1 1,alignx center,growy"); 
		btnSearch.addActionListener(this);
		
		scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(MainPage.scrwidth-100, MainPage.scrheight-150));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		//add(customerSearchPanel, gbc_customerContainer);
		
		componentContainer = new JPanel();
		scrollPane.setViewportView(componentContainer);
		componentContainer.setLayout(new MigLayout("", "[grow][1px][150][150][grow]", "[20][35][10px][0][35][10][35][10][][35][10][35][10][35][10][35]"));
		componentContainer.setBackground(Color.white);
		
		lblBookingID = new JLabel("Booking ID");
		componentContainer.add(lblBookingID, "cell 1 1,alignx left,aligny center");
		
		textBookingId = new JTextField();
		textBookingId.setEditable(false);
		componentContainer.add(textBookingId, "cell 2 1 2 1,grow");
		textBookingId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		lblName = new JLabel("Name");
		componentContainer.add(lblName, "cell 1 4,alignx left");
		
		textName = new JTextField();
		componentContainer.add(textName, "cell 2 4 2 1,grow");
		textName.setEditable(false);
		textName.setColumns(10);
		
		lblMobile = new JLabel("Mobile");
		componentContainer.add(lblMobile, "cell 1 6,alignx left");
		
		textMobile = new JTextField();
		componentContainer.add(textMobile, "cell 2 6 2 1,grow");
		textMobile.setEditable(false);
		textMobile.setColumns(10);
		
		lblAdults = new JLabel("Adults");
		componentContainer.add(lblAdults, "cell 1 9,alignx left,aligny center");
		
		text_adults = new JTextField();
		componentContainer.add(text_adults, "cell 2 9 2 1,grow");
		text_adults.setColumns(10);
		
		lblChildren = new JLabel("Children");
		componentContainer.add(lblChildren, "cell 1 11,alignx left,aligny center");
		
		text_children = new JTextField();
		componentContainer.add(text_children, "cell 2 11 2 1,grow");
		text_children.setColumns(10);
		
		
		lblAdvance = new JLabel("Advance Amount");
		componentContainer.add(lblAdvance, "cell 1 13,alignx left,aligny center");
		text_advance = new JTextField();
		componentContainer.add(text_advance, "cell 2 13 2 1,grow");
		text_advance.setColumns(10);
		text_advance.setInputVerifier(new DoubleValidator(null, text_advance, "Enter only numeric values"));
		
		

		

		
		btnSave = new JButton("Submit");
		componentContainer.add(btnSave, "cell 2 15,grow");
		btnSave.setMnemonic(KeyEvent.VK_B);
		
		btnClear = new JButton("Clear");
		componentContainer.add(btnClear, "cell 3 15,grow");
		btnSave.addActionListener(this);
		btnClear.addActionListener(this);
		text_advance.addFocusListener(this);
		
	
		
	
		
		GridBagConstraints gbc_text_checkInID = new GridBagConstraints();
		gbc_text_checkInID.gridwidth = 2;
		gbc_text_checkInID.insets = new Insets(0, 0, 5, 0);
		gbc_text_checkInID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_checkInID.gridx = 2;
		gbc_text_checkInID.gridy = 2;
		//sbm_consignCom1 = new SearchBoxModel(text_BookingID, DatabaseConstants.BOOKING_ID_BOOKED, Constants.BOOKED);
		if(bookingID!=null)
		//sbm_consignCom1.cb.setSelectedItem(bookingID);
//		List<String> listIDs = checkin_controller.retrieveBookingIDs(DatabaseConstants.BOOKING_ID_BOOKED, Constants.BOOKED);
//		for(String bookingID : listIDs)
//		{
//			text_BookingID.addItem(bookingID);
//		}

		System.out.println("sib bookingid is"+bookingID);

		

		
		
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
		 
		setBackground(new Color(SetColor.bkColor));
		uplcColor();
		uplmtColor();
		uplbkColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		paybkColor();
		//setClear();
		//text_customerID.addFocusListener(this);
		
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
//		sbm_consignCom1.db.remove(text_BookingID.getSelectedItem());
//		sbm_consignCom1.cb.setSelectedItem("");
		text_advance.setText("");
		text_adults.setText("");
		text_children.setText("");
		textBookingId.setText("");
		textName.setText("");
		textMobile.setText("");
		//text_roomNo.setText("");
	}
	public void setClear1()
	{
		//sbm_consignCom1.cb.setSelectedItem("");
		text_advance.setText("");
		text_adults.setText("");
		text_children.setText("");
		//text_roomNo.setText("");
	}
	public boolean checkBookingInDB(String bookingID)
	{
		boolean b = false;
		if(bookingID.length()>0)
		{
//			for(String item : sbm_consignCom1.db)
//			{
//				if(item.equals(bookingID))
//				{
//					b =  true;
//					break;
//				}
//			}
		}
		return b;		
	}
	@Override
	public void actionPerformed(ActionEvent e) {

				if(e.getSource()==btnSave)
				{		
							//startDate = (java.sql.Date) datePickerArrival.getModel().getValue();
							//endDate = (java.sql.Date) datePickerDeparture.getModel().getValue();
							//bookingDate = (java.sql.Date) datePickerBooking.getModel().getValue();
					
//						if(text_BookingID.getText().trim().length()>0)
//						{
							try{
							double advance = Double.parseDouble(text_advance.getText().trim());
							if(advance > 0 && text_advance.getText().trim().length()<=0)
							{

								text_advance.requestFocus(true);
								text_advance.selectAll();
								new CustomDialog(this, "Enter the Advance Amount >0", "Error", text_advance, 75, 0, CustomDialog.ERROR_ICON);
						     
							}
							else if(text_adults.getText().trim().length()<=0)
							{
								new CustomDialog(this, "Enter the adults", "Error", text_adults, 75, 0, CustomDialog.ERROR_ICON);
								text_adults.requestFocus(true);
							}
							else if(text_children.getText().trim().length()<=0)
							{
								new CustomDialog(this, "Enter the children", "Error", text_children, 75, 0, CustomDialog.ERROR_ICON);
								text_children.requestFocus(true);
							}
							else
							{
										Timestamp timestamp = new Timestamp(System.currentTimeMillis());
										obj_checkIn.setCheckInID(checkin_service.generateCheckInId());	
										obj_checkIn.setBookingID(""+textBookingId.getText());
										obj_checkIn.setCheckinDate((timestamp));
										//obj_checkIn.setCustomerID(text_customerID.getText().trim().toUpperCase());
										obj_checkIn.setTotalAdults(text_adults.getText());
										obj_checkIn.setTotalChilds(text_children.getText());
										obj_checkIn.setAdvanceAmt(""+BigDecimalType.roundDown(advance));
										obj_checkIn.setRoomDoorNumber(roomDoorNumber);
							
					
					
										int s = checkin_controller.submitCheckIn();
										
										if(s>0)
										{
										JOptionPane.showMessageDialog(this,"Checkin Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
										
										textBookingId.requestFocus(true);
										
										sbm_consignCom.db.add(""+textBookingId.getText());
										updateUI();
										setClear();
										

										WelcomeEntry.lblCheckins.setForeground(new Color(50, 197, 210));
										CheckinContainer objCheckin = new CheckinContainer(mpg);
										ViewHandler.updateDashBoard(objCheckin, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
						 
										}
										else
										{
											JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
										}
									
								
								}
								
							}catch(NumberFormatException ee){
								text_advance.requestFocus(true);
								text_advance.selectAll();
								JOptionPane.showMessageDialog(this,"Enter the Advance Amount >0","Failure",JOptionPane.ERROR_MESSAGE);
								}
							catch (Exception ee) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
							}
							
							
//						else
//						{
//							text_BookingID.requestFocus(true);
//							new CustomDialog(this, "Select the Booking ID from the list", "Error", text_BookingID, 75, 0, CustomDialog.ERROR_ICON);
//						}
					
				}
				else if(e.getSource()==btnClear)
				{
					setClear();
				}
				else if(e.getSource()==chckbxNew)
				{
					if(chckbxNew.isSelected())
					{
						setClear1();
						btnSave.setText(Constants.SUBMIT);
						this.remove(searchPanel);					
						//this.add(componentContainer, gbc_componentContainer);
						this.updateUI();
					}
					else
					{
						 
						btnSave.setText(Constants.UPDATE);
						this.remove(componentContainer);					
						//this.add(searchPanel, gbc_componentContainer);
						this.updateUI();
					}
					
				}
				else if(e.getSource() == btnSearch)
				{
//					btnSave.setText(Constants.UPDATE);
//	            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//	            	setData(customer);
//					this.remove(customerSearchPanel);					
//					this.add(componentContainer, gbc_componentContainer);
//					this.updateUI();
				}
		 
					else if(e.getSource()==textBookingId)
					{
						text_adults.requestFocus(true);
					}
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

 
 
		else {}
	}
	@Override
	public void focusLost(FocusEvent arg0) {

//		if(arg0.getSource()==text_BookingID)
//			text_BookingID.setText(text_BookingID.getText().trim().toUpperCase());
		if(arg0.getSource()==text_customerID)
			text_customerID.setText(text_customerID.getText().trim().toUpperCase());		
		


		else{}
 
	}

 
 
 
	public void uplcColor()
	{
		lblBookingID.setForeground(new Color(SetColor.cColor));
		lblCustomerID.setForeground(new Color(SetColor.cColor));
		lblAdvance.setForeground(new Color(SetColor.cColor)); 
		lblChildren.setForeground(new Color(SetColor.cColor));
		lblAdults.setForeground(new Color(SetColor.cColor));

		
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		datePanel.setBackground(new Color(SetColor.bkColor));
		datePanel1.setBackground(new Color(SetColor.bkColor));
		datePanel2.setBackground(new Color(SetColor.bkColor));
	}
	public void uplmtColor()
	{
	}
	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		componentContainer.setBackground(new Color(SetColor.bkColor));
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblCustomerID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingID.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdvance.setFont(new Font(ctFType,ctfProp,ctSize)); 
		lblChildren.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdults.setFont(new Font(ctFType,ctfProp,ctSize));
		
		
		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}



 

 
}
