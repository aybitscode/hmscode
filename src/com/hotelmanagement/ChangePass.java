package com.hotelmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hms.util.DBConnection;


public class ChangePass extends JDialog implements ActionListener,FocusListener {

	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	JTextField textField1;
	JPasswordField textField,textField2,textField3;
	JButton okButton,btnCancel;
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	String user,pass;
	JPanel buttonPane;
	/**
	 * Create the dialog.
	 */
	JLabel lblEnterTheUser,lblChangePassword,lblOldPassword,lblEnterNewPassword,lblConfirmPassword;
	Connection con = DBConnection.getDBConnection();
	MainPage mpg;
	public ChangePass(MainPage mpg) {
		super(mpg,"Change Password",true);
		this.mpg = mpg;
		setLocation(d.width/2-150,d.height/2-100);
		setSize(350,240);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			lblChangePassword = new JLabel("Change Password");
			lblChangePassword.setForeground(Color.ORANGE);
			lblChangePassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
			GridBagConstraints gbc_lblChangePassword = new GridBagConstraints();
			gbc_lblChangePassword.gridwidth = 6;
			gbc_lblChangePassword.insets = new Insets(0, 0, 5, 0);
			gbc_lblChangePassword.gridx = 0;
			gbc_lblChangePassword.gridy = 0;
			contentPanel.add(lblChangePassword, gbc_lblChangePassword);
		}
		{
			lblEnterTheUser = new JLabel("Enter the User Name");
			lblEnterTheUser.setForeground(Color.WHITE);
			lblEnterTheUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_lblEnterTheUser = new GridBagConstraints();
			gbc_lblEnterTheUser.anchor = GridBagConstraints.WEST;
			gbc_lblEnterTheUser.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterTheUser.gridx = 0;
			gbc_lblEnterTheUser.gridy = 2;
			contentPanel.add(lblEnterTheUser, gbc_lblEnterTheUser);
		}
		{
			textField1 = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 4;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 2;
			contentPanel.add(textField1, gbc_textField);
		}
		{
			lblOldPassword = new JLabel("Enter Old Password");
			lblOldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblOldPassword.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblOldPassword = new GridBagConstraints();
			gbc_lblOldPassword.anchor = GridBagConstraints.WEST;
			gbc_lblOldPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblOldPassword.gridx = 0;
			gbc_lblOldPassword.gridy = 3;
			contentPanel.add(lblOldPassword, gbc_lblOldPassword);
		}
		{
			textField2 = new JPasswordField(10);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.gridwidth = 4;
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 3;
			contentPanel.add(textField2, gbc_textField);
		}
		{
			lblEnterNewPassword = new JLabel("Enter New Password");
			lblEnterNewPassword.setForeground(Color.WHITE);
			lblEnterNewPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_lblEnterNewPassword = new GridBagConstraints();
			gbc_lblEnterNewPassword.anchor = GridBagConstraints.WEST;
			gbc_lblEnterNewPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblEnterNewPassword.gridx = 0;
			gbc_lblEnterNewPassword.gridy = 4;
			contentPanel.add(lblEnterNewPassword, gbc_lblEnterNewPassword);
		}
		{
			textField3 = new JPasswordField(10);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 4;
			gbc_textField.insets = new Insets(0, 0, 5, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 4;
			contentPanel.add(textField3, gbc_textField);
		}
		{
			lblConfirmPassword = new JLabel("Confirm Password");
			lblConfirmPassword.setForeground(Color.WHITE);
			lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
			GridBagConstraints gbc_lblConfirmPassword = new GridBagConstraints();
			gbc_lblConfirmPassword.anchor = GridBagConstraints.WEST;
			gbc_lblConfirmPassword.insets = new Insets(0, 0, 0, 5);
			gbc_lblConfirmPassword.gridx = 0;
			gbc_lblConfirmPassword.gridy = 5;
			contentPanel.add(lblConfirmPassword, gbc_lblConfirmPassword);
		}
		{
			textField = new JPasswordField(10);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridwidth = 4;
			gbc_textField.insets = new Insets(0, 0, 0, 5);
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 5;
			contentPanel.add(textField, gbc_textField);
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("    OK    ");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.setEnabled(false);
			}
			{
				btnCancel = new JButton("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		chCTFont(SFont.ctFType, SFont.ctfProp, SFont.ctSize);
		chMTFont(SFont.mtFType, SFont.mtfProp, SFont.mtSize);
		chCColor();
		chMTColor();
		chbkColor();	
		okButton.setMnemonic(KeyEvent.VK_K);
		btnCancel.setMnemonic(KeyEvent.VK_C);
		textField1.addFocusListener(this);
		textField2.addFocusListener(this);
		textField.addFocusListener(this);
		textField3.addFocusListener(this);
		okButton.addActionListener(this);
		btnCancel.addActionListener(this);
		setVisible(true);
	}
	public void retrivePass()
	{
		try{
		PreparedStatement psmt= con.prepareStatement("update password set pass=? where user=?");
		psmt.setString(1,textField3.getText().trim());
		psmt.setString(2,textField1.getText().trim());
		psmt.execute();
		JOptionPane.showMessageDialog(this, "Password changed successfully","Message",JOptionPane.INFORMATION_MESSAGE);
		}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Message",JOptionPane.INFORMATION_MESSAGE);}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==okButton)
		{
			String user,pass;
			int st=0;
		try{
		Statement stmt= con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=stmt.executeQuery("select * from password");
		while(rs.next())
		{
			user=rs.getString(1);
			pass=rs.getString(2);
			//System.out.println("the user is"+user);
			if(user.equalsIgnoreCase(textField1.getText().trim())&&pass.equalsIgnoreCase(textField2.getText().trim()))
			{
					st=1;
					retrivePass();

//					Thread t=new Thread(){
//						public void run()
//						{
//							new Login();
//						}
//					};t.start();
					
					dispose();
			}
			else if(user.equalsIgnoreCase(textField1.getText().trim()))
			{
				st=2;
			}
			else if(pass.equalsIgnoreCase(textField2.getText().trim()))
			{
				st=3;
			}
			else{}
		}

		if(st==0)
		{
			JOptionPane.showMessageDialog(this, "User and password are incorrect","Error",JOptionPane.ERROR_MESSAGE);
			textField1.requestFocus();
			textField1.selectAll();
			okButton.setEnabled(false);
			textField.setText("");
			textField1.setText("");
			textField2.setText("");
			textField3.setText("");
			
		}
		if(st==2)
		{

			JOptionPane.showMessageDialog(this, "Password is incorrect","Error",JOptionPane.ERROR_MESSAGE);
			textField2.requestFocus();
			textField2.selectAll();

		}
		if(st==3)
		{
			JOptionPane.showMessageDialog(this, "User name is incorrect","Error",JOptionPane.ERROR_MESSAGE);
			textField1.requestFocus();
			textField1.selectAll();
		}
		}catch(Exception ee){JOptionPane.showMessageDialog(this, ""+ee,"Message",JOptionPane.INFORMATION_MESSAGE);}

		}
		if(arg0.getSource()==btnCancel)
		{
//			Thread t=new Thread(){
//				public void run()
//				{
//					new Login();
//				}
//			};t.start();
			mpg.triggerLogin();
			setVisible(false);
		}
		
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==textField)
			textField.selectAll();
		else
			if(arg0.getSource()==textField1)
				textField1.selectAll();
			else 

				if(arg0.getSource()==textField2)
					textField2.selectAll();
				else
						textField3.selectAll();
			
				
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

		
		if(arg0.getSource()==textField)
		{
			if(textField.getText().trim().equals(textField3.getText().trim()))
			{
			okButton.setEnabled(true);	
			okButton.requestFocus();
			}
			else
			{
			JOptionPane.showMessageDialog(this, "Password doesnt match","Error",JOptionPane.ERROR_MESSAGE);
			textField3.requestFocus();
			okButton.setEnabled(false);
			}
		}
		if(arg0.getSource()==textField1)
		{
			if(textField1.getText().trim().equalsIgnoreCase("admin")||textField1.getText().trim().equalsIgnoreCase("accountant"))
			{
				//okButton.setEnabled(true);
			}
			else
			{
				okButton.setEnabled(false);
			}
		}
		
	}
	public void chMTColor()
	{
		
		lblChangePassword.setForeground(new Color(SetColor.mtColor));
	}

	public void chCColor()
	{
		lblEnterTheUser.setForeground(new Color(SetColor.cColor));
		lblOldPassword.setForeground(new Color(SetColor.cColor));
		lblEnterNewPassword.setForeground(new Color(SetColor.cColor));
		lblConfirmPassword.setForeground(new Color(SetColor.cColor));
	}
	public void chbkColor()
	{
		contentPanel.setBackground(new Color(SetColor.bkColor));
		buttonPane.setBackground(new Color(SetColor.bkColor));
	}
	public void chMTFont(String mtFType,int mtfProp,int mtSize)
	{
		lblChangePassword.setFont(new Font(mtFType,mtfProp,mtSize));
	}

	public void chCTFont(String ctFType,int ctfProp,int ctSize)
	{
		lblEnterTheUser.setFont(new Font(ctFType,ctfProp,ctSize));
		lblOldPassword.setFont(new Font(ctFType,ctfProp,ctSize));
		lblEnterNewPassword.setFont(new Font(ctFType,ctfProp,ctSize));
		lblConfirmPassword.setFont(new Font(ctFType,ctfProp,ctSize));
	}

}
