package com.hms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import net.miginfocom.swing.MigLayout;

import com.hms.controller.BookingCancelController;
import com.hms.model.Booking;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.SearchBoxModel;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;
import com.hotelmanagement.WelcomeEntry;


public class BookingCancel extends JPanel implements ActionListener{

	
	public static JTextField textBookingId;
	private JTextField text_customer_name;
	private JTextField text_mobile;
	private JTextField text_booking_date;
	private JTextField text_checkin_date;
	private JTextField text_checkout_date;
	private JTextField textRoomNo;
	private JTextField text_room_type;
	private JTextField text_days;
	private JTextField text_room_cost;
	private JTextField text_facilities_cost;
	private JTextField text_miscellaneous;
	private JTextField text_net_total;
	private JTextField text_gst;
	private JTextField text_gross_total;
	private JTextField text_discount;
	private JTextField text_advance;
	private JTextField text_balance;
	private JLabel lblBookingId;
	private JLabel lblRoomCost;
	private JLabel lblCustomerName;
	private JLabel lblFacilitiesCost;
	private JLabel lblMobile;
	private JLabel lblMiscellaneous;
	private JLabel lblBookingDate;
	private JLabel lblNetTotal;
	private JLabel lblCheckinDate;
	private JLabel lblGst;
	private JLabel lblCheckoutDate;
	private JLabel lblGrossTotal;
	private JLabel lblRoomNo;
	private JLabel lblDiscount;
	private JLabel lblRoomType;
	private JLabel lblAdvance;
	private JLabel lblNoOfDays;
	private JLabel lblBalance;
	JButton btnSave; 
	public static boolean tglbtnTaxValue = false;
	public static String rdbtnValue = null;
	//private static CancelSearchModel sbm_consignCom; 
	public static String bookingID;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			BookigCheckout dialog = new BookigCheckout();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	//public BookingCancelService bcs;
	//BookingCancelController obj_bcc;
	//public BookingCancelController obj_bcc1;
	//public Booking obj_booking = new Booking();
	private JLabel lblPaymentmode;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnCard;
	ButtonGroup bg;
	//public BookingCancel(String bookingID, CheckOutService chs) {
	TitledBorder border;
	private JPanel componentContainer;
	private JCheckBox checkBox;
	JPanel searchPanel;
	JComboBox comboSearch;
	JLabel lblCustomerMobile;
	JButton btnSearch;
	SearchBoxModel sbm_consignCom1;
	private JScrollPane scrollPane;
	MainPage mpg;
	public BookingCancel(MainPage mpg) {
		this.mpg = mpg;
		bg = new ButtonGroup();
    	//bcs = new BookingCancelService(obj_booking);
    	


		{
			
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0, 0};
			gridBagLayout.rowHeights = new int[]{0};
			gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{1.0};
			setLayout(gridBagLayout);
			{
				checkBox = new JCheckBox("New Checkin");
				checkBox.setSelected(true);
				GridBagConstraints gbc_checkBox = new GridBagConstraints();
				gbc_checkBox.insets = new Insets(0, 0, 5, 5);
				gbc_checkBox.gridx = 1;
				gbc_checkBox.gridy = 2;
				//add(checkBox, gbc_checkBox);
				checkBox.addActionListener(this);
				checkBox.setSelected(true);
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
//			            	Customer customer = customer_controller.populateCustomerForm(""+comboSearch.getSelectedItem());
//			            	setData(customer);
//							remove(customerSearchPanel);					
//							add(componentContainer, gbc_componentContainer);
//							updateUI();
			            }
			            	
			        }
			    }
			});
			
			btnSearch = new JButton("Search");
			searchPanel.add(btnSearch, "cell 1 1,alignx center,growy"); 
			btnSearch.addActionListener(this);
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
					componentContainer.setLayout(new MigLayout("", "[grow][0][300][grow]", "[35][][35][][35][][35][][35][10][35][35][35][35][35][35][23px]"));
					componentContainer.setBackground(Color.white);
					{
						lblBookingId = new JLabel("Booking ID");
						componentContainer.add(lblBookingId, "cell 1 0,grow");
					}
					textBookingId = new JTextField();
					textBookingId.setEditable(false);
					componentContainer.add(textBookingId, "cell 2 0,grow");
					//sbm_consignCom = new CancelSearchModel(comboBookingId, DatabaseConstants.BOOKING_ID_BOOKED, Constants.BOOKED, this);
					
									{
										lblCustomerName = new JLabel("Customer Name");
										componentContainer.add(lblCustomerName, "cell 1 2,grow");
									}
									{
										text_customer_name = new JTextField();
										text_customer_name.setEditable(false);
										componentContainer.add(text_customer_name, "cell 2 2,grow");
										text_customer_name.setColumns(10);
										//text_customer_name.setText(obj_booking.getCustomerName());
									}
									
													{
														lblMobile = new JLabel("Mobile Number");
														componentContainer.add(lblMobile, "cell 1 4,grow");
													}
													{
														text_mobile = new JTextField();
														componentContainer.add(text_mobile, "cell 2 4,grow");
														text_mobile.setEditable(false);
														text_mobile.setColumns(10);
														//text_mobile.setText(obj_booking.getBooking_customer_mobile());
													}
													{
														lblBookingDate = new JLabel("Booking Date");
														componentContainer.add(lblBookingDate, "cell 1 6,grow");
													}
													{
														text_booking_date = new JTextField();
														componentContainer.add(text_booking_date, "cell 2 6,grow");
														text_booking_date.setEditable(false);
														text_booking_date.setColumns(10);
														//text_booking_date.setText(""+obj_booking.getBookingDate());
													}
													{
														lblCheckinDate = new JLabel("Check-In Date");
														componentContainer.add(lblCheckinDate, "cell 1 8,grow");
													}
													{
														text_checkin_date = new JTextField();
														componentContainer.add(text_checkin_date, "cell 2 8,grow");
														text_checkin_date.setEditable(false);
														text_checkin_date.setColumns(10);
														//text_checkin_date.setText(""+obj_booking.getBookedDate());
													}
													{
														lblCheckoutDate = new JLabel("Check-Out Date");
														componentContainer.add(lblCheckoutDate, "cell 1 10,grow");
													}
													{
														text_checkout_date = new JTextField();
														componentContainer.add(text_checkout_date, "cell 2 10,grow");
														text_checkout_date.setEditable(false);
														text_checkout_date.setColumns(10);
														//text_checkout_date.setText(""+obj_booking.getCheckoutDate());
													}
													{
														lblRoomNo = new JLabel("Room No.");
														componentContainer.add(lblRoomNo, "cell 1 11,grow");
													}
													{
														textRoomNo = new JTextField();
														componentContainer.add(textRoomNo, "cell 2 11,grow");
														textRoomNo.setEditable(false);
														textRoomNo.setColumns(10);
														//text_room_no.setText(obj_booking.getBooking_room_door_number());
													}
													{
														lblRoomType = new JLabel("Room Type");
														componentContainer.add(lblRoomType, "cell 1 12,grow");
													}
													{
														text_room_type = new JTextField();
														componentContainer.add(text_room_type, "cell 2 12,grow");
														text_room_type.setEditable(false);
														text_room_type.setColumns(10);
														//text_room_type.setText(obj_booking.getBooking_room_category());
													}
													{
														lblNoOfDays = new JLabel("No. of Days");
														componentContainer.add(lblNoOfDays, "cell 1 13,grow");
													}
													{
														text_days = new JTextField();
														componentContainer.add(text_days, "cell 2 13,grow");
														text_days.setEditable(false);
														text_days.setColumns(10);
														//text_days.setText(obj_booking.getBooking_total_nights());
													}
													{
														lblRoomCost = new JLabel("Room Cost");
														componentContainer.add(lblRoomCost, "cell 1 14,grow");
													}
													{
														text_room_cost = new JTextField();
														componentContainer.add(text_room_cost, "cell 2 14,grow");
														text_room_cost.setEditable(false);
														text_room_cost.setColumns(10);
														//text_room_cost.setText(obj_booking.getRoomCost());
													}
													{
														lblFacilitiesCost = new JLabel("Facilities Cost");
														componentContainer.add(lblFacilitiesCost, "cell 1 15,grow");
													}
													{
														text_facilities_cost = new JTextField();
														componentContainer.add(text_facilities_cost, "cell 2 15,grow");
														text_facilities_cost.setEditable(false);
														text_facilities_cost.setColumns(10);
														//text_facilities_cost.setText(obj_booking.getFacilitiesCost());
													}
													btnSave = new JButton("Cancel Booking");
													componentContainer.add(btnSave, "cell 2 16,grow");
													btnSave.addActionListener(this);
				}
			}
			{
//				if(bookingID!=null)
//					sbm_consignCom.cb.setSelectedItem(bookingID);
	 
				{
					lblMiscellaneous = new JLabel("Miscellaneous");
					GridBagConstraints gbc_lblMiscellaneous = new GridBagConstraints();
					gbc_lblMiscellaneous.fill = GridBagConstraints.BOTH;
					gbc_lblMiscellaneous.insets = new Insets(0, 0, 5, 5);
					gbc_lblMiscellaneous.gridx = 3;
					gbc_lblMiscellaneous.gridy = 4;
					//add(lblMiscellaneous, gbc_lblMiscellaneous);
				}
				{
//					text_miscellaneous = new JTextField();
//					GridBagConstraints gbc_text_miscellaneous = new GridBagConstraints();
//					gbc_text_miscellaneous.fill = GridBagConstraints.HORIZONTAL;
//					gbc_text_miscellaneous.insets = new Insets(0, 0, 5, 0);
//					gbc_text_miscellaneous.gridx = 4;
//					gbc_text_miscellaneous.gridy = 4;
//					//add(text_miscellaneous, gbc_text_miscellaneous);
//					text_miscellaneous.setEditable(false);
//					text_miscellaneous.setColumns(10);
				}
				{
					lblNetTotal = new JLabel("Net Total");
					GridBagConstraints gbc_lblNetTotal = new GridBagConstraints();
					gbc_lblNetTotal.fill = GridBagConstraints.BOTH;
					gbc_lblNetTotal.insets = new Insets(0, 0, 5, 5);
					gbc_lblNetTotal.gridx = 3;
					gbc_lblNetTotal.gridy = 5;
					//add(lblNetTotal, gbc_lblNetTotal);
				}
				{
					text_net_total = new JTextField();
					GridBagConstraints gbc_text_net_total = new GridBagConstraints();
					gbc_text_net_total.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_net_total.insets = new Insets(0, 0, 5, 0);
					gbc_text_net_total.gridx = 4;
					gbc_text_net_total.gridy = 5;
					//add(text_net_total, gbc_text_net_total);
					text_net_total.setEditable(false);
					text_net_total.setColumns(10);

				}
				{
					lblGst = new JLabel("GST ");
					GridBagConstraints gbc_lblGst = new GridBagConstraints();
					gbc_lblGst.fill = GridBagConstraints.BOTH;
					gbc_lblGst.insets = new Insets(0, 0, 5, 5);
					gbc_lblGst.gridx = 3;
					gbc_lblGst.gridy = 6;
					//add(lblGst, gbc_lblGst);
				}
				{
					text_gst = new JTextField();
					GridBagConstraints gbc_text_gst = new GridBagConstraints();
					gbc_text_gst.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_gst.insets = new Insets(0, 0, 5, 0);
					gbc_text_gst.gridx = 4;
					gbc_text_gst.gridy = 6;
					//add(text_gst, gbc_text_gst);
					text_gst.setEditable(false);
					text_gst.setColumns(10);

				}
				{
					lblGrossTotal = new JLabel("Gross Total");
					GridBagConstraints gbc_lblGrossTotal = new GridBagConstraints();
					gbc_lblGrossTotal.fill = GridBagConstraints.BOTH;
					gbc_lblGrossTotal.insets = new Insets(0, 0, 5, 5);
					gbc_lblGrossTotal.gridx = 3;
					gbc_lblGrossTotal.gridy = 7;
					//add(lblGrossTotal, gbc_lblGrossTotal);
				}
				{
					text_gross_total = new JTextField();
					GridBagConstraints gbc_text_gross_total = new GridBagConstraints();
					gbc_text_gross_total.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_gross_total.insets = new Insets(0, 0, 5, 0);
					gbc_text_gross_total.gridx = 4;
					gbc_text_gross_total.gridy = 7;
					//add(text_gross_total, gbc_text_gross_total);
					text_gross_total.setEditable(false);
					text_gross_total.setColumns(10);
					//text_gross_total.setText(obj_booking.getGross_amount());
				}
				{
					lblDiscount = new JLabel("Discount");
					GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
					gbc_lblDiscount.fill = GridBagConstraints.BOTH;
					gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
					gbc_lblDiscount.gridx = 3;
					gbc_lblDiscount.gridy = 8;
					//add(lblDiscount, gbc_lblDiscount);
				}
				{
					text_discount = new JTextField();
					GridBagConstraints gbc_text_discount = new GridBagConstraints();
					gbc_text_discount.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_discount.insets = new Insets(0, 0, 5, 0);
					gbc_text_discount.gridx = 4;
					gbc_text_discount.gridy = 8;
					//add(text_discount, gbc_text_discount);
					text_discount.setEditable(false);
					text_discount.setColumns(10);
					//text_discount.setText(obj_booking.getDiscount());
				}
				{
					lblAdvance = new JLabel("Advance Paid");
					GridBagConstraints gbc_lblAdvance = new GridBagConstraints();
					gbc_lblAdvance.fill = GridBagConstraints.BOTH;
					gbc_lblAdvance.insets = new Insets(0, 0, 5, 5);
					gbc_lblAdvance.gridx = 3;
					gbc_lblAdvance.gridy = 9;
					//add(lblAdvance, gbc_lblAdvance);
				}
				{
					text_advance = new JTextField();
					GridBagConstraints gbc_text_advance = new GridBagConstraints();
					gbc_text_advance.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_advance.insets = new Insets(0, 0, 5, 0);
					gbc_text_advance.gridx = 4;
					gbc_text_advance.gridy = 9;
					//add(text_advance, gbc_text_advance);
					text_advance.setEditable(false);
					text_advance.setColumns(10);
					
				}
				{
					lblBalance = new JLabel("Balance");
					GridBagConstraints gbc_lblBalance = new GridBagConstraints();
					gbc_lblBalance.fill = GridBagConstraints.BOTH;
					gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
					gbc_lblBalance.gridx = 3;
					gbc_lblBalance.gridy = 10;
					//add(lblBalance, gbc_lblBalance);
				}
				{
					text_balance = new JTextField();
					GridBagConstraints gbc_text_balance = new GridBagConstraints();
					gbc_text_balance.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_balance.insets = new Insets(0, 0, 5, 0);
					gbc_text_balance.gridx = 4;
					gbc_text_balance.gridy = 10;
					//add(text_balance, gbc_text_balance);
					text_balance.setEditable(false);
					text_balance.setColumns(10);

				}
				//		{
				//			lblPaymentmode = new JLabel("Payment Mode");
				//			GridBagConstraints gbc_lblPaymentmode = new GridBagConstraints();
				//			gbc_lblPaymentmode.fill = GridBagConstraints.HORIZONTAL;
				//			gbc_lblPaymentmode.insets = new Insets(0, 0, 5, 5);
				//			gbc_lblPaymentmode.gridx = 4;
				//			gbc_lblPaymentmode.gridy = 12;
				//			add(lblPaymentmode, gbc_lblPaymentmode);
				//		}
				//		
				//		{
				//			rdbtnCash = new JRadioButton("CASH");
				//			GridBagConstraints gbc_rdbtnCash = new GridBagConstraints();
				//			gbc_rdbtnCash.fill = GridBagConstraints.HORIZONTAL;
				//			gbc_rdbtnCash.insets = new Insets(0, 0, 5, 5);
				//			gbc_rdbtnCash.gridx = 5;
				//			gbc_rdbtnCash.gridy = 12;
				//			add(rdbtnCash, gbc_rdbtnCash);
				//		}
				//		{
				//			rdbtnCard = new JRadioButton("CARD");
				//			GridBagConstraints gbc_rdbtnCard = new GridBagConstraints();
				//			gbc_rdbtnCard.fill = GridBagConstraints.HORIZONTAL;
				//			gbc_rdbtnCard.insets = new Insets(0, 0, 5, 5);
				//			gbc_rdbtnCard.gridx = 6;
				//			gbc_rdbtnCard.gridy = 12;
				//			add(rdbtnCard, gbc_rdbtnCard);
				//		}
						{
						}
				//text_bookingID.setText(bookingID);
			}
		}
		//okButton.addActionListener(this);
		//cancelButton.addActionListener(this);
		//rdbtnCard.addActionListener(this);
		//rdbtnCash.addActionListener(this);

		paybkColor();
		uplmtColor();
		uplcColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
		//setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	public void setClear1()
	{
		//BookingCancel.sbm_consignCom.db.remove(BookingCancel.text_bookingID.getSelectedItem());
		//BookingCancel.sbm_consignCom.cb.setSelectedItem("");
		textBookingId.setText("");
		text_customer_name.setText("");
		text_mobile.setText("");
		text_booking_date.setText("");
		text_checkin_date.setText("");
		text_checkout_date.setText("");
		textRoomNo.setText("");
		text_room_type.setText("");
		text_days.setText("");
		text_room_cost.setText("");
		text_facilities_cost.setText("");
		text_net_total.setText("");
		text_gst.setText("");
		text_gross_total.setText("");
		text_discount.setText("");
		text_advance.setText("");
		text_balance.setText("");
	}
	public void setClear()
	{

		//BookingCancel.sbm_consignCom.cb.setSelectedItem("");
		textBookingId.setText("");
		text_customer_name.setText("");
		text_mobile.setText("");
		text_booking_date.setText("");
		text_checkin_date.setText("");
		text_checkout_date.setText("");
		textRoomNo.setText("");
		text_room_type.setText("");
		text_days.setText("");
		text_room_cost.setText("");
		text_facilities_cost.setText("");
		text_net_total.setText("");
		text_gst.setText("");
		text_gross_total.setText("");
		text_discount.setText("");
		text_advance.setText("");
		text_balance.setText("");
	}
	public void setData(Booking objBooking)
	{
		text_customer_name.setText(objBooking.getCustomerName());
		text_mobile.setText(objBooking.getBooking_customer_mobile());
		text_booking_date.setText(""+objBooking.getBookingDate());
		text_checkin_date.setText(""+objBooking.getBookedDate());
		text_checkout_date.setText(""+objBooking.getCheckoutDate());
		textRoomNo.setText(objBooking.getBooking_room_door_number());
		text_room_type.setText(objBooking.getBooking_room_category());
		text_days.setText(objBooking.getBooking_total_nights());
		text_room_cost.setText(objBooking.getRoomCost());
		text_facilities_cost.setText(objBooking.getFacilitiesCost());

		text_gross_total.setText(objBooking.getGross_amount());
		text_discount.setText(objBooking.getDiscount());
		
	}
