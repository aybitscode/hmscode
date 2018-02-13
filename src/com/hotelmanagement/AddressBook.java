package com.hotelmanagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hms.util.DBConnection;

public class AddressBook extends JDialog implements FocusListener,ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	JTextArea textArea;
	JLabel lblAddres,lblStudentId;
	MainPage mpg;
	JButton okButton,cancelButton;
	private JButton btnClear;
	JPanel buttonPane;
	static JFrame frm = new JFrame();
	Connection con = DBConnection.getDBConnection();
	public AddressBook(MainPage mpg) {
		super(frm, "Student Address Book", true);	
		this.mpg=mpg;
		setLocation(mpg.scrwidth-400,100);
		setSize(400,300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		
			 lblStudentId = new JLabel("Student Id");
			GridBagConstraints gbc_lblStudentId = new GridBagConstraints();
			gbc_lblStudentId.insets = new Insets(0, 0, 5, 5);
			gbc_lblStudentId.anchor = GridBagConstraints.EAST;
			gbc_lblStudentId.gridx = 0;
			gbc_lblStudentId.gridy = 0;
			contentPanel.add(lblStudentId, gbc_lblStudentId);
		
		
			textField = new JTextField();
			textField.setEditable(true);
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			
			lblAddres = new JLabel("Address");
			GridBagConstraints gbc_lblAddress = new GridBagConstraints();
			gbc_lblAddress.insets = new Insets(0, 0, 0, 5);
			gbc_lblAddress.gridx = 0;
			gbc_lblAddress.gridy = 1;
			contentPanel.add(lblAddres, gbc_lblAddress);

			textArea = new JTextArea();
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 1;
			gbc_textArea.gridy = 1;
			contentPanel.add(textArea, gbc_textArea);
	
		
		
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				okButton = new JButton("Print");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				btnClear = new JButton("Clear");
				buttonPane.add(btnClear);
			
			
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

				adCColor();
				adbkColor();
				adCTFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
				
				textField.addFocusListener(this);
				okButton.addActionListener(this);
				btnClear.addActionListener(this);
				cancelButton.addActionListener(this);
				setVisible(true);
	}


	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		textField.selectAll();
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		textField.setText(textField.getText().toUpperCase());
		int st=0;
		String sql = "select addrs from student where id =(?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, textField.getText().trim());
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				textArea.setText("\n\n"+rs.getString("addrs"));
				st++;
			}
			if(st==0 && textField.getText().trim().length()>0)
			{
				JOptionPane.showMessageDialog(this, "Student doesn't exist","Message",JOptionPane.INFORMATION_MESSAGE);
				textArea.setText("");
				textField.requestFocus(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==cancelButton)
		{
			frm.setVisible(false);
		}
		else if(arg0.getSource()==okButton)
		{
			try {
				textArea.print();
			} catch (PrinterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(arg0.getSource()==btnClear)
		{
			textArea.setText("");
			textField.setText("");
		}
	}
	public void adMTColor()
	{

	}

	public void adCColor()
	{
		lblAddres.setForeground(new Color(SetColor.cColor));
		lblStudentId.setForeground(new Color(SetColor.cColor));
	}
	public void adbkColor()
	{
		contentPanel.setBackground(new Color(SetColor.bkColor));
		buttonPane.setBackground(new Color(SetColor.bkColor));
	}
	public void adMTFont(String mtFType,int mtfProp,int mtSize)
	{

	}

	public void adCTFont(String ctFType,int ctfProp,int ctSize)
	{
		lblAddres.setFont(new Font(ctFType,ctfProp,ctSize));
		lblStudentId.setFont(new Font(ctFType,ctfProp,ctSize));
	}

}
