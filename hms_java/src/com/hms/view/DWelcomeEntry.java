package com.hms.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SetColor;

public class DWelcomeEntry extends JPanel implements MouseListener{

	/**
	 * Create the panel.
	 */
	JPanel sliderMenu;
	private JLabel lblFirstdesk;
	private JLabel lblBooking;
	private JLabel lblCheckin_1;
	private JLabel lblCheckout_1;
	private JLabel lblUsers;
	private JLabel lblBookings;
	private JLabel lblCheckins;
	private JLabel lblCheckouts;
	private JLabel lblCancelbooking;
	private JLabel lblCancelbookings;
	private JLabel lblHistory;
	private JLabel lblHistories;
	private JLabel lblExpense;
	private JLabel lblExpenses;
	public static JLabel lblConfiguration;
	public static JLabel lblConfigurations;
	JPanel dashBoardContainer;
	JPanel bodyPanel;
	MainPage mpg;
	private JLabel lblTemp;
	public DWelcomeEntry(MainPage mpg) {
		this.mpg = mpg;
		setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 0 0,grow");
		
		dashBoardContainer = new JPanel();
		scrollPane.setViewportView(dashBoardContainer);
		dashBoardContainer.setLayout(new MigLayout("", "[][grow]", "[grow]"));
		
		sliderMenu = new JPanel();
		dashBoardContainer.add(sliderMenu, "cell 0 0,grow");
		sliderMenu.setLayout(new MigLayout("", "[]", "[grow]"));
		
		JPanel panel_1 = new JPanel();
		sliderMenu.add(panel_1, "cell 0 0,grow");
		GridBagLayout gbc_panel_1 = new GridBagLayout();
		gbc_panel_1.columnWidths = new int[]{0};
		gbc_panel_1.rowHeights = new int[]{0};
		gbc_panel_1.columnWeights = new double[]{Double.MIN_VALUE};
		gbc_panel_1.rowWeights = new double[]{Double.MIN_VALUE};
		panel_1.setLayout(gbc_panel_1);
		
		
		
		lblFirstdesk = new JLabel("");
		lblFirstdesk.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\user-32.png"));
		GridBagConstraints gbc_lblFirstdesk = new GridBagConstraints();
		gbc_lblFirstdesk.gridwidth = 2;
		gbc_lblFirstdesk.insets = new Insets(0, 0, 5, 0);
		gbc_lblFirstdesk.gridx = 0;
		gbc_lblFirstdesk.gridy = 0;
		panel_1.add(lblFirstdesk, gbc_lblFirstdesk);
		lblFirstdesk.addMouseListener(this);
		
		lblUsers = new JLabel("Users");
		lblUsers.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblUsers = new GridBagConstraints();
		gbc_lblUsers.anchor = GridBagConstraints.NORTH;
		gbc_lblUsers.gridwidth = 2;
		gbc_lblUsers.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsers.gridx = 0;
		gbc_lblUsers.gridy = 1;
		panel_1.add(lblUsers, gbc_lblUsers);
		
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
		//lblCancelbooking.addFocusListener(this);
		lblCancelbooking.addMouseListener(this);
		
		lblCancelbookings = new JLabel("Cancel Booking");
		lblCancelbookings.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCancelbookings = new GridBagConstraints();
		gbc_lblCancelbookings.anchor = GridBagConstraints.NORTH;
		gbc_lblCancelbookings.gridwidth = 2;
		gbc_lblCancelbookings.insets = new Insets(0, 0, 5, 0);
		gbc_lblCancelbookings.gridx = 0;
		gbc_lblCancelbookings.gridy = 9;
		panel_1.add(lblCancelbookings, gbc_lblCancelbookings);
		
		lblHistory = new JLabel("");
		lblHistory.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\history-32.png"));
		GridBagConstraints gbc_lblHistory = new GridBagConstraints();
		gbc_lblHistory.gridwidth = 2;
		gbc_lblHistory.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistory.gridx = 0;
		gbc_lblHistory.gridy = 10;
		panel_1.add(lblHistory, gbc_lblHistory);
		lblHistory.addMouseListener(this);
		
		lblHistories = new JLabel("History");
		lblHistories.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblHistories = new GridBagConstraints();
		gbc_lblHistories.anchor = GridBagConstraints.NORTH;
		gbc_lblHistories.gridwidth = 2;
		gbc_lblHistories.insets = new Insets(0, 0, 5, 0);
		gbc_lblHistories.gridx = 0;
		gbc_lblHistories.gridy = 11;
		panel_1.add(lblHistories, gbc_lblHistories);
		
		lblExpense = new JLabel("");
		lblExpense.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\expenses-32.png"));
		GridBagConstraints gbc_lblExpense = new GridBagConstraints();
		gbc_lblExpense.gridwidth = 2;
		gbc_lblExpense.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpense.gridx = 0;
		gbc_lblExpense.gridy = 12;
		panel_1.add(lblExpense, gbc_lblExpense);
		lblExpense.addMouseListener(this);
		
		lblExpenses = new JLabel("Expenses");
		lblExpenses.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblExpenses = new GridBagConstraints();
		gbc_lblExpenses.anchor = GridBagConstraints.NORTH;
		gbc_lblExpenses.gridwidth = 2;
		gbc_lblExpenses.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpenses.gridx = 0;
		gbc_lblExpenses.gridy = 13;
		panel_1.add(lblExpenses, gbc_lblExpenses);
		
		lblConfiguration = new JLabel("");
		lblConfiguration.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\settings-32.png"));
		GridBagConstraints gbc_lblConfiguration = new GridBagConstraints();
		gbc_lblConfiguration.gridwidth = 2;
		gbc_lblConfiguration.insets = new Insets(0, 0, 5, 0);
		gbc_lblConfiguration.gridx = 0;
		gbc_lblConfiguration.gridy = 14;
		panel_1.add(lblConfiguration, gbc_lblConfiguration);
		lblConfiguration.setVisible(false);
		lblConfiguration.addMouseListener(this);
		
		lblConfigurations = new JLabel("Configurations");
		lblConfigurations.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblConfigurations = new GridBagConstraints();
		gbc_lblConfigurations.anchor = GridBagConstraints.NORTH;
		gbc_lblConfigurations.gridwidth = 2;
		gbc_lblConfigurations.gridx = 0;
		gbc_lblConfigurations.gridy = 15;
		panel_1.add(lblConfigurations, gbc_lblConfigurations);
		lblConfigurations.setVisible(false);
		
		bodyPanel = new JPanel();
		dashBoardContainer.add(bodyPanel, "cell 1 0,grow");
		bodyPanel.setLayout(new MigLayout("", "[grow]", "[]"));
		
		lblTemp = new JLabel("Temp");
		bodyPanel.add(lblTemp, "cell 0 0");

	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblFirstdesk)
		{
			bodyPanel.removeAll();
		//	CustomerEntry objUser = new CustomerEntry();
			//bodyPanel.add(objUser, "cell 0 0");

			
		}
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
		//panel_1.setBackground(new Color(38,53,75));
	}

}
