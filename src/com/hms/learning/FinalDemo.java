package com.hms.learning;

import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hotelmanagement.MainPage;

public class FinalDemo extends JPanel{
	  public static JSpinner spinner;
	  public static SpinnerDateModel model = new SpinnerDateModel();
	  SimpleDateFormat format;
	  MainPage mpg;
	public FinalDemo(MainPage mpg)
	{
		this.mpg = mpg;
		setSize(400,400);
		setLayout(new FlowLayout());
		  try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Calendar cal = Calendar.getInstance();
		    Date date = cal.getTime();
		    model.setValue(date);
		    System.out.println(date);
		    spinner = new JSpinner(model);

		    format = ((JSpinner.DateEditor) spinner.getEditor()).getFormat(); 
		    format.applyPattern("HH:mm:ss a");

		    add(spinner);
		    setVisible(true);
		    
	}
//	public static void main(String arp[])
//	{
//		new FinalDemo();
//	}
}
