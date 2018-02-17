package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.prefs.Preferences;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;



public class SFont extends JPanel implements ItemListener,ActionListener{
	JTextArea textArea;
	private JButton btnOk, btnCancel,btnApply;
	ButtonGroup bg,bg1;
	private JLabel lblComponent;
	private JLabel lblFontProperty;
	private JRadioButton chckbxMainTitle;
	private JRadioButton chckbxSubTitle;
	private JRadioButton chckbxContent;
	private JComboBox <String>comboBox;
	private JComboBox <Integer>comboBox_3;
	private JRadioButton rdbtnBold;
	private JRadioButton rdbtnPlain;
	private JRadioButton rdbtnItalic;
	private JLabel lblTitle;
	private JPanel panel;
	private JPanel panel_1;
	public static int mtSize,stSize,ctSize,mtfProp,stfProp,ctfProp,tbSize,tbfProp;
	int chcod, uscod;
	public static String mtFType,stFType,ctFType,tbFType;
	Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	private JRadioButton rdbtnTabs;
	private JCheckBox chckbxDefault;
	public SFont(MainPage MainPage) {
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{100, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblTitle = new JLabel("");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		lblComponent = new JLabel("Category");
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
		gbc_panel_1.anchor = GridBagConstraints.WEST;
		gbc_panel_1.fill = GridBagConstraints.VERTICAL;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		chckbxMainTitle = new JRadioButton("Main Titles");
		GridBagConstraints gbc_chckbxMainTitle = new GridBagConstraints();
		gbc_chckbxMainTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxMainTitle.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxMainTitle.gridx = 0;
		gbc_chckbxMainTitle.gridy = 0;
		panel_1.add(chckbxMainTitle, gbc_chckbxMainTitle);
		chckbxMainTitle.setForeground(Color.WHITE);
		chckbxMainTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMainTitle.setBackground(Color.BLACK);
		
		chckbxSubTitle = new JRadioButton("Sub Titles");
		GridBagConstraints gbc_chckbxSubTitle = new GridBagConstraints();
		gbc_chckbxSubTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxSubTitle.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSubTitle.gridx = 1;
		gbc_chckbxSubTitle.gridy = 0;
		panel_1.add(chckbxSubTitle, gbc_chckbxSubTitle);
		chckbxSubTitle.setForeground(Color.WHITE);
		chckbxSubTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSubTitle.setBackground(Color.BLACK);
		
		chckbxContent = new JRadioButton("Content");
		GridBagConstraints gbc_chckbxContent = new GridBagConstraints();
		gbc_chckbxContent.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxContent.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxContent.gridx = 2;
		gbc_chckbxContent.gridy = 0;
		panel_1.add(chckbxContent, gbc_chckbxContent);
		chckbxContent.setForeground(Color.WHITE);
		chckbxContent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxContent.setBackground(Color.BLACK);
		
		
		JLabel lblFontType = new JLabel("Font Type");
		lblFontType.setForeground(Color.WHITE);
		lblFontType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFontType = new GridBagConstraints();
		gbc_lblFontType.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFontType.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontType.gridx = 0;
		gbc_lblFontType.gridy = 2;
		add(lblFontType, gbc_lblFontType);
		
		comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		add(comboBox, gbc_comboBox);
		
		JLabel lblFontSize = new JLabel("Font Size");
		lblFontSize.setForeground(Color.WHITE);
		lblFontSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFontSize = new GridBagConstraints();
		gbc_lblFontSize.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFontSize.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontSize.gridx = 0;
		gbc_lblFontSize.gridy = 3;
		add(lblFontSize, gbc_lblFontSize);
		bg=new ButtonGroup();
		bg1=new ButtonGroup();
		
		comboBox_3 = new JComboBox<Integer>();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.anchor = GridBagConstraints.WEST;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.gridx = 1;
		gbc_comboBox_3.gridy = 3;
		add(comboBox_3, gbc_comboBox_3);
		
		lblFontProperty = new JLabel("Font Property");
		lblFontProperty.setForeground(Color.WHITE);
		lblFontProperty.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFontProperty = new GridBagConstraints();
		gbc_lblFontProperty.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFontProperty.insets = new Insets(0, 0, 5, 5);
		gbc_lblFontProperty.gridx = 0;
		gbc_lblFontProperty.gridy = 4;
		add(lblFontProperty, gbc_lblFontProperty);
		
		rdbtnBold = new JRadioButton("Bold");
		rdbtnBold.setBackground(Color.BLACK);
		rdbtnBold.setForeground(Color.WHITE);
		rdbtnBold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnBold = new GridBagConstraints();
		gbc_rdbtnBold.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnBold.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBold.gridx = 1;
		gbc_rdbtnBold.gridy = 4;
		add(rdbtnBold, gbc_rdbtnBold);
		
		rdbtnPlain = new JRadioButton("Plain");
		rdbtnPlain.setBackground(Color.BLACK);
		rdbtnPlain.setForeground(Color.WHITE);
		rdbtnPlain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnPlain = new GridBagConstraints();
		gbc_rdbtnPlain.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnPlain.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPlain.gridx = 1;
		gbc_rdbtnPlain.gridy = 5;
		add(rdbtnPlain, gbc_rdbtnPlain);
		
		rdbtnItalic = new JRadioButton("Italic");
		rdbtnItalic.setBackground(Color.BLACK);
		rdbtnItalic.setForeground(Color.WHITE);
		rdbtnItalic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_rdbtnItalic = new GridBagConstraints();
		gbc_rdbtnItalic.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdbtnItalic.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnItalic.gridx = 1;
		gbc_rdbtnItalic.gridy = 6;
		add(rdbtnItalic, gbc_rdbtnItalic);
		
		JLabel lblPriview = new JLabel("Preview");
		lblPriview.setBackground(new Color(240, 240, 240));
		lblPriview.setForeground(Color.WHITE);
		lblPriview.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblPriview = new GridBagConstraints();
		gbc_lblPriview.anchor = GridBagConstraints.NORTH;
		gbc_lblPriview.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPriview.insets = new Insets(0, 0, 5, 5);
		gbc_lblPriview.gridx = 0;
		gbc_lblPriview.gridy = 7;
		add(lblPriview, gbc_lblPriview);
		
		textArea = new JTextArea(10,5);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridwidth = 2;
		gbc_textArea.gridheight = 2;
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 7;
		add(textArea, gbc_textArea);
		textArea.setText("Main Title");
		textArea.setFont(new Font("Tahoma",Font.PLAIN,14));
		
		chckbxDefault = new JCheckBox("Default");
		chckbxDefault.setBackground(Color.BLACK);
		chckbxDefault.setForeground(Color.WHITE);
		chckbxDefault.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GridBagConstraints gbc_chckbxDefault = new GridBagConstraints();
		gbc_chckbxDefault.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxDefault.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxDefault.gridx = 0;
		gbc_chckbxDefault.gridy = 9;
		add(chckbxDefault, gbc_chckbxDefault);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 9;
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

		
		 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		  String fontNames[] = ge.getAvailableFontFamilyNames();
		  for (int i=0; i<fontNames.length; i++) {
		     comboBox.addItem(fontNames[i]);
		  }
		  for(int i=0;i<100;i++)
		  {
			  comboBox_3.addItem(i);
		  }
			bg.add(rdbtnBold);
			bg.add(rdbtnPlain);
			bg.add(rdbtnItalic);
			

			
			rdbtnTabs = new JRadioButton("Tabs");
			rdbtnTabs.setFont(new Font("Tahoma", Font.PLAIN, 14));
			rdbtnTabs.setBackground(Color.BLACK);
			rdbtnTabs.setForeground(Color.WHITE);
			GridBagConstraints gbc_rdbtnTabs = new GridBagConstraints();
			gbc_rdbtnTabs.gridx = 3;
			gbc_rdbtnTabs.gridy = 0;
			panel_1.add(rdbtnTabs, gbc_rdbtnTabs);
			
			bg1.add(chckbxMainTitle);
			bg1.add(chckbxSubTitle);
			bg1.add(chckbxContent);
			bg1.add(rdbtnTabs);
			
		  comboBox.setSelectedItem(mtFType);
		  comboBox_3.setSelectedItem(mtSize);
		  if(mtfProp==0)
			  rdbtnPlain.setSelected(true);
		  else if(mtfProp==1)
			  rdbtnBold.setSelected(true);
		  else 
			  rdbtnItalic.setSelected(true);
		  chcod=1;
		  textArea.setFont(new Font((String)comboBox.getSelectedItem(),Font.PLAIN,(int) comboBox_3.getSelectedItem()));
		  rdbtnPlain.setSelected(true);
		  

		  chckbxMainTitle.setSelected(true);
		  comboBox.addItemListener(this);
		  comboBox_3.addItemListener(this);
		  
		  rdbtnBold.addItemListener(this);
		  rdbtnPlain.addItemListener(this);
		  rdbtnItalic.addItemListener(this);
		  chckbxDefault.addItemListener(this);
		  
		  chckbxMainTitle.addActionListener(this);
		  chckbxSubTitle.addActionListener(this);
		  chckbxContent.addActionListener(this);
		  rdbtnTabs.addActionListener(this);
		  btnCancel.addActionListener(this);
		  btnApply.addActionListener(this);
		  btnOk.addActionListener(this);

	}
	
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		int temfProp;
		if(rdbtnBold.isSelected())
			temfProp=Font.BOLD;
		else if(rdbtnPlain.isSelected())
			temfProp=Font.PLAIN;
		else 
			temfProp=Font.ITALIC;
		if(arg0.getSource()==comboBox){

			textArea.setFont(new Font((String)comboBox.getSelectedItem(),temfProp,(int) comboBox_3.getSelectedItem()));
		}
		else if(arg0.getSource()==comboBox_3)
		{
			textArea.setFont(new Font((String)comboBox.getSelectedItem(),temfProp,(int) comboBox_3.getSelectedItem()));
		}
		else if(arg0.getSource()==rdbtnBold)
		{
			textArea.setFont(new Font((String)comboBox.getSelectedItem(),Font.BOLD,(int) comboBox_3.getSelectedItem()));
		}
		else if(arg0.getSource()==rdbtnPlain)
		{
			textArea.setFont(new Font((String)comboBox.getSelectedItem(),Font.PLAIN,(int) comboBox_3.getSelectedItem()));
		}
		else if(arg0.getSource()==rdbtnItalic)
		{			
			textArea.setFont(new Font((String)comboBox.getSelectedItem(),Font.ITALIC,(int) comboBox_3.getSelectedItem()));
		}
		else
		{
			
		}
		if(arg0.getSource()==chckbxDefault)
		{
			if(chckbxDefault.isSelected())
			{
				uscod=1;
				setFont();
				MainPage.defaultFontProperties(prefs);
			}
		}
		
	}
	private void setFont()
	{
		System.out.println("from inside s font bro");;
		setPrefs("mtFType", "mtSize", "mtfProp", "Tahoma", 30, 0);
		setPrefs("stFType", "stSize", "stfProp", "Tahoma", 24, 0);
		setPrefs("ctFType", "ctSize", "ctfProp", "Tahoma", 14, 0);
		setPrefs("tbFType", "tbSize", "tbfProp", "Tahoma", 15, 0);
		
		
	}
	public void setPrefs(String fTid, String fSid, String fPid, String fType, int fSize, int fProp)
	{
	    Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
	    prefs.put(fTid, fType);
	    prefs.putInt(fSid, fSize);
	    prefs.putInt(fPid, fProp);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==btnOk)
		{
			if(uscod==1)
			{
				setPrefs("mtFType", "mtSize", "mtfProp", mtFType, mtSize, mtfProp);
				setPrefs("stFType", "stSize", "stfProp", stFType, stSize, stfProp);
				setPrefs("ctFType", "ctSize", "ctfProp", ctFType, ctSize, ctfProp);
				setPrefs("tbFType", "tbSize", "tbfProp", tbFType, tbSize, tbfProp);
				MainPage.mtFont(mtFType, mtfProp, mtSize);
				MainPage.welcome.welMTFont(mtFType,mtfProp,mtSize);
				//MainPage.tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize));
			}
			else
			{
			if(chcod==1)
			{
				mtFType=(String) comboBox.getSelectedItem();
				mtSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					mtfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					mtfProp=Font.PLAIN;
				else 
					mtfProp=Font.ITALIC;
				MainPage.mtFont(mtFType, mtfProp, mtSize);
				MainPage.welcome.welMTFont(mtFType,mtfProp,mtSize);
			setPrefs("mtFType", "mtSize", "mtfProp", mtFType, mtSize, mtfProp);
			}
			else if(chcod==2)
			{
				stFType=(String) comboBox.getSelectedItem();
				stSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					stfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					stfProp=Font.PLAIN;
				else 
					stfProp=Font.ITALIC;
			setPrefs("stFType", "stSize", "stfProp", stFType, stSize, stfProp);
			}
			else if(chcod==3)
			{
				  
				ctFType=(String) comboBox.getSelectedItem();
				ctSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					ctfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					ctfProp=Font.PLAIN;
				else 
					ctfProp=Font.ITALIC;
				MainPage.ctFont(ctFType, ctfProp, ctSize);
			setPrefs("ctFType", "ctSize", "ctfProp", ctFType, ctSize, ctfProp);
			}
			else if(chcod==4)
			{
				tbFType=(String) comboBox.getSelectedItem();
				tbSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					tbfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					tbfProp=Font.PLAIN;
				else 
					tbfProp=Font.ITALIC;
				//MainPage.tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize));
				setPrefs("tbFType", "tbSize", "tbfProp", tbFType, tbSize, tbfProp);
			}
			else {
				JOptionPane.showMessageDialog(this, "Select a category first and then apply the font", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			}
			Settings.df.setVisible(false);
		}
		else if(arg0.getSource()==btnApply)
		{	
			if(uscod==1)
			{
				setPrefs("mtFType", "mtSize", "mtfProp", mtFType, mtSize, mtfProp);
				setPrefs("stFType", "stSize", "stfProp", stFType, stSize, stfProp);
				setPrefs("ctFType", "ctSize", "ctfProp", ctFType, ctSize, ctfProp);
				setPrefs("tbFType", "tbSize", "tbfProp", tbFType, tbSize, tbfProp);
				MainPage.mtFont(mtFType, mtfProp, mtSize);
				MainPage.welcome.welMTFont(mtFType,mtfProp,mtSize);
				//MainPage.tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize));
			}
			else
			{
			if(chcod==1)
			{
				mtFType=(String) comboBox.getSelectedItem();
				mtSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					mtfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					mtfProp=Font.PLAIN;
				else 
					mtfProp=Font.ITALIC;
				MainPage.mtFont(mtFType, mtfProp, mtSize);
				MainPage.welcome.welMTFont(mtFType,mtfProp,mtSize);
			setPrefs("mtFType", "mtSize", "mtfProp", mtFType, mtSize, mtfProp);
			}
			else if(chcod==2)
			{
				stFType=(String) comboBox.getSelectedItem();
				stSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					stfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					stfProp=Font.PLAIN;
				else 
					stfProp=Font.ITALIC;
			setPrefs("stFType", "stSize", "stfProp", stFType, stSize, stfProp);
			}
			else if(chcod==3)
			{
				  
				ctFType=(String) comboBox.getSelectedItem();
				ctSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					ctfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					ctfProp=Font.PLAIN;
				else 
					ctfProp=Font.ITALIC;
			MainPage.ctFont(ctFType, ctfProp, ctSize);
			setPrefs("ctFType", "ctSize", "ctfProp", ctFType, ctSize, ctfProp);
			}
			else if(chcod==4)
			{
				tbFType=(String) comboBox.getSelectedItem();
				tbSize=(int) comboBox_3.getSelectedItem();
				if(rdbtnBold.isSelected())
					tbfProp=Font.BOLD;
				else if(rdbtnPlain.isSelected())
					tbfProp=Font.PLAIN;
				else 
					tbfProp=Font.ITALIC;
				//MainPage.tabbedPane.setFont(new Font(SFont.tbFType,SFont.tbfProp,SFont.tbSize));
				setPrefs("tbFType", "tbSize", "tbfProp", tbFType, tbSize, tbfProp);
			}
			else {
				JOptionPane.showMessageDialog(this, "Select a category first and then apply the font", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			}
		}
		else if(arg0.getSource()==btnCancel)
		{
			MainPage.st.df.dispose();
		}
		else if(arg0.getSource()==chckbxMainTitle)
		{
			chcod=1;
			  comboBox.setSelectedItem(mtFType);
			  comboBox_3.setSelectedItem(mtSize);
			  if(mtfProp==0)
				  rdbtnPlain.setSelected(true);
			  else if(mtfProp==1)
				  rdbtnBold.setSelected(true);
			  else 
				  rdbtnItalic.setSelected(true);
			  

		}
		else if(arg0.getSource()==chckbxSubTitle)
		{
			chcod=2;
			  comboBox.setSelectedItem(stFType);
			  comboBox_3.setSelectedItem(stSize);
			  if(stfProp==0)
				  rdbtnPlain.setSelected(true);
			  else if(stfProp==1)
				  rdbtnBold.setSelected(true);
			  else 
				  rdbtnItalic.setSelected(true);
			  

		}
		else if(arg0.getSource()==chckbxContent)
		{
			chcod=3;
			  comboBox.setSelectedItem(ctFType);
			  comboBox_3.setSelectedItem(ctSize);
			  if(ctfProp==0)
				  rdbtnPlain.setSelected(true);
			  else if(ctfProp==1)
				  rdbtnBold.setSelected(true);
			  else 
				  rdbtnItalic.setSelected(true);

		}
		else if(arg0.getSource()==rdbtnTabs)
		{
			chcod=4;
			  comboBox.setSelectedItem(tbFType);
			  comboBox_3.setSelectedItem(tbSize);
			  if(tbfProp==0)
				  rdbtnPlain.setSelected(true);
			  else if(tbfProp==1)
				  rdbtnBold.setSelected(true);
			  else 
				  rdbtnItalic.setSelected(true);
			  

		}
		else
		{
		}
	}

}
