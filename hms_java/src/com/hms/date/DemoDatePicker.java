package com.hms.date;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import org.jdatepicker.constraints.RangeConstraint;

public class DemoDatePicker extends JFrame{

	private JPanel contentPane;
	private JDatePicker datepicker;
	
	public DemoDatePicker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new FlowLayout());
		//datepicker = new JDatePicker();
		//contentPane.add(datepicker);
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date dateBefore = new Date();
		Date dateAfter = new Date();
		try {
			
			dateAfter = formatter.parse("2017/11/30");
			dateBefore = formatter.parse("2017/12/10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 UtilDateModel model = new UtilDateModel();
		//SqlDateModel model = new SqlDateModel();
		model.setSelected(true);
		datepicker = new JDatePicker(model, "dd/MM/yyyy");
		
		RangeConstraint rc = new RangeConstraint(dateAfter, dateBefore);
		datepicker.addDateSelectionConstraint(rc);

		  
		datepicker.addActionListener(new ActionListener() {     
            public void actionPerformed(ActionEvent e) {
            	Date d1 =  (Date) datepicker.getModel().getValue();
            	DateFormat f = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
            		System.out.println("hi am from inside the listener"+datepicker.getModel().getYear()+ "Month "+ datepicker.getModel().getMonth()+" Day: "+datepicker.getModel().getDay());
            		System.out.println("the typecast date is "+d1);
            		System.out.println("formatted date is "+f.format(d1));
            		
            		
            }
        });

		contentPane.add(datepicker);

		
		
		
		
		
		setContentPane(contentPane);
	}

	
	
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoDatePicker frame = new DemoDatePicker();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}







	


}
