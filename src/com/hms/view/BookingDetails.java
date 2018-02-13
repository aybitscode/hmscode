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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import com.hms.controller.BookingDetailsController;
import com.hms.controller.BookingTransactionController;
import com.hms.controller.ReportDetailsController;
import com.hms.model.ReportDetails;
import com.hms.util.BigDecimalType;
import com.hms.util.Constants;
import com.hms.util.DatabaseConstants;
import com.hms.util.SearchBoxModel;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;


public class BookingDetails extends JDialog implements ActionListener, FocusListener{

	
	private static JComboBox combo_search;
	private JTextField text_customer_name;
	private JTextField text_mobile;
	private JTextField text_booking_date;
	private JTextField text_checkin_date;
	private JTextField text_checkout_date;
	private JTextField text_room_no;
	private JTextField text_room_type;
	private JTextField text_days;
	private JTextField text_room_cost;
	private JTextField text_facilities_cost;
	private JTextField text_miscellaneous;
	private JTextField text_extra_bed;
	private JTextField text_booking_sgst;
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
	private JLabel lblCheckinDate;
	private JLabel lblSGST;
	private JLabel lblCheckoutDate;
	private JLabel lblGrossTotal;
	private JLabel lblRoomNo;
	private JLabel lblDiscount;
	private JLabel lblRoomType;
	private JLabel lblAdvance;
	private JLabel lblNoOfDays;
	private JLabel lblBalance;
	JButton okButton;
	private static SearchBoxModel sbm_consignCom;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	ButtonGroup bg;
	private JPanel panel;
	TitledBorder border;
	private final JPanel contentPanel = new JPanel();
	private JTextField text_adults;
	private JLabel lblAdults;
	private JTextField text_child;
	private JLabel lblChildren;
	private JTextField text_status;
	private JTextField text_mode;
	private JTextField text_invoice;
	private JLabel lblStatus;
	private JLabel lblMode;
	private JLabel lblInvoice;
	BookingDetailsController obj_controller ;
	private ReportDetails obj_rpt;
	private JButton btnSubmit;
	public List<String> bookingList;
	public List<String> originalList;
	private JTextField text_booking_cgst;
	private JTextField text_service_cgst;
	private JTextField text_service_sgst;
	private JLabel lblServiceSGST;
	private JLabel lblServiceCGST;
	private JLabel lblBookingCgst;
	private JLabel lblExtraBed;
	private JButton btnServices;
	public static double total_service_cost = 0.0;
	ShowMiscellaneous obj_mis;
	private JButton btnPrint;
	public BookingDetails(MainPage mpg, String bookingID, String query) {
		super(mpg,"Booking Details",true);
		obj_controller = new BookingDetailsController();
		
		obj_rpt = obj_controller.retrieve(DatabaseConstants.ALL_BOOKING_CHECKIN_ADVANCE, DatabaseConstants.CUSTOMER_NAME_ADDRS, DatabaseConstants.COUPON_DISCOUNT, DatabaseConstants.BILLING_DETAILS, bookingID);
		if(obj_rpt.getBookingID() == null)
		{
			obj_rpt = obj_controller.retrieveBookedCancel(DatabaseConstants.BOOKING_CANCEL, DatabaseConstants.CUSTOMER_NAMES, bookingID);
		}
		bookingList = new ArrayList<String>();
		originalList = new ArrayList<String>();
		if(obj_rpt.getBookingID()!=null)
			originalList.add(obj_rpt.getBookingID());
		else
			originalList.add("");		
		if(obj_rpt.getBookedDate()!=null)
			originalList.add(""+obj_rpt.getBookedDate());
		else
			originalList.add("");
		if(obj_rpt.getCheckoutDate()!=null)
			originalList.add(""+obj_rpt.getCheckoutDate());
		else
			originalList.add("");
		if(obj_rpt.getRoomType()!=null)
			originalList.add(obj_rpt.getRoomType());
		else
			originalList.add("");
		if(obj_rpt.getRoomNo()!=null)
			originalList.add(obj_rpt.getRoomNo());
		else
			originalList.add("");
		
		if(obj_rpt.getMobileNumber()!=null)
			originalList.add(obj_rpt.getMobileNumber());
		else
			originalList.add("");
//		if(obj_rpt.getBooking_total_adults()!=null)
//			originalList.add(obj_rpt.getBooking_total_adults());
//		else
//			originalList.add("");
//		if(obj_rpt.getBooking_total_childs()!=null)
//			originalList.add(obj_rpt.getBooking_total_childs());
//		else
//			originalList.add("");
		if(obj_rpt.getRoomCost()!=null)
			originalList.add(obj_rpt.getRoomCost());
		else
			originalList.add("");
		if(obj_rpt.getFacilitiesCost()!=null)
			originalList.add(obj_rpt.getFacilitiesCost());
		else
			originalList.add("");
		if(obj_rpt.getDays()!=null)
			originalList.add(obj_rpt.getDays());
		else
			originalList.add("");
		if(obj_rpt.getGrossTotal()!=null)
			originalList.add(obj_rpt.getGrossTotal());
		else
			originalList.add("");
		if(obj_rpt.getStatus()!=null)
			originalList.add(obj_rpt.getStatus());
		else
			originalList.add("");
		if(obj_rpt.getPaymentMode()!=null)
			originalList.add(obj_rpt.getPaymentMode());
		else
			originalList.add("");
		if(obj_rpt.getInvoiceID()!=null)
			originalList.add(obj_rpt.getInvoiceID());
		else
			originalList.add("");

		
		  for (String string : originalList) {
		        System.out.println(string);
		    }
		  
		  obj_mis = new ShowMiscellaneous(DatabaseConstants.MIS_SERVICES, obj_rpt.getBookingID());
	
		getContentPane().add(contentPanel);
		
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
		}
		setSize(3*d.width/4+100, 3*d.height/4);
		setLocation(d.width/8-50, d.height/8);
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{

			border = new TitledBorder(null, "Booking Details", TitledBorder.LEADING, TitledBorder.TOP, null, null);
			border.setTitleFont(new Font(SFont.stFType,SFont.stfProp,SFont.stSize));
			border.setTitleColor(new Color(SetColor.mtColor));
			panel.setBorder(border);

			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{100, 0, 100, 100, 0, 100, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			//panel.setBackground(Color.DARK_GRAY);


			{
				lblBookingId = new JLabel("Booking ID");
				GridBagConstraints gbc_lblBookingId = new GridBagConstraints();
				gbc_lblBookingId.fill = GridBagConstraints.BOTH;
				gbc_lblBookingId.insets = new Insets(0, 0, 5, 5);
				gbc_lblBookingId.gridx = 0;
				gbc_lblBookingId.gridy = 2;
				panel.add(lblBookingId, gbc_lblBookingId);
			}
			{
				combo_search = new JComboBox();
				GridBagConstraints gbc_combo_search = new GridBagConstraints();
				gbc_combo_search.fill = GridBagConstraints.HORIZONTAL;
				gbc_combo_search.insets = new Insets(0, 0, 5, 5);
				gbc_combo_search.gridx = 1;
				gbc_combo_search.gridy = 2;				
				combo_search.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

				    @Override
				    public void keyReleased(KeyEvent event) {
				        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
				            if (((JTextComponent) ((JComboBox) ((Component) event
				                    .getSource()).getParent()).getEditor()
				                    .getEditorComponent()).getText().isEmpty())
				            {
				            	JOptionPane.showMessageDialog(null, "No Bookings", "Success", JOptionPane.INFORMATION_MESSAGE);
				            }
				            else
				            {				           				        
				            	ReportDetails obj =obj_controller.retrieve(DatabaseConstants.ALL_BOOKING_CHECKIN_ADVANCE, DatabaseConstants.CUSTOMER_NAMES, DatabaseConstants.COUPON_DISCOUNT, DatabaseConstants.BILLING_DETAILS,  ""+combo_search.getSelectedItem());
				            	setData(obj);
				            	
				            }
				            	
				        }
				    }
				});
				combo_search.setMaximumRowCount(10);
				combo_search.setEditable(true);
				sbm_consignCom = new SearchBoxModel(combo_search, query);
				combo_search.setModel(sbm_consignCom);
				combo_search.addItemListener(sbm_consignCom);
				panel.add(combo_search, gbc_combo_search);
				sbm_consignCom.cb.setSelectedItem(obj_rpt.getBookingID());
				{
					lblChildren = new JLabel("Children");
					lblChildren.setForeground(Color.BLACK);
					lblChildren.setFont(new Font("Dialog", Font.PLAIN, 0));
					GridBagConstraints gbc_lblChildren = new GridBagConstraints();
					gbc_lblChildren.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblChildren.insets = new Insets(0, 0, 5, 5);
					gbc_lblChildren.gridx = 3;
					gbc_lblChildren.gridy = 2;
					panel.add(lblChildren, gbc_lblChildren);
				}
				{
					text_child = new JTextField();
					text_child.setEditable(false);
					text_child.setColumns(10);
					GridBagConstraints gbc_textField = new GridBagConstraints();
					gbc_textField.insets = new Insets(0, 0, 5, 5);
					gbc_textField.fill = GridBagConstraints.HORIZONTAL;
					gbc_textField.gridx = 4;
					gbc_textField.gridy = 2;
					panel.add(text_child, gbc_textField);
					text_child.setText(obj_rpt.getChilds());
				}
				text_child.addFocusListener(this);
				{
					lblServiceSGST = new JLabel("Service SGST");
					GridBagConstraints gbc_lblServiceSGST = new GridBagConstraints();
					gbc_lblServiceSGST.insets = new Insets(0, 0, 5, 5);
					gbc_lblServiceSGST.anchor = GridBagConstraints.WEST;
					gbc_lblServiceSGST.gridx = 6;
					gbc_lblServiceSGST.gridy = 2;
					panel.add(lblServiceSGST, gbc_lblServiceSGST);
				}
				{
					text_service_sgst = new JTextField();
					GridBagConstraints gbc_text_service_sgst = new GridBagConstraints();
					gbc_text_service_sgst.insets = new Insets(0, 0, 5, 0);
					gbc_text_service_sgst.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_service_sgst.gridx = 7;
					gbc_text_service_sgst.gridy = 2;
					panel.add(text_service_sgst, gbc_text_service_sgst);
					text_service_sgst.setColumns(10);
					text_service_sgst.setText(obj_rpt.getServiceSGST());
					text_service_sgst.setEditable(false);
				}
				{
					lblCustomerName = new JLabel("Customer Name");
					GridBagConstraints gbc_lblCustomerName = new GridBagConstraints();
					gbc_lblCustomerName.fill = GridBagConstraints.BOTH;
					gbc_lblCustomerName.insets = new Insets(0, 0, 5, 5);
					gbc_lblCustomerName.gridx = 0;
					gbc_lblCustomerName.gridy = 3;
					panel.add(lblCustomerName, gbc_lblCustomerName);
				}
				{
					text_customer_name = new JTextField();
					GridBagConstraints gbc_text_customer_name = new GridBagConstraints();
					gbc_text_customer_name.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_customer_name.insets = new Insets(0, 0, 5, 5);
					gbc_text_customer_name.gridx = 1;
					gbc_text_customer_name.gridy = 3;
					panel.add(text_customer_name, gbc_text_customer_name);
					text_customer_name.setEditable(false);
					text_customer_name.setColumns(10);
					text_customer_name.setText(obj_rpt.getCustomerName());
				}
				{
					lblNoOfDays = new JLabel("No. of Days");
					GridBagConstraints gbc_lblNoOfDays = new GridBagConstraints();
					gbc_lblNoOfDays.fill = GridBagConstraints.BOTH;
					gbc_lblNoOfDays.insets = new Insets(0, 0, 5, 5);
					gbc_lblNoOfDays.gridx = 3;
					gbc_lblNoOfDays.gridy = 3;
					panel.add(lblNoOfDays, gbc_lblNoOfDays);
				}
				{
					text_days = new JTextField();
					GridBagConstraints gbc_text_days = new GridBagConstraints();
					gbc_text_days.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_days.insets = new Insets(0, 0, 5, 5);
					gbc_text_days.gridx = 4;
					gbc_text_days.gridy = 3;
					panel.add(text_days, gbc_text_days);
					text_days.setEditable(false);
					text_days.setColumns(10);
					text_days.setText(obj_rpt.getDays());
					text_days.addFocusListener(this);
				}
			