//	public boolean checkBookingInDB(String bookingID)
//	{
//		boolean b = false;
//		if(bookingID.length()>0)
//		{
//			for(String item : sbm_consignCom.db)
//			{
//				if(item.equals(bookingID))
//				{
//					b =  true;
//					break;
//				}
//			}
//		}
//		return b;		
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnSave)
		{			

			int answer = JOptionPane.showConfirmDialog(this, "Do you wish to cancel the Booking: "+textBookingId.getText()
					+"\n with Room Number: "+textRoomNo.getText(), null, JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION)
			{		
				BookingCancelController objCon = new BookingCancelController();
			int s = objCon.submitCancelService(textBookingId.getText(), textRoomNo.getText());
			if(s>0)
			{		
				//combo_booking_id.removeItem(combo_booking_id.getSelectedItem());
//				sbm_consignCom.db.remove(comboBookingId.getSelectedItem());
//				sbm_consignCom.setSelectedItem("");
			setClear(); 

			BookingCancelContainer objCancel = new BookingCancelContainer(mpg);
			ViewHandler.updateDashBoard(objCancel, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			//obj_bcc.retrieveAll(DatabaseConstants.TABLE_BOOKING_CANCEL, Constants.CANCEL);
			}
			}
			else if (answer == JOptionPane.NO_OPTION) {
				setClear1();
			}
			
			else
			{
				textBookingId.requestFocus(true);
				JOptionPane.showMessageDialog(this,"Select the Booking ID from the list","Failure",JOptionPane.ERROR_MESSAGE);
			}

				
			
		}
		else if(e.getSource()==checkBox)
		{
			if(checkBox.isSelected())
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
		lblNetTotal.setForeground(new Color(SetColor.cColor));
		lblCheckinDate.setForeground(new Color(SetColor.cColor));
		lblGst.setForeground(new Color(SetColor.cColor));
		lblCheckoutDate.setForeground(new Color(SetColor.cColor));
		lblGrossTotal.setForeground(new Color(SetColor.cColor));
		lblRoomNo.setForeground(new Color(SetColor.cColor));
		lblDiscount.setForeground(new Color(SetColor.cColor));
		lblRoomType.setForeground(new Color(SetColor.cColor));
		lblAdvance.setForeground(new Color(SetColor.cColor));
		lblNoOfDays.setForeground(new Color(SetColor.cColor));
		lblBalance.setForeground(new Color(SetColor.cColor));
		//lblPaymentmode.setForeground(new Color(SetColor.cColor));
		//rdbtnCard.setForeground(new Color(SetColor.cColor));
		//rdbtnCash.setForeground(new Color(SetColor.cColor));
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
		lblNetTotal.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckinDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblGst.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckoutDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblGrossTotal.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomNo.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDiscount.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomType.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdvance.setFont(new Font(ctFType,ctfProp,ctSize));
		lblNoOfDays.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBalance.setFont(new Font(ctFType,ctfProp,ctSize));
		//lblPaymentmode.setFont(new Font(ctFType,ctfProp,ctSize));
		//rdbtnCash.setFont(new Font(ctFType,ctfProp,ctSize));
		//rdbtnCard.setFont(new Font(ctFType,ctfProp,ctSize));
	}

	

}
