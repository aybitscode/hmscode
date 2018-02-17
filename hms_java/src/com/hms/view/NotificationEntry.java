package com.hms.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hms.controller.WelcomeController;
import com.hms.model.Welcome;
import com.hms.util.CustomDialog;
import com.hms.util.CustomGridDialog;
import com.hms.util.DatabaseConstants;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class NotificationEntry extends JPanel {

	/**
	 * Create the panel.
	 */
	String checkins;
	JLabel listCheckinLabels[];
	String listCheckinID[];
	String listCheckinRoomNo[];
	int currCheckins = 0;
	String checkout;
	String listCheckoutRoomNo[];
	JLabel listCheckoutLabels[];
	String listCheckoutID[];
	int currCheckouts = 0;
	String cancel;
	JLabel listCancelLabels[];
	String listCancelRoomNo[];
	String listCancelID[];
	int currCancels = 0;
	
	
	
	private JLabel lblCheckin;
	private JLabel lblCheckout;
	private JLabel lblCivalue;
	private JLabel lblCovalue;
	private JLabel lblCustomersNotArrived;	
	private JLabel lblCustomer;
	
	WelcomeController obj_con;
	MainPage mpg;
	
	public NotificationEntry(final MainPage mpg) {
		this.mpg = mpg;	
		obj_con = new WelcomeController();
		List<Welcome> checkinList = obj_con.retriveCheckinCheckout(DatabaseConstants.CURRENT_BOOKED_DATE, DatabaseConstants.CUSTOMER_NAMES);
		System.out.println("the size is"+checkinList.size());
		List<Welcome> checkoutList = obj_con.retriveCheckinCheckout(DatabaseConstants.CURRENT_CHECKOUT_DATE, DatabaseConstants.CUSTOMER_NAMES); 
		List<Welcome> cancelList = obj_con.retriveCheckinCheckout(DatabaseConstants.CURRENT_CANCEL, DatabaseConstants.CUSTOMER_NAMES);
		checkins = "<html>Rooms to be checked in: <br><br>";
		checkout = "<html>Rooms to be checked out: <br><br>";
		cancel = "<html>Rooms to be canceled: <br><br>";
		
		listCheckinLabels = new JLabel[checkinList.size()];
		listCheckinID = new String[checkinList.size()];
		listCheckinRoomNo = new String[checkinList.size()];
		
		
		listCheckoutRoomNo = new String[checkoutList.size()];
		listCheckoutLabels = new JLabel[checkoutList.size()];
		listCheckoutID = new String[checkoutList.size()];
		
		listCancelLabels = new JLabel[cancelList.size()];
		listCancelID = new String[cancelList.size()];
		listCancelRoomNo = new String[cancelList.size()];
		
		int i = 0;
		for(Welcome wel : checkinList)
		{
			String checkins = "<html>";
			checkins += "Booking ID: "+ wel.getBookingID()+"<br>";
			checkins += "Customer Name: "+ wel.getCustomerName()+"<br>"; 
			checkins += "Room No: "+ wel.getRoomNo()+"<br>";
			checkins += "Room Type: "+wel.getRoomType()+"<br>";
			checkins += "Check-In Date: "+wel.getBookedDate()+"<br>";
			checkins += "Mobile No: "+wel.getMobileNo()+"<br></html>";
			Dimension d = new Dimension(150,100);
			listCheckinLabels[i] = new JLabel(checkins);
			listCheckinLabels[i].setMinimumSize(d);
			listCheckinLabels[i].setPreferredSize(d);
			listCheckinLabels[i].setMaximumSize(d);
			listCheckinID[i] = wel.getBookingID();
			listCheckinRoomNo[i] = wel.getRoomNo();
			EmptyBorder border = new EmptyBorder(5,10,5,10);
			listCheckinLabels[i].setBorder(border);
			currCheckins++;
			i++;
		}
		int j = 0;
		for(Welcome wel : checkoutList)
		{
			String checkout = "<html>";
			checkout += "Booking ID: "+ wel.getBookingID()+"<br>";
			checkout += "Room No: "+ wel.getRoomNo()+"<br>";
			checkout += "Room Type: "+wel.getRoomType()+"<br>";
			checkout += "Check-In Date: "+wel.getBookedDate()+"<br>";
			checkout += "Mobile No: "+wel.getMobileNo()+"<br></html>";
			listCheckoutLabels[j] = new JLabel(checkout);
			listCheckoutID[j] = wel.getBookingID();
			//listCheckoutRoomNo[i] = wel.getRoomNo();
			EmptyBorder border = new EmptyBorder(5,10,5,10);
			listCheckoutLabels[j].setBorder(border);
			currCheckouts++;
			j++;
		}
		int k = 0;
		for(Welcome wel : cancelList)
		{
			String cancel = "<html>";
			cancel += "Booking ID: "+ wel.getBookingID()+"<br>";
			cancel += "Customer Name: "+ wel.getCustomerName()+"<br>"; 
			cancel += "Room No: "+ wel.getRoomNo()+"<br>";
			cancel += "Room Type: "+wel.getRoomType()+"<br>";
			cancel += "Check-In Date: "+wel.getBookedDate()+"<br>";
			cancel += "Mobile No: "+wel.getMobileNo()+"<br></html>";
			Dimension d = new Dimension(150,100);
			listCancelLabels[k] = new JLabel(cancel);
			listCancelLabels[k].setMinimumSize(d);
			listCancelLabels[k].setPreferredSize(d);
			listCancelLabels[k].setMaximumSize(d);
			listCancelID[k] = wel.getBookingID();
			listCancelRoomNo[k] = wel.getRoomNo();
			EmptyBorder border = new EmptyBorder(5,10,5,10);
			listCancelLabels[k].setBorder(border);
			currCancels++;
			k++;
		}
		
		
		checkins += "</html>";
		checkout += "</html>";
		cancel += "</html>";

		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 10, 0, 0, 10};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0};
		//setLayout(gridBagLayout);
		setLayout(null);
		
		
		lblCheckin = new JLabel("");
		GridBagConstraints gbc_lblCheckin = new GridBagConstraints();
		gbc_lblCheckin.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCheckin.insets = new Insets(0, 0, 0, 5);
		gbc_lblCheckin.gridx = 0;
		gbc_lblCheckin.gridy = 1;
		//add(lblCheckin, gbc_lblCheckin);
		add(lblCheckin);
		lblCheckin.setBounds(0, 10, 30, 30);
		
		lblCheckin.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\checkin_micro.png"));
		lblCheckin.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblCheckin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
	
				new CustomDialog(mpg, checkins, "Alert !! (Today's checkin list)", lblCivalue, 75, 0, CustomDialog.INFO_ICON);
				
			}
		});

		
		lblCivalue = new JLabel(" "+currCheckins+" ");
		GridBagConstraints gbc_lblCivalue = new GridBagConstraints();
		gbc_lblCivalue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCivalue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCivalue.gridx = 1;
		gbc_lblCivalue.gridy = 0;
		//add(lblCivalue, gbc_lblCivalue);
		add(lblCivalue);
		lblCivalue.setBounds(25, 0, 20, 20);
	
		lblCivalue.setBackground(Color.red);
		lblCivalue.setOpaque(true);
		lblCivalue.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblCivalue.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
