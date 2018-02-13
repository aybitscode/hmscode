package com.hms.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.hms.util.ViewConstants;
import com.hotelmanagement.MainPage;

public class ConfigurationPanel extends JPanel implements ActionListener, FocusListener, MouseListener{
	public static JPanel containerPanel;
	public static JPanel masterContainer;
	private JLabel lblHotelDetails;
	static JLabel lblCouponDetails;
	static JLabel lblSeasonDetails;
	private JLabel lblEmployeeDetails;
	private JLabel lblTaxDetails;
	private JLabel lblRoomCapacityDetails;
	private JLabel lblRoomCategoryDetails;
	static JLabel lblRoomNumbers;
	static JLabel lblRoomFacilities;
	private JLabel lblRoomPrice;
	private JLabel lblRoomConfiguration;
	List<JLabel> list = new ArrayList<>();
	
	MainPage mpg;
	public static String active=""; 
	public ConfigurationPanel(MainPage mpg) {
		this.mpg = mpg;
		setLayout(new MigLayout("", "[300][grow]", "[grow]"));
		
		JPanel panel = new JPanel();
		add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[][][]", "[][][][][][][][][][][][][][]"));
		panel.setBackground(Color.white);
		
		JLabel lblHotelConfiguration = new JLabel("Hotel Configuration");
		lblHotelConfiguration.setFont(new Font("Open Sans", Font.PLAIN, 16));
		panel.add(lblHotelConfiguration, "cell 1 1");
		
		lblHotelDetails = new JLabel("Hotel Details");
		panel.add(lblHotelDetails, "cell 1 2");
		lblHotelDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		list.add(lblHotelDetails);
		lblHotelDetails.addFocusListener(this); 
		lblHotelDetails.addMouseListener(this);
 
		
		lblCouponDetails = new JLabel("Coupon Details");
		lblCouponDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblCouponDetails, "cell 1 3");
		list.add(lblCouponDetails);
		lblCouponDetails.addFocusListener(this);
		lblCouponDetails.addMouseListener(this);
		
