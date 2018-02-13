package com.hms.learning;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SpinnerDemo {

  JSpinner spinner;
  SpinnerDateModel model = new SpinnerDateModel();
  SimpleDateFormat format;
  JPanel panel;
  JFrame frame = new JFrame();

  public SpinnerDemo() {
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
//    format.setTimeZone(TimeZone.getTimeZone(zones[0]));
//    format.applyPattern("yyyy-MM-dd HH:mm:ss");
    format.applyPattern("HH:mm:ss");
    panel = new JPanel(new GridLayout(1, 2, 10, 10));

    frame.setLayout(new BorderLayout(10, 10));
    frame.add(spinner, BorderLayout.NORTH);
    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new SpinnerDemo();
  }
}