//				NotificationPopup np = new NotificationPopup(mpg);
//				np.setLocationRelativeTo(lblCivalue);
//				Point p = np.getLocation();
//				np.setLocation(p.x - 100, p.y);
//				
//				List<Welcome> welList = obj_con.retriveCheckins(DatabaseConstants.CURRENT_BOOKED_DATE); 
//				String checkins = "<html>Rooms to be checked in: <br>";
//				for(Welcome wel : welList)
//				{
//					checkins += "Room No: "+ wel.getRoomNo()+"<br>";
//					checkins += "Room Type: "+wel.getRoomType()+"<br>";
//					checkins += "Check-In Date: "+wel.getBookedDate()+"<br>";
//					checkins += "Mobile No: "+wel.getMobileNo()+"<br><br>";					
//				}
//				checkins += "</html>";
//				System.out.println(checkins);
//				//np.lbl.setText(checkins);
//				//np.setVisible(true);
				//JOptionPane.showMessageDialog(mpg, checkins, "Alert", JOptionPane.PLAIN_MESSAGE);
//				  final JOptionPane pane = new JOptionPane(checkins);
//				    final JDialog d = pane.createDialog(mpg, "Today's Checkin list");
//				    d.setLocationRelativeTo(lblCivalue);
//				    Point p = d.getLocation();
//				    d.setLocation(p.x + 75, p.y);
//				    d.setVisible(true);
				//new CustomDialog(mpg, checkins, "Alert !! (Today's checkin list)", lblCivalue, 75, 0, CustomDialog.INFO_ICON);
				new CustomGridDialog(mpg, lblCivalue, -200, 30, listCheckinLabels, listCheckinID, listCheckinRoomNo, com.hms.util.Constants.CHECKIN, "Today's Check-in list");
				//CustomDialog dlg = new CustomDialog(mpg, checkins, "Today's Checkin list", lblCivalue, 75, 0);
				
				
			}
			
		});
		

		lblCheckout = new JLabel("");
		GridBagConstraints gbc_lblCheckout = new GridBagConstraints();
		gbc_lblCheckout.anchor = GridBagConstraints.NORTH;
		gbc_lblCheckout.insets = new Insets(0, 0, 0, 5);
		gbc_lblCheckout.gridx = 3;
		gbc_lblCheckout.gridy = 1;
		//add(lblCheckout, gbc_lblCheckout);
		add(lblCheckout);
		lblCheckout.setBounds(60, 10, 30, 30);
		
		lblCheckout.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\checkout_micro.png"));
		
		lblCheckout.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblCheckout.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{

				new CustomDialog(mpg, checkout, "Alert !! (Today's checkout list)", lblCivalue, 75, 0, CustomDialog.INFO_ICON);
				
			}
		});	
		
		lblCovalue = new JLabel(" "+currCheckouts+" ");
		GridBagConstraints gbc_lblCovalue = new GridBagConstraints();
		gbc_lblCovalue.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblCovalue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCovalue.gridx = 4;
		gbc_lblCovalue.gridy = 0;
		//add(lblCovalue, gbc_lblCovalue);
		add(lblCovalue);
		lblCovalue.setBounds(75, 0, 20, 20);
		
		lblCovalue.setBackground(Color.red);
		lblCovalue.setOpaque(true);
		lblCovalue.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblCovalue.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{

				//new CustomDialog(mpg, checkout, "Alert !! (Today's checkout list)", lblCivalue, 75, 0, CustomDialog.INFO_ICON);
				new CustomGridDialog(mpg, lblCovalue, -200, 30, listCheckoutLabels, listCheckoutID, listCheckoutRoomNo, com.hms.util.Constants.CHECKOUT, "Today's Check-out list");
				
			}
			
		});
		

		lblCustomer = new JLabel("");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.insets = new Insets(0, 0, 0, 5);
		gbc_lblCustomer.gridx = 6;
		gbc_lblCustomer.gridy = 1;
		//add(lblCustomer, gbc_lblCustomer);
		lblCustomer.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\customer.png"));
		add(lblCustomer);
		lblCustomer.setBounds(110, 10, 30, 30);


		
		lblCustomersNotArrived = new JLabel(" "+currCancels+" ");
		lblCustomersNotArrived.setOpaque(true);
		lblCustomersNotArrived.setForeground(Color.BLACK);
		lblCustomersNotArrived.setBackground(Color.RED);
		GridBagConstraints gbc_lblCustomersNotArrived = new GridBagConstraints();
		gbc_lblCustomersNotArrived.insets = new Insets(0, 0, 5, 0);
		gbc_lblCustomersNotArrived.gridx = 7;
		gbc_lblCustomersNotArrived.gridy = 0;
		//add(lblCustomersNotArrived, gbc_lblCustomersNotArrived);
		add(lblCustomersNotArrived);
		lblCustomersNotArrived.setBounds(140, 0, 20, 20);
		lblCustomersNotArrived.addMouseListener(new MouseAdapter(){
			public void mouseEntered(MouseEvent e)
			{
				lblCustomersNotArrived.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			public void mousePressed(MouseEvent e)
			{
				new CustomGridDialog(mpg, lblCustomersNotArrived, -200 , 30, listCancelLabels, listCancelID, listCancelRoomNo, com.hms.util.Constants.CANCEL, "Customers not arrived list");
				
			}
		});		
		

		
		
		cColor();
		cFont(SFont.mtFType, SFont.mtfProp, 12);
		setBackground(Color.WHITE);
		//setVisible(true);
	}
	
	public void cFont(String ctFType,int ctfProp,int ctSize)
	{
		lblCheckin.setFont(new Font(ctFType,ctfProp,ctSize));
		lblCivalue.setFont(new Font(ctFType,ctfProp,ctSize));
	}
	public  void cColor()
	{
		lblCheckin.setForeground(new Color(SetColor.cColor));
		lblCivalue.setForeground(new Color(SetColor.cColor));
		lblCheckout.setForeground(new Color(SetColor.cColor));
		lblCovalue.setForeground(new Color(SetColor.cColor));
		lblCustomersNotArrived.setForeground(new Color(SetColor.cColor));
	}

}