				{
					lblGrossTotal = new JLabel("Gross Total");
					GridBagConstraints gbc_lblGrossTotal = new GridBagConstraints();
					gbc_lblGrossTotal.fill = GridBagConstraints.BOTH;
					gbc_lblGrossTotal.insets = new Insets(0, 0, 5, 5);
					gbc_lblGrossTotal.gridx = 6;
					gbc_lblGrossTotal.gridy = 3;
					panel.add(lblGrossTotal, gbc_lblGrossTotal);
				}
				{
					text_gross_total = new JTextField();
					GridBagConstraints gbc_text_gross_total = new GridBagConstraints();
					gbc_text_gross_total.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_gross_total.insets = new Insets(0, 0, 5, 0);
					gbc_text_gross_total.gridx = 7;
					gbc_text_gross_total.gridy = 3;
					panel.add(text_gross_total, gbc_text_gross_total);
					text_gross_total.setEditable(false);
					text_gross_total.setColumns(10);
					text_gross_total.setText(obj_rpt.getGrossTotal());
					text_gross_total.addFocusListener(this);
				}
				
				{
					lblMobile = new JLabel("Mobile Number");
					GridBagConstraints gbc_lblMobile = new GridBagConstraints();
					gbc_lblMobile.fill = GridBagConstraints.BOTH;
					gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
					gbc_lblMobile.gridx = 0;
					gbc_lblMobile.gridy = 4;
					panel.add(lblMobile, gbc_lblMobile);
				}
				{
					text_mobile = new JTextField();
					GridBagConstraints gbc_text_mobile = new GridBagConstraints();
					gbc_text_mobile.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_mobile.insets = new Insets(0, 0, 5, 5);
					gbc_text_mobile.gridx = 1;
					gbc_text_mobile.gridy = 4;
					panel.add(text_mobile, gbc_text_mobile);
					text_mobile.setEditable(false);
					text_mobile.setColumns(10);
					text_mobile.setText(obj_rpt.getMobileNumber());
				}

