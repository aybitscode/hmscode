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

import com.hms.view.BookedTransactions;
import com.hms.view.BookingCancelTransactions;
import com.hms.view.BookingTransactions;
import com.hms.view.CheckInTransactions;
import com.hms.view.CheckOutTransactions;


public class Transactions extends JPanel implements ChangeListener{

	/**
	 * Create the panel.
	 */
	JTabbedPane jtupl;
	MainPage mpg;
	public Transactions(MainPage mpg) {
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
		jtupl.addTab("Booking Transactions", new BookingTransactions(mpg));
		jtupl.addTab("Booked Transactions", null);
		jtupl.addTab("CheckIn Transactions", null);
		jtupl.addTab("CheckOut Transactions", null);
		jtupl.addTab("Canceled Transactions", null);

		
		jtupl.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));

		jtupl.setMnemonicAt(0, KeyEvent.VK_U);
		uplbkColor();
		upltbColor();
		jtupl.addChangeListener(this);
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jtupl)
		{
			if(jtupl.getSelectedIndex()==0)
				jtupl.setComponentAt(0, new BookingTransactions(mpg));
			else if(jtupl.getSelectedIndex()==1)
				jtupl.setComponentAt(1, new BookedTransactions());
			else if(jtupl.getSelectedIndex()==2)
				jtupl.setComponentAt(2, new CheckInTransactions());
			else if(jtupl.getSelectedIndex()==3)
				jtupl.setComponentAt(3, new CheckOutTransactions());
			else if(jtupl.getSelectedIndex()==4)
				jtupl.setComponentAt(4, new BookingCancelTransactions());

		}
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
