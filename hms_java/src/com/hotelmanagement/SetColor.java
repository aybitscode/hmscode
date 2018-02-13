package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import setup.Register;


public class SetColor extends JPanel implements ChangeListener,ActionListener,ItemListener {
	JRadioButton chckbxMainTitle,chckbxContent;
	JColorChooser chh;
	private JLabel lblColor,lblCategory;
	JButton btnOk,btnCancel,btnApply;
	ButtonGroup bg;
	public static int cColor,bkColor,mtColor,tbColor;
	public static int cbxCod;
	private JRadioButton rdbtnBackground;
	private JPanel panel;
	private JPanel panel_1;
	private JCheckBox chckbxDefault;
	int uscod;
	private JRadioButton rdbtnTabs;
	Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	public SetColor() {

		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE, 0.0};
		setLayout(gridBagLayout);
		
		lblColor = new JLabel("Choose the Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblColor.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblCooseTheColors = new GridBagConstraints();
		gbc_lblCooseTheColors.gridwidth = 3;
		gbc_lblCooseTheColors.insets = new Insets(0, 0, 5, 0);
		gbc_lblCooseTheColors.gridx = 0;
		gbc_lblCooseTheColors.gridy = 0;
		add(lblColor, gbc_lblCooseTheColors);
		chh=new JColorChooser(lblColor.getForeground());
		chh.getSelectionModel().addChangeListener(this);
        
        lblCategory = new JLabel("Category         ");
        lblCategory.setForeground(Color.WHITE);
        lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
        GridBagConstraints gbc_lblCategory = new GridBagConstraints();
        gbc_lblCategory.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
        gbc_lblCategory.gridx = 0;
        gbc_lblCategory.gridy = 1;
        add(lblCategory, gbc_lblCategory);
        
        bg=new ButtonGroup();
        
        panel_1 = new JPanel();
        panel_1.setBackground(Color.BLACK);
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.anchor = GridBagConstraints.WEST;
        gbc_panel_1.fill = GridBagConstraints.VERTICAL;
        gbc_panel_1.insets = new Insets(0, 0, 5, 5);
        gbc_panel_1.gridx = 1;
        gbc_panel_1.gridy = 1;
        add(panel_1, gbc_panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_panel_1.rowHeights = new int[]{0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        panel_1.setLayout(gbl_panel_1);
        
        chckbxMainTitle = new JRadioButton("Titles");
        GridBagConstraints gbc_chckbxMainTitle = new GridBagConstraints();
        gbc_chckbxMainTitle.insets = new Insets(0, 0, 0, 5);
        gbc_chckbxMainTitle.gridx = 0;
        gbc_chckbxMainTitle.gridy = 0;
        panel_1.add(chckbxMainTitle, gbc_chckbxMainTitle);
        chckbxMainTitle.setBackground(Color.BLACK);
        chckbxMainTitle.setForeground(Color.WHITE);
        chckbxMainTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        bg.add(chckbxMainTitle);
        
        chckbxContent = new JRadioButton("Content");
        GridBagConstraints gbc_chckbxContent = new GridBagConstraints();
        gbc_chckbxContent.insets = new Insets(0, 0, 0, 5);
        gbc_chckbxContent.gridx = 1;
        gbc_chckbxContent.gridy = 0;
        panel_1.add(chckbxContent, gbc_chckbxContent);
        chckbxContent.setBackground(Color.BLACK);
        chckbxContent.setForeground(Color.WHITE);
        chckbxContent.setFont(new Font("Tahoma", Font.PLAIN, 14));
        bg.add(chckbxContent);
        
        rdbtnTabs = new JRadioButton("Tabs");
        rdbtnTabs.setBackground(Color.BLACK);
        rdbtnTabs.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnTabs.setForeground(Color.WHITE);
        GridBagConstraints gbc_rdbtnTabs = new GridBagConstraints();
        gbc_rdbtnTabs.insets = new Insets(0, 0, 0, 5);
        gbc_rdbtnTabs.gridx = 2;
        gbc_rdbtnTabs.gridy = 0;
        panel_1.add(rdbtnTabs, gbc_rdbtnTabs);
        bg.add(rdbtnTabs);
        
        rdbtnBackground = new JRadioButton("Background");
        GridBagConstraints gbc_rdbtnBackground = new GridBagConstraints();
        gbc_rdbtnBackground.gridx = 3;
        gbc_rdbtnBackground.gridy = 0;
        panel_1.add(rdbtnBackground, gbc_rdbtnBackground);
        rdbtnBackground.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnBackground.setForeground(Color.WHITE);
        rdbtnBackground.setBackground(Color.BLACK);
        bg.add(rdbtnBackground);

        
        chh.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));
		GridBagConstraints gbc_ch = new GridBagConstraints();
		gbc_ch.gridwidth = 3;
		gbc_ch.insets = new Insets(0, 0, 5, 0);
		gbc_ch.gridx = 0;
		gbc_ch.gridy = 2;
		add(chh, gbc_ch);
		
		chckbxDefault = new JCheckBox("Default");
		chckbxDefault.setForeground(Color.WHITE);
		chckbxDefault.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDefault.setBackground(Color.BLACK);
		GridBagConstraints gbc_chckbxDefault = new GridBagConstraints();
		gbc_chckbxDefault.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxDefault.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDefault.gridx = 0;
		gbc_chckbxDefault.gridy = 3;
		add(chckbxDefault, gbc_chckbxDefault);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnOk = new JButton("Ok");
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.EAST;
		gbc_btnOk.insets = new Insets(0, 0, 0, 5);
		gbc_btnOk.gridx = 0;
		gbc_btnOk.gridy = 0;
		panel.add(btnOk, gbc_btnOk);
		
		btnApply = new JButton("Apply");
		GridBagConstraints gbc_btnApply = new GridBagConstraints();
		gbc_btnApply.anchor = GridBagConstraints.EAST;
		gbc_btnApply.insets = new Insets(0, 0, 0, 5);
		gbc_btnApply.gridx = 1;
		gbc_btnApply.gridy = 0;
		panel.add(btnApply, gbc_btnApply);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
		
		chckbxDefault.addItemListener(this);
        rdbtnBackground.addActionListener(this);
        chckbxContent.addActionListener(this);
		chckbxMainTitle.addActionListener(this);
		rdbtnTabs.addActionListener(this);
		btnCancel.addActionListener(this);
		btnApply.addActionListener(this);
		btnOk.addActionListener(this);
	}
        

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		if(cbxCod==1)
		{
			Color clr=chh.getColor();
			mtColor=clr.getRGB();
	        lblColor.setForeground(new Color(mtColor));
	       lblCategory.setForeground(new Color(mtColor));
		}
		else if(cbxCod==2)
		{
			Color clr=chh.getColor();
			cColor=clr.getRGB();
			lblColor.setForeground(new Color(cColor));
			lblCategory.setForeground(new Color(cColor));
		}
		else if(cbxCod==3)
		{
			Color clr=chh.getColor();
			bkColor=clr.getRGB();
			lblColor.setForeground(new Color(bkColor));
			lblCategory.setForeground(new Color(bkColor));
		}
		else if(cbxCod==4)
		{
			Color clr=chh.getColor();
			tbColor=clr.getRGB();
			lblColor.setForeground(new Color(tbColor));
			lblCategory.setForeground(new Color(tbColor));
		}
		else
		{
			JOptionPane.showMessageDialog(this, "Select a category first and then apply the color", "Message", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void setPrefs(String cid, int cVal)
	{
	    Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	    prefs.putInt(cid, cVal);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnOk)
		{
			if(uscod==1)
			{	
				setPrefs("mtColor", mtColor);
				setPrefs("cColor", cColor);
				setPrefs("bkColor", bkColor);
				setPrefs("tbColor", tbColor);
				//MainPage.tabbedPane.setForeground(new Color(tbColor));
				MainPage.mtColor();
				MainPage.welcome.welMTColor();
				MainPage.cColor();
				MainPage.welcome.welbkColor();
				MainPage.bkColor();
			}
			else
			{
				if(cbxCod==1)
				{
					MainPage.mtColor();
					MainPage.welcome.welMTColor();
				setPrefs("mtColor", mtColor);
				}
				else if(cbxCod==2)
				{
					MainPage.cColor();
				setPrefs("cColor", cColor);
				}
				else if(cbxCod==3)
				{
				MainPage.welcome.welbkColor();
				MainPage.bkColor();
				setPrefs("bkColor", bkColor);
				}
				else if(cbxCod==4)
				{
					//MainPage.tabbedPane.setForeground(new Color(tbColor));
					setPrefs("tbColor", tbColor);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Select a category first and then apply the color", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		MainPage.st.df.setVisible(false);
		}
		else if(arg0.getSource()==btnApply)
		{
			if(uscod==1)
			{	
				setPrefs("mtColor", mtColor);
				setPrefs("cColor", cColor);
				setPrefs("bkColor", bkColor);
				setPrefs("tbColor", tbColor);
				//MainPage.tabbedPane.setForeground(new Color(tbColor));
				MainPage.mtColor();
				MainPage.welcome.welMTColor();
				MainPage.cColor();
				MainPage.welcome.welbkColor();
				MainPage.bkColor();
			}
			else
			{
				if(cbxCod==1)
				{
					MainPage.mtColor();
					MainPage.welcome.welMTColor();
				setPrefs("mtColor", mtColor);
				}
				else if(cbxCod==2)
				{
					MainPage.cColor();
				setPrefs("cColor", cColor);
				}
				else if(cbxCod==3)
				{
				MainPage.welcome.welbkColor();
				MainPage.bkColor();
				setPrefs("bkColor", bkColor);
				}
				else if(cbxCod==4)
				{
					//MainPage.tabbedPane.setForeground(new Color(tbColor));
					setPrefs("tbColor", tbColor);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Select a category first and then apply the color", "Message", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		}
		else if(arg0.getSource()==btnCancel)
		{
			MainPage.st.df.dispose();
		}
		else if(arg0.getSource()==chckbxMainTitle)
		{
			if(chckbxMainTitle.isSelected())
			cbxCod=1;
		}
		else if(arg0.getSource()==chckbxContent)
		{
			if(chckbxContent.isSelected())
			cbxCod=2;
		}
		else if(arg0.getSource()==rdbtnBackground)
		{
			if(rdbtnBackground.isSelected())
				cbxCod=3;
		}
		else if(arg0.getSource()==rdbtnTabs)
		{
				if(rdbtnTabs.isSelected())
				cbxCod=4;
		}
	}
    private void setPrefsC(String cid, int cVal)
    {
	    prefs.putInt(cid, cVal);
	    prefs.putInt("wbcColor", 1);	    
    }
    private void setPrefsD(String did, String dval)
    {
    	prefs.put("productType", "Trial Version");
    }
    private void setColor()
    {
    	System.out.println("from inside set color bro");;
		setPrefsC("mtColor", -26368);
		setPrefsC("cColor", -1);
		setPrefsC("bkColor", 4210752);
		setPrefsC("stColor", -26368);
		setPrefsC("tbColor", -16777216);		
				
    }
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==chckbxDefault)
		{
			if(chckbxDefault.isSelected())
			{
				uscod=1;
				//Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);
				setColor();
				MainPage.defaultColors(prefs);
				
			}
			
		}
	}

}
