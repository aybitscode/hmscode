package com.hms.learning;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MigLayoutDemo extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public MigLayoutDemo() {
		setLayout(new MigLayout("", "[][grow]", "[][]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel, "cell 0 0 2 1");
		
		JLabel lblTemp = new JLabel("Temp");
		add(lblTemp, "cell 0 1,alignx trailing");
		
		textField = new JTextField();
		add(textField, "cell 1 1,growx");
		textField.setColumns(10);

	}

}
