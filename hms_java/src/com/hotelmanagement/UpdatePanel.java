package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.hms.view.CouponEntry;
import com.hms.view.GSTEntry;
import com.hms.view.HotelEntry;
import com.hms.view.EmployeeEntry;
import com.hms.view.RoomCapacityEntry;
import com.hms.view.RoomCategoryEntry;
import com.hms.view.RoomEntry;
import com.hms.view.RoomFacilitiesEntry;
import com.hms.view.RoomPriceEntry;
import com.hms.view.SeasonEntry;


public class UpdatePanel extends JPanel implements ChangeListener{

	/**
	 * Create the panel.
	 */
	JTabbedPane jtupl;
	MainPage mpg;
	public UpdatePanel(MainPage mpg) {
		this.mpg=mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		jtupl= new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(jtupl, gbc_tabbedPane);
		//jtupl.addTab("Customer Details", new CustomerEntry());
		jtupl.addTab("Room Capacity", new RoomCapacityEntry());
		jtupl.addTab("Room Category", null);
		jtupl.addTab("Room Numbers", null);		
		jtupl.addTab("Room Facilities", null);
		jtupl.addTab("Hotel", null);
		jtupl.addTab("Coupons", null);
		jtupl.addTab("Seasons", null);
		jtupl.addTab("Room Price", null);		
		jtupl.addTab("Employees", null);
		jtupl.addTab("TAX Rules", null);
		//jtupl.addTab("Booking Details", null);
		//jtupl.addTab("Booking GUI Details", null);
		jtupl.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));

		jtupl.setMnemonicAt(0, KeyEvent.VK_U);
		jtupl.setMnemonicAt(1, KeyEvent.VK_O);
		uplbkColor();
		upltbColor();
		jtupl.addChangeListener(this);
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
//		if(jtupl.getSelectedIndex()==0)
//		{
//			//try {
//				//mpg.data.con.close();
//			//} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			//}
//			jtupl.setComponentAt(0, new CustomerEntry());
//		}
		if(jtupl.getSelectedIndex()==0)
		{
			//try {
				//mpg.data.con.close();
			//} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
			jtupl.setComponentAt(0, new RoomCapacityEntry());
		}
		else if(jtupl.getSelectedIndex()==1)
		{

			jtupl.setComponentAt(1, new RoomCategoryEntry());
		}
		else if(jtupl.getSelectedIndex()==2)
		{

			jtupl.setComponentAt(2, new RoomEntry(mpg));
		}	

		else if(jtupl.getSelectedIndex()==3)
		{

			jtupl.setComponentAt(3, new RoomFacilitiesEntry(mpg));
		}	
		else if(jtupl.getSelectedIndex()==4)
		{

			jtupl.setComponentAt(4, new HotelEntry(mpg));
		}
		else if(jtupl.getSelectedIndex()==5)
		{

			jtupl.setComponentAt(5, new CouponEntry(mpg));
		}
		else if(jtupl.getSelectedIndex()==6)
		{

			jtupl.setComponentAt(6, new SeasonEntry(mpg));
		}
		else if(jtupl.getSelectedIndex()==7)
		{

			jtupl.setComponentAt(7, new RoomPriceEntry());
		}
		else if(jtupl.getSelectedIndex()==8)
		{

			jtupl.setComponentAt(8, new EmployeeEntry());
		}
		else if(jtupl.getSelectedIndex()==9)
		{

			jtupl.setComponentAt(9, new GSTEntry());
		}
	
		
//		else if(jtupl.getSelectedIndex()==9)
//		{
//
//			jtupl.setComponentAt(9, new BookingEntry());
//		}
//		else if(jtupl.getSelectedIndex()==10)
//		{
//
//			jtupl.setComponentAt(10, new BookingGUI());
//		}
	}

	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void upltbColor()
	{
		jtupl.setForeground(new Color(SetColor.tbColor));
	}

}
