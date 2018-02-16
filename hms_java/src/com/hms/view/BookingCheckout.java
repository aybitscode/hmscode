package com.hms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.CheckOutController;
import com.hms.model.ReportDetails;
import com.hms.services.CheckOutService;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.SearchBoxModel;
import com.hms.util.SearchCouponModel;
import com.hms.validators.DoubleValidator;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;

public class BookingCheckout extends JPanel implements ActionListener, FocusListener{

	public static JTextField textBookingId;
	public static JTextField textName;
	public static  JTextField textMobile;
	private static JTextField text_booking_date;
	private static JTextField text_checkin_date;
	private static JTextField text_checkout_date;
	private static JTextField text_room_no;
	private static JTextField text_room_type;
	private static JTextField text_days;
	private static JTextField text_room_cost;
	private static JTextField text_facilities_cost;
	public static JTextField text_miscellaneous;
	public static String bookingID;
	public static String roomNumber;
	//private JTextField text_net_total;
	//private JTextField text_gst;
	//private JTextField text_gross_total;
	public static JTextField text_discount;
	private static JTextField text_advance;
	private JLabel lblBookingId;
	private JLabel lblRoomCost;
	private JLabel lblCustomerName;
	private JLabel lblFacilitiesCost;
	private JLabel lblMobile;
	private JLabel lblMiscellaneous;
	private JLabel lblBookingDate;
	//private JLabel lblNetTotal;
	private JLabel lblCheckinDate;
	//private JLabel lblGst;
	private JLabel lblCheckoutDate;
	//private JLabel lblGrossTotal;
	private JLabel lblRoomNo;
	private JLabel lblDiscount;
	private JLabel lblRoomType;
	private JLabel lblAdvance;
	private JLabel lblNoOfDays;
	//private JLabel lblBalance;
	JButton btnSave;
	JButton cancelButton;
	JButton btnPrint;
	
	public static boolean tglbtnTaxValue = false;
	public static String rdbtnValue = null;
	public static CheckOutController checkOut_controller;
	MiscellaneousServices obj_ms = new MiscellaneousServices();
	int days = 0;
	double room_cost = 0;
	double facilities_cost = 0;
	public static double extra_person = 0;
	public static double miscellaneous_cost = 0;
	
	//double total_room_cost = 0;
	public static double booking_cost = 0;	
	
