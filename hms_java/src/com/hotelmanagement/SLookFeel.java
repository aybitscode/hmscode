package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;



public class SLookFeel extends JPanel implements ActionListener,ItemListener{

	private JLabel lblComponent;
	private JComboBox <String>comboBox_1;
	private JRadioButton rdbtnBold;
	private JTextArea txtrTextaea;
	private JButton btnNewButton;
	private JList<Object> list;
	private JTextField txtTextBox;
	private JPanel panel;
	private JTextPane txtpnTextPane;
	private JSpinner spinner;
	private JTree tree;
	private JButton btnSave;
	private JButton btnApply;
	private JButton btnCancel;
	private JRadioButton rdbtnNimbus;
	private JRadioButton rdbtnWindows;
	private JRadioButton rdbtnMetalic;
	private JRadioButton rdbtnMotif;
	ButtonGroup bg;
	private JPasswordField passwordField;
	private JCheckBox checkBox;
	private JToggleButton tglbtnToggleButton;
	public static String lfType;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel label;
	private JCheckBox chckbxDefault;
	int uscod;
	Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	public SLookFeel() {
		bg=new ButtonGroup();
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		label = new JLabel("                                         ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		add(label, gbc_label);
		
		lblComponent = new JLabel("Look & Feel Type");
		lblComponent.setForeground(Color.WHITE);
		lblComponent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblComponent = new GridBagConstraints();
		gbc_lblComponent.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblComponent.insets = new Insets(0, 0, 5, 5);
		gbc_lblComponent.gridx = 0;
		gbc_lblComponent.gridy = 1;
		add(lblComponent, gbc_lblComponent);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		rdbtnNimbus = new JRadioButton("Nimbus");
		GridBagConstraints gbc_rdbtnNimbus = new GridBagConstraints();
		gbc_rdbtnNimbus.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnNimbus.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnNimbus.gridx = 0;
		gbc_rdbtnNimbus.gridy = 0;
		panel_1.add(rdbtnNimbus, gbc_rdbtnNimbus);
		rdbtnNimbus.setBackground(Color.BLACK);
		rdbtnNimbus.setForeground(Color.WHITE);
		rdbtnNimbus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbtnNimbus);
		
		rdbtnWindows = new JRadioButton("Windows");
		GridBagConstraints gbc_rdbtnWindows = new GridBagConstraints();
		gbc_rdbtnWindows.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnWindows.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnWindows.gridx = 1;
		gbc_rdbtnWindows.gridy = 0;
		panel_1.add(rdbtnWindows, gbc_rdbtnWindows);
		rdbtnWindows.setBackground(Color.BLACK);
		rdbtnWindows.setForeground(Color.WHITE);
		rdbtnWindows.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbtnWindows);
		
