package com.hotelmanagement;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.hms.view.RequestKey;


public class RFPanel extends JPanel implements ActionListener, KeyListener, FocusListener{

	public static JTextField text_key;
	public static JButton btnContinue;
	public static JLabel lblProductKey;
	public static  Label lblNewLabel;
	int calmn;
	private JLabel lblProduct;
	private JTextField text_productID;
	private JButton btnRequest;
	
	/**
	 * Create the panel.
	 */
	public RFPanel(String message) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 30, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0};
		setLayout(gridBagLayout);
		
		lblNewLabel = new Label(message);
		lblNewLabel.setForeground(new Color(SetColor.mtColor));
		lblNewLabel.setFont(new Font(SFont.mtFType,SFont.mtfProp, SFont.mtSize));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridwidth = 3;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		lblProduct = new JLabel("Product ID");
		GridBagConstraints gbc_lblProduct = new GridBagConstraints();
		gbc_lblProduct.anchor = GridBagConstraints.WEST;
		gbc_lblProduct.insets = new Insets(0, 0, 5, 5);
		gbc_lblProduct.gridx = 1;
		gbc_lblProduct.gridy = 1;
		add(lblProduct, gbc_lblProduct);

		Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);		
		text_productID = new JTextField();
		GridBagConstraints gbc_text_productID = new GridBagConstraints();
		gbc_text_productID.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_productID.gridwidth = 2;
		gbc_text_productID.insets = new Insets(0, 0, 5, 5);
		gbc_text_productID.gridx = 2;
		gbc_text_productID.gridy = 1;
		add(text_productID, gbc_text_productID);
		text_productID.setColumns(10);
		text_productID.setEditable(false);
		text_productID.setText(""+prefs.get("pdColor", ""));

		lblProductKey = new JLabel("Product Key");
		GridBagConstraints gbc_lblProductKey = new GridBagConstraints();
		gbc_lblProductKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblProductKey.anchor = GridBagConstraints.WEST;
		gbc_lblProductKey.gridx = 1;
		gbc_lblProductKey.gridy = 2;
		add(lblProductKey, gbc_lblProductKey);
		
		text_key = new JTextField();
		text_key.setColumns(10);
		GridBagConstraints gbc_text_key = new GridBagConstraints();
		gbc_text_key.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_key.gridwidth = 2;
		gbc_text_key.insets = new Insets(0, 0, 5, 5);
		gbc_text_key.gridx = 2;
		gbc_text_key.gridy = 2;
		add(text_key, gbc_text_key);
		text_key.addKeyListener(this);
		
		btnRequest = new JButton("Request");
		GridBagConstraints gbc_btnRequest = new GridBagConstraints();
		gbc_btnRequest.fill = GridBagConstraints.BOTH;
		gbc_btnRequest.insets = new Insets(0, 0, 5, 5);
		gbc_btnRequest.gridx = 2;
		gbc_btnRequest.gridy = 3;
		add(btnRequest, gbc_btnRequest);
		btnRequest.addActionListener(this);
		
		btnContinue = new JButton("Continue");
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.fill = GridBagConstraints.BOTH;
		gbc_btnContinue.insets = new Insets(0, 0, 5, 5);
		gbc_btnContinue.gridx = 3;
		gbc_btnContinue.gridy = 3;
		add(btnContinue, gbc_btnContinue);
		btnContinue.setEnabled(false);
		
		setcColor(new Color(SetColor.cColor));
		setcFont();
		setCompBackground(new Color(SetColor.bkColor));
		//textField.setText("textField");
		//textField_2.setText("textField_2");
		btnContinue.addActionListener(this);
		text_productID.addFocusListener(this);
		text_key.addFocusListener(this);

	}
	public static void setExVisible(boolean b)
	{
		//lblNewLabel.setText("Some files are missing contact service provider");
		text_key.setVisible(b);
		btnContinue.setVisible(b);
		lblProductKey.setVisible(b);
	
	}
	public void setCompBackground(Color clr)
	{
		setBackground(clr);
	}
	public void setcFont()
	{
		lblProduct.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProductKey.setFont(new Font("Tahoma", Font.BOLD, 15));
	}
	public void setcColor(Color clr)
	{
		lblProduct.setForeground(clr);
		lblProductKey.setForeground(clr);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource() == btnContinue)
		{
//		int chk=9999,chk2=9919;
//		File src=new File("C:/oraexe/app/harness/docs/docs/swing-layout-1.0.4-doc");
//		File dst=new File("C:/oraexe/app/harness/docs/docs/swing-layout-1.0.4-src/swing-layout-1.0.4");
//		Calendar now=Calendar.getInstance();
//		int ad=now.get(Calendar.DATE);
//		int mm=now.get(Calendar.MONTH);
//		int yr=now.get(Calendar.YEAR);
//		int hr=now.get(Calendar.HOUR_OF_DAY);
//		int mn=now.get(Calendar.MINUTE);
//		int sec=now.get(Calendar.SECOND);
//		mm=mm+1;
//		calmn=777*mm;
//		chk=chk+1349;
//		chk2=chk2+1349;
//		if(Integer.parseInt(text_key.getText().trim())+calmn==chk2+calmn)
//		{
//			lblNewLabel.setText("Product is being activated.....");
//			  Preferences prefs = Preferences.userRoot().node("com/feemgmt");
//			  Preferences prefs1 = Preferences.userRoot().node("com/mysql");
//			  prefs.put("ProductId", "1349 ");
//			  prefs1.put("DefaultValue", "-1 ");
//
//			MainPage.copyDatabase(src, dst);
//			MainPage.actScreen();
//
//
//		}
//		else
//		{
//			JOptionPane.showMessageDialog(this,"Product key is not valid","Error",JOptionPane.ERROR_MESSAGE);
//		}
			try {

				if(Validator.getStatus())
				{
					File src=new File(Constants.FILE_SRC);
					File dst=new File(Constants.FILE_DST);
					lblNewLabel.setText("Product is being activated.....");
					  Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE_SET);
					  Preferences prefs2 = Preferences.userRoot().node(Constants.PRE_RESOURCE);
					  Preferences prefs1 = Preferences.userRoot().node(Constants.PRE_RESOURCE_MYSQL);
					  prefs.put("pdColor", text_productID.getText()+" ");
					  prefs.put("DefaultValue", "-1 ");
					  prefs1.put("DefaultValue", "-1 ");
					  prefs2.put("   Registered Version 1.0", "");
		
					MainPage.copyDatabase(src, dst);
					MainPage.actScreen();
				}
								
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(arg0.getSource() == btnRequest)
		{
			new RequestKey();
		}
	
	
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==text_key)
		{
			if(text_key.getText().length()>6)
			{
				btnContinue.setEnabled(true);
				btnRequest.setEnabled(false);
			}
			else
			{
				btnContinue.setEnabled(false);
				btnRequest.setEnabled(true);
			}
		
		}
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		text_key.selectAll();
		text_productID.selectAll();
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub		
	}

}