	double booking_cgst = 0;
	double booking_sgst = 0;
	double service_cgst = 0;
	double service_sgst = 0;


	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	public static ReportDetails rptDet = new ReportDetails();
	private JLabel lblPaymentmode;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnCard;
	private JToggleButton tglbtnTax;
	ButtonGroup bg;
	public static JButton btnAdd;
	public static JTextField text_extrabed;
	private JLabel lblExtraPerson;
	public static DefaultTableModel tableModel;
	String filePath;
	public static CheckOutService checkOut_service;
	//CheckoutSearchModel sbm_checkoutSearch;
	SearchBoxModel sbm_consignCom1;
	private JPanel panel;
	public static JComboBox combo_coupon;
	private JLabel lblCoupon;
	public static JTextField text_extrapersons;
	private JLabel lblExtraPersons;
	private SearchCouponModel scm;
	//CheckOutService chs = new CheckOutService();
	//public BookingCheckout(String bookingID, CheckOutService chs) {
	MainPage mpg;
	private JPanel componentContainer;
	private JCheckBox chckbxNew;
	JPanel searchPanel;
	JComboBox comboSearch;
	JLabel lblCustomerMobile;
	JButton btnSearch;
	private JScrollPane scrollPane;
	public BookingCheckout(MainPage mpg) {
		//super(new JFrame(),"Check-out Form",true);
		this.mpg = mpg;
		//this.chs = chs;
		checkOut_service = new CheckOutService();
		
		bg = new ButtonGroup();
 
		setBackground(new Color(SetColor.bkColor));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{298, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		{
			chckbxNew = new JCheckBox("New");
			chckbxNew.setSelected(true);
			GridBagConstraints gbc_chckbxNew = new GridBagConstraints();
			gbc_chckbxNew.anchor = GridBagConstraints.NORTH;
			gbc_chckbxNew.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxNew.gridx = 1;
			gbc_chckbxNew.gridy = 2;
			//add(chckbxNew, gbc_chckbxNew);
			chckbxNew.addActionListener(this);
			chckbxNew.setSelected(true);
		}
		{
			scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setPreferredSize(new Dimension(MainPage.scrwidth-100, MainPage.scrheight-150));
			scrollPane.getVerticalScrollBar().setUnitIncrement(16);
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 0;
			add(scrollPane, gbc_scrollPane);
			//add(customerSearchPanel, gbc_customerContainer);
			{
				componentContainer = new JPanel();
				scrollPane.setViewportView(componentContainer);
				componentContainer.setLayout(new MigLayout("", "[grow][0][300][50][0][150][150][grow]", "[grow][35][10][35][10][35][10][35][10][35][10][35][10][35][10][35][10][35][10][35][grow]"));
				componentContainer.setBackground(Color.white);
				
						{
							lblBookingId = new JLabel("Booking ID");
							componentContainer.add(lblBookingId, "cell 1 1,grow");
						}
						textBookingId = new JTextField();
						componentContainer.add(textBookingId, "cell 2 1,grow");
						textBookingId.setEditable(false);
						//text_bookingID.setEditable(true);
						//sbm_checkoutSearch = new CheckoutSearchModel(text_bookingID, DatabaseConstants.CHECKOUT_BOOKING_ID, Constants.CHECKIN, this);
						{
							lblFacilitiesCost = new JLabel("Facilities Cost");
							componentContainer.add(lblFacilitiesCost, "cell 4 1,grow");
						}
						{
							text_facilities_cost = new JTextField();
							componentContainer.add(text_facilities_cost, "cell 5 1 2 1,grow");
							text_facilities_cost.setEditable(false);
							text_facilities_cost.setColumns(10);

						}
						
						{
							lblCustomerName = new JLabel("Customer Name");
							componentContainer.add(lblCustomerName, "cell 1 3,grow");
						}
						{
							textName = new JTextField();
							componentContainer.add(textName, "cell 2 3,grow");
							textName.setEditable(false);
							textName.setColumns(10);
							
						}
						{
							lblAdvance = new JLabel("Advance Paid");
							componentContainer.add(lblAdvance, "cell 4 3,grow");
						}
						{
							text_advance = new JTextField();
							componentContainer.add(text_advance, "cell 5 3 2 1,grow");
							text_advance.setEditable(false);
							text_advance.setColumns(10);
							
						}
						{
							lblMobile = new JLabel("Mobile Number");
							componentContainer.add(lblMobile, "cell 1 5,grow");
						}
						{
							textMobile = new JTextField();
							componentContainer.add(textMobile, "cell 2 5,grow");
							textMobile.setEditable(false);
							textMobile.setColumns(10);
							
						}
						{
							lblExtraPersons = new JLabel("Extra Persons");
							componentContainer.add(lblExtraPersons, "cell 4 5,alignx left,aligny center");
						}
						{
							text_extrapersons = new JTextField();
							componentContainer.add(text_extrapersons, "cell 5 5 2 1,grow");
							text_extrapersons.setColumns(10);
							text_extrapersons.setEditable(false);
						}
						{
							lblBookingDate = new JLabel("Booking Date");
							componentContainer.add(lblBookingDate, "cell 1 7,grow");
						}
						{
							text_booking_date = new JTextField();
							componentContainer.add(text_booking_date, "cell 2 7,grow");
							text_booking_date.setEditable(false);
							text_booking_date.setColumns(10);
							
						}
						{
							lblExtraPerson = new JLabel("Charges");
							componentContainer.add(lblExtraPerson, "cell 4 7,grow");
						}
						{
							text_extrabed = new JTextField();
							componentContainer.add(text_extrabed, "cell 5 7 2 1,grow");
							text_extrabed.setColumns(10);
							text_extrabed.setInputVerifier(new DoubleValidator(null, text_extrabed, "Enter only numeric values > 0"));
							text_extrabed.getDocument().addDocumentListener(new DocumentListener(){				
								@Override
								public void changedUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub
								}

								@Override
								public void insertUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub

								}

								@Override
								public void removeUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub
									
									
								}				
							});
						}
						{
							lblCheckinDate = new JLabel("Check-In Date");
							componentContainer.add(lblCheckinDate, "cell 1 9,grow");
						}
						{
							text_checkin_date = new JTextField();
							componentContainer.add(text_checkin_date, "cell 2 9,grow");
							text_checkin_date.setEditable(false);
							text_checkin_date.setColumns(10);
							
						}
						
						
						
								{
									lblMiscellaneous = new JLabel("Miscellaneous");
									componentContainer.add(lblMiscellaneous, "cell 4 9,grow");
								}
						{
							text_miscellaneous = new JTextField();
							componentContainer.add(text_miscellaneous, "cell 5 9,grow");
							text_miscellaneous.setEditable(false);
							text_miscellaneous.setColumns(10);
							text_miscellaneous.setInputVerifier(new DoubleValidator(null, text_miscellaneous, "Enter only numeric values > 0"));
							text_miscellaneous.getDocument().addDocumentListener(new DocumentListener(){
								
								@Override
								public void changedUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub
								}

								@Override
								public void insertUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub
									
//					days = Integer.parseInt(text_days.getText());
//					room_cost = Double.parseDouble(text_room_cost.getText());
//					facilities_cost = Double.parseDouble(text_facilities_cost.getText());
//					extra_person = Double.parseDouble(text_extrabed.getText());
//					miscellaneous_cost = Double.parseDouble(text_miscellaneous.getText());
//					
//					total_room_cost = days*(room_cost+facilities_cost);
//					booking_cost = total_room_cost + extra_person;	
									
								}

								@Override
								public void removeUpdate(DocumentEvent arg0) {
									// TODO Auto-generated method stub
									
									
								}

								
							});
						}
						{
							lblCheckoutDate = new JLabel("Check-Out Date");
							componentContainer.add(lblCheckoutDate, "cell 1 11,grow");
						}
						{
							text_checkout_date = new JTextField();
							componentContainer.add(text_checkout_date, "cell 2 11,grow");
							text_checkout_date.setEditable(false);
							text_checkout_date.setColumns(10);
							
						}
						
								{
									lblCoupon = new JLabel("Coupon");
									componentContainer.add(lblCoupon, "cell 4 11,alignx left,aligny center");
								}
						combo_coupon = new JComboBox();
						componentContainer.add(combo_coupon, "cell 5 11 2 1,grow");
						combo_coupon.setEditable(true);
						scm = new SearchCouponModel(combo_coupon, DatabaseConstants.COUPON_TYPE, Constants.COUPON_TYPE_GENERAL);
						combo_coupon.setModel(scm);
						
						combo_coupon.addItemListener(scm);
						combo_coupon.addPopupMenuListener(scm);
						
						
						
						
						
								{
									btnAdd = new JButton("+");
									componentContainer.add(btnAdd, "cell 6 9,grow");
									btnAdd.addActionListener(this);
								}
						{
							lblRoomNo = new JLabel("Room No.");
							componentContainer.add(lblRoomNo, "cell 1 13,grow");
						}
						{
							text_room_no = new JTextField();
							componentContainer.add(text_room_no, "cell 2 13,grow");
							text_room_no.setEditable(false);
							text_room_no.setColumns(10);
							
						}
						
								{
									lblDiscount = new JLabel("Discount");
									componentContainer.add(lblDiscount, "cell 4 13,grow");
								}
						{
							text_discount = new JTextField();
							componentContainer.add(text_discount, "cell 5 13 2 1,grow");
							text_discount.setEditable(false);
							text_discount.setColumns(10);
							
							
						}
						{
							lblRoomType = new JLabel("Room Type");
							componentContainer.add(lblRoomType, "cell 1 15,grow");
						}
						{
							text_room_type = new JTextField();
							componentContainer.add(text_room_type, "cell 2 15,grow");
							text_room_type.setEditable(false);
							text_room_type.setColumns(10);
							
						}
								{
									lblPaymentmode = new JLabel("Payment Mode");
									componentContainer.add(lblPaymentmode, "cell 4 15,grow");
								}
								{
									rdbtnCash = new JRadioButton("CASH");
									componentContainer.add(rdbtnCash, "cell 5 15,grow");
								}
								
			 
				bg.add(rdbtnCash);
				rdbtnCash.addActionListener(this);
								
								{
									tglbtnTax = new JToggleButton("No Tax");
									componentContainer.add(tglbtnTax, "cell 6 15,grow");
									tglbtnTax.setCursor(new Cursor(Cursor.HAND_CURSOR));

								}
								
								tglbtnTax.setVisible(false);
								tglbtnTax.addActionListener(this);
								{
									rdbtnCard = new JRadioButton("CARD");
									componentContainer.add(rdbtnCard, "cell 6 15,grow");
								}
								{
									bg.add(rdbtnCard);
									rdbtnCard.addActionListener(this);
								}
						
								{
									lblNoOfDays = new JLabel("No. of Days");
									componentContainer.add(lblNoOfDays, "cell 1 17,grow");
								}
								{
									text_days = new JTextField();
									componentContainer.add(text_days, "cell 2 17,grow");
									text_days.setEditable(false);
									text_days.setColumns(10);
										
								}
										
														btnSave = new JButton("Checkout");
														componentContainer.add(btnSave, "cell 5 17,grow");
														btnSave.addActionListener(this);
										
										
										cancelButton = new JButton("Clear");
										componentContainer.add(cancelButton, "cell 6 17,grow");
										cancelButton.addActionListener(this);
								
										{
											lblRoomCost = new JLabel("Room Cost");
											componentContainer.add(lblRoomCost, "cell 1 19,grow");
										}
										{
											text_room_cost = new JTextField();
											componentContainer.add(text_room_cost, "cell 2 19,grow");
											text_room_cost.setEditable(false);
											text_room_cost.setColumns(10);
											
										}
										
										//		{
										//			lblNetTotal = new JLabel("Net Total");
										//			GridBagConstraints gbc_lblNetTotal = new GridBagConstraints();
										//			gbc_lblNetTotal.fill = GridBagConstraints.BOTH;
										//			gbc_lblNetTotal.insets = new Insets(0, 0, 5, 5);
										//			gbc_lblNetTotal.gridx = 4;
										//			gbc_lblNetTotal.gridy = 6;
										//			//add(lblNetTotal, gbc_lblNetTotal);
										//		}
										//		{
										//			text_net_total = new JTextField();
										//			text_net_total.setEditable(false);
										//			GridBagConstraints gbc_text_net_total = new GridBagConstraints();
										//			gbc_text_net_total.gridwidth = 2;
										//			gbc_text_net_total.insets = new Insets(0, 0, 5, 5);
										//			gbc_text_net_total.fill = GridBagConstraints.HORIZONTAL;
										//			gbc_text_net_total.gridx = 5;
										//			gbc_text_net_total.gridy = 6;
										//			//add(text_net_total, gbc_text_net_total);
										//			text_net_total.setColumns(10);
										//			//text_net_total.setText(rptDet.getNet_amount());
										//		}
										
										//		{
										//			lblGst = new JLabel("GST ");
										//			GridBagConstraints gbc_lblGst = new GridBagConstraints();
										//			gbc_lblGst.fill = GridBagConstraints.BOTH;
										//			gbc_lblGst.insets = new Insets(0, 0, 5, 5);
										//			gbc_lblGst.gridx = 4;
										//			gbc_lblGst.gridy = 7;
										//			//add(lblGst, gbc_lblGst);
										//		}
										//		{
										//			text_gst = new JTextField();
										//			text_gst.setEditable(false);
										//			GridBagConstraints gbc_text_gst = new GridBagConstraints();
										//			gbc_text_gst.gridwidth = 2;
										//			gbc_text_gst.insets = new Insets(0, 0, 5, 5);
										//			gbc_text_gst.fill = GridBagConstraints.HORIZONTAL;
										//			gbc_text_gst.gridx = 5;
										//			gbc_text_gst.gridy = 7;
										//			//add(text_gst, gbc_text_gst);
										//			text_gst.setColumns(10);
										//			//text_gst.setText(rptDet.getGstValue());
										//		}
										
										//		{
										//			lblGrossTotal = new JLabel("Gross Total");
										//			GridBagConstraints gbc_lblGrossTotal = new GridBagConstraints();
										//			gbc_lblGrossTotal.fill = GridBagConstraints.BOTH;
										//			gbc_lblGrossTotal.insets = new Insets(0, 0, 5, 5);
										//			gbc_lblGrossTotal.gridx = 4;
										//			gbc_lblGrossTotal.gridy = 8;
										//			//add(lblGrossTotal, gbc_lblGrossTotal);
										//		}
										//		{
										//			text_gross_total = new JTextField();
										//			text_gross_total.setEditable(false);
										//			GridBagConstraints gbc_text_gross_total = new GridBagConstraints();
										//			gbc_text_gross_total.gridwidth = 2;
										//			gbc_text_gross_total.insets = new Insets(0, 0, 5, 5);
										//			gbc_text_gross_total.fill = GridBagConstraints.HORIZONTAL;
										//			gbc_text_gross_total.gridx = 5;
										//			gbc_text_gross_total.gridy = 8;
										//			//add(text_gross_total, gbc_text_gross_total);
										//			text_gross_total.setColumns(10);
										//			//text_gross_total.setText(rptDet.getGross_amount());
										//		}
										
										
										
										
										//		{
										//			lblBalance = new JLabel("Balance");
										//			GridBagConstraints gbc_lblBalance = new GridBagConstraints();
										//			gbc_lblBalance.fill = GridBagConstraints.BOTH;
										//			gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
										//			gbc_lblBalance.gridx = 4;
										//			gbc_lblBalance.gridy = 11;
										//			//add(lblBalance, gbc_lblBalance);
										//		}
										//		{
										//			text_balance = new JTextField();
										//			text_balance.setEditable(false);
										//			GridBagConstraints gbc_text_balance = new GridBagConstraints();
										//			gbc_text_balance.gridwidth = 2;
										//			gbc_text_balance.insets = new Insets(0, 0, 5, 5);
										//			gbc_text_balance.fill = GridBagConstraints.HORIZONTAL;
										//			gbc_text_balance.gridx = 5;
										//			gbc_text_balance.gridy = 11;
										//			//add(text_balance, gbc_text_balance);
										//			text_balance.setColumns(10);
										//			double advance_discount = Double.parseDouble(rptDet.getAdvance());
										//			double discount = checkOut_controller.getCouponDiscount(rptDet.getCouponName());
										//			//advance_discount = advance_discount + discount;
										//			//double balanceAmount = Double.parseDouble(rptDet.getGross_amount()) - advance_discount;
										//			//text_balance.setText(""+BigDecimalType.roundDown(balanceAmount));
										//		}
										
												{
														
														btnPrint = new JButton("Print");
														componentContainer.add(btnPrint, "cell 5 19 2 1,grow");
														btnPrint.addActionListener(this);
														btnPrint.setEnabled(false);
														
										
													
												}
			}
		}
		searchPanel = new JPanel();
		searchPanel.setLayout(new MigLayout("", "[79px][300px]", "[35px][35px]"));
		searchPanel.setBackground(new Color(SetColor.bkColor));
		
		
		lblCustomerMobile = new JLabel("Customer Mobile");
		searchPanel.add(lblCustomerMobile, "cell 0 0,alignx right,growy");
		
		comboSearch = new JComboBox();
		comboSearch.setEditable(true);
		sbm_consignCom1 = new SearchBoxModel(comboSearch,  DatabaseConstants.SELECT_CHECKIN_BOOKING_ID);
		comboSearch.setModel(sbm_consignCom1);
		comboSearch.addItemListener(sbm_consignCom1);
		comboSearch.addPopupMenuListener(sbm_consignCom1); 
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
		{
//			if(bookingID!=null)
//				sbm_checkoutSearch.cb.setSelectedItem(bookingID);
//			text_bookingID.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
//
//			    @Override
//			    public void keyReleased(KeyEvent event) {
//			        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
//			            if (((JTextComponent) ((JComboBox) ((Component) event
//			                    .getSource()).getParent()).getEditor()
//			                    .getEditorComponent()).getText().isEmpty())
//			            {
//			            	checkOut_service.retrieveCheckOutDetails();
//			            }
//			            else
//			            {		            	
//			        		
//			        		rptDet = checkOut_service.retrieveCheckOutDetails(""+text_bookingID.getSelectedItem());
//			        		checkOut_controller = new CheckOutController(rptDet);
//			        		setData(rptDet);
//			        		
//			            }
//			            	
//			        }
//			    }
//			});
			//text_bookingID.setText(bookingID);
		}
		{
			
			if(seasonCoupon()!=null)
				scm.db.add(seasonCoupon());
		}


		paybkColor();
		uplmtColor();
		uplcColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public static void setData(ReportDetails rptDet)
	{
		text_facilities_cost.setText(rptDet.getFacilitiesCost());
		text_room_cost.setText(rptDet.getRoomCost());
		textName.setText(rptDet.getCustomerName());
		textMobile.setText(rptDet.getMobileNumber());
		text_booking_date.setText(""+rptDet.getBookedDate());
		text_checkin_date.setText(""+rptDet.getCheckinDate());
		text_checkout_date.setText(""+rptDet.getCheckoutDate());
		text_room_no.setText(rptDet.getRoomNo());
		//text_discount.setText(rptDet.getDiscount());
		text_room_type.setText(rptDet.getRoomType());
		text_advance.setText(rptDet.getAdvancePaid());  
		Date current_date = new Date();
		Date checkin_date = null;
		try {
			checkin_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rptDet.getCheckinDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		int days = Integer.parseInt(DateDifferenceCalculator.calculateTimePeriod(checkin_date, current_date));
//		if(days == 0)
//			days = 1;  Temporarily ignored
		text_days.setText(rptDet.getDays());
		rptDet.setDays(rptDet.getDays());
		
	}
	private String seasonCoupon() {
		Connection con = DBConnection.getDBConnection();
		String couponName = null;
		
		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			ResultSet rk=stmt.executeQuery(DatabaseConstants.SEASONS_COUPONS);
			java.util.Date currentDate = new java.util.Date();
			while(rk.next())
			{
				java.util.Date startDate = new java.util.Date(rk.getDate(1).getTime());
				java.util.Date endDate = new java.util.Date(rk.getDate(2).getTime());
				if(startDate.compareTo(currentDate) * currentDate.compareTo(endDate) > 0)
				{
					couponName = rk.getString(3);
					//coupon_discount = Double.parseDouble(rk.getString(4));
				}
			}
			//System.out.println("coupon name is"+couponName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			return couponName;	
	}
	

	
	public void setClear()
	{
		textBookingId.setText("");
		text_extrapersons.setText("");
		text_extrabed.setText("");
		text_facilities_cost.setText("");
		text_room_cost.setText("");
		textName.setText("");
		textMobile.setText("");
		text_booking_date.setText("");
		text_checkin_date.setText("");
		text_checkout_date.setText("");
		text_room_no.setText("");
		text_discount.setText("");
		text_room_type.setText("");
		text_advance.setText("");
		text_days.setText("");
		//sbm_checkoutSearch.cb.setSelectedItem("");
		scm.cb.setSelectedItem("NO_DISCOUNT");
		bg.clearSelection();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSave)
		{	
			int s=0;
			if(rdbtnValue != null)
			{
						   
				
				if(text_extrabed.getInputVerifier().verify(text_extrabed))
				 {				
					
						   try{	
							   if(text_miscellaneous.getText().trim().length()==0)
								   miscellaneous_cost = 0;
							   else
								   miscellaneous_cost = Double.parseDouble(text_miscellaneous.getText());
							   if(text_days.getText().length() > 5)
								   days = 1;
							   else
								   days = Integer.parseInt(text_days.getText());
								room_cost = Double.parseDouble(text_room_cost.getText());
								facilities_cost = Double.parseDouble(text_facilities_cost.getText());
								extra_person = Double.parseDouble(text_extrabed.getText());
								
								
								//booking_cost = days*(room_cost+facilities_cost+extra_person);
								booking_cost = room_cost+facilities_cost+extra_person;
							   
								if(checkCouponInDB(""+combo_coupon.getSelectedItem()))
								{ 
									s = checkOut_controller.submitService();
								}
								else
								{
									combo_coupon.requestFocus(true);
									JOptionPane.showMessageDialog(this,"Select coupon from the list","Failure",JOptionPane.ERROR_MESSAGE);
								}
							
								if(s>0)
								{
					
								rdbtnValue = null;
								tglbtnTaxValue = false;								
								//checkOut_service.retrieveCheckOutDetails();
						 							
								btnPrint.setEnabled(true);
								btnSave.setEnabled(false);
								cancelButton.setEnabled(false);
								setClear();
								setClear();
								//sbm_checkoutSearch.db.remove(text_bookingID.getSelectedItem());

								updateUI();
								}
								else
								{
									JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
								}
							
								}catch(NumberFormatException ee){
									textBookingId.requestFocus(true);
									System.out.println(ee);
									JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);
								}catch (Exception ee) {
									// TODO Auto-generated catch block
									//JOptionPane.showMessageDialog(this,"Enter the details correctly","Failure",JOptionPane.ERROR_MESSAGE);
									JOptionPane.showMessageDialog(this,ee,"Failure",JOptionPane.ERROR_MESSAGE);
								}
							 }
							
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Select the payment mode","Failure",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(e.getSource()==btnPrint)
		{
			checkOut_controller.generateReport(mpg);	
			CheckoutContainer objCheckin = new CheckoutContainer(mpg);
			ViewHandler.updateDashBoard(objCheckin, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			obj_ms.dispose();
		}
		else if(e.getSource()==btnAdd)
		{
			obj_ms.text_roomFacilitiesName.requestFocus(true);
			obj_ms.setVisible(true);			
		}
		else if(e.getSource()==cancelButton)
		{
			setClear();
			setClear();
		}
		else if(e.getSource()==rdbtnCash)
		{
			if(MainPage.user_role.equals(Constants.ADMIN)&&MainPage.user_mode.equals(Constants.MODE_OFF))
				tglbtnTax.setVisible(true);
			else if(MainPage.user_role.equals(Constants.USER)&&MainPage.user_mode.equals(Constants.MODE_OFF))
				tglbtnTax.setVisible(true);
			else
				tglbtnTax.setVisible(false);
			
			rdbtnValue = Constants.CASH;
		}
		else if(e.getSource()==rdbtnCard)
		{
			tglbtnTax.setVisible(false);
			rdbtnValue = Constants.CARD;
		}
		else if(e.getSource()==tglbtnTax)
		{
			if(tglbtnTax.isSelected())
			{
				tglbtnTaxValue = true;
			}
			else
			{
				tglbtnTaxValue = false;
			}
		}
		else if(e.getSource()==chckbxNew)
		{
			if(chckbxNew.isSelected())
			{
				setClear();
				btnSave.setText(Constants.SUBMIT);
				this.remove(searchPanel);					
				//this.add(componentContainer, gbc_componentContainer);
				this.updateUI();
			}
			else
			{
				 
				btnSave.setText(Constants.UPDATE);
				this.remove(componentContainer);					
				//his.add(searchPanel, gbc_componentContainer);
				this.updateUI();
			}
			
		}
		else if(e.getSource() == btnSearch)
		{
//			btnSave.setText(Constants.UPDATE);
//        	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//        	setData(customer);
//			this.remove(customerSearchPanel);					
//			this.add(componentContainer, gbc_componentContainer);
//			this.updateUI();
		}
		
	}
	
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor)); 
		
	}
	public void uplmtColor()
	{
	}
	public void uplcColor()
	{
		lblBookingId.setForeground(new Color(SetColor.cColor));
		lblRoomCost.setForeground(new Color(SetColor.cColor));
		lblCustomerName.setForeground(new Color(SetColor.cColor));
		lblFacilitiesCost.setForeground(new Color(SetColor.cColor));
		lblMobile.setForeground(new Color(SetColor.cColor));
		lblMiscellaneous.setForeground(new Color(SetColor.cColor));
		lblBookingDate.setForeground(new Color(SetColor.cColor));
		//lblNetTotal.setForeground(new Color(SetColor.cColor));
		lblCheckinDate.setForeground(new Color(SetColor.cColor));
		//lblGst.setForeground(new Color(SetColor.cColor));
		lblCheckoutDate.setForeground(new Color(SetColor.cColor));
		//lblGrossTotal.setForeground(new Color(SetColor.cColor));
		lblRoomNo.setForeground(new Color(SetColor.cColor));
		lblDiscount.setForeground(new Color(SetColor.cColor));
		lblRoomType.setForeground(new Color(SetColor.cColor));
		lblAdvance.setForeground(new Color(SetColor.cColor));
		lblNoOfDays.setForeground(new Color(SetColor.cColor));
		//lblBalance.setForeground(new Color(SetColor.cColor));
		lblPaymentmode.setForeground(new Color(SetColor.cColor));
		rdbtnCard.setForeground(new Color(SetColor.cColor));
		rdbtnCash.setForeground(new Color(SetColor.cColor));
		lblExtraPerson.setForeground(new Color(SetColor.cColor));
		lblExtraPersons.setForeground(new Color(SetColor.cColor));
		lblCoupon.setForeground(new Color(SetColor.cColor));
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblBookingId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCost.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCustomerName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFacilitiesCost.setFont(new Font(ctFType,ctfProp,ctSize));
		lblMobile.setFont(new Font(ctFType,ctfProp,ctSize));
		lblMiscellaneous.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingDate.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblNetTotal.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckinDate.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblGst.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckoutDate.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblGrossTotal.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomNo.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDiscount.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomType.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdvance.setFont(new Font(ctFType,ctfProp,ctSize));
		lblNoOfDays.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblBalance.setFont(new Font(ctFType,ctfProp,ctSize));
		lblPaymentmode.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnCash.setFont(new Font(ctFType,ctfProp,ctSize));
		rdbtnCard.setFont(new Font(ctFType,ctfProp,ctSize));
		lblExtraPerson.setFont(new Font(ctFType,ctfProp,ctSize));
		lblExtraPersons.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCoupon.setFont(new Font(ctFType,ctfProp,ctSize));
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public boolean checkCouponInDB(String coupon)
	{
		boolean b = false;
		if(coupon.length()>0)
		{
			for(String item : scm.db)
			{
				if(item.equals(coupon))
				{
					b =  true;
					rptDet.setCouponName(coupon);
					rptDet.setDiscount(text_discount.getText());
					break;
				}
			}
		}
		return b;		
	}

	

}
