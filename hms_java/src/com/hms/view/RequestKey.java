package com.hms.view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.prefs.Preferences;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import setup.Install;

import com.hms.util.MailHandler;
import com.hms.util.SystemInfo;
import com.hotelmanagement.Constants;
import com.hotelmanagement.SetColor;

public class RequestKey extends JDialog implements ActionListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField text_firstName;
	private JTextField text_lastName;
	private JTextField text_mobile;
	private JTextField text_email;
	private JLabel lblFirstName;
	JLabel lblLastName;
	JLabel lblMobile;
	JLabel lblEmail;
	private JPanel panel_1;
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel lblHotelName;
	private JTextField text_hotelName;
	private JButton btnRegister;
	private JButton btnCancel;
	Message message;
	private JLabel lblAddress;
	private TextArea text_address;
	private JLabel lblRegister;
	public static String pdColor;
	public RequestKey() {
		//super(new JFrame(), "Project SetUp", true);
		try {
			message = MailHandler.connectToMail("Request for sending the product key");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSize(500, 385);
		setLocation(d.width/3,d.height/4);		 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		lblRegister = new JLabel("Request For Product Key");
		GridBagConstraints gbc_lblRegister = new GridBagConstraints();
		gbc_lblRegister.anchor = GridBagConstraints.EAST;
		gbc_lblRegister.gridwidth = 2;
		gbc_lblRegister.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegister.gridx = 1;
		gbc_lblRegister.gridy = 1;
		contentPane.add(lblRegister, gbc_lblRegister);
		lblRegister.setFont(new Font("Arial", Font.BOLD, 17));
		
		lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 1;
		gbc_lblFirstName.gridy = 3;
		contentPane.add(lblFirstName, gbc_lblFirstName);
		
		text_firstName = new JTextField();
		GridBagConstraints gbc_text_firstName = new GridBagConstraints();
		gbc_text_firstName.insets = new Insets(0, 0, 5, 5);
		gbc_text_firstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_firstName.gridx = 2;
		gbc_text_firstName.gridy = 3;
		contentPane.add(text_firstName, gbc_text_firstName);
		text_firstName.setColumns(10);
		
		lblLastName = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 1;
		gbc_lblLastName.gridy = 4;
		contentPane.add(lblLastName, gbc_lblLastName);
		
		text_lastName = new JTextField();
		GridBagConstraints gbc_text_lastName = new GridBagConstraints();
		gbc_text_lastName.insets = new Insets(0, 0, 5, 5);
		gbc_text_lastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_lastName.gridx = 2;
		gbc_text_lastName.gridy = 4;
		contentPane.add(text_lastName, gbc_text_lastName);
		text_lastName.setColumns(10);
		
		lblMobile = new JLabel("Mobile");
		GridBagConstraints gbc_lblMobile = new GridBagConstraints();
		gbc_lblMobile.anchor = GridBagConstraints.WEST;
		gbc_lblMobile.insets = new Insets(0, 0, 5, 5);
		gbc_lblMobile.gridx = 1;
		gbc_lblMobile.gridy = 5;
		contentPane.add(lblMobile, gbc_lblMobile);
		
		text_mobile = new JTextField();
		GridBagConstraints gbc_text_mobile = new GridBagConstraints();
		gbc_text_mobile.insets = new Insets(0, 0, 5, 5);
		gbc_text_mobile.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_mobile.gridx = 2;
		gbc_text_mobile.gridy = 5;
		contentPane.add(text_mobile, gbc_text_mobile);
		text_mobile.setColumns(10);
		
		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 6;
		contentPane.add(lblEmail, gbc_lblEmail);
		
		text_email = new JTextField();
		GridBagConstraints gbc_text_email = new GridBagConstraints();
		gbc_text_email.anchor = GridBagConstraints.NORTH;
		gbc_text_email.insets = new Insets(0, 0, 5, 5);
		gbc_text_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_email.gridx = 2;
		gbc_text_email.gridy = 6;
		contentPane.add(text_email, gbc_text_email);
		text_email.setColumns(10);
		
		lblHotelName = new JLabel("Hotel Name");
		GridBagConstraints gbc_lblHotelName = new GridBagConstraints();
		gbc_lblHotelName.anchor = GridBagConstraints.WEST;
		gbc_lblHotelName.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotelName.gridx = 1;
		gbc_lblHotelName.gridy = 7;
		contentPane.add(lblHotelName, gbc_lblHotelName);
		
		text_hotelName = new JTextField();
		GridBagConstraints gbc_text_hotelName = new GridBagConstraints();
		gbc_text_hotelName.anchor = GridBagConstraints.NORTH;
		gbc_text_hotelName.insets = new Insets(0, 0, 5, 5);
		gbc_text_hotelName.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_hotelName.gridx = 2;
		gbc_text_hotelName.gridy = 7;
		contentPane.add(text_hotelName, gbc_text_hotelName);
		text_hotelName.setColumns(10);
		
		lblAddress = new JLabel("Address");
		GridBagConstraints gbc_lblAddress = new GridBagConstraints();
		gbc_lblAddress.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddress.gridx = 1;
		gbc_lblAddress.gridy = 8;
		contentPane.add(lblAddress, gbc_lblAddress);
		
		text_address = new TextArea();
		text_address.setRows(5);
		GridBagConstraints gbc_text_address = new GridBagConstraints();
		gbc_text_address.anchor = GridBagConstraints.NORTH;
		gbc_text_address.insets = new Insets(0, 0, 5, 5);
		gbc_text_address.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_address.gridx = 2;
		gbc_text_address.gridy = 8;
		contentPane.add(text_address, gbc_text_address);
		text_address.setColumns(10);
		
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 9;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.insets = new Insets(0, 0, 0, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnRegister = new JButton("Send");
		GridBagConstraints gbc_btnRegister = new GridBagConstraints();
		gbc_btnRegister.anchor = GridBagConstraints.EAST;
		gbc_btnRegister.insets = new Insets(0, 0, 0, 5);
		gbc_btnRegister.gridx = 0;
		gbc_btnRegister.gridy = 0;
		panel.add(btnRegister, gbc_btnRegister);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.EAST;
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 0;
		panel.add(btnCancel, gbc_btnCancel);
		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);
		text_firstName.addFocusListener(this);
		text_lastName.addFocusListener(this);
		text_hotelName.addFocusListener(this);
		text_mobile.addFocusListener(this);
		text_email.addFocusListener(this);
		text_address.addFocusListener(this);
		setCompBackground(new Color(SetColor.bkColor));
		setcColor(new Color(SetColor.cColor));
		setVisible(true);
	}
	public void setCompBackground(Color clr)
	{
		contentPane.setBackground(clr);
	}
	public void setcFont(Font fnt)
	{
		 lblFirstName.setFont(fnt);
		 lblLastName.setFont(fnt);
		 lblMobile.setFont(fnt);
		 lblEmail.setFont(fnt);
	}
	public void setcColor(Color clr)
	{
		 lblFirstName.setForeground(clr);
		 lblLastName.setForeground(clr);
		 lblMobile.setForeground(clr);
		 lblEmail.setForeground(clr);
		 lblHotelName.setForeground(clr);
		 lblAddress.setForeground(clr);
		 lblRegister.setForeground(new Color(SetColor.mtColor));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnRegister)
		{
			text_firstName.setEnabled(false);
			text_lastName.setEnabled(false);
			text_mobile.setEnabled(false);
			text_email.setEnabled(false);
			text_hotelName.setEnabled(false);
			text_address.setEnabled(false);
			btnRegister.setEnabled(false);
			contentPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
	        final String firstName = text_firstName.getText().trim().toUpperCase();
			final String lastName = text_lastName.getText().trim().toUpperCase();
			final String mobile = text_mobile.getText().trim();
			final String email = text_email.getText().trim();
			final String hotelName = text_hotelName.getText().trim().toUpperCase();
			final String address = text_address.getText();
			final String hostName = SystemInfo.getSystemName();
			final String ipAddress = SystemInfo.getIPAddress();
			final String macAddress = SystemInfo.getMAC();
			Preferences prefs = Preferences.userRoot().node(Constants.PRE_RESOURCE);
			 pdColor = prefs.get("pdColor", "");
			Thread t = new Thread(new Runnable(){
				public void run()
				{
					try {
						message.setText("Respect Sir, It is kindly to inform AYBITS that we have used your software pls send us the product key."
								+"\n\n Registrar Details: "
								+"\n Full Name: "+firstName+" "+ lastName
								+"\n Mobile Number: "+ mobile
								+"\n E-Mail: "+ email
								+"\n Hotel Name: "+ hotelName
								+"\n Address: \n "+ address
								+"\n\n System Details: "
								+"\n Host Name: "+hostName
								+"\n IP Address: "+ipAddress
								+"\n MAC Address: "+ macAddress
								+"\n Product ID: "+ pdColor);
								

						Transport.send(message);
						JOptionPane.showMessageDialog(null, "Request sent successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
						contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						dispose();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Check your internet connection", "Error Message", JOptionPane.ERROR_MESSAGE);
						contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));		
					}
			 					
				}
			});
			t.start();

		}
		else if(e.getSource() == btnCancel)
		{
			dispose();
		}
		else
		{
			
		}
		
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==text_firstName)
			text_firstName.selectAll();
		else if(e.getSource()==text_lastName)
			text_lastName.selectAll();
		else if(e.getSource()==text_mobile)
			text_mobile.selectAll();
		else if(e.getSource()==text_email)
			text_email.selectAll();
		else if(e.getSource()==text_hotelName)
			text_hotelName.selectAll();
	}
	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==text_email)
		{
			String hex=text_email.getText().trim();
			if(!(hex.equals("")))
			{
			final String EMAIL_PATTERN = 
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		
			Matcher matcher = pattern.matcher(hex);
			if(!(matcher.matches()))
			{
				JOptionPane.showMessageDialog(this, "Invalid email pattern","Error", JOptionPane.ERROR_MESSAGE);
				text_email.requestFocus(true);
				
			}
	
			
			}
		}
		else if(e.getSource()==text_mobile)
		{
			if(text_mobile.getText().equals(""))
				text_mobile.setText("91-");
				String phoneno=text_mobile.getText().trim();
				if(!(phoneno.equals("91-")))
				{
			      Pattern pattern = Pattern.compile("\\d{2}-\\d{10}");
			      if(!phoneno.startsWith("91-"))
			      {
			    	  phoneno="91-"+phoneno;
			    	  text_mobile.setText(phoneno);
			      }
			      Matcher matcher = pattern.matcher(phoneno);
			      if(!matcher.matches())
			      {
			    	  JOptionPane.showMessageDialog(this, "Invalid phone number","Error", JOptionPane.ERROR_MESSAGE);
			    	  text_mobile.requestFocus(true);
			      }
				}
		}

	}


}
