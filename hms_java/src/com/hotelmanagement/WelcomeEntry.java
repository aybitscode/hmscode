package com.hotelmanagement;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import com.hms.util.ScrollUtil;
import com.hms.util.ViewConstants;
import com.hms.view.BookingCancelContainer;
import com.hms.view.BookingCancelHistory;
import com.hms.view.BookingSingleContainer;
import com.hms.view.BookingTransactions;
import com.hms.view.CheckOutHistory;
import com.hms.view.CheckinContainer;
import com.hms.view.CheckoutContainer;
import com.hms.view.ConfigurationPanel;
import com.hms.view.CustomerEntry;
import com.hms.view.CustomersReport;
import com.hms.view.ExpensesEntry;
import com.hms.view.HotelEntry;
import com.hms.view.ImagePanel;
import com.hms.view.ReportContainer;
import com.hms.viewhandler.ViewHandler;

public class WelcomeEntry extends JPanel implements MouseListener, FocusListener {
	/**
	 * Create the panel.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	MainPage mpg;
	JPanel panel_1;
	

	public static JPanel sliderMenu;
	private JLabel lblFirstdesk;
	private JLabel lblBooking;
	private JLabel lblCheckin_1;
	private JLabel lblCheckout_1;
	private JLabel lblCustomers;
	private JLabel lblBookings;
	public static JLabel lblCheckins;
	private JLabel lblCheckouts;
	private JLabel lblCancelbooking;
	private JLabel lblCanceled;
	private JLabel lblHistory;
	public static JLabel lblReports;
	private JLabel lblExpense;
	private JLabel lblExpenses;
	public static JLabel lblConfiguration;
	public static JLabel lblConfigurations;
	public static JScrollPane scrollPane;
	public static JPanel dashBoardContainer;
	static JPanel bodyPanel;
	public static GridBagConstraints gbc_bodyPanel;
	public static GridBagConstraints gbc_sliderMenu;

	JPanel historyPanel;
	int toggle = 0;
	
	JLabel lblHCustomers;
	JLabel lblHBookings;
	JLabel lblHCheckins;
	JLabel lblHCheckouts; 
	JLabel lblHCanceled;
	JLabel lblHExpenses;
	public static String active="";
	public WelcomeEntry(final MainPage mpg) {
		this.mpg = mpg;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowWeights = new double[]{1.0};
		gridBagLayout.rowHeights = new int[]{0};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		
		GridBagLayout gbl_dashBoardContainer = new GridBagLayout();
		gbl_dashBoardContainer.columnWeights = new double[]{0.0, 1.0};
		gbl_dashBoardContainer.columnWidths = new int[]{0, 0};
		gbl_dashBoardContainer.rowWeights = new double[]{1.0};
		gbl_dashBoardContainer.rowHeights = new int[]{0};
		
		dashBoardContainer = new JPanel();
		dashBoardContainer.setBackground(new Color(238,241,245));
		dashBoardContainer.setLayout(gbl_dashBoardContainer);
		scrollPane.setViewportView(dashBoardContainer);
		
		sliderMenu = new JPanel();
		gbc_sliderMenu = new GridBagConstraints();
		gbc_sliderMenu.fill = GridBagConstraints.VERTICAL;
		gbc_sliderMenu.insets = new Insets(0, 0, 0, 5);
		gbc_sliderMenu.gridx = 0;
		gbc_sliderMenu.gridy = 0;
		dashBoardContainer.add(sliderMenu, gbc_sliderMenu);
		sliderMenu.setBackground(new Color(38,53,75));
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints(); 
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		sliderMenu.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 40, 10, 40, 0, 40, 0, 40, 0, 40, 0, 0, 40, 0, 40};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		lblFirstdesk = new JLabel("");
		lblFirstdesk.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\user-32.png"));
		GridBagConstraints gbc_lblFirstdesk = new GridBagConstraints();
		gbc_lblFirstdesk.gridwidth = 2;
		gbc_lblFirstdesk.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstdesk.gridx = 0;
		gbc_lblFirstdesk.gridy = 0;
		panel_1.add(lblFirstdesk, gbc_lblFirstdesk);
		lblFirstdesk.addMouseListener(this);
		
		lblCustomers = new JLabel("Customers");
		lblCustomers.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCustomers = new GridBagConstraints();
		gbc_lblCustomers.anchor = GridBagConstraints.NORTH;
		gbc_lblCustomers.gridwidth = 2;
		gbc_lblCustomers.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomers.gridx = 0;
		gbc_lblCustomers.gridy = 1;
		panel_1.add(lblCustomers, gbc_lblCustomers);
		
		lblBooking = new JLabel("");
		lblBooking.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\hotel-2-32.png"));
		GridBagConstraints gbc_lblBooking = new GridBagConstraints();
		gbc_lblBooking.gridwidth = 2;
		gbc_lblBooking.insets = new Insets(0, 0, 5, 0);
		gbc_lblBooking.gridx = 0;
		gbc_lblBooking.gridy = 2;
		panel_1.add(lblBooking, gbc_lblBooking);
		lblBooking.addMouseListener(this);
		
		lblBookings = new JLabel("Booking");
		lblBookings.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblBookings = new GridBagConstraints();
		gbc_lblBookings.anchor = GridBagConstraints.NORTH;
		gbc_lblBookings.gridwidth = 2;
		gbc_lblBookings.insets = new Insets(0, 0, 5, 0);
		gbc_lblBookings.gridx = 0;
		gbc_lblBookings.gridy = 3;
		panel_1.add(lblBookings, gbc_lblBookings);
		
		lblCheckin_1 = new JLabel("");
		lblCheckin_1.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\checkin_micro.png"));
		GridBagConstraints gbc_lblCheckin_1 = new GridBagConstraints();
		gbc_lblCheckin_1.gridwidth = 2;
		gbc_lblCheckin_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckin_1.gridx = 0;
		gbc_lblCheckin_1.gridy = 4;
		panel_1.add(lblCheckin_1, gbc_lblCheckin_1);
		lblCheckin_1.addMouseListener(this);
		
		lblCheckins = new JLabel("Check In");
		lblCheckins.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCheckins = new GridBagConstraints();
		gbc_lblCheckins.anchor = GridBagConstraints.NORTH;
		gbc_lblCheckins.gridwidth = 2;
		gbc_lblCheckins.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckins.gridx = 0;
		gbc_lblCheckins.gridy = 5;
		panel_1.add(lblCheckins, gbc_lblCheckins);
		
		lblCheckout_1 = new JLabel("");
		lblCheckout_1.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\checkout_micro.png"));
		GridBagConstraints gbc_lblCheckout_1 = new GridBagConstraints();
		gbc_lblCheckout_1.gridwidth = 2;
		gbc_lblCheckout_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckout_1.gridx = 0;
		gbc_lblCheckout_1.gridy = 6;
		panel_1.add(lblCheckout_1, gbc_lblCheckout_1);
		lblCheckout_1.addMouseListener(this);
		
		lblCheckouts = new JLabel("Check Out");
		lblCheckouts.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCheckouts = new GridBagConstraints();
		gbc_lblCheckouts.anchor = GridBagConstraints.NORTH;
		gbc_lblCheckouts.gridwidth = 2;
		gbc_lblCheckouts.insets = new Insets(0, 0, 5, 0);
		gbc_lblCheckouts.gridx = 0;
		gbc_lblCheckouts.gridy = 7;
		panel_1.add(lblCheckouts, gbc_lblCheckouts);
		
		lblCancelbooking = new JLabel("");
		lblCancelbooking.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\hotel-2-32.png"));
		GridBagConstraints gbc_lblCancelbooking = new GridBagConstraints();
		gbc_lblCancelbooking.gridwidth = 2;
		gbc_lblCancelbooking.insets = new Insets(0, 0, 5, 0);
		gbc_lblCancelbooking.gridx = 0;
		gbc_lblCancelbooking.gridy = 8;
		panel_1.add(lblCancelbooking, gbc_lblCancelbooking);
		lblCancelbooking.addFocusListener(this);
		lblCancelbooking.addMouseListener(this);
		
		lblCanceled = new JLabel("Cancel Booking");
		lblCanceled.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCanceled = new GridBagConstraints();
		gbc_lblCanceled.anchor = GridBagConstraints.NORTH;
		gbc_lblCanceled.gridwidth = 2;
		gbc_lblCanceled.insets = new Insets(0, 0, 5, 0);
		gbc_lblCanceled.gridx = 0;
		gbc_lblCanceled.gridy = 9;
		panel_1.add(lblCanceled, gbc_lblCanceled);
		


	
		
		historyPanel = new JPanel();
		
		historyPanel.setLayout(new MigLayout("", "[]", "[20,grow][20,grow][20,grow][20,grow][20,grow][20,grow]"));
		historyPanel.setBackground(new Color(38,53,75));
		
		lblHCustomers = new JLabel("Customers");
		historyPanel.add(lblHCustomers, "cell 0 0");
		lblHCustomers.setForeground(Color.white);
		lblHCustomers.setBorder(new EmptyBorder(5,5,5,5));
	
		
		lblHBookings = new JLabel("Bookings");
		historyPanel.add(lblHBookings, "cell 0 1");
		lblHBookings.setForeground(Color.white);
		lblHBookings.setBorder(new EmptyBorder(5,5,5,5));
 
		lblHCheckins = new JLabel("Checkins");
		historyPanel.add(lblHCheckins, "cell 0 2");
		lblHCheckins.setForeground(Color.white);
		lblHCheckins.setBorder(new EmptyBorder(5,5,5,5));
		 
		
		lblHCheckouts = new JLabel("Checkouts");
		historyPanel.add(lblHCheckouts, "cell 0 3");
		lblHCheckouts.setForeground(Color.white);
		lblHCheckouts.setBorder(new EmptyBorder(5,5,5,5));
	 
		
		lblHCanceled = new JLabel("Canceled");
		historyPanel.add(lblHCanceled, "cell 0 4");
		lblHCanceled.setForeground(Color.white);
		lblHCanceled.setBorder(new EmptyBorder(5,5,5,5));
	 
		
		lblHExpenses = new JLabel("Expenses");
		historyPanel.add(lblHExpenses, "cell 0 5");
		lblHExpenses.setForeground(Color.white);
		lblHExpenses.setBorder(new EmptyBorder(5,5,5,5));
	 
		
		GridBagConstraints gbc_historyPanel = new GridBagConstraints();
		gbc_historyPanel.fill = GridBagConstraints.BOTH;
		gbc_historyPanel.gridwidth = 2;
		gbc_historyPanel.insets = new Insets(0, 0, 0, 0);
		gbc_historyPanel.gridx = 0;
		gbc_historyPanel.gridy = 12;
		historyPanel.setVisible(false);
		//panel_1.add(historyPanel, gbc_historyPanel);
		
		
		lblExpense = new JLabel("");
		lblExpense.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\expenses-32.png"));
		GridBagConstraints gbc_lblExpense = new GridBagConstraints();
		gbc_lblExpense.gridwidth = 2;
		gbc_lblExpense.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpense.gridx = 0;
		gbc_lblExpense.gridy = 10;
		//panel_1.add(lblExpense, gbc_lblExpense);
		lblExpense.addMouseListener(this);
		
		lblExpenses = new JLabel("Expenses");
		lblExpenses.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblExpenses = new GridBagConstraints();
		gbc_lblExpenses.anchor = GridBagConstraints.NORTH;
		gbc_lblExpenses.gridwidth = 2;
		gbc_lblExpenses.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpenses.gridx = 0;
		gbc_lblExpenses.gridy = 11;
		//panel_1.add(lblExpenses, gbc_lblExpenses);
		
		
		lblHistory = new JLabel("");
		lblHistory.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\reports.png"));
		GridBagConstraints gbc_lblHistory = new GridBagConstraints();
		gbc_lblHistory.gridwidth = 2;
		gbc_lblHistory.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistory.gridx = 0;
		gbc_lblHistory.gridy = 11;
		panel_1.add(lblHistory, gbc_lblHistory);
		lblHistory.addMouseListener(this);
		

		
		lblReports = new JLabel("Reports");
		lblReports.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHistories = new GridBagConstraints();
		gbc_lblHistories.anchor = GridBagConstraints.NORTH;
		gbc_lblHistories.gridwidth = 2;
		gbc_lblHistories.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistories.gridx = 0;
		gbc_lblHistories.gridy = 12;
		panel_1.add(lblReports, gbc_lblHistories);
		
		lblConfiguration = new JLabel("");
		lblConfiguration.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\settings-32.png"));
		GridBagConstraints gbc_lblConfiguration = new GridBagConstraints();
		gbc_lblConfiguration.gridwidth = 2;
		gbc_lblConfiguration.insets = new Insets(0, 0, 5, 0);
		gbc_lblConfiguration.gridx = 0;
		gbc_lblConfiguration.gridy = 13;
		panel_1.add(lblConfiguration, gbc_lblConfiguration);
		lblConfiguration.setVisible(false);
		lblConfiguration.addMouseListener(this);
		
		lblConfigurations = new JLabel("Configuration");
		lblConfigurations.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConfigurations = new GridBagConstraints();
		gbc_lblConfigurations.anchor = GridBagConstraints.NORTH;
		gbc_lblConfigurations.gridwidth = 2;
		gbc_lblConfigurations.gridx = 0;
		gbc_lblConfigurations.gridy = 14;
		panel_1.add(lblConfigurations, gbc_lblConfigurations);
		lblConfigurations.setVisible(false);
		
		bodyPanel = new ImagePanel(new ImageIcon("C:\\HotelManagement\\boot\\images\\welcome.jpg").getImage());
		gbc_bodyPanel = new GridBagConstraints();
		gbc_bodyPanel.fill = GridBagConstraints.BOTH;
		gbc_bodyPanel.gridx = 1;
		gbc_bodyPanel.gridy = 0;
		dashBoardContainer.add(bodyPanel, gbc_bodyPanel);
		bodyPanel.setBackground(Color.white);
		
		 lblHCustomers.addMouseListener(this);
		 lblHBookings.addMouseListener(this);
		 lblHCheckins.addMouseListener(this);
		 lblHCheckouts.addMouseListener(this); 
		 lblHCanceled.addMouseListener(this);
		 lblHExpenses.addMouseListener(this);

		

		welbkColor();


		
	}

	public void welMTColor()
	{

	}
	public void welMTFont(String mtFType,int mtfProp,int mtSize)
	{
		
	}

	public void welbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		panel_1.setBackground(new Color(38,53,75));
	}
	
	public void resetForegroundColor()
	{
		 lblHCustomers.setForeground(Color.white);
		 lblHBookings.setForeground(Color.white);
		 lblHCheckins.setForeground(Color.white);
		 lblHCheckouts.setForeground(Color.white); 
		 lblHCanceled.setForeground(Color.white);
		 lblHExpenses.setForeground(Color.white);
	}
	public void resetSliderForegroundColor()
	{
		 lblCustomers.setForeground(Color.white);
		 lblBookings.setForeground(Color.white);
		 lblCheckins.setForeground(Color.white);
		 lblCheckouts.setForeground(Color.white); 
		 lblCanceled.setForeground(Color.white);
		 lblExpenses.setForeground(Color.white);
		 lblReports.setForeground(Color.white);
		 lblConfigurations.setForeground(Color.white);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==lblFirstdesk)
		{
			lblFirstdesk.setCursor(new Cursor(Cursor.HAND_CURSOR));	
			lblCustomers.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblBooking)
		{
			lblBooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblBookings.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblCheckin_1)
		{
			lblCheckin_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCheckins.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblCheckout_1)
		{
			lblCheckout_1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCheckouts.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblCancelbooking)
		{
			lblCancelbooking.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCanceled.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHistory)
		{
			lblHistory.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblReports.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblExpense)
		{
			lblExpense.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblExpenses.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblConfiguration)
		{
			lblConfiguration.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblConfigurations.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHCustomers)
		{
			lblHCustomers.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHCustomers.setForeground(new Color(50, 197, 210));
		
		}
		else if(arg0.getSource()==lblHBookings)
		{ 
			lblHBookings.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHBookings.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHCheckins)
		{
			lblHCheckins.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHCheckins.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHCheckouts )
		{ 
			lblHCheckouts.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHCheckouts.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHCanceled)
		{ 
			lblHCanceled.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHCanceled.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource()==lblHExpenses)
		{ 
			lblHExpenses.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblHExpenses.setForeground(new Color(50, 197, 210));
		}
		else
		{
			
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//History Pane Settings
		if(arg0.getSource()==lblFirstdesk)
		{ 
			if(!active.equals(ViewConstants.CUSTOMERS))
			lblCustomers.setForeground(Color.white);
		
		}
		else if(arg0.getSource()==lblBooking)
		{  
			if(!active.equals(ViewConstants.BOOKINGS))
			lblBookings.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblCheckin_1)
		{ 
			if(!active.equals(ViewConstants.CHECKINS))
			lblCheckins.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblCheckout_1 )
		{  
			if(!active.equals(ViewConstants.CHECKOUTS))
			lblCheckouts.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblCancelbooking)
		{  
			if(!active.equals(ViewConstants.CANCELED))
			lblCanceled.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHistory)
		{
			if(!active.equals(ViewConstants.REPORTS))
			lblReports.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblExpense)
		{  
			if(!active.equals(ViewConstants.EXPENSES))
			lblExpenses.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblConfiguration)
		{
			if(!active.equals(ViewConstants.CONFIGURATION))
			lblConfigurations.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHCustomers)
		{ 
			if(!active.equals(ViewConstants.HISTORY_CUSTOMERS))
			lblHCustomers.setForeground(Color.white);
		
		}
		else if(arg0.getSource()==lblHBookings)
		{  
			if(!active.equals(ViewConstants.HISTORY_BOOKINGS))
			lblHBookings.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHCheckins)
		{ 
			if(!active.equals(ViewConstants.HISTORY_CHECKIN))
			lblHCheckins.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHCheckouts )
		{  
			if(!active.equals(ViewConstants.HISTORY_CHECKOUT))
			lblHCheckouts.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHCanceled)
		{  
			if(!active.equals(ViewConstants.HISTORY_CANCELED))
			lblHCanceled.setForeground(Color.white);
		}
		else if(arg0.getSource()==lblHExpenses)
		{  
			if(!active.equals(ViewConstants.HISTORY_EXPENSES))
			lblHExpenses.setForeground(Color.white);
		}
		else
		{
			
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//resetForegroundColor();
		resetSliderForegroundColor();
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblHistory)
		{
			active = ViewConstants.REPORTS;
			lblReports.setForeground(new Color(50, 197, 210));
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			ReportContainer obj = new ReportContainer(mpg);
			ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}		
		else if(arg0.getSource()==lblFirstdesk)
		{
			active = ViewConstants.CUSTOMERS;
			lblCustomers.setForeground(new Color(50, 197, 210));
			CustomerEntry objUser = new CustomerEntry(mpg);
			ViewHandler.updateDashBoard(objUser, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
//			dashBoardContainer.removeAll();
//			
//			dashBoardContainer.add(sliderMenu, gbc_sliderMenu);
//			dashBoardContainer.add(obj_user, gbc_bodyPanel);
//			dashBoardContainer.updateUI();
			
		}
		else if(arg0.getSource()==lblBooking)
		{
			active = ViewConstants.BOOKINGS;
			lblBookings.setForeground(new Color(50, 197, 210));
			BookingSingleContainer objBooking = new BookingSingleContainer(mpg);
			ViewHandler.updateDashBoard(objBooking, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblCheckin_1)
		{
			active = ViewConstants.CHECKINS;
			lblCheckins.setForeground(new Color(50, 197, 210));
			CheckinContainer objCheckin = new CheckinContainer(mpg);
			ViewHandler.updateDashBoard(objCheckin, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblCheckout_1)
		{
			active = ViewConstants.CHECKOUTS;
			lblCheckouts.setForeground(new Color(50, 197, 210));
			CheckoutContainer objCheckout = new CheckoutContainer(mpg);
			ViewHandler.updateDashBoard(objCheckout, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblCancelbooking)
		{
			active = ViewConstants.CANCELED;
			lblCanceled.setForeground(new Color(50, 197, 210));
			BookingCancelContainer objBookingCancel = new BookingCancelContainer(mpg);
			ViewHandler.updateDashBoard(objBookingCancel, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
//		else if(arg0.getSource()==lblHistory)
//		{
//			if(toggle == 0)
//			{
//				toggle = 1;
//			historyPanel.setVisible(true);
//			}
//			else
//			{
//				toggle = 0;
//				historyPanel.setVisible(false);
//			}
//		}
		else if(arg0.getSource()==lblExpense)
		{
			ExpensesEntry objExpenses = new ExpensesEntry();
			ViewHandler.updateDashBoard(objExpenses, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblConfiguration)
		{
			active = ViewConstants.CONFIGURATION;
			lblConfigurations.setForeground(new Color(50, 197, 210));
			ConfigurationPanel objAdmin = new ConfigurationPanel(mpg);
			ViewHandler.updateDashBoard(objAdmin, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);

		}
		//History Pane Settings
		else if(arg0.getSource()==lblHCustomers)
		{ 
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_CUSTOMERS;
			lblHCustomers.setForeground(new Color(50, 197, 210));
			CustomersReport obj = new CustomersReport(mpg);
			ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblHBookings)
		{
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_BOOKINGS;
			lblHBookings.setForeground(new Color(50, 197, 210));
			BookingTransactions obj = new BookingTransactions(mpg);
			ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblHCheckins)
		{
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_CHECKIN;
			lblHCheckins.setForeground(new Color(50, 197, 210));
			//CheckInHistory obj = new CheckInHistory();
			//ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblHCheckouts )
		{
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_CHECKOUT;
			lblHCheckouts.setForeground(new Color(50, 197, 210));
			CheckOutHistory obj = new CheckOutHistory(mpg);
			ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblHCanceled)
		{
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_CANCELED;
			lblHCanceled.setForeground(new Color(50, 197, 210));
			BookingCancelHistory obj = new BookingCancelHistory(mpg);
			ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
		else if(arg0.getSource()==lblHExpenses)
		{
			ScrollUtil.scroll(scrollPane, SwingConstants.TOP);
			active = ViewConstants.HISTORY_EXPENSES;
			lblHExpenses.setForeground(new Color(50, 197, 210));
			//ExpenseHistory obj = new ExpenseHistory();
			//ViewHandler.updateDashBoard(obj, dashBoardContainer, gbc_bodyPanel, sliderMenu, gbc_sliderMenu);
		}
 
	 
		else
		{
			
		}
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblCancelbooking)
		{
//			dashBoardContainer.removeAll();
//			
//			dashBoardContainer.add(sliderMenu, gbc_sliderMenu);
//			dashBoardContainer.add(objBookingCancel, gbc_bodyPanel);
//			dashBoardContainer.updateUI();
		}
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