				{
					lblRoomCost = new JLabel("Room Cost");
					GridBagConstraints gbc_lblRoomCost = new GridBagConstraints();
					gbc_lblRoomCost.fill = GridBagConstraints.BOTH;
					gbc_lblRoomCost.insets = new Insets(0, 0, 5, 5);
					gbc_lblRoomCost.gridx = 3;
					gbc_lblRoomCost.gridy = 4;
					panel.add(lblRoomCost, gbc_lblRoomCost);
				}
				{
					text_room_cost = new JTextField();
					GridBagConstraints gbc_text_room_cost = new GridBagConstraints();
					gbc_text_room_cost.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_room_cost.insets = new Insets(0, 0, 5, 5);
					gbc_text_room_cost.gridx = 4;
					gbc_text_room_cost.gridy = 4;
					panel.add(text_room_cost, gbc_text_room_cost);
					text_room_cost.setEditable(false);
					text_room_cost.setColumns(10);
					text_room_cost.setText(obj_rpt.getRoomCost());
				}
				text_room_cost.addFocusListener(this);
			
				{
					lblDiscount = new JLabel("Discount");
					GridBagConstraints gbc_lblDiscount = new GridBagConstraints();
					gbc_lblDiscount.fill = GridBagConstraints.BOTH;
					gbc_lblDiscount.insets = new Insets(0, 0, 5, 5);
					gbc_lblDiscount.gridx = 6;
					gbc_lblDiscount.gridy = 4;
					panel.add(lblDiscount, gbc_lblDiscount);
				}
				{
					text_discount = new JTextField();
					GridBagConstraints gbc_text_discount = new GridBagConstraints();
					gbc_text_discount.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_discount.insets = new Insets(0, 0, 5, 0);
					gbc_text_discount.gridx = 7;
					gbc_text_discount.gridy = 4;
					panel.add(text_discount, gbc_text_discount);
					text_discount.setEditable(false);
					text_discount.setColumns(10);
					text_discount.setText(obj_rpt.getDiscount());
				}
				{
					lblBookingDate = new JLabel("Booking Date");
					GridBagConstraints gbc_lblBookingDate = new GridBagConstraints();
					gbc_lblBookingDate.fill = GridBagConstraints.BOTH;
					gbc_lblBookingDate.insets = new Insets(0, 0, 5, 5);
					gbc_lblBookingDate.gridx = 0;
					gbc_lblBookingDate.gridy = 5;
					panel.add(lblBookingDate, gbc_lblBookingDate);
				}
				{
					text_booking_date = new JTextField();
					GridBagConstraints gbc_text_booking_date = new GridBagConstraints();
					gbc_text_booking_date.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_booking_date.insets = new Insets(0, 0, 5, 5);
					gbc_text_booking_date.gridx = 1;
					gbc_text_booking_date.gridy = 5;
					panel.add(text_booking_date, gbc_text_booking_date);
					text_booking_date.setEditable(false);
					text_booking_date.setColumns(10);
					text_booking_date.setText(""+obj_rpt.getBookedDate());
				}
				{
					lblFacilitiesCost = new JLabel("Facilities Cost");
					GridBagConstraints gbc_lblFacilitiesCost = new GridBagConstraints();
					gbc_lblFacilitiesCost.fill = GridBagConstraints.BOTH;
					gbc_lblFacilitiesCost.insets = new Insets(0, 0, 5, 5);
					gbc_lblFacilitiesCost.gridx = 3;
					gbc_lblFacilitiesCost.gridy = 5;
					panel.add(lblFacilitiesCost, gbc_lblFacilitiesCost);
				}
				{
					text_facilities_cost = new JTextField();
					GridBagConstraints gbc_text_facilities_cost = new GridBagConstraints();
					gbc_text_facilities_cost.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_facilities_cost.insets = new Insets(0, 0, 5, 5);
					gbc_text_facilities_cost.gridx = 4;
					gbc_text_facilities_cost.gridy = 5;
					panel.add(text_facilities_cost, gbc_text_facilities_cost);
					text_facilities_cost.setEditable(false);
					text_facilities_cost.setColumns(10);
					text_facilities_cost.setText(obj_rpt.getFacilitiesCost());
				}
				text_facilities_cost.addFocusListener(this);
			