		lblSeasonDetails = new JLabel("Season Details");
		lblSeasonDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblSeasonDetails, "cell 1 4");
		list.add(lblSeasonDetails);
		lblSeasonDetails.addFocusListener(this);
		lblSeasonDetails.addMouseListener(this);
		
		lblEmployeeDetails = new JLabel("Employee Details");
		lblEmployeeDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));		
		panel.add(lblEmployeeDetails, "cell 1 5");
		list.add(lblEmployeeDetails);
		lblEmployeeDetails.addFocusListener(this);
		lblEmployeeDetails.addMouseListener(this);
		
		lblTaxDetails = new JLabel("Tax Details");
		lblTaxDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblTaxDetails, "cell 1 6");
		list.add(lblTaxDetails);
		lblTaxDetails.addFocusListener(this);
		lblTaxDetails.addMouseListener(this);
		
		lblRoomConfiguration = new JLabel("Room Configuration");
		lblRoomConfiguration.setFont(new Font("Open Sans", Font.PLAIN, 16));
		panel.add(lblRoomConfiguration, "cell 1 7");
		
		lblRoomCapacityDetails = new JLabel("Room Capacity Details");
		lblRoomCapacityDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblRoomCapacityDetails, "cell 1 8");
		list.add(lblRoomCapacityDetails);
		lblRoomCapacityDetails.addFocusListener(this);
		lblRoomCapacityDetails.addMouseListener(this);
		
		lblRoomCategoryDetails = new JLabel("Room Category Details");
		lblRoomCategoryDetails.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblRoomCategoryDetails, "cell 1 9");
		list.add(lblRoomCategoryDetails);
		lblRoomCategoryDetails.addFocusListener(this);
		lblRoomCategoryDetails.addMouseListener(this);
		
		lblRoomNumbers = new JLabel("Room Numbers");
		lblRoomNumbers.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblRoomNumbers, "cell 1 10");
		list.add(lblRoomNumbers);
		lblRoomNumbers.addFocusListener(this);
		lblRoomNumbers.addMouseListener(this);
		
		lblRoomFacilities = new JLabel("Room Facilities");
		lblRoomFacilities.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblRoomFacilities, "cell 1 11");
		list.add(lblRoomFacilities);
		lblRoomFacilities.addFocusListener(this);
		lblRoomFacilities.addMouseListener(this);
		
		lblRoomPrice = new JLabel("Room Price");
		lblRoomPrice.setFont(new Font("Open Sans", Font.PLAIN, 12));
		panel.add(lblRoomPrice, "cell 1 12");
		list.add(lblRoomPrice);
		lblRoomPrice.addFocusListener(this);
		lblRoomPrice.addMouseListener(this);
		
		masterContainer = new JPanel();
		add(masterContainer, "cell 1 0,grow");
		masterContainer.setLayout(new MigLayout("", "[grow]", "[][grow]"));
		
		masterContainer.setBackground(Color.white);
	
		containerPanel = new HotelEntry(mpg);
		masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
		containerPanel.setLayout(new MigLayout("", "[]", "[]"));
		restetFocus(false);
		
	}
	public void restetFocus(boolean b)
	{
		lblCouponDetails.requestFocus(b);
	}
	
	public void resetForeground(Color clr)
	{
		lblHotelDetails.setForeground(clr);
		lblCouponDetails.setForeground(clr);
		lblSeasonDetails.setForeground(clr);
		lblEmployeeDetails.setForeground(clr);
		lblTaxDetails.setForeground(clr);
		lblRoomCapacityDetails.setForeground(clr);
		lblRoomCategoryDetails.setForeground(clr);
		lblRoomNumbers.setForeground(clr);
		lblRoomFacilities.setForeground(clr);
		lblRoomPrice.setForeground(clr);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblHotelDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new HotelEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI();
		}
		else if(arg0.getSource()==lblCouponDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new CouponEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			lblHotelDetails.setForeground(Color.black);
			lblCouponDetails.setForeground(new Color(50, 197, 210));
			
			updateUI();
			
		}
		else if(arg0.getSource()==lblSeasonDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new SeasonEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
	 
		else if(arg0.getSource()==lblEmployeeDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new EmployeeEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblTaxDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new GSTEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomCapacityDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new RoomCapacityEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomCategoryDetails)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new RoomCategoryEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomNumbers)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new RoomEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomFacilities)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new RoomFacilitiesEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomPrice)
		{
			masterContainer.remove(containerPanel);
			containerPanel = new RoomPriceEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			updateUI(); 
		}
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblHotelDetails)
		{
			lblHotelDetails.setForeground(new Color(50, 197, 210));
			lblHotelDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblCouponDetails)
		{	
			lblCouponDetails.setForeground(new Color(50, 197, 210));	
			lblCouponDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblSeasonDetails)
		{	
			lblSeasonDetails.setForeground(new Color(50, 197, 210));
			lblSeasonDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblEmployeeDetails)
		{
			lblEmployeeDetails.setForeground(new Color(50, 197, 210));
			lblEmployeeDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblTaxDetails)
		{
			lblTaxDetails.setForeground(new Color(50, 197, 210));
			lblTaxDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblRoomCapacityDetails)
		{
			lblRoomCapacityDetails.setForeground(new Color(50, 197, 210));
			lblRoomCapacityDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblRoomCategoryDetails)
		{
			lblRoomCategoryDetails.setForeground(new Color(50, 197, 210));
			lblRoomCategoryDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblRoomNumbers)
		{
			lblRoomNumbers.setForeground(new Color(50, 197, 210));
			lblRoomNumbers.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblRoomFacilities)
		{
			lblRoomFacilities.setForeground(new Color(50, 197, 210));
			lblRoomFacilities.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else if(arg0.getSource()==lblRoomPrice)
		{
			lblRoomPrice.setForeground(new Color(50, 197, 210));
			lblRoomPrice.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		else
		{
			
		}
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblHotelDetails)
		{
			if(!active.equals(ViewConstants.HOTEL_DETAILS))
			lblHotelDetails.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblCouponDetails)
		{	
			if(!active.equals(ViewConstants.COUPON_DETAILS))
			lblCouponDetails.setForeground(Color.black);			
		}
		else if(arg0.getSource()==lblSeasonDetails)
		{	
			if(!active.equals(ViewConstants.SEASON_DETAILS))
			lblSeasonDetails.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblEmployeeDetails)
		{
			if(!active.equals(ViewConstants.EMPLOYEE_DETAILS))
			lblEmployeeDetails.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblTaxDetails)
		{
			if(!active.equals(ViewConstants.TAX_DETAILS))
			lblTaxDetails.setForeground(Color.black); 
		}
		else if(arg0.getSource()==lblRoomCapacityDetails)
		{
			if(!active.equals(ViewConstants.ROOM_CAPACITY))
			lblRoomCapacityDetails.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblRoomCategoryDetails)
		{
			if(!active.equals(ViewConstants.ROOM_CATEGORY))
			lblRoomCategoryDetails.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblRoomNumbers)
		{
			if(!active.equals(ViewConstants.ROOM_NUMBERS))
			lblRoomNumbers.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblRoomFacilities)
		{
			if(!active.equals(ViewConstants.ROOM_FACILITIES))
			lblRoomFacilities.setForeground(Color.black);
		}
		else if(arg0.getSource()==lblRoomPrice)
		{
			if(!active.equals(ViewConstants.ROOM_PRICE))
			lblRoomPrice.setForeground(Color.black);
		}
		else
		{
			
		}
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	 
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==lblHotelDetails)
		{
			active = ViewConstants.HOTEL_DETAILS;
			masterContainer.remove(containerPanel);
			containerPanel = new HotelEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblHotelDetails.setForeground(new Color(50, 197, 210));
			
			updateUI();
		}
		else if(arg0.getSource()==lblCouponDetails)
		{
			active = ViewConstants.COUPON_DETAILS;
			masterContainer.remove(containerPanel);
			containerPanel = new CouponEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblCouponDetails.setForeground(new Color(50, 197, 210));

			updateUI();
			
		}
		else if(arg0.getSource()==lblSeasonDetails)
		{
			active = ViewConstants.SEASON_DETAILS;
			masterContainer.remove(containerPanel);
			containerPanel = new SeasonEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblSeasonDetails.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblEmployeeDetails)
		{
			active = ViewConstants.EMPLOYEE_DETAILS;
			masterContainer.remove(containerPanel);
			containerPanel = new EmployeeEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblEmployeeDetails.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblTaxDetails)
		{
			active = ViewConstants.TAX_DETAILS;
			masterContainer.remove(containerPanel);
			containerPanel = new GSTEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblTaxDetails.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomCapacityDetails)
		{
			active = ViewConstants.ROOM_CAPACITY;
			masterContainer.remove(containerPanel);
			containerPanel = new RoomCapacityEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblRoomCapacityDetails.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomCategoryDetails)
		{
			active = ViewConstants.ROOM_CATEGORY;
			masterContainer.remove(containerPanel);
			containerPanel = new RoomCategoryEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblRoomCategoryDetails.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomNumbers)
		{
			active = ViewConstants.ROOM_NUMBERS;
			masterContainer.remove(containerPanel);
			containerPanel = new RoomEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblRoomNumbers.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomFacilities)
		{
			active = ViewConstants.ROOM_FACILITIES;
			masterContainer.remove(containerPanel);
			containerPanel = new RoomFacilitiesEntry(mpg);
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblRoomFacilities.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else if(arg0.getSource()==lblRoomPrice)
		{
			active = ViewConstants.ROOM_PRICE;
			masterContainer.remove(containerPanel);
			containerPanel = new RoomPriceEntry();
			masterContainer.add(containerPanel, "cell 0 0,alignx center,aligny center");
			resetForeground(Color.black);
			lblRoomPrice.setForeground(new Color(50, 197, 210));

			updateUI(); 
		}
		else
		{
			
		}
	}

}
