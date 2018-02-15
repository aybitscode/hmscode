package com.hms.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.hms.controller.CheckOutController;
import com.hms.services.CheckOutService;
import com.hms.util.Constants;
import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.SplitDate;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.SetColor;

public class CheckoutRightPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JToggleButton tglbtn[];
	int count, row=1, col=1, newCount;
	public static ButtonGroup bg;
	Statement stmt;
	public static int id=0;
	int flag;
	GridBagConstraints gc;
	String roomIDS[];
	Connection con = DBConnection.getDBConnection();
	DateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");
	String selectedDate;
	public CheckoutRightPanel(java.sql.Timestamp date)
	{
		//setPreferredSize(new Dimension(MainPage.scrwidth/2-300, MainPage.scrheight));
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{100, 0, 0, 0, 0, 0, 0};
//		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
//		//gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		setLayout(gridBagLayout);
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		bg = new ButtonGroup();
	    
//		GridBagConstraints gbc_lblBookingID1 = new GridBagConstraints();
//		gbc_lblBookingID1.anchor = GridBagConstraints.WEST;
//		gbc_lblBookingID1.insets = new Insets(0, 0, 5, 5);
//		gbc_lblBookingID1.gridx = 1;
//		gbc_lblBookingID1.gridy = 1;
//	    add(new JLabel("hello guys"), gbc_lblBookingID1);
	    
		updateRooms(date);
		if(newCount == 0)
			addComponent(new JLabel("Currently no checkouts"), 0,0,1,3);

	    setBackground(new Color(SetColor.bkColor));
		
	}

	private void updateRooms(java.sql.Timestamp selectedStamp) {
		try { 
			selectedDate = SplitDate.returnDate(selectedStamp);
			System.out.println("the time stamp is don"+selectedDate);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs=stmt.executeQuery("select count(*) from rooms");
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
			rs.close();
				
			Map<String, String> map = new HashMap<>();
			
//			PreparedStatement pstBooked = con.prepareStatement("select bookingID, bookedDate, checkoutDate, roomDoorNumber, status from booking where '"+selectedDate+"' between bookedDate and checkoutDate and status = ?");
//			//pstBooked.setString(1, date_format.format(current_date));
//			pstBooked.setString(1, Constants.BOOKED);
//		
//			ResultSet rsBooked = pstBooked.executeQuery();
//			while(rsBooked.next())
//			{
//				System.out.println("BookingID is"+rsBooked.getString(1)+"door no is"+rsBooked.getString(4)+"selected date is"+selectedDate);
//				map.put(rsBooked.getString(4), rsBooked.getString(5));
//				newCount++;
//				//System.out.println("bookingID: "+rsBooked.getString(1)+" checkinDate: "+rsBooked.getDate(2)+" checkoutDate"+rsBooked.getDate(3));
//				//System.out.println("from db roomno is"+rsBooked.getString(1)+" status is"+rsBooked.getString(2));
//			}
			
			PreparedStatement pstCheckedIn = con.prepareStatement("select bookingID, bookedDate, checkoutDate, roomDoorNumber, status from booking where '"+selectedDate+"' between bookedDate and checkoutDate and status = ?");
			//pstCheckedIn.setString(1, date_format.format(current_date));
			pstCheckedIn.setString(1, Constants.CHECKIN);
			ResultSet rsCheckedIn = pstCheckedIn.executeQuery();
			while(rsCheckedIn.next())
			{
				map.put(rsCheckedIn.getString(4), rsCheckedIn.getString(5));
				newCount++;
				//System.out.println("bookingID: "+rsCheckedIn.getString(1)+" checkinDate: "+rsCheckedIn.getDate(2)+" checkoutDate"+rsCheckedIn.getDate(3));
				//System.out.println("from db roomno is"+rsCheckedIn.getString(1)+" status is"+rsCheckedIn.getString(2));
			}
			
		tglbtn= new JToggleButton[newCount];
		ResultSet rk=stmt.executeQuery(DatabaseConstants.SELECT_ROOM_DETAILS);
		PreparedStatement pstRoomPrice=con.prepareStatement(DatabaseConstants.ROOMPRICE_ID_PRICE);

		roomIDS = new String[Constants.TOTAL_ROOMS];
		int j = 0;
		for(int i=0;rk.next();i++)
		{
			
			String roomID, doorNo, roomStatus = Constants.FREE, roomCategoryID, roomPrice="", roomPriceID="";
			roomID = rk.getString(1);
			doorNo = rk.getString(2);
			roomCategoryID = rk.getString(3);
			//roomStatus = rk.getString(4);
			for(Map.Entry<String, String> entry: map.entrySet())
			{
				//System.out.println("the key is"+entry.getKey());
				if(doorNo.equals(entry.getKey()))
				{					
					roomStatus = entry.getValue();
					System.out.println("the key is"+entry.getKey()+" the value is"+roomStatus);
					map.remove(entry.getKey());		
					
					break;
				}
				else
					roomStatus = Constants.FREE;
			}
			//System.out.println("from ouside the loop");
			pstRoomPrice.setString(1, roomCategoryID);
			ResultSet rsp = pstRoomPrice.executeQuery();
			String facilities = "";
			double totalCost = 0;
			if(rsp.next())
			{
				roomPriceID = rsp.getString(1);				
				roomPrice = rsp.getString(2);
				
				PreparedStatement pst;

				try {
					pst = con.prepareStatement(DatabaseConstants.FACILITIES_PRICE);
					pst.setString(1, roomPriceID);
					ResultSet rf = pst.executeQuery();
					while(rf.next())
					{
						facilities = facilities+rf.getString(1)+", ";
						totalCost += Double.parseDouble(rf.getString(2));
					}
					
				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			//roomFacilitiesPrice = resultSetPrice.getString(2);
			

			if(!roomStatus.equals(Constants.FREE))
			{			
				
				tglbtn[j] = new JToggleButton();
				tglbtn[j].setPreferredSize(new Dimension(100,50));
				bg.add(tglbtn[j]);
				
				roomIDS[j] = roomID;
				tglbtn[j].setText("<html>Room No: "+doorNo+"<br />"+roomCategoryID+"</html>");
				tglbtn[j].setHorizontalTextPosition(JButton.CENTER);
				tglbtn[j].setToolTipText("<html>Room No: "+doorNo+"<br /> Room Type: "+roomCategoryID+"<br /> Price: "+roomPrice+"<br /> Facilities : "+facilities+"<br /> Facilities Price: "+totalCost+"<br /> Status: "+roomStatus+"</html>");
//				if(roomStatus.equals(Constants.BOOKED)) 
//				{
//					
//					tglbtn[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
//					//tglbtn[j].setEnabled(false);
//					//tglbtn[j].setIcon(new ImageIcon("C:/HotelManagement/boot/images/booked.png"));
//					tglbtn[j].setBackground(new Color(255, 127, 39));
//					tglbtn[j].addActionListener(this);
//					System.out.println("mohammed j is "+j);
//				}
				if(roomStatus.equals(Constants.CHECKIN)) 
				{
					
					//tglbtn[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
					//tglbtn[j].setEnabled(false);
					//tglbtn[j].setIcon(new ImageIcon("C:/HotelManagement/boot/images/checkin.png"));
					tglbtn[j].setCursor(new Cursor(Cursor.HAND_CURSOR));
					tglbtn[j].setBackground(new Color(153, 217, 234));
					tglbtn[j].addActionListener(this);
					System.out.println("mohammed j from checkin is "+j);
					
				}
				
				if(flag==5)
				{
					flag = 0;
					row+=3;
					col=1;
				}
				addComponent(tglbtn[j], row,col,1,3);
				
				
			    col++;
				flag++;
				j++;


			}


				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String roomID;
System.out.println("the tglbtn length is"+tglbtn.length);
		for(int i=0;i<tglbtn.length;i++)
		{
		if(arg0.getSource()==tglbtn[i])
		{
			
			 
			if(tglbtn[i].isSelected())
			{
			    CheckoutContainer.splitPane.setDividerLocation(MainPage.scrwidth-5);
			    CheckoutContainer.splitPane.setPreferredSize(new Dimension(MainPage.scrwidth-200, MainPage.scrheight-150));
				tglbtn[i].setSelected(true); 
				roomID = roomIDS[i];
				String roomCategoryID = null;
				String roomDoorNo = null;
				int flag = 0;
				try {
//select bookingID, firstName, lastName, b1.mobile, roomCategoryID, roomDoorNumber  from booking b1 inner join customers c1 on b1.mobile = c1.mobile where '2018-02-11' between bookedDate and checkoutDate and status = 'BOOKED' and roomDoorNumber = '05';					
					
					PreparedStatement psmt=con.prepareStatement(DatabaseConstants.ROOMCATEGORY_ROOMID);
					PreparedStatement pstRoomPrice=con.prepareStatement(DatabaseConstants.ROOMPRICE_ID_PRICE);
					psmt.setString(1,roomID);
					ResultSet rs=psmt.executeQuery();
					if(rs.next())
					{
						id = i;
						roomCategoryID = rs.getString(1);	
						PreparedStatement pstDoor =con.prepareStatement(DatabaseConstants.ROOM_DOOR_NO);
						pstDoor.setString(1, roomID);
						ResultSet rsDoor = pstDoor.executeQuery();
						if(rsDoor.next())
						{
							roomDoorNo = rsDoor.getString(1);
							PreparedStatement pstBooked = con.prepareStatement("select bookingID, firstName, lastName, b1.mobile from booking b1 inner join customers c1 on b1.mobile = c1.mobile where '"+selectedDate+"' between bookedDate and checkoutDate and status = 'CHECKED IN' and roomDoorNumber = ?");
							pstBooked.setString(1, roomDoorNo);
							ResultSet rstBooked = pstBooked.executeQuery();
							if(rstBooked.next())
							{
								BookingCheckout.textBookingId.setText(rstBooked.getString(1));
//								BookingCheckout.textName.setText(rstBooked.getString(2)+" "+rstBooked.getString(3));
//								BookingCheckout.textMobile.setText(rstBooked.getString(4)); 
								BookingCheckout.rptDet = BookingCheckout.checkOut_service.retrieveCheckOutDetails(rstBooked.getString(1));
								BookingCheckout.checkOut_controller = new CheckOutController(BookingCheckout.rptDet);
								BookingCheckout.setData(BookingCheckout.rptDet);
								BookingCheckout.roomNumber = roomDoorNo;
							}
							
						}
						

					}
					
					pstRoomPrice.setString(1, roomCategoryID);
					ResultSet rp = pstRoomPrice.executeQuery();
					if(rp.next())
					{
						flag = 1;
						//SingleLeftPanel.room_category_ID = roomCategoryID;
						//SingleLeftPanel.roomDoorNumber = roomDoorNo;
						//SingleLeftPanel.room_rent = rp.getString(2);
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
						//SingleLeftPanel.facilities_cost = ""+facilitiesCost;
							
							
						}catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					//SingleLeftPanel.text_status.setText(Constants.BOOKED);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(flag == 0)
				{
					//SingleLeftPanel.room_category_ID = "";
					//SingleLeftPanel.roomDoorNumber = "";
					//SingleLeftPanel.facilities_cost = "";
					//SingleLeftPanel.room_rent = "";
					bg.clearSelection();
					JOptionPane.showMessageDialog(this, "Configure price for category '"+roomCategoryID+"'", "Error", JOptionPane.ERROR_MESSAGE);
					tglbtn[i].requestFocus(true);
				}
				//else
					//SingleLeftPanel.picker_checkout_date.requestFocus(true);
	
			}
		 
		}
	}
		
	}
	
	public void addComponent(JComponent jc,int r, int c, int w, int h)
	{
		gc.gridx=c;
		gc.gridy=r;
		gc.gridwidth=w;
		gc.ipady = 30;
		gc.ipadx = 30;
		gc.gridheight=h;
		gc.fill=GridBagConstraints.BOTH;
		add(jc,gc);
	}
 

}