				{
					lblAdvance = new JLabel("Advance Paid");
					GridBagConstraints gbc_lblAdvance = new GridBagConstraints();
					gbc_lblAdvance.fill = GridBagConstraints.BOTH;
					gbc_lblAdvance.insets = new Insets(0, 0, 5, 5);
					gbc_lblAdvance.gridx = 6;
					gbc_lblAdvance.gridy = 5;
					panel.add(lblAdvance, gbc_lblAdvance);
				}
				{
					text_advance = new JTextField();
					GridBagConstraints gbc_text_advance = new GridBagConstraints();
					gbc_text_advance.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_advance.insets = new Insets(0, 0, 5, 0);
					gbc_text_advance.gridx = 7;
					gbc_text_advance.gridy = 5;
					panel.add(text_advance, gbc_text_advance);
					text_advance.setEditable(false);
					text_advance.setColumns(10);
					text_advance.setText(obj_rpt.getAdvancePaid());
				}
				{
					lblCheckinDate = new JLabel("Check-In Date");
					GridBagConstraints gbc_lblCheckinDate = new GridBagConstraints();
					gbc_lblCheckinDate.fill = GridBagConstraints.BOTH;
					gbc_lblCheckinDate.insets = new Insets(0, 0, 5, 5);
					gbc_lblCheckinDate.gridx = 0;
					gbc_lblCheckinDate.gridy = 6;
					panel.add(lblCheckinDate, gbc_lblCheckinDate);
				}
				{
					text_checkin_date = new JTextField();
					GridBagConstraints gbc_text_checkin_date = new GridBagConstraints();
					gbc_text_checkin_date.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_checkin_date.insets = new Insets(0, 0, 5, 5);
					gbc_text_checkin_date.gridx = 1;
					gbc_text_checkin_date.gridy = 6;
					panel.add(text_checkin_date, gbc_text_checkin_date);
					text_checkin_date.setEditable(false);
					text_checkin_date.setColumns(10);
					text_checkin_date.setText(""+obj_rpt.getCheckinDate());
				}
				{
					lblExtraBed = new JLabel("Extra Bed Charges");
					GridBagConstraints gbc_lblExtraBed = new GridBagConstraints();
					gbc_lblExtraBed.insets = new Insets(0, 0, 5, 5);
					gbc_lblExtraBed.anchor = GridBagConstraints.EAST;
					gbc_lblExtraBed.gridx = 3;
					gbc_lblExtraBed.gridy = 6;
					panel.add(lblExtraBed, gbc_lblExtraBed);
				}
			
				{
					text_extra_bed = new JTextField();
					GridBagConstraints gbc_text_extra_bed = new GridBagConstraints();
					gbc_text_extra_bed.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_extra_bed.insets = new Insets(0, 0, 5, 5);
					gbc_text_extra_bed.gridx = 4;
					gbc_text_extra_bed.gridy = 6;
					panel.add(text_extra_bed, gbc_text_extra_bed);
					text_extra_bed.setEditable(false);
					text_extra_bed.setColumns(10);
					text_extra_bed.setText(obj_rpt.getExtraBedCharges());

				}
				text_extra_bed.addFocusListener(this);
	
				{
					lblBalance = new JLabel("Balance Amount Paid");
					GridBagConstraints gbc_lblBalance = new GridBagConstraints();
					gbc_lblBalance.anchor = GridBagConstraints.WEST;
					gbc_lblBalance.fill = GridBagConstraints.VERTICAL;
					gbc_lblBalance.insets = new Insets(0, 0, 5, 5);
					gbc_lblBalance.gridx = 6;
					gbc_lblBalance.gridy = 6;
					panel.add(lblBalance, gbc_lblBalance);
				}
				{
					text_balance = new JTextField();
					GridBagConstraints gbc_text_balance = new GridBagConstraints();
					gbc_text_balance.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_balance.insets = new Insets(0, 0, 5, 0);
					gbc_text_balance.gridx = 7;
					gbc_text_balance.gridy = 6;
					panel.add(text_balance, gbc_text_balance);
					text_balance.setEditable(false);
					text_balance.setColumns(10);
					if(!obj_rpt.getGrossTotal().equals("-"))
					{
						obj_rpt.setBalance(""+BigDecimalType.roundDown(Double.parseDouble(obj_rpt.getGrossTotal())-(Double.parseDouble(obj_rpt.getDiscount())+Double.parseDouble(obj_rpt.getAdvancePaid()))));
						text_balance.setText(obj_rpt.getBalance());
					}
					else
						text_balance.setText("-");

				}
				text_balance.addFocusListener(this);

