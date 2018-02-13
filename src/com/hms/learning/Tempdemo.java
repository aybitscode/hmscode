package com.hms.learning;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Tempdemo extends JFrame {
	  JSpinner spinner;
	  SpinnerDateModel model = new SpinnerDateModel();
	  SimpleDateFormat format;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Tempdemo();
	}

	/**
	 * Create the frame.
	 */
	public Tempdemo() {

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
		    spinner = new JSpinner(model);

		    format = ((JSpinner.DateEditor) spinner.getEditor()).getFormat();
		    format.applyPattern("HH:mm:ss");
		    add(spinner);
		   
		    setVisible(true);		
	}

}
