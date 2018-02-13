package com.hotelmanagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.UIManager;

public class LFPanel extends JPanel {
	private JTextPane txtpnTextPane;
	private JSpinner spinner;
	private JTree tree;
	private JComboBox <String>comboBox_1;
	private JRadioButton rdbtnBold;
	private JTextArea txtrTextaea;
	private JButton btnNewButton;
	private JTextField txtTextBox;
	private JPasswordField passwordField;
	private JCheckBox checkBox;
	private JToggleButton tglbtnToggleButton;
	private JFormattedTextField frmtdtxtfldJformattedtextfield;
	private JProgressBar progressBar;
	private JScrollBar scrollBar;
	private JScrollBar scrollBar_1;
	private JSlider slider;
	private JSeparator separator;
	static String lftemp="";
	/**
	 * Create the panel.
	 */
	public LFPanel() {
	        try {

	            UIManager.setLookAndFeel(

	                    lftemp);

	        } catch(Exception e) {   }
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		frmtdtxtfldJformattedtextfield = new JFormattedTextField();
		frmtdtxtfldJformattedtextfield.setText("JFormattedTextField");
		GridBagConstraints gbc_frmtdtxtfldJformattedtextfield = new GridBagConstraints();
		gbc_frmtdtxtfldJformattedtextfield.insets = new Insets(0, 0, 5, 5);
		gbc_frmtdtxtfldJformattedtextfield.fill = GridBagConstraints.HORIZONTAL;
		gbc_frmtdtxtfldJformattedtextfield.gridx = 0;
		gbc_frmtdtxtfldJformattedtextfield.gridy = 0;
		add(frmtdtxtfldJformattedtextfield, gbc_frmtdtxtfldJformattedtextfield);
		
		progressBar = new JProgressBar();
		progressBar.setValue(50);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.gridwidth = 2;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 5, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		add(progressBar, gbc_progressBar);
		
		scrollBar_1 = new JScrollBar();
		GridBagConstraints gbc_scrollBar_1 = new GridBagConstraints();
		gbc_scrollBar_1.fill = GridBagConstraints.VERTICAL;
		gbc_scrollBar_1.gridheight = 7;
		gbc_scrollBar_1.gridx = 3;
		gbc_scrollBar_1.gridy = 0;
		add(scrollBar_1, gbc_scrollBar_1);
		
		scrollBar = new JScrollBar();
		scrollBar.setOrientation(JScrollBar.HORIZONTAL);
		GridBagConstraints gbc_scrollBar = new GridBagConstraints();
		gbc_scrollBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_scrollBar.insets = new Insets(0, 0, 5, 5);
		gbc_scrollBar.gridx = 0;
		gbc_scrollBar.gridy = 1;
		add(scrollBar, gbc_scrollBar);
		
		slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 1;
		add(slider, gbc_slider);
		checkBox = new JCheckBox("Checkbox");
		checkBox.setForeground(Color.WHITE);
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBackground(Color.BLACK);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 2;
		add(checkBox, gbc_checkBox);
		
		
		JLabel lblFontType = new JLabel("Label");
		GridBagConstraints gbc_lblFontType = new GridBagConstraints();
		gbc_lblFontType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFontType.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontType.gridx = 1;
		gbc_lblFontType.gridy = 2;
		add(lblFontType, gbc_lblFontType);
		lblFontType.setForeground(Color.WHITE);
		lblFontType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox_1 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 1;
		add(comboBox_1, gbc_comboBox_1);
		
		separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 2;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		
		rdbtnBold = new JRadioButton("RadioButton");
		GridBagConstraints gbc_rdbtnBold = new GridBagConstraints();
		gbc_rdbtnBold.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnBold.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBold.gridx = 0;
		gbc_rdbtnBold.gridy = 3;
		add(rdbtnBold, gbc_rdbtnBold);
		rdbtnBold.setBackground(Color.BLACK);
		rdbtnBold.setForeground(Color.WHITE);
		rdbtnBold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTextBox = new JTextField();
		GridBagConstraints gbc_txtTextBox = new GridBagConstraints();
		gbc_txtTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_txtTextBox.gridx = 1;
		gbc_txtTextBox.gridy = 3;
		add(txtTextBox, gbc_txtTextBox);
		txtTextBox.setText("          Text Field");
		txtTextBox.setColumns(10);
		
		btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 3;
		add(btnNewButton, gbc_btnNewButton);
		
		tree = new JTree();
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 5;
		add(tree, gbc_tree);
		
		txtpnTextPane = new JTextPane();
		txtpnTextPane.setText("Text Pane");
		GridBagConstraints gbc_txtpnTextPane = new GridBagConstraints();
		gbc_txtpnTextPane.insets = new Insets(0, 0, 5, 5);
		gbc_txtpnTextPane.fill = GridBagConstraints.BOTH;
		gbc_txtpnTextPane.gridx = 2;
		gbc_txtpnTextPane.gridy = 5;
		add(txtpnTextPane, gbc_txtpnTextPane);
		
		txtrTextaea = new JTextArea();
		GridBagConstraints gbc_txtrTextaea = new GridBagConstraints();
		gbc_txtrTextaea.fill = GridBagConstraints.BOTH;
		gbc_txtrTextaea.insets = new Insets(0, 0, 5, 5);
		gbc_txtrTextaea.gridx = 1;
		gbc_txtrTextaea.gridy = 5;
		add(txtrTextaea, gbc_txtrTextaea);
		txtrTextaea.setText("           TextAea");
		

		
		tglbtnToggleButton = new JToggleButton("Toggle Button");
		GridBagConstraints gbc_tglbtnToggleButton = new GridBagConstraints();
		gbc_tglbtnToggleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnToggleButton.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnToggleButton.gridx = 0;
		gbc_tglbtnToggleButton.gridy = 6;
		add(tglbtnToggleButton, gbc_tglbtnToggleButton);
		
		passwordField = new JPasswordField();
		passwordField.setText("Password ");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 0, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 6;
		add(passwordField, gbc_passwordField);
		
		spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 6;
		add(spinner, gbc_spinner);
		
		comboBox_1.addItem("CombBox");


	}

}
