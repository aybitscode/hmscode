package com.hotelmanagement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.SqlDateModel;

import com.hms.util.DBConnection;


public class Payments extends JPanel implements ActionListener,FocusListener,MouseListener {
	JTextField textField;
	JTextField textField_1;
	private JButton btnSearch;
	boolean b=false;
	private JButton btnNewButton;
	private JScrollPane scroll;
	JTable table;
	Statement stmt;
	int cryr,mm,day,hide=0,sv=0,jnyear=0,enbl=0;
	Float pbal=(float) 0.0,balanc=(float) 0.0;
	String month;
	String id,name,course,baltxt,yr,acdyr,email;
	static String fname,fcourse,facademic,famtpd,fid;
	static float famt;
	private JButton btnUpdate;
	private JButton btnPrint;
	TableCellRenderer buttonRend;
	JLabel label,label_1,label_2,label_3,lblAmount,lblDate,label_8;
	ImageIcon icon;
	private JDatePickerImpl datePicker;
	JDatePanelImpl datePanel;
	Calendar calendar;
	SimpleDateFormat sdf;
	java.sql.Date selectedDate;
	static java.sql.Date fdate;
	Message message;
	/**
	 * Create the panel.
	 */
	MainPage mpg;
	Connection con = DBConnection.getDBConnection();
	public Payments(MainPage mpg) {
		this.mpg=mpg;
		//mpg.data=new SqlDataCon();
		buttonRend=new JTableButtonRend();
		setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 75, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{181, 46, 0, 0, -22, 0, 0, 0, 0, 40, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setVisible(false);
		
		label_2 = new JLabel("Payment Details");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.gridwidth = 4;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 1;
		add(label_2, gbc_label_2);
			
		label = new JLabel("Student Id  ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textField, gbc_textField);
		textField.setColumns(10);
		lblAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.WEST;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 1;
		gbc_lblAmount.gridy = 4;
		add(lblAmount, gbc_lblAmount);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 4;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		lblDate = new JLabel("Date");
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.anchor = GridBagConstraints.WEST;
		gbc_lblDate.fill = GridBagConstraints.VERTICAL;
		gbc_lblDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 3;
		add(lblDate, gbc_lblDate);
		Calendar cal = new GregorianCalendar();
		int tyr = cal.get(Calendar.YEAR);
		int tmon = cal.get(Calendar.MONTH);
		int tday = cal.get(Calendar.DAY_OF_MONTH);
		SqlDateModel model = new SqlDateModel();
		model.setDate(tyr, tmon, tday);
		model.setSelected(true);
		datePanel = new JDatePanelImpl(model);		
		datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		datePicker.getJFormattedTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));

		
		GridBagConstraints gbc_datePic = new GridBagConstraints();
		gbc_datePic.gridwidth = 3;
		gbc_datePic.fill = GridBagConstraints.BOTH;
		gbc_datePic.insets = new Insets(0, 0, 5, 5);
		gbc_datePic.gridx = 2;
		gbc_datePic.gridy = 3;
		add(datePicker, gbc_datePic);
		
		 btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.fill = GridBagConstraints.BOTH;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 5;
		add(btnSearch, gbc_btnSearch);
		
		btnNewButton = new JButton("Clear");
		btnNewButton.setEnabled(false);
	
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.insets = new Insets(0, 0, 5, 5);
		gbc_btnUpdate.gridx = 2;
		gbc_btnUpdate.gridy = 5;
		add(btnUpdate, gbc_btnUpdate);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 5;
		add(btnNewButton, gbc_btnNewButton);
		
		btnPrint = new JButton("Print");
		btnPrint.setEnabled(false);
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.fill = GridBagConstraints.BOTH;
		gbc_btnPrint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPrint.gridx = 3;
		gbc_btnPrint.gridy = 5;
		add(btnPrint, gbc_btnPrint);
		
		label_3 = new JLabel("Student Details");
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.gridwidth = 4;
		gbc_label_3.anchor = GridBagConstraints.SOUTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 7;
		add(label_3, gbc_label_3);

		table=new JTable();
		scroll = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 8;
		add(scroll, gbc_scrollPane);
		label_3.setVisible(false);
		scroll.setVisible(false);

		
		label_8 = new JLabel("");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 2;
		gbc_label_8.gridy = 10;
		textField.requestFocus();
		payCTFont(SFont.ctFType, SFont.ctfProp, SFont.ctSize);
		paySTFont(SFont.stFType, SFont.stfProp, SFont.stSize);
		payCColor();
		payMTColor();
		paybkColor();		
		btnSearch.setMnemonic(KeyEvent.VK_H);
		btnNewButton.setMnemonic(KeyEvent.VK_C);
		btnUpdate.setMnemonic(KeyEvent.VK_U);
		btnPrint.setMnemonic(KeyEvent.VK_P);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnNewButton.addActionListener(this);
		btnPrint.addActionListener(this);
		textField.addFocusListener(this);
		textField_1.addFocusListener(this);
		
	}
	public  void display()
	{
	
		int wyr,nyrs=0,acyr=0;
		Float bal=(float) 0.0,pamt,arrs,fee=(float) 0.0,peryr;
		textField.setEditable(false);
		label_3.setVisible(true);
		String columnNames[]={"PHOTO","ID","NAME","COURSE","YEAR OF JOIN","ACADEMIC YEAR","CURR. YEAR FEE","ARREARS","BALANCE"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames); 
		table.setModel(model); 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		table.setRowHeight(90);
		//table.getTableHeader().setBackground(Color.black);
		table.getTableHeader().setForeground(Color.black);
		table.getTableHeader().setFont(new Font("Tahoma",Font.PLAIN,16));
		table.getColumn("PHOTO").setCellRenderer(buttonRend);
		table.getColumn("PHOTO").setMaxWidth(90);
		table.addMouseListener(new JTableButtonMouseListener(table));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		String textvalue = textField.getText().trim();
		try{ 
			String sql = "select * from student where id =(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, textvalue);
			ResultSet rs = ps.executeQuery();
			int l =0;
			if(rs.next())
			{
				id = rs.getString("id");
				name = rs.getString("name");
				email=rs.getString("email");
				course = rs.getString("course");
				fee=rs.getFloat("fee");
				jnyear = rs.getInt("year");
				bal=rs.getFloat("balance");
				
				 byte[] imagedata = rs.getBytes(1) ;
		     	 Image img = Toolkit.getDefaultToolkit().createImage(imagedata);
		   	   	icon =new ImageIcon(img);
		   	 label_8.setIcon(icon);
		   	 acyr=calendar.get(Calendar.YEAR);//hereeeeeeeeeeeeeee
				l++;	
			}
			if(l==0)
			{
				scroll.setVisible(false);
				label_3.setVisible(false);
			}
			
			if(jnyear>acyr)
			{
				enbl=1;
				textField_1.setEditable(false);
				label_3.setVisible(false);
				System.out.println("jnyear is"+jnyear);
				System.out.println("curyear is"+acyr);
				JOptionPane.showMessageDialog(this, "Select the year greater than joining year","Error!!",JOptionPane.ERROR_MESSAGE);

			}
			else
			{
				scroll.setVisible(true);
			}
		
			PreparedStatement pst= con.prepareStatement("select years from courses where coursename=?");
			pst.setString(1,course);
			ResultSet rk=pst.executeQuery();
			if(rk.next())
				nyrs=rk.getInt(1);
				 wyr=cryr-jnyear;
				 int n=wyr;
				 peryr=fee/nyrs;
				 pamt=fee-bal;
				 if(n<1)
					 n=1;
				 if(n>nyrs)
					 n=nyrs;
					 if(n==1)
					 {
						 if(pamt<peryr)
						 {
						 bal=peryr-pamt;
						 pbal=bal;
						 }
						 else 
							 bal=(float) 0.0;
						 arrs=(float) 0.0;
					 }
					 else
					 {
						 arrs=(n-1)*peryr-pamt;
						 if(arrs<0)
							 arrs=(float) 0.0;
						 if(arrs==0)
						 {
							 bal=n*peryr-pamt;
							 if(bal<0)
								 bal=(float) 0.0;
							 pbal=bal;
						 }
						 else
						     bal=peryr+arrs;
						 pbal=bal;
					 }
					 if(jnyear==acyr)
						 acdyr=""+jnyear+" - "+(acyr+1);
					 else
						 acdyr=""+(acyr-1)+" - "+acyr;
					 if(arrs==0)
					 {
						 if(bal>peryr)
						 {
							 arrs=bal-peryr;
							 
						 }
					 }
					
			model.addRow(new Object[]{label_8,id, name, course,jnyear,acdyr,peryr, arrs, bal });
			if(l <1)
			{
				enbl=1;
				scroll.setVisible(false);
				textField.setEditable(true);
				textField_1.setEditable(false);
				JOptionPane.showMessageDialog(this, "No Record Found","Error",JOptionPane.ERROR_MESSAGE);
			}
			}catch(Exception ex){
				textField.setText("");
				textField.setEditable(true);
				textField_1.setEditable(false);
				btnUpdate.setEnabled(false);
				scroll.setVisible(false);
				btnPrint.setEnabled(false);
				label_3.setVisible(false);
				btnSearch.setEnabled(false);
				JOptionPane.showMessageDialog(this, "No data found enter the details correctly"+ex,"Error",JOptionPane.ERROR_MESSAGE);

			}
		
	}
	  public static void throwError() throws Exception {
		    throw new Exception();
		  } 
		@Override
	public void actionPerformed(ActionEvent e) throws NumberFormatException {
		if(e.getSource()==btnSearch)
		{
			try
			{
			selectedDate = (java.sql.Date) datePicker.getModel().getValue();
			calendar = Calendar.getInstance();
			calendar.setTime(selectedDate);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			mm = calendar.get(Calendar.MONTH);
			mm = mm+1;
			cryr = calendar.get(Calendar.YEAR);
				scroll.setVisible(false);
				if(textField.getText().trim().equals(""))
					JOptionPane.showMessageDialog(this, "Enter the details correctly", "Error",JOptionPane.ERROR_MESSAGE);
				else
				{
				display();
				if(enbl==0)
				{
				textField_1.requestFocus();
				textField_1.setEditable(true);
				textField_1.setText("");
				btnNewButton.setEnabled(true);
				btnUpdate.setEnabled(true);
				btnSearch.setEnabled(false);
				}
				enbl=0;
				message = connectToMail();
				}
			}catch(Exception ee){};
				
	
		}
		if(e.getSource()==btnUpdate)
		{
					 	float amt=0.0f;
					 	String name="",textvalue,course="",id="",date;
					 	date=mm+"/"+day+"/"+cryr;
						try{
						amt=Float.parseFloat(textField_1.getText().trim());
						textvalue=textField.getText().trim();
						String sql = "select * from student where id=(?)";
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, textvalue);
						ResultSet rs = ps.executeQuery();
						int i =0;
						if(rs.next())
						{
							id=rs.getString("id");
							name=rs.getString("name");
							course=rs.getString("course");
							balanc=rs.getFloat("balance");
							i++; 
						}
						if(i <1)
						{
							JOptionPane.showMessageDialog(this, "No Record Found ","Error",JOptionPane.ERROR_MESSAGE);
						}
					
						balanc=balanc-amt;
						if(balanc<0||amt==0)
							throwError();
						
							PreparedStatement pst= con.prepareStatement("update student set balance=? where id=?");
							pst.setFloat(1,balanc);
							pst.setString(2,textField.getText().toUpperCase().trim());
							pst.execute();
			
							display();
							fdate=selectedDate;
							famt=amt;
							PreparedStatement psst= con.prepareStatement("insert into payments(id,name,course,pdate,amtpd,balance,year) "+ "values(?,?,?,?,?,?,?)");
							psst.setString(1,id.toUpperCase());
							psst.setString(2,name.toUpperCase());
							psst.setString(3,course.toUpperCase());
							psst.setDate(4,selectedDate);
							psst.setFloat(5,amt);
							psst.setFloat(6,pbal);
							psst.setInt(7,jnyear);
							int s=psst.executeUpdate();
							if(s>0)
							{
								textField.setEditable(true);
								textField_1.setEditable(false);
								btnPrint.setEnabled(true);
								btnUpdate.setEnabled(false);
								if(email.equals(""))
								{
								JOptionPane.showMessageDialog(this, "Data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
								if(JOptionPane.showConfirmDialog(null,"Data updated successfully. Do you wish to send  mail to "+"' "+email+" '", "Choose one", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
								{
									Thread t = new Thread(new Runnable(){
										public void run()
										{
											try {
												message.setText("Dear parent we convey thanks for paying the fee of your son/daughter in the time"
														+ "\n your had paid"+famt+"\n Balance is"+pbal);

												Transport.send(message);
												JOptionPane.showMessageDialog(null, "Mail send successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
											} catch (MessagingException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
									 					
										}
									});
									t.start();
						
								}
								}
							}
					}catch(NumberFormatException ee){
						JOptionPane.showMessageDialog(this,"Enter the values correctly","Failure",JOptionPane.ERROR_MESSAGE);
						textField_1.requestFocus(true);
						textField_1.setEditable(true);
						btnUpdate.setEnabled(true);
						} 
						catch (SQLException ee) {
						// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(this,"Database exception consult admin","Failure",JOptionPane.ERROR_MESSAGE);
							textField_1.requestFocus(true);
							textField_1.setEditable(false);
							btnUpdate.setEnabled(false);
					} catch (AddressException ee) {
							// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this,"From Address Exception ","Failure",JOptionPane.ERROR_MESSAGE);
						textField_1.requestFocus(true);
						textField_1.setEditable(false);
						btnUpdate.setEnabled(false);
						} catch (MessagingException ee) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(this,"Internet connection is not available","Failure",JOptionPane.ERROR_MESSAGE);
							textField_1.requestFocus(true);
							textField_1.setEditable(false);
							btnUpdate.setEnabled(false);
						} catch (Exception ee) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(this,"No balance, amount already paid","Failure",JOptionPane.ERROR_MESSAGE);
							textField_1.requestFocus(true);
							textField_1.setEditable(false);
							btnUpdate.setEnabled(false);
						}				
				}
		if(e.getSource()==btnNewButton)
			{
			textField.requestFocus(true);
			textField.setText("");
			textField.setEditable(true);
			textField_1.setEditable(false);
			textField_1.setText("0");
			btnUpdate.setEnabled(false);
			scroll.setVisible(false);
			btnPrint.setEnabled(false);
			label_3.setVisible(false);
			btnSearch.setEnabled(true);
			
			}
		if(e.getSource()==btnPrint)
		{
			int acd1 = calendar.get(Calendar.YEAR);
			int acd2 = acd1-1;
			fname=name.toUpperCase();
			fcourse=course.toUpperCase();
			fid=id.toUpperCase();
			facademic=""+acd2+"-"+acd1;
			famtpd=textField_1.getText().trim();
			FeeReceipt fcp=new FeeReceipt(mpg);
			//btnPrint.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnSearch.setEnabled(true);
			textField_1.setEditable(false);		
			}

		

	}
		private Message connectToMail() throws MessagingException,
				AddressException {
			final String username = "mohammed.shaik81@gmail.com";
			final String password = "mohammedjuver";
 
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mohammed.shaik81@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Fee Payment Details");
			return message;
		}
	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==textField)
			textField.selectAll();
		if(arg0.getSource()==textField_1)
			textField_1.selectAll();
		updateUI();
		
	}
	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==textField)
		{
			textField.setText(textField.getText().toUpperCase());
		}
		
	}
   private	class JTableButtonRend implements TableCellRenderer {		
   		@Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
   			JLabel butt = (JLabel)value;
   			if (isSelected) {
   				butt.setForeground(table.getSelectionForeground());
   				butt.setBackground(table.getSelectionBackground());
   		    } else {
   		    	butt.setForeground(table.getForeground());
   		    	butt.setBackground(UIManager.getColor("Button.background"));
   		    }
   			return butt;	
   		}
   	}
	private class JTableButtonMouseListener extends MouseAdapter {
   		private final JTable table;
   		public JTableButtonMouseListener(JTable table) {
   			this.table = table;
   		}

   		public void mouseClicked(MouseEvent e) {
   			int column = table.getColumnModel().getColumnIndexAtX(e.getX());
   			int row    = e.getY()/table.getRowHeight(); 

   			if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
   			    Object value = table.getValueAt(row, column);
   			    if (value instanceof JLabel || value instanceof Object) {
   			    table.setEnabled(false);
			    		

   			    }
   				
   			}
   		}
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
		updateUI();
	}
	public void payMTColor()
	{
		label_2.setForeground(new Color(SetColor.mtColor));
		label_3.setForeground(new Color(SetColor.mtColor));
	}

	public void payCColor()
	{
		label.setForeground(new Color(SetColor.cColor));
		lblAmount.setForeground(new Color(SetColor.cColor));
		lblDate.setForeground(new Color(SetColor.cColor));
		table.setForeground(new Color(SetColor.cColor));
	}
	public void paybkColor()
	{
		setBackground(new Color(SetColor.bkColor));
		table.setBackground(new Color(SetColor.bkColor));
		datePanel.setBackground(new Color(SetColor.bkColor));
		datePicker.setBackground(new Color(SetColor.bkColor));
		
	}
	public void paySTFont(String stFType,int fontProp,int stSize)
	{
		label_2.setFont(new Font(stFType,fontProp,stSize));
		label_3.setFont(new Font(stFType,fontProp,stSize));
	}

	public void payCTFont(String ctFType,int ctfProp,int ctSize)
	{
		label.setFont(new Font(ctFType,ctfProp,ctSize));
		lblDate.setFont(new Font(ctFType,ctfProp,ctSize));
		lblAmount.setFont(new Font(ctFType,ctfProp,ctSize));
		table.setFont(new Font(ctFType,ctfProp,ctSize));
		btnSearch.setFont(new Font(ctFType,ctfProp,ctSize));
		btnUpdate.setFont(new Font(ctFType,ctfProp,ctSize));
		btnPrint.setFont(new Font(ctFType,ctfProp,ctSize));
		btnNewButton.setFont(new Font(ctFType,ctfProp,ctSize));
	}


}
