package com.hotelmanagement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.prefs.Preferences;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class SLayout extends JPanel implements ChangeListener,ActionListener,ItemListener {
	JPanel panel,panel_3;
	int lpwidth,lpheight,lpx,lpy;
	int tempwidth,tempheight;
	JSpinner spinner,spinner_1,spinner_2,spinner_3;
	private JButton btnSave,btnApply,btnCancel;
	ImageIcon icon;
	private JCheckBox chckbxDefault;
	int uscod;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	MainPage mpg;
	public SLayout(MainPage mpg) {
		this.mpg=mpg;
		tempwidth=mpg.scrwidth;
		tempheight=mpg.scrheight;
		lpwidth=mpg.scrwidth/3;
		lpheight=mpg.scrheight/3;
		lpx=mpg.scrlx/3;
		lpy=mpg.scrly/3;
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{(d.width)/3, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, (d.height)/3, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		System.out.println("d.iwdth and height is"+d.width/3+"  "+d.height/3);
		JLabel lblLayoutProperties = new JLabel("Layout Properties");
		lblLayoutProperties.setForeground(Color.WHITE);
		lblLayoutProperties.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLayoutProperties = new GridBagConstraints();
		gbc_lblLayoutProperties.insets = new Insets(0, 0, 5, 5);
		gbc_lblLayoutProperties.gridx = 0;
		gbc_lblLayoutProperties.gridy = 0;
		add(lblLayoutProperties, gbc_lblLayoutProperties);
		
		panel = new JPanel();
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		panel_3 = new JPanel(){
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), lpx, lpy, lpwidth, lpheight, null);
				}
				};
		panel.add(panel_3);
		panel_3.setBounds(lpx, lpy,lpwidth,lpheight);
		System.out.println("lpwidth value is"+lpwidth);
		icon=new ImageIcon(SLayout.class.getResource("/images/scrn.png"));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Window Size", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 70, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblWidth = new JLabel("Width");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 0;
		panel_1.add(lblWidth, gbc_lblWidth);
		lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWidth.setForeground(Color.WHITE);
		
		spinner = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel_1.add(spinner, gbc_spinner);
		
		JLabel lblHeight = new JLabel("Height");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 0, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 1;
		panel_1.add(lblHeight, gbc_lblHeight);
		lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHeight.setForeground(Color.WHITE);
		
		spinner_1 = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 1;
		panel_1.add(spinner_1, gbc_spinner_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Window Position", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 70, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label_1 = new JLabel("Width");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 0;
		panel_2.add(label_1, gbc_label_1);
		
		spinner_2 = new JSpinner();
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_2.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 0;
		panel_2.add(spinner_2, gbc_spinner_2);
		
		JLabel label_2 = new JLabel("Height");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 1;
		panel_2.add(label_2, gbc_label_2);
		
		spinner_3 = new JSpinner();
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 1;
		panel_2.add(spinner_3, gbc_spinner_3);
		
		chckbxDefault = new JCheckBox("Default");
		chckbxDefault.setBackground(Color.BLACK);
		chckbxDefault.setForeground(Color.WHITE);
		chckbxDefault.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxDefault = new GridBagConstraints();
		gbc_chckbxDefault.anchor = GridBagConstraints.WEST;
		gbc_chckbxDefault.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDefault.gridx = 0;
		gbc_chckbxDefault.gridy = 3;
		add(chckbxDefault, gbc_chckbxDefault);
		
		btnSave = new JButton("Ok");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.anchor = GridBagConstraints.EAST;
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 3;
		add(btnSave, gbc_btnSave);
		
		btnApply = new JButton("Apply");
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.anchor = GridBagConstraints.EAST;
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 2;
		gbc_btnApply.gridy = 3;
		add(btnApply, gbc_btnApply);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.gridx = 3;
		gbc_btnCancel.gridy = 3;
		add(btnCancel, gbc_btnCancel);
	
		spinner.setValue(mpg.scrwidth);
		spinner_1.setValue(mpg.scrheight);
		spinner_2.setValue(mpg.scrlx);
		spinner_3.setValue(mpg.scrly);
		
		chckbxDefault.addItemListener(this);
		spinner.addChangeListener(this);
		spinner_1.addChangeListener(this);
		spinner_2.addChangeListener(this);
		spinner_3.addChangeListener(this);
		btnSave.addActionListener(this);
		btnApply.addActionListener(this);
		btnCancel.addActionListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==spinner||arg0.getSource()==spinner_1||arg0.getSource()==spinner_2||arg0.getSource()==spinner_3)
		{
			if(chckbxDefault.isSelected())
				chckbxDefault.setSelected(false);
		}
		if(arg0.getSource()==spinner)
		{
			mpg.scrwidth=(int) spinner.getValue();
			lpwidth=mpg.scrwidth/3;
			panel_3.setBounds(lpx, lpy, lpwidth, lpheight);
			updateUI();
		}
		else if(arg0.getSource()==spinner_1)
		{
			mpg.scrheight=(int) spinner_1.getValue();
			lpheight=mpg.scrheight/3;
			panel_3.setBounds(lpx, lpy , lpwidth, lpheight);
			updateUI();
		}
		else if(arg0.getSource()==spinner_2)
		{
			mpg.scrlx=(int) spinner_2.getValue();
			lpx=mpg.scrlx/3;
			panel_3.setBounds(lpx, lpy, lpwidth,lpheight);
			updateUI();
		}
		else if(arg0.getSource()==spinner_3)
		{
			mpg.scrly=(int) spinner_3.getValue();
			lpy=mpg.scrly/3;
			panel_3.setBounds(lpx, lpy, lpwidth,lpheight);
			updateUI();
		}
		
	}

	public void setPrefs(String wWidth, String wHeight, String wXval, String wYval, int width, int height, int xval, int yval)
	{
	    Preferences prefs = Preferences.userRoot().node("com/feemgmt");
	    prefs.putInt(wWidth, width);
	    prefs.putInt(wHeight, height);
	    prefs.putInt(wXval, xval);
	    prefs.putInt(wYval, yval);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnSave)
		{		
			if(uscod==1)
			{
				setPrefs("width","height","xpos","ypos", mpg.scrwidth,mpg.scrheight,mpg.scrlx,mpg.scrly);
				mpg.setSize(mpg.scrwidth,mpg.scrheight);
				mpg.setLocation(mpg.scrlx, mpg.scrly);
			}
			else
			{
			setPrefs("width","height","xpos","ypos", mpg.scrwidth,mpg.scrheight,mpg.scrlx,mpg.scrly);
			mpg.setSize(mpg.scrwidth,mpg.scrheight);
			mpg.setLocation(mpg.scrlx, mpg.scrly);
			}
			mpg.st.df.setVisible(false);
		}
		else if(arg0.getSource()==btnApply)
		{
			if(uscod==1)
			{
				setPrefs("width","height","xpos","ypos", mpg.scrwidth,mpg.scrheight,mpg.scrlx,mpg.scrly);
				mpg.setSize(mpg.scrwidth,mpg.scrheight);
				mpg.setLocation(mpg.scrlx, mpg.scrly);
			}
			else
			{
			setPrefs("width","height","xpos","ypos", mpg.scrwidth,mpg.scrheight,mpg.scrlx,mpg.scrly);
			mpg.setSize(mpg.scrwidth,mpg.scrheight);
			mpg.setLocation(mpg.scrlx, mpg.scrly);
			}
			
		}
		else if(arg0.getSource()==btnCancel)
		{
			mpg.scrwidth=tempwidth;
			mpg.scrheight=tempheight;
			mpg.st.df.dispose();
		}
		else
		{
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==chckbxDefault)
		{
			if(chckbxDefault.isSelected())
			{
				uscod=1;
				Preferences prefs = Preferences.userRoot().node("com/deffeemgmt");
				mpg.defaultLayoutProperties(prefs);
				lpwidth=mpg.scrwidth/3;
				lpheight=mpg.scrheight/3;
				panel_3.setBounds(lpx, lpy , lpwidth, lpheight);
				spinner.setValue(mpg.scrwidth);
				spinner_1.setValue(mpg.scrheight);
				spinner_2.setValue(mpg.scrlx);
				spinner_3.setValue(mpg.scrly);
				panel_3.updateUI();
			}
		}
	}
}
