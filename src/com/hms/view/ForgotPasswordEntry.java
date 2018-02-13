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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.JPlaceholderTextField;
import com.hms.util.PasswordEncrypt;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.WelcomeEntry;


public class ForgotPasswordEntry extends JPanel implements MouseListener{
	private JPlaceholderTextField textUserName;
	private JPlaceholderTextField textPassword;
	JLabel lblLogin;
	JLabel lblBack;
	
	
	String user,pass;
	public static String userName;
	Connection con = DBConnection.getDBConnection();

	/**
	 * Create the panel.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	MainPage mpg;
	private JLabel lblDescription;
	public ForgotPasswordEntry(MainPage mpg) {
		this.mpg = mpg;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 95, 123, 122, 30, 0};
		gbl_panel.rowHeights = new int[]{0, 20, 0, 20, 0, 50, 10, 50, 20, 50, 20, 50, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		panel.setBackground(Color.white);
		
		JLabel lblSignIn = new JLabel("Forget Password ?");
		GridBagConstraints gbc_lblSignIn = new GridBagConstraints();
		gbc_lblSignIn.gridwidth = 3;
		gbc_lblSignIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignIn.gridx = 1;
		gbc_lblSignIn.gridy = 2;
		panel.add(lblSignIn, gbc_lblSignIn);
		lblSignIn.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblSignIn.setForeground(new Color(50, 197, 210));
		
		lblDescription = new JLabel("<html>Enter your username and email address to get <br>your password.</html>");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.VERTICAL;
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.gridwidth = 3;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 4;
		panel.add(lblDescription, gbc_lblDescription);
		lblDescription.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblDescription.setForeground(new Color(130, 144, 163));
		
		textUserName = new JPlaceholderTextField("Username");
		GridBagConstraints gbc_textUserName = new GridBagConstraints();
		gbc_textUserName.gridwidth = 3;
		gbc_textUserName.insets = new Insets(0, 0, 5, 5);
		gbc_textUserName.fill = GridBagConstraints.BOTH;
		gbc_textUserName.gridx = 1;
		gbc_textUserName.gridy = 5;
		panel.add(textUserName, gbc_textUserName);
		textUserName.setColumns(10);
		textUserName.setBackground(new Color(221, 227, 236));
		textUserName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textUserName.setForeground(new Color(130, 144, 163));
		
		
		textPassword = new JPlaceholderTextField("Registered Email");
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridwidth = 3;
		gbc_textPassword.fill = GridBagConstraints.BOTH;
		gbc_textPassword.gridx = 1;
		gbc_textPassword.gridy = 7;
		panel.add(textPassword, gbc_textPassword);
		textPassword.setColumns(10);
		textPassword.setBackground(new Color(221, 227, 236));
		textPassword.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textPassword.setForeground(new Color(130, 144, 163));
		
		lblBack = new JLabel("      Back");
		GridBagConstraints gbc_lblBack = new GridBagConstraints();
		gbc_lblBack.fill = GridBagConstraints.BOTH;
		gbc_lblBack.insets = new Insets(0, 0, 5, 5);
		gbc_lblBack.gridx = 1;
		gbc_lblBack.gridy = 9;
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
		gbc_lblLogin.gridy = 9;
		panel.add(lblLogin, gbc_lblLogin);
		lblLogin.setOpaque(true);
		lblLogin.setBackground(new Color(50, 197, 210));
		lblLogin.setForeground(Color.white);
		lblLogin.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblLogin.addMouseListener(this);
		

		
		
		JLabel lblCreateAccount = new JLabel("                               BACK");
		GridBagConstraints gbc_lblCreateAccount = new GridBagConstraints();
		gbc_lblCreateAccount.fill = GridBagConstraints.BOTH;
		gbc_lblCreateAccount.gridwidth = 5;
		gbc_lblCreateAccount.insets = new Insets(0, 0, 0, 0);
		gbc_lblCreateAccount.gridx = 0;
		gbc_lblCreateAccount.gridy = 11;
		//panel.add(lblCreateAccount, gbc_lblCreateAccount);
		lblCreateAccount.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblCreateAccount.setOpaque(true);
		lblCreateAccount.setBackground(new Color(108, 122, 141));
		lblCreateAccount.setForeground(new Color(195, 206, 221));

	}
	public int retrivePass()
	{
		int count = 0;
		try{
		PreparedStatement psmt= con.prepareStatement(DatabaseConstants.SELECT_EMP_USER_PASS);
		psmt.setString(1,textUserName.getText().trim());
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
		password=textPassword.getText().trim();
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
							userName = textUserName.getText().toUpperCase()+" ";
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
			MainPage.bodyPanel.add(objLogin, MainPage.gbc_dashboard);
			MainPage.bodyPanel.updateUI();
		}

	}

}
