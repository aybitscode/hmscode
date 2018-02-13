package com.hms.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.hotelmanagement.MainPage;
import com.hotelmanagement.SFont;
import com.hotelmanagement.SetColor;


public class BookingContainer extends JPanel implements ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the panel.
	 */
	JTabbedPane tabbedPane;
	MainPage mpg;
	public BookingContainer(MainPage mpg) {
		this.mpg=mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		tabbedPane= new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		add(tabbedPane, gbc_tabbedPane);

		tabbedPane.addTab("Room Booking", new BookingSingleContainer(mpg)); 		


		tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize-2));

		tabbedPane.setMnemonicAt(0, KeyEvent.VK_U);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_O);
		uplbkColor();
		upltbColor();
		tabbedPane.addChangeListener(this);
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(tabbedPane.getSelectedIndex()==0)
		{
			tabbedPane.setComponentAt(0, new BookingSingleContainer(mpg));
		}
		else if(tabbedPane.getSelectedIndex()==1)
		{
			tabbedPane.setComponentAt(1, new BookingMultipleContainer());
		}
		else if(tabbedPane.getSelectedIndex()==2)
		{
			tabbedPane.setComponentAt(2, new BookingHallContainer());
		}	
		else{
			
		}
	

	
	}

	public void uplbkColor()
	{
		setBackground(new Color(SetColor.bkColor));
	}
	public void upltbColor()
	{
		tabbedPane.setForeground(new Color(SetColor.tbColor));
	}

}
