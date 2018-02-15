package com.hms.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;

public class CustomerSearchPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CustomerSearchPanel() {
		setLayout(new MigLayout("", "[79px][150][150][]", "[35px][35px]"));
		
		JLabel lblCustomerMobile = new JLabel("Customer Mobile");
		add(lblCustomerMobile, "cell 0 0,alignx right,growy");
		
		JComboBox comboMobile = new JComboBox();
		add(comboMobile, "cell 1 0 2 1,grow");
		
		JButton btnSearch = new JButton("Search");
		add(btnSearch, "cell 1 1,grow"); 
		
		JButton btnViewAll = new JButton("View All");
		add(btnViewAll, "cell 2 1,grow");

	}

}
