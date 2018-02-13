package com.hotelmanagement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.hms.util.DBConnection;

public class Promote extends JDialog implements ActionListener,FocusListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	JButton btnCancel,btnOk;
	int kkk=0;
	static int year;
	static String cors,pcourse;
	Statement stmt;
	ResultSet rk;
	PreparedStatement psmt;
	MainPage mpg;
	Connection con = DBConnection.getDBConnection();
	static JFrame pf=new JFrame();
	public Promote(MainPage mpg) {
		super(pf, "", true);	
		this.mpg=mpg;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCrs = new JLabel("Class/Course");
			GridBagConstraints gbc_lblCrs = new GridBagConstraints();
			gbc_lblCrs.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblCrs.insets = new Insets(0, 0, 5, 5);
			gbc_lblCrs.gridx = 0;
			gbc_lblCrs.gridy = 0;
			contentPanel.add(lblCrs, gbc_lblCrs);
		}
		{
			comboBox = new JComboBox();
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 0;
			contentPanel.add(comboBox, gbc_comboBox);
		}
		{
			JLabel lblYrs = new JLabel("No. of Years");
			GridBagConstraints gbc_lblYrs = new GridBagConstraints();
			gbc_lblYrs.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblYrs.insets = new Insets(0, 0, 5, 5);
			gbc_lblYrs.gridx = 0;
			gbc_lblYrs.gridy = 1;
			contentPanel.add(lblYrs, gbc_lblYrs);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 2;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblFee = new JLabel("Fee");
			GridBagConstraints gbc_lblFee = new GridBagConstraints();
			gbc_lblFee.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblFee.insets = new Insets(0, 0, 0, 5);
			gbc_lblFee.gridx = 0;
			gbc_lblFee.gridy = 2;
			contentPanel.add(lblFee, gbc_lblFee);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 1;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOk = new JButton("Update");
				btnOk.setActionCommand("OK");
				buttonPane.add(btnOk);
				getRootPane().setDefaultButton(btnOk);
			}
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
		}
		try
		{
			//mpg.data=new SqlDataCon();
			stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rk=stmt.executeQuery("select * from courses");
			while(rk.next())
			{
				comboBox.addItem(rk.getString(1).toUpperCase());	
			}
			rk.first();

		}catch(Exception e){
			Thread t=new Thread(){
				public void run()
				{
					comboBox.removeAllItems();
					JOptionPane.showMessageDialog(null, "No data found", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				};
				t.start();
		}
		comboBox.addFocusListener(this);
		btnCancel.addActionListener(this);
		btnOk.addActionListener(this);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==btnCancel)
			pf.setVisible(false);
		else if(arg0.getSource()==btnOk)
		{
			
			String id;
			float bal;
			pcourse=(String) comboBox.getSelectedItem();
			if(JOptionPane.showConfirmDialog(null, "Do you wish to promote to "+comboBox.getSelectedItem()+"?","Choose One" , JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
			try
			{
				 if(kkk==1)
				{

				}
				psmt=con.prepareStatement("update student set course=?,year=?,fee=?,balance=? where id=?");
				PreparedStatement pst=con.prepareStatement("select id,fee,balance from student where course=? and year=?");
				pst.setString(1,cors);
				pst.setInt(2, year);
				rk=pst.executeQuery();

				while(rk.next())
				{
					id=rk.getString("id");
					bal=rk.getFloat("balance");
					promote(bal,id);
				}

			}catch(Exception e){JOptionPane.showMessageDialog(this, "Enter the details properly"+e, "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			JOptionPane.showMessageDialog(this,"Data Updated Successfully.","Success",JOptionPane.INFORMATION_MESSAGE);	
			pf.setVisible(false);
		}
		}
		
	
	}
	private void promote(float balance,String id) throws SQLException {
		Calendar calendar = new GregorianCalendar();
		year       = calendar.get(Calendar.YEAR);
		float fee=Float.parseFloat(textField_1.getText());
		balance=balance+fee;
		psmt.setString(1,(String) comboBox.getSelectedItem());
		psmt.setInt(2, year);
		psmt.setFloat(3,fee);
		psmt.setFloat(4,balance);
		psmt.setString(5, id);
		psmt.execute();
	}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==comboBox)
		{
		String crs=(String) comboBox.getSelectedItem();
		try
		{
			PreparedStatement psmt=con.prepareStatement("select years,fee from courses where coursename=?");
			psmt.setString(1,crs);
			rk=psmt.executeQuery();
			if(rk.next())
			{
			textField.setText(Integer.toString(rk.getInt(1)));
			textField_1.setText(Float.toString(rk.getFloat(2)));
			}
		}catch(Exception e){System.out.println(e);}
		}
	}

}
