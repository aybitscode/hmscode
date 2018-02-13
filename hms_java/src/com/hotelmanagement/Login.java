package com.hotelmanagement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hms.util.DBConnection;
import com.hms.util.DatabaseConstants;
import com.hms.util.PasswordEncrypt;
import com.hms.view.EmployeeEntry;


public class Login extends JDialog implements ActionListener,FocusListener,MouseListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textUserName;
	private JPasswordField textPassword;
	JButton okButton;
	JButton cancelButton;
	JLabel change;
	String user,pass;
	JLabel lblLoginForm, lblUserName, lblPasword;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	JPanel buttonPane;
	public static String userName;
	Connection con = DBConnection.getDBConnection();
	private JLabel lblRegister;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	MainPage mpg;
	public Login(MainPage mpg) {		
		super(mpg,"Login Form",true);
		this.mpg =mpg;
		setSize(d.width/4, d.height/4);
		setLocation(3*d.width/8, 3*d.height/8);
		setResizable(false);     
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 100, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblLoginForm = new JLabel("Login Form");
			GridBagConstraints gbc_lblLoginForm = new GridBagConstraints();
			gbc_lblLoginForm.gridwidth = 2;
			gbc_lblLoginForm.insets = new Insets(0, 0, 5, 5);
			gbc_lblLoginForm.gridx = 1;
			gbc_lblLoginForm.gridy = 0;
			contentPanel.add(lblLoginForm, gbc_lblLoginForm);
		}
		{
			lblUserName = new JLabel("Enter User Name");
			GridBagConstraints gbc_lblUserName = new GridBagConstraints();
			gbc_lblUserName.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblUserName.insets = new Insets(0, 0, 5, 5);
			gbc_lblUserName.gridx = 1;
			gbc_lblUserName.gridy = 2;
			contentPanel.add(lblUserName, gbc_lblUserName);
		}
		{
			textUserName = new JTextField();
			textUserName.setText("admin");
			GridBagConstraints gbc_textUserName = new GridBagConstraints();
			gbc_textUserName.insets = new Insets(0, 0, 5, 5);
			gbc_textUserName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textUserName.gridx = 2;
			gbc_textUserName.gridy = 2;
			contentPanel.add(textUserName, gbc_textUserName);
			textUserName.setColumns(10);
		}
		{
			lblPasword = new JLabel("Enter Password");
			GridBagConstraints gbc_lblPasword = new GridBagConstraints();
			gbc_lblPasword.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblPasword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPasword.gridx = 1;
			gbc_lblPasword.gridy = 3;
			contentPanel.add(lblPasword, gbc_lblPasword);
		}
		{
			textPassword = new JPasswordField();
			textPassword.setEchoChar('.');
			GridBagConstraints gbc_textPassword = new GridBagConstraints();
			gbc_textPassword.insets = new Insets(0, 0, 5, 5);
			gbc_textPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_textPassword.gridx = 2;
			gbc_textPassword.gridy = 3;
			contentPanel.add(textPassword, gbc_textPassword);
			textPassword.setColumns(10);
			textPassword.setText("admin");
		}
		{
			change=new JLabel("                                          Change Password");
			GridBagConstraints gbc_change = new GridBagConstraints();
			gbc_change.insets = new Insets(0, 0, 0, 5);
			gbc_change.gridwidth = 2;
			gbc_change.fill = GridBagConstraints.HORIZONTAL;
			gbc_change.gridx = 1;
			gbc_change.gridy = 4;
			//contentPanel.add(change, gbc_change);
		}
		{
			buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{100, 0};
			gbl_buttonPane.rowHeights = new int[]{0, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 4.9E-324};
			gbl_buttonPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				lblRegister = new JLabel("Register");
				GridBagConstraints gbc_lblRegister = new GridBagConstraints();
				gbc_lblRegister.gridx = 0;
				gbc_lblRegister.gridy = 0;
				//buttonPane.add(lblRegister, gbc_lblRegister);
				//lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
				//lblRegister.addMouseListener(this);
			}
			{
				okButton = new JButton("Login");
				okButton.setActionCommand("OK");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.anchor = GridBagConstraints.EAST;
				gbc_okButton.gridx = 1;
				gbc_okButton.gridy = 0;
				buttonPane.add(okButton, gbc_okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
			cancelButton = new JButton("Close");
			cancelButton.setActionCommand("Close");
			GridBagConstraints gbc_cancelButton = new GridBagConstraints();
			gbc_cancelButton.gridx = 2;
			gbc_cancelButton.gridy = 0;
			buttonPane.add(cancelButton, gbc_cancelButton);
			}

		}
		logCTFont(SFont.ctFType, SFont.ctfProp, SFont.ctSize);
		logMTFont(SFont.mtFType, SFont.mtfProp, SFont.mtSize);
		logCColor();
		logMTColor();
		logbkColor();		
		
		   this.addWindowListener(new WindowListener() {
	            public void windowActivated(WindowEvent e) {
	                //System.out.println("windowActivated");
	            }

	            public void windowClosed(WindowEvent e) {
	                //System.out.println("windowClosed");
	                System.exit(0);
	            }

	            public void windowClosing(WindowEvent e) {
	                //System.out.println("windowClosing");
	                System.exit(0);
	            }

	            public void windowDeactivated(WindowEvent e) {
	                //System.out.println("windowDeactivated");
	            }

	            public void windowDeiconified(WindowEvent e) {
	                //System.out.println("windowDeiconified");
	            }

	            public void windowIconified(WindowEvent e) {
	                System.out.println("windowIconified");
	            }

	            public void windowOpened(WindowEvent e) {
	                System.out.println("windowOpened");
	            }
	        });
		okButton.setMnemonic(KeyEvent.VK_L);
		cancelButton.setMnemonic(KeyEvent.VK_C);
		textUserName.addFocusListener(this);
		textPassword.addFocusListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		change.addMouseListener(this);
		change.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setVisible(true);
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stubla
		int count = retrivePass();


		String password;
		password=textPassword.getText().trim();
		if(arg0.getSource()==okButton)
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
							userName = textUserName.getText().toUpperCase()+" ";
							MainPage.labelUser.setText(userName);
							if(MainPage.user_role.equals(com.hms.util.Constants.ADMIN))
							{							
								//MainPage.tabbedPane.addTab("Configuration", null);
								WelcomeEntry.lblConfiguration.setVisible(true);
								WelcomeEntry.lblConfigurations.setVisible(true);
							}			
							//MainPage.tabbedPane.setSelectedIndex(2);
							setVisible(false);
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
		if(arg0.getSource()==cancelButton){
			DBConnection.closeDBConnection();
			System.exit(0);
		}

		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		((JTextField)arg0.getSource()).selectAll();
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//new ChangePass();
		if(arg0.getSource()==change)
		{
		Thread t=new Thread(){
			public void run()
			{
				new ChangePass(mpg);
			}
		};
		t.start();
		dispose();
		}
		else if(arg0.getSource()==lblRegister)
		{
			setVisible(false);
			new EmployeeEntry();
		}
	}
	public void logMTColor()
	{
		lblLoginForm.setForeground(new Color(SetColor.mtColor));
	}

	public void logCColor()
	{
		lblUserName.setForeground(new Color(SetColor.cColor));
		lblPasword.setForeground(new Color(SetColor.cColor));
		change.setForeground(new Color(SetColor.cColor));
		lblRegister.setForeground(new Color(SetColor.cColor));
	}
	public void logbkColor()
	{
		contentPanel.setBackground(new Color(SetColor.bkColor));
		buttonPane.setBackground(new Color(SetColor.bkColor));
	}
	public void logMTFont(String mtFType,int mtfProp,int mtSize)
	{
		lblLoginForm.setFont(new Font(mtFType,mtfProp,mtSize));
	}

	public void logCTFont(String ctFType,int ctfProp,int ctSize)
	{
		lblUserName.setFont(new Font(ctFType,ctfProp,ctSize));
		lblPasword.setFont(new Font(ctFType,ctfProp,ctSize));
		change.setFont(new Font(ctFType,ctfProp,ctSize));
		lblRegister.setFont(new Font(ctFType,ctfProp,ctSize));
	}
}
