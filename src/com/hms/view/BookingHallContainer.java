 package com.hms.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;

public class BookingHallContainer extends JPanel {

	JPanel leftSplitPanel;
	static JPanel rightSplitPanel;

	JSplitPane splitPane;
	Connection con = DBConnection.getDBConnection();
	private JLabel lblFree;
	private JLabel lblBooked;
	private JLabel lblCheckedIn;

  public BookingHallContainer() {
		setBackground(new Color(SetColor.bkColor));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		

	    leftSplitPanel = new JPanel();
	   // leftSplitPanel.add(new SingleLeftPanel(), BorderLayout.NORTH);
	    //splitPane.setTopComponent(leftSplitPanel);  
	    
	    
	    rightSplitPanel = new JPanel();
	    rightSplitPanel.setLayout(new BorderLayout());
	    long millis = System.currentTimeMillis();
		java.sql.Timestamp current_date = new java.sql.Timestamp(millis);
		HallRightPanel rightPanel = new HallRightPanel(current_date);
		//MultipleRoomBooking rightPanel = new MultipleRoomBooking(current_date);
	    rightSplitPanel.add(new JScrollPane(rightPanel), BorderLayout.CENTER);
		
		lblFree = new JLabel("");
		lblFree.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\available_small.png"));
		GridBagConstraints gbc_lblFree = new GridBagConstraints();
		gbc_lblFree.anchor = GridBagConstraints.SOUTH;
		gbc_lblFree.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFree.insets = new Insets(0, 0, 0, 5);
		gbc_lblFree.gridx = 1;
		gbc_lblFree.gridy = 0;
		add(lblFree, gbc_lblFree);
		lblFree.setText("AVAILABLE");
		
		lblBooked = new JLabel("");
		lblBooked.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\booked_small.png"));
		GridBagConstraints gbc_lblBooked = new GridBagConstraints();
		gbc_lblBooked.anchor = GridBagConstraints.SOUTH;
		gbc_lblBooked.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBooked.insets = new Insets(0, 0, 0, 5);
		gbc_lblBooked.gridx = 2;
		gbc_lblBooked.gridy = 0;
		add(lblBooked, gbc_lblBooked);
		lblBooked.setText("BOOKED");
		lblBooked.setForeground(Color.white);
		
		lblCheckedIn = new JLabel("");
		lblCheckedIn.setIcon(new ImageIcon("C:\\HotelManagement\\boot\\images\\checkin_small.png"));
		GridBagConstraints gbc_lblCheckedIn = new GridBagConstraints();
		gbc_lblCheckedIn.anchor = GridBagConstraints.SOUTH;
		gbc_lblCheckedIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCheckedIn.insets = new Insets(0, 0, 0, 5);
		gbc_lblCheckedIn.gridx = 3;
		gbc_lblCheckedIn.gridy = 0;
		add(lblCheckedIn, gbc_lblCheckedIn);
		lblCheckedIn.setText("CHECKED IN");
	    //splitPane.setBottomComponent(rightSplitPanel);
	    
	    

		
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitPanel, rightSplitPanel);
	    splitPane.setOneTouchExpandable(true);
	    splitPane.setDividerLocation(MainPage.scrwidth/2-350);
	    splitPane.setPreferredSize(new Dimension(MainPage.scrwidth, MainPage.scrheight-150));
	    
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 4;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 1;
		add(splitPane, gbc_splitPane);
		
	    Calendar cal = Calendar.getInstance();
	    SingleLeftPanel.checkinSpinModel.setValue(cal.getTime());
	    //cal.add(Calendar.HOUR, Constants.CHECKOUT_TIME_LIMIT);
	    SingleLeftPanel.checkoutSpinModel.setValue(cal.getTime());
   

       bkColor();
       setcFont();
       setcColor();
    
  }

	public void bkColor()
	{

		leftSplitPanel.setBackground(new Color(SetColor.bkColor));
		rightSplitPanel.setBackground(new Color(SetColor.bkColor));
	}
	public void setcColor()
	{
		lblBooked.setForeground(new Color(SetColor.cColor));
		lblFree.setForeground(new Color(SetColor.cColor));
		lblCheckedIn.setForeground(new Color(SetColor.cColor));
	}
	public void setcFont()
	{
		lblBooked.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
		lblFree.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
		lblCheckedIn.setFont(new Font(SFont.ctFType,SFont.ctfProp,SFont.ctSize));
	}

}