				{
					lblCheckoutDate = new JLabel("Check-Out Date");
					GridBagConstraints gbc_lblCheckoutDate = new GridBagConstraints();
					gbc_lblCheckoutDate.fill = GridBagConstraints.BOTH;
					gbc_lblCheckoutDate.insets = new Insets(0, 0, 5, 5);
					gbc_lblCheckoutDate.gridx = 0;
					gbc_lblCheckoutDate.gridy = 7;
					panel.add(lblCheckoutDate, gbc_lblCheckoutDate);
				}
				{
					text_checkout_date = new JTextField();
					GridBagConstraints gbc_text_checkout_date = new GridBagConstraints();
					gbc_text_checkout_date.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_checkout_date.insets = new Insets(0, 0, 5, 5);
					gbc_text_checkout_date.gridx = 1;
					gbc_text_checkout_date.gridy = 7;
					panel.add(text_checkout_date, gbc_text_checkout_date);
					text_checkout_date.setEditable(false);
					text_checkout_date.setColumns(10);
					text_checkout_date.setText(""+obj_rpt.getCheckoutDate());
				}
				{
					lblBookingCgst = new JLabel("Booking CGST");
					GridBagConstraints gbc_lblBookingCgst = new GridBagConstraints();
					gbc_lblBookingCgst.insets = new Insets(0, 0, 5, 5);
					gbc_lblBookingCgst.anchor = GridBagConstraints.WEST;
					gbc_lblBookingCgst.gridx = 3;
					gbc_lblBookingCgst.gridy = 7;
					panel.add(lblBookingCgst, gbc_lblBookingCgst);
				}
				{
					text_booking_cgst = new JTextField();
					GridBagConstraints gbc_text_booking_cgst = new GridBagConstraints();
					gbc_text_booking_cgst.insets = new Insets(0, 0, 5, 5);
					gbc_text_booking_cgst.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_booking_cgst.gridx = 4;
					gbc_text_booking_cgst.gridy = 7;
					panel.add(text_booking_cgst, gbc_text_booking_cgst);
					text_booking_cgst.setColumns(10);
					text_booking_cgst.setText(obj_rpt.getBookingCGST());
					text_booking_cgst.setEditable(false);
				}


				{
					lblStatus = new JLabel("Status");
					GridBagConstraints gbc_lblStatus = new GridBagConstraints();
					gbc_lblStatus.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
					gbc_lblStatus.gridx = 6;
					gbc_lblStatus.gridy = 7;
					panel.add(lblStatus, gbc_lblStatus);
				}
				{
					text_status = new JTextField();
					text_status.setEditable(false);
					GridBagConstraints gbc_text_status = new GridBagConstraints();
					gbc_text_status.insets = new Insets(0, 0, 5, 0);
					gbc_text_status.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_status.gridx = 7;
					gbc_text_status.gridy = 7;
					panel.add(text_status, gbc_text_status);
					text_status.setColumns(10);
					text_status.setText(obj_rpt.getStatus());
				}
				text_status.addFocusListener(this);
				{
					lblRoomNo = new JLabel("Room No.");
					GridBagConstraints gbc_lblRoomNo = new GridBagConstraints();
					gbc_lblRoomNo.fill = GridBagConstraints.BOTH;
					gbc_lblRoomNo.insets = new Insets(0, 0, 5, 5);
					gbc_lblRoomNo.gridx = 0;
					gbc_lblRoomNo.gridy = 8;
					panel.add(lblRoomNo, gbc_lblRoomNo);
				}
				{
					text_room_no = new JTextField();
					GridBagConstraints gbc_text_room_no = new GridBagConstraints();
					gbc_text_room_no.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_room_no.insets = new Insets(0, 0, 5, 5);
					gbc_text_room_no.gridx = 1;
					gbc_text_room_no.gridy = 8;
					panel.add(text_room_no, gbc_text_room_no);
					text_room_no.setEditable(false);
					text_room_no.setColumns(10);
					text_room_no.setText(obj_rpt.getRoomNo());
				}
				{
					lblSGST = new JLabel("Booking SGST");
					GridBagConstraints gbc_lblSGST = new GridBagConstraints();
					gbc_lblSGST.fill = GridBagConstraints.BOTH;
					gbc_lblSGST.insets = new Insets(0, 0, 5, 5);
					gbc_lblSGST.gridx = 3;
					gbc_lblSGST.gridy = 8;
					panel.add(lblSGST, gbc_lblSGST);
				}
				{
					text_booking_sgst = new JTextField();
					GridBagConstraints gbc_text_booking_sgst = new GridBagConstraints();
					gbc_text_booking_sgst.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_booking_sgst.insets = new Insets(0, 0, 5, 5);
					gbc_text_booking_sgst.gridx = 4;
					gbc_text_booking_sgst.gridy = 8;
					panel.add(text_booking_sgst, gbc_text_booking_sgst);
					text_booking_sgst.setEditable(false);
					text_booking_sgst.setColumns(10);
					text_booking_sgst.setText(obj_rpt.getBookingSGST());

				}
				text_booking_sgst.addFocusListener(this);

				{
					lblMode = new JLabel("Payment Mode");
					GridBagConstraints gbc_lblMode = new GridBagConstraints();
					gbc_lblMode.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblMode.insets = new Insets(0, 0, 5, 5);
					gbc_lblMode.gridx = 6;
					gbc_lblMode.gridy = 8;
					panel.add(lblMode, gbc_lblMode);
				}