		rdbtnMetalic = new JRadioButton("Metalic");
		GridBagConstraints gbc_rdbtnMetalic = new GridBagConstraints();
		gbc_rdbtnMetalic.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnMetalic.insets = new Insets(0, 0, 0, 5);
		gbc_rdbtnMetalic.gridx = 2;
		gbc_rdbtnMetalic.gridy = 0;
		panel_1.add(rdbtnMetalic, gbc_rdbtnMetalic);
		rdbtnMetalic.setBackground(Color.BLACK);
		rdbtnMetalic.setForeground(Color.WHITE);
		rdbtnMetalic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbtnMetalic);
		
		rdbtnMotif = new JRadioButton("Motif");
		GridBagConstraints gbc_rdbtnMotif = new GridBagConstraints();
		gbc_rdbtnMotif.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnMotif.gridx = 3;
		gbc_rdbtnMotif.gridy = 0;
		panel_1.add(rdbtnMotif, gbc_rdbtnMotif);
		rdbtnMotif.setBackground(Color.BLACK);
		rdbtnMotif.setForeground(Color.WHITE);
		rdbtnMotif.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bg.add(rdbtnMotif);
		rdbtnMotif.addActionListener(this);
		rdbtnMetalic.addActionListener(this);
		rdbtnWindows.addActionListener(this);
		rdbtnNimbus.addActionListener(this);
		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.inactiveCaptionText);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Components", TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		checkBox = new JCheckBox("Checkbox");
		checkBox.setForeground(Color.WHITE);
		checkBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox.setBackground(Color.BLACK);
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 0;
		panel.add(checkBox, gbc_checkBox);
		
		
		JLabel lblFontType = new JLabel("Label");
		GridBagConstraints gbc_lblFontType = new GridBagConstraints();
		gbc_lblFontType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFontType.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontType.gridx = 1;
		gbc_lblFontType.gridy = 0;
		panel.add(lblFontType, gbc_lblFontType);
		lblFontType.setForeground(Color.WHITE);
		lblFontType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		comboBox_1 = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 0;
		panel.add(comboBox_1, gbc_comboBox_1);
		
		rdbtnBold = new JRadioButton("RadioButton");
		GridBagConstraints gbc_rdbtnBold = new GridBagConstraints();
		gbc_rdbtnBold.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnBold.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBold.gridx = 0;
		gbc_rdbtnBold.gridy = 1;
		panel.add(rdbtnBold, gbc_rdbtnBold);
		rdbtnBold.setBackground(Color.BLACK);
		rdbtnBold.setForeground(Color.WHITE);
		rdbtnBold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtTextBox = new JTextField();
		GridBagConstraints gbc_txtTextBox = new GridBagConstraints();
		gbc_txtTextBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTextBox.insets = new Insets(0, 0, 5, 5);
		gbc_txtTextBox.gridx = 1;
		gbc_txtTextBox.gridy = 1;
		panel.add(txtTextBox, gbc_txtTextBox);
		txtTextBox.setText("          Text Box");
		txtTextBox.setColumns(10);
		
		btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		tree = new JTree();
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.insets = new Insets(0, 0, 5, 5);
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 3;
		panel.add(tree, gbc_tree);
		
		txtpnTextPane = new JTextPane();
		txtpnTextPane.setText("Text Pane");
		GridBagConstraints gbc_txtpnTextPane = new GridBagConstraints();
		gbc_txtpnTextPane.insets = new Insets(0, 0, 5, 0);
		gbc_txtpnTextPane.fill = GridBagConstraints.BOTH;
		gbc_txtpnTextPane.gridx = 2;
		gbc_txtpnTextPane.gridy = 3;
		panel.add(txtpnTextPane, gbc_txtpnTextPane);
		
		txtrTextaea = new JTextArea();
		GridBagConstraints gbc_txtrTextaea = new GridBagConstraints();
		gbc_txtrTextaea.fill = GridBagConstraints.BOTH;
		gbc_txtrTextaea.insets = new Insets(0, 0, 5, 5);
		gbc_txtrTextaea.gridx = 1;
		gbc_txtrTextaea.gridy = 3;
		panel.add(txtrTextaea, gbc_txtrTextaea);
		txtrTextaea.setText("           TextAea");
		
		list = new JList<Object>();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 2;
		panel.add(list, gbc_list);
		
		tglbtnToggleButton = new JToggleButton("Toggle Button");
		GridBagConstraints gbc_tglbtnToggleButton = new GridBagConstraints();
		gbc_tglbtnToggleButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnToggleButton.insets = new Insets(0, 0, 0, 5);
		gbc_tglbtnToggleButton.gridx = 0;
		gbc_tglbtnToggleButton.gridy = 4;
		panel.add(tglbtnToggleButton, gbc_tglbtnToggleButton);
		
		passwordField = new JPasswordField();
		passwordField.setText("Password ");
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 0, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 4;
		panel.add(passwordField, gbc_passwordField);
		
		spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 4;
		panel.add(spinner, gbc_spinner);
		
		chckbxDefault = new JCheckBox("Default");
		chckbxDefault.setBackground(Color.BLACK);
		chckbxDefault.setForeground(Color.WHITE);
		chckbxDefault.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxDefault = new GridBagConstraints();
		gbc_chckbxDefault.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxDefault.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDefault.gridx = 0;
		gbc_chckbxDefault.gridy = 3;
		add(chckbxDefault, gbc_chckbxDefault);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 3;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		btnSave = new JButton("Ok");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 3;
		gbc_btnSave.gridy = 0;
		panel_2.add(btnSave, gbc_btnSave);
		
		btnApply = new JButton("Apply");
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.anchor = GridBagConstraints.EAST;
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 4;
		gbc_btnApply.gridy = 0;
		panel_2.add(btnApply, gbc_btnApply);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.gridx = 5;
		gbc_btnCancel.gridy = 0;
		panel_2.add(btnCancel, gbc_btnCancel);
		
		comboBox_1.addItem("CombBox"); 

			System.out.println(lfType);
		if(lfType.equals("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"))
		rdbtnNimbus.setSelected(true);
		else if(lfType.equals("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"))
		rdbtnWindows.setSelected(true);
		else if(lfType.equals("javax.swing.plaf.metal.MetalLookAndFeel"))
		rdbtnMetalic.setSelected(true);
		else if(lfType.equals("com.sun.java.swing.plaf.motif.MotifLookAndFeel"))
		rdbtnMotif.setSelected(true);
		else 
		{}
		
		
		chckbxDefault.addItemListener(this);
		btnCancel.addActionListener(this);
		btnApply.addActionListener(this);
		btnSave.addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnSave)
		{		
			if(uscod==1)
			{
				System.out.println("lftype is"+lfType);
				setPrefs("lfType", lfType);
				try {
					UIManager.setLookAndFeel(lfType);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//MainPage.tabbedPane.updateUI();	
				
			
			}
			else
			{
				setPrefs("lfType", lfType);
				//MainPage.tabbedPane.updateUI();
			}
			MainPage.st.df.dispose();
		}
		else if(arg0.getSource()==btnApply)
		{
			if(uscod==1)
			{
				
				setPrefs("lfType", lfType);
				try {
					UIManager.setLookAndFeel(lfType);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//MainPage.tabbedPane.updateUI();	
			}
			else
			{

			//MainPage.tabbedPane.updateUI();	
			setPrefs("lfType", lfType);
			}
		}
		else if(arg0.getSource()==btnCancel)
		{
			try {
				UIManager.setLookAndFeel(lfType);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainPage.st.df.dispose();
		}
		else if(arg0.getSource()==rdbtnNimbus)
		{
			lfType="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
			try {
				UIManager.setLookAndFeel(lfType);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Settings.jtb.updateUI();
			Settings.jtb.setSelectedIndex(1);
			Settings.jtb.setSelectedIndex(2);
		}
		else if(arg0.getSource()==rdbtnWindows)
		{
			lfType="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
			try {
				UIManager.setLookAndFeel(lfType);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Settings.jtb.updateUI();
			Settings.jtb.setSelectedIndex(1);
			Settings.jtb.setSelectedIndex(2);
		}
		else if(arg0.getSource()==rdbtnMetalic)
		{
			lfType ="javax.swing.plaf.metal.MetalLookAndFeel";
			try {
				UIManager.setLookAndFeel(lfType);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Settings.jtb.updateUI();
			Settings.jtb.setSelectedIndex(1);
			Settings.jtb.setSelectedIndex(2);
		}
		else if(arg0.getSource()==rdbtnMotif)
		{
			lfType ="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
			try {
				UIManager.setLookAndFeel(lfType);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Settings.jtb.updateUI();
			Settings.jtb.setSelectedIndex(1);
			Settings.jtb.setSelectedIndex(2);

		}
		
	}
	public void setPrefs(String lfid, String lftStr)
	{
	    Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	    prefs.put(lfid, lftStr);
	}
	private void slookfeel()
    {
		updatePrefs("lfType", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    }
	private void updatePrefs(String lfid, String lftStr)
	{
	    prefs.put(lfid, lftStr);
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==chckbxDefault)
		{
			if(chckbxDefault.isSelected())
			{
				
				lfType ="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
				try {
					UIManager.setLookAndFeel(lfType);
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Settings.jtb.updateUI();
				Settings.jtb.setSelectedIndex(1);
				Settings.jtb.setSelectedIndex(2);
				bg.clearSelection();
				slookfeel();
				uscod=1;
				Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
				MainPage.defaultLFeel(prefs);
				chckbxDefault.setSelected(true);
			}
		}
	}


}
