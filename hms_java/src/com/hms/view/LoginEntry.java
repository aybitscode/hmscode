package com.hms.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.JPlaceholderPasswordField;
import com.hms.util.JPlaceholderTextField;
import com.hms.util.PasswordEncrypt;
import com.hms.viewhandler.ViewHandler;
import com.hotelmanagement.MainPage;
import com.hotelmanagement.WelcomeEntry;


public class LoginEntry extends JPanel implements MouseListener{
	private JPlaceholderTextField textUserName;
	private JPlaceholderPasswordField textPassword;
	JLabel lblLogin;
	JLabel lblForgotPassword;
	
	
	String user,pass;
	public static String userName;
	Connection con = DBConnection.getDBConnection();
	JLabel lblCreateAccount;

	/**
	 * Create the panel.
	 */
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	MainPage mpg;
	public LoginEntry(final MainPage mpg) {
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
		gbl_panel.rowHeights = new int[]{0, 20, 0, 20, 50, 10, 50, 20, 50, 20, 50, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		panel.setBackground(Color.white);
		
		JLabel lblSignIn = new JLabel("Sign In");
		GridBagConstraints gbc_lblSignIn = new GridBagConstraints();
		gbc_lblSignIn.gridwidth = 3;
		gbc_lblSignIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignIn.gridx = 1;
		gbc_lblSignIn.gridy = 2;
		panel.add(lblSignIn, gbc_lblSignIn);
		lblSignIn.setFont(new Font("Open Sans", Font.PLAIN, 28));
		lblSignIn.setForeground(new Color(50, 197, 210));
		
		textUserName = new JPlaceholderTextField("Username");
		GridBagConstraints gbc_textUserName = new GridBagConstraints();
		gbc_textUserName.gridwidth = 3;
		gbc_textUserName.insets = new Insets(0, 0, 5, 5);
		gbc_textUserName.fill = GridBagConstraints.BOTH;
		gbc_textUserName.gridx = 1;
		gbc_textUserName.gridy = 4;
		panel.add(textUserName, gbc_textUserName);
		textUserName.setColumns(10);
		textUserName.setBackground(new Color(221, 227, 236));
		textUserName.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textUserName.setForeground(new Color(130, 144, 163));
		
		
		textPassword = new JPlaceholderPasswordField("Password");
		GridBagConstraints gbc_textPassword = new GridBagConstraints();
		gbc_textPassword.insets = new Insets(0, 0, 5, 5);
		gbc_textPassword.gridwidth = 3;
		gbc_textPassword.fill = GridBagConstraints.BOTH;
		gbc_textPassword.gridx = 1;
		gbc_textPassword.gridy = 6;
		panel.add(textPassword, gbc_textPassword);
		textPassword.setColumns(10);
		textPassword.setBackground(new Color(221, 227, 236));
		textPassword.setFont(new Font("Open Sans", Font.PLAIN, 14));
		textPassword.setForeground(new Color(130, 144, 163));
		textPassword.addKeyListener(new KeyAdapter() {

		    @Override
		    public void keyReleased(KeyEvent event) {
		        if (event.getKeyChar() == KeyEvent.VK_ENTER) {
		            if (textPassword.getText().trim().isEmpty())
		            {
		            	JOptionPane.showMessageDialog(new JFrame(),"Invalid User Name / Password","Message",JOptionPane.INFORMATION_MESSAGE);
		            }
		            else
		            {		   
		        		int count = retrivePass();


		        		String password;
		        		password=textPassword.getText().trim();
		    			if(count >0)
		    			{			    				
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
		    							JOptionPane.showMessageDialog(new JFrame(),"Invalid User Name / Password","Security Check",JOptionPane.ERROR_MESSAGE);
		    						}
		    						
		    			}
		    			else
		    			{
		    				JOptionPane.showMessageDialog(new JFrame(),"Invalid User Name / Password","Message",JOptionPane.INFORMATION_MESSAGE);			
		    			}
		            }
		            	
		        }
		    }
		});
		
		lblLogin = new JLabel("     LOGIN");
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.fill = GridBagConstraints.BOTH;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 8;
		panel.add(lblLogin, gbc_lblLogin);
		lblLogin.setOpaque(true);
		lblLogin.setBackground(new Color(50, 197, 210));
		lblLogin.setForeground(Color.white);
		lblLogin.setFont(new Font("Open Sans", Font.BOLD, 14));
		lblLogin.addMouseListener(this);
		
		lblForgotPassword = new JLabel("Forgot Password?");
		GridBagConstraints gbc_lblForgotPassword = new GridBagConstraints();
		gbc_lblForgotPassword.anchor = GridBagConstraints.EAST;
		gbc_lblForgotPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblForgotPassword.gridx = 3;
		gbc_lblForgotPassword.gridy = 8;
		//panel.add(lblForgotPassword, gbc_lblForgotPassword);
		lblForgotPassword.setFont(new Font("Open Sans", Font.PLAIN, 14));
		lblForgotPassword.setForeground(new Color(104, 166, 218));
		lblForgotPassword.addMouseListener(this);
		
		lblCreateAccount = new JLabel("                                CREATE AN ACCOUNT");
		GridBagConstraints gbc_lblCreateAccount = new GridBagConstraints();
		gbc_lblCreateAccount.fill = GridBagConstraints.BOTH;
		gbc_lblCreateAccount.gridwidth = 5;
		gbc_lblCreateAccount.insets = new Insets(0, 0, 0, 0);
		gbc_lblCreateAccount.gridx = 0;
		gbc_lblCreateAccount.gridy = 10;
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
			ViewHandler.mouseEnteredJLabel(lblLogin);
		}
		else if(arg0.getSource() == lblForgotPassword)
		{
			lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblForgotPassword.setText("<html><p><u>Forgot Password?</u><p></html>");
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
			ViewHandler.mouseExitedJLabel(lblLogin);
		}
		else if(arg0.getSource() == lblForgotPassword)
		{
			lblForgotPassword.setText("Forgot Password?");
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
		else if(arg0.getSource()==lblForgotPassword){
			MainPage.bodyPanel.removeAll();
			ForgotPasswordEntry objFPE = new ForgotPasswordEntry(mpg);
			MainPage.bodyPanel.add(objFPE, MainPage.gbc_dashboard);
			MainPage.bodyPanel.updateUI();
			
		}		
		else if(arg0.getSource()==lblCreateAccount){
			MainPage.bodyPanel.removeAll();
			MainPage.bodyPanel.setLayout(null);
			UserEntry objUser = new UserEntry(mpg);
			MainPage.bodyPanel.add(objUser);
			objUser.setBounds(0, 0, MainPage.bodyPanel.getWidth(), MainPage.bodyPanel.getHeight());
			MainPage.bodyPanel.updateUI();
			
		}
		else
		{
			
		}

	}

}