				{
					text_mode = new JTextField();
					text_mode.setEditable(false);
					GridBagConstraints gbc_text_mode = new GridBagConstraints();
					gbc_text_mode.insets = new Insets(0, 0, 5, 0);
					gbc_text_mode.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_mode.gridx = 7;
					gbc_text_mode.gridy = 8;
					panel.add(text_mode, gbc_text_mode);
					text_mode.setColumns(10);
					text_mode.setText(obj_rpt.getPaymentMode());
				}
				text_mode.addFocusListener(this);
				{
					lblRoomType = new JLabel("Room Type");
					GridBagConstraints gbc_lblRoomType = new GridBagConstraints();
					gbc_lblRoomType.fill = GridBagConstraints.BOTH;
					gbc_lblRoomType.insets = new Insets(0, 0, 5, 5);
					gbc_lblRoomType.gridx = 0;
					gbc_lblRoomType.gridy = 9;
					panel.add(lblRoomType, gbc_lblRoomType);
				}
				{
					text_room_type = new JTextField();
					GridBagConstraints gbc_text_room_type = new GridBagConstraints();
					gbc_text_room_type.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_room_type.insets = new Insets(0, 0, 5, 5);
					gbc_text_room_type.gridx = 1;
					gbc_text_room_type.gridy = 9;
					panel.add(text_room_type, gbc_text_room_type);
					text_room_type.setEditable(false);
					text_room_type.setColumns(10);
					text_room_type.setText(obj_rpt.getRoomType());
				}
				{
					lblMiscellaneous = new JLabel("Services");
					GridBagConstraints gbc_lblMiscellaneous = new GridBagConstraints();
					gbc_lblMiscellaneous.fill = GridBagConstraints.BOTH;
					gbc_lblMiscellaneous.insets = new Insets(0, 0, 5, 5);
					gbc_lblMiscellaneous.gridx = 3;
					gbc_lblMiscellaneous.gridy = 9;
					panel.add(lblMiscellaneous, gbc_lblMiscellaneous);
				}
				{
					text_miscellaneous = new JTextField();
					GridBagConstraints gbc_text_miscellaneous = new GridBagConstraints();
					gbc_text_miscellaneous.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_miscellaneous.insets = new Insets(0, 0, 5, 5);
					gbc_text_miscellaneous.gridx = 4;
					gbc_text_miscellaneous.gridy = 9;
					panel.add(text_miscellaneous, gbc_text_miscellaneous);
					text_miscellaneous.setEditable(false);
					text_miscellaneous.setColumns(10);
					text_miscellaneous.setText(""+BigDecimalType.round(total_service_cost));
					
				}
				{
					btnServices = new JButton("view");
					GridBagConstraints gbc_btnServices = new GridBagConstraints();
					gbc_btnServices.anchor = GridBagConstraints.WEST;
					gbc_btnServices.insets = new Insets(0, 0, 5, 5);
					gbc_btnServices.gridx = 5;
					gbc_btnServices.gridy = 9;
					panel.add(btnServices, gbc_btnServices);
					btnServices.addActionListener(this);
					if(total_service_cost<=0)
						btnServices.setEnabled(false);
				}




				{
					lblInvoice = new JLabel("Invoice ID");
					GridBagConstraints gbc_lblInvoice = new GridBagConstraints();
					gbc_lblInvoice.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblInvoice.insets = new Insets(0, 0, 5, 5);
					gbc_lblInvoice.gridx = 6;
					gbc_lblInvoice.gridy = 9;
					panel.add(lblInvoice, gbc_lblInvoice);
				}
				{
					text_invoice = new JTextField();
					text_invoice.setEditable(false);
					GridBagConstraints gbc_text_invoice = new GridBagConstraints();
					gbc_text_invoice.insets = new Insets(0, 0, 5, 0);
					gbc_text_invoice.fill = GridBagConstraints.HORIZONTAL;
					gbc_text_invoice.gridx = 7;
					gbc_text_invoice.gridy = 9;
					panel.add(text_invoice, gbc_text_invoice);
					text_invoice.setColumns(10);
					text_invoice.setText(obj_rpt.getInvoiceID());
				}
				text_invoice.addFocusListener(this);
				{
					lblAdults = new JLabel("Adults");
					GridBagConstraints gbc_lblAdults = new GridBagConstraints();
					gbc_lblAdults.fill = GridBagConstraints.HORIZONTAL;
					gbc_lblAdults.insets = new Insets(0, 0, 5, 5);
					gbc_lblAdults.gridx = 0;
					gbc_lblAdults.gridy = 10;
					panel.add(lblAdults, gbc_lblAdults);
				}
		
		{
			text_adults = new JTextField();
			GridBagConstraints gbc_text_adults = new GridBagConstraints();
			gbc_text_adults.insets = new Insets(0, 0, 5, 5);
			gbc_text_adults.fill = GridBagConstraints.HORIZONTAL;
			gbc_text_adults.gridx = 1;
			gbc_text_adults.gridy = 10;
			panel.add(text_adults, gbc_text_adults);
			text_adults.setColumns(10);
			text_adults.setEditable(false);
			text_adults.setText(obj_rpt.getAdults());
		}
		text_adults.addFocusListener(this);
		{
			lblServiceCGST = new JLabel("Service CGST");
			GridBagConstraints gbc_lblServiceCGST = new GridBagConstraints();
			gbc_lblServiceCGST.insets = new Insets(0, 0, 5, 5);
			gbc_lblServiceCGST.anchor = GridBagConstraints.WEST;
			gbc_lblServiceCGST.gridx = 3;
			gbc_lblServiceCGST.gridy = 10;
			panel.add(lblServiceCGST, gbc_lblServiceCGST);
		}
		{
			text_service_cgst = new JTextField();
			GridBagConstraints gbc_text_service_cgst = new GridBagConstraints();
			gbc_text_service_cgst.insets = new Insets(0, 0, 5, 5);
			gbc_text_service_cgst.fill = GridBagConstraints.HORIZONTAL;
			gbc_text_service_cgst.gridx = 4;
			gbc_text_service_cgst.gridy = 10;
			panel.add(text_service_cgst, gbc_text_service_cgst);
			text_service_cgst.setColumns(10);
			text_service_cgst.setText(obj_rpt.getServiceCGST());
			text_service_cgst.setEditable(false);
		}
		{
			btnSubmit = new JButton("Edit");
			GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
			gbc_btnSubmit.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
			gbc_btnSubmit.gridx = 6;
			gbc_btnSubmit.gridy = 10;
			//panel.add(btnSubmit, gbc_btnSubmit);
			btnSubmit.addActionListener(this);
		}
		{
			btnPrint = new JButton("Print");
			GridBagConstraints gbc_btnPrint = new GridBagConstraints();
			gbc_btnPrint.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnPrint.insets = new Insets(0, 0, 5, 0);
			gbc_btnPrint.gridx = 7;
			gbc_btnPrint.gridy = 10;
			//panel.add(btnPrint, gbc_btnPrint);
			btnPrint.addActionListener(this);
			if(obj_rpt.getStatus().equals(Constants.CHECKOUT))
				btnPrint.setEnabled(true);
			else
				btnPrint.setEnabled(false);
		}

