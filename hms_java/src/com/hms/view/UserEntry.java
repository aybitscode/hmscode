package com.hms.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.JPlaceholderPasswordField;
import com.hms.util.JPlaceholderTextField;
import com.hms.util.PasswordEncrypt;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.WelcomeEntry;


public class UserEntry extends JPanel implements MouseListener{
	private JPlaceholderTextField textFirstName;
	private JPlaceholderTextField textLastName;
	JLabel lblLogin;
	JLabel lblBack;
	
	
	String user,pass;
	public static String userName;
	Connection con = DBConnection.getDBConnection();
	JLabel lblCreateAccount;

	/**
	 * Create the panel.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	MainPage mpg;
	private JPlaceholderTextField textUsername;
	private JPlaceholderPasswordField textPassword;
	private JPlaceholderPasswordField textConfirmPassword;
	private JPlaceholderTextField textEmail;
	private JPlaceholderTextField textMobile;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JDatePicker picker_birth;
	UtilDateModel birth_model = new UtilDateModel();
	DateFormat date_format;
	public UserEntry(MainPage mpg) {
		this.mpg = mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		panel_1.setBackground(new Color(54, 65, 80));
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		panel_1.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 95, 123, 122, 30, 0};
		gbl_panel.rowHeights = new int[]{0, 20, 0, 20, 50, 10, 50, 20, 50, 20, 50, 20, 50, 20, 50, 20, 50, 20, 50, 20, 50, 20};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 4.9E-324, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		panel.setBackground(Color.white);
		
		JLabel lblSignUp = new JLabel("Sign Up");
		GridBagConstraints gbc_lblSignUp = new GridBagConstraints();
		gbc_lblSignUp.gridwidth = 3;
		gbc_lblSignUp.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignUp.gridx = 1;
		gbc_lblSignUp.gridy = 2;
		panel.add(lblSignUp, gbc_lblSignUp);
		lblSignUp.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblSignUp.setForeground(new Color(50, 197, 210));
		
		textFirstName = new JPlaceholderTextField("First name");
		GridBagConstraints gbc_textFirstName = new GridBagConstraints();
		gbc_textFirstName.gridwidth = 3;
		gbc_textFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_textFirstName.fill = GridBagConstraints.BOTH;
		gbc_textFirstName.gridx = 1;
		gbc_textFirstName.gridy = 4;
		panel.add(textFirstName, gbc_textFirstName);
		textFirstName.setColumns(10);
		textFirstName.setBackground(new Color(221, 227, 236));
		textFirstName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textFirstName.setForeground(new Color(130, 144, 163));
		
		
		textLastName = new JPlaceholderTextField("Last name");
		GridBagConstraints gbc_textLastName = new GridBagConstraints();
		gbc_textLastName.insets = new Insets(0, 0, 5, 5);
		gbc_textLastName.gridwidth = 3;
		gbc_textLastName.fill = GridBagConstraints.BOTH;
		gbc_textLastName.gridx = 1;
		gbc_textLastName.gridy = 6;
		panel.add(textLastName, gbc_textLastName);
		textLastName.setColumns(10);
		textLastName.setBackground(new Color(221, 227, 236));
		textLastName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textLastName.setForeground(new Color(130, 144, 163));
		
		textUsername = new JPlaceholderTextField("Username");
		textUsername.setForeground(new Color(130, 144, 163));
		textUsername.setFont(new Font("Dialog", Font.PLAIN, 14));
		textUsername.setColumns(10);
		textUsername.setBackground(new Color(221, 227, 236));
		GridBagConstraints gbc_textUsername = new GridBagConstraints();
		gbc_textUsername.gridwidth = 3;
		gbc_textUsername.insets = new Insets(0, 0, 5, 5);
		gbc_textUsername.fill = GridBagConstraints.BOTH;
		gbc_textUsername.gridx = 1;
		gbc_textUsername.gridy = 8;
		panel.add(textUsername, gbc_textUsername);
		
		textPassword = new JPlaceholderPasswordField("Password");
		textPassword.setForeground(new Color(130, 144, 163));
		textPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		textPassword.setColumns(10);
		textPassword.setBackground(new Color(221, 227, 236));
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.gridwidth = 3;
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.fill = GridBagConstraints.BOTH;
		gbc_textPassword.gridx = 1;
		gbc_textPassword.gridy = 10;
		panel.add(textPassword, gbc_textPassword);
		
		textConfirmPassword = new JPlaceholderPasswordField("Confirm password");
		textConfirmPassword.setForeground(new Color(130, 144, 163));
		textConfirmPassword.setFont(new Font("Dialog", Font.PLAIN, 14));
		textConfirmPassword.setColumns(10);
		textConfirmPassword.setBackground(new Color(221, 227, 236));
		GridBagConstraints gbc_textConfirmPassword = new GridBagConstraints();
		gbc_textConfirmPassword.gridwidth = 3;
		gbc_textConfirmPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textConfirmPassword.fill = GridBagConstraints.BOTH;
		gbc_textConfirmPassword.gridx = 1;
		gbc_textConfirmPassword.gridy = 12;
		panel.add(textConfirmPassword, gbc_textConfirmPassword);
		
		
		
		date_format = new SimpleDateFormat("dd-MM-yyyy");
		Date current_date = new Date();
		birth_model.setValue(current_date);
		picker_birth = new JDatePicker(birth_model, "dd-MM-yyyy");	 
		picker_birth.getFormattedTextField().setForeground(new Color(130, 144, 163));
		picker_birth.setFont(new Font("Dialog", Font.PLAIN, 14)); 
		picker_birth.getFormattedTextField().setBackground(new Color(221, 227, 236));

		
		GridBagConstraints gbc_placeholderTextField_2 = new GridBagConstraints();
		gbc_placeholderTextField_2.gridwidth = 3;
		gbc_placeholderTextField_2.insets = new Insets(0, 0, 5, 5);
		gbc_placeholderTextField_2.fill = GridBagConstraints.BOTH;
		gbc_placeholderTextField_2.gridx = 1;
		gbc_placeholderTextField_2.gridy = 14; 
		panel.add(picker_birth, gbc_placeholderTextField_2);
		
		
		textEmail = new JPlaceholderTextField("Email");
		textEmail.setForeground(new Color(130, 144, 163));
		textEmail.setFont(new Font("Dialog", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBackground(new Color(221, 227, 236));
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 3;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.BOTH;
		gbc_textEmail.gridx = 1;
		gbc_textEmail.gridy = 16;
		panel.add(textEmail, gbc_textEmail);
		
		
		textMobile = new JPlaceholderTextField("Mobile number");
		textMobile.setForeground(new Color(130, 144, 163));
		textMobile.setFont(new Font("Dialog", Font.PLAIN, 14));
		textMobile.setColumns(10);
		textMobile.setBackground(new Color(221, 227, 236));
		GridBagConstraints gbc_textMobile = new GridBagConstraints();
		gbc_textMobile.gridwidth = 3;
		gbc_textMobile.insets = new Insets(0, 0, 5, 5);
		gbc_textMobile.fill = GridBagConstraints.BOTH;
		gbc_textMobile.gridx = 1;
		gbc_textMobile.gridy = 18;
		panel.add(textMobile, gbc_textMobile);
		
 
		
		lblBack = new JLabel("      Back");
		GridBagConstraints gbc_lblBack = new GridBagConstraints();
		gbc_lblBack.fill = GridBagConstraints.BOTH;
		gbc_lblBack.insets = new Insets(0, 0, 5, 5);
		gbc_lblBack.gridx = 1;
		gbc_lblBack.gridy = 20;
		panel.add(lblBack, gbc_lblBack);
		lblBack.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblBack.setForeground(new Color(104, 166, 218));
		lblBack.addMouseListener(this);
		lblBack.setOpaque(true);
        Border border = BorderFactory.createLineBorder(new Color(50, 197, 210));
        lblBack.setBorder(border);
        lblBack.setBackground(Color.white);
        lblBack.setForeground(new Color(50, 197, 210));
        
        
		lblLogin = new JLabel("        SUBMIT");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.fill = GridBagConstraints.BOTH;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 3;
		gbc_lblLogin.gridy = 20;
		panel.add(lblLogin, gbc_lblLogin);
		lblLogin.setOpaque(true);
		lblLogin.setBackground(new Color(50, 197, 210));
		lblLogin.setForeground(Color.white);
		lblLogin.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblLogin.addMouseListener(this);
		
		lblCreateAccount = new JLabel("                                CREATE AN ACCOUNT");
		GridBagConstraints gbc_lblCreateAccount = new GridBagConstraints();
		gbc_lblCreateAccount.fill = GridBagConstraints.BOTH;
		gbc_lblCreateAccount.gridwidth = 5;
		gbc_lblCreateAccount.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateAccount.gridx = 0;
		gbc_lblCreateAccount.gridy = 17;
		//panel.add(lblCreateAccount, gbc_lblCreateAccount);
		lblCreateAccount.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblCreateAccount.setOpaque(true);
		lblCreateAccount.setBackground(new Color(108, 122, 141));
		lblCreateAccount.setForeground(new Color(195, 206, 221));
		lblCreateAccount.addMouseListener(this);

	}
	public int retrivePass()
	{
		int count = 0;
		try{
		PreparedStatement psmt= con.prepareStatement(DatabaseConstants.SELECT_EMP_USER_PASS);
		psmt.setString(1,textFirstName.getText().trim());
		ResultSet rs=psmt.executeQuery();
		if(rs.next())
		{
			MainPage.userID = rs.getString(1);
			user=rs.getString(2);
			pass=rs.getString(3);
			MainPage.user_role = rs.getString(4);
			MainPage.user_mode = rs.getString(5);
			count++;
		}
		
		}catch(Exception ee){JOptionPane.showMessageDialog(this, "No user found","Error",JOptionPane.ERROR_MESSAGE);}
		return count;
	}
 
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblLogin)
		{
			lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblLogin.setBackground(new Color(44, 174, 186));
		}
		else if(arg0.getSource() == lblBack)
		{
			lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblBack.setBackground(new Color(50, 197, 210));
			lblBack.setForeground(Color.white);
		}
		else if(arg0.getSource() == lblCreateAccount)
		{
			lblCreateAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblCreateAccount.setText("<html><p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>CREATE AN ACCOUNT</u><p></html>");
		}
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==lblLogin)
		{
			//lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblLogin.setBackground(new Color(50, 197, 210));
		}
		else if(arg0.getSource() == lblBack)
		{
			lblBack.setBackground(Color.white);
			lblBack.setForeground(new Color(50, 197, 210));
		}
		else if(arg0.getSource() == lblCreateAccount)
		{
			lblCreateAccount.setText("                                CREATE AN ACCOUNT");
		}
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int count = retrivePass();


		String password;
		password=textLastName.getText().trim();
		if(arg0.getSource()==lblLogin)
		{
			
			if(count >0)
			{	
//			if(user.compareToIgnoreCase(sus)!=0&&pass.compareToIgnoreCase(sps)!=0)
//			{
//				JOptionPane.showMessageDialog(this,"Invalid User Name / Password","Security Check",JOptionPane.ERROR_MESSAGE);
//				txtAdmin.requestFocus();
//			}
//			else if(user.compareToIgnoreCase(sus)!=0)
//			{
//				JOptionPane.showMessageDialog(this,"Invalid User Name / Password","Security Check",JOptionPane.ERROR_MESSAGE);
//				txtAdmin.requestFocus();
//
//			}
//			else if(pass.compareToIgnoreCase(sps)!=0)
//			{
//				
//				JOptionPane.showMessageDialog(this,"Invalid User Name / Password","Security Check",JOptionPane.ERROR_MESSAGE);
//				textField_1.requestFocus();
//			}
				
						if(PasswordEncrypt.checkPassword(password, pass))
						{
							MainPage.bodyPanel.removeAll(); 
							MainPage.bodyPanel.setLayout(null);
							userName = textFirstName.getText().toUpperCase()+" ";
							MainPage.labelUser.setText(userName);
							MainPage.notificationPanel.setVisible(true);
							MainPage.lblHome.setVisible(true);
							WelcomeEntry objWelcome = new WelcomeEntry(mpg);
							MainPage.bodyPanel.add(objWelcome);
							objWelcome.setBounds(0, 0, MainPage.bodyPanel.getWidth(), MainPage.bodyPanel.getHeight());
							MainPage.bodyPanel.updateUI();
							
							if(MainPage.user_role.equals(com.hms.util.Constants.ADMIN))
							{				
								
								//MainPage.tabbedPane.addTab("Configuration", null);
								WelcomeEntry.lblConfiguration.setVisible(true);
								WelcomeEntry.lblConfigurations.setVisible(true);
							}			
							
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid User Name / Password","Security Check",JOptionPane.ERROR_MESSAGE);
						}
						
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Invalid User Name / Password","Message",JOptionPane.INFORMATION_MESSAGE);			
			}
		}
		if(arg0.getSource()==lblBack){
			MainPage.bodyPanel.removeAll();
			LoginEntry objLogin = new LoginEntry(mpg);
			MainPage.bodyPanel.setLayout(MainPage.gbl_bodyPanel);
			MainPage.bodyPanel.add(objLogin, MainPage.gbc_dashboard);
			MainPage.bodyPanel.updateUI();
			
		}

	}

}
