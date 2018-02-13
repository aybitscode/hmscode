package com.hms.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.hms.view.BookingCancel;
import com.hms.view.BookingCheckout;
import com.hms.view.CheckInEntry;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.WelcomeEntry;

public class CustomGridDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the dialog.
	 */
	GridBagConstraints gc;
	int flag;
	int row=1, col=1;
	JLabel list[];
	String bookingList[];
	String roomNos[];
	int i = 0;
	String text_enter;
	String text_exit;
	Dimension d;
	Dimension dd = Toolkit.getDefaultToolkit().getScreenSize();
	String trans_type;
	Point p;
	private JScrollPane scrollPane;
	MainPage mpg;
	public CustomGridDialog(MainPage mpg, Component rel, int xPixels, int yPixels, JLabel listP[], String listB[], String listR[], String type, String title) {
		super(mpg, title, true);
		this.mpg = mpg;
		this.list = listP;
		this.bookingList = listB;
		this.roomNos = listR; 
		trans_type = type;
		//setLocation(dd.width/2,100);
		setLocationRelativeTo(rel);
		p = this.getLocation();
		setLocation(p.x + xPixels, p.y + yPixels);
		if(list.length == 0)
			setSize(250,100);
		else
			setSize(250,500);

		CustomPanel panel = new CustomPanel();
		//pack();
		scrollPane = new JScrollPane(); //scroll pane code added
		//scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(panel);
		
		
		setResizable(false);		
		setVisible(true);

	}

	
	
	class CustomPanel extends JPanel implements MouseListener{
		
		CustomPanel()
		{
			setLayout(new GridBagLayout());
			gc = new GridBagConstraints();
			if(list.length==0)
			{
				JLabel l;
				if(trans_type.equals(Constants.CHECKIN))					
					l = new JLabel("    No checkins on current date");
				else if(trans_type.equals(Constants.CHECKOUT))
					l = new JLabel("    No checkouts on current date");
				else if(trans_type.equals(Constants.CANCEL))
					l = new JLabel("    No cancellations on current date");
				else
					l = new JLabel("    New entry");
				
				l.setForeground(Color.black);
				l.setHorizontalTextPosition(JLabel.CENTER);
				l.setOpaque(true);
				l.setBackground(Color.white);
				
				addComponent(l, 1,1,1,3);
			}
			else
			{	
			for(i=0;i<list.length;i++)
			{
				
				if(flag==1)
				{
					flag = 0;
					row+=3;
					col=1;
				}

				addComponent(list[i], row,col,1,3);
				list[i].setOpaque(true);
				list[i].setBackground(Color.white);
				list[i].setForeground(Color.black);
				
				

			    col++;
				flag++;
				if(list[i].getMouseListeners().length < 1)
				list[i].addMouseListener(this);
				
				
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
			gc.insets = new Insets(5,5,5,5);
			add(jc,gc);
		}
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			for(int i=0;i<list.length;i++)
			{
				if(arg0.getSource()==list[i])
				{

						d = list[i].getSize();
						d.width = d.width - 30;
						d.height = d.height - 30;
//						System.out.println("THE SIZE IS"+d.getWidth());
//						System.out.println("THE SIZE IS"+d.getHeight());
						list[i].setMinimumSize(d);
						list[i].setPreferredSize(d);
						list[i].setMaximumSize(d);
						list[i].setCursor(new Cursor(Cursor.HAND_CURSOR));					
						list[i].setOpaque(true);					
						//list[i].setBackground(Color.orange);
						list[i].setBackground(new Color(23,196, 187));
						d= list[i].getSize();
						text_enter = list[i].getText();
						if(trans_type.equals(Constants.CHECKIN))					
							list[i].setText("CHECK IN");
						else if(trans_type.equals(Constants.CHECKOUT))
							list[i].setText("CHECK OUT");
						else if(trans_type.equals(Constants.CANCEL))
						{
							list[i].setText("CANCEL");
							System.out.println("sibbu mouse has been entered "+text_enter);
						}
						else 
							list[i].setText("NEW ENTRY");
						list[i].setHorizontalAlignment(JLabel.CENTER);
						
				}
			}
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
			for(int i=0;i<list.length;i++)
			{
				if(arg0.getSource()==list[i])
				{
					System.out.println("the length is"+list[i].getMouseListeners().length);
							list[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							list[i].setBackground(Color.white);
							list[i].setOpaque(true);	
							list[i].setText(text_enter);
							System.out.println("sibbu mouse has been exited "+text_enter);
				}
			}		
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub  
			//Temporarily commented for delivery
//			for(int i=0;i<list.length;i++)
//			{
//				if(trans_type.equals(Constants.CHECKIN))
//				{
//					if(arg0.getSource()==list[i])
//					{
//						list[i].setText(text_enter);
//						String bookingID = bookingList[i];
//						String roomNo = roomNos[i];
//						
//						CheckInEntry.bookingID = bookingID;
//						CheckInEntry.text_roomNo.setText(roomNo);
//						//MainPage.tabbedPane.setSelectedIndex(3);
//
//						WelcomeEntry.dashBoardContainer.removeAll();
//						CheckInEntry obj_user = new CheckInEntry(mpg);
//						WelcomeEntry.dashBoardContainer.add(WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
//						WelcomeEntry.dashBoardContainer.add(obj_user, WelcomeEntry.gbc_bodyPanel);
//						WelcomeEntry.dashBoardContainer.updateUI();
//						CheckInEntry.text_BookingID.setPopupVisible(true);
//						CheckInEntry.text_BookingID.setPopupVisible(false);
//						CheckInEntry.text_BookingID.setSelectedItem(bookingID);
//						dispose();
//		
//					}
//				}
//				else if(trans_type.equals(Constants.CHECKOUT))
//				{
//					if(arg0.getSource()==list[i])
//					{
//						String bookingID = bookingList[i];
//						String roomNo = roomNos[i];
//						//CheckOutEntry.text_roomNo.setText(roomNo);
//						BookingCheckout.bookingID = bookingID;
//						MainPage.tabbedPane.setSelectedIndex(4);
//						BookingCheckout.text_bookingID.setPopupVisible(true);
//						BookingCheckout.text_bookingID.setPopupVisible(false);
//						BookingCheckout.text_bookingID.setSelectedItem(bookingID);
//						dispose();
//
//					}
//				}
//				else if(trans_type.equals(Constants.CANCEL))
//				{
//					if(arg0.getSource()==list[i])
//					{
//						list[i].setText(text_enter);
//						CustomGridDialog.this.dispose();
//						WelcomeEntry.dashBoardContainer.removeAll();
//						BookingCancel obj_user = new BookingCancel(mpg);
//						WelcomeEntry.dashBoardContainer.add(WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
//						WelcomeEntry.dashBoardContainer.add(obj_user, WelcomeEntry.gbc_bodyPanel);
//						WelcomeEntry.dashBoardContainer.updateUI();
//						
//						String bookingID = bookingList[i];
//						String roomNo = roomNos[i];
//						//CheckOutEntry.text_roomNo.setText(roomNo);
//						BookingCancel.bookingID = bookingID;
//						//MainPage.tabbedPane.setSelectedIndex(5);
//						BookingCancel.combo_booking_id.setPopupVisible(true);
//						BookingCancel.combo_booking_id.setPopupVisible(false);
//						BookingCancel.combo_booking_id.setSelectedItem(bookingID);
//
//						
//						
//
//					}
//				}
//			}
		}
	}

}