		{
			okButton = new JButton("Close");
			GridBagConstraints gbc_okButton = new GridBagConstraints();
			gbc_okButton.gridwidth = 2;
			gbc_okButton.insets = new Insets(0, 0, 0, 5);
			gbc_okButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_okButton.gridx = 6;
			gbc_okButton.gridy = 11;
			panel.add(okButton, gbc_okButton);
			okButton.addActionListener(this);
		}


					
						
					



			}
		}
		text_booking_date.addFocusListener(this);
		text_checkin_date.addFocusListener(this);
		text_checkout_date.addFocusListener(this);
		text_room_type.addFocusListener(this);
		text_room_no.addFocusListener(this);		
		text_mobile.addFocusListener(this);
		
		paybkColor();
		uplmtColor();
		uplcColor();
		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
	
	}
	public void setData(ReportDetails obj_rpt)
	{
		
		text_customer_name.setText(obj_rpt.getCustomerName());
		text_mobile.setText(obj_rpt.getMobileNumber());
		text_booking_date.setText(""+obj_rpt.getBookedDate());
		text_checkin_date.setText(""+obj_rpt.getBookedDate());
		text_checkout_date.setText(""+obj_rpt.getCheckoutDate());
		text_room_no.setText(obj_rpt.getRoomNo());
		text_room_type.setText(obj_rpt.getRoomType());
		text_days.setText(obj_rpt.getDays());
		text_room_cost.setText(obj_rpt.getRoomCost());
		text_facilities_cost.setText(obj_rpt.getFacilitiesCost());
		text_child.setText(obj_rpt.getChilds());
		text_adults.setText(obj_rpt.getAdults());
		text_extra_bed.setText(obj_rpt.getExtraBedCharges());
		text_booking_cgst.setText(obj_rpt.getBookingCGST());
		text_booking_sgst.setText(obj_rpt.getBookingSGST());
		text_service_cgst.setText(obj_rpt.getServiceCGST());
		text_service_sgst.setText(obj_rpt.getServiceSGST());
		text_miscellaneous.setText(""+BigDecimalType.round(total_service_cost));
		text_gross_total.setText(obj_rpt.getGrossTotal());
		text_discount.setText(obj_rpt.getDiscount());
		text_advance.setText(obj_rpt.getAdvancePaid());
		if(!obj_rpt.getGrossTotal().equals("-"))
		text_balance.setText(""+BigDecimalType.roundDown(Double.parseDouble(obj_rpt.getGrossTotal())-(Double.parseDouble(obj_rpt.getDiscount())+Double.parseDouble(obj_rpt.getAdvancePaid()))));
		else
			text_balance.setText("-");
		text_status.setText(obj_rpt.getStatus());
		text_invoice.setText(obj_rpt.getInvoiceID());
		text_mode.setText(obj_rpt.getPaymentMode());
	}
	public void editData(boolean b)
	{
		combo_search.setEditable(false);
		text_booking_date.setEditable(b);
		text_checkin_date.setEditable(b);
		text_checkout_date.setEditable(b);
		text_room_type.setEditable(b);
		text_room_no.setEditable(b);		
		text_mobile.setEditable(b);
		text_adults.setEditable(b);
		text_child.setEditable(b);
		text_room_cost.setEditable(b);
		text_facilities_cost.setEditable(b);
		text_days.setEditable(b);
		text_extra_bed.setEditable(b);
		text_booking_sgst.setEditable(b);
		text_gross_total.setEditable(b);
		text_balance.setEditable(b);
		text_status.setEditable(b);
		text_mode.setEditable(b);
		text_invoice.setEditable(b);
		
		//text_customer_name.setEditable(b);
		//text_discount.setEditable(b);
		//text_advance.setEditable(b);
	}
	public void getNewList()
	{
		bookingList.add(""+combo_search.getSelectedItem());
		bookingList.add(text_booking_date.getText());
		bookingList.add(text_checkin_date.getText());
		bookingList.add(text_checkout_date.getText());
		bookingList.add(text_room_type.getText());
		bookingList.add(text_room_no.getText());		
		bookingList.add(text_mobile.getText());
		bookingList.add(text_adults.getText());
		bookingList.add(text_child.getText());
		bookingList.add(text_room_cost.getText());
		bookingList.add(text_facilities_cost.getText());
		bookingList.add(text_days.getText());
		bookingList.add(text_extra_bed.getText());
		bookingList.add(text_booking_sgst.getText());
		bookingList.add(text_gross_total.getText());
		bookingList.add(text_balance.getText());
		bookingList.add(text_status.getText());
		bookingList.add(text_mode.getText());
		bookingList.add(text_invoice.getText());
		
//		bookingList.add(text_customer_name.getText());
//		bookingList.add(text_discount.getText());
//		bookingList.add(text_advance.getText());

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==okButton)
		{
			String obj = null;
			System.out.println("the value is "+obj+"");
			dispose();
		}		
		else if(e.getActionCommand().equals("Edit"))
		{
			editData(true);
			btnSubmit.setText("Update");
		}
		else if(e.getActionCommand().equals("Update"))
		{
			int answer = JOptionPane.showConfirmDialog(new JFrame(), "Do you wish to update the Booking: "+obj_rpt.getBookingID()
					+"\n with Room Number: "+obj_rpt.getRoomNo());
			if (answer == JOptionPane.YES_OPTION)
			{		
					getNewList();
				try {
					BookingTransactionController btc = new BookingTransactionController();
					int s = btc.updateService(DatabaseConstants.UPDATE_BOOKING, originalList, bookingList, ""+combo_search.getSelectedItem());
					if(s>0)
					{
					editData(false);
					BookingTransactions.btc.retrieveAll();
					JOptionPane.showMessageDialog(this, "Record updated successfully", "Success",  JOptionPane.INFORMATION_MESSAGE);
					BookingTransactions.bg.clearSelection();
					BookingTransactions.chckbxAll.setSelected(true);
					dispose();
					}
					else
					{
						editData(true);

					}
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (answer == JOptionPane.NO_OPTION) {
				
			}
			else if (answer == JOptionPane.CANCEL_OPTION) {
				//setClear1();
			}			
		}
		else if(e.getSource() == btnServices)
			obj_mis.setVisible(true);
		else if(e.getSource() == btnPrint)
		{
			ReportDetailsController obj_con = new ReportDetailsController(obj_rpt);
			obj_con.submitService();
			obj_con.generateReport(this);
		}
	}
	
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel.setBackground(new Color(SetColor.bkColor));
		getContentPane().setBackground(new Color(SetColor.bkColor));
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
		lblCheckinDate.setForeground(new Color(SetColor.cColor));
		lblSGST.setForeground(new Color(SetColor.cColor));
		lblCheckoutDate.setForeground(new Color(SetColor.cColor));
		lblGrossTotal.setForeground(new Color(SetColor.cColor));
		lblRoomNo.setForeground(new Color(SetColor.cColor));
		lblDiscount.setForeground(new Color(SetColor.cColor));
		lblRoomType.setForeground(new Color(SetColor.cColor));
		lblAdvance.setForeground(new Color(SetColor.cColor));
		lblNoOfDays.setForeground(new Color(SetColor.cColor));
		lblBalance.setForeground(new Color(SetColor.cColor));
		lblAdults.setForeground(new Color(SetColor.cColor));
		lblStatus.setForeground(new Color(SetColor.cColor));
		lblMode.setForeground(new Color(SetColor.cColor));
		lblInvoice.setForeground(new Color(SetColor.cColor));
		lblChildren.setForeground(new Color(SetColor.cColor));
		lblExtraBed.setForeground(new Color(SetColor.cColor));
		lblBookingCgst.setForeground(new Color(SetColor.cColor));
		lblServiceCGST.setForeground(new Color(SetColor.cColor));
		lblServiceSGST.setForeground(new Color(SetColor.cColor));
		
	}
	public void uplSTFont(String stFType,int stfProp,int stSize)
	{
		
	}
	public void uplcFont(String ctFType,int ctfProp,int ctSize)
	{
		lblServiceCGST.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingCgst.setFont(new Font(ctFType,ctfProp,ctSize));
		lblExtraBed.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingId.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomCost.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCustomerName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblFacilitiesCost.setFont(new Font(ctFType,ctfProp,ctSize));
		lblMobile.setFont(new Font(ctFType,ctfProp,ctSize));
		lblMiscellaneous.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBookingDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckinDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblSGST.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCheckoutDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblGrossTotal.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomNo.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDiscount.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRoomType.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdvance.setFont(new Font(ctFType,ctfProp,ctSize));
		lblNoOfDays.setFont(new Font(ctFType,ctfProp,ctSize));
		lblBalance.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAdults.setFont(new Font(ctFType,ctfProp,ctSize));
		lblChildren.setFont(new Font(ctFType,ctfProp,ctSize));
		lblStatus.setFont(new Font(ctFType,ctfProp,ctSize));
		lblMode.setFont(new Font(ctFType,ctfProp,ctSize));
		lblInvoice.setFont(new Font(ctFType,ctfProp,ctSize));
		lblServiceSGST.setFont(new Font(ctFType,ctfProp,ctSize));

	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == text_booking_date)
		{
			text_booking_date.selectAll();
		}
		else if(e.getSource() == text_checkin_date)
		{
			text_checkin_date.selectAll();
		}
		else if(e.getSource() == text_checkout_date)
		{
			text_checkout_date.selectAll();
		}
		else if(e.getSource() == text_room_type)
		{
			text_room_type.selectAll();
		}
		else if(e.getSource() == text_room_no)
		{
			text_room_no.selectAll();
		}
		else if(e.getSource() == text_mobile)
		{
			text_mobile.selectAll();
		}
		else if(e.getSource() == text_adults)
		{
			text_adults.selectAll();
		}
		else if(e.getSource() == text_child)
		{
			text_child.selectAll();
		}
		else if(e.getSource() == text_room_cost)
		{
			text_room_cost.selectAll();
		}
		else if(e.getSource() == text_facilities_cost)
		{
			text_facilities_cost.selectAll();
		}
		else if(e.getSource() == text_days)
		{
			text_days.selectAll();
		}
		else if(e.getSource() == text_extra_bed)
		{
			text_extra_bed.selectAll();
		}
		else if(e.getSource() == text_booking_sgst)
		{
			text_booking_sgst.selectAll();
		}
		else if(e.getSource() == text_gross_total)
		{
			text_gross_total.selectAll();
		}
		else if(e.getSource() == text_balance)
		{
			text_balance.selectAll();
		}
		else if(e.getSource() == text_status)
		{
			text_status.selectAll();
		}
		else if(e.getSource() == text_mode)
		{
			text_mode.selectAll();
		}
		else if(e.getSource() == text_invoice)
		{
			text_invoice.selectAll();
		}
		else
		{
			
		}
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}


	

}
