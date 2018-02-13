//package com.feemgmt.org;
//import java.awt.Color;
//import java.awt.FileDialog;
//import java.awt.Font;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.swing.ButtonGroup;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.JTextArea;
//
//public class StudentEntry extends JPanel implements ItemListener,ActionListener,FocusListener,MouseListener {
//	private JTextField textField;
//	private JTextField textField_1;
//	private JTextField textField_2;
//	JLabel lblEnterStudentDetails;
//    private JLabel lblName;
//    private JLabel lblCourse;
//    private JLabel lblDateOfJoin;
//	private JComboBox<String> choice;
//	Statement stmt;
//	ResultSet rs;
//	private JLabel label_4;
//	private JComboBox<String> choice_1;
//	private JLabel label_5;
//	private JTextField textField_4;
//	private JButton btnSave;
//	private JLabel  lblStudentId,lblPhoto;
//	public int yr;
//	private JLabel label_6;
//	String fn;
//	FileInputStream fis;
//	private JButton btnCancel_1;
//	MainPage mpg;
//	private JPanel panel_1;
//	private JLabel lblFather;
//	private JLabel lblEmail;
//	private JTextField textField_3;
//	private JTextField textField_5;
//	private JLabel lblMobileNo;
//	private JTextField textField_6;
//	JTextArea textArea;
//	private JPanel panel;
//	private JLabel lblAdrs;
//	public StudentEntry(MainPage mpg){
//		this.mpg=mpg;
//		GridBagLayout gridBagLayout = new GridBagLayout();
//		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 80, 200, 0, 0};
//		gridBagLayout.rowHeights = new int[]{0, 0, 80, 150, 0, 0};
//		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
//		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0};
//		setLayout(gridBagLayout);
//		
//		lblEnterStudentDetails = new JLabel("Enter Student Details");
//		GridBagConstraints gbc_lblEnterStudentDetails = new GridBagConstraints();
//		gbc_lblEnterStudentDetails.anchor = GridBagConstraints.WEST;
//		gbc_lblEnterStudentDetails.gridwidth = 5;
//		gbc_lblEnterStudentDetails.insets = new Insets(0, 0, 5, 5);
//		gbc_lblEnterStudentDetails.gridx = 1;
//		gbc_lblEnterStudentDetails.gridy = 1;
//		add(lblEnterStudentDetails, gbc_lblEnterStudentDetails);
//		
//		panel = new JPanel();
//		GridBagConstraints gbc_panel = new GridBagConstraints();
//		gbc_panel.gridheight = 3;
//		gbc_panel.fill = GridBagConstraints.BOTH;
//		gbc_panel.insets = new Insets(0, 0, 5, 5);
//		gbc_panel.gridx = 1;
//		gbc_panel.gridy = 2;
//		add(panel, gbc_panel);
//		GridBagLayout gbl_panel = new GridBagLayout();
//		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
//		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
//		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//		panel.setLayout(gbl_panel);
//		lblStudentId = new JLabel("Student Id  ");
//		GridBagConstraints gbc_lblStudentId = new GridBagConstraints();
//		gbc_lblStudentId.anchor = GridBagConstraints.WEST;
//		gbc_lblStudentId.insets = new Insets(0, 0, 5, 5);
//		gbc_lblStudentId.gridx = 0;
//		gbc_lblStudentId.gridy = 0;
//		panel.add(lblStudentId, gbc_lblStudentId);
//		
//		textField = new JTextField();
//		GridBagConstraints gbc_textField = new GridBagConstraints();
//		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField.gridwidth = 2;
//		gbc_textField.insets = new Insets(0, 0, 5, 0);
//		gbc_textField.gridx = 1;
//		gbc_textField.gridy = 0;
//		panel.add(textField, gbc_textField);
//		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		textField.setColumns(10);
//		
//		lblName = new JLabel("Name");
//		GridBagConstraints gbc_lblName = new GridBagConstraints();
//		gbc_lblName.anchor = GridBagConstraints.WEST;
//		gbc_lblName.insets = new Insets(0, 0, 5, 5);
//		gbc_lblName.gridx = 0;
//		gbc_lblName.gridy = 1;
//		panel.add(lblName, gbc_lblName);
//		
//		textField_1 = new JTextField();
//		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
//		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_1.gridwidth = 2;
//		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
//		gbc_textField_1.gridx = 1;
//		gbc_textField_1.gridy = 1;
//		panel.add(textField_1, gbc_textField_1);
//		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		textField_1.setColumns(10);
//		
//		lblFather = new JLabel("Father's Name");
//		GridBagConstraints gbc_lblFather = new GridBagConstraints();
//		gbc_lblFather.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblFather.insets = new Insets(0, 0, 5, 5);
//		gbc_lblFather.gridx = 0;
//		gbc_lblFather.gridy = 2;
//		panel.add(lblFather, gbc_lblFather);
//		
//		textField_3 = new JTextField();
//		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
//		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_3.gridwidth = 2;
//		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
//		gbc_textField_3.gridx = 1;
//		gbc_textField_3.gridy = 2;
//		panel.add(textField_3, gbc_textField_3);
//		textField_3.setColumns(10);
//		
//		lblEmail = new JLabel("E-mail");
//		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
//		gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
//		gbc_lblEmail.gridx = 0;
//		gbc_lblEmail.gridy = 3;
//		panel.add(lblEmail, gbc_lblEmail);
//		
//		textField_5 = new JTextField();
//		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
//		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_5.gridwidth = 2;
//		gbc_textField_5.insets = new Insets(0, 0, 5, 0);
//		gbc_textField_5.gridx = 1;
//		gbc_textField_5.gridy = 3;
//		panel.add(textField_5, gbc_textField_5);
//		textField_5.setColumns(10);
//		
//		lblMobileNo = new JLabel("Mobile No");
//		GridBagConstraints gbc_lblMobileNo = new GridBagConstraints();
//		gbc_lblMobileNo.fill = GridBagConstraints.HORIZONTAL;
//		gbc_lblMobileNo.insets = new Insets(0, 0, 5, 5);
//		gbc_lblMobileNo.gridx = 0;
//		gbc_lblMobileNo.gridy = 4;
//		panel.add(lblMobileNo, gbc_lblMobileNo);
//		
//		textField_6 = new JTextField();
//		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
//		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_6.gridwidth = 2;
//		gbc_textField_6.insets = new Insets(0, 0, 5, 0);
//		gbc_textField_6.gridx = 1;
//		gbc_textField_6.gridy = 4;
//		panel.add(textField_6, gbc_textField_6);
//		textField_6.setColumns(10);
//		textField_6.setText("91-");
//		
//		lblCourse = new JLabel("Course");
//		GridBagConstraints gbc_lblCourse = new GridBagConstraints();
//		gbc_lblCourse.anchor = GridBagConstraints.WEST;
//		gbc_lblCourse.insets = new Insets(0, 0, 5, 5);
//		gbc_lblCourse.gridx = 0;
//		gbc_lblCourse.gridy = 5;
//		panel.add(lblCourse, gbc_lblCourse);
//		
//		choice = new JComboBox();
//		GridBagConstraints gbc_choice = new GridBagConstraints();
//		gbc_choice.fill = GridBagConstraints.BOTH;
//		gbc_choice.gridwidth = 2;
//		gbc_choice.insets = new Insets(0, 0, 5, 0);
//		gbc_choice.gridx = 1;
//		gbc_choice.gridy = 5;
//		panel.add(choice, gbc_choice);
//		
//		lblDateOfJoin = new JLabel("Fee");
//		GridBagConstraints gbc_lblDateOfJoin = new GridBagConstraints();
//		gbc_lblDateOfJoin.anchor = GridBagConstraints.WEST;
//		gbc_lblDateOfJoin.insets = new Insets(0, 0, 5, 5);
//		gbc_lblDateOfJoin.gridx = 0;
//		gbc_lblDateOfJoin.gridy = 6;
//		panel.add(lblDateOfJoin, gbc_lblDateOfJoin);
//		
//		textField_2 = new JTextField();
//		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
//		gbc_textField_2.fill = GridBagConstraints.BOTH;
//		gbc_textField_2.gridwidth = 2;
//		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
//		gbc_textField_2.gridx = 1;
//		gbc_textField_2.gridy = 6;
//		panel.add(textField_2, gbc_textField_2);
//		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		textField_2.setColumns(10);
//		
//		label_4 = new JLabel("Year");
//		GridBagConstraints gbc_label_4 = new GridBagConstraints();
//		gbc_label_4.anchor = GridBagConstraints.WEST;
//		gbc_label_4.insets = new Insets(0, 0, 5, 5);
//		gbc_label_4.gridx = 0;
//		gbc_label_4.gridy = 7;
//		panel.add(label_4, gbc_label_4);
//		
//		choice_1 = new JComboBox<String>();
//		GridBagConstraints gbc_choice_1 = new GridBagConstraints();
//		gbc_choice_1.fill = GridBagConstraints.HORIZONTAL;
//		gbc_choice_1.gridwidth = 2;
//		gbc_choice_1.insets = new Insets(0, 0, 5, 0);
//		gbc_choice_1.gridx = 1;
//		gbc_choice_1.gridy = 7;
//		panel.add(choice_1, gbc_choice_1);
//		
//		label_5 = new JLabel("Amount");
//		GridBagConstraints gbc_label_5 = new GridBagConstraints();
//		gbc_label_5.anchor = GridBagConstraints.WEST;
//		gbc_label_5.insets = new Insets(0, 0, 0, 5);
//		gbc_label_5.gridx = 0;
//		gbc_label_5.gridy = 8;
//		panel.add(label_5, gbc_label_5);
//		
//		textField_4 = new JTextField();
//		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
//		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField_4.gridwidth = 2;
//		gbc_textField_4.gridx = 1;
//		gbc_textField_4.gridy = 8;
//		panel.add(textField_4, gbc_textField_4);
//		textField_4.setColumns(10);
//		textField_4.setText("0.0");
//		
//				textField_4.addFocusListener(this);
//				textField_4.addFocusListener(this);
//		
//				choice_1.addItem("------Select------");
//				choice_1.addFocusListener(this);
//		textField_2.addFocusListener(this);
//		
//			choice.addItem("------Select------");
//			choice.addFocusListener(this);
//		textField_6.addFocusListener(this);
//		textField_5.addFocusListener(this);
//		textField_3.addFocusListener(this);
//		textField_1.addFocusListener(this);
//		textField.addFocusListener(this);
//		
//		lblAdrs = new JLabel("Address");
//		GridBagConstraints gbc_lblAdrs = new GridBagConstraints();
//		gbc_lblAdrs.anchor = GridBagConstraints.NORTH;
//		gbc_lblAdrs.insets = new Insets(0, 0, 5, 5);
//		gbc_lblAdrs.gridx = 3;
//		gbc_lblAdrs.gridy = 3;
//		add(lblAdrs, gbc_lblAdrs);
//		
//		textArea = new JTextArea();
//		GridBagConstraints gbc_textArea = new GridBagConstraints();
//		gbc_textArea.gridwidth = 2;
//		gbc_textArea.insets = new Insets(0, 0, 5, 5);
//		gbc_textArea.fill = GridBagConstraints.BOTH;
//		gbc_textArea.gridx = 4;
//		gbc_textArea.gridy = 3;
//		add(textArea, gbc_textArea);
//		
//		lblPhoto = new JLabel("Browse");
//		GridBagConstraints gbc_lblPhoto = new GridBagConstraints();
//		gbc_lblPhoto.anchor = GridBagConstraints.NORTH;
//		gbc_lblPhoto.insets = new Insets(0, 0, 5, 5);
//		gbc_lblPhoto.gridx = 3;
//		gbc_lblPhoto.gridy = 2;
//		add(lblPhoto, gbc_lblPhoto);
//
//		panel_1 = new JPanel();
//		panel_1.setBackground(Color.BLACK);
//		panel_1.setLayout(null);
//		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
//		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
//		gbc_panel_1.fill = GridBagConstraints.BOTH;
//		gbc_panel_1.gridx = 4;
//		gbc_panel_1.gridy = 2;
//		add(panel_1, gbc_panel_1);
//		label_6 = new JLabel("");
//		label_6.setBounds(0, 0, 66, 74);
//		panel_1.add(label_6);
//		label_6.setIcon(new ImageIcon(UpdatePanel.class.getResource("/images/default.jpg")));
//		
//				System.out.println("Label width is"+label_6.getWidth());
//				System.out.println("Label height is"+label_6.getHeight());
//				label_6.addMouseListener(this);
//		for(int i=2008;i<2050;i++)
//			choice_1.addItem(Integer.toString(i));
//		try
//		{
//			//mpg.data=new SqlDataCon();
//			stmt=mpg.data.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//			rs=stmt.executeQuery("select * from courses");
//			while(rs.next())
//			{
//				choice.addItem(rs.getString(1).toUpperCase());	
//			}
//			rs.first();
//
//		}catch(Exception e){
//			Thread t=new Thread(){
//				public void run()
//				{
//					choice.removeAllItems();
//					JOptionPane.showMessageDialog(this, "No courses found", "Error Message", JOptionPane.ERROR_MESSAGE);
//				}
//				};
//				t.start();
//		}
//		
//		btnSave = new JButton("Submit");
//		GridBagConstraints gbc_btnSave = new GridBagConstraints();
//		gbc_btnSave.fill = GridBagConstraints.BOTH;
//		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
//		gbc_btnSave.gridx = 4;
//		gbc_btnSave.gridy = 4;
//		add(btnSave, gbc_btnSave);
//		btnSave.setMnemonic(KeyEvent.VK_B);
//		btnSave.addActionListener(this);
//		
//		btnCancel_1 = new JButton("Cancel");
//		GridBagConstraints gbc_btnCancel_1 = new GridBagConstraints();
//		gbc_btnCancel_1.insets = new Insets(0, 0, 5, 5);
//		gbc_btnCancel_1.fill = GridBagConstraints.BOTH;
//		gbc_btnCancel_1.gridx = 5;
//		gbc_btnCancel_1.gridy = 4;
//		add(btnCancel_1, gbc_btnCancel_1);
//		btnCancel_1.setMnemonic(KeyEvent.VK_C);
//		uplcColor();
//		uplmtColor();
//		uplbkColor();
//		uplcFont(SFont.ctFType,SFont.ctfProp,SFont.ctSize);
//		uplSTFont(SFont.stFType,SFont.stfProp,SFont.stSize);
//		btnCancel_1.addActionListener(this);
//		
//	}
//
//	private void setClear1()
//	{
//		textField.setText("");
//		textField_1.setText("");
//		textField_2.setText("");
//		textField_4.setText("0.0");
//		textField_3.setText("");
//		textField_5.setText("");
//		textField_6.setText("91-");
//		textArea.setText("");
//		label_6.setIcon(new ImageIcon(UpdatePanel.class.getResource("/images/default.jpg")));
//	}
//
//	@Override
//	public void itemStateChanged(ItemEvent arg0) {
//		
//		
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if(e.getSource()==btnCancel_1)
//		{
//			label_6.setIcon(new ImageIcon(UpdatePanel.class.getResource("/images/default.jpg")));
//			setClear1();
//		}
//
//				if(e.getSource()==btnSave)
//				{
//					Float bal,fee=0.0f,amt=0.0f;
//
//			
//
//					try{
//						URI uri=new URI("FeeManagement/src/images/default.jpg");
//							fee=Float.parseFloat(textField_2.getText().trim());
//							amt=Float.parseFloat(textField_4.getText().trim());
//							if(choice.getSelectedItem().equals("------Select------"))
//								throwError("Select the course");
//							if(Float.parseFloat(textField_4.getText().trim())>Float.parseFloat(textField_2.getText().trim()))
//							{
//								throwError("Amount paid should be less than fee");
//							}
//							yr=Integer.parseInt((String) choice_1.getSelectedItem());
//						bal=fee-amt;
//						if(fn==null)
//							fn="C:/oraexe/accdata/db/admin/dbase/default.jpg";
//					File img=new File(fn);
//					System.out.println("The fn value is sibbu check"+fn);
//					PreparedStatement pst=mpg.data.con.prepareStatement("insert into student(img,id,name,fname,email,mobile,course,year,fee,balance,addrs) "+ "values(?,?,?,?,?,?,?,?,?,?,?)");
//					PreparedStatement psts=mpg.data.con.prepareStatement("insert into payments(id,name,course,pdate,amtpd,balance,year)"+ "values(?,?,?,?,?,?,?)");
//					
//					fis=new FileInputStream(img);
//					pst.setBinaryStream(1, (InputStream)fis, (int)(img.length()));
//					pst.setString(2,textField.getText().toUpperCase().trim());
//					pst.setString(3,textField_1.getText().toUpperCase().trim());
//					pst.setString(4,textField_3.getText().toUpperCase().trim());
//					pst.setString(5,textField_5.getText().trim());
//					pst.setString(6,textField_6.getText().trim());
//					pst.setString(7,(String) choice.getSelectedItem());
//					pst.setInt(8,yr);
//					pst.setFloat(9,Float.parseFloat(textField_2.getText().trim()));
//					pst.setFloat(10,bal);
//					pst.setString(11,textArea.getText());
//					int s=pst.executeUpdate();
//					
//					psts.setString(1,textField.getText().toUpperCase().trim());
//					psts.setString(2,textField_1.getText().toUpperCase().trim());
//					psts.setString(3,(String) choice.getSelectedItem());
//						java.sql.Date dt = new java.sql.Date(System.currentTimeMillis());
//						psts.setDate(4, dt);
//					psts.setFloat(5,Float.parseFloat(textField_4.getText().trim()));
//					psts.setFloat(6,bal);
//					psts.setInt(7, yr);
//					int s1=psts.executeUpdate();
//					if(s>0&&s1>0)
//					{
//					JOptionPane.showMessageDialog(this,"Data Updated Successfully","Success",JOptionPane.INFORMATION_MESSAGE);
//					label_6.setIcon(new ImageIcon(UpdatePanel.class.getResource("/images/default.jpg")));
//					textField.requestFocus(true);
//					setClear1();
//					}
//					else
//					{
//					JOptionPane.showMessageDialog(this,"Check For Solution","Failure",JOptionPane.ERROR_MESSAGE);
//					}
//					
//				
//					}catch(NumberFormatException ee){
//						textField.requestFocus(true);
//						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);}
//					catch(SQLException ee)
//					{
//						JOptionPane.showMessageDialog(this,"Duplicate value"+" '"+textField.getText()+"' "+" already exist"+ee,"Failure",JOptionPane.ERROR_MESSAGE);
//					} catch (FileNotFoundException ee) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
//					} catch (URISyntaxException ee) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
//					} catch (Exception ee) {
//						// TODO Auto-generated catch block
//						JOptionPane.showMessageDialog(this,""+ee,"Failure",JOptionPane.ERROR_MESSAGE);
//					}
//					
//					
//				}
//			}
//	
//	private static void throwError(String msg) throws Exception 
//	{
//		    throw new Exception(msg);
//	} 
//
//	@Override
//	public void focusGained(FocusEvent arg0) {
//		// TODO Auto-generated method stub
//		if(arg0.getSource()==textField)
//			textField.selectAll();
//		else if(arg0.getSource()==textField_1)
//			textField_1.selectAll();
//		else if(arg0.getSource()==textField_2)
//			textField_2.selectAll();
//		else if(arg0.getSource()==textField_3)
//			textField_3.selectAll();
//		else if(arg0.getSource()==textField_4)
//			textField_4.selectAll();
//		else if(arg0.getSource()==textField_5)
//			textField_5.selectAll();
//		else if(arg0.getSource()==textField_6)
//			textField_6.selectAll();
//		else if(arg0.getSource()==choice)
//			textField_2.setText("");
//		else {}
//	}
//	@Override
//	public void focusLost(FocusEvent arg0) {
//
//		if(arg0.getSource()==textField)
//			textField.setText(textField.getText().trim().toUpperCase());
//		else if(arg0.getSource()==textField_1)
//			textField_1.setText(textField_1.getText().trim().toUpperCase());
//		else if(arg0.getSource()==textField_3)
//			textField_3.setText(textField_3.getText().trim().toUpperCase());
//		else if(arg0.getSource()==textField_5)
//		{
//			String hex=textField_5.getText().trim();
//			if(!(hex.equals("")))
//			{
//			final String EMAIL_PATTERN = 
//					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//		
//			Matcher matcher = pattern.matcher(hex);
//			if(!(matcher.matches()))
//			{
//				JOptionPane.showMessageDialog(this, "Invalid email pattern","Error", JOptionPane.ERROR_MESSAGE);
//				textField_5.requestFocus(true);
//				
//			}
//	
//			
//			}
//		}
//		else if(arg0.getSource()==textField_6)
//		{
//			if(textField_6.getText().equals(""))
//				textField_6.setText("91-");
//				String phoneno=textField_6.getText().trim();
//				if(!(phoneno.equals("91-")))
//				{
//			      Pattern pattern = Pattern.compile("\\d{2}-\\d{10}");
//			      if(!phoneno.startsWith("91-"))
//			      {
//			    	  phoneno="91-"+phoneno;
//			    	  textField_6.setText(phoneno);
//			      }
//			      Matcher matcher = pattern.matcher(phoneno);
//			      if(!matcher.matches())
//			      {
//			    	  JOptionPane.showMessageDialog(this, "Invalid phone number","Error", JOptionPane.ERROR_MESSAGE);
//			    	  textField_6.requestFocus(true);
//			      }
//				}
//		}
//		else{}
//		if(arg0.getSource()==choice)
//		{
//			String crs=(String) choice.getSelectedItem();
//			try
//			{
//				PreparedStatement psmt=mpg.data.con.prepareStatement("Select fee from courses where coursename=?");
//				psmt.setString(1,crs);
//				rs=psmt.executeQuery();
//				if(rs.next())
//				textField_2.setText(Float.toString(rs.getFloat(1)));
//				System.out.println("fee is"+rs.getFloat(1));
//			}catch(Exception e){}
//	
//		}
//	}
//
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//	private String returnMonth(int mm)
//	{
//		String month="";
//		if(mm==1)
//			month="JAN";
//		else if(mm==2)
//			month="FEB";
//			else if(mm==3)
//				month="MAR";
//			else if(mm==4)
//				month="APR";
//			else if(mm==5)
//				month="MAY";
//			else if(mm==6)
//				month="JUN";
//			else if(mm==7)
//				month="JUL";
//			else if(mm==8)
//				month="AUG";
//			else if(mm==9)
//				month="SEP";
//			else if(mm==10)
//				month="OCT";
//			else if(mm==11)
//				month="NOV";
//			else if(mm==12)
//				month="DEC";
//			else{ }
//		return month;
//	}
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		try{
//			 FileDialog fd=new FileDialog(new JFrame(),"Open",FileDialog.LOAD);
//			fd.setVisible(true);
//			fn=fd.getDirectory()+fd.getFile();
//			System.out.println(fn);
//			if(fn.equals("nullnull"))
//				fn="C:/oraexe/accdata/db/admin/dbase/default.jpg";
//			label_6.setIcon(new ImageIcon(fn));
//			System.out.println("sibbu fn is"+fn);
//
//			}catch(Exception ee){System.out.println(ee);}
//			
//		System.out.println("Label width is"+label_6.getWidth());
//		System.out.println("Label height is"+label_6.getHeight());
//	}
//	public void uplcColor()
//	{
//		lblStudentId.setForeground(new Color(SetColor.cColor));
//		lblFather.setForeground(new Color(SetColor.cColor));
//		lblEmail.setForeground(new Color(SetColor.cColor));
//		lblName.setForeground(new Color(SetColor.cColor));
//		lblCourse.setForeground(new Color(SetColor.cColor));
//		label_4.setForeground(new Color(SetColor.cColor));
//		label_5.setForeground(new Color(SetColor.cColor));
//		lblDateOfJoin.setForeground(new Color(SetColor.cColor));
//		lblMobileNo.setForeground(new Color(SetColor.cColor));
//		lblPhoto.setForeground(new Color(SetColor.cColor));
//		lblAdrs.setForeground(new Color(SetColor.cColor));
//	}
//	public void uplmtColor()
//	{
//		lblEnterStudentDetails.setForeground(new Color(SetColor.mtColor));
//	}
//	public void uplbkColor()
//	{
//		setBackground(new Color(SetColor.bkColor));
//		panel.setBackground(new Color(SetColor.bkColor));
//		panel_1.setBackground(new Color(SetColor.bkColor));
//	}
//	public void uplcFont(String ctFType,int ctfProp,int ctSize)
//	{
//		lblMobileNo.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblStudentId.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblFather.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblEmail.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblName.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblCourse.setFont(new Font(ctFType,ctfProp,ctSize));
//		label_4.setFont(new Font(ctFType,ctfProp,ctSize));
//		label_5.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblDateOfJoin.setFont(new Font(ctFType,ctfProp,ctSize));
//		btnSave.setFont(new Font(ctFType,ctfProp,ctSize));
//		btnCancel_1.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblPhoto.setFont(new Font(ctFType,ctfProp,ctSize));
//		lblAdrs.setFont(new Font(ctFType,ctfProp,ctSize));
//	}
//	public void uplSTFont(String stFType,int stfProp,int stSize)
//	{
//		lblEnterStudentDetails.setFont(new Font(stFType,stfProp,stSize));
//	}
//}
