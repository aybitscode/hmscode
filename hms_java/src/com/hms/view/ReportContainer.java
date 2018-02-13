package com.hms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.hms.util.ViewConstants;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.WelcomeEntry;

public class ReportContainer extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	JComboBox comboReport;
	JButton btnSearch;
	MainPage mpg;
	public ReportContainer(MainPage mpg) {
		this.mpg = mpg;
		setLayout(new MigLayout("", "[grow][][300][grow]", "[20][][10][35][10][25][grow]"));
		
		JLabel lblGenerateReports = new JLabel("Generate Reports");
		add(lblGenerateReports, "cell 1 1 2 1,alignx center");
		lblGenerateReports.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblGenerateReports.setForeground(new Color(50, 197, 210));
		
		JLabel lblSelect = new JLabel("Report Type");
		add(lblSelect, "cell 1 3,alignx trailing");
		
		comboReport = new JComboBox();
		add(comboReport, "cell 2 3,grow");
		comboReport.addItem("");
		comboReport.addItem(ViewConstants.CUSTOMERS);
		comboReport.addItem(ViewConstants.BOOKINGS);
		comboReport.addItem(ViewConstants.CHECKINS);
		comboReport.addItem(ViewConstants.CHECKOUTS);
		comboReport.addItem(ViewConstants.CANCELED);
		comboReport.addItem(ViewConstants.EMPLOYEES);
		//comboReport.addItem(ViewConstants.EXPENSES);
		
		btnSearch = new JButton("Search");
		add(btnSearch, "cell 2 5,alignx center");
		btnSearch.addActionListener(this);
		
		
		setBackground(Color.white);
		

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnSearch)
		{
			if(comboReport.getSelectedItem().equals(ViewConstants.CUSTOMERS))
			{				 
				CustomersReport obj = new CustomersReport(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.BOOKINGS))
			{				 
				BookingTransactions obj = new BookingTransactions(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.CHECKINS))
			{				 
				CheckInHistory obj = new CheckInHistory(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.CHECKOUTS))
			{				 
				CheckOutHistory obj = new CheckOutHistory(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.CANCELED))
			{				 
				BookingCancelHistory obj = new BookingCancelHistory(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.EXPENSES))
			{				 
				ExpenseHistory obj = new ExpenseHistory(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
			else if(comboReport.getSelectedItem().equals(ViewConstants.EMPLOYEES))
			{				 
				EmployeesReport obj = new EmployeesReport(mpg);
				ViewHandler.updateDashBoard(obj, WelcomeEntry.dashBoardContainer, WelcomeEntry.gbc_bodyPanel, WelcomeEntry.sliderMenu, WelcomeEntry.gbc_sliderMenu);
			}
		}
	}

}
